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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Box extends GameObject{
    public float velX = 0;
    public float velY = 0;
    public Color c;
    
    public Box(float x, float y, int w, int h, ObjectType type, Color c) {
        super(x, y, w, h, type);
        this.c = c;
    }



    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillRect((int)x, (int)y, w, h);
    }

    @Override
    public void tick(List<GameObject> objects) {
        x+=velX;
        y+=velY;
    }

    public void keepOnScreen(){
        if(x > GameA.WIDTH - w || x<0){
            velX*=-1;
        }
        
        if(y > GameA.HEIGHT - h || y<0){
            velY*=-1;
        }
    }
    
    
}
