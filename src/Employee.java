
/*
 * Name: Dennis Franks Jr.
 * Date: October 10, 2026
 * Assignment: SDC330L Week 1 - Employee Management
 * Description: Base Employee class that stores employee
 * information and demonstrates inheritance and composition.
 */

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private Department department;

    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Demonstrates composition:
    // An Employee contains a Department object.
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        String information = "Employee ID: " + employeeId
                + "\nFirst Name: " + firstName
                + "\nLast Name: " + lastName;

        if (department != null) {
            information += "\nDepartment: "
                    + department.getDepartmentName();
        }

        return information;
    }
}
