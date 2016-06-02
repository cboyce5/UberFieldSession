package geospatial.tests;


import geospatial.server.GeospatialHandler;
import geospatial.thrift.Feature;
import geospatial.thrift.Point;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricky on 5/30/16.
 */
public class DatabaseTests {
    @Test
    public void testInsert() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            Feature f = handler.createFeature(new Point(1.0, 1.0), "{}");
            assertEquals("02222221", f.getGrid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        GeospatialHandler handler = new GeospatialHandler();

        try {
            Feature f = handler.getFeature("f80feafc-f549-41fb-8cba-803499f89faa");

            assertEquals(new Point(1, 1), f.getPoint());
            assertEquals("f80feafc-f549-41fb-8cba-803499f89faa", f.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
