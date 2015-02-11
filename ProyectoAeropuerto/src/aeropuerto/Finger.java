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
public class Finger {
    //private Avion a1 = new Avion();
    private String id = "";
    private int posx = 0;
    private int posy = 0;
    private final int longi = 500;
    private int altura = 700;
    
    public enum Estado{
        OCUPADO, LIBRE, RESERVADO
    };
    private Estado estado;
    
    public Finger( String id,int posx, int posy,Estado estado){
        //this.a1 = av;
        this.id = id;
        this.posx = posx;
        this.posy = posy;
        this.estado = estado;
    }
    public String getId(){
        return id;
    }
    //mostramos el estado del finger
    public boolean getEstado(){
        if(estado == Estado.LIBRE){
            return true;
        }
        else{
            return false;
        }
    }
    public void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY) {
        int iniX, iniY, finX, finY;
         
        iniX = (int)((this.posx / factorX) + offsetX);
        iniY = (int)((this.posy / factorY) + offsetY);
        finX = (int)((longi / factorX));
        finY = (int)((altura / factorY));                
                 
        // Paint crossroad
        if(estado == Estado.OCUPADO){
            g.setColor(Color.red);
        }
        else if(estado == Estado.LIBRE){
            g.setColor(Color.green);
        }
        else{
            g.setColor(Color.ORANGE);
        }
        g.fillRect(iniX, iniY, finX, finY);
        g.setColor(Color.BLACK);
        g.drawRect(iniX, iniY, finX, finY);
 
    }
    public void setOcupat(){
        this.estado = Estado.OCUPADO;
    }
    public void setLibre(){
        this.estado= Estado.LIBRE;
    }
    public void setReservado(){
        this.estado = Estado.RESERVADO;
    }
    /**
     * @return the a1
     */
    /*public Avion getA1() {
        return a1;
    }

    /**
     * @param a1 the a1 to set
     */
    /*public void setA1(Avion a1) {
        this.a1 = a1;
    }*/

    /**
     * @return the posx
     */
    public int getPosx() {
        return posx;
    }

    /**
     * @param posx the posx to set
     */
    public void setPosx(int posx) {
        this.posx = posx;
    }

    /**
     * @return the posy
     */
    public int getPosy() {
        return posy;
    }

    /**
     * @param posy the posy to set
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }
}
