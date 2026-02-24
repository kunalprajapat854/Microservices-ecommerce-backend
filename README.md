# 🛒 E-Commerce Backend – Microservices Architecture

<p align="center">
  <img src="E-commerce microservices architecture diagram.png" width="900"/>
</p>

<p align="center">
  <b>Spring Boot • Spring Cloud • Microservices • Docker • MySQL</b>
</p>

---

## 📌 Overview

A scalable and production-ready **E-Commerce Backend System** built using  
**Spring Boot Microservices Architecture**.

This project follows:

- Clean Architecture  
- RESTful APIs  
- Centralized Configuration  
- Service-to-Service Communication  
- Cloud-Native Design Principles  

---

## 🏗️ Architecture

The system is designed using independent microservices behind an API Gateway.

### Core Components:

- **API Gateway (Spring Cloud Gateway)**
- **Service Registry (Eureka)**
- **Config Server**
- **User Service**
- **Product Service**
- **Order Service**
- **Payment Service**
- Independent MySQL databases per service

---

## 🧱 Microservices

### User Service
- Registration
- Authentication
- JWT Token Generation
- Role-Based Access

### Product Service
- Product Management
- Inventory Handling
- Category Management

### Order Service
- Order Placement
- Order History
- Status Tracking

### Payment Service
- Payment Processing
- Payment Status
- Order Integration

---

## ⚙️ Tech Stack

| Layer | Technology |
|--------|------------|
| Backend | Java 17 |
| Framework | Spring Boot |
| Microservices | Spring Cloud |
| API Gateway | Spring Cloud Gateway |
| Service Discovery | Eureka |
| Database | MySQL |
| ORM | Spring Data JPA |
| Security | Spring Security + JWT |
| Build Tool | Maven |
| Containerization | Docker |

---

## 🔄 Inter-Service Communication

- REST
- OpenFeign
- Synchronous HTTP Calls  
- *(Future: Kafka – Event Driven Architecture)*

---

## 🔐 Security

- JWT Authentication  
- Role-Based Authorization  
- BCrypt Password Encryption  

---

## 🗄️ Database Strategy

Each microservice has its own isolated database:

```
user_db
product_db
order_db
payment_db
```

---

## 🚀 Run Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-microservices.git
```

### 2️⃣ Start Services (Order Matters)

1. Config Server  
2. Eureka Server  
3. API Gateway  
4. All Microservices  

### 3️⃣ Run Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ Access Gateway

```
http://localhost:8080
```

---

## 🐳 Docker

```bash
docker-compose up --build
```

---

## 📊 Future Enhancements

- Redis Caching  
- Kafka Integration  
- Kubernetes Deployment  
- Prometheus & Grafana Monitoring  
- Distributed Tracing (Zipkin)

---

## 👨‍💻 Author

**Kunal Prajapat**  
Java Backend Developer  

🔗 LinkedIn:  
https://www.linkedin.com/in/kunal-prajapat-java-dev/

---
