/*
 * Name: Dennis Franks Jr.
 * Date: November 7, 2026
 * Assignment: SDC330L Week 5 Final Project
 * Description: Commission employee paid a percentage of sales.
 */
public class CommissionEmployee extends Employee implements Payable {
    private double sales;
    private double commissionRate;

    public CommissionEmployee(int id, String first, String last,
                              double sales, double commissionRate) {
        super(id, first, last);
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    public double getSales() { return sales; }
    public void setSales(double sales) { this.sales = sales; }
    public double getCommissionRate() { return commissionRate; }
    public void setCommissionRate(double rate) { this.commissionRate = rate; }

    @Override
    public String getEmployeeType() { return "Commission"; }

    @Override
    public double calculatePay() { return sales * commissionRate; }

    @Override
    public String toString() {
        return super.toString() + "\nEmployee Type: " + getEmployeeType()
                + "\nSales: $" + String.format("%.2f", sales)
                + "\nCommission Rate: " + commissionRate
                + "\nTotal Pay: $" + String.format("%.2f", calculatePay());
    }
}
