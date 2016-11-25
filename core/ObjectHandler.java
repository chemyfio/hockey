/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Lenovo
 */
public class ObjectHandler {
    public List<GameObject> objects;
    public Set<Integer> keys; //di dalam sini unik semua datanya
    
    public ObjectHandler() {
        this.objects = new ArrayList<>();
        this.keys = new HashSet<>();
    }
    
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    
    public void addKey(int key){
        this.keys.add(key);
    }
    
    public void remObject(GameObject object){
        this.objects.remove(object);
    }
    
    public void removeKey(int key){
        this.keys.remove(key);
    }
    
    public void tick(){
        for(GameObject object: objects){
            object.tick(objects);
        }
    }
    
    public void render(Graphics2D g2d){
        for(GameObject object: objects){
            object.render(g2d);
        }
    }
}
