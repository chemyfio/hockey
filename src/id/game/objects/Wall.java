/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.objects;

import id.game.core.Constants;
import id.game.core.GameObject;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Wall extends GameObject{
    
    public Wall(float x, float y, int w, int h, ObjectType type) {
        super(x, y, w, h, type);
    }



    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Constants.FUI_POMEGRANATE);
        g2d.fillRect((int)x, (int)y, w, h);
    }

    @Override
    public void tick(List<GameObject> objects) {
        
    }
}
