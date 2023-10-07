/**
 * Autogenerated by Thrift Compiler (0.19.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package ServicioCalculadora;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.19.0)", date = "2023-10-07")
public class CalculadoraExcepcion extends org.apache.thrift.TException implements org.apache.thrift.TBase<CalculadoraExcepcion, CalculadoraExcepcion._Fields>, java.io.Serializable, Cloneable, Comparable<CalculadoraExcepcion> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CalculadoraExcepcion");

  private static final org.apache.thrift.protocol.TField MENSAJE_FIELD_DESC = new org.apache.thrift.protocol.TField("mensaje", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new CalculadoraExcepcionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new CalculadoraExcepcionTupleSchemeFactory();

  private @org.apache.thrift.annotation.Nullable java.lang.String mensaje; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MENSAJE((short)1, "mensaje");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MENSAJE
          return MENSAJE;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MENSAJE, new org.apache.thrift.meta_data.FieldMetaData("mensaje", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CalculadoraExcepcion.class, metaDataMap);
  }

  public CalculadoraExcepcion() {
  }

  public CalculadoraExcepcion(
    java.lang.String mensaje)
  {
    this();
    this.mensaje = mensaje;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CalculadoraExcepcion(CalculadoraExcepcion other) {
    if (other.isSetMensaje()) {
      this.mensaje = other.mensaje;
    }
  }

  @Override
  public CalculadoraExcepcion deepCopy() {
    return new CalculadoraExcepcion(this);
  }

  @Override
  public void clear() {
    this.mensaje = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getMensaje() {
    return this.mensaje;
  }

  public void setMensaje(@org.apache.thrift.annotation.Nullable java.lang.String mensaje) {
    this.mensaje = mensaje;
  }

  public void unsetMensaje() {
    this.mensaje = null;
  }

  /** Returns true if field mensaje is set (has been assigned a value) and false otherwise */
  public boolean isSetMensaje() {
    return this.mensaje != null;
  }

  public void setMensajeIsSet(boolean value) {
    if (!value) {
      this.mensaje = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case MENSAJE:
      if (value == null) {
        unsetMensaje();
      } else {
        setMensaje((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case MENSAJE:
      return getMensaje();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case MENSAJE:
      return isSetMensaje();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof CalculadoraExcepcion)
      return this.equals((CalculadoraExcepcion)that);
    return false;
  }

  public boolean equals(CalculadoraExcepcion that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_mensaje = true && this.isSetMensaje();
    boolean that_present_mensaje = true && that.isSetMensaje();
    if (this_present_mensaje || that_present_mensaje) {
      if (!(this_present_mensaje && that_present_mensaje))
        return false;
      if (!this.mensaje.equals(that.mensaje))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMensaje()) ? 131071 : 524287);
    if (isSetMensaje())
      hashCode = hashCode * 8191 + mensaje.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(CalculadoraExcepcion other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetMensaje(), other.isSetMensaje());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMensaje()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mensaje, other.mensaje);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("CalculadoraExcepcion(");
    boolean first = true;

    sb.append("mensaje:");
    if (this.mensaje == null) {
      sb.append("null");
    } else {
      sb.append(this.mensaje);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CalculadoraExcepcionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public CalculadoraExcepcionStandardScheme getScheme() {
      return new CalculadoraExcepcionStandardScheme();
    }
  }

  private static class CalculadoraExcepcionStandardScheme extends org.apache.thrift.scheme.StandardScheme<CalculadoraExcepcion> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, CalculadoraExcepcion struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MENSAJE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.mensaje = iprot.readString();
              struct.setMensajeIsSet(true);
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
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, CalculadoraExcepcion struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.mensaje != null) {
        oprot.writeFieldBegin(MENSAJE_FIELD_DESC);
        oprot.writeString(struct.mensaje);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CalculadoraExcepcionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public CalculadoraExcepcionTupleScheme getScheme() {
      return new CalculadoraExcepcionTupleScheme();
    }
  }

  private static class CalculadoraExcepcionTupleScheme extends org.apache.thrift.scheme.TupleScheme<CalculadoraExcepcion> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CalculadoraExcepcion struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMensaje()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetMensaje()) {
        oprot.writeString(struct.mensaje);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CalculadoraExcepcion struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.mensaje = iprot.readString();
        struct.setMensajeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

