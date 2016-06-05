package geospatial.client

import java.util.List;
import java.util.Scanner;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

import scala.sys.SystemProperties;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

/*
 * Created by cliff on 6/4/16.
 */
public class JavaClient {
	
	private Feature currentFeature;

	public static void main(String [] args) {

		if (args.length != 1) {
			System.out.println("Please enter 'simple' or 'secure'");
			System.exit(0);
		}

		try {
			TTransport transport;
			if (args[0].contains("simple")) {
				transport = new TSocket("localhost", 9090);
				transport.open();
			}
			else {
				/*
				 * Similar to the server, you can use the parameters to setup client parameters or
				 * use the default settings. On the client side, you will need a TrustStore which
				 * contains the trusted certificate along with the public key. 
				 * For this example it's a self-signed cert. 
				 */
				TSSLTransportParameters params = new TSSLTransportParameters();
				params.setTrustStore("../../lib/java/test/.truststore", "thrift", "SunX509", "JKS");
				/*
				 * Get a client transport instead of a server transport. The connection is opened on
				 * invocation of the factory method, no need to specifically call open()
				 */
				transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
			}

			TProtocol protocol = new  TBinaryProtocol(transport);
			Geospatial.Client client = new Geospatial.Client(protocol);

			perform(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		} 
	}

	private static void perform(Geospatial.Client client) throws TException
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		boolean loop = true;
		while (loop) { 
			printOptions();
			int input = reader.nextInt();
			switch (input) {
			case 1:
				System.out.print("Enter the new point x: ");
				double x = reader.nextDouble();
				System.out.print("Enter the new point y: ");
				double y = reader.nextDouble();
				Point newPoint = new Point(x,y);
				System.out.print("Enter the new payload: ");
				String newPayload = reader.nextLine();
				currentFeature = client.createFeature(newPoint, newPayload);
				break;
			case 2:
				System.out.print("Enter the feature id: ");
				String id = reader.nextLine();
				currentFeature = client.getFeature(id);
				break;
			case 3:
				System.out.print("Enter the top left x: ");
				double x1 = reader.nextDouble();
				System.out.print("Enter the top left y: ");
				double y1 = reader.nextDouble();
				System.out.print("Enter the bottom right x: ");
				double x2 = reader.nextDouble();
				System.out.print("Enter the bottom right y: ");
				double y2 = reader.nextDouble();
				Point newPoint_tl = new Point(x1,y1);
				Point newPoint_br = new Point(x2,y2);
				Rectangle rect = new Rectanle(newPoint_tl, newPoint_br);
				List<Feature> features = client.getFeaturesInRect(rect);
				break;
			case 4:
				boolean saveSuccess = client.saveFeature(currentFeature);
				break;
			case 5:
				boolean deleteSuccess = client.deleteFeature(currentFeature);
				break;
			case 6:
				loop = false;
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}
		}
	}
	
	private static void printOptions() {
		System.out.println("Options: ");
		System.out.println("1. Create Feature");
		System.out.println("2. Get Feature");
		System.out.println("3. Get Features in Rectangle");
		System.out.println("4. Save Feature");
		System.out.println("5. Delete Feature");
		System.out.println("6. Exit");
		System.out.println("Enter your selection: ");
	}
}