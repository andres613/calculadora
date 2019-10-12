package accionesEventosRecursos;

import Modelo.Jugador;
import interfaz.Juego;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Registros {
    
    
    ArrayList <Jugador> arreglo_estadisticas  = new ArrayList<>();
    private final int NUMEROREGISTROS = 100;
    String registro, archivo_registros = "",
            ruta = "./src/recursos/Registros/Registros.txt",
            dato_usuario="",
            esp="    ";
    String nombre_usuario, tiempo, texto_cronometro, fecha;
    DateFormat hourdateFormat;
    Date date;
    
    char letra;
    int i, j, tam=0, ttiempo = 0;
    
    public String mostrarEstadisticas() {
        return Leer();
    }
    

    public void guardarRegistro(String texto_cronometro, String nombre_usuario) {
        date = Calendar.getInstance().getTime();
        hourdateFormat = new SimpleDateFormat("dd-MMM-yyyy    hh:mm a");
        this.texto_cronometro = texto_cronometro;
        this.fecha = hourdateFormat.format(date);
        this.nombre_usuario = nombre_usuario;
        this.registro = texto_cronometro +esp+ fecha +esp+ nombre_usuario;
        guardarRegistro();
        if (Leer()!=("")) {
            ordenarRecords();
        }
    }
    
    
    void ordenarRecords() {
        try {
            FileReader lector = new FileReader(ruta);
            BufferedReader cont = new BufferedReader(lector);
            while ((registro = cont.readLine()) != null && arreglo_estadisticas.size()<NUMEROREGISTROS) {
                arregloEstadisticas(pasarTiempoaNumero(registro.substring(0, 11)), registro.substring(0, 11), 
                        registro.substring(15, 38), registro.substring(42, registro.length()));
            }
            lector.close();
        } catch (IOException e) {
        }
        ordenarTiempos();
        eliminarArchivo();
        nuevoArchivo();
   }
    
    
    void arregloEstadisticas(int ttiempo, String tiempo, String fecha, String nombre) {
        Jugador jugador = new Jugador();
        jugador.setTtiempo(ttiempo);
        jugador.setTiempo(tiempo);
        jugador.setFecha(fecha);
        jugador.setNombre(nombre);
        this.arreglo_estadisticas.add(jugador);
    }


    int pasarTiempoaNumero(String texto_cronometro) {
        tiempo = "";
        for (i = 0; i < texto_cronometro.length(); i++) {
            if (texto_cronometro.charAt(i) != ':') {
                tiempo += texto_cronometro.charAt(i);
            }
        }
        return Integer.parseInt(tiempo);
    }
    
    
    void ordenarTiempos() {
        Jugador temporal;
        for(i=0;i<arreglo_estadisticas.size()-1;i++){
            for(j=i+1;j<arreglo_estadisticas.size();j++){
               if(arreglo_estadisticas.get(i).getTtiempo()>arreglo_estadisticas.get(j).getTtiempo()){
                   temporal = arreglo_estadisticas.get(i);
                   arreglo_estadisticas.set(i, arreglo_estadisticas.get(j));
                   arreglo_estadisticas.set(j, temporal);
               } 
            }
        }
    }

    
    public String Leer() {
        archivo_registros = "";
        try {
            FileReader lector = new FileReader(ruta);
            BufferedReader cont = new BufferedReader(lector);
            while ((registro = cont.readLine()) != null) {
                archivo_registros = archivo_registros + "\n" + registro;
            }
            lector.close();
            return archivo_registros;
        } catch (IOException e) {
            archivo_registros = "";
            return archivo_registros;
        }
    }
    
    
    String guardarRegistro(){
        try {
            File archivo_registros = new File(ruta);
            FileWriter escribir = new FileWriter(archivo_registros, true);
            escribir.write(registro += "\n");
            escribir.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el registro", "Archivo inexistente", 0);
        }
        return null;
    }
    
    
    void eliminarArchivo() {
        File fichero = new File(ruta);
        fichero.delete();
    }
    
    
    void nuevoArchivo(){
        for(i=0;i<arreglo_estadisticas.size();i++){
            this.registro = arreglo_estadisticas.get(i).getTiempo()+esp+arreglo_estadisticas.get(i).getFecha()+esp+
                    arreglo_estadisticas.get(i).getNombre();
            guardarRegistro();
        }
    }
    
}
