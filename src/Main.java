
/*
 * Name: Dennis Franks Jr.
 * Date: October 24, 2026
 * Assignment: SDC330L Week 3 Project
 * Description: Main application demonstrating abstraction,
 * constructors, access specifiers, inheritance,
 * composition, interfaces, and polymorphism.
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("SDC330L - WEEK 3 PROJECT");
        System.out.println("Abstraction, Constructors, & Access Specifiers");
        System.out.println("Employee Management Application");
        System.out.println("Created by: Dennis Franks Jr.");
        System.out.println("======================================");

        System.out.println("\nWelcome to Employee Management!");
        System.out.println("This program displays employee and department information.");
        System.out.println("Select an option from the menu below.");

        // Demonstrates a parameterized constructor.
        Department department = new Department(
                101, "Information Technology");

        // Demonstrates inherited constructors and initialization.
        HourlyEmployee employee1 = new HourlyEmployee(
                1001, "Dennis", "Franks Jr.", 25.00, 40.0);

        HourlyEmployee employee2 = new HourlyEmployee(
                1002, "John", "Smith", 20.00, 35.0);

        // Demonstrates an overloaded constructor.
        HourlyEmployee employee3 = new HourlyEmployee(
                1003, "Jane", "Doe");

        // Access specifiers: Public setter methods provide
        // controlled access to private employee fields.
        employee3.setHourlyRate(22.00);
        employee3.setHoursWorked(30.0);

        // Composition: Employees contain a Department object.
        employee1.setDepartment(department);
        employee2.setDepartment(department);
        employee3.setDepartment(department);

        // Abstraction and polymorphism:
        // Employee is abstract, so objects are created from
        // HourlyEmployee and referenced through Employee.
        Employee[] employees = {employee1, employee2, employee3};

        // Interface polymorphism:
        // Payable references can call calculatePay().
        Payable[] payableEmployees = {employee1, employee2, employee3};

        int choice = 0;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Display Employee Information");
            System.out.println("2. Display Department Information");
            System.out.println("3. Display Employee Pay");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n***** Employee Information *****");

                    // Polymorphism calls the subclass toString().
                    for (Employee employee : employees) {
                        System.out.println(employee);
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.println("\n***** Department Information *****");
                    System.out.println(department);
                    break;

                case 3:
                    System.out.println("\n***** Employee Pay *****");

                    // Interface references calculate each employee's pay.
                    for (int i = 0; i < payableEmployees.length; i++) {
                        System.out.println("Employee ID: "
                                + employees[i].getEmployeeId());
                        System.out.printf("Total Pay: $%.2f%n",
                                payableEmployees[i].calculatePay());
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.println("\nThank you for using Employee Management!");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }

        } while (choice != 4);

        input.close();
    }
}
