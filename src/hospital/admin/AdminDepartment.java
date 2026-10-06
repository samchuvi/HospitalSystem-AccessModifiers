package hospital.admin;

import java.util.HashMap;
import java.util.Map;

/**
 * AdminDepartment is the ONLY gateway to financial records.
 *
 * ACCESS DESIGN:
 *  - financialRecords map is PRIVATE: no class can reach the raw data.
 *  - ADMIN_PIN is PRIVATE static final: the password must never leave this class.
 *  - Public methods (createBill, recordPayment, printFinancialReport) demand the
 *    admin PIN, which is checked by the PRIVATE method isAuthorised().
 *  - FinancialRecord objects are never returned to callers, so they cannot leak.
 */
public class AdminDepartment {

    private static final String ADMIN_PIN = "ADM-2026";
    private final Map<String, FinancialRecord> financialRecords = new HashMap<>();

    public void createBill(String pin, String patientId, double amount, String insurer) {
        if (!isAuthorised(pin)) return;
        financialRecords.put(patientId, new FinancialRecord(patientId, amount, insurer));
        System.out.println("  Admin: bill of UGX " + format(amount) + " created for " + patientId);
    }

    public void recordPayment(String pin, String patientId, double amount) {
        if (!isAuthorised(pin)) return;
        FinancialRecord record = financialRecords.get(patientId);
        if (record == null) {
            System.out.println("  Admin: no bill found for " + patientId);
            return;
        }
        record.recordPayment(amount);
        System.out.println("  Admin: payment of UGX " + format(amount) + " recorded for " + patientId);
    }

    public void printFinancialReport(String pin, String patientId) {
        if (!isAuthorised(pin)) return;
        FinancialRecord r = financialRecords.get(patientId);
        if (r == null) {
            System.out.println("  Admin: no bill found for " + patientId);
            return;
        }
        System.out.println("  FINANCIAL REPORT - " + r.getPatientId());
        System.out.println("    Insurance  : " + r.getInsuranceProvider());
        System.out.println("    Total Bill : UGX " + format(r.getTotalBill()));
        System.out.println("    Paid       : UGX " + format(r.getAmountPaid()));
        System.out.println("    Balance    : UGX " + format(r.getBalance()));
    }

    // PRIVATE: security check is an internal detail - callers must not skip or change it.
    private boolean isAuthorised(String pin) {
        if (ADMIN_PIN.equals(pin)) {
            return true;
        }
        System.out.println("  ACCESS DENIED: financial records are for the Administration Department only.");
        return false;
    }

    // PRIVATE helper for formatting money.
    private String format(double amount) {
        return String.format("%,.0f", amount);
    }
}
