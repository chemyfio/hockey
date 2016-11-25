/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.core;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public abstract class GameObject {
//    public static final int TYPE_CIRCLE = 0; //menandakan tipe objek circle
//    public static final int TYPE_BOX1 = 1; //menandakan tipe objek box
//    public static final int TYPE_BOX2 = 2;
    
    public enum ObjectType{
        TYPE_CIRCLE, PLAYER, WALL_UP, WALL_DOWN, WALL_LEFT, WALL_RIGHT;
    }
    protected float x,y;
    protected int w, h;
    protected ObjectType type;

    public GameObject(float x, float y, int w, int h, ObjectType type) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.type = type;
    }
    
    public abstract void tick(List<GameObject> objects);
    public abstract void render(Graphics2D g2d);
    public Rectangle getBound(){
        return new Rectangle((int)x, (int)y, w, h);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }
    
    
}
