/*
 * Name: Dennis Franks Jr.
 * Date: November 7, 2026
 * Assignment: SDC330L Week 5 Final Project
 * Description: Salaried employee with fixed pay.
 */
public class SalariedEmployee extends Employee implements Payable {
    private double salary;

    public SalariedEmployee(int id, String first, String last, double salary) {
        super(id, first, last);
        this.salary = salary;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String getEmployeeType() { return "Salaried"; }

    @Override
    public double calculatePay() { return salary; }

    @Override
    public String toString() {
        return super.toString() + "\nEmployee Type: " + getEmployeeType()
                + "\nSalary: $" + String.format("%.2f", salary);
    }
}
