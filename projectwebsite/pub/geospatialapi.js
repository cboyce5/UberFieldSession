/*var GeospatialAPI = function() {

    geospatial.thrift.GeospatialClient.prototype.ws = null;

    geospatial.thrift.GeospatialClient.prototype.init = function() {
        if ("WebSocket" in window) {
            console.log('creating ws');
            this.ws = new WebSocket('ws://'+window.location.host+'/kafka');

            this.ws.onopen = function() {
                this.send("Message to send");
            }.bind(this.ws);

            this.ws.onmessage = function (evt)  {
                var received_msg = evt.data;
                console.log(received_msg);
            };
        } else {
            alert("WebSocket NOT supported by your Browser!");
        }
    };

    var transport = new Thrift.Transport("http://localhost:8080/geospatial");
    var protocol = new Thrift.Protocol(transport);

    return new geospatial.thrift.GeospatialClient(protocol);
}();

function runFeatureCreateTest() {
    GeospatialAPI.createFeature(geospatial.thrift.Point({x: 69, y: 69}), JSON.stringify({}));
}*/


var GeospatialAPI = function() {
    var ws = null;
    return {
        init: function() {
            if ("WebSocket" in window) {
                console.log('creating ws');
                ws = new WebSocket('ws://'+window.location.host+'/kafka');

                ws.onopen = function() {
                    this.send("Message to send");
                }.bind(ws);

                ws.onmessage = function (evt)  {
                    var received_msg = evt.data;
                    console.log(received_msg);
                };
            } else {
                alert("WebSocket NOT supported by your Browser!");
            }
        },

        createFeature: function(point, payload, cb) {
            /*
             point should be in format: {x: , y: };
             payload should be json
             cb is a function to run as a callback
             */

            if($.type(payload) !== 'string') {
                payload = JSON.stringify(payload);

            }
            $.post('/features', {point: point, payload: payload}, function(feature) {
                if(cb)
                    cb(feature);
            });

        },

        getFeature: function(id, cb) {
            $.get('/feature/' + id, function(feature) {
                if(cb)
                    cb(feature);
            })
        },

        getFeaturesInRect: function(top_lt, btm_rt, cb) {
            if(!top_lt.x)
                top_lt.x = -180;

            if(!top_lt.y)
                top_lt.y = 90;

            if(!btm_rt.x)
                btm_rt.x = 180;

            if(!btm_rt.y)
                btm_rt.y = -90;


            $.get('/features/' + top_lt.x + ',' + top_lt.y +'/' + btm_rt.x + ',' + btm_rt.y, function(features) {
                if(cb)
                    cb(features);
                console.log(features);
            })
        },

        updateFeature: function(feature, cb) {
            if($.type(feature.payload) !== 'string') {
                feature.payload = JSON.stringify(feature.payload);

            }

            $.ajax ({
                url: '/feature/' + feature.id,
                type: 'PUT',
                data: feature,

                success: function(status) {
                    if(cb)
                        cb(status);
                }
            });
        },

        deleteFeature: function(feature, cb) {
            $.ajax ({
                url: '/feature/' + feature.id,
                type: 'DELETE',
                data: feature,

                success: function(feature) {
                    if(cb)
                        cb(feature);
                }
            });
        }
    }
}();


function runFeatureTests() {
    GeospatialAPI.createFeature({x: 69, y: 69}, {tile: "Here fuck", description: "i like ass"}, function(feature) {
        GeospatialAPI.getFeature(feature.id, function(feature){
            console.log('got my feature that i just created: ' + feature.id);
            console.log('now we want to update it');
            feature.point.x = -3;
            feature.point.y = 3;
            GeospatialAPI.updateFeature(feature, function(feature) {
                console.log('now my feature is at: ' + feature.point.x + ' ' + feature.point.y);
                console.log('now im deleting it');
                GeospatialAPI.deleteFeature(feature, function(status) {
                    if(status.status == 'success') {
                        console.log('this shit was deleted');
                    }
                })
            })
        })
    });

}