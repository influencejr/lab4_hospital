package Hospital.Appointments.DispensingOfMedicine;

import java.util.HashMap;
import java.util.Map;


/*
* Клас, створенний для перевірки на існування класу з ліками
*
* Якщо ліки, назву яких ми передали вже існують, то ми просто повертаємо їх.
* В протилежному випадку ми створюємо новий клас з назвою, брендом та побічними ефектами.
* */
public class MedicineFactory {
    private static Map<String, MedicineType> prescriptions = new HashMap<>();

    public static MedicineType getPrescription(String medicineName, String medicineBrand, String medicineSideEffects) {
        if(!prescriptions.containsKey(medicineName)){
            prescriptions.put(medicineName, new MedicineType(medicineName, medicineBrand, medicineSideEffects));
        }
        return prescriptions.get(medicineName);
    }
}
