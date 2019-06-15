/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;

import ucn.StdOut;

/**
 *
 * @author ncofr
 */
public class ListaRepartidor {
    private Nodo first;

    public ListaRepartidor() {
        this.first = null;
    }
    
    public boolean insertarRepartidor(Repartidor repartidor){
        Nodo nuevoNodo = new Nodo(repartidor);
        Nodo aux = this.first;
        // inserta si no hay nada
        if(this.first == null){
            this.first = nuevoNodo;
            return true;
        }
        // inserta si esta con datos
        else{
            while(aux.getSiguiente() != null){
               aux = aux.getSiguiente();
            }
        }
        aux.setSiguiente(nuevoNodo);
        return true;     
    }
    
    public boolean eliminarRepartidor(Repartidor repartidor){
        // caso 1, no hay elementos
        if(this.first == null){
            StdOut.print("caso1");      
            return false;
        }
        
        // caso 2, hay 1 elemento
        if(this.first.getRepartidor().getId().equals(repartidor.getId()) && this.first.getSiguiente() == null){
            this.first = null;
            StdOut.print("caso2");      
            return true;
        }
        
        // caso 3, hay mas de 1 elemento, y eliminamos al comienzo de la lista
        if(this.first.getRepartidor().getId().equals(repartidor.getId()) && this.first != null){
            this.first = this.first.getSiguiente();
            StdOut.print("caso3");      
            return true;
        }
        
        // caso 4, hay mas de un elemento y eliminamos en cualquier otra posicion
        //creamos nodo auxiliar para recorrer la lista
        Nodo aux = this.first;
        Nodo auxSiguiente = this.first.getSiguiente();
        while(auxSiguiente.getSiguiente() != null){
            if(auxSiguiente.getRepartidor().getId().equals(repartidor.getId())){
                aux.setSiguiente(auxSiguiente.getSiguiente());
                StdOut.println("caso 4");
                return true;
            }
            // caso 5: llega al final de la lista y es el elmento a eliminar
            if(auxSiguiente.getSiguiente().getSiguiente()== null && auxSiguiente.getSiguiente().getRepartidor().getId().equalsIgnoreCase(repartidor.getId())){
                auxSiguiente.setSiguiente(auxSiguiente.getSiguiente().getSiguiente());
                StdOut.println("caso 5");
                return true;
            }
            aux = auxSiguiente;
            auxSiguiente = auxSiguiente.getSiguiente();
        }
        StdOut.println("caso 0");
        return false;
    }

    public Nodo getFirst() {
        return first;
    }

    public void setFirst(Nodo first) {
        this.first = first;
    }
    
}
