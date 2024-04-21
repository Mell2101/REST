package bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectionManager implements ConnectionManager{

    private static HikariDataSource dataSource;

    Connection connection;

//    @Override
//    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        String connectionString = "jdbc:postgresql://localhost:5432/database_aston";
//        Class.forName("org.postgresql.Driver");
//        connection = DriverManager.getConnection(connectionString, "postgres", "postgres");
//        return connection;
//    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("C:\\Users\\Tkago\\eclipse-workspace\\REST\\src\\main\\resources\\bd\\db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.jdbcUrl"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));

        Class.forName("org.postgresql.Driver");
        dataSource = new HikariDataSource(config);

        Connection connection = dataSource.getConnection();
//        dataSource.close();

        return connection;
    }

    @Override
    public  void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
