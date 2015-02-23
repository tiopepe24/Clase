
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dvd
 */
public class apparche extends JFrame{
    private JFrame base, crearusu;
    private JPanel inicio,buscarobj, buscardis, buscarhab, crearuser, creatuequipo, creatushabi;
    private Gestor_conexion con;
    private int iduser;
    private int usuarios;
    
    apparche() throws SQLException{
        
        con = new Gestor_conexion();
        base = new JFrame();
        base.setBounds(0, 0, 600, 450);
        base.setVisible(true);
        
        crearusu = new JFrame();
        crearusu.setBounds(100, 0, 450, 200);
        crearusu.setVisible(false);
        con.opencon();
        con.consultar4("select count(*) from usuarios");
        con.closecon();
        this.usuarios = Integer.parseInt(con.dato.get(0));
        pantallainicio();
        pantallabuscarobjetos();
        pantallahabi();
        pantalladis();
        crearusuario();
        crearhabi();
        creatuequipo();
        
        base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void pantallainicio(){
        inicio = new JPanel();
        inicio.setBounds(0, 0, 600, 450);
        inicio.setLayout(null);
        
        inicio.setBackground(Color.ORANGE);
        
        JLabel jl1 = new JLabel("Usuario:");
        jl1.setBounds(300, 20, 200, 20);
        inicio.add(jl1);
        
        JTextField tx1 = new JTextField();
        tx1.setBounds(380,20,100,20);
        inicio.add(tx1);
        
        JLabel jl2 = new JLabel("Password:");
        jl2.setBounds(300, 40, 80, 20);
        inicio.add(jl2);
        
        JPasswordField pas1 = new JPasswordField();
        pas1.setBounds(380,40,100,20);
        inicio.add(pas1);
        
        JButton btn1 = new JButton("Conectarse");
        btn1.setBounds(300, 70, 120, 20);
        inicio.add(btn1);
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.orange);
        
        JButton btn2 = new JButton("Registrarse");
        btn2.setBounds(420, 70, 120, 20);
        inicio.add(btn2);
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.orange);
        
        JButton btn3 = new JButton("Desconectarse");
        btn3.setBounds(420, 70, 150, 20);
        inicio.add(btn3);
        btn3.setVisible(false);
        btn3.setBackground(Color.BLACK);
        btn3.setForeground(Color.orange);
        
