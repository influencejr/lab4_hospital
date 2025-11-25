package Hospital.Appointments.DispensingOfMedicine;

import java.util.HashMap;
import java.util.Map;

public class MedicineFactory {
    private static Map<String, MedicineType> prescriptions = new HashMap<>();

    public static MedicineType getPrescription(String medicineName, String medicineBrand, String medicineSideEffects) {
        if(!prescriptions.containsKey(medicineName)){
            prescriptions.put(medicineName, new MedicineType(medicineName, medicineBrand, medicineSideEffects));
        }
        return prescriptions.get(medicineName);
    }
}
