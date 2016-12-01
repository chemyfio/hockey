/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.net;

import id.game.core.GameObject;
import id.game.main.GameA;
import id.game.objects.Box;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.awt.Color;

/**
 *
 * @author Lenovo
 */
public class SIOClient {
    public static final String host = "localhost:2000";
    Socket socket;
    GameA game;

    public SIOClient(GameA game) {
        try{
            socket = IO.socket("http://"+host);
            this.game = game;
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("Client Connected");
                }
            });
            
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("Client Disconnected");
                }
            });
            
            socket.on("newPlayer", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("player - "+os[0].toString());
                    switch(Integer.parseInt(os[0].toString())){
                        case 0: game.currentPlayer = GameObject.ObjectType.PLAYER_1;
                            break;
                        case 1: game.currentPlayer = GameObject.ObjectType.PLAYER_2;
                            break;
                        case 2: game.currentPlayer = GameObject.ObjectType.PLAYER_3;
                            break;
                        case 3: game.currentPlayer = GameObject.ObjectType.PLAYER_4;
                            break;
                            
                    }
                }
            });
            
            socket.on("movingBall", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
//                    System.out.println(Float.parseFloat(os[0].toString()));
//                    System.out.println(os[1].toString());
                    game.c1.setX(Float.parseFloat(os[0].toString()));
                    game.c1.setY(Float.parseFloat(os[1].toString()));
                    game.c1.setVelX(Float.parseFloat(os[2].toString()));
                    game.c1.setVelY(Float.parseFloat(os[3].toString()));
                }
            });
            
            socket.on("movingPaddle", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
//                    System.out.println(Float.parseFloat(os[0].toString()));
                    Box player;
                    if(game.currentPlayer == GameObject.ObjectType.PLAYER_1){
                        player = game.b1;
                    }else if(game.currentPlayer == GameObject.ObjectType.PLAYER_2){
                        player = game.b2;
                    }else if(game.currentPlayer == GameObject.ObjectType.PLAYER_3){
                        player = game.b3;
                    }else{
                        player = game.b4;
                    }
                    player.setX(Float.parseFloat(os[0].toString()));
                    player.setY(Float.parseFloat(os[1].toString()));
                    player.setVelX(Float.parseFloat(os[2].toString()));
                    player.setVelY(Float.parseFloat(os[3].toString()));

                }
            });
            
            socket.connect();
        }catch(Exception e){
            
        }
        
    }
    
    public void send(String param,float x, float y, float velX, float velY){
        socket.emit(param, x, y, velX, velY);
    }
}
