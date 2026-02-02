###Run app (root)###
./gradlew bootRun
./gradlew clean bootRun รัน+clear cache

###Problem Found###
1. bootRun ไม่ start แอป — แสดง "7 tasks: 7 up-to-date"
สาเหตุ: Gradle cache ทำให้ bootRun task ถูก skip ไม่ได้รันจริง
แก้: เพิ่มใน build.gradle.kts:64-66

tasks.named<BootRun>("bootRun") {
    outputs.upToDateWhen { false }
}

2. Schema validation: wrong column type (uuid vs varchar)
สาเหตุ: Entity ใช้ String id แต่คอลัมน์ใน DB เป็น uuid → Hibernate validate ไม่ผ่าน
แก้ครั้งแรก: เพิ่ม @Column(columnDefinition = "uuid") — ผ่าน validate แต่ยังมีปัญหาตอน INSERT (ปัญหาข้อ 5)

***
3. Schema validation: missing table [user_test]
สาเหตุ: เปลี่ยน @Table(name = "user_test") แต่ตารางไม่มีใน DB + ddl-auto: validate ไม่สร้างตารางให้
แก้: เปลี่ยน ddl-auto: update ให้ Hibernate สร้างตารางอัตโนมัติ
***

4. 401 Unauthorized
สาเหตุ: spring-boot-starter-security ล็อคทุก endpoint อัตโนมัติ
แก้: comment dependency security ออกไปก่อน

***
5. INSERT ไม่ได้ — uuid vs character varying
สาเหตุ: @Column(columnDefinition = "uuid") แก้ได้แค่ตอน validate schema แต่ตอน INSERT จริง Java ยังส่ง String ไป PostgreSQL ปฏิเสธ
แก้: เปลี่ยนเป็น UUID ทั้งระบบ — 4 ไฟล์:
UserData.java — String id → UUID id
UserRepo.java — JpaRepository<UserData, String> → <UserData, UUID>
UserDto.java — String id → UUID id
UserController.java — @PathVariable String id → UUID id
***

6. @Valid validation หยุดทำงาน
สาเหตุ: น่าจะเป็น AOT cache เก่าค้าง ไม่ได้ rebuild หลังแก้โค้ด
แก้: ./gradlew clean bootRun เพื่อล้าง cache แล้ว build ใหม่