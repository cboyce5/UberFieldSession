using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;
using System.Net.Sockets;
using geospatial.thrift;
using Thrift;


namespace ConsoleApplication1
{

    class Program
    {
        private static geospatial.thrift.Feature currentFeature;
        private static System.Net.Sockets.TcpClient serverSocket;
        private static geospatial.thrift.Geospatial.Client client;
        private static System.Net.Sockets.NetworkStream stream;

	    public static void Main(String[] args) {
		    try {
			    try {
                    serverSocket = new System.Net.Sockets.TcpClient("suchlol.com",6969);
                    stream = serverSocket.GetStream();
                    //Send the message to the connected TcpServer
			    } catch (Exception e) {
				    System.Console.WriteLine(e);
			    }

			    Thrift.Transport.TTransport transport;
			    transport = new Thrift.Transport.TSocket("suchlol.com", 9090);
			    transport.Open();

			    Thrift.Protocol.TProtocol protocol = new  Thrift.Protocol.TBinaryProtocol(transport);
			    client = new geospatial.thrift.Geospatial.Client(protocol);
                serverSocket.Close();
			    perform(client);
			    transport.Close();
		    } catch (TException x) {

			    Console.WriteLine(x.StackTrace);
		    } 
		    try {
			    serverSocket.Close();
		    } catch (IOException e) {
			    Console.WriteLine(e.StackTrace);
		    }

	    }
        private static void perform(geospatial.thrift.Geospatial.Client client)	{
		    //Scanner reader = new Scanner(System.in);
		    bool loop = true;
		    while (loop) {
			    try {
                    Byte[] data;
                    data = new Byte[256];
                    // String to store the response ASCII representation.
                    String responseData = String.Empty;
                    // Read the first batch of the TcpServer response bytes.
                    Int32 bytes = stream.Read(data, 0, data.Length);
                    responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
                    Console.WriteLine("Received: {0}", responseData); 
			    } catch (IOException e) {
				    System.Console.WriteLine(e.Message);
			    } catch(Exception r){
                    System.Console.WriteLine(r.Message);
                }
                changeFeature();
			    printOptions();
			    int input = Convert.ToInt32(Console.ReadLine());
			    switch (input) {
			    case 1:
				    //Create a new feature
				    System.Console.Write("Enter the new point x: ");
				    double x = Convert.ToDouble(System.Console.ReadLine());
				    System.Console.Write("Enter the new point y: ");
				    double y = Convert.ToDouble(System.Console.ReadLine());
				    Point newPoint = new Point();
                    newPoint.X = x;
                    newPoint.Y = y;
				    System.Console.Write("Enter the new payload: ");
				    String newPayload = System.Console.ReadLine();
                    currentFeature = client.createFeature(newPoint, newPayload);
				    System.Console.WriteLine("Your new feature is now your current feature: " + currentFeature.Id);
				    break;
			    case 2:
				    //Get a feature based on id
				    System.Console.WriteLine("Enter the feature id: ");
				    String id = System.Console.ReadLine();
				    currentFeature = client.getFeature(id);
				    if (currentFeature != null) {
					    System.Console.WriteLine("Your current feature is now saved: " + currentFeature.Id);
				    }
				    else {
					    System.Console.WriteLine("That feature does not exist.");
				    }
				    break;
			    case 3:
				    //Get features in a rectangle based on 2 points
				    System.Console.WriteLine("Enter the top left x: ");
				    double x1 = Convert.ToDouble(System.Console.ReadLine());
				    System.Console.WriteLine("Enter the top left y: ");
				    double y1 = Convert.ToDouble(System.Console.ReadLine());
				    System.Console.WriteLine("Enter the bottom right x: ");
				    double x2 = Convert.ToDouble(System.Console.ReadLine());
				    System.Console.WriteLine("Enter the bottom right y: ");
				    double y2 = Convert.ToDouble(System.Console.ReadLine());
				    //reader.nextLine();
				    Point newPoint_tl = new geospatial.thrift.Point();
                    newPoint_tl.X = x1;
                    newPoint_tl.Y = y1;
				    Point newPoint_br = new Point();
                    newPoint_br.X = x2;
                    newPoint_br.Y = y2;
				    Rectangle rect = new Rectangle();
                    rect.Top_lt = newPoint_tl;
                    rect.Btm_rt = newPoint_br;
				    List<Feature> features = client.getFeaturesInRect(rect);
				    currentFeature = selectFeature(features);
				    break;
			    case 4:
				    //Update the current feature in the database
				    if (currentFeature != null) {
					    bool success = client.saveFeature(currentFeature);
                        if(success){
					        System.Console.WriteLine("Your feature was updated successfully.");
				        }
                        else {
					        System.Console.WriteLine("You must have a non-null feature to update.");
				        }
                    }

				   
				    break;
			    case 5:
				    //Delete the current feature
				    bool deleteSuccess = client.deleteFeature(currentFeature);
				    if (deleteSuccess) {
					    System.Console.WriteLine("Your feature was deleted successfully.");
				    }
				    else {
					    System.Console.WriteLine("Your feature was deleted unsuccessfully.");
				    }
				    break;
			    case 6:
				    //Change the current feature's properties
				    if (currentFeature == null) {
					    System.Console.WriteLine("You must have a non-null feature to change.");
					    break;
				    }
				    bool changeFeatureLoop = true;
				    while (changeFeatureLoop) {
					    printFeatureChangeOptions();
					    int option = Convert.ToInt32(System.Console.ReadLine());
					    //reader.nextLine();
					    switch (option) {
					    case 1:
						    //Change the point of the feature
						    System.Console.Write("Enter the new point x: ");
						    double newX = Convert.ToDouble(System.Console.ReadLine());
						    System.Console.Write("Enter the new point y: ");
						    double newY = Convert.ToDouble(System.Console.ReadLine());
						    newPoint = new geospatial.thrift.Point();
                            newPoint.X = newX;
                            newPoint.Y = newY;
						    currentFeature.Point = newPoint;
                            client.saveFeature(currentFeature);
						    break;
					    case 2:
						    //Change the state of the feature
						    System.Console.Write("Enter the new state (1 for clean or 2 for dirty): ");
						    int newState = Convert.ToInt32(System.Console.ReadLine());
						    
						    if (newState == 1) {
                                currentFeature.State = FeatureState.CLEAN;

						    }
						    else if (newState == 2){
							    currentFeature.State = FeatureState.DIRTY;
						    }
						    else {
							    System.Console.WriteLine("Please enter a valid state.");
						    }
                            client.saveFeature(currentFeature);
						    break;
					    case 3:
						    //Change the payload of the feature
						    System.Console.Write("Enter the new payload: ");
						    newPayload = System.Console.ReadLine();
                            currentFeature.Payload = newPayload;
                            client.saveFeature(currentFeature);

						    break;
					    case 4:
						    //Break loop
						    changeFeatureLoop = false;
						    break;
					    default:
						    System.Console.WriteLine("Enter a valid option.");
						    break;
					    }
				    }
				    break;
			    case 7:
				    //Break loop
				    loop = false;
				    break;
			    default:
				    System.Console.WriteLine("Please enter a valid option.");
				    break;
			}
                //System.Console.Write("Would you like to change the feature you are editing?");

		    }
	    }
        //Selects a feature based on id from the list of features returned from query (called above)
        private static Feature selectFeature(List<Feature> features)
        {
            int i = 0;
            foreach (Feature f in features)
            {
                System.Console.Write(i + ": ");
                System.Console.WriteLine(f);
                i++;
            }
            System.Console.Write("Enter the number of the feature that you want of the feature you want: ");
            int numb = Convert.ToInt32(Console.ReadLine());
            if (numb < features.Count)
            {
                return features.ElementAt(numb);
            }
            //foreach (Feature f in features)
            //{
            //    if (f.Id == id)
            //    {

            //        return f;
            //    }
            //}
            return null;
        }
        private static List<Feature> getAllFeatures()
        {
            //Utilizes the get rectangle function to query the top left most point all the way to 
            //the bottom right most point.  This gets all of the points.
            Point newPoint_tl = new geospatial.thrift.Point();
            newPoint_tl.X = -180;
            newPoint_tl.Y = 180;
            Point newPoint_br = new Point();
            newPoint_br.X = 180;
            newPoint_br.Y = -180;
            Rectangle rect = new Rectangle();
            rect.Top_lt = newPoint_tl;
            rect.Btm_rt = newPoint_br;
            List<Feature> features = client.getFeaturesInRect(rect);
            return features;
        }
        private static void printOptions()
        {
            System.Console.WriteLine("Options:");
            System.Console.WriteLine("1. Create Feature");
            System.Console.WriteLine("2. Get Feature");
            System.Console.WriteLine("3. Get Features in Rectangle");
            System.Console.WriteLine("4. Save Feature");
            System.Console.WriteLine("5. Delete Feature");
            System.Console.WriteLine("6. Edit Feature");
            System.Console.WriteLine("7. Exit");
            System.Console.WriteLine("Enter your selection: ");
        }
        private static void printFeatureChangeOptions()
        {
            System.Console.WriteLine("Options:");
            System.Console.WriteLine("1. Change Point");
            System.Console.WriteLine("2. Change State");
            System.Console.WriteLine("3. Change Payload");
            System.Console.WriteLine("Enter your selection: ");
        }
        private static void changeFeature()
        {
            if (currentFeature != null)
            {
                System.Console.WriteLine("\nYour current feature is: " + currentFeature + "\n");
                System.Console.Write("Would you like to change your current feature? (y/n)");
                bool invalidSelection = true;
                do
                {
                    String selection = Console.ReadLine();
                    if (selection == "y" || selection == "Y")
                    {
                        invalidSelection = false;
                        currentFeature = selectFeature(getAllFeatures());
                        System.Console.WriteLine("\nYour current feature is: " + currentFeature + "\n");
                    }
                    else if (selection == "n" || selection == "N")
                    {
                        invalidSelection = false;
                    }
                    System.Console.Write("That is not a valid choice.  Please enter (y/n): ");
                } while (invalidSelection);
            }
            else
            {
                System.Console.WriteLine("You do not have a feature currently selected.");
                System.Console.Write("Would you like to select a feature (y/n)");
                String choice = Console.ReadLine();
                if (choice == "y" || choice == "Y")
                {
                    currentFeature = selectFeature(getAllFeatures());
                    System.Console.WriteLine("\nYour current feature is: " + currentFeature + "\n");
                }
            }
        }
	}
}
