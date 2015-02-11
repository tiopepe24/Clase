/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointerficiesapp;

import java.sql.SQLException;

/**
 *
 * @author dvd
 */
public class principal {
    protected Buscar_Diseños buscardis;
    protected Buscar_Habilidades buscarhabi;
    protected Buscar_Objetos buscarobj;
    protected Crea_tu_build creatubuild;
    protected crea_tu_equipo creatuequipo;
    protected Crear_usuario crearuser;
    protected inicio inicio;
    principal() throws SQLException{
        inicio = new inicio();
        buscardis = new Buscar_Diseños();
        buscarhabi = new Buscar_Habilidades();
        buscarobj = new Buscar_Objetos();
        creatubuild = new Crea_tu_build();
        creatuequipo = new crea_tu_equipo();
        crearuser = new Crear_usuario(); 
    }
    public static void main(String[] args) throws SQLException {
        principal princ = new principal();
        princ.inicio.setprinc(princ);
        princ.buscarobj.setprinc(princ);
        princ.buscarhabi.setprinc(princ);
        princ.buscardis.setprinc(princ);
        princ.creatubuild.setprinc(princ);
        princ.creatuequipo.setprinc(princ);
        princ.crearuser.setprinc(princ);
        princ.inicio.setVisible(true); 
        
    }
}
