var express = require('express');
var morgan = require('morgan');

//import session and file store
var session = require('express-session');
var FileStore = require('session-file-store')(session);

var hostname = 'localhost';
var port = 3000;

var app = express();

app.use(morgan('dev'));

//make the app use session
app.use(session({
  name: 'session-id',
  secret: '12345-67890-09876-54321',
  saveUninitialized: true,
  resave: true,
  store: new FileStore()
})); 
// Session

function auth (req, res, next) {
    console.log(req.headers);
    //make the user enter username and password to generate cookie if the auth header has no cookie
    if(!req.session.user){
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
        res.session.user = 'admin';
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
    if(req.session.user === 'admin'){
        console.log('req.session: ', req.session);
        
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