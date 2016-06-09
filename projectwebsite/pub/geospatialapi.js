var GeospatialAPI = function() {
    var ws = null;
    return {
        init: function() {
            /*var self = this;
            if ("WebSocket" in window) {
                console.log('attempting to connect ws');
                ws = new WebSocket('ws://'+window.location.host+'/kafka');

                ws.onopen = function() {
                    console.log('opened');
                    ws.send("Message to send");
                }.bind(ws);

                ws.onerror = function(err) {
                    console.log('got an error');
                    console.log(err);
                };

                ws.onclose = function (event) {
                    var reason;
                    alert(event.code);
                    // See http://tools.ietf.org/html/rfc6455#section-7.4.1
                    if (event.code == 1000)
                        reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
                    else if (event.code == 1001)
                        reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
                    else if (event.code == 1002)
                        reason = "An endpoint is terminating the connection due to a protocol error";
                    else if (event.code == 1003)
                        reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
                    else if (event.code == 1004)
                        reason = "Reserved. The specific meaning might be defined in the future.";
                    else if (event.code == 1005)
                        reason = "No status code was actually present.";
                    else if (event.code == 1006)
                        reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
                    else if (event.code == 1007)
                        reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
                    else if (event.code == 1008)
                        reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
                    else if (event.code == 1009)
                        reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
                    else if (event.code == 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
                        reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
                    else if (event.code == 1011)
                        reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
                    else if (event.code == 1015)
                        reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
                    else
                        reason = "Unknown reason";

                    console.log(reason);
                }

                    ws.onmessage = function(evt) {
                    console.log('receiving message');
                    self.onUpdateReceived(evt.data);
                };
            } else {
                alert("WebSocket NOT supported by your Browser!");
            }*/

            var socket = io.connect('http://'+window.location.host);

            var self = this;

            socket.on('geospatial', function (data) {
                self.onUpdateReceived(data);
            });
        },

        onUpdateReceived: function(data) {
            console.log('this needs to be implemented - received: ' + data);
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
                if(feature && feature.payload) {
                    feature.payload = JSON.parse(feature.payload);
                }
                if(cb)
                    cb(feature);
            });

        },

        getFeature: function(id, cb) {
            $.get('/feature/' + id, function(feature) {
                if(feature && feature.payload) {
                    feature.payload = JSON.parse(feature.payload);
                }
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


            $.get('/features/' + top_lt.x + ',' + top_lt.y + '/' + btm_rt.x + ',' + btm_rt.y, function(features) {
                if(features) {
                    for(var i = 0; i < features.length; i++) {
                        features[i].payload = JSON.parse(features[i].payload);
                    }
                }

                if(cb)
                    cb(features);
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
            if($.type(feature.payload) !== 'string') {
                feature.payload = JSON.stringify(feature.payload);
            }
            
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
GeospatialAPI.init();


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