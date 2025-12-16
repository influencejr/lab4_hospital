package Hospital.DataBase.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            * Першим аргументом передаємо посилання на нашу базу даних
            * Другий аргумент - це ім'я користувача
            * Третій аргумент - це пароль користувача
            * */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
            System.out.println("Successfully connected to database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
