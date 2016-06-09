var kafka = require('kafka-node'),
    net   =  require('net');

var connections = {};

var server = net.createServer(function(connection) {
    connection.guid = guid();
    connections[connection.guid] = connection;

    connection.setKeepAlive(true, 0);

    connection.on('end', function() {
        console.log('deleting connection');
        delete connections[connection.guid];
    });

    connection.on('error', function() {
        delete connections[connection.guid];
    });
});

server.listen(6969, function() {
    console.log('Kafka Bridge started.');
});

Consumer = kafka.Consumer,
    client = new kafka.Client(),
    consumer = new Consumer(
        client,
        [
            { topic: 'geospatial', partition: 0 }
        ],
        {
            autoCommit: false
        }
    );

consumer.on('message', function (message) {
    for(var guid in connections) {
        if(connections.hasOwnProperty(guid)) {
            console.log(message.value);
            connections[guid].write(message.value);
        }
    }
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
