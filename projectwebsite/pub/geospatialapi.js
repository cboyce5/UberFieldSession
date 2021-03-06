var GeospatialAPI = function() {
    return {
        init: function() {
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