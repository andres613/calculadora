package Modelo;

public class Jugador {
    int ttiempo;
    String tiempo;
    String fecha;
    String nombre;
    
    public Jugador(){}
    
    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTtiempo() {
        return ttiempo;
    }

    public void setTtiempo(int ttiempo) {
        this.ttiempo = ttiempo;
    }

}
