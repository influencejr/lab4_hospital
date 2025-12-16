package Hospital;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.sql.SQLException;

import Hospital.DataBase.Repository.*;
import Hospital.Staff.*;
import Hospital.Appointments.*;

public class HospitalTest {

    // Репозиторії
    UserRepository userRepo = new UserRepository();
    PatientRepository patientRepo = new PatientRepository();
    StaffMemberRepository staffRepo = new StaffMemberRepository();
    AppointmentRepository appRepo = new AppointmentRepository();

    @Test
    public void testUserLifecycle() throws SQLException {
        User user = new User();
        user.setFirstName("Тест");
        user.setLastName("Користувач");
        user.setUsername("testuser_ukr");
        user.setPassword("password123");
        user.setEmail("test@ukr.net");

        userRepo.create(user);
        assertNotNull(user.getId(), "ID користувача має бути згенеровано після create()");
        assertTrue(user.getId() > 0, "ID користувача має бути більше 0");

        User fetchedUser = userRepo.findById(user.getId());
        assertNotNull(fetchedUser, "Маємо знайти користувача, якого щойно створили");
        assertEquals("testuser_ukr", fetchedUser.getUsername());

        user.setFirstName("ОновленеІм'я");
        userRepo.update(user);

        User updatedUser = userRepo.findById(user.getId());
        assertEquals("ОновленеІм'я", updatedUser.getFirstName());

        userRepo.delete(user.getId());
        User deletedUser = userRepo.findById(user.getId());
        assertNull(deletedUser, "Користувач має бути null після видалення");
    }

    @Test
    public void testPatientLifecycle() throws SQLException {
        User user = new User("Пацієнт", "Тестовий", "ptest_ukr", "123", "p@test.com");
        userRepo.create(user);

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setRole("Стаціонар");
        patient.setDescription("Симптоми грипу");

        patientRepo.create(patient);
        assertTrue(patient.getId() > 0, "ID пацієнта має бути згенеровано");

        Patient fetchedPatient = patientRepo.findById(patient.getId());
        assertNotNull(fetchedPatient);
        assertEquals("Стаціонар", fetchedPatient.getRole());
        assertEquals(user.getId(), fetchedPatient.getUser().getId());

        patient.setDescription("Одужав");
        patientRepo.update(patient);

        Patient updatedPatient = patientRepo.findById(patient.getId());
        assertEquals("Одужав", updatedPatient.getDescription());

        patientRepo.delete(patient.getId());
        assertNull(patientRepo.findById(patient.getId()));

        userRepo.delete(user.getId());
    }

    @Test
    public void testStaffLifecycle() throws SQLException {
        User user = new User("Лікар", "Хаус", "drhouse", "123", "dr@house.com");
        userRepo.create(user);

        StaffMember staff = new StaffMember();
        staff.setUser(user);
        staff.setRole("Лікар");
        staff.setDescription("Діагност");

        staffRepo.create(staff);
        assertTrue(staff.getId() > 0);

        StaffMember fetchedStaff = staffRepo.findById(staff.getId());
        assertNotNull(fetchedStaff);
        assertEquals("Лікар", fetchedStaff.getRole());

        staff.setRole("Завідувач відділення");
        staffRepo.update(staff);
        assertEquals("Завідувач відділення", staffRepo.findById(staff.getId()).getRole());

        staffRepo.delete(staff.getId());
        assertNull(staffRepo.findById(staff.getId()));

        userRepo.delete(user.getId());
    }

    @Test
    public void testFullAppointmentFlow() throws SQLException {

        User userP = new User("Василь", "Хворий", "vasyl_sick", "111", "sick@test.com");
        userRepo.create(userP);
        Patient patient = new Patient();
        patient.setUser(userP);
        patient.setRole("Пацієнт");
        patient.setDescription("Головний біль");
        patientRepo.create(patient);

        User userD = new User("Іван", "Лікарчук", "doc_ivan", "222", "doc@test.com");
        userRepo.create(userD);
        StaffMember doctor = new StaffMember();
        doctor.setUser(userD);
        doctor.setRole("Лікар");
        doctor.setDescription("Терапевт");
        staffRepo.create(doctor);

        DoctorVisit visit = new DoctorVisit();

        visit.setApp_name("Візит до лікаря");

        visit.setApp_description("Регулярний огляд");
        visit.setApp_day(20);
        visit.setApp_month(12);
        visit.setApp_hour(10);
        visit.setApp_minute(30);
        visit.setPatient(patient);
        visit.setStaffMember(doctor);
        visit.setAppointment_result("Очікується");
        visit.setAppointment_diagnose("Невідомо");

        appRepo.create(visit);
        assertTrue(visit.getId() > 0, "ID призначення має бути згенеровано");

        List<Appointment> appointments = appRepo.findAll(patient.getId());
        assertFalse(appointments.isEmpty(), "Маємо знайти хоча б одне призначення");

        Appointment fetchedApp = appointments.get(0);
        assertEquals("Візит до лікаря", fetchedApp.getApp_name());
        assertEquals(patient.getId(), fetchedApp.getPatient().getId());

        assertNotNull(fetchedApp.getStaffMember());
        assertEquals(doctor.getId(), fetchedApp.getStaffMember().getId());

        visit.setAppointment_result("Завершено");
        visit.setAppointment_diagnose("Здоровий");
        appRepo.update(visit);

        List<Appointment> updatedList = appRepo.findAll(patient.getId());
        assertEquals("Здоровий", updatedList.get(0).getAppointment_diagnose());

        appRepo.delete(visit.getId());

        List<Appointment> emptyList = appRepo.findAll(patient.getId());
        assertTrue(emptyList.isEmpty());

        patientRepo.delete(patient.getId());
        staffRepo.delete(doctor.getId());
        userRepo.delete(userP.getId());
        userRepo.delete(userD.getId());
    }
}