
/*
 * Name: Dennis Franks Jr.
 * Date: October 24, 2026
 * Assignment: SDC330L Week 3 Project
 * Description: Department class demonstrating constructors,
 * access specifiers, encapsulation, and composition.
 */

public class Department {

    // Private fields demonstrate encapsulation.
    private int departmentId;
    private String departmentName;

    // Parameterized constructor initializes department information.
    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // No-argument constructor provides default values.
    public Department() {
        this(0, "Unassigned");
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department ID: " + departmentId
                + "\nDepartment Name: " + departmentName;
    }
}
