# Sgcardmart
 
A simple backend service for user authentication and registration, order creation and card listing
 
## Usage
 
Please copy the uploads folder along with the src and pom.xml as the uploads is required for user to upload their card images.
DDL and DML are present in the resources folder directly. Application properties was instructed to use the uploads folder as well.
 
## Features
 
- **User Management**

  - User registration with email validation

  - Password matching

  - Retrieval of user details by email (ID)
 
- **Validation Utilities**

  - Regex‑based email format checker

  - Input validation for empty fields
 
- **Repositories**

  - All repositories extending`JpaRepositor` for CRUD operations
 
- **Services**

  - `UserService` encapsulating business logic for registration, login, and retrieval

  - `OrderService` encapsulating business logic for order creation and retrieval
  - `ProductService` encapsulating business logic for product retrieval and card listing
  - `OrderItemService` for OrderItem creation to hold product in the respective orders and its quantities.

 