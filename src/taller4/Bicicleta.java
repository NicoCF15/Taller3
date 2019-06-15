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
public abstract class Bicicleta {
    protected double costoMantencion;
    protected String patente;
    protected String color;

    public Bicicleta(double costoMantencion, String patente) {
        this.costoMantencion = costoMantencion;
        this.patente = patente;
        this.color = color;
    }

    public double getCostoMantencion() {
        return costoMantencion;
    }

    public String getPatente() {
        return patente;
    }

    public String getColor() {
        return color;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public abstract double calcularCostoMantencion();
}
