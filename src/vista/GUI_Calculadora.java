package vista;

import modelo.Operaciones;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author debian
 */
public class GUI_Calculadora extends javax.swing.JFrame{

    Operaciones op = new Operaciones();
    
    boolean operador = false;
    String primera_cifra = "", salida = "";
    String operacion = "";
    float num_uno, num_dos;
    char c;

    /**
     * Creates new form GUI_Calculadora
     */
    public GUI_Calculadora() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        txtsalida.setEnabled(false);
        txt_entrada.setEditable(true);
    }
      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn_ac = new javax.swing.JButton();
        btn_resta = new javax.swing.JButton();
        btn_suma = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn_multiplicacion = new javax.swing.JButton();
        btn_division = new javax.swing.JButton();
        btn_porcentaje = new javax.swing.JButton();
        btn_igual = new javax.swing.JButton();
        btn_punto = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        txt_entrada = new javax.swing.JTextField();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        txtsalida = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KAZIO Original");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        btn_ac.setText("AC");
        btn_ac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ac, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 100, -1));

        btn_resta.setText("-");
        btn_resta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_resta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 50, -1));

        btn_suma.setText("+");
        btn_suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 50, -1));

        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        btn_multiplicacion.setText("x");
        btn_multiplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_multiplicacionActionPerformed(evt);
            }
        });
        jPanel1.add(btn_multiplicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 50, -1));

        btn_division.setText("/");
        btn_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_divisionActionPerformed(evt);
            }
        });
        jPanel1.add(btn_division, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 50, -1));

        btn_porcentaje.setText("%");
        btn_porcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_porcentajeActionPerformed(evt);
            }
        });
        jPanel1.add(btn_porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 50, -1));

        btn_igual.setText("=");
        btn_igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_igualActionPerformed(evt);
            }
        });
        jPanel1.add(btn_igual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 90, -1));

        btn_punto.setText(".");
        btn_punto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_puntoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_punto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 37, -1));

        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        jPanel1.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        txt_entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_entradaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_entradaKeyPressed(evt);
            }
        });
        jPanel1.add(txt_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 40));

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        txtsalida.setEditable(false);
        txtsalida.setBackground(new java.awt.Color(0, 0, 0));
        txtsalida.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtsalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 250, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_entradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_entradaKeyTyped
        char tecla = evt.getKeyChar();//se captura la tecla pulsada para validar que sea correcta
        if (((tecla < '0') || (tecla > '9')) && (tecla != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignora el evento de teclado, es decir, no muestra o admite dicha pulsacion
        }
    }//GEN-LAST:event_txt_entradaKeyTyped

    private void btn_sumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumaActionPerformed
        Operaciones("suma", "+");// se envian el tipo de operación y el operador
    }//GEN-LAST:event_btn_sumaActionPerformed

    private void btn_restaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restaActionPerformed
        Operaciones("resta", "-");
    }//GEN-LAST:event_btn_restaActionPerformed

    private void txt_entradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_entradaKeyPressed
        
    }//GEN-LAST:event_txt_entradaKeyPressed

    private void btn_multiplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_multiplicacionActionPerformed
        Operaciones("multiplicacion", "*");
    }//GEN-LAST:event_btn_multiplicacionActionPerformed

    private void btn_igualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_igualActionPerformed
        if (txt_entrada.getText().equals("")) {//validar que el campo no este vacío para no generar error
        } else {
            try {/*Se valida que hay un número en la entrada de datos*/
                this.num_uno = Float.parseFloat(txt_entrada.getText());
                txtsalida.setText(num_uno + "");
            } catch (NumberFormatException excepcion) {//Para el caso que no sea un número, o sea, que contenga un operador aritmético
                c = txt_entrada.getText().charAt(txt_entrada.getText().length() - 1);
                if (((c < '0') || (c > '9'))) {//se verifica el uĺtimo caracter que no sea un signo
                } else {//Se extraen los valores a operar, y se muestra el resultado de la operación
                    num_dos = Float.parseFloat(txt_entrada.getText().substring(primera_cifra.length(), txt_entrada.getText().length()));
                    salida = op.Operaciones(operacion, num_uno, num_dos) + "";
                    txtsalida.setText(salida);
                    txt_entrada.setText(salida);
                }
            }
        }
    }//GEN-LAST:event_btn_igualActionPerformed

    private void btn_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_divisionActionPerformed
        Operaciones("division", "/");
    }//GEN-LAST:event_btn_divisionActionPerformed

    
    void Operaciones(String operacion, String operador){
        
        if (txt_entrada.getText().equals("")) {//validar que el campo no este vacío para no generar error
            if((txtsalida.getText().equals(""))){// se verifica si existe un valor como resultado para ejecutar la próximaoperación
            } else {
                this.num_uno = Float.parseFloat(txtsalida.getText());
                this.primera_cifra = txtsalida.getText() + operador;
                this.operacion = operacion;
                txt_entrada.setText(txtsalida.getText() + operador);
            }
        } else {
            try {
                this.num_uno = Float.parseFloat(txt_entrada.getText());//se hace la validación como un numero flotante, de serlo, continúa la ejecución
                txt_entrada.setText(num_uno + operador);//toma lo contenido en caja y de adiciona un + para mostrarlo
                primera_cifra = txt_entrada.getText();//lo mostrado mas el + adicional se guarda en primera_cifra
                this.operacion = operacion;//se determina la operación a ejecutar
            } catch (NumberFormatException excepcion) {
                c = txt_entrada.getText().charAt(txt_entrada.getText().length() - 1);/*este segmento es el resultado de que no pasó la prueba de vallidación
            y se obtiene el último caracter para saber si es un operador*/
                if (((c < '0') || (c > '9'))) {//si es verdadero, es un operador
                    txt_entrada.setText(txt_entrada.getText().substring(0, txt_entrada.getText().length() - 1) + operador);//se le asigna el simbolo del operador pulsado
                    this.operacion = operacion;//se asigna la operación a jejecutar
                } else {//en el caso en que c no es un operador, quiere decir, que es un número pero la cadedna no es un número dado que no paso el try
                    num_dos = Float.parseFloat(txt_entrada.getText().substring(primera_cifra.length(), txt_entrada.getText().length()));//se extrae el segundo número quitando primera_cifra
                    salida = op.Operaciones(operacion, num_uno, num_dos) + "";//se operan los dos valores y se retornan para mostrarlos
                    txt_entrada.setText(salida + operador);//se muestra el resultado
                    txtsalida.setText(salida);
                    num_uno = Float.parseFloat(salida);
                    primera_cifra = (num_uno + operador);//se le da el valor al segmento inicial del arreglo para poder extraer el segundo número
                    this.operacion = operacion;//se determina nuevamente el tipo de operación
                }
            }
        }
        /*En principio, todas la operaciones son iguales, salvo el caracter del correspondiente operador */
    }
    
    
    
    
    
    private void btn_acActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acActionPerformed
        num_uno=0;
        num_dos=0;
        txt_entrada.setText("");
        txtsalida.setText("");
    }//GEN-LAST:event_btn_acActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn_puntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_puntoActionPerformed
        /*Se verifica que no sea el segundo signo decimal por número, que no sea un operador o que no sea campo vacía*/
        if (txt_entrada.getText().equals("")) {
        } else {
            c = txt_entrada.getText().charAt(txt_entrada.getText().length() - 1);
            if (((c < '0') || (c > '9'))) {
            } else {
                try {
                    Float.parseFloat(txt_entrada.getText());
                    txt_entrada.setText(txt_entrada.getText() + ".");
                } catch (NumberFormatException excepcion) {
                }
                try {
                    Float.parseFloat(txt_entrada.getText().substring(primera_cifra.length(), txt_entrada.getText().length())+".");
                    txt_entrada.setText(txt_entrada.getText() + ".");
                } catch (NumberFormatException excepcion) {
                }
            }
        }
    }//GEN-LAST:event_btn_puntoActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        txt_entrada.setText(txt_entrada.getText()+"9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn_porcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_porcentajeActionPerformed
        /*B+asicamente se hacen las mismas verificaciones y se corrobora que la operación a realizar sea un producto*/
        if (txt_entrada.getText().equals("")) {
        } else {
            c = txt_entrada.getText().charAt(txt_entrada.getText().length() - 1);
            if (((c < '0') || (c > '9'))) {
            } else {
                if (operacion != "multiplicacion") {
                } else {
                    num_dos = Float.parseFloat(txt_entrada.getText().substring(primera_cifra.length(), txt_entrada.getText().length())+".");
                    txt_entrada.setText(txt_entrada.getText() + "%");
                    operacion = "porcentaje";
                    salida = op.Operaciones(operacion, num_uno, num_dos) + "";
                    num_uno = Float.parseFloat(salida);
                    txt_entrada.setText("");
                    txtsalida.setText(salida);
                }
            }
        }
    }//GEN-LAST:event_btn_porcentajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn0;
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn2;
    public javax.swing.JButton btn3;
    public javax.swing.JButton btn4;
    public javax.swing.JButton btn5;
    public javax.swing.JButton btn6;
    public javax.swing.JButton btn7;
    public javax.swing.JButton btn8;
    public javax.swing.JButton btn9;
    public javax.swing.JButton btn_ac;
    public javax.swing.JButton btn_division;
    public javax.swing.JButton btn_igual;
    public javax.swing.JButton btn_multiplicacion;
    public javax.swing.JButton btn_porcentaje;
    public javax.swing.JButton btn_punto;
    public javax.swing.JButton btn_resta;
    public javax.swing.JButton btn_suma;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField txt_entrada;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables

}
