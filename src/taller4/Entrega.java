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
public class Entrega {
    private Repartidor repartidor;
    private int idEnvio;
    private String patenteBicicleta;
    private double ganancia;

    public Entrega(Repartidor repartidor, int idEnvio, String patenteBicicleta, double ganancia) {
        this.repartidor = repartidor;
        this.idEnvio = idEnvio;
        this.patenteBicicleta = patenteBicicleta;
        this.ganancia = ganancia;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public String getPatenteBicicleta() {
        return patenteBicicleta;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setPatenteBicicleta(String patenteBicicleta) {
        this.patenteBicicleta = patenteBicicleta;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }
    
}
