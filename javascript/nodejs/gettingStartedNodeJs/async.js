var fs = require('fs');

console.log(1);
fs.readFile('data.txt', {encoding:'utf8'}, function(err, data){
	console.log(data);
})
console.log(2);
