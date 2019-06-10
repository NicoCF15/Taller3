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
public class Zona {
    private String nombre;
    private String color;
    private double tasaEnvio;

    public Zona(String nombre, String color, double tasaEnvio) {
        this.nombre = nombre;
        this.color = color;
        this.tasaEnvio = tasaEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public double getTasaEnvio() {
        return tasaEnvio;
    }
     
}
