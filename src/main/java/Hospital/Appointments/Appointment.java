package Hospital.Appointments;


/*
* Абстрактний клас, який використовується для створення призначень
*/
public abstract class Appointment {

    /*ID призначення*/
    public Long id;

    public static Long nextid = 1L;

    /*
    * Назва призначення, наприклад: видача ліків, прийом у лікаря, операція...
    */
    public String appointment_name;


    /*
    * Опис призначення, наприклад: видача ліків від головної болі,
    * прийом у терапевта через кашель, операція по видаленню...
    */
    public String appointment_description;


    /*
    * Дата та час призначення:
    * appointment_day - день призначення
    * appointment_month - місяць призначення
    * appointment_hour - година призначення
    * appointment_minute - хвилини призначення
    */
    public int appointment_day;
    public int appointment_month;
    public int appointment_hour;
    public int appointment_minute;

    /*
    * Дані лікаря, до якого зробили це призначення
    */
    public String appointment_doctor_name;
    public String appointment_doctor_email;

    public abstract String toString();

    public void increment_id() {
        id = nextid++;
    }

}
