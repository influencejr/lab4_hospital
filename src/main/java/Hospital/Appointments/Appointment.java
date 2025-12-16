package Hospital.Appointments;


import Hospital.Appointments.DispensingOfMedicine.MedicineType;
import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;
import lombok.*;

@Getter
@Setter

/*
* Абстрактний клас, який використовується для створення призначень
*/
public abstract class Appointment {

    /*ID призначення*/
    private Integer id;

    private static Long nextid = 1L;

    /*
    * Назва призначення, наприклад: видача ліків, прийом у лікаря, операція...
    */
    private String app_name;


    /*
    * Опис призначення, наприклад: видача ліків від головної болі,
    * прийом у терапевта через кашель, операція по видаленню...
    */
    private String app_description;


    /*
    * Дата та час призначення:
    * appointment_day - день призначення
    * appointment_month - місяць призначення
    * appointment_hour - година призначення
    * appointment_minute - хвилини призначення
    */
    private int app_day;
    private int app_month;
    private int app_hour;
    private int app_minute;


    /*
    * Об'єкти пацієнта, лікаря та вид ліків, які будуть видавати пацієнту
    * */
    private Patient patient;
    private StaffMember staffMember;
    private MedicineType medicineType;

    private String appointment_result;
    private String appointment_diagnose;

    public abstract String toString();

}
