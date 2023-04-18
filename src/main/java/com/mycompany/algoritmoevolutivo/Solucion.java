/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoevolutivo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author cashd
 */
public class Solucion implements Comparable<Solucion>{
    public int[][] matriz = new int[8][8];
    private int aptitud;
    private float probabilidad;
    private Random random;
    
    public Solucion(){
         random = new Random();
    }
    
    public Solucion(Solucion sol){
        random = new Random();
        for (int i = 0; i < 8; i++){
            System.arraycopy(sol.matriz[i], 0, this.matriz[i], 0, sol.matriz.length);
        }
    }
    
    public int getAptitud() {
        return aptitud;
    }

    public void setAptitud(int aptitud) {
        this.aptitud = aptitud;
    }

    public float getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(float probabilidad) {
        this.probabilidad = probabilidad;
    }
 
    public void inicializarSolucion(){
        matriz = new int[8][8];
        int numeroReinas = 0;
        while(numeroReinas < 8){
            int x = random.nextInt(8);
            int y = random.nextInt(8);
            if (matriz[x][y] != 1){
                matriz[x][y] = 1;
                numeroReinas++;
            }
        }
    }
    public void imprimirTablero(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int calcularAtaquesHorizontales(int x, int y){
        int numeroAtaquesHorizontales = 0;
        for (int i = 0; i < 8; i++){
            if (i != y && matriz[x][i] == 1){
                numeroAtaquesHorizontales++;
            }
        }
        return numeroAtaquesHorizontales;
    }
    public int calcularAtaquesVerticales(int x, int y){
        int numeroAtaquesVerticales = 0;
        for (int i = 0; i < 8; i++){
            if (i != x && matriz[i][y] == 1){
                numeroAtaquesVerticales++;
            }
        }
        return numeroAtaquesVerticales;
    }
    
    public int calcularAtaquesDiagonales(int x, int y){
        int numeroAtaquesDiagonales = 0;
        for (int i = -7; i < 8; i++){
            if (x+i >= 0 && x+i < 8 && i!=0){
                if(y+i >=0 && y+i<8 && matriz[x+i][y+i] == 1){
                    numeroAtaquesDiagonales++;
                }
                if (y-i >= 0 && y-i<8 && matriz[x+i][y-i] == 1){
                    numeroAtaquesDiagonales++;
                }
            }
        }
        return numeroAtaquesDiagonales;
    }
    
    public int evaluarSolucion(){
        int numeroTotalDeAtaques = 0;
        for (int i = 0; i < 8; i++){
            for (int j=0; j<8; j++){
                if (matriz[i][j] == 1){
                    numeroTotalDeAtaques += calcularAtaquesVerticales(i, j) 
                        + calcularAtaquesHorizontales(i, j) + calcularAtaquesDiagonales(i, j); 
                }
            }
        }
        this.aptitud = numeroTotalDeAtaques;
        return numeroTotalDeAtaques;
    }
    
    public Solucion mutar(float probabilidad){
        float probabilidadEstaSolucion = (float)Math.random();
        Solucion nueva = new Solucion(this);
        if (probabilidadEstaSolucion < probabilidad){
            boolean reinaMovida = false;
            while (!reinaMovida){
                List<Integer> posicionesReinas = new ArrayList<>();
                int fila = random.nextInt(8);
                int contadorReinasFila = 0;
                for (int i = 0; i < 8; i++){
                    if (nueva.matriz[fila][i] == 1){
                        posicionesReinas.add(i);
                        contadorReinasFila++;
                    }
                }
                if (!posicionesReinas.isEmpty()){
                    int columna = posicionesReinas.get((int)(Math.random()*posicionesReinas.size()));
                    nueva.matriz[fila][columna] = 0;
                    do {
                        int columnaAleatoria = random.nextInt(8);
                        int filaAleatoria = random.nextInt(8);
                        if ((filaAleatoria != fila ||columnaAleatoria != columna) &&
                                nueva.matriz[filaAleatoria][columnaAleatoria] == 0){
                            nueva.matriz[filaAleatoria][columnaAleatoria] = 1;
                            reinaMovida = true;
                        }
                    }
                    while (!reinaMovida);
                }
            }
        }
        return nueva;
    }
    
    public Solucion cruzar(float probabilidadCruza, Solucion pareja){
        Solucion copia = new Solucion();
        float ordenCruza = (float)(Math.random());
        if (ordenCruza < 0.50){
            float probabilidadCruzaSolucion = (float)(Math.random());
            if (probabilidadCruzaSolucion < probabilidadCruza){
                for (int i = 0; i<4; i++){
                    System.arraycopy(matriz[i], 0, copia.matriz[i], 0, matriz[i].length);
                }
                for (int i = 4; i<8; i++){
                    System.arraycopy(pareja.matriz[i], 0, copia.matriz[i], 0, pareja.matriz[i].length);
                }
                /**
                 * Probada con Probabilidad de cruza recibida = 1 y orden de cruza menor a 0.5
                 */
                return copia;
            }
        }
        else {
            float probabilidadCruzaSolucion = (float)(Math.random());
            if (probabilidadCruzaSolucion < probabilidadCruza){
                for (int i = 0; i<4; i++){
                    System.arraycopy(pareja.matriz[i], 0, copia.matriz[i], 0, pareja.matriz[i].length);
                }
                for (int i = 4; i<8; i++){
                    System.arraycopy(matriz[i], 0, copia.matriz[i], 0, matriz[i].length);
                }
                /**
                 * Probada con Probabilidad de cruza recibida = 1 y orden de cruza mayor a 0.5
                 */
                return copia;
            }
            /**
             *  Probada con Probabilidad de cruza recibida = 0 y orden de cruza mayor a 0.5
             */
            else {
                copia.matriz = pareja.matriz;
                return copia;
            }
        }
        /**
         * Probado con Probabilida de cruza recibida = 0 y orden de cruza menor a 0.5
         */
        copia.matriz = this.matriz;
        System.out.println("Llegó hasta acá");
        return copia;
    }
    
    public void repararSolucion(){
        int numeroReinas = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (matriz[i][j] == 1){
                    numeroReinas++;
                }
            }
        }
        while(numeroReinas < 8){
            int columnaAleatoria = random.nextInt(8);
            int filaAleatoria = random.nextInt(8);
            if (matriz[filaAleatoria][columnaAleatoria] == 0){
                matriz[filaAleatoria][columnaAleatoria] = 1;
                numeroReinas++;
            }
        }
        while (numeroReinas > 8){
            List<Integer> posicionesReinas = new ArrayList<>();
            int fila = random.nextInt(8);
            int contadorReinasFila = 0;
            for (int i = 0; i < 8; i++){
                if (matriz[fila][i] == 1){
                    posicionesReinas.add(i);
                    contadorReinasFila++;
                }
            }
            if (!posicionesReinas.isEmpty()){
                int columna = posicionesReinas.get((int)(Math.random()*posicionesReinas.size()));
                matriz[fila][columna] = 0;
                numeroReinas--;
            }
        }
    }

    @Override
    public int compareTo(Solucion o) {
        return this.aptitud - o.getAptitud();
    }
    
}
