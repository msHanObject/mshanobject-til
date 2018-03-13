var mysql = require('mysql');

var conn = mysql.createConnection({
	host     : 'lawdy.cryctwa9qcbx.ap-northeast-2.rds.amazonaws.com',
    user     : 'minsu',
    password : 'minsu123',
    database : 'lawdy'
});

conn.connect();

/*
var sql = 'SELECT * FROM topic';

conn.query(sql, (err, rows, fields)=>{
	if (err){
		console.log(err);
	}
	//console.log('rows', rows);
	//console.log('fields', fields);
	for (var i=0; i<rows.length; i++){
		console.log(rows[i].title);
	}
});
*/

/*
var sql = 'INSERT INTO topic (title, description, author) VALUES("?", "?", "?")';

var params = ['Supervisor', 'Watcher', 'graphittie'];

conn.query(sql, params, (err, rows, fields)=>{
	if (err){
		console.log(err);
	}
	console.log(rows.insertId);
});
*/

/*
var sql = 'UPDATE topic SET title=?, author=? WHERE id=?';

var params = ['NPM', 'leezche', '1'];

conn.query(sql, params, (err, rows, fields)=>{
	if (err) {
		console.log(err);
	}
	console.log(rows);
});
*/

var sql = 'DELETE FROM topic WHERE id=?';

var params = [1];

conn.query(sql, params, (err, rows, fields)=>{
	if (err){
		console.log(err);
	}
	console.log(rows);
});

conn.end();
