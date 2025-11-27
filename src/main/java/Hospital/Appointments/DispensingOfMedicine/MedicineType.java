package Hospital.Appointments.DispensingOfMedicine;

import Hospital.Staff.Patient;

public class MedicineType {

    // Спільні дані, тобто дані, які завжди будуть однакові
    private String medicineName;
    private String medicineBrand;
    private String medicineSideEffects;

    public MedicineType(String medicineName, String medicineBrand, String medicineSideEffects) {
        this.medicineName = medicineName;
        this.medicineBrand = medicineBrand;
        this.medicineSideEffects = medicineSideEffects;
    }

    /*
    * Метод, в який ми передаємо ситуативні дані: об'єкт пацієнта та кількість ліків.
    * */
    public String getMedicineDetails(Patient patient, double amount) {
        return "Назва ліків: " + medicineName
                + "\nБренд ліків: " + medicineBrand
                + "\nОбережно! " + medicineSideEffects
                + "\nКому призначено: " + patient.getUser().getFirstName() + " " + patient.getUser().getLastName()
                + "\nКількість ліків: " + amount;
    }
}
