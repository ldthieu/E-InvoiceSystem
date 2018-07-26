# HOW TO RUN PROJECT
## Database: Mysql
* Create database name: eis
* Fix:
spring.datasource.username and spring.datasource.password in application.properties

## Run: Application.java by Application
## Default username: 
* admin@gmail.com - password: 123456 - role: ROLE_ADMIN, ROLE_MEMBER
* bvn@gmail.com - password: 123456 - role: ROLE_MEMBER
* ldth@gmail.com - password: 123456 - role: ROLE_MEMBER

# a. Description
E-Invoice is built for the purpose of monthly invoice management in a family. It helps us to have an overall report of monthly expenses so that user could make plan to save money. User could set an alert for a limited amount of money consumed every month. Each invoice will have following information:
* Type of invoice: Electric, internet, telephone, water, ...
* Amount of money.
* VAT
* Charged period (monthly).
* Invoice No.
* Customer code (optional).

# b. Functional Specification
## Users:
* Register account.
* CRUD services.
* CRUD invoice consumed by a service.
* View expenses report: Monthly, Yearly, or a period of time.

## Admin:
* Activate/Deactivate a user account.

## Decentralization::
* Login
* Logout
* User Registered

# Technical Requirements
* Framework: Spring MVC, Hibernate. (Bonus if: Spring Security, Applying Spring boot)
* Client side: AngularJS, Boostrap, HTML5/CSS3
* Database system: MySQL
* All CRUD operation should have JUnit tests.
* Sample data should be prepared before presentation
* Pagination on returned result in search function.
* Source control: GitHub
