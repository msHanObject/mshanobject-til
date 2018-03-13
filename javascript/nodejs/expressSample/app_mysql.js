var express = require('express');

var bodyParser = require('body-parser');

var fs = require('fs');

var multer = require('multer');

var storage = multer.diskStorage({
	destination: (req, file, cb)=>{
		cb(null, 'uploads')
	},
	filename: (req, file, cb)=>{
		cb(null, file.originalname)
	}
});

var upload = multer({ storage: storage });

var mysql = require('mysql');

var conn = mysql.createConnection({
	host     : 'lawdy.cryctwa9qcbx.ap-northeast-2.rds.amazonaws.com',
    user     : 'minsu',
    password : 'minsu123',
    database : 'lawdy'
});

conn.connect();

var app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.locals.pretty = true;

app.use('/user', express.static('uploads'));

app.set('views', './views_mysql');

app.set('view engine', 'jade');

app.get('/upload', (req,res)=>{
	res.render('upload');
});

app.post('/upload', upload.single('userfile'), (req,res)=>{
	console.log(req.file);
	res.send('Uploaded : ' + req.file.filename);
});

app.get('/topic/add', (req,res) => {
	var sql = 'select id, title from topic';
	conn.query(sql, (err, topics, fields)=>{
		if (err) {
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		res.render('add', {topics:topics});
	});
});

app.post('/topic/add', (req,res)=>{
	var title = req.body.title;
	var description = req.body.description;
	var author = req.body.author;
	var sql = 'insert into topic (title, description, author) values(?, ?, ?)';
	conn.query(sql, [title, description, author], (err, result, fields)=>{
		if (err) {
			console.log(err);
			res.status(500).send('Internal Server Error');
		} else {
			res.redirect('/topic/' + result.insertId);
			//res.send(rows);
		}
	});
	//Following code for handling file
	/*fs.writeFile('data/'+title, description, (err)=>{
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		res.redirect('/topic/'+title);
	});*/
	
});

app.get(['/topic/:id/edit'], (req,res)=>{
	var sql = 'select id, title from topic';	
	conn.query(sql, (err, topics, fields)=>{//original argument was (err, rows, fields)
		//res.send(rows);//It just show array of rows by json object
		var id = req.params.id;
		if (id) {
			var sql = 'select * from topic where id=?';
			conn.query(sql, [id], (err, topic, fields)=>{
				if (err) {
					console.log(err);
					res.status(500).send('Internal Server Error');
				} else {
					res.render('edit', {topics:topics, topic:topic[0]});
				}
			});
		} else {
			console.log('There is no id.');
			res.status(500).send('Internal Server Error');
		}
	});
});

app.post(['/topic/:id/edit'], (req,res)=>{
	var title = req.body.title;
	var description = req.body.description;
	var author = req.body.author;
	var id = req.params.id;
	var sql = 'update topic set title=?, description=?, author=? where id=?';	
	conn.query(sql, [title, description, author, id], (err, result, fields)=>{
		if (err) {
			console.log(err);
			res.status(500).send('Internal Server Error');
		} else {
			res.redirect('/topic/' + id);
		}
	});
});

app.get('/topic/:id/delete', (req,res)=>{
	var id = req.params.id;
	if (!id){
		console.log(err);
		console.log('There is no id for topic');
		res.status(500).send('Internal Server Error, No id');
	} else {
		var sql = 'select id, title from topic';
		conn.query(sql, (err, topics)=>{
			var sql = 'select * from topic where id=?';
			conn.query(sql, [id], (err, topic)=>{
				if (err) {
					console.log(err);
					res.status(500).send('Internal Server Error');
				} else {
					if (topic.length === 0) {
						console.log('There is no record.');
						res.status(500).sensd('Internal Server Error');
					} else {
						res.render('delete', {topics:topics, topic:topic[0]});
					}
				}
			});
		});
	}
});

app.post('/topic/:id/delete', (req, res)=>{
	var id = req.params.id;
	var sql = 'delete from topic where id=?';
	conn.query(sql, [id], (err, result)=>{
		res.redirect('/topic/');
	});
});

app.get(['/topic', '/topic/:id'], (req,res)=>{
	var sql = 'select id, title from topic';	
	conn.query(sql, (err, topics, fields)=>{//original argument was (err, rows, fields)
		//res.send(rows);//It just show array of rows by json object
		var id = req.params.id;
		if (id) {
			var sql = 'select * from topic where id=?';
			conn.query(sql, [id], (err, topic, fields)=>{
				if (err) {
					console.log(err);
					res.status(500).send('Internal Server Error');
				} else {
					res.render('view', {topics:topics, topic:topic[0]});
				}
			});
		} else {
			res.render('view', {topics:topics});//This code inject values({}) in 'view' that .jade file
		}
	});
});

app.listen(3001, () =>{
	console.log('Connected, 3001 port!');
});
