package hospital.admin;

/**
 * FinancialRecord holds billing information for one patient.
 *
 * ACCESS DESIGN:
 *  - The CLASS itself has NO modifier (package-private). Classes outside
 *    hospital.admin cannot even SEE that this class exists - they cannot
 *    declare a variable of this type. Only the administration department
 *    (AdminDepartment) can use it.
 *  - All fields are PRIVATE (encapsulation) and read through package-private methods.
 */
class FinancialRecord {

    private final String patientId;
    private double totalBill;
    private double amountPaid;
    private String insuranceProvider;

    FinancialRecord(String patientId, double totalBill, String insuranceProvider) {
        this.patientId = patientId;
        this.totalBill = totalBill;
        this.amountPaid = 0;
        this.insuranceProvider = insuranceProvider;
    }

    String getPatientId()         { return patientId; }
    double getTotalBill()         { return totalBill; }
    double getAmountPaid()        { return amountPaid; }
    String getInsuranceProvider() { return insuranceProvider; }

    double getBalance() {
        return totalBill - amountPaid;
    }

    void recordPayment(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("  Invalid payment amount: " + amount);
            return;
        }
        amountPaid += amount;
    }
}
