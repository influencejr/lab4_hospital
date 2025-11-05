package Hospital.Main;

import Hospital.Staff.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static void main() {

        Set<StaffMember> staff = new HashSet<StaffMember>();
        Set<Patient> patients = new HashSet<Patient>();

        User user1 = new User(1L,
                "Vasyliy",
                "Pupkin",
                "vasyka_pupkin",
                "veryhardpassword_vasya",
                "vasyliy_pupkin1990@gmail.com");
        StaffMember userDoctor1 = new StaffMember(user1.getId(),
                user1,
                "doctor",
                "Терапевт");
        System.out.println(userDoctor1.toString());

        User user2 = new User(2L,
                "Olena",
                "Pipkina",
                "oleno4ka_pipkina",
                "oleno4ka1234",
                "olena_pipkina1985@gmail.com");
        Patient userPatient1 = new Patient(2L,
                user2,
                "patient",
                "Видача ліків",
                13,
                12,
                11,
                10);
        System.out.println(userPatient1.toString());


    }
}
