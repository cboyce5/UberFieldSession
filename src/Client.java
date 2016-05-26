import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Client {
	private Map<Point,String> dataPoints;
	private Scanner reader;
	private Producer<String,String> producer;
	private Consumer<String,String> consumer;
	
	public Client() {
		dataPoints = new HashMap<Point,String>();
		addPoint();
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		Properties props1 = new Properties();
		props1.put("bootstrap.servers", "localhost:9092");
		props1.put("group.id", "test");
		props1.put("enable.auto.commit", "false");
		props1.put("auto.commit.interval.ms", "1000");
		props1.put("session.timeout.ms", "30000");
		props1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props1.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<>(props1);
		consumer.subscribe(Arrays.asList("newTopics"));
	}
	
	public void printValues() {
		System.out.println(dataPoints);
	}
	
	public void addPoint() {
		reader = new Scanner(System.in);
		boolean loop = true;
		String entry = "";
		while (loop) {
			System.out.print("Enter a point followed by a name (x y name) Q/q to quit: ");
			entry = reader.nextLine();
			if (entry.equals("Q") || entry.equals("q")) {
				loop = false;
			}
			String[] userString = entry.split("\\s+");
			Point pt = null;
			try {
				pt = new Point(Integer.parseInt(userString[0]), Integer.parseInt(userString[1]));
			} catch (Exception e) {
				System.out.println("Please enter a valid x y coordinate.");
			}
			String name = userString[2];
			dataPoints.put(pt,name);
		}
	}
	
	public void updatePoint() {
		reader = new Scanner(System.in);
		System.out.print("Enter the x and y value of the point you want to change: ");
		String enter = "";
		enter = reader.nextLine();
		String[] enterString = enter.split("\\s+");
		if (!dataPoints.containsKey(new Point(Integer.parseInt(enterString[0]),Integer.parseInt(enterString[1])))) {
			System.out.println("The data does not contain this point");
			return;
		}
		System.out.print("Enter the new name for the point you just entered: ");
		String newName = "";
		newName = reader.nextLine();
		dataPoints.put(new Point(Integer.parseInt(enterString[0]),Integer.parseInt(enterString[1])), newName);
		producer.send(new ProducerRecord<String, String>("newTopic", "key", enterString[0]+" "+enterString[1]+newName));
	}
	
	public void run() {
		reader = new Scanner(System.in);
		printOptions();
		boolean loop = true;
		String enter = "";
		while (loop) {
			System.out.print("Enter your option choice: ");
			enter = reader.nextLine();
			switch (enter) {
			case "1":
				printValues();
				break;
			case "2":
				addPoint();
				break;
			case "3":
				updatePoint();
				break;
			case "4":
				printOptions();
				break;
			case "5":
				loop = false;
				break;
			default:
				System.out.println("Please select a valid option.");
				break;
			}
			ConsumerRecords<String, String> records = consumer.poll(100);
			System.out.println(records);
		}
		reader.close();
	}
	
	public void printOptions() {
		System.out.println("Options:");
		System.out.println("1: Print dataPoints");
		System.out.println("2: Add new dataPoint(s)");
		System.out.println("3: Update dataPoint(s)");
		System.out.println("4: Print options again");
		System.out.println("5: Quit");
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.run();
		
	}
}
