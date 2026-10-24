
/*
 * Name: Dennis Franks Jr.
 * Date: October 24, 2026
 * Assignment: SDC330L Week 3 Project
 * Description: HourlyEmployee extends the abstract Employee
 * class and demonstrates inheritance, constructors,
 * access specifiers, abstraction, and polymorphism.
 */

// Inheritance: HourlyEmployee extends the abstract Employee class.
public class HourlyEmployee extends Employee implements Payable {

    // Private fields protect employee pay information.
    private double hourlyRate;
    private double hoursWorked;

    // Parameterized constructor initializes employee information.
    public HourlyEmployee(int employeeId, String firstName,
                          String lastName, double hourlyRate,
                          double hoursWorked) {
        super(employeeId, firstName, lastName);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Overloaded constructor supplies default pay information.
    public HourlyEmployee(int employeeId, String firstName,
                          String lastName) {
        this(employeeId, firstName, lastName, 0.0, 0.0);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Abstraction: Implements the method required by Employee.
    @Override
    public String getEmployeeType() {
        return "Hourly";
    }

    // Implements the Payable interface.
    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    // Polymorphism: Overrides the Employee toString() method.
    @Override
    public String toString() {
        return super.toString()
                + "\nEmployee Type: " + getEmployeeType()
                + "\nHourly Rate: $" + String.format("%.2f", hourlyRate)
                + "\nHours Worked: " + hoursWorked
                + "\nTotal Pay: $" + String.format("%.2f", calculatePay());
    }
}
