# Catalog Management System

This project is a **Catalog Management System** built using **Spring Boot**. The system allows users to manage product information such as product name, brand, description, price, quantity, and category. The system supports **CRUD** (Create, Read, Update, Delete) operations for managing products.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Requirements](#requirements)
- [Setup Instructions](#setup-instructions)
- [Technologies Used](#technologies-used)
- [Product Entity](#product-entity)
- [API Endpoints](#api-endpoints)
- [Validation and Error Handling](#validation-and-error-handling)
- [Running the Application](#running-the-application)
- [Additional Notes](#additional-notes)

---

## Project Overview

The **Catalog Management System** allows users to perform CRUD operations on products. These operations are exposed through RESTful APIs that allow interaction with the database. Data validation is also applied to ensure correct input, and the application uses **MySQL** as the database for storing product information.

---

## Requirements

Before running the project, ensure you have the following installed:

- **Java**: Version 17 or higher.
- **Spring Boot**: Version 3.x or higher.
- **MySQL**: Installed and running on your machine.
- **Maven**: For managing project dependencies.

---

## Setup Instructions

### 1. Clone the repository:

Clone the project to your local machine using Git:

```bash
git clone https://github.com/yourusername/catalog-management-system.git
cd catalog-management-system
```
### 2. Install dependencies:

Ensure **Maven** is installed on your system. If Maven is not installed, you can follow the installation guide from the [official Maven website](https://maven.apache.org/install.html).

After Maven is installed, open your terminal or command prompt and navigate to the project directory where your `pom.xml` file is located. Run the following command to install the necessary dependencies:

```bash
mvn clean install
```
### 3. Run the Application:

After configuring MySQL and installing the necessary dependencies, you can run the application with the following command:

```bash
mvn spring-boot:run
```
# Catalog Management System

## Technologies Used

- **Spring Boot**: A framework for building Java applications with minimal configuration.
- **Spring Data JPA**: Simplifies database interactions with MySQL using JPA repositories.
- **Hibernate Validator**: Used for input validation using annotations.
- **MySQL**: A relational database management system used for storing product data.
- **Lombok**: Reduces boilerplate code (getters, setters, constructors) in the Java classes.

## Product Entity

The **Product entity** represents a product in the catalog. The entity has the following fields:

- **id**: Unique identifier for each product.
- **name**: The name of the product.
- **brand**: The brand of the product.
- **description**: A short description of the product.
- **price**: The price of the product.
- **quantity**: The number of items in stock.
- **category**: The category of the product (e.g., Electronics, Furniture).
- **dateAdded**: The date the product was added to the catalog.

### Validation:
Various fields in the Product entity are validated using annotations like `@NotBlank`, `@Positive`, and `@Size`.

## API Endpoints

The application exposes the following **RESTful API endpoints**:

1. **Create Product** (`POST /api/v1/product`)
   - **Description**: Creates a new product in the catalog.
   - **Request Body**: Product details such as name, brand, description, price, quantity, and category.
   - **Response**: Confirmation of product creation.

2. **Get Product by ID** (`GET /api/v1/product`)
   - **Description**: Fetches the product details by ID.
   - **Request Parameter**: `id` (Product ID).
   - **Response**: Returns the product details for the specified ID.



3. **Delete Product** (`DELETE /api/v1/product`)
   - **Description**: Deletes a product by ID.
   - **Request Parameter**: `id` (Product ID).
   - **Response**: Confirmation of product deletion.

4. **Get All Products by Brand** (`GET /api/v1/products/brand`)
- **Description**: Retrieves all products associated with a given brand, filtered by the `brand` query parameter.
- **Request Parameter**:
   - `brand` (Query Parameter): The ID of the brand to filter products by.
- **Response**:
   - **200 OK**: A list of products under the specified brand.
   - **404 Not Found**: If no products are found for the given brand ID.

### 5. **Get Products by Brand and Category**

#### **Endpoint**:
`GET /api/v1/products/brand/{brand}/category/{category}`

#### **Description**:
This endpoint retrieves a list of products under a specific **brand** and **category**. It filters the products based on the provided `brand` and `category` values.

#### **Path Parameters**:
- `brand` (required): The name of the brand for which products need to be fetched.
- `category` (required): The name of the category for which products need to be fetched.

## Validation and Error Handling

The application uses Java Validation API annotations like `@NotBlank`, `@NotNull`, and `@Positive` to ensure that the input is valid. If any of the validation constraints are violated, the application will respond with an appropriate HTTP status code and a message indicating what went wrong.

### Example of an error response:
```json
{
  "message": "Product name cannot be blank"
}



