package Hospital.Main;

import Hospital.Appointments.Appointment;
import Hospital.Appointments.AppointmentFactory;
import Hospital.Appointments.DispensingOfMedicine.DispensingOfMedicines;
import Hospital.Appointments.DispensingOfMedicine.MedicineFactory;
import Hospital.Appointments.DispensingOfMedicine.MedicineType;
import Hospital.Staff.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static void main() {


        // Створюємо нового користувача для пацієнта
        User user1 = new User(1L,
                "Olexiy",
                "Vasylenko",
                "olexiy_vasylenko1990",
                "olexiy123",
                "olexiyvasylenko_1990@gmail.com");

        // Створюємо нового користувача для лікаря
        User user2 = new User(2L,
                        "Vasyliy",
                        "Petrenko",
                        "vasyliy_petrenko1965",
                        "vasya_petr",
                        "vasyliy_petrenko@gmail.com");

        // Створюємо новий вид ліків через Factory
        MedicineType medicineType1 = new MedicineFactory().getPrescription("Каплі для очей",
                "СуперОптік", "Немає");

        // Створюємо нових пацієнта та лікаря
        Patient patient = new Patient(1L, user1, "PATIENT", "Має алергію на активоване вугілля");
        StaffMember staffMember = new StaffMember(2L, user2, "DOCTOR", "Лікар окуліст");

        // Створюємо нове призначення через Factory
        Appointment newAppointment = AppointmentFactory.createDispensingOfMedicine("Каплі для очей",
                14,
                10,
                23,
                59,
        patient,
        staffMember,
        medicineType1);

        System.out.println(newAppointment.getMedicineType().getMedicineDetails(patient, 20));

        System.out.println(newAppointment.toString());

    }
}
