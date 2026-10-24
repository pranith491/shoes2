PRANITH STORE - START HERE (TELUGU + ENGLISH)
================================================

IMPORTANT:
This ZIP contains a complete beginner-friendly frontend + Spring Boot backend starter.
The frontend is plain HTML/CSS/JavaScript. Backend uses Java 17+, Spring Boot, Spring Security,
JWT, BCrypt and MySQL. Product images use remote HTTPS URLs, so internet is needed for them.

A) SOFTWARE NEEDED
1. Java JDK 17 or newer
2. MySQL Server running
3. VS Code
4. VS Code extensions:
   - Extension Pack for Java
   - Spring Boot Extension Pack (optional)
   - Live Server
5. Maven. Check with: mvn -version
   If Maven is missing, install Apache Maven or create/import a Maven Wrapper.

B) PROJECT STRUCTURE
Pranith-Store/
  frontend/
    index.html
    style.css
    script.js
  backend/
    pom.xml
    src/main/java/com/pranith/store/...
    src/main/resources/application.properties

C) STEP 1: CREATE MYSQL DATABASE
Open MySQL Workbench and run:
CREATE DATABASE pranith_store CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'pranith_app'@'localhost' IDENTIFIED BY 'ChangeThis_DB_Password_123!';
GRANT SELECT, INSERT, UPDATE, DELETE ON pranith_store.* TO 'pranith_app'@'localhost';

If you already created this user/database, don't create again. Ensure application.properties has the correct DB username/password.

D) STEP 2: SET A STRONG JWT SECRET (LOCAL DEVELOPMENT)
Open PowerShell and run:
$bytes = New-Object byte[] 32
[System.Security.Cryptography.RandomNumberGenerator]::Fill($bytes)
$env:JWT_SECRET = [Convert]::ToBase64String($bytes)
Keep that same terminal open while running Spring Boot.
The starter has a development-only fallback secret; do not deploy with that fallback.

E) STEP 3: RUN BACKEND
Open VS Code -> File -> Open Folder -> select the Pranith-Store folder.
Open Terminal -> New Terminal:
cd backend
mvn spring-boot:run

Wait until Spring Boot says it started. API base URL: http://localhost:8080

F) STEP 4: RUN FRONTEND
Open a second terminal in VS Code:
Open frontend/index.html, right-click -> Open with Live Server.
Expected URL: http://127.0.0.1:5500
If Live Server uses another port, update app.cors.allowed-origin in backend application.properties and restart backend.

G) STEP 5: REGISTER / LOGIN
1. Register from the website.
2. Login.
3. Add products to cart.
4. Place order.
5. My Orders shows your own orders.
The JWT token is held in JavaScript memory for this learning demo; refreshing the page signs you out.

H) STEP 6: PROMOTE YOUR OWN ACCOUNT TO ADMIN
Register and log in once as a normal user. In MySQL Workbench run:
UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';
Replace the email with your own registered email.
Logout/login again to receive a token with the new role.
Never let public users select their own role. Keep admin access controlled in the backend.

I) ADMIN FEATURES
- Add product
- Soft-delete product
- View all orders
- Update order status
Backend verifies ADMIN role. Hiding a button in the browser is not security by itself.

J) API ENDPOINTS
POST /api/auth/register
POST /api/auth/login
GET  /api/products
POST /api/products               ADMIN
PUT  /api/products/{id}          ADMIN
DELETE /api/products/{id}        ADMIN
POST /api/orders                 login required
GET  /api/orders/mine             login required
GET  /api/orders/admin/all        ADMIN
PUT  /api/orders/admin/{id}/status ADMIN

K) COMMON ERRORS
- "mvn is not recognized": Maven is not installed or PATH is not set.
- "Access denied for user": MySQL username/password in application.properties don't match.
- "Communications link failure": MySQL server is not running or port is wrong.
- CORS error: ensure frontend URL matches app.cors.allowed-origin and restart backend.
- 401: log in again.
- 403 on admin: promote your account in MySQL, then log out and back in.
- No products: admin login -> add products. Public product listing is empty until products are added.

SECURITY / PRODUCTION WARNING
This is a learning starter, not a security-audited production commerce platform.
Before public deployment: HTTPS, managed secrets, secure token/cookie strategy, rate limiting,
database migrations, backups, stronger input validation, monitoring and payment-provider integration are needed.
Do not collect/store card numbers or CVVs in this project.
