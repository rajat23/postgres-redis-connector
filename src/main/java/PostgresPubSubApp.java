import com.impossibl.postgres.api.jdbc.PGConnection;
import connection.Connector;
import listener.Listener;

public class PostgresPubSubApp {

    public static void main(String[] args) {
        PGConnection connection = Connector.connect();
        Listener listener = new Listener(connection);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
    }



}
