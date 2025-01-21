# Product Management

![GitHub Repo](https://github.com/pushkar666/PRODUCT-MANAGEMENT.git)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [APIs](#apis)
- [Technologies Used](#technologies-used)
- [License](#license)

## Introduction

The **Product Management** application is a Spring Boot-based system designed to manage products, GTINs (Global Trade Item Numbers), and their associated batches. It provides APIs to create, retrieve, and query data based on predefined conditions, including batch quantity and GTIN relationships.

## Features

1. **CRUD Operations** for Products, GTINs, and Batches.
2. Query GTINs based on available quantity in associated batches.
3. Retrieve the latest batch with negative or zero available quantity.
4. RESTful APIs for seamless integration.

## Project Structure

```
.
├── .gitattributes
├── .gitignore
├── code_block.png
├── HELP.md
├── LICENSE
├── mvnw, mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.product_management.product_management
│   │   │       ├── Controller
│   │   │       │   └── DataController.java
│   │   │       ├── entity
│   │   │       │   ├── Batch.java
│   │   │       │   ├── Gtin.java
│   │   │       │   └── Product.java
│   │   │       ├── repo
│   │   │       │   ├── BatchRepo.java
│   │   │       │   ├── GtinRepo.java
│   │   │       │   └── ProductRepo.java
│   │   │       └── service
│   │   │           └── DataService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com.product_management.product_management
│               └── ProductManagementApplicationTests.java
└───target
```

### Key Components

- **Entities**: Represent database tables (`Batch`, `Gtin`, `Product`).
- **Repositories**: Data access layer interfaces (`BatchRepo`, `GtinRepo`, `ProductRepo`).
- **Service Layer**: Business logic in `DataService`.
- **Controller**: REST APIs in `DataController`.

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- A relational database (e.g., MySQL, PostgreSQL)

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/pushkar666/PRODUCT-MANAGEMENT.git
   cd PRODUCT-MANAGEMENT
   ```

2. Update database configurations in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ``

   ```

3. Build the project using Maven:

   ```bash
   ./mvnw clean install
   ```

4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

5. Access APIs at `http://localhost:8080/api`.

## APIs

### 1. Create Data

- **Create Product**:

  - Endpoint: `POST /api/products`
  - Request Body:
    ```json
    {
      "productName": "Product A",
      "createdOn": "2025-01-20"
    }
    ```

- **Create GTIN**:

  - Endpoint: `POST /api/gtins`
  - Request Body:
    ```json
    {
      "gtin": "G1",
      "product": { "productId": 1 }
    }
    ```

- **Create Batch**:
  - Endpoint: `POST /api/batches`
  - Request Body:
    ```json
    {
      "mrp": 100,
      "sp": 80,
      "purchasePrice": 70,
      "availableQuantity": 10,
      "inwardedOn": "2025-01-18",
      "gtin": { "id": 1 }
    }
    ```

### 2. Fetch Data

- **Get GTIN by ID**:
  - Endpoint: `GET /api/gtins/{id}`
- **Get All GTINs**:
  - Endpoint: `GET /api/gtins`
- **Get GTINs with Positive Quantity Batches**:
  - Endpoint: `GET /api/gtins/positive-quantity`
- **Get Latest Batch with Negative or Zero Quantity**:
  - Endpoint: `GET /api/batches/negative-zero-latest`

## Technologies Used

- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL (or other relational DBs)
- **Build Tool**: Maven
- **Language**: Java

## License

This project is licensed under the [MIT License](LICENSE).
