# Employee Management Application

**Student:** Dennis Franks Jr.  
**Course:** SDC330L – Advanced Object-Oriented Programming Using Java Lab  
**Project:** Employee Management Application  
**Current Phase:** Week 5 – Final Application Delivery  
**Date:** November 7, 2026

## Project Overview

This Java application demonstrates object-oriented programming concepts through an Employee Management System. The project was developed over five weeks, with each phase building on the previous week's work.

The completed application uses a console-based menu to manage employee and department information. It supports Hourly, Salaried, and Commission employees and uses an SQLite database to store employee records.

## Features

- Displays employee information and department details.
- Calculates employee pay.
- Supports Hourly, Salaried, and Commission employees.
- Adds employees to the SQLite database.
- Displays employee records stored in the database.
- Searches for employees by type.
- Updates employee pay information.
- Deletes employee records.
- Adds departments to the database.
- Uses a repeating menu until the user selects Exit.
- Demonstrates abstraction, inheritance, composition, interfaces, polymorphism, constructors, and encapsulation.

## Java Classes

**Employee.java**  
An abstract parent class containing shared employee information, constructors, getters, setters, and an abstract `getEmployeeType()` method.

**HourlyEmployee.java**  
Extends Employee and implements Payable. Represents employees paid according to an hourly rate and hours worked.

**SalariedEmployee.java**  
Extends Employee and represents employees receiving a fixed salary.

**CommissionEmployee.java**  
Extends Employee and represents employees receiving pay based on sales and commission rates.

**Department.java**  
Stores department information and demonstrates encapsulation, constructors, and composition.

**Payable.java**  
An interface defining the `calculatePay()` method.

**DatabaseManager.java**  
Manages SQLite database initialization and employee database operations, including adding, displaying, searching, updating, and deleting employee records.

**Main.java**  
Contains the main application, sample employee functionality, and console-based menu for user interaction.

## Object-Oriented Programming Concepts

### Abstraction

The abstract Employee class provides common employee information while requiring subclasses to implement the `getEmployeeType()` method.

### Inheritance

HourlyEmployee, SalariedEmployee, and CommissionEmployee extend the Employee class and inherit shared employee properties and methods.

### Composition

Employee objects maintain a relationship with Department objects.

### Polymorphism

The application uses employee objects of different subclasses to demonstrate polymorphism and uses the Payable interface for employee pay calculations.

### Constructors

The application demonstrates constructors for initializing employee and department objects.

### Access Specifiers

Private fields protect employee and department information. Public methods provide controlled access to employee data.

### Database Integration

SQLite provides persistent storage for employee information. DatabaseManager handles database operations while the main application provides the user interface.

## Application Menu

1. Display Employee Information
2. Display Department Information
3. Display Employee Pay
4. Add Department to Database
5. Add Employee to Database
6. Display Database Employees
7. Search Employees by Type
8. Update Employee Pay Information
9. Delete Employee
10. Exit

## Running the Application

1. Open the project folder in Visual Studio Code.
2. Ensure Java and the SQLite JDBC driver are configured.
3. Open `src/Main.java`.
4. Select **Run Java**.
5. Follow the menu instructions in the terminal.
6. Select option 10 to exit the application.

The SQLite database stores employee information between application sessions.

## Project Development Phases

| Week | Project Focus | Status |
|---|---|---|
| Week 1 | Inheritance and Composition | Completed |
| Week 2 | Polymorphism and Interfaces | Completed |
| Week 3 | Abstraction, Constructors, and Access Specifiers | Completed |
| Week 4 | SQLite Database Implementation | Completed |
| Week 5 | Final Testing, Documentation, and Submission | In Progress |

## Testing

The following application functionality was successfully tested during Week 5:

- Java source files compiled without errors.
- Added a Salaried employee to the SQLite database.
- Added a Commission employee to the SQLite database.
- Added an Hourly employee to the SQLite database.
- Displayed saved employee records.
- Searched for employees by employee type.
- Updated an existing employee's salary.
- Deleted an employee from the database.
- Verified that deleted employee records no longer appeared.
- Exited the application successfully.

These tests confirmed the primary employee management functions. Additional testing may be performed as needed.



## Project Summary

The Employee Management Application was developed as a five-week Java programming project to demonstrate the practical application of object-oriented programming principles.

During development, the application progressed from basic employee and department classes to a console-based employee management system with SQLite database functionality. The completed application