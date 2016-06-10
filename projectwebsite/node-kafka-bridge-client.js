/**
 * Created by ricky on 6/4/16.
 */
var net = require('net');

exports = module.exports = createClient

function createClient(cb) {
    var client = new net.Socket();

    connect();

    client.on('end', function() {
        console.log('ended')
        setTimeout(connect, 500);
    });

    client.on('error', function() {
        console.log('errored')

        setTimeout(connect, 500);
    });

    client.on('data', function(data) {
        console.log(data);
        cb(data);
    });

    function connect() {
        client.connect(6969, 'localhost', function() {
            console.log('Node Kafka Bridge Client connected.');
            client.setKeepAlive(true, 0);
        });
    }
}


