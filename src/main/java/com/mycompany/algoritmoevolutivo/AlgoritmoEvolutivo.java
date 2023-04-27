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
       Poblacion poblacion = new Poblacion(30,100,10000,1,1);
       poblacion.IniciarAlgoritmoEvolutivo();      
    }
}
