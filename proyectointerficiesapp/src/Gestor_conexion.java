/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import proyectointerficiesapp.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author dvd
 */
public class Gestor_conexion {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    //  Database credentials
    String user;
    String pass;
    Connection conn = null;
    Statement stmt = null;
    ArrayList<String[]> datos = new ArrayList<String[]>();
    ArrayList<String>datoss = new ArrayList<>();
    Object[][] data;
    String[] cabeceras;
    String[] tablas;
    String database;
    public void opencon() {
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/archeage","root","arroba24");
            System.out.println("Connected");
            stmt = conn.createStatement();
        }catch(SQLException se){
           //Handle errors for JDBC
            System.out.println("Error al conectar");
           //se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
            System.out.println("Error al conectar 2");
           //e.printStackTrace();
        }
    }//end main
    public void consultar1(String sql) throws SQLException{
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            //recogemos el numero de columnas de la consulta
            int n = meta.getColumnCount();
            cabeceras = new String[n];
            //insertamos los nombres de las columnas en la array
            for (int i = 1; i <= n; i++) {
                cabeceras[i-1] = meta.getColumnName(i);
            }
            //varibales para contar el tamaño de la matriz
            //recorremos la sentencia sql en la bbdd
            datoss.clear();
            while(rs.next()){
                String temp ="";
                for (int i = 1; i <= n; i++) {
                    temp = rs.getString(i);
                }
                datoss.add(temp);
            }
            rs.close();
    }
    public void consultar(String sql) throws SQLException{
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            //recogemos el numero de columnas de la consulta
            int n = meta.getColumnCount();
            cabeceras = new String[n];
            //insertamos los nombres de las columnas en la array
            for (int i = 1; i <= n; i++) {
                cabeceras[i-1] = meta.getColumnName(i);
            }
            //varibales para contar el tamaño de la matriz
            //recorremos la sentencia sql en la bbdd
            datos.clear();
            while(rs.next()){
                String []temp = new String[n];
                for (int i = 1; i <= n; i++) {
                    temp[i-1] = rs.getString(i);
                }
                datos.add(temp);
            }
            rs.close();
    }
    public void insertar(String sql) throws ClassNotFoundException{
        try{
            stmt.executeUpdate(sql);
        }catch(SQLException se){
           //Handle errors for JDBC
            System.out.println("Error");
           //se.printStackTrace();
        }
    }
    public void MostrarTablas() throws SQLException{
        String sql = "show tables";
        tablas = new String[100];
        int i = 0;
        try{
            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("Tables_in_"+this.database)!=null){
                    tablas[i]=rs.getString("Tables_in_"+this.database);
                    //System.out.println(tablas[i]);  
                }
                i++;
            }
            //System.out.println(rs.getString(n));
        }catch(SQLException se){
           //Handle errors for JDBC
            System.out.println("Error");
           //se.printStackTrace();
        }
    }
    public boolean comprobarnick(String nick) throws SQLException{
            ResultSet rs = stmt.executeQuery("select nick from usuarios where nick = '"+nick+"'");
            ResultSetMetaData meta = rs.getMetaData();
            //recogemos el numero de columnas de la consulta
            int n = meta.getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= n; i++) {
                    if(rs.getString(i).equals(nick)){
                        return true;
                    }
                }
            }
            rs.close();
            return false;
    }
    public boolean comprobarusuario(String user) throws SQLException{
            ResultSet rs = stmt.executeQuery("select nombre from usuarios where nombre = '"+user+"'");
            ResultSetMetaData meta = rs.getMetaData();
            //recogemos el numero de columnas de la consulta
            int n = meta.getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= n; i++) {
                    if(rs.getString(i).equals(user)){
                        return true;
                    }
                }
            }
            rs.close();
            return false;
    }
    public boolean comprobarusuarioypass(String user,String pass) throws SQLException{
            ResultSet rs = stmt.executeQuery("select nombre,password from usuarios where nombre = '"+user+"' and password = '"+pass+"'");
            ResultSetMetaData meta = rs.getMetaData();
            //recogemos el numero de columnas de la consulta
            int n = meta.getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= n; i++) {
                    if(rs.getString(i).equals(user)){
                        return true;
                    }
                }
            }
            rs.close();
            return false;
    }
    public void closecon() throws SQLException{
        System.out.println("Closing conection");
        stmt.close();
        conn.close();
    }
    public Object[][] getDatos(){
        String [][]tempo = new String[datos.size()][datos.get(0).length];
        for (int i = 0; i < datos.size(); i++) {
            String[] fila = new String[datos.get(0).length];
            fila = datos.get(i);
            for (int j = 0; j < datos.get(0).length; j++) {
                tempo[i][j] = fila[j];
            }
        }
        return tempo;
    }
    public String[] getCabeceras(){
        return cabeceras;
    }
    public String[] getTablas(){
        return tablas;
    }
    public void setdatabase(String db){
        this.database = db;
    }
    public void setuser(String user){
        this.user = user;
    }
    public void setpass(String pass){
        this.pass = pass;
    }
    public String getuser(){
        return user;
    }
    public String getpass(){
        return pass;
    }
}//end FirstExample
