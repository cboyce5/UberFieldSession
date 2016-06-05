var GeospatialAPI = {
    ws: null,
    init: function() {
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

    },

    onFeaturesUpdated: function() {console.log('implement this shit.')},

    getFeatureForRect: function(topLeftX, topLeftY, btmLeftX, btmLeftY) {

    }
};