package hospital.staff;

import hospital.records.Patient;

/**
 * Receptionist can ONLY view basic patient details.
 *
 * Receptionist is in package hospital.staff, while Patient is in hospital.records.
 * Therefore the receptionist can only use Patient's PUBLIC methods
 * (getName, getAppointmentDate, setAppointmentDate).
 * The package-private medical methods (getDiagnosis, etc.) are INVISIBLE here -
 * trying to call them gives a COMPILE ERROR.
 */
public class Receptionist extends Staff {

    public Receptionist(String staffId, String name) {
        super(staffId, name);      // uses the PROTECTED constructor of Staff
    }

    @Override
    protected String describeRole() {
        return "Receptionist (can view basic details only)";
    }

    public void viewBasicDetails(Patient patient) {
        System.out.println("  Receptionist " + name + " views patient:");   // 'name' is protected -> OK in subclass
        System.out.println("    ID          : " + patient.getPatientId());
        System.out.println("    Name        : " + patient.getName());
        System.out.println("    Appointment : " + patient.getAppointmentDate());

        // patient.getDiagnosis();   // COMPILE ERROR: getDiagnosis() is not public in Patient
    }

    public void rescheduleAppointment(Patient patient, String newDate) {
        System.out.println("  Receptionist " + name + " reschedules " + patient.getName() + " to " + newDate);
        patient.setAppointmentDate(newDate);
    }
}
