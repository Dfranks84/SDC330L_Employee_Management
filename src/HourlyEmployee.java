
/*
 * Name: Dennis Franks Jr.
 * Date: October 10, 2026
 * Assignment: SDC330L Week 1 - Employee Management
 * Description: HourlyEmployee class that inherits from
 * Employee and stores hourly pay and hours worked.
 * Demonstrates inheritance and method overriding.
 */

public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(int employeeId, String firstName,
                          String lastName, double hourlyRate,
                          double hoursWorked) {
        super(employeeId, firstName, lastName);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    // Getters and setters
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

    // Calculate employee's earnings
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
