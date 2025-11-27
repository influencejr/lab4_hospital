package Hospital.Appointments;

import Hospital.Appointments.DispensingOfMedicine.DispensingOfMedicines;
import Hospital.Appointments.DispensingOfMedicine.MedicineType;
import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;


/*
* Цей клас призначенний для створення призначення
* В залежності від призначення, тут реалізовані різні методи для створення різних призначень
* */

public class AppointmentFactory {
    public static Appointment createOperation(String description,
                                              int day,
                                              int month,
                                              int hour,
                                              int minute,
                                              Patient patient,
                                              StaffMember staffMember) {
        return new DoctorOperation(description, day, month, hour, minute, patient, staffMember);
    }

    public static Appointment createDispensingOfMedicine(String description,
                                                         int day,
                                                         int month,
                                                         int hour,
                                                         int minute,
                                                         Patient patient,
                                                         StaffMember staffMember, MedicineType medicineType) {
        return new DispensingOfMedicines(description, day, month, hour, minute, patient, staffMember, medicineType);
    }

    public static Appointment createDoctorVisit(String description,
                                              int day,
                                              int month,
                                              int hour,
                                              int minute,
                                              Patient patient,
                                                StaffMember staffMember) {
        return new DoctorVisit(description, day, month, hour, minute, patient, staffMember);
    }
}
