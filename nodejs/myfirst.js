var http = require("http");
var fs = require('fs');

http.createServer(function (req,res){
    fs.readFile('demofile1.html',function(err, data){
        res.writeHead(200, {'content-Type':'text/html'});
        res.write(data);
        return res.end();
    });
}).listen(8080);

console.log("서버 시작됨 : http://localhost:8080");