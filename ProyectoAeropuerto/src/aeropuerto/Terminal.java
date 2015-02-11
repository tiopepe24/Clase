/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author dvd
 */
public class Terminal {
    private String id = "";
    private int posx = 0;
    private int posy = 0;
    private final int longi = 9000;
    private int altura = 1200;
    
    public Terminal(String id,int posx, int posy){
        //this.a1 = av;
        this.id = id;
        this.posx = posx;
        this.posy = posy;
    }
    public void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY) {
        int iniX, iniY, finX, finY;
         
        iniX = (int)((this.posx / factorX) + offsetX);
        iniY = (int)((this.posy / factorY) + offsetY);
        finX = (int)((longi / factorX));
        finY = (int)((altura / factorY));                
                 
        // Paint crossroad
        g.setColor(Color.YELLOW);
        g.fillRect(iniX, iniY, finX, finY);
        g.setColor(Color.BLACK);
        g.drawRect(iniX, iniY, finX, finY);
 
    }
}
