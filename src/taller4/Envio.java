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
class Envio {
    private String emisor;
    private String receptor;
    private String direccionEmisor;
    private String direccionReceptor;
    private String nombreZona;
    // aniadido despues
    private int id;
    private boolean estado;

    public Envio(String emisor, String receptor, String direccionEmisor, String direccionReceptor,String nombreZona) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.direccionEmisor = direccionEmisor;
        this.direccionReceptor = direccionReceptor;
        this.nombreZona = nombreZona;
        this.id = 0;
        // al crear un envio, este se encuentra en la cola
        this.estado = false;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getDireccionEmisor() {
        return direccionEmisor;
    }

    public String getDireccionReceptor() {
        return direccionReceptor;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public int getId() {
        return id;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public void setDireccionEmisor(String direccionEmisor) {
        this.direccionEmisor = direccionEmisor;
    }

    public void setDireccionReceptor(String direccionReceptor) {
        this.direccionReceptor = direccionReceptor;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
