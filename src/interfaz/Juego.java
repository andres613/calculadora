package interfaz;

import Silabacion.Alfabeto;
import Silabacion.GeneradorAleatorio;
import Silabacion.OperacionesSilabacion;
import accionesEventosRecursos.Recursos;
import accionesEventosRecursos.Registros;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Juego extends javax.swing.JFrame implements KeyListener {
    
    Recursos recursos = new Recursos();
    Registros registros = new Registros();
    Menu menu = new Menu();
    Mensajes mensajes = new Mensajes();
    Alfabeto alfabeto = new Alfabeto();
    GeneradorAleatorio generador = new GeneradorAleatorio();
    OperacionesSilabacion operacionessilabacion = new OperacionesSilabacion();
    
// ninoa es un arreglo de tipo String que almacena la ruta del personaje para cada una de sus posiciones
    String [] ninoa;
    
//Variable que permite pausar el juego
    public boolean [] pausa_mensaje = {false,false,false};   

//Variables de personaje
    String nombre_usuario;
    public final int DESPLAZAMIENTO = 10;//establece velocidad de desplazamiento
    int pos_x, pos_y, 
            height, width;
    char direccion;
    Rectangle personaje,

//Escenario Uno 
            separadorSuperior, separadorIzquierda, separadorInferior, separadorDerecha,
            lago1, lago2, lago3, lago4, lago5, lago6,
            objeto1e1, objeto2e1, objeto3e1, objeto4e1, objeto5e1, objeto6e1, objeto7e1, objeto8e1, objeto9e1, objeto10e1, objeto11e1,
            objeto12e1, objeto13e1, objeto14e1, objeto15e1, objeto16e1, objeto17e1, objeto18e1,
//Escenario Dos
            objeto1e2, objeto2e2, objeto3e2, objeto4e2, objeto5e2, objeto6e2, objeto7e2, objeto8e2, objeto9e2, objeto10e2, objeto11e2,
            objeto12e2,
//Puertas
            puertaDerecha, puertaIzquierda,

//Habilidades y tarea_numero
            habilidad, 
            realizar_tarea;
    int habilidad_numero = 0,
            tarea_numero = 0;
    boolean [] mastarea_mashabili = {true,true};

//Variable que habilita las puertas para cambio de escenario
    boolean habilitar_puertas = true;
    String escenario = "escenariouno";

//colision[] determina en cada indice, si colisiona con objetos, con puertas, tarea o con habilidad respectivamente
    boolean[] colision_ob_pue_tar_hab={false,false,false,false};
    
//Variables empleadas para muestra de mensajes
    int contador_sucesos = 100;
    
//Variables de cronómetro
    Timer timer;
    int h=0,m=0,s=0,cs=0;
    String texto_cronometro="";
    
    /*Se inician componentes, se indica la imposibilidad de redimensionar, se despliega la ventana centrada con respecto a la pantalla,
    se indica que debe estar atenta a las pulsaciones por teclado*/
    public Juego() {
        dispose();
        setUndecorated(true);/*Quita la barra de opcion de minimizar, restaurar y cerrar
        sin embargo, la opcion debe estar precedida del dispose()*/
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        addKeyListener((KeyListener) this);
        lblHabilidad.hide();
        lblTarea.hide();
        lblContinuar.hide();
        lblVolverMenu.hide();
        lblFlechaU.hide();
        lblFlechaD.hide();
        lblFlechaL.hide();
        lblFlechaR.hide();
        lblVolverMenu.setEnabled(false);
        this.pos_x = lblPersonaje.getX();
        this.pos_y = lblPersonaje.getY();
        this.height = lblPersonaje.getHeight();
        this.width = lblPersonaje.getWidth();
        lblAEscenarioUno.setVisible(false);
        txtCronometro.setOpaque(true);
        ubicacionComponentesEsceneario();//Crea los rectangulos de colision con cada objeto de los escenarios
        timer = new Timer(10, accion_cronometro);
        timer.start();
        lblContinuar1.setText("<html>"+"Jugando con Palabras"+"</html>");
        mensajeAyuda(6000, 5, 100, contador_sucesos);/*se envia tiempo en milisegundos para iniciar ejecucion, segundos, centesimas de segundo, la opcion*/
        lblAyuda.setText("<html>"+mensajes.mensajesMenu(51)[1]+"</html>");
    }
    
//Método de ejecución del cronómetro
    ActionListener accion_cronometro = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cs++;
            if(cs==100){
                cs=0;s++;
            }
            if(s==60){
                s=0;m++;
            }
            if(m==60){
                m=0;h++;
            }
            texto_cronometro = (h<=9?"0":"")+h+":"+(m<=9?"0":"")+m+":"+(s<=9?"0":"")+s+":"+(cs<=9?"0":"")+cs;
            txtCronometro.setText(texto_cronometro);
        }    
    };
    
    
    public void setPersonaje(char p_ninoa, String nombre_usuario){//Metodo que guarda nombre e imagen de jugador
        this.nombre_usuario = nombre_usuario;
        this.ninoa = recursos.personajeSeleccionado(p_ninoa);
        lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[5]));
    }
    
    
    //Metodo que escucha recurrentemente las pulsaciones de las teclas
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            if(pausa_mensaje[0]){//El enter se ha oprimido (está pausado)
                if(pausa_mensaje[1]){//Si se está sobre la opción de "Continuar" con el juego pausado
                    this.pausa_mensaje[0] = false; lblContinuar.hide(); lblVolverMenu.hide(); timer.start();
                }else{
                    if(JOptionPane.showConfirmDialog(null, "Si vuelve al menú perderá el avance\n"+"¿Desea salir?", "Salir", 2)==0){
                        dispose();
                        menu.setVisible(true);
                    }
                }
            }
            else{//Pausa el juego
                this.pausa_mensaje[0] = true; this.pausa_mensaje[1] = true; lblContinuar.show(); lblVolverMenu.show(); timer.stop();
            }
        }
        /////////
        if(e.getExtendedKeyCode()==KeyEvent.VK_UP){
            if(pausa_mensaje[0]){lblContinuar.setEnabled(true); lblVolverMenu.setEnabled(false); this.pausa_mensaje[0] = true; this.pausa_mensaje[1] = true;}
            else{
                this.direccion = 'u';
                mover();
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            if(pausa_mensaje[0]){lblContinuar.setEnabled(false); lblVolverMenu.setEnabled(true); this.pausa_mensaje[0] = true; this.pausa_mensaje[1] = false;}
            else{
                this.direccion = 'd';
                mover();
            }
        }if(e.getExtendedKeyCode()==KeyEvent.VK_LEFT){
            if(pausa_mensaje[0]){}
            else{
                this.direccion = 'l';
                mover();
            }
        }if(e.getExtendedKeyCode()==KeyEvent.VK_RIGHT){
            if(pausa_mensaje[0]){}
            else{
                this.direccion = 'r';
                mover();
            }
        }
        
        if(e.getExtendedKeyCode()==KeyEvent.VK_M){
            /*JOptionPane.showMessageDialog(null, "contador_sucesos "+contador_sucesos+" colision_ob_pue_tar_hab[3] "+colision_ob_pue_tar_hab[3]+
                    "\n habilidad_numero "+habilidad_numero+" tarea_numero "+tarea_numero+"\nmastar_mashab[0] "+mastarea_mashabili[0]+" colision_ob_pue_tar_hab[2] "+colision_ob_pue_tar_hab[2]);*/
            JOptionPane.showMessageDialog(null,contador_sucesos);
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
            if (txtEntrada.isEditable()) {
            } else {
                if (contador_sucesos == 102) {
                    Palabras(silaba_tilde);
                    txtEntrada.setEditable(true);
                    txtEntrada.setEnabled(true);
                    txtEntrada.requestFocus();
                }
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_T){
            if (txtEntrada.isEditable()) {
            } else {
                if (contador_sucesos == 105) {
                    Palabras(silaba_tilde);
                    txtEntrada.setEditable(true);
                    txtEntrada.setEnabled(true);
                    txtEntrada.requestFocus();
                }
            }
        }
        if(e.getExtendedKeyCode()==KeyEvent.VK_G){
            registros.guardarRegistro(texto_cronometro, nombre_usuario);
        }
        
        /////////
        /*
        if(e.getExtendedKeyCode()==KeyEvent.VK_A){
            if(mensajes.getPausaMensaje()[2]){}/*Se retorna pausa_mensaje en la posición 2 al ser verdadero,
            impide que al estar desplegada la ventana, sea mostrada una nueva ventana por cada A oprimida*//*
            else{
                mensajeAyuda(1, 1, 1, 51, "");
            }
        }/*
        */
    }
    
    @Override
    public void keyReleased(KeyEvent e) {//Muestra la posicion del personaje al soltar alguna tecla
        lblPersonaje.setLocation(pos_x, pos_y);
        switch(direccion){
            case 'u':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[4]));
                break;
            case 'd':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[5]));
                break;
            case 'l':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[6]));
                break;
            case 'r':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[7]));
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    
    void mover(){//Es el método encargado de los desplazamientos del personaje
        switch (direccion){
            case 'u':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[0]));
                this.colision_ob_pue_tar_hab = colisiones(pos_x, pos_y-DESPLAZAMIENTO, width, height);
                eventosColisiones();
                if(colision_ob_pue_tar_hab[0]){}
                else{
                    this.pos_y+=-DESPLAZAMIENTO;
                    lblPersonaje.setLocation(pos_x, pos_y);
                }
                break;
            case 'd':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[1]));
                this.colision_ob_pue_tar_hab = colisiones(pos_x, pos_y+DESPLAZAMIENTO, width, height);
                eventosColisiones();
                if(colision_ob_pue_tar_hab[0]){}
                else{
                    this.pos_y+=DESPLAZAMIENTO;
                    lblPersonaje.setLocation(pos_x, pos_y);
                }
                break;
            
            case 'l':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[2]));
                this.colision_ob_pue_tar_hab = colisiones(pos_x-DESPLAZAMIENTO, pos_y, width, height);
                eventosColisiones();
                if(colision_ob_pue_tar_hab[0]){}
                else{
                    if(colision_ob_pue_tar_hab[1]){
                        ///////////
                        ///////////
                    }else{
                        pos_x+=-DESPLAZAMIENTO;
                        lblPersonaje.setLocation(pos_x, pos_y);
                    }
                }
                break;
            case 'r':
                lblPersonaje.setIcon(new javax.swing.ImageIcon(ninoa[3]));
                this.colision_ob_pue_tar_hab = colisiones(pos_x+DESPLAZAMIENTO, pos_y, width, height);
                eventosColisiones();
                if(colision_ob_pue_tar_hab[0]){}
                else{
                    if(colision_ob_pue_tar_hab[1]){
                        ///////////
                        ///////////
                    }else{
                        pos_x+=DESPLAZAMIENTO;
                        lblPersonaje.setLocation(pos_x, pos_y);
                    }
                }
                break;
        }
    }
    
    
    /*Crea un arreglo de tipo boolean para establecer en cada indice si se efectuaron colisiones*/
    boolean [] colisiones(int x, int y, int width, int height){
        personaje = new Rectangle(x, y, width, height);
        switch (escenario){
            /////
            case "escenariouno":
                if(personaje.intersects(lago1) || personaje.intersects(lago2) || personaje.intersects(lago3) || personaje.intersects(lago4) || 
                        personaje.intersects(lago5) || personaje.intersects(lago6) ||
                        personaje.intersects(objeto1e1) || personaje.intersects(objeto2e1) || personaje.intersects(objeto3e1) ||
                        personaje.intersects(objeto4e1) || personaje.intersects(objeto5e1) || personaje.intersects(objeto6e1) ||
                        personaje.intersects(objeto7e1) || personaje.intersects(objeto8e1) || personaje.intersects(objeto9e1) ||
                        personaje.intersects(objeto10e1) || personaje.intersects(objeto11e1) || personaje.intersects(objeto12e1) ||
                        personaje.intersects(objeto13e1) || personaje.intersects(objeto14e1) || personaje.intersects(objeto15e1) ||
                        personaje.intersects(objeto16e1) || personaje.intersects(objeto17e1) || personaje.intersects(objeto18e1) ||
                        personaje.intersects(separadorSuperior) || personaje.intersects(separadorInferior) || 
                        personaje.intersects(separadorIzquierda) || personaje.intersects(separadorDerecha)){
                    colision_ob_pue_tar_hab[0] = true;
                }else{
                    colision_ob_pue_tar_hab[0] = false;
                }
                if(personaje.intersects(puertaDerecha)){
                    colision_ob_pue_tar_hab[1] = true;
                }else{
                    colision_ob_pue_tar_hab[1] = false;
                }
                break;
            /////    
            case "escenariodos":
                if(personaje.intersects(separadorSuperior) || personaje.intersects(separadorInferior) || 
                        personaje.intersects(separadorIzquierda) || personaje.intersects(separadorDerecha) || 
                        personaje.intersects(objeto1e2) || personaje.intersects(objeto2e2) || personaje.intersects(objeto3e2) ||
                        personaje.intersects(objeto4e2) || personaje.intersects(objeto5e2) || personaje.intersects(objeto6e2) ||
                        personaje.intersects(objeto7e2) || personaje.intersects(objeto8e2) || personaje.intersects(objeto9e2) ||
                        personaje.intersects(objeto10e2) || personaje.intersects(objeto11e2) || personaje.intersects(objeto12e2)){
                    colision_ob_pue_tar_hab[0] = true;
                }else{
                    colision_ob_pue_tar_hab[0] = false;
                }
                if(personaje.intersects(puertaIzquierda)){
                    colision_ob_pue_tar_hab[1] = true;
                }else{
                    colision_ob_pue_tar_hab[1] = false;
                }
                break;
        }
        if(personaje.intersects(realizar_tarea)){/*En esta parte del método, si se intersecta con tarea, siempre va a ser verdadero hasta que se termine la tarea*/
            lblTarea.hide();
            if(colision_ob_pue_tar_hab[2]){}
            else{
                colision_ob_pue_tar_hab[2] = true;
                contador_sucesos++;
                mensajeAyuda(1, 5, 100, contador_sucesos);
                flechaAyuda();
            }
        }
        if(personaje.intersects(habilidad)){
            colision_ob_pue_tar_hab[3] = true;
        }else{
            colision_ob_pue_tar_hab[3] = false;
        }
        return colision_ob_pue_tar_hab;
    }
    
    void eventosColisiones(){/*Genera algunos comportamientos del escenario, como mostrar habilidades, contarlas, entre otras*/
        if(colision_ob_pue_tar_hab[1]){//Evento de colision al cambiar el escenario
            switch (direccion){
                case 'r':
                    cambioEscenarios();
                    if(colision_ob_pue_tar_hab[2]){
                        lblTarea.hide();
                    }else{
                        lblTarea.show();
                    }
                    this.pos_x=10; this.pos_y=430;
                    lblPersonaje.setLocation(pos_x,pos_y);
                    break;
                case 'l':
                    cambioEscenarios();
                    lblTarea.hide();
                    this.pos_x=960; this.pos_y=330;
                    lblPersonaje.setLocation(pos_x,pos_y);
                    break;
            }
        }
        
        switch (escenario){/*Cuenta, y muestra las habilidades en el menú*/
            case "escenariouno":
                if(colision_ob_pue_tar_hab[2]){
                    if(colision_ob_pue_tar_hab[3] && mastarea_mashabili[1]){
                        mastarea_mashabili[1]=false;
                        }
                    }
                break;
            case "escenariodos":
                if(mastarea_mashabili[0] && colision_ob_pue_tar_hab[2]){
                    mastarea_mashabili[0] = false;
                    tarea_numero++;
                }
                break;
        }
        mostrarHabilidades();
    }
    
    
    void mostrarHabilidades(){
        if(colision_ob_pue_tar_hab[2]){//Se pregunta si existen tareas pendientes
            switch(escenario){
                case "escenariouno":
                    if(contador_sucesos==101 && colision_ob_pue_tar_hab[2] || contador_sucesos==104 && colision_ob_pue_tar_hab[2]){
                        if(colision_ob_pue_tar_hab[3]){
                            lblHabilidad.hide();
                            contador_sucesos++;
                            habilidad_numero++;
                            mensajeAyuda(3000, 5, 100, contador_sucesos);
                        }
                        if(contador_sucesos<104){
                            lblHabilidad.setIcon(new javax.swing.ImageIcon(recursos.r_habGuion));
                            lblHabilidad.show();
                        }else{
                            lblHabilidad.setIcon(new javax.swing.ImageIcon(recursos.r_habTilde));
                            lblHabilidad.show();
                        }
                    }
                    else{
                        lblHabilidad.hide();
                    }
                    break;
                case "escenariodos":
                    lblHabilidad.hide();
                    break;
            }
        }
        /*A continuación, se muestra en el menú las habilidades tomadas*/
        if(habilidad_numero==3){
            lblHabilidadTomada1.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadGuion));
            lblHabilidadTomada2.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadGuion));
            lblHabilidadTomada3.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadGuion));
        }else{
            if(habilidad_numero==2){
                lblHabilidadTomada1.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadGuion));
                lblHabilidadTomada2.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadTilde));
            }else{
                if(habilidad_numero==1){
                    lblHabilidadTomada1.setIcon(new javax.swing.ImageIcon(recursos.r_habilidadGuion));
                }
            }
        }
    }
    
    
    void cambioEscenarios(){//Simplemente hace el cambio de la imagen de fondo del escenario y habilita o desabilita las puertas de paso ademas de otros objetos
        switch (direccion){
            case 'r':
                lblFondoEscenario.setIcon(new ImageIcon(recursos.r_fondo_escenario2));
                this.escenario = "escenariodos";
                lblAEscenarioDos.setVisible(false);
                lblAEscenarioUno.setVisible(true);
                flechaAyuda();
                break;
            case 'l':
                lblFondoEscenario.setIcon(new ImageIcon(recursos.r_fondo_escenario1));
                this.escenario = "escenariouno";
                lblAEscenarioUno.setVisible(false);
                lblAEscenarioDos.setVisible(true);
                flechaAyuda();
                break;
        }
    }
    
    int contador = 0;
    String p_entrada;
    private void txtEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            p_entrada = txtEntrada.getText().trim().toLowerCase();
            if (silaba_tilde == 's') {
                if (p_entrada.equals(palabra_silabada)) {
                    contador++;
                    if (contador < 3) {
                        txtEntrada.setText("");
                        Palabras(silaba_tilde);
                    } else {
                        if (silaba_tilde == 's') {
                            //JOptionPane.showMessageDialog(null, "iguales");
                            txtEntrada.setText("");
                            txtEntrada.setEditable(false);
                            txtEntrada.setEnabled(false);
                            lblPalabra.setText("");
                            this.requestFocus();
                            contador_sucesos++;
                            silaba_tilde = 't';
                            lblFlechaD.hide();
                            lblHacerTarea.setText("");
                            colision_ob_pue_tar_hab[2] = false;
                            flechaAyuda();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "no iguales");
                    JOptionPane.showMessageDialog(null, palabra_silabada);
                }
            }else{
                if(palabra_tipo[1].equals(txtEntrada.getText())){
                    txtEntrada.setText("");
                    Palabras(silaba_tilde);
                    contador++;
                    if (contador < 6) {}
                    else{
                        contador_sucesos++;
                        silaba_tilde = 'a';
                        txtEntrada.setText("");
                        txtEntrada.setEnabled(false);
                        registros.guardarRegistro(texto_cronometro, nombre_usuario);
                        lblPalabra.setText("");
                        JOptionPane.showMessageDialog(null, "FIN DEL JUEGO!");
                        this.pausa_mensaje[0] = true; this.pausa_mensaje[1] = true; lblContinuar.show(); lblVolverMenu.show(); timer.stop();
                    }
                }else{
                    if(p_entrada.equals("a")||p_entrada.equals("g")||p_entrada.equals("e")||p_entrada.equals("s")){
                        JOptionPane.showMessageDialog(null, "Intente nuevamente!!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Acción inválida!!");
                    }
                }
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            txtEntrada.setEditable(false);
            txtEntrada.setEnabled(false);
            lblPalabra.setText("");
            this.requestFocus();
        }
    }//GEN-LAST:event_txtEntradaKeyPressed

    int limite = 30;//Será el número límite de caracteres permitidos para el usuario
    private void txtEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyTyped
        if (txtEntrada.getText().length() >= limite)//Se pregunta si se ha llegado a ese límite de caracteres
            evt.consume();//Se eliminan los caracteres al superar dicho límite
    }//GEN-LAST:event_txtEntradaKeyTyped

    
    String cadena_alfabeto, palabra_silabada="";
    String [] palabra_tipo = {"",""};
    char silaba_tilde = 's';
    public void Palabras(char silaba_tilde){//recibe s o t: s para silabar y t para tildar y de esta forma generar la palabra
        alfabeto.generarAlfabeto();
        cadena_alfabeto = alfabeto.getCadena_alfabeto();
        String [] arreglo_vocal_consonante = {alfabeto.getCadena_vocal(),alfabeto.getCadena_consonante()};
        /* El método GeneradorAleatorio(), lo que hace es generador un número dependiendo de s o t, el cual será el número de línea. del archivo
        luego una variable trae lo contenido en ese número de línea (variable palabra) y realiza el proceso de silabación de forma automática o lo tilda si es el caso*/
        try {
            palabra_tipo = generador.GenerarAleatoriamente(silaba_tilde);
        } catch (IOException ex) {Logger.getLogger(UI_Silabacion.class.getName()).log(Level.SEVERE, null, ex);}
        operacionessilabacion.operacionesSilabacion(palabra_tipo[0], cadena_alfabeto, arreglo_vocal_consonante);
        this.palabra_silabada = operacionessilabacion.getPalabraSilabada();
        lblPalabra.setText(palabra_tipo[0]);
    }
    
    void ubicacionComponentesEsceneario(){//Crea los rectangulos de colision con cada objeto de los escenarios
        this.separadorSuperior = new Rectangle(jsepSuperior.getLocation().x,jsepSuperior.getLocation().y,jsepSuperior.getWidth(), jsepSuperior.getHeight());
        this.separadorIzquierda = new Rectangle(jsepIzquierdo.getLocation().x, jsepIzquierdo.getLocation().y, jsepIzquierdo.getWidth(), jsepIzquierdo.getHeight());
        this.separadorInferior = new Rectangle(jsepInferior.getLocation().x,jsepInferior.getLocation().y,jsepInferior.getWidth(), jsepInferior.getHeight());
        this.separadorDerecha = new Rectangle(jsepDerecho.getLocation().x, jsepDerecho.getLocation().y, jsepDerecho.getWidth(), jsepDerecho.getHeight());
        //Escenario Uno
        this.lago1 = new Rectangle(lblLago1.getLocation().x, lblLago1.getLocation().y, lblLago1.getWidth(), lblLago1.getHeight());
        this.lago2 = new Rectangle(lblLago2.getLocation().x, lblLago2.getLocation().y, lblLago2.getWidth(), lblLago2.getHeight());
        this.lago3 = new Rectangle(lblLago3.getLocation().x, lblLago3.getLocation().y, lblLago3.getWidth(), lblLago3.getHeight());
        this.lago4 = new Rectangle(lblLago4.getLocation().x, lblLago4.getLocation().y, lblLago4.getWidth(), lblLago4.getHeight());
        this.lago5 = new Rectangle(lblLago5.getLocation().x, lblLago5.getLocation().y, lblLago5.getWidth(), lblLago5.getHeight());
        this.lago6 = new Rectangle(lblLago6.getLocation().x, lblLago6.getLocation().y, lblLago6.getWidth(), lblLago6.getHeight());
        this.objeto1e1 = new Rectangle(lblObjeto1e1.getLocation().x, lblObjeto1e1.getLocation().y, lblObjeto1e1.getWidth(), lblObjeto1e1.getHeight());
        this.objeto2e1 = new Rectangle(lblObjeto2e1.getLocation().x, lblObjeto2e1.getLocation().y, lblObjeto2e1.getWidth(), lblObjeto2e1.getHeight());
        this.objeto3e1 = new Rectangle(lblObjeto3e1.getLocation().x, lblObjeto3e1.getLocation().y, lblObjeto3e1.getWidth(), lblObjeto3e1.getHeight());
        this.objeto4e1 = new Rectangle(lblObjeto4e1.getLocation().x, lblObjeto4e1.getLocation().y, lblObjeto4e1.getWidth(), lblObjeto4e1.getHeight());
        this.objeto5e1 = new Rectangle(lblObjeto5e1.getLocation().x, lblObjeto5e1.getLocation().y, lblObjeto5e1.getWidth(), lblObjeto5e1.getHeight());
        this.objeto6e1 = new Rectangle(lblObjeto6e1.getLocation().x, lblObjeto6e1.getLocation().y, lblObjeto6e1.getWidth(), lblObjeto6e1.getHeight());
        this.objeto7e1 = new Rectangle(lblObjeto7e1.getLocation().x, lblObjeto7e1.getLocation().y, lblObjeto7e1.getWidth(), lblObjeto7e1.getHeight());
        this.objeto8e1 = new Rectangle(lblObjeto8e1.getLocation().x, lblObjeto8e1.getLocation().y, lblObjeto8e1.getWidth(), lblObjeto8e1.getHeight());
        this.objeto9e1 = new Rectangle(lblObjeto9e1.getLocation().x, lblObjeto9e1.getLocation().y, lblObjeto9e1.getWidth(), lblObjeto9e1.getHeight());
        this.objeto10e1 = new Rectangle(lblObjeto10e1.getLocation().x, lblObjeto10e1.getLocation().y, lblObjeto10e1.getWidth(), lblObjeto10e1.getHeight());
        this.objeto11e1 = new Rectangle(lblObjeto11e1.getLocation().x, lblObjeto11e1.getLocation().y, lblObjeto11e1.getWidth(), lblObjeto11e1.getHeight());
        this.objeto12e1 = new Rectangle(lblObjeto12e1.getLocation().x, lblObjeto12e1.getLocation().y, lblObjeto12e1.getWidth(), lblObjeto12e1.getHeight());
        this.objeto13e1 = new Rectangle(lblObjeto13e1.getLocation().x, lblObjeto13e1.getLocation().y, lblObjeto13e1.getWidth(), lblObjeto13e1.getHeight());
        this.objeto14e1 = new Rectangle(lblObjeto14e1.getLocation().x, lblObjeto14e1.getLocation().y, lblObjeto14e1.getWidth(), lblObjeto14e1.getHeight());
        this.objeto15e1 = new Rectangle(lblObjeto15e1.getLocation().x, lblObjeto15e1.getLocation().y, lblObjeto15e1.getWidth(), lblObjeto15e1.getHeight());
        this.objeto16e1 = new Rectangle(lblObjeto16e1.getLocation().x, lblObjeto16e1.getLocation().y, lblObjeto16e1.getWidth(), lblObjeto16e1.getHeight());
        this.objeto17e1 = new Rectangle(lblObjeto17e1.getLocation().x, lblObjeto17e1.getLocation().y, lblObjeto17e1.getWidth(), lblObjeto17e1.getHeight());
        this.objeto18e1 = new Rectangle(lblObjeto18e1.getLocation().x, lblObjeto18e1.getLocation().y, lblObjeto18e1.getWidth(), lblObjeto18e1.getHeight());
        this.puertaDerecha = new Rectangle(lblAEscenarioDos.getLocation().x, lblAEscenarioDos.getLocation().y, lblAEscenarioDos.getWidth(), lblAEscenarioDos.getHeight());
        //Escenario Dos
        this.objeto1e2 = new Rectangle(lblObjeto1e2.getLocation().x, lblObjeto1e2.getLocation().y, lblObjeto1e2.getWidth(), lblObjeto1e2.getHeight());
        this.objeto2e2 = new Rectangle(lblObjeto2e2.getLocation().x, lblObjeto2e2.getLocation().y, lblObjeto2e2.getWidth(), lblObjeto2e2.getHeight());
        this.objeto3e2 = new Rectangle(lblObjeto3e2.getLocation().x, lblObjeto3e2.getLocation().y, lblObjeto3e2.getWidth(), lblObjeto3e2.getHeight());
        this.objeto4e2 = new Rectangle(lblObjeto4e2.getLocation().x, lblObjeto4e2.getLocation().y, lblObjeto4e2.getWidth(), lblObjeto4e2.getHeight());
        this.objeto5e2 = new Rectangle(lblObjeto5e2.getLocation().x, lblObjeto5e2.getLocation().y, lblObjeto5e2.getWidth(), lblObjeto5e2.getHeight());
        this.objeto6e2 = new Rectangle(lblObjeto6e2.getLocation().x, lblObjeto6e2.getLocation().y, lblObjeto6e2.getWidth(), lblObjeto6e2.getHeight());
        this.objeto7e2 = new Rectangle(lblObjeto7e2.getLocation().x, lblObjeto7e2.getLocation().y, lblObjeto7e2.getWidth(), lblObjeto7e2.getHeight());
        this.objeto8e2 = new Rectangle(lblObjeto8e2.getLocation().x, lblObjeto8e2.getLocation().y, lblObjeto8e2.getWidth(), lblObjeto8e2.getHeight());
        this.objeto9e2 = new Rectangle(lblObjeto9e2.getLocation().x, lblObjeto9e2.getLocation().y, lblObjeto9e2.getWidth(), lblObjeto9e2.getHeight());
        this.objeto10e2 = new Rectangle(lblObjeto10e2.getLocation().x, lblObjeto10e2.getLocation().y, lblObjeto10e2.getWidth(), lblObjeto10e2.getHeight());
        this.objeto11e2 = new Rectangle(lblObjeto11e2.getLocation().x, lblObjeto11e2.getLocation().y, lblObjeto11e2.getWidth(), lblObjeto11e2.getHeight());
        this.objeto12e2 = new Rectangle(lblObjeto12e2.getLocation().x, lblObjeto12e2.getLocation().y, lblObjeto12e2.getWidth(), lblObjeto12e2.getHeight());
        this.puertaIzquierda = new Rectangle(lblAEscenarioUno.getLocation().x, lblAEscenarioUno.getLocation().y, lblAEscenarioUno.getWidth(), lblAEscenarioUno.getHeight());
        //Tareas
        this.habilidad = new Rectangle(lblHabilidad.getLocation().x, lblHabilidad.getLocation().y, lblHabilidad.getWidth(), lblHabilidad.getHeight());
        this.realizar_tarea = new Rectangle(lblTarea.getLocation().x, lblTarea.getLocation().y, lblTarea.getWidth(), lblTarea.getHeight());
    }
    
    
    public void mensajeAyuda(int tiempo, int seg, int cseg, int contador_tareas) {
        mensajes.setContador_tareas(contador_tareas);
        mensajes.setTemporizador(tiempo, seg, cseg, contador_tareas);/*se envia tiempo, segundos, centesimas de segundo, la opcion*/
        //mensajes.setPausaMensaje(pausa_mensaje);
        temporizadorMensajes();
    }
    
    
    public void temporizadorMensajes() {
        java.util.Timer timer_msn = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mensajes.setVisible(true);
                mensajes.Temporizador();
                timer_msn.cancel();/*
                pausa_mensaje[0] = true;
                pausa_mensaje[1] = true;
                pausa_mensaje[2] = true;
                lblContinuar.show();
                lblVolverMenu.show();
                timer.stop();*/
                flechaAyuda();
            }
        };
        timer_msn.schedule(task, mensajes.tiempo, 10);//Inicia en tiempo segundos y se estará alternando cada 10 milisegundos
    }
    
    int i=0;
    void flechaAyuda(){
        if (contador_sucesos == 100 || contador_sucesos==103) {
            if (escenario.equals("escenariouno")) {
                lblFlechaL.hide();
                lblFlechaR.show();
                lblFlechaU.hide();
                lblFlechaD.hide();
            } else {
                lblFlechaL.hide();
                lblFlechaR.hide();
                lblFlechaU.show();
                lblFlechaD.hide();
            }
        }
        if (contador_sucesos == 101 || contador_sucesos == 104){
            if (escenario.equals("escenariouno")) {
                lblFlechaL.hide();
                lblFlechaR.hide();
                lblFlechaU.hide();
                lblFlechaD.hide();
            } else {
                lblFlechaL.show();
                lblFlechaR.hide();
                lblFlechaU.hide();
                lblFlechaD.hide();
            }
        }
        if (contador_sucesos == 102 || contador_sucesos == 105) {
            lblFlechaL.hide();
            lblFlechaR.hide();
            lblFlechaU.hide();
            lblFlechaD.show();
            lblHacerTarea.setText("<html>" + mensajes.getMsg() + "</html>");
            if (i == 0) {
                i++;
                Palabras(silaba_tilde);
                txtEntrada.setEditable(true);
                txtEntrada.setEnabled(true);
                txtEntrada.requestFocus();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContinuar1 = new javax.swing.JLabel();
        lblContinuar = new javax.swing.JLabel();
        lblVolverMenu = new javax.swing.JLabel();
        lblPersonaje = new javax.swing.JLabel();
        lblHabilidad = new javax.swing.JLabel();
        lblLetreroHabilidades = new javax.swing.JLabel();
        lblHabilidadTomada1 = new javax.swing.JLabel();
        lblHabilidadTomada2 = new javax.swing.JLabel();
        lblHabilidadTomada3 = new javax.swing.JLabel();
        lblAEscenarioUno = new javax.swing.JLabel();
        lblAEscenarioDos = new javax.swing.JLabel();
        jsepSuperior = new javax.swing.JSeparator();
        jsepIzquierdo = new javax.swing.JSeparator();
        jsepDerecho = new javax.swing.JSeparator();
        jsepInferior = new javax.swing.JSeparator();
        lblObjeto1e1 = new javax.swing.JLabel();
        lblObjeto2e1 = new javax.swing.JLabel();
        lblObjeto3e1 = new javax.swing.JLabel();
        lblObjeto4e1 = new javax.swing.JLabel();
        lblObjeto5e1 = new javax.swing.JLabel();
        lblObjeto6e1 = new javax.swing.JLabel();
        lblObjeto7e1 = new javax.swing.JLabel();
        lblObjeto8e1 = new javax.swing.JLabel();
        lblObjeto9e1 = new javax.swing.JLabel();
        lblObjeto10e1 = new javax.swing.JLabel();
        lblObjeto11e1 = new javax.swing.JLabel();
        lblObjeto12e1 = new javax.swing.JLabel();
        lblObjeto13e1 = new javax.swing.JLabel();
        lblObjeto14e1 = new javax.swing.JLabel();
        lblObjeto15e1 = new javax.swing.JLabel();
        lblObjeto16e1 = new javax.swing.JLabel();
        lblObjeto17e1 = new javax.swing.JLabel();
        lblObjeto18e1 = new javax.swing.JLabel();
        lblLago1 = new javax.swing.JLabel();
        lblLago2 = new javax.swing.JLabel();
        lblLago3 = new javax.swing.JLabel();
        lblLago4 = new javax.swing.JLabel();
        lblLago5 = new javax.swing.JLabel();
        lblLago6 = new javax.swing.JLabel();
        lblObjeto1e2 = new javax.swing.JLabel();
        lblObjeto2e2 = new javax.swing.JLabel();
        lblObjeto3e2 = new javax.swing.JLabel();
        lblObjeto4e2 = new javax.swing.JLabel();
        lblObjeto5e2 = new javax.swing.JLabel();
        lblObjeto6e2 = new javax.swing.JLabel();
        lblObjeto7e2 = new javax.swing.JLabel();
        lblObjeto8e2 = new javax.swing.JLabel();
        lblObjeto9e2 = new javax.swing.JLabel();
        lblObjeto10e2 = new javax.swing.JLabel();
        lblObjeto11e2 = new javax.swing.JLabel();
        lblObjeto12e2 = new javax.swing.JLabel();
        lblTarea = new javax.swing.JLabel();
        lblAyuda = new javax.swing.JLabel();
        txtCronometro = new javax.swing.JTextField();
        lblHacerTarea = new javax.swing.JLabel();
        lblPalabra = new javax.swing.JLabel();
        lblFlechaL = new javax.swing.JLabel();
        lblFlechaR = new javax.swing.JLabel();
        lblFlechaU = new javax.swing.JLabel();
        lblFlechaD = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        lblFondoEscenario = new javax.swing.JLabel();
        lblFondoGeneral = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblContinuar1.setBackground(new java.awt.Color(0, 0, 0));
        lblContinuar1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblContinuar1.setForeground(new java.awt.Color(255, 0, 0));
        lblContinuar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContinuar1.setBorder(new javax.swing.border.MatteBorder(null));
        lblContinuar1.setOpaque(true);
        getContentPane().add(lblContinuar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 30));

        lblContinuar.setBackground(new java.awt.Color(0, 0, 0));
        lblContinuar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblContinuar.setForeground(new java.awt.Color(255, 0, 0));
        lblContinuar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContinuar.setText("Continuar");
        lblContinuar.setBorder(new javax.swing.border.MatteBorder(null));
        lblContinuar.setOpaque(true);
        getContentPane().add(lblContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 140, 30));

        lblVolverMenu.setBackground(new java.awt.Color(0, 0, 0));
        lblVolverMenu.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblVolverMenu.setForeground(new java.awt.Color(255, 0, 0));
        lblVolverMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVolverMenu.setText("Volver al menu");
        lblVolverMenu.setBorder(new javax.swing.border.MatteBorder(null));
        lblVolverMenu.setOpaque(true);
        getContentPane().add(lblVolverMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 140, -1));

        lblPersonaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Personaje/nino/nino_down_stop.png"))); // NOI18N
        getContentPane().add(lblPersonaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        lblHabilidad.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblHabilidad.setForeground(new java.awt.Color(204, 255, 0));
        lblHabilidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Habilidades/Habilidades/guion.png"))); // NOI18N
        getContentPane().add(lblHabilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 30, 30));

        lblLetreroHabilidades.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        lblLetreroHabilidades.setForeground(new java.awt.Color(0, 255, 255));
        lblLetreroHabilidades.setText("Habilidades");
        lblLetreroHabilidades.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(51, 51, 255)));
        getContentPane().add(lblLetreroHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 50, 150, 40));

        lblHabilidadTomada1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabilidadTomada1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblHabilidadTomada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 50, 50));

        lblHabilidadTomada2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabilidadTomada2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblHabilidadTomada2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 50, 50));

        lblHabilidadTomada3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabilidadTomada3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblHabilidadTomada3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, 50, 50));

        lblAEscenarioUno.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        lblAEscenarioUno.setOpaque(true);
        getContentPane().add(lblAEscenarioUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 10, 80));

        lblAEscenarioDos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        lblAEscenarioDos.setOpaque(true);
        getContentPane().add(lblAEscenarioDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(983, 300, 10, 80));
        getContentPane().add(jsepSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 980, 10));

        jsepIzquierdo.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jsepIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 10, 600));

        jsepDerecho.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jsepDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 10, 600));
        getContentPane().add(jsepInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 50, 990, 10));
        getContentPane().add(lblObjeto1e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 70, 20));
        getContentPane().add(lblObjeto2e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 30, 40));
        getContentPane().add(lblObjeto3e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 310, 70, 20));
        getContentPane().add(lblObjeto4e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 30, 40));
        getContentPane().add(lblObjeto5e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 70, 20));
        getContentPane().add(lblObjeto6e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 70, 20));
        getContentPane().add(lblObjeto7e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 30, 20));
        getContentPane().add(lblObjeto8e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 60, 20));
        getContentPane().add(lblObjeto9e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 140, 90));
        getContentPane().add(lblObjeto10e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, 20));
        getContentPane().add(lblObjeto11e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 50, 240));
        getContentPane().add(lblObjeto12e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 60, 200));
        getContentPane().add(lblObjeto13e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 60, 100));
        getContentPane().add(lblObjeto14e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 620, 210, 20));
        getContentPane().add(lblObjeto15e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 40, 20));
        getContentPane().add(lblObjeto16e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 400, 60, 220));
        getContentPane().add(lblObjeto17e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 180, 20));
        getContentPane().add(lblObjeto18e1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 50, 30));
        getContentPane().add(lblLago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 120, 40));
        getContentPane().add(lblLago2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 420, 50));
        getContentPane().add(lblLago3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 70, 10));
        getContentPane().add(lblLago4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 70, 70));
        getContentPane().add(lblLago5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 350, 40));
        getContentPane().add(lblLago6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 80, 100));
        getContentPane().add(lblObjeto1e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, 40, 30));
        getContentPane().add(lblObjeto2e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 60, 80));
        getContentPane().add(lblObjeto3e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 120, 60));
        getContentPane().add(lblObjeto4e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 60, 30));
        getContentPane().add(lblObjeto5e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 50, 40));
        getContentPane().add(lblObjeto6e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 20, 40));
        getContentPane().add(lblObjeto7e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 30, 60));
        getContentPane().add(lblObjeto8e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 90, 20));
        getContentPane().add(lblObjeto9e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 50, 40));
        getContentPane().add(lblObjeto10e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 50, 40));
        getContentPane().add(lblObjeto11e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 130, 30));
        getContentPane().add(lblObjeto12e2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, 20, 40));

        lblTarea.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblTarea.setForeground(new java.awt.Color(255, 255, 0));
        lblTarea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTarea.setText("Tarea");
        getContentPane().add(lblTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, 60, 30));

        lblAyuda.setBackground(new java.awt.Color(0, 0, 0));
        lblAyuda.setForeground(new java.awt.Color(255, 255, 255));
        lblAyuda.setOpaque(true);
        getContentPane().add(lblAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 150, 170, 110));

        txtCronometro.setEditable(false);
        txtCronometro.setBackground(new java.awt.Color(0, 0, 0));
        txtCronometro.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        txtCronometro.setForeground(new java.awt.Color(0, 0, 204));
        txtCronometro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCronometro.setText("00:00:00:00");
        txtCronometro.setEnabled(false);
        getContentPane().add(txtCronometro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 150, 30));

        lblHacerTarea.setBackground(new java.awt.Color(0, 0, 0));
        lblHacerTarea.setForeground(new java.awt.Color(255, 255, 255));
        lblHacerTarea.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 204)));
        lblHacerTarea.setOpaque(true);
        getContentPane().add(lblHacerTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 310, 170, 260));

        lblPalabra.setBackground(new java.awt.Color(255, 255, 255));
        lblPalabra.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPalabra.setForeground(new java.awt.Color(0, 0, 255));
        lblPalabra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPalabra.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 204)));
        lblPalabra.setOpaque(true);
        getContentPane().add(lblPalabra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 570, 170, 40));

        lblFlechaL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Mensajes/strelka1L.gif"))); // NOI18N
        getContentPane().add(lblFlechaL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 60, 60));

        lblFlechaR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Mensajes/strelka1R.gif"))); // NOI18N
        getContentPane().add(lblFlechaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 310, 60, 60));

        lblFlechaU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Mensajes/strelka1U.gif"))); // NOI18N
        getContentPane().add(lblFlechaU, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 50, 50));

        lblFlechaD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Mensajes/strelka1D.gif"))); // NOI18N
        getContentPane().add(lblFlechaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 260, 50, 50));

        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntradaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntradaKeyTyped(evt);
            }
        });
        getContentPane().add(txtEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 610, 170, 30));

        lblFondoEscenario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Escenarios/fondoUno.png"))); // NOI18N
        getContentPane().add(lblFondoEscenario, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 49, 1000, 600));

        lblFondoGeneral.setBackground(new java.awt.Color(204, 204, 204));
        lblFondoGeneral.setForeground(new java.awt.Color(102, 255, 51));
        lblFondoGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Escenarios/thumb-1920-697507.jpg"))); // NOI18N
        getContentPane().add(lblFondoGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jsepDerecho;
    private javax.swing.JSeparator jsepInferior;
    private javax.swing.JSeparator jsepIzquierdo;
    private javax.swing.JSeparator jsepSuperior;
    private javax.swing.JLabel lblAEscenarioDos;
    private javax.swing.JLabel lblAEscenarioUno;
    private javax.swing.JLabel lblAyuda;
    public javax.swing.JLabel lblContinuar;
    private javax.swing.JLabel lblContinuar1;
    private javax.swing.JLabel lblFlechaD;
    private javax.swing.JLabel lblFlechaL;
    private javax.swing.JLabel lblFlechaR;
    private javax.swing.JLabel lblFlechaU;
    private javax.swing.JLabel lblFondoEscenario;
    private javax.swing.JLabel lblFondoGeneral;
    private javax.swing.JLabel lblHabilidad;
    private javax.swing.JLabel lblHabilidadTomada1;
    private javax.swing.JLabel lblHabilidadTomada2;
    private javax.swing.JLabel lblHabilidadTomada3;
    public javax.swing.JLabel lblHacerTarea;
    private javax.swing.JLabel lblLago1;
    private javax.swing.JLabel lblLago2;
    private javax.swing.JLabel lblLago3;
    private javax.swing.JLabel lblLago4;
    private javax.swing.JLabel lblLago5;
    private javax.swing.JLabel lblLago6;
    private javax.swing.JLabel lblLetreroHabilidades;
    private javax.swing.JLabel lblObjeto10e1;
    private javax.swing.JLabel lblObjeto10e2;
    private javax.swing.JLabel lblObjeto11e1;
    private javax.swing.JLabel lblObjeto11e2;
    private javax.swing.JLabel lblObjeto12e1;
    private javax.swing.JLabel lblObjeto12e2;
    private javax.swing.JLabel lblObjeto13e1;
    private javax.swing.JLabel lblObjeto14e1;
    private javax.swing.JLabel lblObjeto15e1;
    private javax.swing.JLabel lblObjeto16e1;
    private javax.swing.JLabel lblObjeto17e1;
    private javax.swing.JLabel lblObjeto18e1;
    private javax.swing.JLabel lblObjeto1e1;
    private javax.swing.JLabel lblObjeto1e2;
    private javax.swing.JLabel lblObjeto2e1;
    private javax.swing.JLabel lblObjeto2e2;
    private javax.swing.JLabel lblObjeto3e1;
    private javax.swing.JLabel lblObjeto3e2;
    private javax.swing.JLabel lblObjeto4e1;
    private javax.swing.JLabel lblObjeto4e2;
    private javax.swing.JLabel lblObjeto5e1;
    private javax.swing.JLabel lblObjeto5e2;
    private javax.swing.JLabel lblObjeto6e1;
    private javax.swing.JLabel lblObjeto6e2;
    private javax.swing.JLabel lblObjeto7e1;
    private javax.swing.JLabel lblObjeto7e2;
    private javax.swing.JLabel lblObjeto8e1;
    private javax.swing.JLabel lblObjeto8e2;
    private javax.swing.JLabel lblObjeto9e1;
    private javax.swing.JLabel lblObjeto9e2;
    private javax.swing.JLabel lblPalabra;
    public javax.swing.JLabel lblPersonaje;
    private javax.swing.JLabel lblTarea;
    public javax.swing.JLabel lblVolverMenu;
    private javax.swing.JTextField txtCronometro;
    public javax.swing.JTextField txtEntrada;
    // End of variables declaration//GEN-END:variables
    
}
