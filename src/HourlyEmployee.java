
/*
 * Name: Dennis Franks Jr.
 * Date: October 17, 2026
 * Assignment: SDC330L Week 2 Project
 * Description: HourlyEmployee inherits from Employee
 * and implements the Payable interface.
 */

// Demonstrates inheritance and interface implementation.
public class HourlyEmployee extends Employee implements Payable {

    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(int employeeId, String firstName,
                          String lastName, double hourlyRate,
                          double hoursWorked) {
        super(employeeId, firstName, lastName);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
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

    // Implements the method defined in Payable.
    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nEmployee Type: Hourly"
                + "\nHourly Rate: $" + String.format("%.2f", hourlyRate)
                + "\nHours Worked: " + hoursWorked
                + "\nTotal Pay: $" + String.format("%.2f", calculatePay());
    }
}
