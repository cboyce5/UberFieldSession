/**
 * Created by ricky on 6/4/16.
 */
var net = require('net');

exports = module.exports = createClient

function createClient(cb) {
    var client = new net.Socket();

    client.connect(6969, 'localhost', function() {
        console.log('Node Kafka Bridge Client connected.');
    });

    client.on('data', function(data) {
        cb(data);
    });
}


