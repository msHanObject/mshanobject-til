
var form = $('#form');

form.submit(function(event){
	console.log($(this).serializeArray());
	api($(this).serializeArray());
	event.preventDefault();
});

function api(data) {
	let formattedData = {};
	data.map((item) => {
		formattedData[item.name] = item.value;
	});

	console.log(data);
	console.log(formattedData);

	$.post("/contract", formattedData, function(res) {
		alert(res);
	});

	console.log("AJAX CALLED!")
}
