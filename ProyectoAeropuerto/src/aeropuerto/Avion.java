/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author dvd
 */
public class Avion extends Thread implements Runnable {

 
    public static enum EstatAvio {
        HIDE, STOP, RUN, TAKINGOFF, LANDING, FLYING
    };
      
    public static enum Orientation {
         
        NORTH(Direction.BACKWARD),
        SOUDTH(Direction.FORWARD),
        WEST(Direction.BACKWARD),
        EAST(Direction.FORWARD);
        private Direction direction;
 
        private Orientation(Direction direction) {
            this.direction = direction;
        }
 
        public static Direction getDirection(Orientation orientation) {
            if ((orientation == Orientation.SOUDTH) || (orientation == Orientation.EAST)) {
                return Direction.FORWARD; // =================================>>
            }
            return Direction.BACKWARD;
        }
 
        public static Orientation getOrientation(Avion avio) {
            return getOrientation(avio.getWay(), avio.getDirection());
        }
 
        public static Orientation getOrientation(Calle way, Direction direction) {
            if (way instanceof VCalle) {
                if (direction == Direction.FORWARD) {
                    return Orientation.SOUDTH; //=============================>>
                } else {
                    return Orientation.NORTH; //==============================>>
                }
            }
 
            if (way instanceof HCalle) {
                if (direction == Direction.FORWARD) {
                    return Orientation.EAST; //===============================>>
                } else {
                    return Orientation.WEST; //===============================>>
                }
            }
 
            return Orientation.WEST;
        }
 
        public static int getDegrees(Orientation orientation) {
            switch (orientation) {
                case NORTH:
                    return 0;
                case EAST:
                    return 90;
                case SOUDTH:
                    return 180;
                case WEST:
                    return 270;
            }
 
            return 0;
        }
    }
 
    public static enum Direction {
 
        FORWARD(1), BACKWARD(-1);
        private int increment;
 
        private Direction(int increment) {
            this.increment = increment;
        }
 
        public int getIncrement() {
            return this.increment;
        }
 
        public Orientation getOrientation(Calle way) {
            if (way instanceof VCalle) {
                if (this == Direction.FORWARD) {
                    return Orientation.SOUDTH; //=============================>>
                } else {
                    return Orientation.NORTH; //==============================>>
                }
            }
 
            if (way instanceof HCalle) {
                if (this == Direction.FORWARD) {
                    return Orientation.EAST; //===============================>>
                } else {
                    return Orientation.WEST; //===============================>>
                }
            }
 
            return Orientation.WEST;
        }
 
        @Override
        public String toString() {
            switch (this) {
                case FORWARD:
                    return "FORWARD";
                case BACKWARD:
                    return "BACKWARD";
            }
            return "Undefined";
        }
    }
 
    public Direction getDirection() {
 
        // TODO Auto-generated method stub
        return null;
    }
 
   /* 
    private int maxSpeedInCmSecond;
    private int maxAcceleration; // In units compatibles with speed
    private int maxDeceleration;    
    private volatile int acceleration;
    private boolean turn;
    */
    private int cmLong;
    private int cmWidth;
    //posicion avion probisional
    //private int posx, posy;
    private volatile int cmPosition;             // Car position relative to actual way   
    private volatile int speedInCmSecond;
    private volatile long lastUpdateTime;
    private int speed;
    private double course;
    private float factorX, factorY;
    private Image imgCar;
    private volatile Calle way;
    private Color color;
    private String idCar;
    private Direction direction;
    private int movX = 0, movY = 0;
    private String finger;
    private boolean entrando = true;
    private boolean seguir = true;
    private String antiguofinger = "";
    private Controlador c;
     
    public Avion(String idAvio, Calle way, String finger, Controlador c) {
        this.idCar = idAvio;
        this.cmLong = 700;
        this.cmWidth = 800;
        this.speed = 400;
        this.color=Color.CYAN;
        this.factorX = this.factorY = -1;
        this.course = -1;
        this.finger = finger;
        this.c = c;
        try{
            this.imgCar = new ImageIcon(getClass().getResource("avion_2.png")).getImage();
        }catch(Exception e){
            System.out.println("Error: imagen no encontrada");
        }
        this.cmPosition = 0;
        this.speedInCmSecond = 0;
 
        this.setWay(way);
        this.setDirection(Direction.FORWARD);
        System.out.println(idCar+" Creado en la calle "+way.getId()+" y va hacia el finger "+finger); 
    }
     
    public Calle getWay() {
        // TODO Auto-generated method stub
        return this.way;
    }
    public String getFinger(){
        return this.antiguofinger;
    }
    public int getCmPosition() {
            return this.cmPosition;
    }
 
    public int getLongInCm() {
        // TODO Auto-generated method stub
        return this.cmLong;
    }
 
    public int getSpeedInCmSecond() {
        return speedInCmSecond;
    }
 
    public void setSpeedInCmSecond(int speedInCmSecond) {
        this.speedInCmSecond = speedInCmSecond;
    }
 
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
 
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
 
