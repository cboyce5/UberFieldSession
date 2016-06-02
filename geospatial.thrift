namespace js geospatial.thrift
namespace java geospatial.thrift
namespace csharp geospatial.thrift

typedef string Quadkey // Quadkeys that will be in the database
typedef string JSON// JSON will just be represented as a string and must be parsed when needed
typedef string FeatureID // This is a UUID

struct Point {
    1: double x;
    2: double y;
}

enum FeatureState {
    CLEAN = 1; // If the data isn't being modified it is in CLEAN State
    DIRTY = 2; // If the data is currently being modified it is in the DIRTY State
}

struct Rectangle {
    1: Point top_lt;
    2: Point top_rt;
    3: Point btm_lt;
    4: Point btm_rt;
}

struct Feature {
    1: required Quadkey grid;
    2: required FeatureID id;
    3: required Point point;
    4: required FeatureState state;
    5: required JSON payload = "{}";
}

service Geospatial {
    Feature createFeature(1: Point point, 2: JSON payload) // creates a new feature at a point with a payload
    Feature getFeature(1: FeatureID id) // gets feature by id
    list<Feature> getFeaturesInRect(1: Rectangle rect) // get a list of features that intersect a rectangle (uses quadkeys in background)
    bool saveFeature(1: Feature feature) // saves a feature after making changes such as payload or moving to a different point, returns true if succeeds, false otherwise
    bool deleteFeature(1: Feature feature) // deletes a feature, returns true if successful, false otherwise
}
