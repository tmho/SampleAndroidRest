var restify = require('restify');
var util = require('util');
var version = "0.0.1"
var port = 3000

var smarta = restify.createServer({
    name: "Silly Server",
    version: version
});

smarta.use(function(req, res, next) {
    res.setHeader("Content-Type", "application/json");
    next();
});

smarta.use(restify.bodyParser());

smarta.post("/sayHello", respond);

function respond(req, res, next) {
  res.send( { message : 'hello ' + req.body.name } );
  next();
}

smarta.listen(port, function() {
    console.info(util.format("Silly Server v%s - Listening on port %s...", version, port));
});
