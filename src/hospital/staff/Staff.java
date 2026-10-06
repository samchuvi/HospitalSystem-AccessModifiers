package hospital.staff;

/**
 * Staff is the PARENT class for every hospital employee.
 *
 * ACCESS DESIGN:
 *  - staffId and name are PROTECTED: subclasses (Doctor, Receptionist) need them
 *    even when they live in a DIFFERENT package (Doctor is in hospital.records),
 *    but unrelated classes (e.g. HospitalApp) must not change them directly.
 *  - describeRole() is PROTECTED + abstract: every subclass must define its own role,
 *    but the outside world only sees the result through the public introduce().
 *  - getName() is PUBLIC: anyone may know who a staff member is.
 */
public abstract class Staff {

    protected String staffId;
    protected String name;

    // PROTECTED constructor: only subclasses can create Staff objects.
    protected Staff(String staffId, String name) {
        this.staffId = staffId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // PUBLIC: safe way for anyone to see who the staff member is.
    public void introduce() {
        System.out.println("  [" + staffId + "] " + name + " - " + describeRole());
    }

    // PROTECTED: subclasses must fill this in.
    protected abstract String describeRole();
}
