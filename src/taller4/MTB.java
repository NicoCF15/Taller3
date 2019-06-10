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
public class MTB extends Bicicleta{

    public MTB(double costoMantencion, String patente) {
        super(costoMantencion, patente);
        this.color = "rojo";
    }   
    @Override
    public double calcularCostoMantencion(double costoMantencion){
        double costo = 5000 + costoMantencion;
        return costo;
    }
}
