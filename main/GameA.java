/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.main;

import id.game.core.Constants;
import id.game.core.GameObject;
import id.game.core.KeyHandler;
import id.game.core.ObjectHandler;
import id.game.objects.Box;
import id.game.objects.Circle;
import id.game.objects.Wall;
import id.net.SIOClient;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class GameA extends Canvas implements Runnable{
    public static final int FPS = 60;
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH/4*3;
    private boolean running = false;
    private Thread thread;
    private ObjectHandler handler;
    private KeyHandler keyHandler;
    
    public static GameA game;
    public Circle c1;
    public static SIOClient sio;
    public void init(){
        //init()
        handler = new ObjectHandler();
        keyHandler = new KeyHandler(handler);
        sio = new SIOClient();
        
        c1 = new Circle(WIDTH/2, HEIGHT/2, 50, 50);
        
        Box b1 = new Box(50, 50, 90, 90, GameObject.ObjectType.PLAYER, Constants.FUI_ALIZARIN);
        Box b2 = new Box(WIDTH-100, 50, 90, 90, GameObject.ObjectType.PLAYER, Constants.FUI_AMETHYST);
        
        Box b3 = new Box(50, HEIGHT-200, 90, 90, GameObject.ObjectType.PLAYER, Constants.FUI_ASBESTOS);
        Box b4 = new Box(WIDTH-100, HEIGHT-200, 90, 90, GameObject.ObjectType.PLAYER, Constants.FUI_BELIZE_HOLE);
        
        Wall wUp = new Wall(0, 0, WIDTH, 25, GameObject.ObjectType.WALL_UP);
        Wall wDown = new Wall(0, HEIGHT-25, WIDTH, 25, GameObject.ObjectType.WALL_DOWN);
        Wall wLeftUp = new Wall(0, 25, 25, (HEIGHT/2)-100 , GameObject.ObjectType.WALL_LEFT);
        Wall wLeftDown = new Wall(0, (HEIGHT/2)+75, 25, (HEIGHT/2)-100, GameObject.ObjectType.WALL_LEFT);
        Wall wRightUp = new Wall(WIDTH-25, 25, 25, (HEIGHT/2)-100 , GameObject.ObjectType.WALL_RIGHT);
        Wall wRightDown = new Wall(WIDTH-25, (HEIGHT/2)+75, 25, (HEIGHT/2)-100, GameObject.ObjectType.WALL_RIGHT);
        
        handler.addObject(c1);
        handler.addObject(b1);
        handler.addObject(b2);
        handler.addObject(b3);
        handler.addObject(b4);
        handler.addObject(wUp);
        handler.addObject(wDown);
        handler.addObject(wLeftUp);
        handler.addObject(wLeftDown);
        handler.addObject(wRightUp);
        handler.addObject(wRightDown);

        addKeyListener(keyHandler);
    }
    
    public synchronized void start(){
        if(running){
            return;
        }
        
        thread = new Thread(this);
        running = true;
        thread.start();
    }
    
    @Override
    public void run() {
        init();
//        while(running){
//            tick();
//            render();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GameA.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        long lastTime = System.nanoTime();
        double amountOfTicks = FPS;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    public void tick(){ //apa yg bakal dilakukan untuk setiap tick; logika
        handler.tick();
        keyHandler.tick();
        //inputHandler.tick()
    }
    
    public void render(){ //apa yg mau ditampilin setiap loop
        //Double Buffer Render
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        
        //Default Render
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Constants.FUI_MIDNIGHT_BLUE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        
        game = new GameA();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.add(game);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
    
}
