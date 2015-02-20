package misbeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author dvd
 */
public class Producto implements Serializable {
    
    private String descripcion;
    private int idproducto;
    private int stockactual;
    private int stockminimo;
    private float pvp;
    
    
    private PropertyChangeSupport propertySupport;
    
    public Producto(String descripcion, int idproducto, int stockactual, int stockminimo, float pvp) {
        propertySupport = new PropertyChangeSupport(this);
        this.descripcion = descripcion;
        this.idproducto = idproducto;
        this.stockactual = stockactual;
        this.stockminimo = stockminimo;
        this.pvp = pvp;
    }
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertySupport().addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertySupport().removePropertyChangeListener(listener);
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idproducto
     */
    public int getIdproducto() {
        return idproducto;
    }

    /**
     * @param idproducto the idproducto to set
     */
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    /**
     * @return the stockactual
     */
    public int getStockactual() {
        return stockactual;
    }

    /**
     * @param stockactual the stockactual to set
     */
    public void setStockactual(int valornuevo) {
        int valoranterior = stockactual;
        stockactual = valornuevo;
        if(stockactual < getStockminimo()){
            propertySupport.firePropertyChange("stockactual",valoranterior,stockactual);
        }
    }

    /**
     * @return the stockminimo
     */
    public int getStockminimo() {
        return stockminimo;
    }

    /**
     * @param stockminimo the stockminimo to set
     */
    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    /**
     * @return the pvp
     */
    public float getPvp() {
        return pvp;
    }

    /**
     * @param pvp the pvp to set
     */
    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    /**
     * @return the propertySupport
     */
    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    /**
     * @param propertySupport the propertySupport to set
     */
    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    
}
