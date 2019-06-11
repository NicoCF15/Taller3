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
public class ListaRepartidor {
    private Nodo first;

    public ListaRepartidor() {
        this.first = null;
    }
    
    public boolean insertarRepartidor(Repartidor repartidor){
        Nodo nuevoNodo = new Nodo(repartidor);
        Nodo aux = this.first;
        if(this.first == null){
            this.first = nuevoNodo;
            return true;
        }
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
            return false;
        }
        
        // caso 2, hay 1 elemento
        if(this.first.getRepartidor().getId().equals(repartidor.getId()) && this.first.getSiguiente() == null){
            this.first = null;
            return true;
        }
        
        // caso 3, hay mas de 1 elemento, y eliminamos al comienzo de la lista
        if(this.first.getRepartidor().getId().equals(repartidor.getId()) && this.first != null){
            this.first = this.first.getSiguiente();
            return true;
        }
        
        // caso 4, hay mas de un elemento y eliminamos en cualquier otra posicion
        //creamos nodo auxiliar para recorrer la lista
        Nodo aux = this.first;
        while(aux.getSiguiente() != null){
            if(aux.getRepartidor().getId().equals(repartidor.getId())){
                aux.setSiguiente(aux.getSiguiente());
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public Nodo getFirst() {
        return first;
    }

    public void setFirst(Nodo first) {
        this.first = first;
    }
    
}
