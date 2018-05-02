package listener;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;

import java.sql.SQLException;
import java.sql.Statement;

public class Listener implements Runnable {

    private PGConnection connection;
    private Boolean isConnectionAlive;

    public Listener(PGConnection connection) {
        this.connection = connection;
        isConnectionAlive = true;

    }

    private void listen() throws SQLException {
        PGNotificationListener listener = (int processId, String channelName, String payload)
                -> System.out.println("notification = " + payload);

        Statement statement = connection.createStatement();
        statement.execute("LISTEN user_channel");
        statement.close();
        connection.addNotificationListener(listener);
        while(isConnectionAlive);
    }

    @Override
    public void run() {
        try  {
            listen();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
