package hospital.records;

/**
 * Patient holds ALL information about one patient.
 *
 * ACCESS DESIGN:
 *  - Basic details (id, name, appointment date) -> PUBLIC getters,
 *    because receptionists and everyone else in the system may view them.
 *  - Medical details (diagnosis, history, prescription) -> PACKAGE-PRIVATE
 *    (no modifier) getters/setters, so ONLY classes inside hospital.records
 *    (i.e. Doctor) can reach them.
 *  - All fields are PRIVATE so nothing outside this class touches them directly.
 */
public class Patient {

    // PUBLIC constant: harmless, shared information everyone can read.
    public static final String HOSPITAL_NAME = "Mercy Private Hospital";

    // PRIVATE fields: data is hidden (encapsulation). Access only through methods.
    private final String patientId;
    private String name;
    private String appointmentDate;

    // Sensitive medical data - PRIVATE, never exposed publicly.
    private String diagnosis;
    private String medicalHistory;
    private String prescription;

    // PUBLIC constructor: any part of the system (e.g. reception) can register a patient.
    public Patient(String patientId, String name, String appointmentDate) {
        this.patientId = patientId;
        this.name = name;
        this.appointmentDate = appointmentDate;
        this.diagnosis = "Not yet diagnosed";
        this.medicalHistory = "None recorded";
        this.prescription = "None";
    }

    // ---------- PUBLIC: basic details (receptionist can view) ----------
    public String getPatientId()       { return patientId; }
    public String getName()            { return name; }
    public String getAppointmentDate() { return appointmentDate; }

    // PUBLIC setter with validation: reception can reschedule appointments.
    public void setAppointmentDate(String newDate) {
        if (isValidDate(newDate)) {
            this.appointmentDate = newDate;
        } else {
            System.out.println("  Invalid date format. Use YYYY-MM-DD.");
        }
    }

    // ---------- PACKAGE-PRIVATE (default): medical details ----------
    // No modifier => visible ONLY inside package hospital.records.
    // Receptionist (hospital.staff) and HospitalApp (hospital.app) CANNOT call these.
    String getDiagnosis()      { return diagnosis; }
    String getMedicalHistory() { return medicalHistory; }
    String getPrescription()   { return prescription; }

    void setDiagnosis(String diagnosis)       { this.diagnosis = diagnosis; }
    void setPrescription(String prescription) { this.prescription = prescription; }
    void addToHistory(String entry) {
        if (medicalHistory.equals("None recorded")) {
            medicalHistory = entry;
        } else {
            medicalHistory = medicalHistory + "; " + entry;
        }
    }

    // ---------- PRIVATE: internal helper ----------
    // Only this class needs it, so no one else should see it.
    private boolean isValidDate(String date) {
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
