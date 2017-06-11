var express = require('express');
var morgan = require('morgan');

//import cookie parser for parsing cookie from header
var cookieparser = require('cookie-parser');

var hostname = 'localhost';
var port = 3000;

var app = express();

app.use(morgan('dev'));
//make the app use cookie parser secret key
app.use(cookieparser('12345-67890-09876-54321'));// Secret Key---12345-67890-09876-54321

function auth (req, res, next) {
    console.log(req.headers);
    //make the user enter username and password to generate cookie if the auth header has no cookie
    if(!req.signedCookies.user){
    var authHeader = req.headers.authorization;
    if (!authHeader) {
        var err = new Error('You are not authenticated!');
        err.status = 401;
        next(err);
        return;
    }

    var auth = new Buffer(authHeader.split(' ')[1], 'base64').toString().split(':');
    var user = auth[0];
    var pass = auth[1];
    if (user == 'admin' && pass == 'password') {
      res.cookie('user','admin',{signed: true});
        next(); // authorized
    } else if(user == 'admin' && pass != 'password' ){
        var err = new Error('Password doesn\'t match Username!');
        err.status = 401;
        next(err);
    }
    else if(user != 'admin' && pass == 'password' ){
        var err = new Error(' doesn\'t match Username!');
        err.status = 401;
        next(err);
    }

}
else{
    if(req.signedCookies.user === 'admin'){
        console.log(req.signedCookies);
        
        next();
    }
    else{
        var err = new Error(" Invalid Cookie");
        err.status = 401;
        next(err);
    }
}
}

app.use(auth);

app.use(express.static(__dirname + '/public'));
app.use(function(err,req,res,next) {
            res.writeHead(err.status || 500, {
            'WWW-Authenticate': 'Basic',
            'Content-Type': 'text/plain'
        });
        res.end(err.message);
});

app.listen(port, hostname, function(){
  console.log(`Server running at http://${hostname}:${port}/`);
});