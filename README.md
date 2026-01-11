StayKeto Java Backend

🚀 Overview
This is the core RESTful API for StayKeto, a comprehensive nutritional platform. Built with Java Spring Boot, this service manages user authentication, metabolic tracking, and nutritional data persistence.

🛠 Tech Stack
Framework: Spring Boot 3.x

Language: Java 17+

Security: Spring Security & JWT (JSON Web Tokens)

Data Access: Spring Data JPA / Hibernate

Database: MySQL

Cloud: Google Cloud Platform (GCP) / Render / Railway (menciona donde lo hayas subido)

✨ Key Features
Secure Authentication: Robust login/signup system using JWT for stateless session management.

Habit Tracking System: CRUD operations for daily habits with user-specific data isolation.

Nutritional Logic: Business logic for calculating macros and monitoring ketosis states.

Relational Database: Optimized MySQL schema with JPA entities and relationships.

CORS Configuration: Securely integrated with the React frontend.

📂 Project Structure
Following the N-Layer Architecture:

controller/: REST endpoints and request handling.

service/: Business logic and service interfaces.

repository/: Data access layer (JPA).

model/: Database entities.

security/: JWT filters and security configuration.

🚦 Getting Started
Prerequisites
JDK 17 or higher

Maven

MySQL Server
