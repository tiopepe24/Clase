/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
 
 
public class Mapa extends Canvas implements Runnable {
 
    private int cityCmWidth;
    private int cityCmHeight;
    private int mapWidth;
    private int mapHeigth;
    private float factorX;
    private float factorY;
    private float zoomLevel;
    private int offsetX;
    private int offsetY;
    private int cmCarrerWidth;
    private int cmCarrerMark;
    private Controlador c;
    private BufferedImage imgMap, imgBg;
    private Terminal term;
    ArrayList<Avion> avionwait = new ArrayList<Avion>();
    int plibres = 0;
    int aespera = 0;
    //private Traffic traffic;
    private ArrayList<Calle> carrers;
    private ArrayList<Cruce> crossroads;
    ArrayList<Finger> f = new ArrayList<Finger>();
 
    public Mapa(int cityCmWidth, int cityCmHeight, int mapPixWidth, int mapPixHeight) {
        this.cityCmWidth = cityCmWidth;
        this.cityCmHeight = cityCmHeight;
        this.mapWidth = mapPixWidth;
        this.mapHeigth = mapPixHeight;
        this.offsetX = 0;
        this.offsetY = 0;
        this.zoomLevel = 2;
        this.setFactorXY();
 
        this.cmCarrerWidth = 800;
        this.cmCarrerMark = 300; // Longitud marcas viales en cm
 
        this.carrers = new ArrayList<Calle>();
        this.crossroads = new ArrayList<Cruce>();
 
        this.loadCarrers();
        this.calculateCrossRoads();
 
        Dimension d = new Dimension(1024, 600);
        this.setPreferredSize(d);
 
        try {
            this.imgBg = ImageIO.read(new File("fondo.jpg"));
        } catch (IOException e) {
            System.out.println("Img Error: not found bg.jpg");
        }
    }
    public void setControlador(Controlador c){
        this.c = c;
    }
    public void CrearFinger(){
        int espacio = 5;
        for (int i = 0; i < 11; i++) {
                f.add(new Finger("F"+(i+1),200*(espacio+i*4),3300,Finger.Estado.LIBRE));
                plibres++;
                System.out.println("F"+(i+1)+" creado");
        }
    }
    public ArrayList<Finger> getFinger(){
        return f;
    }
    public void pintaAvion(Graphics g){
        this.c.paint(g, factorX, factorY, offsetX, offsetY);
    }
    public void pintaTerminal(Graphics g){
        term = new Terminal("Terminal",900,4000);
        term.paint(g, factorX, factorY, offsetX, offsetY);
    }
    public void pintaFinger(Graphics g){
        for (int i = 0; i < f.size(); i++) {
            f.get(i).paint(g, factorX, factorY, offsetX, offsetY);
        }
    }
 
    public ArrayList<Calle> getCarrers() {
        return this.carrers;
    }
 
    public void setWidth(int mapWidth) {
        this.mapWidth = mapWidth;
        this.setFactorXY();
    }
 
    public void setHeig(int mapHeigth) {
        this.mapHeigth = mapHeigth;
        this.setFactorXY();
    }
 
 
    public void setFactorXY() {
        this.mapWidth = this.getWidth();
        this.mapHeigth = this.getHeight();
 
        this.factorX = ((float) this.cityCmWidth / (float) this.mapWidth / this.zoomLevel);
        this.factorY = ((float) this.cityCmHeight / (float) this.mapHeigth / this.zoomLevel);
        this.paintImgMap();
    }
 
