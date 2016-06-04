package geospatial.server;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Created by ricky on 5/30/16.
 */
public class Database {
    private static Cluster cluster = null;
    private static Session session = null;

    public static void init() {
        if(session == null) {
            cluster = Cluster
                    .builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            session = cluster.connect("spatial");
        }
    }

    public static Session getSession() {
        init();

        return session;
    }
}
