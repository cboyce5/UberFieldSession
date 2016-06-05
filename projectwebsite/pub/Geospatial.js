//
// Autogenerated by Thrift Compiler (0.9.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//


//HELPER FUNCTIONS AND STRUCTURES

geospatial.thrift.Geospatial_createFeature_args = function(args) {
  this.point = null;
  this.payload = null;
  if (args) {
    if (args.point !== undefined) {
      this.point = args.point;
    }
    if (args.payload !== undefined) {
      this.payload = args.payload;
    }
  }
};
geospatial.thrift.Geospatial_createFeature_args.prototype = {};
geospatial.thrift.Geospatial_createFeature_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRUCT) {
        this.point = new geospatial.thrift.Point();
        this.point.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.payload = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_createFeature_args.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_createFeature_args');
  if (this.point !== null && this.point !== undefined) {
    output.writeFieldBegin('point', Thrift.Type.STRUCT, 1);
    this.point.write(output);
    output.writeFieldEnd();
  }
  if (this.payload !== null && this.payload !== undefined) {
    output.writeFieldBegin('payload', Thrift.Type.STRING, 2);
    output.writeString(this.payload);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_createFeature_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
geospatial.thrift.Geospatial_createFeature_result.prototype = {};
geospatial.thrift.Geospatial_createFeature_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.STRUCT) {
        this.success = new geospatial.thrift.Feature();
        this.success.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_createFeature_result.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_createFeature_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.STRUCT, 0);
    this.success.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeature_args = function(args) {
  this.id = null;
  if (args) {
    if (args.id !== undefined) {
      this.id = args.id;
    }
  }
};
geospatial.thrift.Geospatial_getFeature_args.prototype = {};
geospatial.thrift.Geospatial_getFeature_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.id = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeature_args.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_getFeature_args');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.STRING, 1);
    output.writeString(this.id);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeature_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
geospatial.thrift.Geospatial_getFeature_result.prototype = {};
geospatial.thrift.Geospatial_getFeature_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.STRUCT) {
        this.success = new geospatial.thrift.Feature();
        this.success.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeature_result.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_getFeature_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.STRUCT, 0);
    this.success.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeaturesInRect_args = function(args) {
  this.rect = null;
  if (args) {
    if (args.rect !== undefined) {
      this.rect = args.rect;
    }
  }
};
geospatial.thrift.Geospatial_getFeaturesInRect_args.prototype = {};
geospatial.thrift.Geospatial_getFeaturesInRect_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRUCT) {
        this.rect = new geospatial.thrift.Rectangle();
        this.rect.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeaturesInRect_args.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_getFeaturesInRect_args');
  if (this.rect !== null && this.rect !== undefined) {
    output.writeFieldBegin('rect', Thrift.Type.STRUCT, 1);
    this.rect.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeaturesInRect_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
geospatial.thrift.Geospatial_getFeaturesInRect_result.prototype = {};
geospatial.thrift.Geospatial_getFeaturesInRect_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.LIST) {
        var _size0 = 0;
        var _rtmp34;
        this.success = [];
        var _etype3 = 0;
        _rtmp34 = input.readListBegin();
        _etype3 = _rtmp34.etype;
        _size0 = _rtmp34.size;
        for (var _i5 = 0; _i5 < _size0; ++_i5)
        {
          var elem6 = null;
          elem6 = new geospatial.thrift.Feature();
          elem6.read(input);
          this.success.push(elem6);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_getFeaturesInRect_result.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_getFeaturesInRect_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.LIST, 0);
    output.writeListBegin(Thrift.Type.STRUCT, this.success.length);
    for (var iter7 in this.success)
    {
      if (this.success.hasOwnProperty(iter7))
      {
        iter7 = this.success[iter7];
        iter7.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_saveFeature_args = function(args) {
  this.feature = null;
  if (args) {
    if (args.feature !== undefined) {
      this.feature = args.feature;
    }
  }
};
geospatial.thrift.Geospatial_saveFeature_args.prototype = {};
geospatial.thrift.Geospatial_saveFeature_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRUCT) {
        this.feature = new geospatial.thrift.Feature();
        this.feature.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_saveFeature_args.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_saveFeature_args');
  if (this.feature !== null && this.feature !== undefined) {
    output.writeFieldBegin('feature', Thrift.Type.STRUCT, 1);
    this.feature.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_saveFeature_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
geospatial.thrift.Geospatial_saveFeature_result.prototype = {};
geospatial.thrift.Geospatial_saveFeature_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.BOOL) {
        this.success = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_saveFeature_result.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_saveFeature_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.BOOL, 0);
    output.writeBool(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_deleteFeature_args = function(args) {
  this.feature = null;
  if (args) {
    if (args.feature !== undefined) {
      this.feature = args.feature;
    }
  }
};
geospatial.thrift.Geospatial_deleteFeature_args.prototype = {};
geospatial.thrift.Geospatial_deleteFeature_args.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRUCT) {
        this.feature = new geospatial.thrift.Feature();
        this.feature.read(input);
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_deleteFeature_args.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_deleteFeature_args');
  if (this.feature !== null && this.feature !== undefined) {
    output.writeFieldBegin('feature', Thrift.Type.STRUCT, 1);
    this.feature.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.Geospatial_deleteFeature_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined) {
      this.success = args.success;
    }
  }
};
geospatial.thrift.Geospatial_deleteFeature_result.prototype = {};
geospatial.thrift.Geospatial_deleteFeature_result.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 0:
      if (ftype == Thrift.Type.BOOL) {
        this.success = input.readBool().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

geospatial.thrift.Geospatial_deleteFeature_result.prototype.write = function(output) {
  output.writeStructBegin('Geospatial_deleteFeature_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.BOOL, 0);
    output.writeBool(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

geospatial.thrift.GeospatialClient = function(input, output) {
    this.input = input;
    this.output = (!output) ? input : output;
    this.seqid = 0;
};
geospatial.thrift.GeospatialClient.prototype = {};
geospatial.thrift.GeospatialClient.prototype.createFeature = function(point, payload) {
  this.send_createFeature(point, payload);
  return this.recv_createFeature();
};

geospatial.thrift.GeospatialClient.prototype.send_createFeature = function(point, payload) {
  this.output.writeMessageBegin('createFeature', Thrift.MessageType.CALL, this.seqid);
  var args = new geospatial.thrift.Geospatial_createFeature_args();
  args.point = point;
  args.payload = payload;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

geospatial.thrift.GeospatialClient.prototype.recv_createFeature = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new geospatial.thrift.Geospatial_createFeature_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'createFeature failed: unknown result';
};
geospatial.thrift.GeospatialClient.prototype.getFeature = function(id) {
  this.send_getFeature(id);
  return this.recv_getFeature();
};

geospatial.thrift.GeospatialClient.prototype.send_getFeature = function(id) {
  this.output.writeMessageBegin('getFeature', Thrift.MessageType.CALL, this.seqid);
  var args = new geospatial.thrift.Geospatial_getFeature_args();
  args.id = id;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

geospatial.thrift.GeospatialClient.prototype.recv_getFeature = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new geospatial.thrift.Geospatial_getFeature_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'getFeature failed: unknown result';
};
geospatial.thrift.GeospatialClient.prototype.getFeaturesInRect = function(rect) {
  this.send_getFeaturesInRect(rect);
  return this.recv_getFeaturesInRect();
};

geospatial.thrift.GeospatialClient.prototype.send_getFeaturesInRect = function(rect) {
  this.output.writeMessageBegin('getFeaturesInRect', Thrift.MessageType.CALL, this.seqid);
  var args = new geospatial.thrift.Geospatial_getFeaturesInRect_args();
  args.rect = rect;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

geospatial.thrift.GeospatialClient.prototype.recv_getFeaturesInRect = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new geospatial.thrift.Geospatial_getFeaturesInRect_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'getFeaturesInRect failed: unknown result';
};
geospatial.thrift.GeospatialClient.prototype.saveFeature = function(feature) {
  this.send_saveFeature(feature);
  return this.recv_saveFeature();
};

geospatial.thrift.GeospatialClient.prototype.send_saveFeature = function(feature) {
  this.output.writeMessageBegin('saveFeature', Thrift.MessageType.CALL, this.seqid);
  var args = new geospatial.thrift.Geospatial_saveFeature_args();
  args.feature = feature;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

geospatial.thrift.GeospatialClient.prototype.recv_saveFeature = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new geospatial.thrift.Geospatial_saveFeature_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'saveFeature failed: unknown result';
};
geospatial.thrift.GeospatialClient.prototype.deleteFeature = function(feature) {
  this.send_deleteFeature(feature);
  return this.recv_deleteFeature();
};

geospatial.thrift.GeospatialClient.prototype.send_deleteFeature = function(feature) {
  this.output.writeMessageBegin('deleteFeature', Thrift.MessageType.CALL, this.seqid);
  var args = new geospatial.thrift.Geospatial_deleteFeature_args();
  args.feature = feature;
  args.write(this.output);
  this.output.writeMessageEnd();
  return this.output.getTransport().flush();
};

geospatial.thrift.GeospatialClient.prototype.recv_deleteFeature = function() {
  var ret = this.input.readMessageBegin();
  var fname = ret.fname;
  var mtype = ret.mtype;
  var rseqid = ret.rseqid;
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(this.input);
    this.input.readMessageEnd();
    throw x;
  }
  var result = new geospatial.thrift.Geospatial_deleteFeature_result();
  result.read(this.input);
  this.input.readMessageEnd();

  if (null !== result.success) {
    return result.success;
  }
  throw 'deleteFeature failed: unknown result';
};
