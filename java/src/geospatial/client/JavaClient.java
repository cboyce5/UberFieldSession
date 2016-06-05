package geospatial.client;

import java.util.List;
import java.util.Scanner;

import geospatial.thrift.Feature;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import geospatial.thrift.*;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

/*
 * Created by cliff on 6/4/16.
 */
public class JavaClient {
	
	private static Feature currentFeature;

	public static void main(String[] args) {
		try {
			TTransport transport;
			transport = new TSocket("localhost", 9090);
			transport.open();


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
				System.out.println("Your new feature is now your current feature: " + currentFeature.getId());
				break;
			case 2:
				System.out.print("Enter the feature id: ");
				String id = reader.nextLine();
				currentFeature = client.getFeature(id);
				if (currentFeature != null) {
					System.out.println("Your current feature is now saved.");
				}
				else {
					System.out.println("That feature does not exist.");
				}
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
				Rectangle rect = new Rectangle(newPoint_tl, newPoint_br);
				List<Feature> features = client.getFeaturesInRect(rect);
				break;
			case 4:
				/*boolean saveSuccess = client.saveFeature(currentFeature);
				if (saveSuccess) {
					System.out.println("Your feature was saved successfully.");
				}
				else {
					System.out.println("Your feature was saved unsuccessfully.");
				}
				break;*/
			case 5:
				boolean deleteSuccess = client.deleteFeature(currentFeature);
				if (deleteSuccess) {
					System.out.println("Your feature was deleted successfully.");
				}
				else {
					System.out.println("Your feature was deleted unsuccessfully.");
				}
				break;
			case 6:
				printFeatureChangeOptions();
				int option = reader.nextInt();
				switch (option) {
				case 1:
					System.out.print("Enter the new point x: ");
					double newX = reader.nextDouble();
					System.out.print("Enter the new point y: ");
					double newY = reader.nextDouble();
					newPoint = new Point(newX,newY);
					currentFeature = currentFeature.setPoint(newPoint);
					break;
				case 2:
					System.out.print("Enter the new state (1 or 2): ");
					int newState = reader.nextInt();
					if (newState == 1) {
						currentFeature = currentFeature.setState(FeatureState.CLEAN);
					}
					else if (newState == 2){
						currentFeature = currentFeature.setState(FeatureState.DIRTY);
					}
					else {
						System.out.println("Please enter a valid state.");
					}
					break;
				case 3:
					System.out.print("Enter the new payload: ");
					newPayload = reader.nextLine();
					currentFeature = currentFeature.setPayload(newPayload);
					break;
				default:
					System.out.println("Enter a valid option.");
					break;
				}
				break;
			case 7:
				loop = false;
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}
		}
	}
	
	private static void printOptions() {
		System.out.println("Options:");
		System.out.println("1. Create Feature");
		System.out.println("2. Get Feature");
		System.out.println("3. Get Features in Rectangle");
		System.out.println("4. Save Feature");
		System.out.println("5. Delete Feature");
		System.out.println("6. Edit Feature");
		System.out.println("7. Exit");
		System.out.print("Enter your selection: ");
	}
	
	private static void printFeatureChangeOptions() {
		System.out.println("Options:");
		System.out.println("1. Change Point");
		System.out.println("2. Change State");
		System.out.println("3. Change Payload");
		System.out.print("Enter your selection: ");
	}
}