$(document).ready(function() {
	'use strict';
	paper.install(window);
	paper.setup(document.getElementById('mainCanvas'));

	var tool = new Tool();

	tool.onMouseDown = function(event){
		var c = Shape.Circle(event.point, 20);
		c.fillColor ='green';
	}

	var c = Shape.Circle(200,200,80);
	c.fillColor ='blank';
	var text = new PointText(200,200);
	text.justification ='center';
	text.fillColor = 'white';
	text.fontSize = 20;
	text.content = 'hello world';
		
	paper.view.draw();

	//console.log('main.js loaded');
});
