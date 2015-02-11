/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

/**
 *
 * @author dvd
 */
import aeropuerto.Avion.Direction;
import aeropuerto.Avion.Orientation;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Cruce {
    private String idCrossRoad;
    private boolean northCarrer;
    private boolean southCarrer;
    private boolean eastCarrer;
    private boolean westCarrer;
    private int safetySpeedInCmHour;
     
    private int cmCenterX, cmCenterY;
    private int cmIniX, cmIniY, cmFinX, cmFinY;
    private int cmWidthX, cmWidthY;
     
    private Calle hCarrer;
    private Calle vCarrer;
    //private ArrayList<Turn> turns = new ArrayList<Turn>();
 
     
    public Cruce(Calle Carrer1, Calle Carrer2) {
        this.safetySpeedInCmHour = 600;
         
        this.setCarrers(Carrer1, Carrer2);
        this.setDimension();
        this.setPosition();
        this.setTurns();
 
        // Actualize crossferences
        this.hCarrer.addCrossRoad(this);
        this.vCarrer.addCrossRoad(this);
    }
     
    public boolean testOrientationAvailable(Orientation orientation) {
        switch (orientation) {
            case EAST:
                return (this.eastCarrer); // ====================================>>
            case WEST:
                return (this.westCarrer); // ====================================>>
            case NORTH:
                return (this.northCarrer); // ===================================>>
            case SOUDTH:
                return (this.southCarrer); // ===================================>>
        }
         
        return false;
    }
 
    public Calle getRandomCarrer(Calle actualCarrer) {
        if (actualCarrer instanceof VCalle)
            return this.hCarrer;
         
        return this.vCarrer;
    }
     
    public Direction getRandomDirection(Calle Carrer) {
        int randomDirection;
 
        Random rand = new Random();
        randomDirection = rand.nextInt(2);
 
 
        if (Carrer instanceof VCalle) {
            if ((this.southCarrer) && (this.northCarrer)) {
                if (randomDirection == 0) {
                    return Direction.FORWARD;
                } else {
                    return Direction.BACKWARD;
                }
            } else {
                if (this.southCarrer) {
                    return Direction.FORWARD;
                } else {
                    return Direction.BACKWARD;
                }
            }
        }
 
        if (Carrer instanceof HCalle) {
            if ((this.eastCarrer) && (this.westCarrer)) {
                if (randomDirection == 0) {
                    return Direction.FORWARD;
                } else {
                    return Direction.BACKWARD;
                }
            } else {
                if (this.eastCarrer) {
                    return Direction.FORWARD;
                } else {
                    return Direction.BACKWARD;
                }
            }
        }
 
        return Direction.FORWARD;
    }
     
    public String getId() {
        return this.idCrossRoad;
    }
     
    public int getIniX() {        
        return this.cmIniX;
    }
     
    public int getFinX() {        
        return this.cmFinX;
    }
     
    public int getIniY() {        
        return this.cmIniY;
    }
     
    public int getFinY() {        
        return this.cmFinY;
    }
 
    public int getSafetySpeedInCmHour() {
        return this.safetySpeedInCmHour;
    }
     
    public Calle getVCarrer() {
        return this.vCarrer;
    }
     
    public Calle getHCarrer() {
        return this.hCarrer;
    }
     
    private void setTurns() {       
        this.eastCarrer = this.vCarrer.cmFinX<this.hCarrer.cmFinX;
        this.westCarrer = this.hCarrer.cmIniX<this.vCarrer.cmIniX;
 
        this.northCarrer = this.vCarrer.cmIniY<this.hCarrer.cmIniY;
        this.southCarrer = this.hCarrer.cmFinY<this.vCarrer.cmFinY;
    }
 
 
 
    private void setCarrers(Calle Carrer1, Calle Carrer2) {
        if (Carrer1 instanceof VCalle) {
            this.vCarrer = Carrer1;
        }
 
        if (Carrer1 instanceof HCalle) {
            this.hCarrer = Carrer1;
        }
 
        if (Carrer2 instanceof VCalle) {
            this.vCarrer = Carrer2;
        }
 
        if (Carrer2 instanceof HCalle) {
            this.hCarrer = Carrer2;
        }
         
        this.idCrossRoad = this.vCarrer.getId() + "·" + this.hCarrer.getId();
    }
 
    private void setDimension() {
        this.cmWidthX = this.vCarrer.cmWidth;
        this.cmWidthY = this.hCarrer.cmWidth;
    }
     
    private void setPosition() {
        this.cmCenterX = this.vCarrer.cmIniX + (this.cmWidthX / 2);
        this.cmCenterY = this.hCarrer.cmIniY + (this.cmWidthY / 2);
 
        this.cmIniX = this.vCarrer.cmIniX;
        this.cmFinX = this.cmIniX + this.cmWidthX;
        this.cmIniY = this.hCarrer.cmIniY;
        this.cmFinY = this.cmIniY + this.cmWidthY;
    }
    public Calle getDiferenteDe(Calle calle){
        if(vCarrer.equals(calle)){
            return hCarrer;
        }else{
            return vCarrer;
        }
    }
    public void setSafetySpeedInCmHour(int safetySpeedInCmHour) {
        this.safetySpeedInCmHour = safetySpeedInCmHour;
    }
     
    @Override
    public String toString() {
        return "Crossroad "+this.getId();
    }
     
    public void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY) {
        int iniX, iniY, finX, finY;
         
        iniX = (int)((this.cmIniX / factorX) + offsetX);
        iniY = (int)((this.cmIniY / factorY) + offsetY);
        finX = (int)((this.cmWidthX / factorX));
        finY = (int)((this.cmWidthY / factorY));                
                 
        // Paint crossroad
        g.setColor(Color.GRAY);
        g.fillRect(iniX, iniY, finX, finY);
        g.setColor(Color.BLACK);
        g.drawRect(iniX, iniY, finX, finY);
 
    }
}
