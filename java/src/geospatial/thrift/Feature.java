/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package geospatial.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Feature implements org.apache.thrift.TBase<Feature, Feature._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Feature");

  private static final org.apache.thrift.protocol.TField GRID_FIELD_DESC = new org.apache.thrift.protocol.TField("grid", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField POINT_FIELD_DESC = new org.apache.thrift.protocol.TField("point", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("state", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField PAYLOAD_FIELD_DESC = new org.apache.thrift.protocol.TField("payload", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new FeatureStandardSchemeFactory());
    schemes.put(TupleScheme.class, new FeatureTupleSchemeFactory());
  }

  public String grid; // required
  public String id; // required
  public Point point; // required
  /**
   * 
   * @see FeatureState
   */
  public FeatureState state; // required
  public String payload; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    GRID((short)1, "grid"),
    ID((short)2, "id"),
    POINT((short)3, "point"),
    /**
     * 
     * @see FeatureState
     */
    STATE((short)4, "state"),
    PAYLOAD((short)5, "payload");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // GRID
          return GRID;
        case 2: // ID
          return ID;
        case 3: // POINT
          return POINT;
        case 4: // STATE
          return STATE;
        case 5: // PAYLOAD
          return PAYLOAD;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.GRID, new org.apache.thrift.meta_data.FieldMetaData("grid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "Quadkey")));
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "FeatureID")));
    tmpMap.put(_Fields.POINT, new org.apache.thrift.meta_data.FieldMetaData("point", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Point.class)));
    tmpMap.put(_Fields.STATE, new org.apache.thrift.meta_data.FieldMetaData("state", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, FeatureState.class)));
    tmpMap.put(_Fields.PAYLOAD, new org.apache.thrift.meta_data.FieldMetaData("payload", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "JSON")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Feature.class, metaDataMap);
  }

  public Feature() {
    this.payload = "{}";

  }

  public Feature(
    String grid,
    String id,
    Point point,
    FeatureState state,
    String payload)
  {
    this();
    this.grid = grid;
    this.id = id;
    this.point = point;
    this.state = state;
    this.payload = payload;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Feature(Feature other) {
    if (other.isSetGrid()) {
      this.grid = other.grid;
    }
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetPoint()) {
      this.point = new Point(other.point);
    }
    if (other.isSetState()) {
      this.state = other.state;
    }
    if (other.isSetPayload()) {
      this.payload = other.payload;
    }
  }

  public Feature deepCopy() {
    return new Feature(this);
  }

  @Override
  public void clear() {
    this.grid = null;
    this.id = null;
    this.point = null;
    this.state = null;
    this.payload = "{}";

  }

  public String getGrid() {
    return this.grid;
  }

  public Feature setGrid(String grid) {
    this.grid = grid;
    return this;
  }

  public void unsetGrid() {
    this.grid = null;
  }

  /** Returns true if field grid is set (has been assigned a value) and false otherwise */
  public boolean isSetGrid() {
    return this.grid != null;
  }

  public void setGridIsSet(boolean value) {
    if (!value) {
      this.grid = null;
    }
  }

  public String getId() {
    return this.id;
  }

  public Feature setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public Point getPoint() {
    return this.point;
  }

  public Feature setPoint(Point point) {
    this.point = point;
    return this;
  }

  public void unsetPoint() {
    this.point = null;
  }

  /** Returns true if field point is set (has been assigned a value) and false otherwise */
  public boolean isSetPoint() {
    return this.point != null;
  }

  public void setPointIsSet(boolean value) {
    if (!value) {
      this.point = null;
    }
  }

  /**
   * 
   * @see FeatureState
   */
  public FeatureState getState() {
    return this.state;
  }

  /**
   * 
   * @see FeatureState
   */
  public Feature setState(FeatureState state) {
    this.state = state;
    return this;
  }

  public void unsetState() {
    this.state = null;
  }

  /** Returns true if field state is set (has been assigned a value) and false otherwise */
  public boolean isSetState() {
    return this.state != null;
  }

  public void setStateIsSet(boolean value) {
    if (!value) {
      this.state = null;
    }
  }

  public String getPayload() {
    return this.payload;
  }

  public Feature setPayload(String payload) {
    this.payload = payload;
    return this;
  }

  public void unsetPayload() {
    this.payload = null;
  }

  /** Returns true if field payload is set (has been assigned a value) and false otherwise */
  public boolean isSetPayload() {
    return this.payload != null;
  }

  public void setPayloadIsSet(boolean value) {
    if (!value) {
      this.payload = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case GRID:
      if (value == null) {
        unsetGrid();
      } else {
        setGrid((String)value);
      }
      break;

    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case POINT:
      if (value == null) {
        unsetPoint();
      } else {
        setPoint((Point)value);
      }
      break;

    case STATE:
      if (value == null) {
        unsetState();
      } else {
        setState((FeatureState)value);
      }
      break;

    case PAYLOAD:
      if (value == null) {
        unsetPayload();
      } else {
        setPayload((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case GRID:
      return getGrid();

    case ID:
      return getId();

    case POINT:
      return getPoint();

    case STATE:
      return getState();

    case PAYLOAD:
      return getPayload();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case GRID:
      return isSetGrid();
    case ID:
      return isSetId();
    case POINT:
      return isSetPoint();
    case STATE:
      return isSetState();
    case PAYLOAD:
      return isSetPayload();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Feature)
      return this.equals((Feature)that);
    return false;
  }

  public boolean equals(Feature that) {
    if (that == null)
      return false;

    boolean this_present_grid = true && this.isSetGrid();
    boolean that_present_grid = true && that.isSetGrid();
    if (this_present_grid || that_present_grid) {
      if (!(this_present_grid && that_present_grid))
        return false;
      if (!this.grid.equals(that.grid))
        return false;
    }

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_point = true && this.isSetPoint();
    boolean that_present_point = true && that.isSetPoint();
    if (this_present_point || that_present_point) {
      if (!(this_present_point && that_present_point))
        return false;
      if (!this.point.equals(that.point))
        return false;
    }

    boolean this_present_state = true && this.isSetState();
    boolean that_present_state = true && that.isSetState();
    if (this_present_state || that_present_state) {
      if (!(this_present_state && that_present_state))
        return false;
      if (!this.state.equals(that.state))
        return false;
    }

    boolean this_present_payload = true && this.isSetPayload();
    boolean that_present_payload = true && that.isSetPayload();
    if (this_present_payload || that_present_payload) {
      if (!(this_present_payload && that_present_payload))
        return false;
      if (!this.payload.equals(that.payload))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Feature other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Feature typedOther = (Feature)other;

    lastComparison = Boolean.valueOf(isSetGrid()).compareTo(typedOther.isSetGrid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grid, typedOther.grid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetId()).compareTo(typedOther.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, typedOther.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPoint()).compareTo(typedOther.isSetPoint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPoint()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.point, typedOther.point);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetState()).compareTo(typedOther.isSetState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.state, typedOther.state);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPayload()).compareTo(typedOther.isSetPayload());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPayload()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.payload, typedOther.payload);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Feature(");
    boolean first = true;

    sb.append("grid:");
    if (this.grid == null) {
      sb.append("null");
    } else {
      sb.append(this.grid);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("point:");
    if (this.point == null) {
      sb.append("null");
    } else {
      sb.append(this.point);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("state:");
    if (this.state == null) {
      sb.append("null");
    } else {
      sb.append(this.state);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("payload:");
    if (this.payload == null) {
      sb.append("null");
    } else {
      sb.append(this.payload);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (grid == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'grid' was not present! Struct: " + toString());
    }
    if (id == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not present! Struct: " + toString());
    }
    if (point == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'point' was not present! Struct: " + toString());
    }
    if (state == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'state' was not present! Struct: " + toString());
    }
    if (payload == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'payload' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (point != null) {
      point.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class FeatureStandardSchemeFactory implements SchemeFactory {
    public FeatureStandardScheme getScheme() {
      return new FeatureStandardScheme();
    }
  }

  private static class FeatureStandardScheme extends StandardScheme<Feature> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Feature struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // GRID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.grid = iprot.readString();
              struct.setGridIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // POINT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.point = new Point();
              struct.point.read(iprot);
              struct.setPointIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.state = FeatureState.findByValue(iprot.readI32());
              struct.setStateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PAYLOAD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.payload = iprot.readString();
              struct.setPayloadIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Feature struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.grid != null) {
        oprot.writeFieldBegin(GRID_FIELD_DESC);
        oprot.writeString(struct.grid);
        oprot.writeFieldEnd();
      }
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.point != null) {
        oprot.writeFieldBegin(POINT_FIELD_DESC);
        struct.point.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.state != null) {
        oprot.writeFieldBegin(STATE_FIELD_DESC);
        oprot.writeI32(struct.state.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.payload != null) {
        oprot.writeFieldBegin(PAYLOAD_FIELD_DESC);
        oprot.writeString(struct.payload);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FeatureTupleSchemeFactory implements SchemeFactory {
    public FeatureTupleScheme getScheme() {
      return new FeatureTupleScheme();
    }
  }

  private static class FeatureTupleScheme extends TupleScheme<Feature> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Feature struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.grid);
      oprot.writeString(struct.id);
      struct.point.write(oprot);
      oprot.writeI32(struct.state.getValue());
      oprot.writeString(struct.payload);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Feature struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.grid = iprot.readString();
      struct.setGridIsSet(true);
      struct.id = iprot.readString();
      struct.setIdIsSet(true);
      struct.point = new Point();
      struct.point.read(iprot);
      struct.setPointIsSet(true);
      struct.state = FeatureState.findByValue(iprot.readI32());
      struct.setStateIsSet(true);
      struct.payload = iprot.readString();
      struct.setPayloadIsSet(true);
    }
  }

}
