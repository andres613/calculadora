package interfaz;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Silabacion.OperacionesSilabacion;
import Silabacion.Alfabeto;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Silabacion.GeneradorAleatorio;


public class UI_Silabacion extends javax.swing.JFrame {
    
    OperacionesSilabacion operacionessilabacion = new OperacionesSilabacion();
    Alfabeto alfabeto = new Alfabeto();
    GeneradorAleatorio generar = new GeneradorAleatorio();
    String cadena_alfabeto, palabra_silabada = "";
    boolean si_palabra = false;
    
    public UI_Silabacion() {
        initComponents();
        //Título de la ventana
        this.setTitle("Silabacion Aplicacion v 1.0.19");
        //Centrar la ventana
        this.setLocationRelativeTo(null);
        //Impedir redimensionado
        this.setResizable(false);
        areaTextoSalida.setEditable(false);
        alfabeto.generarAlfabeto();
        //Agregar la imagen a jLabel1 contenido en jPanel3
        //ImageIcon image = new ImageIcon("/home/debian/Desarrollo/NetBeans/NetBeansProjects/SILABACION/recursos/silabayacento.png");
        ImageIcon image = new ImageIcon("./src/recursos/silabayacento.png");
        jLabel1.setIcon(image);
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoTextoEntrada = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTextoSalida = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("UI_Silabacion"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoTextoEntrada)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoTextoEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        areaTextoSalida.setColumns(20);
        areaTextoSalida.setRows(5);
        jScrollPane2.setViewportView(areaTextoSalida);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnalizar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnSalir)
                    .addComponent(btnGenerar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
          this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        cadena_alfabeto = alfabeto.getCadena_alfabeto();
        // se crea un arreglo y se guardan las vocales en la primera posicion y el alfabeto en la segunda
        String [] arreglo_vocal_consonante = {alfabeto.getCadena_vocal(),alfabeto.getCadena_consonante()};
        //CVerifica que haya contenido dentro del campo para ingresar la palabra
        if(campoTextoEntrada.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor ingrese una palabra");
            campoTextoEntrada.requestFocus();
            areaTextoSalida.setText("");
        }//Si hay contenido en el campo de ingreso de la palabra...
        else{
            /*Se comprueba si lo ingresado es una palabra valida (si_palabra) retornando un true si es palabra o false si no es palabra.
            Téngase en cuenta, que la aplicación determina que no es palabra cuando no hay vocales, o cuando hay caracteres
            que no están contenidos en el alfabeto.
            trim() y .toLowerCase() el primero quita los espacios antes y despues mientras el segundo pasa todo la capturado a minúsculas*/
            si_palabra = 
                    operacionessilabacion.operacionesSilabacion(campoTextoEntrada.getText().trim().toLowerCase(),
                            cadena_alfabeto, arreglo_vocal_consonante);
            if(si_palabra){
                // Se vá a donde se almacenó la palabra, se captura y se guarda en palabra_silabada y se imprime la variable
                palabra_silabada = operacionessilabacion.getPalabraSilabada();
                areaTextoSalida.setText(palabra_silabada);
            }else{
                areaTextoSalida.setText("LA PALABRA INGRESADA CONTIENE NUMEROS, ESPACIOS,\n"
                        + "CARACTERES NO VALIDOS O NO TIENE VOCALES\n\n"
                        + "¡Por favor ingrese una palabra sin números, ni espacios!");
            }
            campoTextoEntrada.requestFocus();
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        campoTextoEntrada.setText("");
        areaTextoSalida.setText("");
        campoTextoEntrada.requestFocus();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    String [] palabra_tipo = {"",""};
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        campoTextoEntrada.setText("");
        areaTextoSalida.setText("");
        campoTextoEntrada.requestFocus();
        cadena_alfabeto = alfabeto.getCadena_alfabeto();
        String [] arreglo_vocal_consonante = {alfabeto.getCadena_vocal(),alfabeto.getCadena_consonante()};
        /* El método GeneradorAleatorio(), lo que hace es generar un número entre 0 y n, el cual será el número de línea.
        luego una variable trae lo contenido en ese número de línea (variable palabra) y realiza el proceso de silabación de forma automática*/
        try {
            palabra_tipo = generar.GenerarAleatoriamente('s');
        } catch (IOException ex) {Logger.getLogger(UI_Silabacion.class.getName()).log(Level.SEVERE, null, ex);}
        operacionessilabacion.operacionesSilabacion(palabra_tipo[0], cadena_alfabeto, arreglo_vocal_consonante);
        palabra_silabada = operacionessilabacion.getPalabraSilabada();
        campoTextoEntrada.setText(palabra_tipo[0]);
        areaTextoSalida.setText(palabra_silabada);
    }//GEN-LAST:event_btnGenerarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTextoSalida;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JTextField campoTextoEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
