/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dvd
 */
public class Controlador extends Thread {
    ArrayList<Avion> aviones = new ArrayList<>();
    ArrayList<Calle> calles = new ArrayList<>();
    ArrayList<Finger> fingers = new ArrayList<Finger>();
    int avion = 0;
    final int numMaxaviones = 40;
    public Controlador(ArrayList<Calle> calle, ArrayList<Finger> f){
        this.calles = calle;
        this.fingers = f;
    }
    public ArrayList<Avion> getAviones(){
        return aviones;
    }
    
    public synchronized void AddAvion(String idAvion, Calle way){
        String finger = "";
        int rand;
        while(finger==""){
            rand = (int) (Math.random()*11);
            while(!fingers.get(rand).getEstado()){
                rand = (int) (Math.random()*11);
            }
            for (int i = 0; i < fingers.size(); i++) {
                if(fingers.get(rand).getEstado()){
                    finger = fingers.get(rand).getId();
                    fingers.get(rand).setReservado();
                    break;
                }
            }
            if(finger==""){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Avion av1 = new Avion(idAvion,way,finger,this);
        this.aviones.add(av1);
        av1.start();
    }
    public synchronized void BorrarAvion(Avion avion){
        String fav = avion.getFinger();
        for (int i = 0; i < fingers.size(); i++) {
            if(fingers.get(i).getId()==fav){
                fingers.get(i).setLibre();
                //notify();
                break;
            }
        }
        this.borrar(avion);   
    }
    public void setFingerOcu(String finger){
        for (int i = 0; i < fingers.size(); i++) {
            if(fingers.get(i).getId()==finger){
                fingers.get(i).setOcupat();
                break;
            }
        }
    }
    public boolean avanzar(String id,int position, Calle calle){
        for (int i = 0; i < aviones.size(); i++) {
            if(aviones.get(i).getCmPosition() == position && !aviones.get(i).getIdCar().equals(id) && aviones.get(i).getWay().equals(calle)){
                return false;
            }
        }
        return true;
    }
    public void setFingerLibre(String finger){
        for (int i = 0; i < fingers.size(); i++) {
            if(fingers.get(i).getId()==finger){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                fingers.get(i).setLibre();
                break;
            }
        }
    }
    public void paint(Graphics g, float factorX, float factorY, int offsetX, int offsetY){
        for (int i = 0; i < this.aviones.size(); i++) {
            this.aviones.get(i).paint(g, factorX, factorY, offsetX, offsetY);
        }
    }
    public void borrar(Avion av){
        aviones.remove(av);
    }
    public synchronized void comprobarcalle(Calle calle){
        for (int i = 0; i < aviones.size(); i++) {
            if(aviones.get(i).getWay() == calle){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public synchronized void despoater(){
        for (int i = 0; i < aviones.size(); i++) {
            if(aviones.get(i).getWay().getId() != "H1"){
                break;
            }
        }
        notify();
    
    }
    @Override
    public void run(){
        for (avion = 0; avion < numMaxaviones; avion++) {
            comprobarcalle(calles.get(0));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddAvion("Avion "+avion,calles.get(0));
            } 
            
        }
}
