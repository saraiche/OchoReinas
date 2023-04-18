package com.mycompany.algoritmoevolutivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cashd
 */
public class Poblacion {
    private int tamanio;
    private int proporcionPadres;
    private int generaciones;
    private float probabilidadMutacion;
    private float probabilidadCruza;
    private ArrayList<Solucion> poblacion;
    private ArrayList<Solucion> padres;
    private ArrayList<Solucion> hijos;

    public Poblacion(int tamanio, int proporcionPadres, int generaciones, float probabilidadMutacion, float probabilidadCruza) {
        this.tamanio = tamanio;
        this.proporcionPadres = proporcionPadres;
        this.generaciones = generaciones;
        this.probabilidadMutacion = probabilidadMutacion;
        this.probabilidadCruza = probabilidadCruza;
        this.poblacion = new ArrayList<Solucion>();
        this.padres = new ArrayList<Solucion>();
        this.hijos = new ArrayList<Solucion>();
    }
    
    
    
    public void IniciarAlgoritmoEvolutivo(){
        for (int i = 0; i < tamanio; i++){
            Solucion solucion = new Solucion();
            solucion.inicializarSolucion();
            solucion.evaluarSolucion();
            poblacion.add(solucion);
        }
        poblacion.sort((Solucion sol1, Solucion sol2) -> Double.compare((double)sol1.getAptitud(), (double)sol2.getAptitud()));
        int generacionActual = 1;
        boolean solucionEncontrada = false;
        while (!solucionEncontrada){
            for (int i = 0; i < tamanio; i++){
               float decremento = 1.0f - ((i+1.0f) * (1.0f / (tamanio + 1 )));
               poblacion.get(i).setProbabilidad(decremento);
            }
            int padresGenerados = 0;
            
            int iterador = tamanio-1;
            while (padresGenerados < (tamanio * (float)(proporcionPadres/100.0f))){
                float aleatorio = (float)Math.random();
                float decremento = 1.0f - ((iterador+1.0f) * (1.0f / (tamanio + 1 )));
                if (aleatorio < decremento){
                    padres.add(poblacion.get(iterador));
                    padresGenerados++;
                }
                iterador = (iterador+1)%tamanio;
            }
            for (int i = 0; i < padres.size()-1; i+=2){
                Solucion solCruza = new Solucion();
                solCruza = padres.get(i).cruzar(probabilidadCruza, padres.get(i+1));
                solCruza.repararSolucion();
                Solucion solMuta = new Solucion(solCruza);
                solMuta = solMuta.mutar(probabilidadMutacion);
                int aptitudCruz = solCruza.evaluarSolucion();
                int aptitudMuta = solMuta.evaluarSolucion();
                hijos.add(solCruza);
                hijos.add(solMuta);
            }
            hijos.addAll(poblacion);
            Collections.sort(hijos);

            
            while (hijos.size() > tamanio){
                hijos.remove(hijos.size()-1);
            }
            poblacion.clear();
            padres.clear();
            
            poblacion.addAll(hijos);
            hijos.clear();
            
            System.out.println("Generación: " + generacionActual);
         //   poblacion.get(0).imprimirTablero();
            System.out.println("Aptitud: " + poblacion.get(0).getAptitud()+"\n");
            if (poblacion.get(0).getAptitud() == 0){
                solucionEncontrada = true;
            }
            generacionActual++;
        }
    }
}
