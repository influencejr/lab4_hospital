package Hospital.Appointments.DispensingOfMedicine;

public class MedicineType {
    private String medicineName;
    private String medicineBrand;
    private String medicineSideEffects;

    public MedicineType(String medicineName, String medicineBrand, String medicineSideEffects) {
        this.medicineName = medicineName;
        this.medicineBrand = medicineBrand;
        this.medicineSideEffects = medicineSideEffects;
    }

    public String getMedicineDetails() {
        return "Назва ліків: " + medicineName
                + "\nБренд ліків: " + medicineBrand
                + "\nОбережно! " + medicineSideEffects;
    }
}
