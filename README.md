
**Problem Statement**

Develop a RESTful API service using Spring Boot to manage an online book rental system while using MySQL to persist the data.

[RentAndRead.postman_collection.json](https://github.com/mrstrange47/krayush25_rent_and_read/files/14889354/RentAndRead.postman_collection.json)

**Key Features**

The service must implement authentication and authorization

The service uses Basic Auth

The service must have two roles: USER and ADMIN

The service must have two types of API endpoints:

Public endpoints - Anyone can access (Ex. Registration, Login)

Private endpoints - Only authenticated users can access (Ex. GET all books)

The private endpoints also require authorization i.e. only users with specific permissions can access the endpoint (Ex. Creating (POST) a book is only allowed for the admin)

**User Registration and Login**

Users must be able to register by providing their email address and password

The password must be encrypted and stored using BCrypt

Fields: Email, Password, First Name, Last Name, Role

The Role must be defaulted to “User” if it is not specified

Registered users must log in using their email address and password

**Book Management**

Store and manage book details

Fields: Title, Author, Genre, Availability Status

Availability Status tells whether the book is available to rent or not

Any user can browse all the available books

Only the administrator is allowed to create, update, and delete books

**Rental Management**

Users must be able to rent books using the service

A user cannot have more than two active rentals i.e. the service should throw an error if a user requests to rent a book while already having two other book rentals

Users must be able to return books that they have rented
