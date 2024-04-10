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

    Connection connection;

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connactionBD ="jdbc:postgresql://db:5432/database_aston";
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(connactionBD,"postgres", "postgres");

        return connection;

    }
}
