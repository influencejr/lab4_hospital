package Hospital.Appointments.DispensingOfMedicine;


import Hospital.Appointments.Appointment;
import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;


/*
* Клас створення призначення "Видача ліків"
*/
public class DispensingOfMedicines extends Appointment {


    private MedicineType medicineType;
    private Patient patient;
    private StaffMember staffMember;

    public DispensingOfMedicines() {
        this.setApp_name("Видача ліків");
    }

    public DispensingOfMedicines(String appointment_description,
                                 int appointment_day,
                                 int appointment_month,
                                 int appointment_hour,
                                 int appointment_minutes,
                                 Patient patient,
                                 StaffMember staffMember,
                                 MedicineType medicineType) {
        this.setApp_name("Видача ліків");
        this.setId(this.getId());
        this.setApp_description(appointment_description);
        this.setApp_day(appointment_day);
        this.setApp_month(appointment_month);
        this.setApp_hour(appointment_hour);
        this.setApp_minute(appointment_minutes);
        this.setPatient(patient);
        this.setStaffMember(staffMember);
        this.setMedicineType(medicineType);

        // Збільшуємо id на 1 для всіх видів призначень.
        increment_id();
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public Patient getPatient() {
        return patient;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }


    @Override
    public String toString() {
        return "\nНазва призначення: " + getApp_name()
                + "\nОпис призначення: " + getApp_description()
                + "\nДень, місяць, година:хвилина призначення: " + getApp_day() + ", " + getApp_month() + ", " + getApp_hour() + ":" + getApp_minute()
                + "\nІм'я та пошта пацієнта: " + patient.getUser().getFirstName() + ", " + patient.getUser().getEmail()
                + "\nІм'я та пошта лікаря: " + staffMember.getUser().getFirstName() + ", " + staffMember.getUser().getEmail()
                + "\nid призначення: " + getId();
    }

}
