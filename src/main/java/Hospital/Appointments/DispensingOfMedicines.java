package Hospital.Appointments;


/*
* Клас створення призначення "Видача ліків"
*/
public class DispensingOfMedicines extends Appointment {


    /*
    * Конструктор без параметрів з встановленою назвою призначення
    */
    public DispensingOfMedicines() {
        appointment_name = "Видача ліків";
    }

    /*
     * Конструктор з усіма параметрами і заздалегідь встановленою назвою
     * @param appointment_description Опис призначення, наприклад: "Прийом у терапевта через кашель".
     * @param appointment_day День призначення
     * @param appointment_month Місяць призначення
     * @param appointment_hour Година призначення
     * @param appointment_minutes Хвилини призначення
     * @param appointment_doctor_name Ім'я лікаря, до якого зробили призначення
     * @param appointment_doctor_email Електронна пошта лікаря, до якого зробили призначення
     */
    public DispensingOfMedicines(String appointment_description,
                                 int appointment_day,
                                 int appointment_month,
                                 int appointment_hour,
                                 int appointment_minutes,
                                 String appointment_doctor_name,
                                 String appointment_doctor_email) {
        this.appointment_name = "Видача ліків";
        this.id = id;
        this.appointment_description = appointment_description;
        this.appointment_day = appointment_day;
        this.appointment_month = appointment_month;
        this.appointment_hour = appointment_hour;
        this.appointment_minute = appointment_minutes;
        this.appointment_doctor_name = appointment_doctor_name;
        this.appointment_doctor_email = appointment_doctor_email;

        // Збільшуємо id на 1 для всіх видів призначень.
        increment_id();
    }

    @Override
    public String toString() {
        return "\nНазва призначення: " + appointment_name
                + "\nОпис призначення: " + appointment_description
                + "\nДень, місяць, година:хвилина призначення: " + appointment_day + ", " + appointment_month + ", " + appointment_hour + ":" + appointment_minute
                + "\nІм'я та пошта лікаря: " + appointment_doctor_name + ", " + appointment_doctor_email
                + "\nid призначення: " + id;
    }

}
