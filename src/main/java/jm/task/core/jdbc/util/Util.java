package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util extends UserServiceImpl {
    private static final String URL = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Whydoit1";
    private static  SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().
                        setProperties(getProperties()).addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Properties getProperties() {
        Properties result = new Properties();
        result.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        result.put(Environment.URL, URL);

        result.put(Environment.USER, LOGIN);
        result.put(Environment.PASS, PASSWORD);
        result.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        result.put(Environment.SHOW_SQL, "true");
        result.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        result.put(Environment.HBM2DDL_AUTO, "create-drop");

        return result;
    }

    public static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionToDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
