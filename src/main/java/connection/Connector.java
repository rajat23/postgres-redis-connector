package connection;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.jdbc.PGDataSource;

import java.sql.SQLException;

import static configuration.Configuration.*;

public class Connector {

    public static PGConnection connect() {
        PGDataSource dataSource = initiateDataSource();
        PGConnection conn = null;
        try {
            conn = (PGConnection)dataSource.getConnection();
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static PGDataSource initiateDataSource() {
        PGDataSource dataSource  = new PGDataSource();
        dataSource.setHost(HOST);
        dataSource.setPort(PORT);
        dataSource.setDatabase(DB);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
