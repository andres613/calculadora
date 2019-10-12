package interfaz;

import accionesEventosRecursos.Registros;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Mensajes extends javax.swing.JFrame implements KeyListener{
    
    Registros registros = new Registros();
    //Juego juego = new Juego();
    //Menu menu = new Menu();
    
    String [] mensaje = {"","",""};
    String encabezado_estadisticas = "Tiempo            Fecha              Hora            Nombre\n";

//Variables de temporizador
    Timer timer_tempo;
    int tiempo, seg=0,cseg=0, opcion;
    String msg, texto_temporizador="";
    boolean [] pausa_mensaje = {false,false,false};
    boolean salirmenu = false;
    int contador_tareas;
    
    public Mensajes() {/*
        dispose();
        setUndecorated(true);/*Quita la barra de opcion de minimizar, restaurar y cerrar
        sin embargo, la opcion debe estar precedida del dispose()*/
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        //Título de la ventana
        this.setTitle("Mensaje");
        addKeyListener((KeyListener) this);
        //Centrar la ventana
        this.setLocationRelativeTo(null);
        //Impedir redimensionado
        this.setResizable(false);
        this.setAlwaysOnTop(true); /*Esto permite que el nuevo jFrame sea un modal, es decir,
        que siempre esté por encima del otro frame*/
        txtTituloMenu.setEditable(false);
        lblSalir.setEnabled(false);
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (salirmenu) {
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                this.pausa_mensaje[2] = false;
                salirmenu = false;
                lblSalir.setEnabled(false);
                dispose();
            }
        }
    }
    @Override    public void keyTyped(KeyEvent e) {}
    @Override    public void keyReleased(KeyEvent e) {}
    
    public void setTemporizador(int tiempo, int seg, int cseg, int opcion){
       this.tiempo = tiempo; this.seg = seg; this.cseg = cseg; this.opcion = opcion;
    }
    
   
    public void mostrarMensajes(){
        mensaje = Mensajes(opcion);
        txtTituloMenu.setText(mensaje[0]);
        if(opcion==105){
            txtaMensaje1.setFont(new Font("Calibri", 0, 12));
            txtaMensaje1.setText(mensaje[1]);
        }else{
            txtaMensaje1.setText(mensaje[1]);
        }
        txtaMensaje1.setText(mensaje[1]);
        lblSalir.setText(mensaje[2]);
    }
    
    
    //Métodos de ejecución del cronómetro
    public void Temporizador(){
        timer_tempo = new Timer(10, accion_temporizador);
        mostrarMensajes();
        timer_tempo.start();
    }
    
    ActionListener accion_temporizador = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cseg--;
            if(cseg==-1){
                cseg=99;seg--;
            }
            if(seg==0){
                cseg=0;
                timer_tempo.stop();
                salirmenu = true;
                lblSalir.setEnabled(true);
                requestFocus();
            }
            texto_temporizador = (seg<=9?"0":"")+seg+":"+(cseg<=9?"0":"")+cseg;
            txtTemporizador.setText(texto_temporizador);
        }    
    };
    
    
    public String [] mensajesMenu(int opcion){
        switch (opcion){
            case 1:
                mensaje[0] = "Ingrese nombre";
                mensaje[1] = "Por favor, ingrese su nombres";
                break;
            case 2:
                mensaje[0] = "Seleccione su personaje";
                mensaje[1] = "Para seleccionar su personaje, oprima las teclas \"Flecha derecha\" o \"Flecha izquierda\"";
                break;
            case 3:
                mensaje[0] = "Jugar";
                mensaje[1] = "Oprima la tecla \"Enter\" para iniciar el juego";
                break;
            case 4:
                mensaje[0] = "Estadísticas";
                mensaje[1] = "Vea las estadísticas de los jugadores: nombres y tiempos";
                break;
            case 5:
                mensaje[0] = "Ayuda";
                mensaje[1] = "Ofrece ayuda de cómo jugar";
                break;
            case 6:
                mensaje[0] = "Créditos";
                mensaje[1] = "Personas que hicieron posible la realización de este juego";
                break;
            case 7:
                mensaje[0] = "Salir";
                mensaje[1] = "Oprima la tecla \"Enter\" para salir";
                break;
            case 41:
                mensaje[0] = "Estadísticas";
                mensaje[1] = registros.mostrarEstadisticas();
                mensaje[2] = "Para salir de las estadísticas oprima la tecla \"Enter\"";
                break;
            case 51:
                mensaje = Mensajes(opcion);
                break;
            case 61:
                mensaje[0] = "Créditos";
                mensaje[1] = "    Este proyecto fué diseñado y construido por: \n\n    Andres E. Restrepo F. \n    y \n    J. Agustín Palomino P. \n\n    Armenia\n    2019";
                mensaje[2] = "Para salir de los créditos oprima la tecla \"Enter\"";
                break;
                
            case 100:
                mensaje = Mensajes(opcion);
                break;
            case 101:
                mensaje = Mensajes(opcion);
                break;
           case 102:
                mensaje = Mensajes(opcion);
                break;
           case 103:
                mensaje = Mensajes(opcion);
                break;
           case 104:
                mensaje = Mensajes(opcion);
                break;
           case 105:
                mensaje = Mensajes(opcion);
                break;
        }
        return mensaje;
    }

    public void setContador_tareas(int contador_tareas){this.contador_tareas = contador_tareas;}

    public void setMsg(String msg) {this.msg = msg;}
    public String getMsg() {return msg;}
    
    
    
    public String [] Mensajes(int opcion){
        switch (opcion){
            case 51:
                mensaje[0] = "¿Como Jugar?";
                mensaje[1] = " * Use las teclas de desplazamiento para mover al personaje\n"
                        + "\n * Oprima la tecla \"Enter\" para detener el juego"
                        + "\n   y/o poder volver al menú principal";
                mensaje[2] = "Para salir de las ayudas oprima la tecla \"Enter\"";
                break;
            case 100:
                mensaje[0] = "Las SÍLABAS ";
                mensaje[1] = "Una sílaba es una parte de una palabra que se\npronuncia en una sola emisión o golpe de voz.\nPor ejemplo, la palabra \"amigo\" puede \nsilabarse en \"a  mi  go\" o la palabra\n\"perro\" puede silabarse en \"pe  rro\", y así\ncualquier palabra del idioma español\npuede descomponerse en sílabas.";
                mensaje[2] = "Para salir oprima la tecla \"Enter\"";
                break;
            case 101:
                mensaje[0] = "Tarea # "+(contador_tareas-100);
                mensaje[1] = "Dirijase hacia el occidente vaya y recoja\nel guión \"-\" de color rojo y cumpla con la tarea\npropuesta.";
                mensaje[2] = "Para salir oprima la tecla \"Enter\"";
                break;
            case 102:
                mensaje[0] = "Tarea # "+(contador_tareas-100);
                mensaje[1] = "Como ya tiene el poder del guión \"-\" de\ncolor rojo, puede cumplir la primera misión que\nse le está señalando a la derecha del mapa con\nuna flecha hacia abajo.";
                mensaje[2] = "Para salir oprima la tecla \"Enter\"";
                msg = "Utilice la \"-\" de su teclado para separar en sílabas la palabra que aparece en el recuadro de abajo y luego presione \"Enter\". Por ejemplo: \"camisa\" en \"ca-mi-sa\". Si desea salir de la prueba, presione la tecla \"Esc\", y para realizar la prueba, oprima la \"S\" de su teclado. Recuerde que no es posible continuar con el juego si no ha superado esta prueba.";
                setMsg(msg);
                break;
           case 104:
                mensaje[0] = "Tarea : Aprende a tildar.";
                mensaje[1] = "Diríjase nuevamente hacia el occidente y tome la\ntilde (´), ésta será su nueva habilidad para cumplir\nla próxima misión.";
                mensaje[2] = "Para salir oprima la tecla \"Enter\"";
                msg = "Utilice las teclas \"A\", \"G\", \"E\" y \"S\" de su teclado para indicar si la palabra que aparece debajo es aguda, grave, esdrújula o sobreesdrújula respectivamente. Si desea salir de la prueba, presione la tecla \"Esc\", y para realizar la prueba, oprima la \"T\" de su teclado. Recuerde que no es posible continuar con el juego si no ha superado esta prueba.";
                setMsg(msg);
                break;
          case 105:
                mensaje[0] = "Tarea: clasificar palabras.";
                mensaje[1] = "Felicidades!! Ya sabes lo que es una sílaba.\nLas palabras se clasifican según el acento, es decir, el lugar de la\n"
                        + "palabra donde se hace mas fuerza al pronunciarla.\n"
                        + "Las palabras agudas se acentúan en la última sílaba, pero solo\nse tildan cuando terminan en \"n\", \"s\" o vocal.\n"
                        + "Las palabras graves se acentúan en la penúltima sílaba, pero solo se\n"
                        + "tildan cuando terminan en letras diferentes a \"n\", \"s\" o vocal.\n"
                        + "Las palabras esdrújulas, tienen el acento en la antepenúltima sílaba;\ntodas se tildan\n"
                        + "Las palabras sobreesdrújulas, tienen el acento en la\ntrasantepenúltima sílaba; todas se tildan";
                mensaje[2] = "Para salir oprima la tecla \"Enter\"";
                msg = "Utilice las teclas \"A\", \"G\", \"E\" y \"S\" de su teclado para indicar si la palabra que aparece debajo es aguda, grave, esdrújula o sobreesdrújula. Si desea salir de la prueba, presione la tecla \"Esc\", y para realizar la prueba, oprima la \"T\" de su teclado. Recuerde que no es posible continuar con el juego si no ha superado esta prueba.";
                setMsg(msg);
                break;
        }
        return mensaje;
    }
    
    
    public void setPausaMensaje(boolean [] pausa_mensaje){
        this.pausa_mensaje = pausa_mensaje;
    }
    public boolean [] getPausaMensaje(){
        return pausa_mensaje;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTituloMenu = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtaMensaje1 = new javax.swing.JTextArea();
        txtTemporizador = new javax.swing.JTextField();
        lblSalir = new javax.swing.JLabel();
        lblFondoMensajes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTituloMenu.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        txtTituloMenu.setForeground(new java.awt.Color(255, 255, 0));
        txtTituloMenu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTituloMenu.setText("jTextField1");
        txtTituloMenu.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtTituloMenu.setOpaque(false);
        getContentPane().add(txtTituloMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 370, 30));

        txtaMensaje1.setEditable(false);
        txtaMensaje1.setBackground(new java.awt.Color(0, 0, 0));
        txtaMensaje1.setColumns(20);
        txtaMensaje1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtaMensaje1.setForeground(new java.awt.Color(255, 255, 255));
        txtaMensaje1.setRows(5);
        jScrollPane3.setViewportView(txtaMensaje1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 400, 190));

        txtTemporizador.setEditable(false);
        txtTemporizador.setBackground(new java.awt.Color(0, 0, 0));
        txtTemporizador.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        txtTemporizador.setForeground(new java.awt.Color(0, 0, 204));
        txtTemporizador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTemporizador.setText("00:00");
        txtTemporizador.setEnabled(false);
        getContentPane().add(txtTemporizador, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 230, 30));

        lblSalir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSalir.setForeground(new java.awt.Color(255, 51, 51));
        lblSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 380, 30));

        lblFondoMensajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Escenarios/thumb-1920-697507.jpg"))); // NOI18N
        getContentPane().add(lblFondoMensajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFondoMensajes;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JTextField txtTemporizador;
    private javax.swing.JTextField txtTituloMenu;
    private javax.swing.JTextArea txtaMensaje1;
    // End of variables declaration//GEN-END:variables

    
}
/*
    public String [] mensajes(String msn){
        switch (msn){
            case "uno":
                mensaje[0] = "Diríjase hacia el oriente para saber cual será su primera misión.";
                break;
            case "dos":
                mensaje[0] = "En esta oportunidad veremos que es una sílaba \ny aprenderemos a separar palabras en sílabas.";
                break;
            case "guion":
                mensaje[0] = "El guión lo usaremos para separar las palabras en sílabas.";
                break;
        }        
        return mensaje;
    }*/