
/************************************************************
 * Name: Dennis Franks Jr.
 * Date: November 7, 2026
 * Assignment: SDC330L Week 5 Final Project
 * Application Delivery
 *
 * Description: Employee Management Application using
 * abstraction, inheritance, composition, constructors,
 * interfaces, polymorphism, and SQLite database operations.
 ************************************************************/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("EMPLOYEE MANAGEMENT FINAL PROJECT");
        System.out.println("Application Delivery");
        System.out.println("Employee Management Application");
        System.out.println("Created by: Dennis Franks Jr.");
        System.out.println("======================================");

        System.out.println("\nWelcome to Employee Management!");
        System.out.println("This program manages employee information.");
        System.out.println("Select an option from the menu below.");

        // Creates the SQLite database tables.
        DatabaseManager database = new DatabaseManager();
        database.createTables();

        // Constructor and composition demonstration.
        Department department = new Department(
                101, "Information Technology");

        // Demonstrates inheritance and constructors.
        HourlyEmployee employee1 = new HourlyEmployee(
                1001, "Dennis", "Franks Jr.", 25.00, 40.0);

        HourlyEmployee employee2 = new HourlyEmployee(
                1002, "John", "Smith", 20.00, 35.0);

        // Demonstrates an overloaded constructor.
        HourlyEmployee employee3 = new HourlyEmployee(
                1003, "Jane", "Doe");

        // Access specifiers: Setters modify private fields.
        employee3.setHourlyRate(22.00);
        employee3.setHoursWorked(30.0);

        // Composition: Employees reference a Department.
        employee1.setDepartment(department);
        employee2.setDepartment(department);
        employee3.setDepartment(department);

        // Abstraction and polymorphism.
        Employee[] employees = {
                employee1, employee2, employee3
        };

        // Interface polymorphism.
        Payable[] payableEmployees = {
                employee1, employee2, employee3
        };

        int choice = 0;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Display Employee Information");
            System.out.println("2. Display Department Information");
            System.out.println("3. Display Employee Pay");
            System.out.println("4. Add Department to Database");
            System.out.println("5. Add Employee to Database");
            System.out.println("6. Display Database Employees");
            System.out.println("7. Search Employees by Type");
            System.out.println("8. Update Employee Pay Information");
            System.out.println("9. Delete Employee");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println(
                        "Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.println(
                            "\n***** Employee Information *****");

                    // Polymorphism calls the subclass toString().
                    for (Employee employee : employees) {
                        System.out.println(employee);
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.println(
                            "\n***** Department Information *****");
                    System.out.println(department);
                    break;

                case 3:
                    System.out.println(
                            "\n***** Employee Pay *****");

                    // Interface references calculate employee pay.
                    for (int i = 0;
                         i < payableEmployees.length; i++) {

                        System.out.println("Employee ID: "
                                + employees[i].getEmployeeId());

                        System.out.printf("Total Pay: $%.2f%n",
                                payableEmployees[i].calculatePay());

                        System.out.println();
                    }
                    break;

                case 4:
                    // CREATE: Add a department to SQLite.
                    System.out.print("Enter Department ID: ");
                    int departmentId = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Department Name: ");
                    String departmentName = input.nextLine();

                    Department newDepartment = new Department(
                            departmentId, departmentName);

                    database.addDepartment(newDepartment);
                    break;

                case 5:
                    // CREATE: Add one of three employee types.
                    System.out.print("Enter Employee ID: ");
                    int employeeId = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter First Name: ");
                    String firstName = input.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = input.nextLine();
                    System.out.print("Employee Type (1 Hourly, 2 Salaried, 3 Commission): ");
                    int typeChoice = input.nextInt();
                    input.nextLine();

                    Employee newEmployee;
                    if (typeChoice == 1) {
                        System.out.print("Enter Hourly Rate: ");
                        double hourlyRate = input.nextDouble();
                        System.out.print("Enter Hours Worked: ");
                        double hoursWorked = input.nextDouble();
                        input.nextLine();
                        newEmployee = new HourlyEmployee(employeeId,
                                firstName, lastName, hourlyRate, hoursWorked);
                    } else if (typeChoice == 2) {
                        System.out.print("Enter Salary: ");
                        double salary = input.nextDouble();
                        input.nextLine();
                        newEmployee = new SalariedEmployee(employeeId,
                                firstName, lastName, salary);
                    } else if (typeChoice == 3) {
                        System.out.print("Enter Sales Amount: ");
                        double sales = input.nextDouble();
                        System.out.print("Enter Commission Rate (e.g. 0.10): ");
                        double commissionRate = input.nextDouble();
                        input.nextLine();
                        newEmployee = new CommissionEmployee(employeeId,
                                firstName, lastName, sales, commissionRate);
                    } else {
                        System.out.println("Invalid employee type.");
                        break;
                    }

                    System.out.print("Enter Department ID (0 for none): ");
                    int employeeDepartmentId = input.nextInt();
                    input.nextLine();
                    if (employeeDepartmentId != 0) {
                        newEmployee.setDepartment(new Department(
                                employeeDepartmentId, "Assigned Department"));
                    }
                    database.addEmployee(newEmployee);
                    break;

                case 6:
                    // READ: Display saved database employees.
                    System.out.println(
                            "\n***** Database Employees *****");
                    database.displayEmployees();
                    break;

                case 7:
                    // READ: Filter database employees by type.
                    System.out.print("Enter Employee Type: ");
                    String employeeType = input.nextLine();

                    database.displayEmployeesByType(employeeType);
                    break;

                case 8:
                    // UPDATE: Change employee name and type-specific pay.
                    System.out.print("Enter Employee ID: ");
                    int updateId = input.nextInt();
                    input.nextLine();
                    String currentType = database.getEmployeeType(updateId);
                    if (currentType == null) {
                        System.out.println("Employee not found.");
                        break;
                    }
                    System.out.print("Enter New First Name: ");
                    String newFirst = input.nextLine();
                    System.out.print("Enter New Last Name: ");
                    String newLast = input.nextLine();
                    double firstValue;
                    double secondValue = 0;
                    if ("Hourly".equalsIgnoreCase(currentType)) {
                        System.out.print("Enter New Hourly Rate: ");
                        firstValue = input.nextDouble();
                        System.out.print("Enter New Hours Worked: ");
                        secondValue = input.nextDouble();
                    } else if ("Salaried".equalsIgnoreCase(currentType)) {
                        System.out.print("Enter New Salary: ");
                        firstValue = input.nextDouble();
                    } else {
                        System.out.print("Enter New Sales Amount: ");
                        firstValue = input.nextDouble();
                        System.out.print("Enter New Commission Rate (e.g. 0.10): ");
                        secondValue = input.nextDouble();
                    }
                    input.nextLine();
                    database.updateEmployee(updateId, newFirst, newLast,
                            firstValue, secondValue);
                    break;

                case 9:
                    // DELETE: Remove an employee from SQLite.
                    System.out.print(
                            "Enter Employee ID to Delete: ");
                    int deleteId = input.nextInt();
                    input.nextLine();

                    database.deleteEmployee(deleteId);
                    break;

                case 10:
                    System.out.println(
                            "\nThank you for using Employee Management!");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-10.");
            }

        } while (choice != 10);

        input.close();
    }
}
