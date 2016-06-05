var thrift = require('thrift');
var express = require('express');
var bodyParser = require('body-parser')

var app = express();
    app.use(bodyParser.json());       // to support JSON-encoded bodies
    app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
        extended: true
    }));


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
        console.log(req.body.point.x);
        client.createFeature(new ttypes.Point({x: parseFloat(req.body.point.x), y: parseFloat(req.body.point.y)}), req.body.payload, function(err, feature) {
            res.json(feature);
        });
    }
});

app.get('/features/:topLeftX,:topLeftY/:btmRightX,:btmRightY', function(req, res) {
    var rect = new ttypes.Rectangle();

    rect.top_lt = new ttypes.Point({x: parseFloat(req.params.topLeftX), y: parseFloat(req.params.topLeftY)});
    rect.btm_rt = new ttypes.Point({x: parseFloat(req.params.btmRightX), y: parseFloat(req.params.btmRightY)});

    console.log('getting for rect: ' + rect.top_lt.x + " " + rect.top_lt.y + " " + rect.btm_rt.x + " " + rect.btm_rt.y);

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

app.listen(8080, function() {
   console.log('Web Server Started.');
});