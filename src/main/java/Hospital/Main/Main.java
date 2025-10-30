package Hospital.Main;

import Hospital.Appointments.Appointment;
import Hospital.Appointments.DispensingOfMedicines;
import Hospital.Appointments.DoctorOperation;
import Hospital.Appointments.DoctorVisit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {

        /*
        * Створюємо список з усіма призначеннями
        */
        List<Appointment> appointments = new ArrayList<>();

        /*
        * Створюємо новий об'єкт для призначення "Видача ліків"
        */
        DispensingOfMedicines dispensingofmedicines = new DispensingOfMedicines(
                "Видача ліків \"Ібупрофен\"",
                18,
                9,
                18,
                30,
                "Василій",
                "vasyliy_pupkin1990@gmail.com"
        );


        /*
        * Створюємо новий об'єкт для призначення "Призначення до лікаря"
        */
        DoctorVisit doctorvisit = new DoctorVisit(
                "Візит до терапевта через кашель у дитини",
                13,
                10,
                10,
                00,
                "Людмила",
                "lyudmula_borusivna1979@gmail.com"
        );


        /*
        * Створюємо новий об'єкт для призначення "Операція"
        */
        DoctorOperation doctoroperation = new DoctorOperation(
                "Операція на серці у чоловіка, 32 роки",
                19,
                12,
                15,
                45,
                "Андрій",
                "andrew_thesurgeon1985@gmail.com"
        );


        /*
        * Додаємо наші призначення в список усіх призначень
        */
        appointments.add(dispensingofmedicines);
        appointments.add(doctorvisit);
        appointments.add(doctoroperation);


        /*
        * Виводимо всі наші призначення на екран
        */
        appointments.forEach(System.out::println);
    }
}
