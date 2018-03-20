var express = require('express');
var session = require('express-session');
var FileStore = require('session-file-store')(session);
var bodyParser = require('body-parser');
//var md5 = require('md5');
var sha256 = require('sha256');
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
  delete req.session.displayName;
  req.session.save(()=> {
	  res.redirect('/welcome');
  });
});

app.get('/welcome', function(req, res){
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

//var salt = '@#!$@asdfij!#$12u5';
var users = [
	{
		username:'egoing',
		password:'7a85f54baffcffbee3441eee5fb358217b4c85987abde26a675a0b73bfce4819',
		salt:'!@#SDAF312',
		displayName:'Egoing'
	},
	{
		username:'kgoing',
		password:'f12c4e246e58a8c91571fc939527e16ca4a4498719f41c9e79c6868ba72aa33a',
		salt:'!@#SDF#@48842',
		displayName:'Kgo'
	}
];

app.post('/auth/register', (req,res) => {
	var user = {
		username:req.body.username,
		password:req.body.password,
		displayName:req.body.displayName
	};
	users.push(user);
	req.session.displayName = req.body.displayName;
	return req.session.save(function() {
		res.redirect('/welcome');
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

app.post('/auth/login', function(req, res){
  var uname = req.body.username;
  //var pwd = md5(req.body.password + salt);
  var pwd = req.body.password;
  for(var i=0; i<users.length; i++){
	  var user = users[i];
	  if(uname === user.username && sha256(pwd + user.salt) === user.password){
		  req.session.displayName = user.displayName;
		  return req.session.save(function(){
			  res.redirect('/welcome');
		  });
	  }
  }
  res.send('Who are you? <a href="/auth/login">login</a>');
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

