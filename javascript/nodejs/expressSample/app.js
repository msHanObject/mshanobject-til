var express = require('express');
var bodyParser = require('body-parser')
var app = express();

app.locals.pretty = true;

app.set('view engine', 'jade');

app.set('views', './views');

app.use(express.static('public'));

app.use(bodyParser.urlencoded({ extended: false}));

app.get('/form', (req,res)=>{
	res.render('form');

});

app.get('/form_receiver', (req,res)=>{
	var title = req.query.title;
	var description = req.query.description;
	res.send(title+','+description);

});

app.post('/form_receiver', (req,res)=>{
	var title = req.body.title;
	var description = req.body.description;
	res.send(title+','+description);
});

app.get('/topic/:id', (req, res)=>{
	//res.send(req.query.id+','+req.query.name);
	var topics = [
		'Javascript is...',
		'Nodejs is...',
		'Express is...'
	];
	var output = `
		<a href="/topic/0">Javascript</a><br/>
		<a href="/topic/1">Nodejs</a><br/>
		<a href="/topic/2">Express</a><br/><br/>
		${topics[req.params.id]}
		//${topics[req.query.id]}
	`
	res.send(output);
	//res.send(topics[req.query.id]);
});

app.get('/topic/:id/:mode', (req,res)=>{
	res.send(req.params.id+','+req.params.mode);
});

app.get('/template', (req, res)=>{
	res.render('temp', {time:Date(), title:'Jade'});
});

app.get('/', function(req, res){
	res.send('Hello home page');
});

app.get('/dynamic', function(req, res){
	var lis = '';
	for(var i=0; i<5; i++){
		lis = lis + '<li>coding</li>';
	}
	var time = Date();
	var output = `
	<!doctype html>
	<html>
		<head>
			<meta charset="utf-8">
			<title>Hello Dynamic</title>
		</head>
		<body>
			Hello, Dynamic!
			<ul>
				${lis}
			</ul>
			${time}
		</body>
	</html>`;
	res.send(output);
});

var mysql      = require('mysql');

var connection = mysql.createConnection({
    host     : 'lawdy.cryctwa9qcbx.ap-northeast-2.rds.amazonaws.com',
    user     : 'minsu',
    password : 'minsu123',
    database : 'lawdy'
});

connection.connect(function(err) {
	if(err) {
		console.log("error: " + err);
	}
	console.log("Connected to DB!");
});

connection.query('SELECT * from contract', function(err, rows, fields) {
    if (!err)
        console.log('The solution is: ', rows);
    else
        console.log('Error while performing Query.');
});

app.get('/route', function(req, res){
	res.send('Hello Router, <img src="/alex-knight.jpg">')
});

app.get('/login', function(req, res){
	res.send('<h1>Login please</h1>');
});

app.get('/contract', function(req, res) {
	//res.sendFile(__dirname+'/public/contract.html');
	connection.query('select * from contract', function(err, rows, fields){
		res.send(rows);
	});
});

app.post('/contract', function(req, res){
	//var id = req.body.id;
	//var name = req.body.name;
	//res.send(id+', '+name);
	console.log(JSON.stringify(req.body));
	console.log(req.params);
	console.log(res.query);		
	var query = 'INSERT INTO contract (id,name) VALUES ('+req.query.id+',"'+req.query.name+'")';
	console.log(query);
	connection.query(query, function(err, rows, fields) {
		if (!err)
		 console.log('The solution is: ', rows);
	 else
	     console.log('Error while performing Query.');
	});
});

app.on('close', function(){
	connection.end();
});

app.listen(4000, function(){
	console.log('Connected 4000 port!');
});
