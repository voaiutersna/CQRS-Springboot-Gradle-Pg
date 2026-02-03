###Use spring boot initializr for Create Project (its also can add dependencies)###

ADD: Spring Web, Validation, Spring Security, OAuth2 Client, JDBC API, PostgreSQL Driver, Mail, Lombok, DevTools, Configuration Processor, GraalVM Native Support, Testcontainers

Database Config to postgresql:
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cloud_db
    username: postgres
    password: xxxxxxx1234
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true

# Jpa for faster Test / Use Raw SQL for best performance
# validate — ตรวจสอบอย่างเดียว ไม่แตะ DB , ถ้าไม่ตรง (ตารางหาย, คอลัมน์ type ผิด) → error ทันที แอปไม่ start
# update — สร้าง/แก้ schema ให้อัตโนมัติ , ถ้าตารางไม่มี → สร้างให้ / ถ้าคอลัมน์ไม่มี → เพิ่มให้

Java Version ในเครื่อง Missmatch กับใน Project 
แก้: java -version ดู version ในเครื่อง
แก้ใน build.gradle.kts เป็น version ที่ตรงกัน
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

More Detial (JAVA:4.0.2)

Project Settings
Setting	Value
Project	Gradle - Kotlin DSL
Language	Java
Spring Boot	4.0.0
Java	25

Dependencies ที่ต้องเลือกใน Initializr
Core
ชื่อใน Initializr	Starter	หมายเหตุ
Spring Web	spring-boot-starter-webmvc	Web MVC
Validation	spring-boot-starter-validation	Bean Validation
Spring Security	spring-boot-starter-security	Authentication/Authorization
OAuth2 Client	spring-boot-starter-security-oauth2-client	OAuth2 login

Data
ชื่อใน Initializr	Starter	หมายเหตุ
JDBC API	spring-boot-starter-jdbc	JDBC (ไม่ใช้ JPA ใน production)
PostgreSQL Driver	postgresql	Runtime DB driver

Utilities
ชื่อใน Initializr	Starter	หมายเหตุ
Java Mail Sender	spring-boot-starter-mail	ส่งอีเมล
Lombok	lombok	Boilerplate reduction
Spring Boot DevTools	spring-boot-devtools	Hot reload (dev only)
Spring Configuration Processor	spring-boot-configuration-processor	Metadata สำหรับ config
GraalVM Native Support	graalvm-buildtools	Native image

Testing (จะมาพร้อม Initializr อยู่แล้วบางส่วน)
ชื่อใน Initializr	Starter	หมายเหตุ
Testcontainers	testcontainers-postgresql	Docker-based integration test
Flyway Migration	flyway-core + flyway-database-postgresql	ใช้เฉพาะใน test
Spring Data JPA	spring-boot-starter-data-jpa	ใช้เฉพาะใน test

สรุปสั้นๆ: เลือก Spring Web, Validation, Spring Security, OAuth2 Client, JDBC API, PostgreSQL Driver, Mail, Lombok, DevTools, Configuration Processor, GraalVM Native Support, Testcontainers ก็จะได้โปรเจกต์ที่ dependency ตรงกับที่มีอยู่ครับ