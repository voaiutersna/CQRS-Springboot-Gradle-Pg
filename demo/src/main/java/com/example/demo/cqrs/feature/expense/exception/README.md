UserExistsQueryRepository.java — query port interface สำหรับ check email
PgUserExistsQuery.java — PostgreSQL implementation ใช้ SELECT EXISTS(SELECT 1 FROM users WHERE email = ?)

total_amount < 0 → throw ExpenseValidationException
email ไม่มีใน DB → throw UserNotFoundException
