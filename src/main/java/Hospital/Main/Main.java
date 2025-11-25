package Hospital.Main;

import Hospital.Appointments.Appointment;
import Hospital.Appointments.AppointmentFactory;
import Hospital.Appointments.DispensingOfMedicine.MedicineType;
import Hospital.Staff.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static void main() {

        Appointment newAppointment = AppointmentFactory.createDispensingOfMedicine("aktyvovane vygillya",
                14,
                10,
                23,
                59,
                "Olexiy",
                "olexiy_boberovych@gmail.com",
                "Vasyliy Pupkovich",
                "vasyliy_pupkovich228@gmail.com",
                new MedicineType("Aktyvovane vygillya", "pe4era", "smakota"));

        System.out.println(newAppointment.toString());

    }
}
