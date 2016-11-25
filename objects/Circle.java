/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.objects;

import id.game.core.Constants;
import id.game.core.GameObject;
import id.game.main.GameA;
import static id.game.main.GameA.HEIGHT;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Circle extends GameObject{

    public float velX = 0f;
    public float velY = 0f;

    public Circle(float x, float y, int w, int h) {
        super(x, y, w, h, GameObject.ObjectType.TYPE_CIRCLE);
    }

    @Override
    public void tick(List<GameObject> objects) {
        x+=velX;
        y+=velY;
        GameA.sio.send("movingBall", x, y);
//        if(velY > 0){
//            velY += 9.8/60;
//        }else{
//            velY +=9.8/40;
//        }
        keepOnScreen();
//        keepRunning();
//        playerComputer(objects);
        checkCollision(objects);
        checkOutOfScreen(objects);
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    
    public void keepOnScreen(){
        if(x > GameA.WIDTH - w || x<0){
            velX*=-1;
        }
        
        if(y > GameA.HEIGHT - h || y<0){
            velY*=-1;
        }
    }
    
    public void keepRunning(){
        if(x > GameA.WIDTH- (w/2)){
            x=-(w/2);
        }
        
        if(y > GameA.HEIGHT-(h/2)){
            y=-(h/2);
        }        
    }
    
    public void playerComputer(List<GameObject> objects){
        
        for(GameObject object: objects){
            if(object.getType() == GameObject.ObjectType.PLAYER){
                if(x >= object.getX()-300){
                    object.setY(object.getY()+velY);
                }
            }
        }
    }
    
    public void checkOutOfScreen(List<GameObject> objects){
        for(GameObject object: objects){
            if(object.getType() == GameObject.ObjectType.TYPE_CIRCLE){
                if((x <= 0 || x >= GameA.WIDTH-object.getW()) && (y < (HEIGHT/2)+75 && y > (HEIGHT/2)-99) ){
                    velX = 0;
                    velY = 0;
                    x = GameA.WIDTH/2;
                    y = GameA.HEIGHT/2;
                }
            }
        }
    }
    public void checkCollision(List<GameObject> objects){ //apakah circle bertumbukan dengan object lain?
        for(GameObject object: objects){
            if(object.getType() == GameObject.ObjectType.PLAYER ){
                if(object.getBound().intersects(getBoundsBottom())){
                    if(velY == 0)
                    {
                        velY = 5;
                    }
                    else
                    {
                        velY*=-1;
                    }
                }
                
                if(object.getBound().intersects(getBoundsTop())){
                    if(velY == 0)
                    {
                        velY = 5;
                    }
                    else
                    {
                        velY*=-1;
                    }
                }
                
                if(object.getBound().intersects(getBoundsLeft())){
                    if(velX == 0)
                    {
                        velX = 5;
                    }
                    else
                    {
                        velX*=-1;
                    }
                }
                
                if(object.getBound().intersects(getBoundsRight())){
                    if(velX == 0)
                    {
                        velX = 5;
                    }
                    else
                    {
                        velX*=-1;
                    }
                }
            }else if(object.getType() == GameObject.ObjectType.PLAYER){
                if(object.getBound().intersects(getBound())){
                    
                    if( x +w >= object.getX()){
                        velX*=-1;
                    }

//                    if(y < object.getY() || y+h >= object.getY()){
//                        velY*=-1;
//                    }
                }
            } else if(object.getType() == GameObject.ObjectType.WALL_UP){
                if(object.getBound().intersects(getBound())){
                    if(y < object.getY()+25){
                        velY*=-1;
                    }
                }
            }else if(object.getType() == GameObject.ObjectType.WALL_DOWN){
                if(object.getBound().intersects(getBound())){
                    if(y < object.getY()){
                        velY*=-1;
                    }
                }
            }else if(object.getType() == GameObject.ObjectType.WALL_LEFT){
                if(object.getBound().intersects(getBound())){
                    if(x < object.getX()+25){
                        velX*=-1;
                    }
                }
            }else if(object.getType() == GameObject.ObjectType.WALL_RIGHT){
                if(object.getBound().intersects(getBound())){
                    if(x+w > object.getX()){
                        velX*=-1;
                    }
                }
            }
        }
    }
    
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Constants.FUI_ALIZARIN);
        g2d.fillOval((int)x, (int)y, w, h);
    }
    
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)(x+((w/4))), (int)(y+h/2), (int)(w/2), (int)h/2);
    }
    
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x+((w/4))), (int)y, (int)(w/2), (int)h/3);
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+((w/5)*4)), (int)(y+((int)(h/6))), (int)w/5, (int)(h-(h/2)));
    }
    
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)(y+((int)(h/6))), (int)w/5, (int)(h-(h/2)));
    }
}
