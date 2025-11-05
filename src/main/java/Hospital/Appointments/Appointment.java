package Hospital.Appointments;


import lombok.*;

@Getter
@Setter

/*
* Абстрактний клас, який використовується для створення призначень
*/
public abstract class Appointment {

    /*ID призначення*/
    private Long id;

    private static Long nextid = 1L;

    /*
    * Назва призначення, наприклад: видача ліків, прийом у лікаря, операція...
    */
    private String appointment_name;


    /*
    * Опис призначення, наприклад: видача ліків від головної болі,
    * прийом у терапевта через кашель, операція по видаленню...
    */
    private String appointment_description;


    /*
    * Дата та час призначення:
    * appointment_day - день призначення
    * appointment_month - місяць призначення
    * appointment_hour - година призначення
    * appointment_minute - хвилини призначення
    */
    private int appointment_day;
    private int appointment_month;
    private int appointment_hour;
    private int appointment_minute;

    /*
    * Дані лікаря, до якого зробили це призначення
    */
    private String appointment_doctor_name;
    private String appointment_doctor_email;

    public abstract String toString();

    public void increment_id() {
        id = nextid++;
    }

}
