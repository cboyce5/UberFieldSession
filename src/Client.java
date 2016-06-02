import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Client extends JFrame{
	private Map<Point,String> dataPoints;
	private Producer<String,String> producer;
	private Consumer<String,String> consumer;
	
	
	public Client() {
		
		//Creating the properties for the consumer and producer
		String name = "suchlol.com:9092";
		String name1 = "suchlol.com:2181";
		dataPoints = new HashMap<Point,String>();
		Properties props = new Properties();
		props.put("bootstrap.servers", name);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		Properties props1 = new Properties();
		props1.put("bootstrap.servers", name1);
		props1.put("group.id", "test");
		props1.put("enable.auto.commit", "false");
		props1.put("auto.commit.interval.ms", "1000");
		props1.put("session.timeout.ms", "30000");
		props1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props1.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<>(props1);
		consumer.subscribe(Arrays.asList("newTopic"));
		
		//Creating the gui components for the client
		setUp();
		
	}
	
	public void setUp() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("Java Client");
		this.setLayout(new GridLayout(3,1));
		JButton addButton = new JButton("Add Point");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPoint();
			}
		});
		this.add(addButton);
		JButton updateButton = new JButton("Update Point");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePoint();
			}
		});
		this.add(updateButton);
		JButton printPointsButton = new JButton("Print Points");
		printPointsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printValues();
			}
		});
		this.add(printPointsButton);
		
	}
	
	public void printValues() {
		JDialog printDialog = new JDialog();
		printDialog.setTitle("All Points");
		JLabel label = new JLabel();
		label.setText(dataPoints.toString());
		printDialog.add(label);
		printDialog.setSize(300,300);
		printDialog.setModal(true);
		printDialog.setVisible(true);
	}
	
	public void addPoint() {
		JDialog addDialog = new JDialog();
		JTextField point1 = new JTextField();
		JTextField point2 = new JTextField();
		JTextField name = new JTextField();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point pt = new Point(Integer.parseInt(point1.getText()),Integer.parseInt(point2.getText()));
				dataPoints.put(pt, name.getText());
				addDialog.setVisible(false);
			}
		});
		addDialog.setLayout(new GridLayout(4,1));
		
		addDialog.add(point1);
		addDialog.add(point2);
		addDialog.add(name);
		addDialog.add(submitButton);
		addDialog.setTitle("Adding a point");
		addDialog.setSize(200,200);
		addDialog.setModal(true);
		addDialog.setVisible(true);
		
	}
	
	public void updatePoint() {
		JDialog updateDialog = new JDialog();
		updateDialog.setModal(true);
		updateDialog.setTitle("Update a Point");
		updateDialog.setSize(200, 200);
		JTextField point1 = new JTextField();
		JTextField point2 = new JTextField();
		JTextField name = new JTextField();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point pt = new Point(Integer.parseInt(point1.getText()),Integer.parseInt(point2.getText()));
				dataPoints.put(pt,name.getText());
				producer.send(new ProducerRecord<String, String>("newTopic", "key", point1.getText()+point2.getText()+name.getText()));
				updateDialog.setVisible(false);
			}
		});
		updateDialog.add(point1);
		updateDialog.add(point2);
		updateDialog.add(name);
		updateDialog.add(submitButton);
		updateDialog.setLayout(new GridLayout(4,1));
		updateDialog.setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		Client client = new Client();
		client.setVisible(true);		
	}
	
}
