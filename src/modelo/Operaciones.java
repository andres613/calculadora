/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author debian
 */
public class Operaciones {
    
    float resultado;
    String suma, resta, multiplicacion, division, porcentaje;
    
    public float Operaciones(String operacion, float n1, float n2){
        
        switch (operacion){
            case "suma":
                resultado = n1+n2;
                break;
            case "resta":
                resultado = n1-n2;
                break;
            case "multiplicacion":
                resultado = n1*n2;
                break;
            case "division":
                resultado = n1/n2;
                break;
            case "porcentaje":
                resultado = n1*(n2/100);
                break;
        }        
        return resultado;
    }
    
}
