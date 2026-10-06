package hospital.records;

import hospital.staff.Staff;

/**
 * Doctor can VIEW and UPDATE patient medical information.
 *
 * WHY Doctor IS IN hospital.records:
 *  Patient's medical methods are PACKAGE-PRIVATE (default). Only classes in the
 *  SAME package (hospital.records) can call them. Putting Doctor here is how we
 *  grant doctors - and nobody else - access to medical data.
 *
 * Doctor also extends Staff (from hospital.staff), so it can use Staff's
 * PROTECTED fields (staffId, name) even though it is in a different package.
 */
public class Doctor extends Staff {

    // PRIVATE: specialization is the doctor's own data; read via a public getter.
    private String specialization;

    public Doctor(String staffId, String name, String specialization) {
        super(staffId, name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    protected String describeRole() {
        return "Doctor, " + specialization + " (full medical access)";
    }

    // PUBLIC: any part of the system may ASK a doctor to show a record,
    // but the medical data is only read here, inside the records package.
    public void viewMedicalRecord(Patient patient) {
        System.out.println("  Dr. " + name + " views medical record of " + patient.getName() + ":");
        System.out.println("    Diagnosis    : " + patient.getDiagnosis());      // package-private -> OK
        System.out.println("    History      : " + patient.getMedicalHistory());
        System.out.println("    Prescription : " + patient.getPrescription());
    }

    public void updateMedicalRecord(Patient patient, String diagnosis, String prescription) {
        System.out.println("  Dr. " + name + " updates record of " + patient.getName());
        patient.setDiagnosis(diagnosis);                                  // package-private -> OK
        patient.setPrescription(prescription);
        patient.addToHistory(stamp(diagnosis));
    }

    // PRIVATE helper: internal formatting, nobody else needs it.
    private String stamp(String diagnosis) {
        return diagnosis + " (by Dr. " + name + ", " + staffId + ")";
    }
}
