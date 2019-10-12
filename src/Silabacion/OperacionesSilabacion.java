package Silabacion;


public class OperacionesSilabacion {
    
    //Objeto del tipo la clase Alfabeto para hacer usu de sus métodos
    Alfabeto bdsilabacion = new Alfabeto();
    /*Se crean las variables: cadena_alfabeto, usada para guardar todo el alfabeto; cadena_letras, empleada para guardar
    o solo las vocales o solo las consonantes (dependiendo del indice de arreglo_vocal_consonante) y finalmente, subpalabra, que tomará inicialmente el valor de palabra, e irá
    transformandose en el resíduo faltante por analizar o silabar*/
    String cadena_alfabeto="", cadena_letras, subpalabra="";
    //Arreglo vocal consonantes, guarda en una posicion a las vocales y en la otra a las consonantes
    String [] arreglo_vocal_consonante;
    //Este arreglo guardará verdadero cada vez que una letra sea vocal, y falso cuando no lo sea
    boolean [] arreglo_boolean_vocales;
    //TAMANO es algo así como una constate que almacenará el tamaño real de palabra
    int TAMANO;
    //i, j y k son contadores para los ciclos. Entre tanto, posicion_arreglo_alfabeto soma 0 o 1, las posiciones de arreglo_vocal_consonante
    int i, j, k, posicion_arreglo_alfabeto;
    //
    boolean si_palabra = false, existe_caracter = true;
    boolean no_hayvocal;
    // Esta es la variable que finalmente va a guardar la palabra silabada
    public String palabra_silabada;
    
    
    public boolean operacionesSilabacion(String palabra, String cadena_alfabeto, String [] arreglo_vocal_consonante){
        this.subpalabra = palabra;
        this.TAMANO = palabra.length();
        this.cadena_alfabeto = cadena_alfabeto;
        this.arreglo_vocal_consonante = arreglo_vocal_consonante;
        /* Crear arreglo de tipo boolean del tamaño de la palabra de tal forma que cada verdadero guardado
        es una vocal en la palabra*/
        arreglo_boolean_vocales = new boolean [subpalabra.length()];
        si_palabra = comprobarSiPalabra();
        if(si_palabra){
            arreglo_boolean_vocales = composicionPalabra(); 
            no_hayvocal = soloConsonantes();
            // se evalua si se encontro una vocal para cpontinuar con el proceso
            if(no_hayvocal){
                if(palabra.equals("y")){
                    palabra_silabada = palabraEnSilabas();
                    setPalabraSilabada(palabra_silabada);
                    return true;
                }
                else
                    return false;
            }else{
                palabra_silabada = palabraEnSilabas();
                // Se envía la palabra_silabada para capturarla desde la interfaz y mostrarla
                setPalabraSilabada(palabra_silabada);
                return true;
            }
        }else{
            return false;
        }
    }
    
    
    public void setPalabra(String palabra){this.subpalabra = palabra;}
    
    
    boolean comprobarSiPalabra(){
        si_palabra = true;
        for(i=0;i<subpalabra.length();i++){
            for(j=0;j<cadena_alfabeto.length();j++){
                if(subpalabra.charAt(i)==cadena_alfabeto.charAt(j)){
                    existe_caracter = true;
                }
            }
            if(existe_caracter==false){
                si_palabra = false;
                break;
            }else{
                existe_caracter = false;
            }
        }
        return si_palabra;
    }
    
    
    boolean [] composicionPalabra(){
        
        for(int f=0;f<TAMANO;f++)
        {this.arreglo_boolean_vocales[f]=false;}
        // para recorrer uno a uno los caracteres de la palabra ingresada
        for(i=0;i<subpalabra.length();i++){
            // en este apartado se miran las dos posiciones del arreglo dado que la primera contiene vocales y la segunda consonantes
            for(j=0;j<arreglo_vocal_consonante.length;j++){
                //damos a cadena_letras lo contenido en el arreglo en la posicion j, es decir primero las vocales luego el alfabeto
                cadena_letras = arreglo_vocal_consonante[j];
                /* para recorrer uno a uno los caracteres de "letras" y compararlo uno a uno con los caracteres de "palabras"
                así se podría determinar en la comparación si es una vocal o una consonante */
                for(k=0;k<cadena_letras.length();k++){
                    if(subpalabra.charAt(i)==cadena_letras.charAt(k)){
                        /* segun el indice del arreglo en el que se esté, asi mismo se sabrá si es una vocal o no*/
                        posicion_arreglo_alfabeto = j;
                        break;
                    }
                }  
            }
            if(posicion_arreglo_alfabeto%2==0){
                arreglo_boolean_vocales[i] = true;
            }else{
                arreglo_boolean_vocales [i] = false;
            }
        }
        return arreglo_boolean_vocales;
    } 
    
    
    boolean soloConsonantes(){
        no_hayvocal = true;
        // asegurar que al menos haya una vocal en la palabra
        for(int v = 0; v<arreglo_boolean_vocales.length;v++){
            if(arreglo_boolean_vocales[v])
                no_hayvocal = false;
        }
        return no_hayvocal;
    }
    
    
    String palabraEnSilabas(){
        String palabra = subpalabra;
        String silaba="", silaba_s="",temp="";
        
        for(int i=0; i<TAMANO;i++){
            /*Crear la nueva subpalabra con la parte no analizada, es decir, cortando la sílaba 
            (como inicialmente no hay silaba, subpalabra es del tamaño de la palabra)*/
            subpalabra = subpalabra.substring(silaba.length(),subpalabra.length());//Se extrae la sílaba
            this.arreglo_boolean_vocales = composicionPalabra();
            silaba = componerSilaba();
            if(i==0){
                silaba_s += silaba;
            }else{
                if(soloConsonantes()){
                    silaba_s += subpalabra;
                }else{
                    if(arreglo_boolean_vocales[silaba.length()-1] && arreglo_boolean_vocales[silaba.length()]){
                        String vocalhiato = subpalabra.charAt(silaba.length()-1)+""+palabra.charAt(silaba.length());
                        if(hiato(vocalhiato)){
                            silaba_s += "-"+silaba;
                        }else{
                            silaba_s += "-"+silaba;
                        }
                    }else{/*Arropa el caso en el que silaba solo es una consonante*/
                        temp = subpalabra;
                        subpalabra = silaba;
                        this.arreglo_boolean_vocales = composicionPalabra();
                        if(soloConsonantes()){
                            silaba_s += subpalabra;
                        }else{
                            silaba_s +="-"+silaba;
                        }
                        subpalabra = temp;
                    }
                }
            }
            i += silaba.length()-1;
        }
        return silaba_s;
    }
    
    
    String componerSilaba (){
        String cadena="", silaba = "";
        if(subpalabra.length()<3){
            if(subpalabra.length()==2){
                if (arreglo_boolean_vocales[0] && arreglo_boolean_vocales[1]){
                    if (hiato(subpalabra)){
                        silaba = subpalabra.charAt(0)+"";
                    }else{   
                        silaba = subpalabra;
                    }
                }else{
                    silaba = subpalabra;
                }
            }else{
                silaba = subpalabra;
            }
        }// se ha silabado para el caso en que sea una palabra de menos de 3 caracteres
        else{
            if(arreglo_boolean_vocales[0]){ //primer letra una vocal
                if(arreglo_boolean_vocales[1]){  //primer y segunda letras vocales
                    if(arreglo_boolean_vocales[2]){  //tres primeras letras vocales
                        if(hiato(subpalabra.charAt(0)+""+subpalabra.charAt(1))){
                            silaba = subpalabra.charAt(0)+"";
                        }else{
                            if(hiato(subpalabra.charAt(1)+""+subpalabra.charAt(2))){
                                silaba = subpalabra.substring(0,2);
                            }else{
                                silaba = subpalabra.substring(0,3);
                            }
                        }
                    }else{ // dos primeras letras vocales seguida de una consonante
                        if(hiato(subpalabra.charAt(0)+""+subpalabra.charAt(1))){
                            silaba = subpalabra.charAt(0)+"";
                        }else{
                            silaba = subpalabra.substring(0,2);
                        }
                    }
                }else{ // primera letra una vocal seguida de una consonante
                    if(arreglo_boolean_vocales[2]){  //una consonante entre dos vocales
                        if(subpalabra.charAt(1)=='h'){ // una vocal seguida de una "h" seguida de una consonante
                            if(hiato(subpalabra.charAt(0)+""+subpalabra.charAt(2))){
                                silaba = subpalabra.charAt(0)+"";
                            }else{
                                silaba = subpalabra.substring(0,3);
                            }
                        }else{
                            silaba = subpalabra.charAt(0)+"";
                        }
                    }else{ // primera letra una vocal seguida de dos consonantes
                            if(grupoConsonantico(subpalabra.charAt(1)+""+subpalabra.charAt(2))){// se examina si la union de la segunda y tercer letra hace parte de algun grupo consonatico
                                silaba = subpalabra.charAt(0)+"";
                            }else{
                                silaba = subpalabra.substring(0,2);
                            }
                    }
                }
            } /* Se ha estraido la primer sílaba para una palabra mayor a dos letras, en los casos:
            vocal-vocal-vocal, vocal-vocal-consonante, vocal-consonante-vocal, vocal-consonante-consonante*/
            else{ // primera letra una consonante
                if(arreglo_boolean_vocales[1]){ //primera letra una consonante, seguida de una vocal
                    if(arreglo_boolean_vocales[2]){ // primera letra una consonante, seguida de dos vocales
                        cadena = subpalabra.substring(0,3);
                        if(cadena.equals("que") || cadena.equals("qui") 
                                || cadena.equals("gue") || cadena.equals("gui")){
                            silaba = subpalabra.substring(0,3);
                        }else{
                            if(hiato(subpalabra.charAt(1)+""+subpalabra.charAt(2))){
                                silaba = subpalabra.substring(0,2);
                            }else{
                                silaba = subpalabra.substring(0,3);
                            }
                        }
                    }else{ // vocal entre dos consonantes
                        if(subpalabra.length()>3){
                            if(arreglo_boolean_vocales[3]){//como es del tipo CVCV, se sabe que C esta con la V a su derecha
                                silaba = subpalabra.substring(0,2);
                            }else{
                                if(grupoConsonantico(subpalabra.charAt(2)+""+subpalabra.charAt(3))){
                                    silaba = subpalabra.substring(0,2);
                                }else{
                                    silaba = subpalabra.substring(0,3);
                                }
                            }
                        }
                        else{
                            silaba = subpalabra.substring(0,3);
                        }
                    }
                }else{ // las dos primeras letras, consonantes
                    if(arreglo_boolean_vocales[2]){ // las dos primeras letras son consonantes, seguida de una vocal
                        /* Se examina si la union de la primer y segunda letra 
                        hace parte de algun grupo consonatico*/                        
                        if(grupoConsonantico(subpalabra.charAt(0)+""+subpalabra.charAt(1))){
                            silaba = subpalabra.substring(0,3);
                        }else{
                            silaba = subpalabra.charAt(0)+"";
                        }
                    }else{ // las tres primeras son consonantes
                        /* Se examina si la union de la segunda y tercer letra 
                        hace parte de algun grupo consonatico*/
                        if(grupoConsonantico(subpalabra.charAt(1)+""+subpalabra.charAt(2))){
                            silaba = subpalabra.charAt(0)+"";
                        }else{
                            silaba = subpalabra.charAt(0)+"";
                        }
                    }
                }
            }
        }
        return silaba;
    }
    
    
    private boolean hiato(String vocalhiato){
        bdsilabacion.generarAlfabeto();
        // Traer los hiatos y guardarlos en arreglo_hiato
        String [] arreglo_hiato = bdsilabacion.getArreglo_hiato();
        // Se establece una variable de tipo booleano
        boolean hiato = false;
        for(int v=0; v<arreglo_hiato.length;v++){
            if(vocalhiato.equals(arreglo_hiato[v])){
                hiato = true;
            }
        }
        return hiato;
    }
    
    
    private boolean grupoConsonantico(String grupo){
        boolean grupoconsonantico = false;
        bdsilabacion.gruposConsonanticos();
        String [] arreglo_grupos_consonanticos = bdsilabacion.getArreglo_grupos_consonanticos();
        for(int gc=0; gc<arreglo_grupos_consonanticos.length;gc++){
            if(grupo.equals(arreglo_grupos_consonanticos[gc])){
                grupoconsonantico = true;
            }
        }
        return grupoconsonantico;
    }
    
    public void setPalabraSilabada(String palabra_silabada) {this.palabra_silabada = palabra_silabada;}
    public String getPalabraSilabada() {return palabra_silabada;} 
    public void setCadena_alfabeto(String alfabeto_completo) {this.cadena_alfabeto = cadena_alfabeto;}
    public String getCadena_alfabeto() {return cadena_alfabeto;}
    
}