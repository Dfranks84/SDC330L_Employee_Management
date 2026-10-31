
/************************************************************
 * Name: Dennis Franks Jr.
 * Date: October 31, 2026
 * Assignment: SDC330L Week 4 Project
 * Database Interactions
 *
 * Description: Manages the SQLite database connection,
 * creates the employee and department tables, and provides
 * methods for creating, reading, updating, and deleting
 * employee records.
 ************************************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    // Database location.
    private static final String DATABASE_URL =
            "jdbc:sqlite:employees.db";

    // Constructor demonstrates controlled object creation.
    public DatabaseManager() {
    }

    // Connects the Java application to the SQLite database.
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Creates the database tables if they do not exist.
    public void createTables() {

        String departmentTable =
                "CREATE TABLE IF NOT EXISTS departments ("
                + "department_id INTEGER PRIMARY KEY, "
                + "department_name TEXT NOT NULL)";

        String employeeTable =
                "CREATE TABLE IF NOT EXISTS employees ("
                + "employee_id INTEGER PRIMARY KEY, "
                + "first_name TEXT NOT NULL, "
                + "last_name TEXT NOT NULL, "
                + "employee_type TEXT NOT NULL, "
                + "hourly_rate REAL NOT NULL, "
                + "hours_worked REAL NOT NULL, "
                + "department_id INTEGER, "
                + "FOREIGN KEY (department_id) "
                + "REFERENCES departments(department_id))";

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON");
            statement.execute(departmentTable);
            statement.execute(employeeTable);

            System.out.println("Database tables ready.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // CREATE: Saves a department in the database.
    public void addDepartment(Department department) {

        String sql = "INSERT INTO departments "
                + "(department_id, department_name) VALUES (?, ?)";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, department.getDepartmentId());
            statement.setString(2, department.getDepartmentName());

            statement.executeUpdate();

            System.out.println("Department added successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // CREATE: Saves an hourly employee in the database.
    public void addEmployee(HourlyEmployee employee) {

        String sql = "INSERT INTO employees "
                + "(employee_id, first_name, last_name, "
                + "employee_type, hourly_rate, hours_worked, "
                + "department_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, employee.getEmployeeId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getEmployeeType());
            statement.setDouble(5, employee.getHourlyRate());
            statement.setDouble(6, employee.getHoursWorked());

            if (employee.getDepartment() != null) {
                statement.setInt(7,
                        employee.getDepartment().getDepartmentId());
            } else {
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // READ: Displays all employees stored in SQLite.
    public void displayEmployees() {

        String sql = "SELECT * FROM employees ORDER BY employee_id";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {

            boolean found = false;

            while (results.next()) {
                found = true;

                System.out.println("\nEmployee ID: "
                        + results.getInt("employee_id"));
                System.out.println("Name: "
                        + results.getString("first_name") + " "
                        + results.getString("last_name"));
                System.out.println("Employee Type: "
                        + results.getString("employee_type"));
                System.out.println("Hourly Rate: $"
                        + results.getDouble("hourly_rate"));
                System.out.println("Hours Worked: "
                        + results.getDouble("hours_worked"));
                System.out.println("Department ID: "
                        + results.getInt("department_id"));
            }

            if (!found) {
                System.out.println("No employees found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // READ: Displays employees matching a selected type.
    public void displayEmployeesByType(String employeeType) {

        String sql = "SELECT * FROM employees "
                + "WHERE employee_type = ? ORDER BY employee_id";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, employeeType);

            try (ResultSet results = statement.executeQuery()) {

                boolean found = false;

                while (results.next()) {
                    found = true;

                    System.out.println("\nEmployee ID: "
                            + results.getInt("employee_id"));
                    System.out.println("Name: "
                            + results.getString("first_name") + " "
                            + results.getString("last_name"));
                    System.out.println("Employee Type: "
                            + results.getString("employee_type"));
                }

                if (!found) {
                    System.out.println("No matching employees found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UPDATE: Changes an employee's hourly rate and hours.
    public void updateEmployee(int employeeId,
                               double hourlyRate,
                               double hoursWorked) {

        String sql = "UPDATE employees "
                + "SET hourly_rate = ?, hours_worked = ? "
                + "WHERE employee_id = ?";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, hourlyRate);
            statement.setDouble(2, hoursWorked);
            statement.setInt(3, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // DELETE: Removes an employee from the database.
    public void deleteEmployee(int employeeId) {

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
