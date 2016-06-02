package geospatial.util;

import geospatial.thrift.Point;

/**
 * Created by ricky on 5/30/16.
 */
public class GridUtil {
    public static String pointToQuadkey(Point point) {
        double latitude = 180;
        double longitude = 360;  //Use the entire range unsigned.
        Point p = point.deepCopy();
        p.y += 90;
        p.x += 180;     // Add range to make all numbers positive.
        String quadKey = "";
        for(int i = 0; i < 8; i++){
            if((p.x / longitude) >= .5){
                if((p.y / latitude) >= .5){
                    quadKey += "0";
                }
                else{
                    quadKey += "3";
                }
            }
            else{
                if((p.y / latitude) >= .5){
                    quadKey += "1";
                }
                else{
                    quadKey += "2";
                }
            }
            latitude *= .5;
            longitude *= .5;
            if(p.x >= longitude){
                p.x -= longitude;
            }
            if(p.y >= latitude){
                p.y -= latitude;
            }
        }
        return quadKey;
    }
}
