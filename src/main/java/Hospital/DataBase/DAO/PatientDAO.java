package Hospital.DataBase.DAO;

import Hospital.Staff.Patient;

import java.util.List;

public interface PatientDAO extends GeneralDAO<Patient> {

    List<Patient> findAll();
}
