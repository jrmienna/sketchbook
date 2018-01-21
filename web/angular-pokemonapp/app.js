var express = require('express');

var app = express();

app.get('/', function(req, res) {
    res.sendFile(__dirname + '/views/index.html');
});

app.use('/ctrl/', express.static(__dirname + '/controllers/'));

app.listen(3000, function() {
    console.log('Listening on port 3000');
});
