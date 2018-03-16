var express = require('express');

var cookieParser = require('cookie-parser');

var app = express();

app.use(cookieParser('0b$arqs@je$01@ct$sdaf'));

var products = {
	1:{title:'The history of web 1'},
	2:{title:'The next web'}
}

app.get('/products', (req, res) => {
	var output = '';
	for (var index in products) {
		output += `
		<li>
			<a href="/cart/${index}">${products[index].title}</a>
		</li>`
	}
	res.send(`<h1>Products</h1><ul>${output}</ul><a href="/cart">Cart</a>`);
})

app.get('/cart/:id', (req, res) => {
	var id= req.params.id;

	if (req.cookies.cart) {
		var cart = req.signedCookies.cart;
	} else {
		var cart = {};
	}

	if (!cart[id]) {
		cart[id] = 0;
	}

	cart[id] += 1;	//cart[id] = parseInt(cart[id]) + 1;
	res.cookie('cart', cart, {signed:true});
	console.log(cart);
	res.redirect('/cart');
})

app.get('/cart', (req,res) => {
	var cart = req.signedCookies.cart;
	if (!cart) {
		res.send('Empty!');
	} else {
		var output = '';
		for (var index in cart) {
			output += `<li>${products[index].title} (${cart[index]})</li>`;
		}
	}
	res.send(`
	<h1>Cart</h1>
	<ul>${output}</ul>
	<a href="/products">Products List</a>
	`);
})

app.get('/count', (req, res) => {
	var count = req.signedCookies.count;
	if (!count) {
		var count = 0;
	} else {
		var count = parseInt(count);
	}
	count += 1;
	
	res.cookie('count', count, {signed:true});
	res.send('count : ' + count);
})

app.listen(3003, () =>{
	console.log('Connected 3003 port!!!');
})
