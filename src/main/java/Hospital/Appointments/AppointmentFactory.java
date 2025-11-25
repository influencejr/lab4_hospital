package Hospital.Appointments;

import Hospital.Appointments.DispensingOfMedicine.DispensingOfMedicines;
import Hospital.Appointments.DispensingOfMedicine.MedicineType;

public class AppointmentFactory {
    public static Appointment createOperation(String description,
                                              int day,
                                              int month,
                                              int hour,
                                              int minute,
                                              String patient_name,
                                              String patient_email,
                                              String doctor_name,
                                              String doctor_email) {
        return new DoctorOperation(description, day, month, hour, minute, patient_name, patient_email, doctor_name, doctor_email);
    }

    public static Appointment createDispensingOfMedicine(String description,
                                              int day,
                                              int month,
                                              int hour,
                                              int minute,
                                              String patient_name,
                                              String patient_email,
                                              String doctor_name,
                                              String doctor_email, MedicineType medicineType) {
        return new DispensingOfMedicines(description, day, month, hour, minute, patient_name, patient_email, doctor_name, doctor_email, medicineType);
    }

    public static Appointment createDoctorVisit(String description,
                                              int day,
                                              int month,
                                              int hour,
                                              int minute,
                                                String patient_name,
                                                String patient_email,
                                              String doctor_name,
                                              String doctor_email) {
        return new DoctorVisit(description, day, month, hour, minute, patient_name, patient_email, doctor_name, doctor_email);
    }
}
