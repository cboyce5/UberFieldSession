package geospatial.tests;

import geospatial.server.KafkaProducer;
import org.junit.Test;

import java.util.Random;

/**
 * Created by ricky on 6/4/16.
 */
public class KafkaTests {
    @Test
    public void testKafkaProducer() {
        KafkaProducer producer = KafkaProducer.getProducer();

        producer.produce("test", "test: " + new Random(69).nextDouble());
    }
}
