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
var app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.locals.pretty = true;

app.use('/user', express.static('uploads'));

app.set('views', './views_file');

app.set('view engine', 'jade');

app.get('/upload', (req,res)=>{
	res.render('upload');
});

app.post('/upload', upload.single('userfile'), (req,res)=>{
	console.log(req.file);
	res.send('Uploaded : ' + req.file.filename);
});

app.get('/topic/new', (req,res) => {
	fs.readdir('data', (err,files)=>{
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		res.render('new', {topics:files});
	});
});

app.get(['/topic', '/topic/:id'], (req,res)=>{
	fs.readdir('data', (err,files)=>{
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		var id = req.params.id;
		if (id) {
			//value of id is exist
			fs.readFile('data/'+id, 'utf8', (err, data)=>{
				if (err) {
					console.log(err);
					res.status(500).send('Internal Server Error');
				}
				res.render('view', {topics:files, title:id, description:data});
			});
		} else {
			//value of id wasn't exist
			res.render('view', {topics:files, title:'Welcome', description:'Hello, JavaScript for server.'});
		}
	});
});

app.post('/topic', (req,res)=>{
	var title = req.body.title;
	var description = req.body.description;
	fs.writeFile('data/'+title, description, (err)=>{
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		res.redirect('/topic/'+title);
	});
	
});

app.listen(3000, () =>{
	console.log('Connected, 3000 port!');
});
