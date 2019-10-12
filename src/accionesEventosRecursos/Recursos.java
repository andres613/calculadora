package accionesEventosRecursos;

public class Recursos extends Thread{
    
    public String [] r_personaje = {"","","","","","","",""};
    public String
            r_fondo_escenario1="./src/recursos/Escenarios/fondoUno.png",
            r_fondo_escenario2="./src/recursos/Escenarios/fondoDos.png",
            r_habGuion = "./src/recursos/Habilidades/Habilidades/guion.png",
            r_habTilde = "./src/recursos/Habilidades/Habilidades/tilde.png",
            r_habilidadGuion = "./src/recursos/Habilidades/HabilidadesMenu/guion.png",
            r_habilidadTilde = "./src/recursos/Habilidades/HabilidadesMenu/tilde.png";
    
    public String [] personajeSeleccionado(char p_ninoa){
        switch (p_ninoa){
            case 'r':
                r_personaje[0] = "./src/recursos/Personaje/nina/nina_up_run.gif";
                r_personaje[1] = "./src/recursos/Personaje/nina/nina_down_run.gif";
                r_personaje[2] = "./src/recursos/Personaje/nina/nina_left_run.gif";
                r_personaje[3] = "./src/recursos/Personaje/nina/nina_right_run.gif";
                r_personaje[4] = "./src/recursos/Personaje/nina/nina_up_stop.png";
                r_personaje[5] = "./src/recursos/Personaje/nina/nina_down_stop.png";
                r_personaje[6] = "./src/recursos/Personaje/nina/nina_left_stop.png";
                r_personaje[7] = "./src/recursos/Personaje/nina/nina_right_stop.png";
                break;
            case 'l':
                r_personaje[0] = "./src/recursos/Personaje/nino/nino_up_run.gif";
                r_personaje[1] = "./src/recursos/Personaje/nino/nino_down_run.gif";
                r_personaje[2] = "./src/recursos/Personaje/nino/nino_left_run.gif";
                r_personaje[3] = "./src/recursos/Personaje/nino/nino_right_run.gif";
                r_personaje[4] = "./src/recursos/Personaje/nino/nino_up_stop.png";
                r_personaje[5] = "./src/recursos/Personaje/nino/nino_down_stop.png";
                r_personaje[6] = "./src/recursos/Personaje/nino/nino_left_stop.png";
                r_personaje[7] = "./src/recursos/Personaje/nino/nino_right_stop.png";
                break;
        }
        return r_personaje;
    }
    
}