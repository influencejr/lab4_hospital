package Hospital.Main;

import Hospital.Appointments.Appointment;
import Hospital.Appointments.AppointmentFactory;
import Hospital.DataBase.Config.ConnectionToDB;
import Hospital.DataBase.Repository.AppointmentRepository;
import Hospital.DataBase.Repository.PatientRepository;
import Hospital.DataBase.Repository.StaffMemberRepository;
import Hospital.DataBase.Repository.UserRepository;
import Hospital.Staff.Patient;
import Hospital.Staff.StaffMember;
import Hospital.Staff.User;

import java.util.List;

import static java.sql.Types.NULL;

public class Main {
    static void main() {

        new ConnectionToDB().getConnection();

        User user1 = new User();
        user1.setFirstName("Vasyliy");
        user1.setLastName("Pupkin");
        user1.setUsername("vasyliy");
        user1.setPassword("1234");
        user1.setEmail("vasyliy@gmail.com");
        UserRepository userRepository = new UserRepository();
        userRepository.create(user1);

        User user2 = new User();
        user2.setFirstName("Tatiana");
        user2.setLastName("Pupkina");
        user2.setUsername("tatiana");
        user2.setPassword("1234");
        user2.setEmail("tatiana@gmail.com");
        userRepository.create(user2);

        Patient patient = new Patient();
        patient.setUser(user1);
        patient.setRole("Patient");
        patient.setDescription("-");
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.create(patient);

        StaffMember staffMember = new StaffMember();
        staffMember.setUser(user2);
        staffMember.setRole("Doctor");
        staffMember.setDescription("-");
        StaffMemberRepository staffMemberRepository = new StaffMemberRepository();
        staffMemberRepository.create(staffMember);

        Appointment appointment = new AppointmentFactory().createAppointment("Візит до лікаря");
        appointment.setApp_name("Візит до лікаря");
        appointment.setApp_description("-");
        appointment.setApp_day(11);
        appointment.setApp_month(11);
        appointment.setApp_hour(11);
        appointment.setApp_minute(11);
        appointment.setPatient(patient);
        appointment.setStaffMember(staffMember);
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        appointmentRepository.create(appointment);

        List<Appointment> appointmentList = appointmentRepository.findAll(patient.getId());
        for(Appointment a : appointmentList){
            System.out.println(a);
        }

    }
}
