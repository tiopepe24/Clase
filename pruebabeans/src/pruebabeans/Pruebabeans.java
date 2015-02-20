/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabeans;

/**
 *
 * @author dvd
 */
import misbeans.Producto;
import misbeans.Pedido;
public class Pruebabeans {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Producto producto = new Producto("Dabber sur femme 2011",1,10,3,16);
        Pedido pedido = new Pedido();
        
        producto.addPropertyChangeListener(pedido);
        producto.setStockactual(2);
        if(pedido.isPedir()){
            System.out.println("REALIZAR PEDIDO EN PRODUCTO: "+producto.getDescripcion());
        }else{
            System.out.println("NO ES NECESARIO REALIZAR PEDIDO EN PRODUCTO: "+ producto.getDescripcion());
        }
            
    }
    
}
