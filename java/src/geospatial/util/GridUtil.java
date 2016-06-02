package geospatial.util;

import geospatial.thrift.Feature;
import geospatial.thrift.Point;
import geospatial.thrift.Rectangle;

import java.util.*;

/**
 * Created by austin on 5/30/16.
 */
public class GridUtil {
    private static int depth = 6;
    public static Set<String> rectangleToQuadKeySet(Rectangle r) {
        double xSpan = 360;  // number of x deg
        double ySpan = 180;  // number of y deg
        for (int i = 0; i < depth; i++) {
            xSpan /= 2.0;
            ySpan /= 2.0;
        }
        //xSpan and ySpan now contain the dimensions of the quadKey depth that we will be querying.
        //Thus we use them as an iterator where we only check points that lye on these increments.
        Set<String> quadKeys = new HashSet<>();
        Point p;

        for (double x = r.top_lt.x; x <= r.btm_rt.x + xSpan; x += xSpan) {
            for (double y = r.btm_rt.y; y <= r.top_lt.y + ySpan; y += ySpan) {
                p = new Point(x, y);
                quadKeys.add(pointToQuadKey(p));
            }
        }
        return quadKeys;
    }

    public static String pointToQuadKey(Point p) {
        double latitude = 180;
        double longitude = 360;  //Use the entire range unsigned.
        Point point = p.deepCopy();
        point.y += 90;
        point.x += 180;     // Add range to make all numbers positive.
        String quadKey = "";

        for (int i = 0; i < depth; i++) {
            if ((point.x / longitude) >= .5) {
                if ((point.y / latitude) >= .5) {
                    quadKey += "0";
                } else {
                    quadKey += "3";
                }
            } else {
                if ((point.y / latitude) >= .5) {
                    quadKey += "1";
                } else {
                    quadKey += "2";
                }
            }
            latitude *= .5;
            longitude *= .5;
            if (point.x >= longitude) {
                point.x -= longitude;
            }
            if (point.y >= latitude) {
                point.y -= latitude;
            }
        }
        return quadKey;
    }

    public static List<Feature> filterPointInRect(Rectangle rect, List<Feature> features) {
        // for f in features
        //  is f.point inside rectangle?
        //  if yes, add to returning
        //  if no, dont add
        List<Feature> returnFeatures = new ArrayList<Feature>();
        for (Feature f : features) {
            if (pointInRect(rect, f.getPoint())) {
                returnFeatures.add(f);
            }
        }
        return returnFeatures;
    }

    public static boolean pointInRect(Rectangle rect, Point p) {
        return p.getX() >= rect.top_lt.getX() &&
                p.getX() <= rect.btm_rt.getX() &&
                p.getY() <= rect.top_lt.getY() &&
                p.getY() >= rect.btm_rt.getY();
    }
}
