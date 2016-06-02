package geospatial.server;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;
import geospatial.thrift.*;
import geospatial.util.GridUtil;
import org.apache.thrift.TException;

import java.util.List;
import java.util.UUID;

/**
 * Created by ricky on 5/26/16.
 */
public class GeospatialHandler implements Geospatial.Iface {

    @Override
    public Feature createFeature(Point point, String payload) throws TException {
        Session s = Database.getSession();

        String grid = GridUtil.pointToQuadkey(point);
        UUID uuid = UUIDs.random();

        PreparedStatement stmt = s.prepare("insert into feature (grid, feature_id, payload, point_x, point_y, state) values (?, ?, ?, ?, ?, ?)");
        BoundStatement bstmt = new BoundStatement(stmt);

        bstmt.bind(grid, uuid, payload, point.x, point.y, FeatureState.CLEAN.getValue());

        s.execute(bstmt);

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
            return new Feature(r.getString("grid"), r.getUUID("feature_id").toString(), new Point(r.getDouble("point_x"), r.getDouble("point_y")), FeatureState.findByValue(r.getInt("state")), r.getString("payload"));
        } else {
            return null;
        }
    }

    @Override
    public List<Feature> getFeaturesInRect(Rectangle rect) throws TException {
        return null;
    }

    @Override
    public boolean saveFeature(Feature feature) throws TException {
        return false;
    }

    @Override
    public boolean deleteFeature(Feature feature) throws TException {
        return false;
    }
}
