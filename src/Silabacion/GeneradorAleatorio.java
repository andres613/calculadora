package Silabacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GeneradorAleatorio {
    
    /* ruta es la variable que guarda la ubicación del directorio que contiene el archivo con el diccionario.
    Mientras que contador guarda las iteraciones para comparar y salir del ciclo indicando la posición de la línea*/
    String ruta = ".//src/recursos/dic/es_ES/diccionario.dic", linea;
    String [] palabra_tipo = {"",""};
    int contador=0,numeroLinea;
    
    public String[] GenerarAleatoriamente(char p) throws IOException {
        if(p=='s'){
            numeroLinea = (int) Math.floor(Math.random()*100);
        } else {
            numeroLinea = (int) Math.floor(Math.random()*(153-100+1)+100);//genera numeros aleatorios (HASTA-DESDE+1)+DESDE incluidos DESDE y HASTA
            if(numeroLinea>152){
                palabra_tipo[1] = "s";
            } else {
                if(numeroLinea>136){
                    palabra_tipo[1] = "e";
                }else{
                    if(numeroLinea>122){
                        palabra_tipo[1] = "g";
                    }else{
                        palabra_tipo[1] = "a";
                    }
                }
            }
        }
        try{
            FileReader f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            while((linea = b.readLine())!=null) {
                if(numeroLinea == contador){
                    break;
                }
                contador++;
            }
            contador=0;
            b.close();
        }catch(IOException e){FileWriter fw = new FileWriter(ruta, true);}
        palabra_tipo[0] = linea;
        //return linea;
        return palabra_tipo;
    }
    
}