    public int getSpeed() {
        return speed;
    }
 
    public void setSpeed(int speed) {
        this.speed = speed;
    }
 
    public double getCourse() {
        return course;
    }
 
    public void setCourse(double course) {
        this.course = course;
    }
 
    public float getFactorX() {
        return factorX;
    }
 
    public void setFactorX(float factorX) {
        this.factorX = factorX;
    }
 
    public float getFactorY() {
        return factorY;
    }
 
    public void setFactorY(float factorY) {
        this.factorY = factorY;
    }
 
    public Image getImgCar() {
        return imgCar;
    }
 
    public void setImgCar(Image imgCar) {
        this.imgCar = imgCar;
    }
 
    public Color getColor() {
        return color;
    }
 
    public void setColor(Color color) {
        this.color = color;
    }
 
    public String getIdCar() {
        return idCar;
    }
 
    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }
 
    public void setCmPosition(int cmPosition) {
        this.cmPosition = cmPosition;
    }
 
    public void setWay(Calle way) {
        this.way = way;
    }
 
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
 
    public int getWidthInCm() {
        // TODO Auto-generated method stub
        return this.cmWidth;
    }
 
    public void setPositionInCm(int cmPos) {
        this.cmPosition = cmPos;
         
    }
    public void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY) {
        int iniX, iniY, finX, finY;
        if(this.way instanceof HCalle){
            if(this.way.getId() == "H2"){
                this.setImgCar(new ImageIcon(getClass().getResource("avion_3.png")).getImage());
            }
            else{
                this.setImgCar(new ImageIcon(getClass().getResource("avion_2.png")).getImage());
            }
            if(this.cmPosition >= (this.way.getCmLong()/2) && this.way.getId().equals("H3")){
                this.movY-=10;
            }else{
                movY = 0;
            }
            movX = cmPosition;
            
        }
        else{
            if(!(entrando) && this.way.getId() != "V1"){
                this.setImgCar(new ImageIcon(getClass().getResource("avion_4.png")).getImage());
            }
            else{
                this.setImgCar(new ImageIcon(getClass().getResource("avion_1.png")).getImage());
            }
            movX = 0;
            movY = cmPosition;
            
        }
        iniX = (int)(((this.way.cmIniX+movX) / factorX) + offsetX);
        iniY = (int)(((this.way.cmIniY+movY) / factorY) + offsetY);
        finX = (int)((cmWidth/ factorX));
        finY = (int)((cmLong / factorY));                
        // Paint avion
        g.drawImage(this.imgCar,iniX,iniY,finX,finY,null);

    }
    public boolean Girar(){
        Calle calle = this.way.Pedircalle(this.cmPosition); 
        if(calle != null && calle.getId().equals(finger)){
            return true;
        }
        if(!(entrando) && this.way.getId()=="H3"){
            if(this.way.getCmLong() == cmPosition){
                seguir = false;
            }
            return false;
        }
        else if(cmPosition >= 0 && cmPosition < this.way.getCmLong()){
            return false;
        }else{
            return true;
        }
    }
    public void run(){
        while(seguir){
            try {
                Thread.sleep(5);
                if(Girar()){
                    if(cmPosition < 0){cmPosition = 0;}
                    Calle nueva;
                    int posx = this.way.cmIniX;
                    nueva = this.way.Pedircalle(this.cmPosition);
                    if(nueva!=null){
                        if(nueva.getId().equals("H1")){
                            c.comprobarcalle(nueva);
                        }else if(nueva.getId().equals("V2") && entrando){
                            c.despoater();
                        }
                        this.setWay(nueva);
                        if(this.way.getId().equals("H2") || this.way.getId().equals("V1")){
                            if(!(entrando) && this.way.getId() == "H2" || this.way.getId().equals("V1")){
                                cmPosition = posx;
                            }
                            else{
                                cmPosition = this.way.getCmLong()-4;
                            }
                        }
                        else{
                            cmPosition = 0;
                        }
                    }else{
                        c.setFingerOcu(finger);
                        Thread.sleep(5000);
                        this.entrando = false;
                        antiguofinger = finger;
                        finger = "";
                        cmPosition = this.way.getCmLong()-4;  
                    } 
                }
                else if(this.way.getId().equals("H1") || this.way.getId().equals("H3")){
                    if(c.avanzar(idCar, cmPosition, way)){
                        cmPosition += 10;
                    }else{
                        Thread.sleep(100);
                    }
     
                }
                else if(this.way.getId().equals("V1")){
                    if(c.avanzar(idCar, cmPosition, way)){
                        cmPosition += 10;
                    }else{
                        Thread.sleep(100);
                    }
                }
                else if(this.way.getId().equals("H2")){
                        cmPosition -= 4;
                }
                else if(this.way.getId().equals("V2")){
                        cmPosition += 10;
                }
                else{
                    if(entrando){
                        cmPosition += 2;
                    }else{
                        cmPosition -= 2;
                    }
                    
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Throwable ex) {
                Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.BorrarAvion(this);
    }
}

