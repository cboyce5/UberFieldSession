/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package geospatial.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum FeatureState implements org.apache.thrift.TEnum {
  CLEAN(1),
  DIRTY(2);

  private final int value;

  private FeatureState(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static FeatureState findByValue(int value) { 
    switch (value) {
      case 1:
        return CLEAN;
      case 2:
        return DIRTY;
      default:
        return null;
    }
  }
}
