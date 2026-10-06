# BIT2115 – Java Access Modifiers: Hospital Patient Record System

## Package structure
```
src/
└── hospital/
    ├── records/   Patient.java, Doctor.java       -> medical data + doctors
    ├── staff/     Staff.java, Receptionist.java   -> staff hierarchy + reception
    ├── admin/     AdminDepartment.java, FinancialRecord.java -> finance (admin only)
    └── app/       HospitalApp.java                -> main program (demo)
```

## How to run (PowerShell / CMD, from the HospitalSystem folder)
```
mkdir out
javac -d out src/hospital/records/*.java src/hospital/staff/*.java src/hospital/admin/*.java src/hospital/app/*.java
java -cp out hospital.app.HospitalApp
```

## Justification of access levels

| Member | Modifier | Why |
|---|---|---|
| Patient fields (name, diagnosis, ...) | private | Encapsulation – no class can change data directly |
| getName(), getAppointmentDate() | public | Receptionists (other package) may view basic details |
| setAppointmentDate() | public | Reception reschedules; private validation protects data |
| getDiagnosis(), setDiagnosis(), ... | default (package-private) | Only Doctor (same package hospital.records) can view/update medical data |
| isValidDate() | private | Internal helper only Patient needs |
| Staff.staffId, Staff.name | protected | Subclasses in other packages (Doctor) need them; outsiders don't |
| Staff.describeRole() | protected abstract | Each subclass must define its role |
| FinancialRecord (class) | default | Invisible outside hospital.admin – only administration uses it |
| AdminDepartment.ADMIN_PIN, financialRecords | private | Password and raw financial data never leave the class |
| AdminDepartment.isAuthorised() | private | Security check cannot be bypassed or called from outside |
| Patient.HOSPITAL_NAME | public static final | Harmless constant everyone may read |
