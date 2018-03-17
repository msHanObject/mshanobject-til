var express = require('express');

var session = require('express-session');

var bodyParser = require('body-parser');

var app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.use(session({
	secret: '0b$9A31@je$21G7@ct',// key value for hashing cookies
	resave: false,
	saveUninitialized: true
}))

app.get('/count', (req, res) => {
	if (req.session.count) {
		req.session.count++;
	} else {
		req.session.count = 1;
	}
	res.send('count : ' + req.session.count);
})

app.get('/auth/login', (req, res) => {
	var output =`
	<h1>Login</h1>
	<form action="/auth/login" method="post">
		<p>
			<input type="text" name="username" placeholde="username">	
		</p>
		<p>
			<input type="password" name="password" placehodler="passworld">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	`;
	res.send(output);
})

app.post('/auth/login', (req, res) => {
	var user = {
		username:'egoing',
		password:'111',
		displayName:'Egoing'
	};
	var uname = req.body.username;
	var pwd = req.body.password;
	if (uname === user.username && pwd === user.password) {
		req.session.displayName = user.displayName;	
		res.redirect('/welcome');
	} else {
		res.send('Who are you? <a href="/auth/login">login</a>');
	}
});

app.get('/welcome', (req,res) => {
	if(req.session.displayName) {
		res.send(`
			<h1>Hello, ${req.session.displayName}</h1>
			<a href="/auth/logout">logout</a>
		`);
	} else {
		res.send(`
		<h1>Welcome</h1>
		<a href="/auth/login">Login</a>
		`);
	}
});

app.get('/auth/logout', (req,res) => {
	delete req.session.displayName;
	res.redirect('/welcome');
});
app.listen(3003, () =>{
	console.log('Connected 3003 port!!!');
})