        JButton btn4 = new JButton("Buscar informacion Objetos");
        btn4.setBounds(150, 140, 220, 20);
        inicio.add(btn4);
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.orange);
        
        JButton btn5 = new JButton("Buscar informacion habilidades");
        btn5.setBounds(150, 180, 220, 20);
        inicio.add(btn5);
        btn5.setBackground(Color.BLACK);
        btn5.setForeground(Color.orange);
        
        JButton btn6 = new JButton("Buscar informacion diseños");
        btn6.setBounds(150, 220, 220, 20);
        inicio.add(btn6);
        btn6.setBackground(Color.BLACK);
        btn6.setForeground(Color.orange);
        
        JButton btn7 = new JButton("Crea tu build");
        btn7.setBounds(150, 260, 220, 20);
        inicio.add(btn7);
        btn7.setVisible(false);
        btn7.setBackground(Color.BLACK);
        btn7.setForeground(Color.orange);
        
        JButton btn8 = new JButton("Crea tu equipo");
        btn8.setBounds(150, 300, 220, 20);
        inicio.add(btn8);
        btn8.setVisible(false);
        btn8.setBackground(Color.BLACK);
        btn8.setForeground(Color.orange);
        
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)        
            {
                String pass = "";
                char[] pas = pas1.getPassword();
                for (int i = 0; i < pas.length; i++) {
                    pass = pass+pas[i];
                }
                con.opencon();
                try {
                    if(con.comprobarusuarioypass(tx1.getText(),pass)){
                        jl1.setText("Bienvenido "+tx1.getText());
                        jl2.setVisible(false);
                        tx1.setVisible(false);
                        pas1.setVisible(false);
                        btn1.setVisible(false);
                        btn2.setVisible(false);
                        btn3.setVisible(true);
                        btn7.setVisible(true);
                        btn8.setVisible(true);
                        con.consultar1("select iduser from usuarios where nombre = \'"+tx1.getText()+"\'");
                        iduser = Integer.parseInt(con.datoss.get(0));
                        System.out.println(iduser);
                    }
                    con.closecon();
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                crearusu.setVisible(true);
                crearusu.setContentPane(crearuser);
            }
        });
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                jl1.setText("Usuario:");
                jl2.setVisible(true);
                tx1.setVisible(true);
                pas1.setVisible(true);
                btn1.setVisible(true);
                btn2.setVisible(true);
                btn3.setVisible(false);
                btn7.setVisible(false);
                btn8.setVisible(false);
                
            }
        });
        btn4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(buscarobj);   
            }
        });
        btn5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(buscarhab);   
            }
        });
        btn6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(buscardis);   
            }
        });
        btn7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(creatushabi);
            }
        });
        btn8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(creatuequipo);
            }
        });
        base.setContentPane(inicio);
    }
    public void pantallabuscarobjetos() throws SQLException{
        buscarobj = new JPanel();
        buscarobj.setBounds(0, 0, 600, 450);
        buscarobj.setLayout(null);
        
        DefaultTableModel jModel1 = new DefaultTableModel();
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(150, 40, 400, 300);
        buscarobj.add(scroll);

        JLabel lb1 = new JLabel("Buscar informacion de objetos de ArcheAge");
        lb1.setBounds(10, 20, 300, 20);
        buscarobj.add(lb1);
        
        JButton bt1 = new JButton("Back");
        bt1.setBounds(10, 300, 90, 20);
        buscarobj.add(bt1);
        
        JComboBox combo1 = new JComboBox();
        combo1.setBounds(20, 40, 100, 20);
        buscarobj.add(combo1);
        combo1.addItem("Armas");
        combo1.addItem("Armaduras");
        combo1.addItem("Accesorios");
        combo1.addItem("Trajes");
        
        JComboBox combo2 = new JComboBox();
        combo2.setBounds(20, 80, 100, 20);
        buscarobj.add(combo2);
        combo2.setEnabled(false);
        
        JComboBox combo3 = new JComboBox();
        combo3.setBounds(20, 120, 100, 20);
        buscarobj.add(combo3);
        combo3.setEnabled(false);
        
        JTable tabla = new JTable();
        tabla.setBounds(100, 30, 400, 300);
        buscarobj.add(tabla);
        tabla.setEnabled(false);
        scroll.setViewportView(tabla);
        
        bt1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(inicio);   
            }
        });
        combo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                combo2.removeAllItems();
                if(combo1.getSelectedItem().equals("Armas")){
                    try {
                        combo2.setEnabled(true);
                        llenarcombos1(combo2,"Select distinct(tipo) from armas");
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    combo2.removeAllItems();
                    combo2.setEnabled(false);
                    combo3.setEnabled(false);
                }
        
 
                if(combo1.getSelectedItem().equals("Armaduras")){                   
                    try {
                        con.opencon();
                        con.consultar("select * from "+combo1.getSelectedItem().toString());
                        jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                        tabla.setModel(jModel1);
                        con.closecon();
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(combo1.getSelectedItem().equals("Accesorios")){                   
                    try {
                        con.opencon();
                        con.consultar("select * from "+combo1.getSelectedItem().toString());
                        jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                        tabla.setModel(jModel1);
                        con.closecon();
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(combo1.getSelectedItem().equals("Trajes")){                   
                    try {
                        con.opencon();
                        con.consultar("select * from "+combo1.getSelectedItem().toString());
                        jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                        tabla.setModel(jModel1);
                        con.closecon();
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                

            }
        });
        combo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                combo3.removeAllItems();
                try {
                    combo3.setEnabled(true);
                    llenarcombos(combo3,"select distinct(tipo_arma) from armas where tipo=\'"+combo2.getSelectedItem()+"\'");
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        combo3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(combo3.getSelectedItem()!=null){
                    try {
                        con.opencon();
                        con.consultar("select * from armas where tipo_arma =\'"+combo3.getSelectedItem()+"\'");
                        jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                        tabla.setModel(jModel1);
                        con.closecon();
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
        });
    }
    public void llenarcombos(JComboBox combo,String consulta) throws SQLException{
        con.opencon();
        con.consultar1(consulta);
        for (int i = 0; i < con.datoss.size(); i++) {
            combo.addItem(con.datoss.get(i));
        }
        con.closecon();
    }
    public void llenarcombos1(JComboBox combo,String consulta) throws SQLException{
        con.opencon();
        con.consultar2(consulta);
        for (int i = 0; i < con.datosss.size(); i++) {
            combo.addItem(con.datosss.get(i));
        }
        con.closecon();
    }
    public void pantallahabi() throws SQLException{
        buscarhab = new JPanel();
        buscarhab.setBounds(0, 0, 600, 450);
        buscarhab.setLayout(null);
        
        DefaultTableModel jModel1 = new DefaultTableModel();
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(150, 40, 400, 300);
        buscarhab.add(scroll);
        
        JLabel lb1 = new JLabel("Buscar informacion de habilidades de ArcheAge");
        lb1.setBounds(10, 20, 300, 20);
        buscarhab.add(lb1);
        
        JButton bt1 = new JButton("Back");
        bt1.setBounds(10, 300, 90, 20);
        buscarhab.add(bt1);
        
        JComboBox combo1 = new JComboBox();
        combo1.setBounds(20, 40, 100, 20);
        buscarhab.add(combo1);
        
        JTable tabla = new JTable();
        tabla.setBounds(100, 30, 400, 300);
        buscarhab.add(tabla);
        tabla.setEnabled(false);
        scroll.setViewportView(tabla);
        
        
        llenarcombos(combo1,"select distinct(clase) from habilidades");
        
        bt1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(inicio);   
            }
        });
        combo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                    try {
                        con.opencon();
                        con.consultar("select id,nombre,clase,tipo from habilidades where clase = \'"+combo1.getSelectedItem().toString()+"\'");
                        jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                        tabla.setModel(jModel1);
                        con.closecon();
                    } catch (SQLException ex) {
                        Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }); 
    }
    public void pantalladis() throws SQLException{
        buscardis = new JPanel();
        buscardis.setBounds(0, 0, 600, 450);
        buscardis.setLayout(null);
        
        DefaultTableModel jModel1 = new DefaultTableModel();
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(150, 40, 400, 300);
        buscardis.add(scroll);
        
        JLabel lb1 = new JLabel("Buscar informacion de Diseños de ArcheAge");
        lb1.setBounds(10, 20, 300, 20);
        buscardis.add(lb1);
        lb1.setForeground(Color.red);
        
        JButton bt1 = new JButton("Back");
        bt1.setBounds(10, 300, 90, 20);
        buscardis.add(bt1);
        
        JComboBox combo1 = new JComboBox();
        combo1.setBounds(20, 40, 100, 20);
        buscardis.add(combo1);
        
        JTable tabla = new JTable();
        tabla.setBounds(100, 30, 400, 300);
        buscardis.add(tabla);
        tabla.setEnabled(false);
        scroll.setViewportView(tabla);
        
        
        llenarcombos(combo1,"select distinct(profesion) from disenos");
        
        
        combo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    con.opencon();
                    con.consultar("select * from disenos where profesion =\'"+combo1.getSelectedItem().toString()+"\'");
                    jModel1.setDataVector(con.getDatos(), con.getCabeceras());
                    tabla.setModel(jModel1);
                    con.closecon();
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        bt1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(inicio);   
            }
        });
    }
    public void crearusuario() throws SQLException{
        crearuser = new JPanel();
        crearuser.setBounds(0, 0, 600, 400);
        crearuser.setLayout(null);
        
        JLabel jl1 = new JLabel("Usuario:");
        jl1.setBounds(10, 20, 200, 20);
        crearuser.add(jl1);
        
        JTextField tx1 = new JTextField();
        tx1.setBounds(140,20,100,20);
        crearuser.add(tx1);
        
        JButton btn2 = new JButton("Comprobar Usuario");
        btn2.setBounds(240, 20, 150, 20);
        crearuser.add(btn2);
        
        JLabel jl4 = new JLabel("Nick:");
        jl4.setBounds(10, 40, 200, 20);
        crearuser.add(jl4);
        
        JTextField tx2 = new JTextField();
        tx2.setBounds(140,40,100,20);
        crearuser.add(tx2);
        
        JButton btn3 = new JButton("Comprobar Nick");
        btn3.setBounds(240, 40, 150, 20);
        crearuser.add(btn3);
        
        JLabel jl2 = new JLabel("Password:");
        jl2.setBounds(10, 60, 80, 20);
        crearuser.add(jl2);
        
        JPasswordField pas1 = new JPasswordField();
        pas1.setBounds(140,60,100,20);
        crearuser.add(pas1);
        
        JLabel jl3 = new JLabel("Repeat Password:");
        jl3.setBounds(10, 80, 120, 20);
        crearuser.add(jl3);
        
        JPasswordField pas2 = new JPasswordField();
        pas2.setBounds(140,80,100,20);
        crearuser.add(pas2);
        
        JLabel jl5 = new JLabel("Email:");
        jl5.setBounds(10, 100, 200, 20);
        crearuser.add(jl5);
        
        JTextField tx3 = new JTextField();
        tx3.setBounds(140,100,100,20);
        crearuser.add(tx3);
        
        JButton btn1 = new JButton("Crear Usuario");
        btn1.setBounds(140, 120, 140, 20);
        crearuser.add(btn1);
        btn1.setEnabled(false);
        btn3.setEnabled(false);
        
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    con.opencon();
                    if(con.comprobarnick(tx2.getText())){
                        JOptionPane.showMessageDialog(btn3, " No Puedes usar este nick");
                    }else{
                        JOptionPane.showMessageDialog(btn3, "Puedes usar este nick");
                        btn1.setEnabled(true);
                    }
                    con.closecon();
                    
                } catch (SQLException ex) {
                    
                }
            }
        });
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    con.opencon();
                    if(con.comprobarusuario(tx1.getText())){
                        JOptionPane.showMessageDialog(btn2, " No Puedes usar este Usuario");
                    }else{
                        JOptionPane.showMessageDialog(btn2, "Puedes usar este Usuario");
                        btn3.setEnabled(true);
                    }
                    con.closecon();
                } catch (SQLException ex) {
                    
                }
            }
        });
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try {
                    if(pas1.getText().equals(pas2.getText())){
                        con.opencon();
                        con.insertar("insert into usuarios(iduser,nick,nombre,email,password) VALUES("+usuarios+",'"+tx2.getText()+"','"+tx1.getText()+"','"+tx3.getText()+"','"+pas2.getText()+"')");
                        con.closecon();
                        crearusu.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(btn2, "Las contraseñas no coinciden");
                    }
                    
                } catch (SQLException ex) {
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }
    public void crearhabi() throws SQLException{
        creatushabi = new JPanel();
        creatushabi.setBounds(0, 0, 600, 450);
        creatushabi.setLayout(null);
        ArrayList<JButton> botones =  new ArrayList<JButton>();
        ArrayList<JButton> bp =  new ArrayList<JButton>();
        ArrayList<JButton> botones2 =  new ArrayList<JButton>();
        ArrayList<JButton> bp2 =  new ArrayList<JButton>();
        ArrayList<JButton> botones3 =  new ArrayList<JButton>();
        ArrayList<JButton> bp3 =  new ArrayList<JButton>();
        
        JButton btnsave = new JButton();
        btnsave.setBounds(420, 10, 20, 20);
        creatushabi.add(btnsave);
        
        JLabel jl = new JLabel("Guardar Build");
        jl.setBounds(450,10,120,20);
        creatushabi.add(jl);
        
        JLabel jl1 = new JLabel("Clase:");
        jl1.setBounds(230,10,120,20);
        creatushabi.add(jl1);
        
        JLabel jl2 = new JLabel();
        jl2.setBounds(310,10,120,20);
        creatushabi.add(jl2);
        
        JButton btn1 = new JButton();
        btn1.setBounds(10, 80, 40, 40);
        creatushabi.add(btn1);
        botones.add(btn1);
        
        JButton btn2 = new JButton();
        btn2.setBounds(50, 80, 40, 40);
        creatushabi.add(btn2);
        botones.add(btn2);
        
        JButton btn3 = new JButton();
        btn3.setBounds(90, 80, 40, 40);
        creatushabi.add(btn3);
        botones.add(btn3);
        
        JButton btn4 = new JButton();
        btn4.setBounds(130, 80, 40, 40);
        creatushabi.add(btn4);
        botones.add(btn4);
        
        JButton btn5 = new JButton();
        btn5.setBounds(10, 120, 40, 40);
        creatushabi.add(btn5);
        botones.add(btn5);
        
        JButton btn6 = new JButton();
        btn6.setBounds(50, 120, 40, 40);
        creatushabi.add(btn6);
        botones.add(btn6);
        
        JButton btn7 = new JButton();
        btn7.setBounds(90, 120, 40, 40);
        creatushabi.add(btn7);
        botones.add(btn7);
        
        JButton btn8 = new JButton();
        btn8.setBounds(130, 120, 40, 40);
        creatushabi.add(btn8);
        botones.add(btn8);
        
        JButton btn9 = new JButton();
        btn9.setBounds(10, 160, 40, 40);
        creatushabi.add(btn9);
        botones.add(btn9);
        
        JButton btn10 = new JButton();
        btn10.setBounds(50, 160, 40, 40);
        creatushabi.add(btn10);
        botones.add(btn10);
        
        JButton btn11 = new JButton();
        btn11.setBounds(90, 160, 40, 40);
        creatushabi.add(btn11);
        botones.add(btn11);
        
        JButton btn12 = new JButton();
        btn12.setBounds(200, 80, 40, 40);
        creatushabi.add(btn12);
        botones2.add(btn12);
        
        JButton btn13 = new JButton();
        btn13.setBounds(240, 80, 40, 40);
        creatushabi.add(btn13);
        botones2.add(btn13);
        
        JButton btn14 = new JButton();
        btn14.setBounds(280, 80, 40, 40);
        creatushabi.add(btn14);
        botones2.add(btn14);
        
        JButton btn15 = new JButton();
        btn15.setBounds(320, 80, 40, 40);
        creatushabi.add(btn15);
        botones2.add(btn15);
        
        JButton btn16 = new JButton();
        btn16.setBounds(200, 120, 40, 40);
        creatushabi.add(btn16);
        botones2.add(btn16);
        
        JButton btn17 = new JButton();
        btn17.setBounds(240, 120, 40, 40);
        creatushabi.add(btn17);
        botones2.add(btn17);
        
        JButton btn18 = new JButton();
        btn18.setBounds(280, 120, 40, 40);
        creatushabi.add(btn18);
        botones2.add(btn18);
        
        JButton btn19 = new JButton();
        btn19.setBounds(320, 120, 40, 40);
        creatushabi.add(btn19);
        botones2.add(btn19);
        
        JButton btn20 = new JButton();
        btn20.setBounds(200, 160, 40, 40);
        creatushabi.add(btn20);
        botones2.add(btn20);
        
        JButton btn21 = new JButton();
        btn21.setBounds(240, 160, 40, 40);
        creatushabi.add(btn21);
        botones2.add(btn21);
        
        JButton btn22 = new JButton();
        btn22.setBounds(280, 160, 40, 40);
        creatushabi.add(btn22);
        botones2.add(btn22);
        
        JButton btn23 = new JButton();
        btn23.setBounds(400, 80, 40, 40);
        creatushabi.add(btn23);
        botones3.add(btn23);
        
        JButton btn24 = new JButton();
        btn24.setBounds(440, 80, 40, 40);
        creatushabi.add(btn24);
        botones3.add(btn24);
        
        JButton btn25 = new JButton();
        btn25.setBounds(480, 80, 40, 40);
        creatushabi.add(btn25);
        botones3.add(btn25);
        
        JButton btn26 = new JButton();
        btn26.setBounds(520, 80, 40, 40);
        creatushabi.add(btn26);
        botones3.add(btn26);
        
        JButton btn27 = new JButton();
        btn27.setBounds(400, 120, 40, 40);
        creatushabi.add(btn27);
        botones3.add(btn27);
        
        JButton btn36 = new JButton();
        btn36.setBounds(440, 120, 40, 40);
        creatushabi.add(btn36);
        botones3.add(btn36);
        
        JButton btn37 = new JButton();
        btn37.setBounds(480, 120, 40, 40);
        creatushabi.add(btn37);
        botones3.add(btn37);
        
        JButton btn38 = new JButton();
        btn38.setBounds(520, 120, 40, 40);
        creatushabi.add(btn38);
        botones3.add(btn38);
        
        JButton btn39 = new JButton();
        btn39.setBounds(400, 160, 40, 40);
        creatushabi.add(btn39);
        botones3.add(btn39);
        
        JButton btn40 = new JButton();
        btn40.setBounds(440, 160, 40, 40);
        creatushabi.add(btn40);
        botones3.add(btn40);
        
        JButton btn41 = new JButton();
        btn41.setBounds(480, 160, 40, 40);
        creatushabi.add(btn41);
        botones3.add(btn41);
        
        JButton btn46 = new JButton();
        btn46.setBounds(10, 260, 40, 40);
        creatushabi.add(btn46);
        bp.add(btn46);
        
        JButton btn47 = new JButton();
        btn47.setBounds(50, 260, 40, 40);
        creatushabi.add(btn47);
        bp.add(btn47);
        
        JButton btn48 = new JButton();
        btn48.setBounds(90, 260, 40, 40);
        creatushabi.add(btn48);
        bp.add(btn48);
        
        JButton btn49 = new JButton();
        btn49.setBounds(130, 260, 40, 40);
        creatushabi.add(btn49);
        bp.add(btn49);
        
        JButton btn50 = new JButton();
        btn50.setBounds(10, 300, 40, 40);
        creatushabi.add(btn50);
        bp.add(btn50);
        
        JButton btn51 = new JButton();
        btn51.setBounds(50, 300, 40, 40);
        creatushabi.add(btn51);
        bp.add(btn51);
        
        JButton btn52 = new JButton();
        btn52.setBounds(90, 300, 40, 40);
        creatushabi.add(btn52);
        bp.add(btn52);
        
        JButton btn53 = new JButton();
        btn53.setBounds(200, 260, 40, 40);
        creatushabi.add(btn53);
        bp2.add(btn53);
        
        JButton btn54 = new JButton();
        btn54.setBounds(240, 260, 40, 40);
        creatushabi.add(btn54);
        bp2.add(btn54);
        
        JButton btn55 = new JButton();
        btn55.setBounds(280, 260, 40, 40);
        creatushabi.add(btn55);
        bp2.add(btn55);
        
        JButton btn56 = new JButton();
        btn56.setBounds(320, 260, 40, 40);
        creatushabi.add(btn56);
        bp2.add(btn56);
        
        JButton btn57 = new JButton();
        btn57.setBounds(200, 300, 40, 40);
        creatushabi.add(btn57);
        bp2.add(btn57);
        
        JButton btn58 = new JButton();
        btn58.setBounds(240, 300, 40, 40);
        creatushabi.add(btn58);
        bp2.add(btn58);
        
        JButton btn59 = new JButton();
        btn59.setBounds(280, 300, 40, 40);
        creatushabi.add(btn59);
        bp2.add(btn59);
        
        JButton btn60 = new JButton();
        btn60.setBounds(400, 260, 40, 40);
        creatushabi.add(btn60);
        bp3.add(btn60);
        
        JButton btn61 = new JButton();
        btn61.setBounds(440, 260, 40, 40);
        creatushabi.add(btn61);
        bp3.add(btn61);
        
        JButton btn62 = new JButton();
        btn62.setBounds(480, 260, 40, 40);
        creatushabi.add(btn62);
        bp3.add(btn62);
        
        JButton btn63 = new JButton();
        btn63.setBounds(520, 260, 40, 40);
        creatushabi.add(btn63);
        bp3.add(btn63);
        
        JButton btn64 = new JButton();
        btn64.setBounds(400, 300, 40, 40);
        creatushabi.add(btn64);
        bp3.add(btn64);
        
        JButton btn65 = new JButton();
        btn65.setBounds(440, 300, 40, 40);
        creatushabi.add(btn65);
        bp3.add(btn65);
        
        JButton btn66 = new JButton();
        btn66.setBounds(480, 300, 40, 40);
        creatushabi.add(btn66);
        bp3.add(btn66);
        
        JComboBox combo1 = new JComboBox();
        combo1.setBounds(20, 40, 100, 20);
        creatushabi.add(combo1);
        
        JComboBox combo2 = new JComboBox();
        combo2.setBounds(220, 40, 100, 20);
        creatushabi.add(combo2);
        
        JComboBox combo3 = new JComboBox();
        combo3.setBounds(420, 40, 100, 20);
        creatushabi.add(combo3);
        
        JButton btn = new JButton("Back");
        btn.setBounds(10,360,100,20);
        creatushabi.add(btn);
        
        llenarcombos(combo1,"select distinct(clase) from habilidades");
            
        
        combo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    combo2.removeAllItems();
                    llenarcombos(combo2,"select distinct(clase) from habilidades where clase != \'"+combo1.getSelectedItem().toString()+"\'");
                    if(combo1.getSelectedItem().toString().equals("Battlerage")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("bp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("b"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        }  
                    }
                    else if(combo1.getSelectedItem().toString().equals("Auramancy")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("ap"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("a"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Witchcraft")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("wp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("w"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Defense")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("dp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("d"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Occultism")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("op"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("o"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Sorcery")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("sop"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("so"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Songcraft")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("sonp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("son"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Archery")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("arp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("ar"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Shadowplay")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("sp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("s"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else if(combo1.getSelectedItem().toString().equals("Vitalism")){
                        for (int i = 0; i < bp.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("vp"+(i+1)+".JPG"));
                            bp.get(i).setIcon(image1);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            ImageIcon image1 = new ImageIcon(getClass().getResource("v"+(i+1)+".JPG"));
                            botones.get(i).setIcon(image1);
                        } 
                    }
                    else{
                        for (int i = 0; i < bp.size(); i++) {
                            bp.get(i).setIcon(null);
                        }
                        for (int i = 0; i < botones.size(); i++) {
                            botones.get(i).setIcon(null);
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        combo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    combo3.removeAllItems();
                    if(combo2.getSelectedItem()!=null){
                        llenarcombos(combo3,"select distinct(clase) from habilidades where clase != \'"+combo1.getSelectedItem().toString()+"\' and clase != \'"+combo2.getSelectedItem().toString()+"\'");
                        if(combo2.getSelectedItem().toString().equals("Battlerage")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("bp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("b"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            }  
                        }
                        else if(combo2.getSelectedItem().toString().equals("Auramancy")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("ap"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("a"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Witchcraft")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("wp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("w"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Defense")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("dp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("d"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Occultism")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("op"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("o"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Sorcery")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sop"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("so"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Songcraft")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sonp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("son"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Archery")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("arp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("ar"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Shadowplay")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("s"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo2.getSelectedItem().toString().equals("Vitalism")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("vp"+(i+1)+".JPG"));
                                bp2.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("v"+(i+1)+".JPG"));
                                botones2.get(i).setIcon(image1);
                            } 
                        }
                        else{
                            for (int i = 0; i < bp.size(); i++) {
                                bp2.get(i).setIcon(null);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                botones2.get(i).setIcon(null);
                            } 
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        combo3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                    if(combo3.getSelectedItem()!=null){
                        if(combo3.getSelectedItem()!=null){
                            con.opencon();
                            try {
                                //con.consultar3("select clase from clases where clase1 = \'"+combo1.getSelectedItem()+"\' and clase2 = \'"+combo2.getSelectedItem()+"\' and clase3 = \'"+combo3.getSelectedItem()+"\'");
                                con.consultar3("select clase from clases where clase1 = \'"+combo1.getSelectedItem()+"\' and clase2 = \'"+combo2.getSelectedItem()+"\' and clase3 = \'"+combo3.getSelectedItem()+"\' or clase1 = \'"+combo2.getSelectedItem()+"\' and clase2 = \'"+combo1.getSelectedItem()+"\' and clase3 = \'"+combo3.getSelectedItem()+"\' or clase1 = \'"+combo3.getSelectedItem()+"\' and clase2 = \'"+combo2.getSelectedItem()+"\' and clase3 = \'"+combo1.getSelectedItem()+"\' or clase1= \'"+combo3.getSelectedItem()+"\' and clase2 = \'"+combo1.getSelectedItem()+"\' and clase3 = \'"+combo2.getSelectedItem()+"\' or clase1 = \'"+combo1.getSelectedItem()+"\' and clase2 = \'"+combo3.getSelectedItem()+"\' and clase3 = \'"+combo2.getSelectedItem()+"\' or clase1 = \'"+combo2.getSelectedItem()+"\' and clase2 = \'"+combo3.getSelectedItem()+"\' and clase3 = \'"+combo1.getSelectedItem()+"\'");
                                con.closecon();  
                                jl2.setText(con.datos2.get(0));
                            } catch (SQLException ex) {
                                Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                        else{
                            jl2.setText("");
                        }
                        if(combo3.getSelectedItem().toString().equals("Battlerage")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("bp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("b"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            }  
                        }
                        else if(combo3.getSelectedItem().toString().equals("Auramancy")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("ap"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("a"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Witchcraft")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("wp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("w"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Defense")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("dp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("d"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Occultism")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("op"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("o"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Sorcery")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sop"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("so"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Songcraft")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sonp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("son"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Archery")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("arp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("ar"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Shadowplay")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("sp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("s"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else if(combo3.getSelectedItem().toString().equals("Vitalism")){
                            for (int i = 0; i < bp.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("vp"+(i+1)+".JPG"));
                                bp3.get(i).setIcon(image1);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                ImageIcon image1 = new ImageIcon(getClass().getResource("v"+(i+1)+".JPG"));
                                botones3.get(i).setIcon(image1);
                            } 
                        }
                        else{
                            for (int i = 0; i < bp.size(); i++) {
                                bp3.get(i).setIcon(null);
                            }
                            for (int i = 0; i < botones.size(); i++) {
                                botones3.get(i).setIcon(null);
                            } 
                        }
                    }
            }
        });
        btnsave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                con.opencon();
                try {
                    con.insertar("insert into builds(iduser,build) VALUES("+iduser+",'"+jl2.getText()+"')");
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    con.closecon();
                } catch (SQLException ex) {
                    Logger.getLogger(apparche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(inicio);   
            }
        }); 
    }
    public void creatuequipo(){
        creatuequipo = new JPanel();
        creatuequipo.setBounds(0, 0, 600, 450);
        creatuequipo.setLayout(null);
        
        JButton btn1 = new JButton();
        btn1.setBounds(10,10,40,40);
        creatuequipo.add(btn1);
        
        JButton btn2 = new JButton();
        btn2.setBounds(10,50,40,40);
        creatuequipo.add(btn2);
        
        JButton btn3 = new JButton();
        btn3.setBounds(10,90,40,40);
        creatuequipo.add(btn3);
        
        JButton btn4 = new JButton();
        btn4.setBounds(10,130,40,40);
        creatuequipo.add(btn4);
        
        JButton btn5 = new JButton();
        btn5.setBounds(10,170,40,40);
        creatuequipo.add(btn5);
        
        JButton btn6 = new JButton();
        btn6.setBounds(10,210,40,40);
        creatuequipo.add(btn6);
        
        JButton btn7 = new JButton();
        btn7.setBounds(10,250,40,40);
        creatuequipo.add(btn7);
        
        JButton btn8 = new JButton();
        btn8.setBounds(10,290,40,40);
        creatuequipo.add(btn8);
        
        JButton btn9 = new JButton();
        btn9.setBounds(200,10,40,40);
        creatuequipo.add(btn9);
        
        JButton btn10 = new JButton();
        btn10.setBounds(200,50,40,40);
        creatuequipo.add(btn10);
        
        JButton btn11 = new JButton();
        btn11.setBounds(200,90,40,40);
        creatuequipo.add(btn11);
        
        JButton btn12 = new JButton();
        btn12.setBounds(200,130,40,40);
        creatuequipo.add(btn12);
        
        JButton btn13 = new JButton();
        btn13.setBounds(200,170,40,40);
        creatuequipo.add(btn13);
        
        JButton btn14 = new JButton();
        btn14.setBounds(200,210,40,40);
        creatuequipo.add(btn14);
        
        JButton btn15 = new JButton();
        btn15.setBounds(200,250,40,40);
        creatuequipo.add(btn15);
        
        JButton btn16 = new JButton();
        btn16.setBounds(200,290,40,40);
        creatuequipo.add(btn16);
        
        JButton btn17 = new JButton();
        btn17.setBounds(200,330,40,40);
        creatuequipo.add(btn17);
        
        JButton btn = new JButton("Back");
        btn.setBounds(10,350,120,20);
        creatuequipo.add(btn);
        
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                base.setContentPane(inicio);   
            }
        });
    }
    public static void main(String[] args) throws SQLException {
        apparche app = new apparche();
    }
}
