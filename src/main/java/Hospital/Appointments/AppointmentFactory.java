package Hospital.Appointments;

import Hospital.Appointments.DispensingOfMedicine.DispensingOfMedicines;



public class AppointmentFactory {

    public Appointment createAppointment(String name) {

        Appointment appointment = null;

        switch (name) {
            case "Видача ліків":
                appointment = new DispensingOfMedicines();
                break;

            case "Операція":
                appointment = new DoctorOperation();
                break;

            case "Візит до лікаря":
                appointment = new DoctorVisit();
        }

        return appointment;
    }

}
