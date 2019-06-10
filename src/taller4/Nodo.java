/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;

/**
 *
 * @author ncofr
 */
public class Nodo {
    private Repartidor repartidor;
    private Nodo siguiente;

    public Nodo(Repartidor repartidor) {
        this.repartidor = repartidor;
        this.siguiente = null;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
