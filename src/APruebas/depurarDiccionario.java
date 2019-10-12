package APruebas;


//Esta clase se encargó de depurar el diccionario

/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
*/

public class depurarDiccionario {
    
    /*
    static int i, j; 
    static String rutaEntrada = ".//recursos/dic/es_ES/main.dic";
    static String rutaSalida = ".//recursos/dic/es_ES/diccionario.dic";
    static String linea, palabra="";
    static String cadena_alfabeto = "aeiouáéíóúübcdfghjklmnñpqrstvwxyz";
    
    public static void main(String[] args) throws IOException {
        try{
            FileReader f = new FileReader(rutaEntrada);
            BufferedReader b = new BufferedReader(f);
            while((linea = b.readLine())!=null) {
                for(i=0;i<linea.length();i++){
                    for(j=0;j<cadena_alfabeto.length();j++){
                        if(linea.charAt(i)==cadena_alfabeto.charAt(j)){
                            palabra += linea.charAt(i);
                        }
                    }
                }
                escribirArchivo(palabra);
                palabra="";
            }
            b.close();
        }catch(IOException e){FileWriter fw = new FileWriter(rutaEntrada, true);}
    }
    
    static void escribirArchivo(String palabra){
        try(
                FileWriter fw = new FileWriter(rutaSalida, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
        {
            out.println(palabra);
        } catch (IOException e) {}
    }
    */ 

 }