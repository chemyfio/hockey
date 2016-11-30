/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.net;

import id.game.main.GameA;
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
            
            socket.on("newPositions", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    
                }
            });
            
            socket.on("movingBall", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println(Float.parseFloat(os[0].toString()));
                    System.out.println(os[1].toString());
                    game.c1.setVelX(Float.parseFloat(os[0].toString()));
//                    System.out.println("X = "+game.c1.getX());
                    game.c1.setVelY(Float.parseFloat(os[1].toString()));
//                    System.out.println("Y = "+game.c1.getY());
                }
            });
            
            socket.on("movingPaddle", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println(Float.parseFloat(os[0].toString()));
                    System.out.println(os[1].toString());
                    game.b1.setVelX(Float.parseFloat(os[0].toString()));
//                    System.out.println("X = "+game.c1.getX());
                    game.b1.setVelY(Float.parseFloat(os[1].toString()));
//                    System.out.println("Y = "+game.c1.getY());
                }
            });
            
            socket.connect();
        }catch(Exception e){
            
        }
        
    }
    
    public void send(String param, float x, float y){
        socket.emit(param, x, y);
    }
}
