var thrift = require('thrift');
var express = require('express');
var bodyParser = require('body-parser');
var nodeKafkaBridgeClient = require('./node-kafka-bridge-client');

var app = express();
    app.use(bodyParser.json());       // to support JSON-encoded bodies
    app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
        extended: true
    }));


var expressWs = require('express-ws')(app);

nodeKafkaBridgeClient(function(msg) {
    expressWs.getWss('/kafka').clients.forEach(function(client) {
        client.send(msg.toString());
    })
});


var Geospatial = require('./Geospatial');
var ttypes = require('./geospatial_types');

transport = thrift.TBufferedTransport();
protocol = thrift.TBinaryProtocol();

var connection = thrift.createConnection("localhost", 9090, {
    transport : transport,
    protocol : protocol
});

var client = thrift.createClient(Geospatial, connection);


app.use(express.static('pub'));

app.get('/', function(req, res) {
   res.sendfile('pub/index.html', {root: __dirname});
});

app.get('/feature/:id', function(req, res) {
    client.getFeature(req.params.id, function(error, feature) {
        res.json(feature);
    });
});

app.post('/features', function(req, res) {
    if(req.body.point.x && req.body.point.y) {
        client.createFeature(new ttypes.Point({x: parseFloat(req.body.point.x), y: parseFloat(req.body.point.y)}), req.body.payload, function(err, feature) {
            res.json(feature);
        });
    }
});

app.get('/features/:topLeftX,:topLeftY/:btmRightX,:btmRightY', function(req, res) {
    var rect = new ttypes.Rectangle();

    rect.top_lt = new ttypes.Point({x: parseFloat(req.params.topLeftX), y: parseFloat(req.params.topLeftY)});
    rect.btm_rt = new ttypes.Point({x: parseFloat(req.params.btmRightX), y: parseFloat(req.params.btmRightY)});

    client.getFeaturesInRect(rect, function(err, features) {
        res.json(features);
    });
});

app.put('/feature/:id', function(req, res) {
    /*
        Required params in put:
        * point: {x, y}
        * payload: string
        * state: 1 (Clean) or 2 (Dirty)
        * grid: quadkey - required in order to do look up in cassandra
     */

    var feature = new ttypes.Feature({
        grid: req.body.grid,
        id: req.params.id,
        point: new ttypes.Point({x: parseFloat(req.body.point.x), y: parseFloat(req.body.point.y)}),
        state: parseInt(req.body.state) == 1 ? ttypes.FeatureState.CLEAN : ttypes.FeatureState.DIRTY,
        payload: req.body.payload
    });


    client.saveFeature(feature, function(err, result) {
        res.json({'status': result ? 'success' : 'fail'});
    })
});


app.delete('/feature/:id', function(req, res) {
    /*
         Required params in put:
         * point: {x, y}
         * payload: string
         * state: 1 (Clean) or 0 (Dirty)
         * grid: quadkey - required in order to do look up in cassandra
     */

    var feature = new ttypes.Feature({
        grid: req.body.grid,
        id: req.params.id,
        point: new ttypes.Point({x: parseFloat(req.body.point.x), y: parseFloat(req.body.point.y)}),
        state: parseInt(req.body.state) == 1 ? ttypes.FeatureState.CLEAN : ttypes.FeatureState.DIRTY,
        payload: req.body.payload
    });

    client.deleteFeature(feature, function(err, result) {
        res.json({'status': result ? 'success' : 'fail'});
    });
});

app.ws('/kafka', function(ws, req) {
    // should do verification for security in a production environment
    ws.on('message', function(msg) {
        // noop
    });
});

app.listen(8080, function() {
   console.log('Web Server Started.');
});

function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
        s4() + '-' + s4() + s4() + s4();
}