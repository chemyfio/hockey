var express = require('express');
var app = express();
var serv = require('http').Server(app);

serv.listen(2000);
console.log("Server started.");

var io = require('socket.io')(serv,{});
var socketList = [];
var playerList = [];
io.sockets.on('connection', function(socket){

	socketList.push(socket);

	socket.emit('newPlayer', socketList.indexOf(socket));
	socket.on('disconnect',function(){
		socketList.splice(socketList.indexOf(socket), 1);
		// delete PLAYER_LIST[socket.id];
	});

	socket.on('movingPaddle', function(x, y, velX, velY, indexPlayer){
		io.emit('movingPaddle',x, y, velX, velY, indexPlayer);
	});
	

	socket.on('movingBall', function(x, y, velX, velY){
		io.emit('movingBall', x, y, velX, velY);
	});
	
});
