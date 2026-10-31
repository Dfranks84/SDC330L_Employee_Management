# SDC330L Employee Management Application

**Student:** Dennis Franks Jr.  
**Course:** SDC330L – Advanced Object-Oriented Programming Using Java Lab  
**Project:** Employee Management Application  
**Current Phase:** Week 3 – Abstraction, Constructors, and Access Specifiers  
**Date:** October 24, 2026

## Project Overview

This Java application demonstrates object-oriented programming concepts through an Employee Management System. The project is developed over five weeks, with each phase building on the previous week's work.

The application currently uses a console-based menu to display employee information, department details, and employee pay calculations.

## Features

- Displays employee information, including employee ID, first name, last name, department, hourly rate, and hours worked.
- Displays department information.
- Calculates employee pay based on hourly rate and hours worked.
- Uses a menu that continues until the user chooses to exit.
- Demonstrates abstraction, inheritance, composition, interfaces, polymorphism, constructors, and encapsulation.

## Java Classes

**Employee.java**  
An abstract parent class containing shared employee information, private fields, constructors, getters, setters, and an abstract `getEmployeeType()` method.

**HourlyEmployee.java**  
Extends Employee and implements Payable. Demonstrates inheritance, overloaded constructors, method overriding, and employee pay calculations.

**Department.java**  
Stores department information and demonstrates encapsulation, constructors, and composition through its relationship with Employee.

**Payable.java**  
An interface defining the `calculatePay()` method.

**Main.java**  
Contains the main application, creates sample employee objects, and provides the console menu for user interaction.

## Object-Oriented Programming Concepts

### Abstraction
The abstract Employee class provides common employee information while requiring subclasses to implement the `getEmployeeType()` method.

### Inheritance
HourlyEmployee extends Employee and inherits shared employee properties and methods.

### Composition
Employee objects contain a reference to a Department object.

### Polymorphism
Employee references are used to access HourlyEmployee objects, and Payable references are used to calculate employee pay.

### Constructors
The application demonstrates parameterized, no-argument, and overloaded constructors to initialize objects.

### Access Specifiers
Private fields protect employee and department information. Public getters and setters provide access to that information.

## Application Menu

1. Display Employee Information
2. Display Department Information
3. Display Employee Pay
4. Exit

## Running the Application

1. Open the project in Visual Studio Code with Java support installed.
2. Open `src/Main.java`.
3. Click **Run Java** above the main method.
4. Follow the instructions displayed in the terminal.

The application uses sample employee data for demonstration purposes.

## Project Development Phases

| Week | Project Focus | Status |
|---|---|---|
| Week 1 | Inheritance and Composition | Completed |
| Week 2 | Polymorphism and Interfaces | Completed |
| Week 3 | Abstraction, Constructors, and Access Specifiers | Completed |
| Week 4 | SQLite Database Implementation | Planned |
| Week 5 | Final Testing, Documentation, and Submission | Planned |

## Testing

The Week 3 application was tested successfully using the following menu options:

- Option 1: Displayed three employee records.
- Option 2: Displayed department information.
- Option 3: Calculated employee pay correctly.
- Option 4: Exited the application successfully.

## Future Development

Week 4 will introduce SQLite database functionality. The final project will expand
