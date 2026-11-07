
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
                + "hourly_rate REAL NOT NULL DEFAULT 0, "
                + "hours_worked REAL NOT NULL DEFAULT 0, "
                + "department_id INTEGER, "
                + "FOREIGN KEY (department_id) "
                + "REFERENCES departments(department_id))";

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON");
            statement.execute(departmentTable);
            statement.execute(employeeTable);
            // Add new columns to existing Week 4 databases without deleting records.
            addColumnIfMissing(connection, "salary", "REAL NOT NULL DEFAULT 0");
            addColumnIfMissing(connection, "sales", "REAL NOT NULL DEFAULT 0");
            addColumnIfMissing(connection, "commission_rate", "REAL NOT NULL DEFAULT 0");

            System.out.println("Database tables ready.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private void addColumnIfMissing(Connection connection, String name,
                                    String definition) throws SQLException {
        boolean exists = false;
        try (ResultSet columns = connection.getMetaData()
                .getColumns(null, null, "employees", name)) {
            exists = columns.next();
        }
        if (!exists) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("ALTER TABLE employees ADD COLUMN "
                        + name + " " + definition);
            }
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

    // CREATE: Stores all three employee types using prepared statements.
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees "
                + "(employee_id, first_name, last_name, employee_type, "
                + "hourly_rate, hours_worked, department_id, salary, sales, "
                + "commission_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getEmployeeType());

            double rate = 0, hours = 0, salary = 0, sales = 0, commission = 0;
            if (employee instanceof HourlyEmployee) {
                HourlyEmployee hourly = (HourlyEmployee) employee;
                rate = hourly.getHourlyRate();
                hours = hourly.getHoursWorked();
            } else if (employee instanceof SalariedEmployee) {
                salary = ((SalariedEmployee) employee).getSalary();
            } else if (employee instanceof CommissionEmployee) {
                CommissionEmployee worker = (CommissionEmployee) employee;
                sales = worker.getSales();
                commission = worker.getCommissionRate();
            }
            statement.setDouble(5, rate);
            statement.setDouble(6, hours);
            if (employee.getDepartment() == null) {
                statement.setNull(7, java.sql.Types.INTEGER);
            } else {
                statement.setInt(7, employee.getDepartment().getDepartmentId());
            }
            statement.setDouble(8, salary);
            statement.setDouble(9, sales);
            statement.setDouble(10, commission);
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
                printPayInformation(results);
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

    private void printPayInformation(ResultSet results) throws SQLException {
        String type = results.getString("employee_type");
        if ("Hourly".equalsIgnoreCase(type)) {
            System.out.println("Hourly Rate: $" + results.getDouble("hourly_rate"));
            System.out.println("Hours Worked: " + results.getDouble("hours_worked"));
        } else if ("Salaried".equalsIgnoreCase(type)) {
            System.out.println("Salary: $" + results.getDouble("salary"));
        } else if ("Commission".equalsIgnoreCase(type)) {
            System.out.println("Sales: $" + results.getDouble("sales"));
            System.out.println("Commission Rate: " + results.getDouble("commission_rate"));
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

    // UPDATE: Change name and pay information for any employee type.
    public void updateEmployee(int employeeId, String first, String last,
                               double firstPayValue, double secondPayValue) {
        String type = null;
        try (Connection connection = connect();
             PreparedStatement find = connection.prepareStatement(
                     "SELECT employee_type FROM employees WHERE employee_id = ?")) {
            find.setInt(1, employeeId);
            try (ResultSet results = find.executeQuery()) {
                if (results.next()) type = results.getString("employee_type");
            }
            if (type == null) {
                System.out.println("Employee not found.");
                return;
            }
            String sql;
            if ("Hourly".equalsIgnoreCase(type)) {
                sql = "UPDATE employees SET first_name=?, last_name=?, "
                        + "hourly_rate=?, hours_worked=? WHERE employee_id=?";
            } else if ("Salaried".equalsIgnoreCase(type)) {
                sql = "UPDATE employees SET first_name=?, last_name=?, "
                        + "salary=? WHERE employee_id=?";
            } else {
                sql = "UPDATE employees SET first_name=?, last_name=?, "
                        + "sales=?, commission_rate=? WHERE employee_id=?";
            }
            try (PreparedStatement update = connection.prepareStatement(sql)) {
                update.setString(1, first);
                update.setString(2, last);
                update.setDouble(3, firstPayValue);
                if ("Salaried".equalsIgnoreCase(type)) {
                    update.setInt(4, employeeId);
                } else {
                    update.setDouble(4, secondPayValue);
                    update.setInt(5, employeeId);
                }
                update.executeUpdate();
                System.out.println("Employee updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public String getEmployeeType(int employeeId) {
        String sql = "SELECT employee_type FROM employees WHERE employee_id=?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) return results.getString("employee_type");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
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
