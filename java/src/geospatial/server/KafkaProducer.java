package geospatial.server;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;

/**
 * Created by ricky on 5/31/16.
 */
public class KafkaProducer {
	private Producer<String,String> producer = null;
	
	public static Producer getProducer() {
		if (producer = null) {
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
		}
		return producer;
	}
	
	public void produce(String topic, String message) {
		producer.send(new ProducerRecord<String,String>(topic,message));
	}
	
}