    private boolean addCrossRoad(Cruce newCr) {
        Iterator<Cruce> itr = this.crossroads.iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(newCr)) {
                return false;  // ====== Crossroad duplicated ================>>
            }
        }
 
        this.crossroads.add(newCr);
        return true;
    }
 
    private void calculateCrossRoads() {
        Iterator<Calle> itrCarrers1;
        Iterator<Calle> itrCarrers2;
        Calle auxCarrer1, auxCarrer2;

        itrCarrers1 = this.carrers.iterator();
        while (itrCarrers1.hasNext()) {
            auxCarrer1 = itrCarrers1.next();

            itrCarrers2 = this.carrers.iterator();
            if (auxCarrer1 instanceof HCalle){
                while (itrCarrers2.hasNext()) {
                    auxCarrer2 = itrCarrers2.next();
                    if (auxCarrer2 instanceof VCalle){
                    	if (auxCarrer1.carrerIntersection(auxCarrer2)) {
                        this.addCrossRoad(new Cruce(auxCarrer1, auxCarrer2));
                    	}
                    }
                }
            }
        }
    }
 
    private void loadCarrers() {
        this.carrers.add(new HCalle("H1", this.cmCarrerWidth, this.cmCarrerMark, 10000, 0, 0));
        this.carrers.add(new HCalle("H3", this.cmCarrerWidth, this.cmCarrerMark, 10000, 0, 6500));
        //this.carrers.add(new VCalle("V5", this.cmCarrerWidth, this.cmCarrerMark, 3000, 7000, 0));
        this.carrers.add(new VCalle("V1", this.cmCarrerWidth, this.cmCarrerMark, 4000, 0, 2500));
        this.carrers.add(new VCalle("F1", 500, 200, 800, 1000, 2500));
        this.carrers.add(new VCalle("F2", 500, 200, 800, 1800, 2500));
        this.carrers.add(new VCalle("F3", 500, 200, 800, 2600, 2500));
        this.carrers.add(new VCalle("F4", 500, 200, 800, 3400, 2500));
        this.carrers.add(new VCalle("F5", 500, 200, 800, 4200, 2500));
        this.carrers.add(new VCalle("F6", 500, 200, 800, 5000, 2500));
        this.carrers.add(new VCalle("F7", 500, 200, 800, 5800, 2500));
        this.carrers.add(new VCalle("F8", 500, 200, 800, 6600, 2500));
        this.carrers.add(new VCalle("F9", 500, 200, 800, 7400, 2500));
        this.carrers.add(new VCalle("F10", 500, 200, 800, 8200, 2500));
        this.carrers.add(new VCalle("F11", 500, 200, 800, 9000, 2500));
        this.carrers.add(new VCalle("V2", this.cmCarrerWidth, this.cmCarrerMark, 2500, 10000, 0));
        this.carrers.add(new HCalle("H2", this.cmCarrerWidth, this.cmCarrerMark, 10000, 0, 2500));
        CrearFinger();
    }
 
    public void moveRight() {
        this.offsetX += 10;
        this.setFactorXY();
    }
 
    public void moveLeft() {
        this.offsetX -= 10;
        this.setFactorXY();
    }
 
    public void moveDown() {
        this.offsetY += 10;
        this.setFactorXY();
    }
 
    public void moveUp() {
        this.offsetY -= 10;
        this.setFactorXY();
    }
 
    public synchronized void paint() {
        BufferStrategy bs;
        bs = this.getBufferStrategy();
        if (bs == null) {
            return; 
        }
         
        if ((this.mapWidth < 0) || (this.mapHeigth < 0)) {
            System.out.println("Map size error: (" + this.mapWidth + "," + this.mapHeigth + ")");
            return; 
        }        
         
        Graphics gg = bs.getDrawGraphics();
 
        gg.drawImage(this.imgMap, 0, 0, this.mapWidth, this.mapHeigth, null); 
        this.pintaFinger(gg);
        this.pintaAvion(gg);
        bs.show();
        gg.dispose();
    }
 
    public void paintBackgroud(Graphics g) {
        g.drawImage(this.imgBg, 0, 0, null);
    }
 
 
 
    public void paintCrossRoads(Graphics g) {
        Iterator<Cruce> itr = this.crossroads.iterator();
 
        while (itr.hasNext()) {
            itr.next().paint(g, this.factorX, this.factorY, this.offsetX, this.offsetY);
        }
    }
 
    public synchronized void paintImgMap() {
        if ((this.mapWidth <= 0) || (this.mapHeigth <= 0)) {
            System.out.println("Map size error: (" + this.mapWidth + "," + this.mapHeigth + ")");
            return; 
        }
 
        this.imgMap = new BufferedImage(this.mapWidth, this.mapHeigth, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = this.imgMap.createGraphics();
 
        this.paintBackgroud(g);
        this.paintCarrers(g);
        this.paintCrossRoads(g);
        
        this.pintaTerminal(g);
        
        
        
 
        g.dispose();
    }
    public void paintCarrers(Graphics g) {
        Iterator<Calle> itr = this.carrers.iterator();
 
        while (itr.hasNext()) {
            itr.next().paint(g, this.factorX, this.factorY, this.offsetX, this.offsetY);
        }
    }
 
    @Override
    public void run() {
        this.createBufferStrategy(2);
 
        while (!Aeropuerto.isEnd()) {
            this.paint();
 
            do {
                try {
                    Thread.sleep(7); // nano -> ms
                } catch (InterruptedException ex) {
                }
            } while (Aeropuerto.isPaused());
        }
    }
 
    public void zoomIn() {
        this.zoomIn(0.01f);
    }
 
    public void zoomIn(float inc) {
        this.zoomLevel += inc;
        this.setFactorXY();
 
    }
 
    public void zoomOut() {
        this.zoomOut(0.01f);
        this.setFactorXY();
    }
 
    public void zoomOut(float inc) {
        this.zoomLevel -= inc;
        this.setFactorXY();
    }
 
    public void zoomReset() {
        this.zoomLevel = 1;
        this.setFactorXY();
    }
    public void AparcarAvion(Avion av){
        System.out.println("Hay "+plibres+" Fingers libres");
        if(plibres == 0){
            System.out.println("Se pone el avion en espera");
            avionwait.add(av);
            aespera++;
            System.out.println("Hay "+aespera+" aviones en espera");
        }
        else{
            for (int i = 0; i < f.size(); i++) {
                if(f.get(i).getEstado()==true){
                    //f.get(i).setA1(av);
                    f.get(i).setOcupat();
                    System.out.println("El avion aparca en el Finger: "+(i+1));
                    plibres--;
                    break;
                }
            }
        }
    }
    /*public void SaleAvion(Avion av){
        for (int i = 0; i < f.size(); i++) {
            if(f.get(i).getA1()== av){
                f.get(i).setLibre();
                plibres++;      
                System.out.println("El avion "+f.get(i).getA1()+" Sale y deja el finger "+f.get(i)+" libre");
            }
        }
    }*/
}

