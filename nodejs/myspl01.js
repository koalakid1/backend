
var mysql = require('mysql');

var client = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '1234',
  database: 'Company'
});


client.query('INSERT INTO products (name, modelnumber, series) VALUES (?, ?, ?)', [
    '그랜져', 'granger7', 'granger'
], function (error, results, fields) {

});
client.query('SELECT * FROM products', function (error, result, fields) {
  if (error) {
    console.log(error);
  } else {
    console.log(result);
  }
});

client.end();