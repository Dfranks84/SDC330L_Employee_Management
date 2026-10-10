
/*
 * Name: Dennis Franks Jr.
 * Date: October 10, 2026
 * Assignment: SDC330L Week 1 - Employee Management
 * Description: Main application demonstrating basic user
 * interaction, inheritance, and composition.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Display Week 1 project information
        System.out.println("======================================");
        System.out.println("SDC330L - WEEK 1 PROJECT");
        System.out.println("Employee Management Application");
        System.out.println("Created by: Dennis Franks Jr.");
        System.out.println("======================================");

        // Display welcome message and instructions
        System.out.println("\nWelcome to Employee Management!");
        System.out.println("This program displays employee and department information.");
        System.out.println("Select an option from the menu below.");

        // Create a department
        Department department = new Department(
                101, "Information Technology");

        // Demonstrates inheritance:
        // HourlyEmployee is a child class of Employee.
        HourlyEmployee employee = new HourlyEmployee(
                1001, "Dennis", "Franks Jr.", 25.00, 40.0);

        // Demonstrates composition:
        // The Employee object contains a Department object.
        employee.setDepartment(department);

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
                    System.out.println(employee);
                    break;

                case 2:
                    System.out.println("\n***** Department Information *****");
                    System.out.println(department);
                    break;

                case 3:
                    System.out.println("\n***** Employee Pay *****");
                    System.out.printf("Total Pay: $%.2f%n",
                            employee.calculatePay());
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
