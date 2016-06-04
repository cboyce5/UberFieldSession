var thrift = require('thrift');
var express = require('express');

var app = express();

var expressWs = require('express-ws')(app);

var Geospatial = require('./Geospatial');
var ttypes = require('./geospatial_types');

transport = thrift.TBufferedTransport();
protocol = thrift.TBinaryProtocol();

var connection = thrift.createConnection("localhost", 9090, {
    transport : transport,
    protocol : protocol
});

var client = thrift.createClient(Geospatial, connection);

connection.on('error', function(err) {
    assert(false, err);
});

app.get('/test', function(req, res) {
   var feature = client.createFeature(new ttypes.Point(68, 70), "{0x421}", function(err, feature) {
       res.send(feature.id);
   });

});

app.listen(8080, function() {
   console.log('Web Server Started.');
});