package Silabacion;

public class Alfabeto {
 
    String cadena_alfabeto, cadena_vocal, cadena_vocal_debil, cadena_vocal_fuerte, cadena_consonante;
    String [] arreglo_hiato, arreglo_grupos_consonanticos;
    
    public void generarAlfabeto(){
        cadena_vocal = "aeiouáéíóúü";
        //Hiato: dos vocales juntas pero de diferentes silabas
        String [] array_hiato = {"aa","ae","aí","ao","aú","ea","ee","eí","eo","eú","ía","íe","ii","ío","oa","oe","oí","oo","oú","úa","úe","úo","uu"};
        arreglo_hiato = array_hiato;
        cadena_consonante = "bcdfghjklmnñpqrstvwxyz";
        cadena_alfabeto = cadena_vocal+cadena_consonante;
   }
    
    public void gruposConsonanticos(){
        String [] vector_grupos_consonanticos = {"bl","br","ch","cl","cr","dl","dr","fl","fr","gl","gn","gr","kl","ll","pl","pr","ps","qu","rr","tl","tr"};
        arreglo_grupos_consonanticos = vector_grupos_consonanticos;
    }

    public String getCadena_alfabeto() {return cadena_alfabeto;}
    public String getCadena_vocal() {return cadena_vocal;}
    public String getCadena_consonante() {return cadena_consonante;}
    public String[] getArreglo_hiato() {return arreglo_hiato;}
    public String[] getArreglo_grupos_consonanticos() {return arreglo_grupos_consonanticos;}
    
}
