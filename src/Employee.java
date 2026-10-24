
/*
 * Name: Dennis Franks Jr.
 * Date: October 24, 2026
 * Assignment: SDC330L Week 3 Project
 * Description: Abstract Employee class that stores shared
 * employee information and demonstrates abstraction,
 * constructors, access specifiers, and composition.
 */

// Abstraction: Employee is a base class that cannot
// be instantiated directly.
public abstract class Employee {

    // Private fields demonstrate encapsulation.
    private int employeeId;
    private String firstName;
    private String lastName;
    private Department department;

    // Parameterized constructor initializes employee information.
    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // No-argument constructor demonstrates constructor overloading.
    public Employee() {
        this(0, "Unknown", "Employee");
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

    // Composition: Each Employee can contain a Department object.
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // Abstract method: Subclasses must provide an employee type.
    public abstract String getEmployeeType();

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
