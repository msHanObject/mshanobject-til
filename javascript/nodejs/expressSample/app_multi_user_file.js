var express = require('express');
var session = require('express-session');
var FileStore = require('session-file-store')(session);
var bodyParser = require('body-parser');
var bkfd2Password = require('pbkdf2-password');
var hasher = bkfd2Password();
var app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.use(session({
  secret: '1234DSFs@adf1234!@#$asd',
  resave: false,
  saveUninitialized: true,
  store:new FileStore()
}));

app.get('/count', function(req, res){
  if(req.session.count) {
    req.session.count++;
  } else {
    req.session.count = 1;
  }
  res.send('count : '+req.session.count);
});

app.get('/auth/logout', function(req, res){
/*
  req.session.destroy(function() {
	  console.log(users);
	  res.redirect('/welcome');
  });
*/
  delete req.session.displayName;
  req.session.save(()=> {
	  console.log('logout1: '+ users);
	  res.redirect('/welcome');
  });
});

app.get('/welcome', function(req, res){
	console.log('welcome: ' + users);
  if(req.session.displayName) {
    res.send(`
      <h1>Hello, ${req.session.displayName}</h1>
      <a href="/auth/logout">logout</a>
    `);
  } else {
    res.send(`
      <h1>Welcome</h1>
	  <ul>
		<li><a href="/auth/login">Login</a></li>
		<li><a href="/auth/register">Register</a></li>
	  </ul>
    `);
  }
});

app.post('/auth/login', function(req, res){
  var uname = req.body.username;
  var pwd = req.body.password;
  for(var i=0; i<users.length; i++){
	  var user = users[i];
	  console.log('login, foundUser:' +user);
	  if(uname === user.username) {
		  return hasher({password:pwd, salt:user.salt}, function(err, pass, salt, hash){
			  if(hash === user.password) {
				  req.session.displayName = user.displayName;
				  req.session.save(function() {
					  console.log('login success');
					  res.redirect('/welcome');
				  })
			  } else {
				  console.log('password not match');
				  res.send('Who are you? <a href="/auth/login">login</a>');
			  }
		  });
		}
  }
  console.log('There is no such a user');
  res.send('Who are you? <a href="/auth/login">login</a>');
});

var users = [
	{
		username:'egoing',
		password:'KAxzthxiOEFqUpcxcD3UKrVdImjEdX4KX8qcJG0DQI5hl/ODMIxqfMNGy3WyhRVUBTkWkpNYmK12ndhhogjdzwoe34fUEUzctkhUD7Djndrhswvt8FQjPWSlvUCnqAOydSGw6d7lBIrbMlgpPU39F4UH3pHMYlsBX9W98j3Rc6A=',
		salt:'I/i9llKkMl0hcpj3jEDEPfcWW5orz8Ty16ZlNxmvEqaepr4of4XZpfVTCT87m24gbctNqccq+1LWP6LomH4Tyg==',
		displayName:'Egoing'
	}
];

app.post('/auth/register', (req,res) => {
	hasher({password:req.body.password}, function(err, pass, salt, hash){
		if (err) {
			console.log(err);
		}
		var user = {
			username:req.body.username,
			password:hash,
			salt:salt,
			displayName:req.body.displayName
		};
		users.push(user);
		req.session.displayName = req.body.displayName;
		return req.session.save(function() {
			console.log('register: '+ users);
			res.redirect('/welcome');
		});
	});
});

app.get('/auth/register', (req,res) => {
	var output = `
	<h1>Register</h1>
	<form action="/auth/register" method="post">
		<p>
			<input type="text" name="username" placeholder="username">
		</p>
		<p>
			<input type="password" name="password" placeholder="password">
		</p>
		<p>
			<input type="text" name="displayName" placeholder="displayName">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	`;
	res.send(output);
});

app.get('/auth/login', function(req, res){
  var output = `
  <h1>Login</h1>
  <form action="/auth/login" method="post">
    <p>
      <input type="text" name="username" placeholder="username">
    </p>
    <p>
      <input type="password" name="password" placeholder="password">
    </p>
    <p>
      <input type="submit">
    </p>
  </form>
  `;
  res.send(output);
});

app.listen(3003, function(){
  console.log('Connected 3003 port!!!');
});

