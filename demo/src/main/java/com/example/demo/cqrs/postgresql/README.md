-- ====================================
-- 1. ตาราง users
-- ====================================
CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ====================================
-- 2. ตาราง category
-- ====================================
CREATE TABLE category (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  name VARCHAR(255) NOT NULL,
  describe VARCHAR(500),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  -- Foreign key constraint
  CONSTRAINT fk_category_user
    FOREIGN KEY (user_id) 
    REFERENCES users(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- ====================================
-- 3. ตาราง payment_status
-- ====================================
CREATE TABLE payment_status (
  id INT PRIMARY KEY,
  status VARCHAR(50) NOT NULL,
  payment_display VARCHAR(255) NOT NULL
);

-- Insert ข้อมูล default สำหรับ payment_status
INSERT INTO payment_status (id, status, payment_display) VALUES
  (1, 'unpaid', 'ยังไม่ได้ชำระ'),
  (2, 'paid', 'ชำระแล้ว'),
  (3, 'cancel', 'ยกเลิก');



-- ====================================
-- 4. ตาราง expense
-- ====================================
CREATE TABLE expense (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  total_amount DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  note VARCHAR(500),
  category_id UUID NOT NULL,
  pay_status_id INT NOT NULL DEFAULT 1,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  -- Foreign key constraints
  CONSTRAINT fk_expense_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_expense_category
    FOREIGN KEY (category_id)
    REFERENCES category(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_expense_payment_status
    FOREIGN KEY (pay_status_id)
    REFERENCES payment_status(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    

);


-- ====================================
-- Optional: สร้าง views สำหรับการ query ที่ใช้บ่อย
-- ====================================

-- View: ดูรายจ่ายพร้อมข้อมูลที่เกี่ยวข้องทั้งหมด
CREATE OR REPLACE VIEW v_expense_details AS
SELECT 
  e.id,
  e.user_id,
  u.name AS user_name,
  u.email AS user_email,
  e.total_amount,
  e.note,
  e.category_id,
  c.name AS category_name,
  c.describe AS category_description,
  e.pay_status_id,
  ps.status AS payment_status,
  ps.payment_display AS payment_display,
  e.created_at,
  e.updated_at
FROM expense e
INNER JOIN users u ON e.user_id = u.id
INNER JOIN category c ON e.category_id = c.id
INNER JOIN payment_status ps ON e.pay_status_id = ps.id;

-- View: สรุปยอดรายจ่ายแต่ละ category
CREATE OR REPLACE VIEW v_expense_summary_by_category AS
SELECT 
  c.id AS category_id,
  c.name AS category_name,
  c.user_id,
  COUNT(e.id) AS expense_count,
  COALESCE(SUM(e.total_amount), 0) AS total_amount,
  COALESCE(AVG(e.total_amount), 0) AS avg_amount
FROM category c
LEFT JOIN expense e ON c.id = e.category_id
GROUP BY c.id, c.name, c.user_id;

-- View: สรุปยอดรายจ่ายแต่ละ user
CREATE OR REPLACE VIEW v_expense_summary_by_user AS
SELECT 
  u.id AS user_id,
  u.name AS user_name,
  u.email,
  COUNT(e.id) AS expense_count,
  COALESCE(SUM(e.total_amount), 0) AS total_amount,
  COALESCE(SUM(CASE WHEN e.pay_status_id = 1 THEN e.total_amount ELSE 0 END), 0) AS unpaid_amount,
  COALESCE(SUM(CASE WHEN e.pay_status_id = 2 THEN e.total_amount ELSE 0 END), 0) AS paid_amount
FROM users u
LEFT JOIN expense e ON u.id = e.user_id
GROUP BY u.id, u.name, u.email;


-- ====================================
-- ตัวอย่างการ INSERT ข้อมูล
-- ====================================

-- Insert user ตัวอย่าง
INSERT INTO users (name, email, password) VALUES
  ('สมชาย ใจดี', 'somchai@example.com', '$2a$10$...hashed_password...'),
  ('สมหญิง รักสะอาด', 'somying@example.com', '$2a$10$...hashed_password...');

-- Insert category ตัวอย่าง
INSERT INTO category (user_id, name, describe) VALUES
  ((SELECT id FROM users WHERE email = 'somchai@example.com'), 'อาหาร', 'ค่าอาหารและเครื่องดื่ม'),
  ((SELECT id FROM users WHERE email = 'somchai@example.com'), 'ค่าเดินทาง', 'ค่าน้ำมัน ค่าทางด่วน'),
  ((SELECT id FROM users WHERE email = 'somchai@example.com'), 'ช้อปปิ้ง', 'ซื้อของใช้ส่วนตัว');

-- Insert expense ตัวอย่าง
INSERT INTO expense (user_id, category_id, total_amount, note, pay_status_id) VALUES
  (
    (SELECT id FROM users WHERE email = 'somchai@example.com'),
    (SELECT id FROM category WHERE name = 'อาหาร' LIMIT 1),
    350.00,
    'ข้าวเที่ยง ร้านข้าวแกง',
    2  -- paid
  ),
  (
    (SELECT id FROM users WHERE email = 'somchai@example.com'),
    (SELECT id FROM category WHERE name = 'ค่าเดินทาง' LIMIT 1),
    1500.00,
    'เติมน้ำมัน Shell',
    1  -- unpaid
  );