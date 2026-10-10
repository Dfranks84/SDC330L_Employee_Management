
/*
 * Name: Dennis Franks Jr.
 * Date: October 10, 2026
 * Assignment: SDC330L Week 1 - Employee Management
 * Description: Department class that stores department
 * information and supports composition with Employee.
 */

public class Department {
    private int departmentId;
    private String departmentName;

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
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
