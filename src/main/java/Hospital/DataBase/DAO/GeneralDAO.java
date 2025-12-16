package Hospital.DataBase.DAO;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDAO<T> {

    void create(T obj);
    void update(T obj);
    void delete(T obj);
    T findById(int id);

}
