package interfaz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Menu extends javax.swing.JFrame implements KeyListener{
       
    Mensajes mensajes = new Mensajes();
    
    int opcion = 1;//Asigna un número a la opción en la que se está
    char personaje_seleccionado = 'l';//Asigna "l" a nino y "r" a nina
    boolean en_opcion = false;//Determina si el usuario se encuentra dentro de una opción
    
    public Menu() {
        dispose();
        setUndecorated(true);/*Quita la barra de opcion de minimizar, restaurar y cerrar
        sin embargo, la opcion debe estar precedida del dispose()*/
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        addKeyListener((KeyListener) this);
        txtaMensajeEnMenu.hide();
        lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
        lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[1]+"</html>");
        lblSelPerNino.setEnabled(false);
        lblSelPerNina.setEnabled(false);
        lblJugar.setEnabled(false);
        lblEstadisticas.setEnabled(false);
        lblAyuda.setEnabled(false);
        lblCreditos.setEnabled(false);
        lblSalir.setEnabled(false);
    }
    
        
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            if(en_opcion){}
            else{
                if(opcion<7){
                    opcion++;
                    accionesFlechasMenu();
                }
                else{}
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_UP){
            if(en_opcion){}
            else{
                if(opcion>1){
                    opcion--;
                    accionesFlechasMenu();
                }
                else{}
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_RIGHT){
            if(opcion==2){
                lblSelPerNina.setIcon(new javax.swing.ImageIcon("./src/recursos/Personaje/nina_menu.png"));
                lblSelPerNino.setIcon(new javax.swing.ImageIcon("./src/recursos/Personaje/nino/nino_down_stop.png"));
                personaje_seleccionado = 'r';
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_LEFT){
            if(opcion==2){
                lblSelPerNina.setIcon(new javax.swing.ImageIcon("./src/recursos/Personaje/nina/nina_down_stop.png"));
                lblSelPerNino.setIcon(new javax.swing.ImageIcon("./src/recursos/Personaje/nino_menu.png"));
                personaje_seleccionado = 'l';
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            accionesEnterMenu();
        }
    }
    
    
    public void accionesFlechasMenu(){
        switch (opcion){
            case 1:
                txtNombreUsuario.setEnabled(true);
                txtNombreUsuario.requestFocus();
                lblSelPerNino.setEnabled(false);
                lblSelPerNina.setEnabled(false);
                break;
            case 2:
                txtNombreUsuario.setEnabled(false);
                lblSelPerNino.setEnabled(true);
                lblSelPerNina.setEnabled(true);
                lblJugar.setEnabled(false);
                lblEstadisticas.setEnabled(false);
                break;
            case 3:
                lblJugar.setEnabled(true);
                lblEstadisticas.setEnabled(false);
                lblCreditos.setEnabled(false);
                lblSelPerNino.setEnabled(false);
                lblSelPerNina.setEnabled(false);
                break;
            case 4:
                lblJugar.setEnabled(false);
                lblEstadisticas.setEnabled(true);
                lblAyuda.setEnabled(false);
                break;
            case 5:
                lblEstadisticas.setEnabled(false);
                lblAyuda.setEnabled(true);
                lblCreditos.setEnabled(false);
                break;
            case 6:
                lblAyuda.setEnabled(false);
                lblCreditos.setEnabled(true);
                lblSalir.setEnabled(false);
                break;
            case 7:
                lblSalir.setEnabled(true);
                lblCreditos.setEnabled(false);
                break;
        }
        lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
        lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[1]+"</html>");
    }
    
    
    public void accionesEnterMenu(){
        switch (opcion){
            case 2:
                opcion++;
                accionesFlechasMenu();
                break;
            case 3:
                if (txtNombreUsuario.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre de usuario!");
                    opcion = 1;
                    lblJugar.setEnabled(false);
                    txtNombreUsuario.setEnabled(true);
                    txtNombreUsuario.requestFocus();
                } else {
                    dispose();
                    Juego juego = new Juego();
                    juego.setPersonaje(personaje_seleccionado, txtNombreUsuario.getText());
                    juego.txtEntrada.setEnabled(false);
                    juego.setVisible(true);
                }
                break;
            case 4:
                if(en_opcion){
                    en_opcion = false;
                    txtaMensajeEnMenu.setText("");
                    txtNombreUsuario.setVisible(true);
                    lblSelPerNino.setVisible(true);
                    lblSelPerNina.setVisible(true);
                    lblJugar.setVisible(true);
                    lblEstadisticas.setVisible(true);
                    lblAyuda.setVisible(true);
                    lblCreditos.setVisible(true);
                    lblSalir.setVisible(true);
                    txtaMensajeEnMenu.setVisible(false);
                }else{
                    opcion = 41;
                    txtNombreUsuario.setVisible(false);
                    lblSelPerNino.setVisible(false);
                    lblSelPerNina.setVisible(false);
                    lblJugar.setVisible(false);
                    lblEstadisticas.setVisible(false);
                    lblAyuda.setVisible(false);
                    lblCreditos.setVisible(false);
                    lblSalir.setVisible(false);
                    txtaMensajeEnMenu.setVisible(true);
                    txtaMensajeEnMenu.setFocusable(true);
                    lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
                    if(mensajes.mensajesMenu(opcion)[1]==""){
                        txtaMensajeEnMenu.setFont(new java.awt.Font("Arial", 0, 30));
                        txtaMensajeEnMenu.setText("No hay estadísticas");
                    }else{
                        txtaMensajeEnMenu.setFont(new java.awt.Font("Arial", 0, 12));
                        txtaMensajeEnMenu.setText(mensajes.encabezado_estadisticas+mensajes.mensajesMenu(opcion)[1]);
                    }
                    lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[2]+"</html>");
                    opcion = 4;
                    en_opcion = true;
                }
                break;
            case 5:
                if(en_opcion){
                    en_opcion = false;
                    txtaMensajeEnMenu.setText("");
                    txtNombreUsuario.setVisible(true);
                    lblSelPerNino.setVisible(true);
                    lblSelPerNina.setVisible(true);
                    lblJugar.setVisible(true);
                    lblEstadisticas.setVisible(true);
                    lblAyuda.setVisible(true);
                    lblCreditos.setVisible(true);
                    lblSalir.setVisible(true);
                    txtaMensajeEnMenu.setVisible(false);
                }else{
                    opcion = 51;
                    txtNombreUsuario.setVisible(false);
                    lblSelPerNino.setVisible(false);
                    lblSelPerNina.setVisible(false);
                    lblJugar.setVisible(false);
                    lblEstadisticas.setVisible(false);
                    lblAyuda.setVisible(false);
                    lblCreditos.setVisible(false);
                    lblSalir.setVisible(false);
                    txtaMensajeEnMenu.setVisible(true);
                    txtaMensajeEnMenu.setFocusable(true);
                    lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
                    txtaMensajeEnMenu.setFont(new java.awt.Font("Arial", 0, 14));
                    txtaMensajeEnMenu.setText(mensajes.mensajesMenu(opcion)[1]);
                    lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[2]+"</html>");
                    opcion = 5;
                    en_opcion = true;
                }
                break;
            case 6:
                if(en_opcion){
                    en_opcion = false;
                    txtaMensajeEnMenu.setText("");
                    txtNombreUsuario.setVisible(true);
                    lblSelPerNino.setVisible(true);
                    lblSelPerNina.setVisible(true);
                    lblJugar.setVisible(true);
                    lblEstadisticas.setVisible(true);
                    lblAyuda.setVisible(true);
                    lblCreditos.setVisible(true);
                    lblSalir.setVisible(true);
                    txtaMensajeEnMenu.setVisible(false);
                }else{
                    opcion = 61;
                    txtNombreUsuario.setVisible(false);
                    lblSelPerNino.setVisible(false);
                    lblSelPerNina.setVisible(false);
                    lblJugar.setVisible(false);
                    lblEstadisticas.setVisible(false);
                    lblAyuda.setVisible(false);
                    lblCreditos.setVisible(false);
                    lblSalir.setVisible(false);
                    txtaMensajeEnMenu.setVisible(true);
                    txtaMensajeEnMenu.setFocusable(true);
                    lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
                    txtaMensajeEnMenu.setFont(new java.awt.Font("Arial", 0, 20));
                    txtaMensajeEnMenu.setText(mensajes.mensajesMenu(opcion)[1]);
                    lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[2]+"</html>");
                    opcion = 6;
                    en_opcion = true;
                }
                break;
            case 7:
                if(JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?\n", "Salir", 2)==0){
                    System.exit(0);
                }
                break;
        }
        //Para que cada vez que salga de una opción, muestre los mensajes correctos
        if(en_opcion){
        }else{
            lblTituloMenu.setText("<html>"+mensajes.mensajesMenu(opcion)[0]+"</html>");
            lblIndicaciones.setText("<html>"+mensajes.mensajesMenu(opcion)[1]+"</html>");
        }
    }
    
    
    @Override   public void keyReleased(KeyEvent e) {}
    @Override   public void keyTyped(KeyEvent e) {}


    private void txtNombreUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtNombreUsuario.setEnabled(false);
            this.requestFocus();
            opcion++;
            accionesFlechasMenu();
        }
    }//GEN-LAST:event_txtNombreUsuarioKeyPressed

    int limite = 30;//Será el número límite de caracteres permitidos para el usuario
    private void txtNombreUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioKeyTyped
        if (txtNombreUsuario.getText().length() >= limite)//Se pregunta si se ha llegado a ese límite de caracteres
            evt.consume();//Se eliminan los caracteres al superar dicho límite
    }//GEN-LAST:event_txtNombreUsuarioKeyTyped

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblTituloMenu = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblSelPerNina = new javax.swing.JLabel();
        lblSelPerNino = new javax.swing.JLabel();
        lblJugar = new javax.swing.JLabel();
        lblEstadisticas = new javax.swing.JLabel();
        lblCreditos = new javax.swing.JLabel();
        lblAyuda = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaMensajeEnMenu = new javax.swing.JTextArea();
        lblIndicaciones = new javax.swing.JLabel();
        lblFondoMenu = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloMenu.setBackground(new java.awt.Color(0, 0, 0));
        lblTituloMenu.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblTituloMenu.setForeground(new java.awt.Color(255, 0, 0));
        lblTituloMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloMenu.setOpaque(true);
        getContentPane().add(lblTituloMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 50));

        txtNombreUsuario.setBackground(new java.awt.Color(0, 0, 0));
        txtNombreUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioKeyPressed(evt);
            }
        });
        getContentPane().add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 330, 30));

        lblSelPerNina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelPerNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Personaje/nina/nina_down_stop.png"))); // NOI18N
        getContentPane().add(lblSelPerNina, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        lblSelPerNino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelPerNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Personaje/nino_menu.png"))); // NOI18N
        getContentPane().add(lblSelPerNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        lblJugar.setBackground(new java.awt.Color(0, 0, 0));
        lblJugar.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblJugar.setForeground(new java.awt.Color(255, 0, 0));
        lblJugar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJugar.setText("Jugar");
        lblJugar.setOpaque(true);
        getContentPane().add(lblJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 160, 30));

        lblEstadisticas.setBackground(new java.awt.Color(0, 0, 0));
        lblEstadisticas.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblEstadisticas.setForeground(new java.awt.Color(255, 0, 0));
        lblEstadisticas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstadisticas.setText("Estadísticas");
        lblEstadisticas.setOpaque(true);
        getContentPane().add(lblEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 160, 30));

        lblCreditos.setBackground(new java.awt.Color(0, 0, 0));
        lblCreditos.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblCreditos.setForeground(new java.awt.Color(255, 0, 0));
        lblCreditos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreditos.setText("Créditos");
        lblCreditos.setOpaque(true);
        getContentPane().add(lblCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 160, 30));

        lblAyuda.setBackground(new java.awt.Color(0, 0, 0));
        lblAyuda.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblAyuda.setForeground(new java.awt.Color(255, 0, 0));
        lblAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAyuda.setText("Ayuda");
        lblAyuda.setOpaque(true);
        getContentPane().add(lblAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 160, 30));

        lblSalir.setBackground(new java.awt.Color(0, 0, 0));
        lblSalir.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblSalir.setForeground(new java.awt.Color(255, 0, 0));
        lblSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalir.setText("Salir");
        lblSalir.setOpaque(true);
        getContentPane().add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 160, 30));

        txtaMensajeEnMenu.setEditable(false);
        txtaMensajeEnMenu.setBackground(new java.awt.Color(0, 0, 0));
        txtaMensajeEnMenu.setColumns(20);
        txtaMensajeEnMenu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaMensajeEnMenu.setForeground(new java.awt.Color(255, 255, 255));
        txtaMensajeEnMenu.setRows(5);
        jScrollPane2.setViewportView(txtaMensajeEnMenu);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, 290));

        lblIndicaciones.setBackground(new java.awt.Color(0, 0, 0));
        lblIndicaciones.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblIndicaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblIndicaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIndicaciones.setOpaque(true);
        getContentPane().add(lblIndicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 480, 50));

        lblFondoMenu.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        lblFondoMenu.setForeground(new java.awt.Color(255, 0, 0));
        lblFondoMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Escenarios/thumb-1920-697507.jpg"))); // NOI18N
        getContentPane().add(lblFondoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 480, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblCreditos;
    private javax.swing.JLabel lblEstadisticas;
    private javax.swing.JLabel lblFondoMenu;
    private javax.swing.JLabel lblIndicaciones;
    private javax.swing.JLabel lblJugar;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblSelPerNina;
    private javax.swing.JLabel lblSelPerNino;
    private javax.swing.JLabel lblTituloMenu;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextArea txtaMensajeEnMenu;
    // End of variables declaration//GEN-END:variables
    
}
