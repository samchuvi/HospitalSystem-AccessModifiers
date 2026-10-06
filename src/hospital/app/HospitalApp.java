package hospital.app;

import hospital.admin.AdminDepartment;
import hospital.records.Doctor;
import hospital.records.Patient;
import hospital.staff.Receptionist;

/**
 * HospitalApp is the entry point. It is in its OWN package (hospital.app),
 * so it can only use the PUBLIC parts of every other class.
 * The commented-out lines show what access modifiers BLOCK at compile time.
 */
public class HospitalApp {

    public static void main(String[] args) {
        System.out.println("=== " + Patient.HOSPITAL_NAME + " - Patient Record System ===\n");

        Patient p1 = new Patient("P001", "Nakato Sarah", "2026-10-02");
        Patient p2 = new Patient("P002", "Okello James", "2026-10-05");

        Doctor doctor = new Doctor("D101", "Mugisha Peter", "General Medicine");
        Receptionist receptionist = new Receptionist("R201", "Achieng Grace");
        AdminDepartment admin = new AdminDepartment();

        System.out.println("1. STAFF (protected fields used by subclasses, shown via public method)");
        doctor.introduce();
        receptionist.introduce();

        System.out.println("\n2. RECEPTIONIST - public access to basic details only");
        receptionist.viewBasicDetails(p1);
        receptionist.rescheduleAppointment(p1, "2026-10-09");
        receptionist.rescheduleAppointment(p2, "9th October");   // rejected by private validation
        receptionist.viewBasicDetails(p1);

        System.out.println("\n3. DOCTOR - package-private access to medical data");
        doctor.viewMedicalRecord(p1);
        doctor.updateMedicalRecord(p1, "Malaria", "Artemether-Lumefantrine, 3 days");
        doctor.viewMedicalRecord(p1);

        System.out.println("\n4. ADMINISTRATION - financial records protected by private data + PIN");
        admin.createBill("ADM-2026", "P001", 250000, "Jubilee Health");
        admin.recordPayment("ADM-2026", "P001", 100000);
        admin.printFinancialReport("ADM-2026", "P001");

        System.out.println("\n5. UNAUTHORISED ATTEMPT (wrong PIN)");
        admin.printFinancialReport("1234", "P001");

        // ---------------- THESE LINES WOULD NOT COMPILE ----------------
        // p1.diagnosis = "Flu";            // ERROR: diagnosis is PRIVATE in Patient
        // p1.getDiagnosis();               // ERROR: getDiagnosis() is package-private (hospital.records only)
        // doctor.staffId = "X";            // ERROR: staffId is PROTECTED (subclasses only)
        // FinancialRecord f = null;        // ERROR: FinancialRecord is package-private (hospital.admin only)
        // admin.isAuthorised("ADM-2026");  // ERROR: isAuthorised() is PRIVATE in AdminDepartment

        System.out.println("\n=== End of demonstration ===");
    }
}
