package Hospital.DataBase.DAO;

import Hospital.Staff.User;

import java.util.List;

public interface UserDAO extends GeneralDAO<User> {

    List<User> findAll();
}
