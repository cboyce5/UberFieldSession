package geospatial.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

import geospatial.thrift.Geospatial;

/**
 * Created by ricky on 5/26/16.
 */
public class GeospatialServer {
    public static final int port = 9090;

    private void start() {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            Geospatial.Processor processor = new Geospatial.Processor(new GeospatialHandler());

            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting Server on Port " + port);

            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GeospatialServer s = new GeospatialServer();
        s.start();
    }
}
