package geospatial.server;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;
import geospatial.thrift.*;
import geospatial.util.GridUtil;
import org.apache.thrift.TException;

import java.util.*;

/**
 * Created by ricky on 5/26/16.
 */
public class GeospatialHandler implements Geospatial.Iface {
    KafkaProducer producer = KafkaProducer.getProducer();

    public GeospatialHandler() {
        Database.init();
    }

    @Override
    public Feature createFeature(Point point, String payload) throws TException {
        Session s = Database.getSession();

        String grid = GridUtil.pointToQuadKey(point);
        UUID uuid = UUIDs.random();

        PreparedStatement stmt = s.prepare("insert into feature (grid, feature_id, payload, point_x, point_y, state) values (?, ?, ?, ?, ?, ?)");
        BoundStatement bstmt = new BoundStatement(stmt);

        bstmt.bind(grid, uuid, payload, point.x, point.y, FeatureState.CLEAN.getValue());

        s.execute(bstmt);

        producer.produce("geospatial", uuid.toString());

        return new Feature(grid, uuid.toString(), point, FeatureState.CLEAN, payload);
    }

    @Override
    public Feature getFeature(String id) throws TException {
        Session s = Database.getSession();

        PreparedStatement stmt = s.prepare("select * from feature where feature_id = ?");
        BoundStatement bstmt = new BoundStatement(stmt);

        bstmt.bind(UUID.fromString(id));

        ResultSet result = s.execute(bstmt);
        Row r = result.one();

        if(r != null) {
            return rowToFeature(r);
        } else {
            return null;
        }
    }

    @Override
    public List<Feature> getFeaturesInRect(Rectangle rect) throws TException {
        Set<String> quadKeys = GridUtil.rectangleToQuadKeySet(rect);
        List<Feature> retList = new ArrayList<>();

        Session s = Database.getSession();

        StringBuilder builder = new StringBuilder();

        builder.append("select * from feature where grid in (");


        for(int i = 0; i < quadKeys.size(); i++) {
            builder.append("?,");
        }

        builder.deleteCharAt(builder.lastIndexOf(","));

        builder.append(")");

        PreparedStatement stmt = s.prepare(builder.toString());
        BoundStatement bstmt = new BoundStatement(stmt);

        int i = 0;
        for(String key : quadKeys) {
            bstmt.setString(i , key);
            i++;
        }

        ResultSet results = s.execute(bstmt);

        Iterator<Row> iterator = results.iterator();

        Row r;
        while((r = iterator.next()) != null) {
            if(GridUtil.pointInRect(rect, new Point(r.getDouble("point_x"), r.getDouble("point_y")))) {
                retList.add(rowToFeature(r));
            }
        }

        return retList;
    }

    @Override
    public boolean saveFeature(Feature feature) throws TException {
        // update feature where feature_id = feature.getId();
        Session s = Database.getSession();
        Feature featureOld = getFeature(feature.getId());
        PreparedStatement stmt;
        BoundStatement bstmt;
        if(!feature.getPoint().equals(featureOld.getPoint())) {
            deleteFeature(featureOld);
            stmt = s.prepare("insert into feature (grid, feature_id, payload, point_x, point_y, state) values (?, ?, ?, ?, ?, ?)");
            bstmt = new BoundStatement(stmt);
            bstmt.bind(GridUtil.pointToQuadKey(feature.getPoint()), UUID.fromString(feature.getId()), feature.getPayload(),
                    feature.getPoint().getX(), feature.getPoint().getY(), FeatureState.CLEAN.getValue());
        }else{
            stmt = s.prepare("update feature set point_x = ?, point_y = ?, payload = ?, state = ? where grid = ? and feature_id = ?");
            bstmt = new BoundStatement(stmt);
            bstmt.bind(feature.getPoint().getX(), feature.getPoint().getY(), feature.getPayload(), feature.getState().getValue(), feature.getGrid(), UUID.fromString(feature.getId()));
        }
        s.execute(bstmt);

        producer.produce("geospatial", feature.getId());

        return true;
    }

    @Override
    public boolean deleteFeature(Feature feature) throws TException {
        Session s  = Database.getSession();
        PreparedStatement stmt = s.prepare("delete from feature where grid = ? and feature_id = ?");
        BoundStatement bstmt = new BoundStatement(stmt);
        bstmt.bind(feature.getGrid(), UUID.fromString(feature.getId()));
        s.execute(bstmt);

        producer.produce("geospatial", feature.getId());

        return true;
    }

    protected Feature rowToFeature(Row r) {
        return new Feature(r.getString("grid"), r.getUUID("feature_id").toString(), new Point(r.getDouble("point_x"), r.getDouble("point_y")), FeatureState.findByValue(r.getInt("state")), r.getString("payload"));
    }
}
