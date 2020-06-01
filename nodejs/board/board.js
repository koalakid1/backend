var fs = require('fs');
var ejs = require('ejs');
var mysql = require('mysql');
var express = require('express');
var bodyParser = require('body-parser');

var client = mysql.createConnection({
    user : 'root',
    password : '1234',
    database : 'Company'
});

var app = express();
app.use('/css', express.static('css'));
app.use(bodyParser.urlencoded({extended : false}));

app.listen(52273, function(){
    console.log('server running at http://127.0.0.1:52273');
});

//라우트(url 이동)
app.get('/', function(request, response){
    fs.readFile('board_list.html', 'utf8', function(error, data){
        client.query('select * from board order by id desc', function(error, results){
            response.send(ejs.render(data,{
                data:results
            }));
        });
    });
});

app.get('/insert', function (request, response){
    fs.readFile('board_insert.html', 'utf8', function(error, data){
        response.send(data);
    });
});

app.post('/insert', function(request,response){
    var body = request.body;
    var d= new Date();
    var year = d.getFullYear();
    var month = d.getMonth()+1;
    var date = d.getDate();
    if (month<10) {
        month="0"+month;
    }
    if (date < 10) {
        date="0"+date;
    }
    var ymd = year + "-" + month + "-" + date;
    client.query("insert into board(title, content, wdate) values(?,?,?)", [
        body.title, body.content, ymd
    ], function(){
        response.redirect('/');
    });
});

app.get('/content/:id', function (request, response) {
    // 파일을 읽습니다.
    fs.readFile('board_content.html', 'utf8', function (error, data) {
        // 데이터베이스 쿼리를 실행합니다.
        client.query('SELECT * FROM board WHERE id = ?', [
            request.params.id
        ], function (error, result1) {

            // 데이터베이스 쿼리를 실행합니다.
            client.query('SELECT * FROM board_repl WHERE parent_id = ?', [
                request.params.id
            ], function (error, result2) {
                // 응답합니다.
                response.send(ejs.render(data, {
                    data: result1[0], data2: result2
                }));
            });

        });
    });

});
app.get('/edit/:id', function (request, response) {
    // 파일을 읽습니다.
    fs.readFile('board_edit.html', 'utf8', function (error, data) {
        // 데이터베이스 쿼리를 실행합니다.
        client.query('SELECT * FROM board WHERE id = ?', [
            request.params.id
        ], function (error, result) {
            // 응답합니다.
            response.send(ejs.render(data, {
                data: result[0]
            }));
        });
    });
});
//edit form에 데이터입력후 submit을 클릭했을 때
app.post('/edit/:id', function (request, response) {
    // 변수를 선언합니다.
    var body = request.body

    // 데이터베이스 쿼리를 실행합니다.
    client.query('UPDATE board SET title=?, content=?, wdate=? WHERE id=?', [
        body.title, body.content, body.wdate, request.params.id
    ], function () {
        // 응답합니다.
        response.redirect('/');//목록으로 이동
    });
});

app.get('/delete/:id', function (request, response) {
    // 데이터베이스 쿼리를 실행합니다.
    client.query('DELETE FROM board WHERE id=?', [request.params.id], function () {
        // 응답합니다.
        response.redirect('/');
    });
});