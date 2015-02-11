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
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
 
public abstract class Calle {
 
    protected String idWay;
    protected int cmLong;
    protected int cmIniX;
    protected int cmIniY;
    protected int cmFinX;
    protected int cmFinY;
    protected int cmWidth;
    protected int cmMark;
    protected ArrayList<Avion> avions = new ArrayList();
    protected ArrayList<Cruce> crossRoads = new ArrayList();
    protected ArrayList<Integer> forwardEntryPoint = new ArrayList();
    protected ArrayList<Integer> backwardEntryPoint = new ArrayList();
 
    public Calle(String idWay, int cmWayWidth, int cmWayMark, int cmLong, int cmPosIniX, int cmPosIniY) {
        this.idWay = idWay;
        this.cmLong = cmLong;
        this.cmWidth = cmWayWidth;
        this.cmMark = cmWayMark;
        this.cmIniX = cmPosIniX;
        this.cmIniY = cmPosIniY;
 
        this.createDefaultEntryPoints();
    }
 
    public String getId() {
        return this.idWay;
    }
    public int getCmLong() {
        return this.cmLong;
    }
     
    /*public abstract double getCourse(int cmPosition, Direction direction);    
     
    public abstract int getCmPosX(int cmPosition, Direction direction);
 
    public abstract int getCmPosY(int cmPosition, Direction direction);
 
    public abstract int getCmPosition(int cmPosX, int cmPosY, Avio.Direction direction);
     
    */
 
    public int getCmPosIniX() {
        return this.cmIniX;
    }
 
    public int getCmPosIniY() {
        return this.cmIniY;
    }
 
    public int getEntryPoint(Direction direction) {
        int cmPosition = 0;
 
        switch (direction) {
            case FORWARD:
                cmPosition = this.forwardEntryPoint.get(0);
                break;
            case BACKWARD:
                cmPosition = this.backwardEntryPoint.get(0);
                break;
            default:
                throw new AssertionError(direction.name());
        }
 
        return cmPosition;
    }
 
    public synchronized void addAvio(Avion avio) {
 
            this.avions.add(avio);
 
    }
 
    public abstract void addCrossRoad(Cruce cr);
 
    private void createDefaultEntryPoints() {
        this.forwardEntryPoint.add(0);
        this.backwardEntryPoint.add(this.cmLong);
    }
 
    public abstract int distanceToCrossRoadInCm(Cruce cr, Avion Avio);
 
    public abstract Cruce inFrontCrossRoad(Avion Avio);
 
    public synchronized Avion inFrontAvio(Avion Avio) {
        ArrayList<Avion> AvioList;
        Avion inFrontAvio, actualAvio;
        int minDistance, actualDistance;
 
        inFrontAvio = null;
            AvioList = this.avions;
 
 
        minDistance = this.getCmLong() + 1;
 
        Iterator<Avion> itr = AvioList.iterator();
        while (itr.hasNext()) {
            actualAvio = itr.next();
 
            if (!actualAvio.equals(Avio)) {
                actualDistance = Avio.getDirection().getIncrement() * (actualAvio.getCmPosition() - Avio.getCmPosition());
 
                if ((actualDistance < minDistance) && (actualDistance > 0)) {
                    minDistance = actualDistance;
                    inFrontAvio = actualAvio;
                }
            }
        }
 
        return inFrontAvio;
    }
 
    public abstract boolean insideAnyCrossRoad(int cmPosition);
 
    public abstract Cruce intersectedCrossRoad(int cmPosition);
 
    public abstract boolean insideThisCrossRoad(int cmPosition, Cruce crossRoad);
 
    public abstract boolean posIsInside(int cmPosition, Direction direction);
 
    public synchronized void removeAvio(Avion Avio) {
        this.avions.remove(Avio);
    }
    public Calle Pedircalle(int position){
        int avposition = 0;
        for (int i = 0; i < crossRoads.size(); i++) {
            if(this instanceof HCalle){
               avposition = position+this.cmIniX;
               if (crossRoads.get(i).getIniX()==avposition){
                   return crossRoads.get(i).getVCarrer();
               }
            }
            else{
               avposition = position+this.cmIniY;
               if (crossRoads.get(i).getIniY()==avposition){
                   return crossRoads.get(i).getHCarrer();
               }
            }
        }
        return null;
    }
    public Cruce getCruce(Calle calle){
        for (int i = 0; i < crossRoads.size(); i++) {
            if(crossRoads.get(i).getDiferenteDe(this).equals(calle)){
                return crossRoads.get(i);
            } 
        }
        return null;
    }
 
    public boolean carrerIntersection(Calle way) {
        Calle vWay, hWay;
 
        hWay = vWay = null;
        if (way instanceof VCalle) {
            vWay = way;
        }
 
        if (way instanceof HCalle) {
            hWay = way;
        }
 
        if (this instanceof VCalle) {
            vWay = this;
        }
 
        if (this instanceof HCalle) {
            hWay = this;
        }
 
        if ((vWay == null) || (hWay == null)) {
            return false; // =================>
        }
 
        return (hWay.cmIniX <= vWay.cmFinX)
                && (vWay.cmIniX <= hWay.cmFinX)
                && (hWay.cmIniY <= vWay.cmFinY)
                && (vWay.cmIniY <= hWay.cmFinY);
    }
 
    public abstract void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY);
 
    @Override
    public String toString() {
        return "Avió: " + this.getId();
    }
}
