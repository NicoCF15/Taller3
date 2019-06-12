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
public class Repartidor {
    private String id;
    private String nombre;
    private int edad;
    private String direccion;
    //aniadido despues
    private String patente;
    private Bicicleta bici;
    private Envio envio;
    private boolean disponibilidad;

    public Repartidor(String id, String nombre, int edad, String direccion, String patente) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.patente = patente;
        this.envio = null;
        this.bici = null;
        this.disponibilidad = true;
    }

    public String getPatente() {
        return patente;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public Bicicleta getBicicleta() {
        return bici;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bici= bicicleta;
    }
    // aniadido despues
    public void setBicicleta(String patente, double costoMantencion, String tipoBicicleta){
        /* falta costo de mantencion
        Bicicleta bicicleta;
        if(tipoBicicleta.equals("bicicletamontaña")){
            
        }
        if(tipoBicicleta.equals("bicicletaruta")){
            
        }
        if(tipoBicicleta.equals("bicicletaurbana")){
            
        }
        this.bici = bicicleta;
        */
        
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
}
