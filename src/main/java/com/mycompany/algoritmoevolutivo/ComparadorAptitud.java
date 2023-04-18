/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoevolutivo;
import java.util.Comparator;
/**
 *
 * @author cashd
 */
public class ComparadorAptitud implements Comparator<Solucion>{

    @Override
    public int compare(Solucion sol1, Solucion sol2) {
        int resultado = 0;
        if (sol1.getAptitud() < sol2.getAptitud()){
            resultado =  -1;
        }
        else{
            resultado = 1;
        }
        return resultado;
    }
    
}
