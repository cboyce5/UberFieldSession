package geospatial.tests;


import com.datastax.driver.core.Session;
import geospatial.server.Database;
import geospatial.server.GeospatialHandler;
import geospatial.thrift.Feature;
import geospatial.thrift.Geospatial;
import geospatial.thrift.Point;
import geospatial.thrift.Rectangle;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricky on 5/30/16.
 */
public class DatabaseTests {
    String testUUID;

    @Before
    public void setup() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            Session s = Database.getSession();

            s.execute("truncate feature");

            handler.createFeature(new Point(0, 0), "{}");
            handler.createFeature(new Point(52, 52), "{}");
            handler.createFeature(new Point(-52, 52), "{}");
            handler.createFeature(new Point(-52, -52), "{}");
            testUUID = handler.createFeature(new Point(52, -52), "{}").getId();

            handler.createFeature(new Point(54, 54), "{}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            Feature f = handler.createFeature(new Point(1.0, 1.0), "{}");
            assertEquals("022222", f.getGrid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            Feature f = handler.getFeature(testUUID); // This must be in the database already

            assertEquals(new Point(52, -52), f.getPoint());
            assertEquals(testUUID, f.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSaveFeature(){
        GeospatialHandler handler = new GeospatialHandler();
        try {

            Feature f = handler.createFeature(new Point(52, -52), "{}");
            testUUID = f.getId();
            f.setPoint(new Point(15, 15));
            f.setPayload("{00}");
            assertTrue(handler.saveFeature(f));
            f = handler.getFeature(testUUID);
            assertEquals(new Point(15, 15), f.getPoint());
            assertEquals("{00}", f.getPayload());


            f.setPayload("{69}");
            assertTrue(handler.saveFeature(f));
            f = handler.getFeature(testUUID);
            assertEquals("{69}", f.getPayload());

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Test
    public void testGetFromRect() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            List<Feature> fs = handler.getFeaturesInRect(new Rectangle(new Point(-53, 53), new Point(53, -53)));

            boolean has_point_1 = false, has_point_2 = false, has_point_3 = false, has_point_4 = false, has_point_5 = false;

            Point p1 = new Point(52, 52), p2 = new Point(-52, 52), p3 = new Point(0, 0), p4 = new Point(52, -52), p5 = new Point(-52, -52);

            for(Feature f : fs) {
                assertNotEquals(new Point(54, 54), f.getPoint());

                if(f.getPoint().equals(p1)) {
                    has_point_1 = true;
                } else if(f.getPoint().equals(p2)) {
                    has_point_2 = true;
                } else if(f.getPoint().equals(p3)) {
                    has_point_3 = true;
                } else if(f.getPoint().equals(p4)) {
                    has_point_4 = true;
                } else if(f.getPoint().equals(p5)) {
                    has_point_5 = true;
                }
            }

            assertTrue(has_point_1);
            assertTrue(has_point_2);
            assertTrue(has_point_3);
            assertTrue(has_point_4);
            assertTrue(has_point_5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
