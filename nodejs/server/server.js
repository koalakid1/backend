// 모듈을 추출합니다.
var express = require('express');
var bodyParser = require('body-parser');
var mysql = require('mysql');
// 데이터베이스와 연결합니다.
var client = mysql.createConnection({
    user: 'root',
    password: '1234',
    database: 'Company'
});
// 웹 서버를 생성합니다.
var app = express();
app.use(express.static('public'));// public폴더 인식가능하도록 설정.
//app.use(express.bodyParser());
//app.use(app.router);
app.use(bodyParser.urlencoded({
    extended: false
  }));

//상품목록
app.get('/products', function (request, response) {
    // 데이터베이스 요청을 수행합니다.
    client.query('SELECT * FROM products', function (error, data) {
        response.send(data);
    });
});
//상품등록
app.post('/products', function (request, response) {
    // 변수를 선언합니다.
    //var name = request.params.name;
    //var modelnumber = request.params.modelnumber;
    //var series = request.params.series;
    var body=request.body;
    console.log(body.name);
    console.log(body.modelnumber);
    console.log(body.series);

    // 데이터베이스 요청을 수행합니다.
    client.query('INSERT INTO products (name, modelnumber, series) VALUES(?,?,?)', [
        body.name, body.modelnumber, body.series
    ], function (error, data) {
        console.log(data);
        response.send(data);
    });
});
//웹서버시작
app.listen(52273, function () {
    console.log('Server Running at http://127.0.0.1:52273');
});