var dishserver = require('./dishRouter')
var promoserver = require('./promoRouter')
var leaderserver = require('./leaderRouter')
var express = require('express');
var morgan = require('morgan');


var hostname = 'localhost';
var port = 3000;

var app = express();

app.use(morgan('dev'));
var dishRouter = express.Router();
var promoRouter =express.Router();
var leaderRouter = express.Router();
leaderserver(leaderRouter);
promoserver(promoRouter);
dishserver(dishRouter);
app.use('/leaders',leaderRouter);
app.use('/promotions',promoRouter);

app.use('/dishes',dishRouter);

app.use(express.static(__dirname + '/public'));

app.listen(port, hostname, function(){
  console.log(`Server running at http://${hostname}:${port}/`);
});