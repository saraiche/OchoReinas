/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.algoritmoevolutivo;

import java.util.ArrayList;

/**
 *
 * @author cashd
 */
public class AlgoritmoEvolutivo {

    public static void main(String[] args) {
       Poblacion poblacion = new Poblacion(100,40,10000,1,1);
        poblacion.IniciarAlgoritmoEvolutivo();      
        
       /* Solucion sol1 = new Solucion();
        sol1.inicializarSolucion();
        sol1.imprimirTablero();
        System.out.println("");
        Solucion sol2 = new Solucion();
        sol2.inicializarSolucion();
        sol2.imprimirTablero();
        for(int i = 0; i < 5; i ++){
            System.out.println("**** CRUZA " + (i+1) + "******");
            sol1 = sol1.cruzar(1, sol2);
            sol1.imprimirTablero();
            System.out.println("");
            sol2 = sol2.cruzar(1, sol1);
            sol2.imprimirTablero();
            System.out.println("");
        }*/
    }
}
