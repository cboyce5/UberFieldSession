package geospatial.server;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by ricky on 5/31/16.
 */
public class KafkaProducer {
	private static Producer<String,String> producer = null;
	private static KafkaProducer mProducer = null;


	public KafkaProducer() {
		if (producer == null) {
			Properties props = new Properties();
			props.put("bootstrap.servers", "localhost:9092");
			props.put("acks", "all");
			props.put("retries", 0);
			props.put("batch.size", 16384);
			props.put("linger.ms", 1);
			props.put("buffer.memory", 33554432);
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
		}
	}
	
	public static KafkaProducer getProducer() {
		if(mProducer == null) {
			mProducer = new KafkaProducer();
		}
		return mProducer;
	}
	
	public void produce(String topic, String message) {
		producer.send(new ProducerRecord<String,String>(topic,message));
	}
	
}
