# 🔐 Full Stack Authentication System (Spring Boot + React)

A modern, secure, and scalable **full-stack authentication system** built using **Spring Boot** (backend) and **React.js** (frontend). This project implements **JWT-based authentication**, **OAuth2 login (Google & GitHub)**, and **interactive API documentation using Swagger**.

---

## 🚀 Features

### 🔑 Authentication & Security

* User Registration & Login using JWT
* Secure password hashing with BCrypt
* Stateless authentication using JWT tokens
* Role-Based Access Control (USER / ADMIN)
* Custom authentication & authorization filters

### 🌐 OAuth2 Integration

* Login with Google
* Login with GitHub
* OAuth2 user handling & account mapping

### 📄 API Documentation

* Interactive API documentation using Swagger UI
* Easy testing of secured endpoints

### 🖥️ Frontend (React.js)

* Modern UI built with React
* Login / Signup forms
* OAuth login buttons (Google & GitHub)
* Token handling & protected routes
* API integration with backend

### ⚙️ Backend (Spring Boot)

* RESTful API architecture
* Spring Security configuration using SecurityFilterChain
* JWT token generation & validation
* UserDetailsService implementation
* Database integration with JPA & Hibernate

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* OAuth2 Client (Google & GitHub)
* Spring Data JPA (Hibernate)
* MySQL / MariaDB
* Swagger (SpringDoc OpenAPI)

### Frontend

* React.js
* Axios
* Tailwind CSS / CSS
* React Router

---

## 📂 Project Structure

```
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── security/
│   │   ├── jwt/
│   │   ├── oauth/
│   └── config/
│
├── frontend/
│   ├── components/
│   ├── pages/
│   ├── services/
│   └── utils/
```

---

## 🔐 Authentication Flow

### JWT Authentication

1. User registers or logs in
2. Server validates credentials
3. JWT token is generated and returned
4. Client stores token (localStorage/sessionStorage)
5. Token is sent in Authorization header for protected APIs

### OAuth2 Login

1. User clicks Google/GitHub login
2. Redirect to OAuth provider
3. Successful authentication returns user info
4. Backend generates JWT token
5. User is logged into the system

---
