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
public class BicicletaRuta extends Bicicleta{

    public BicicletaRuta(double costoMantencion, String patente) {
        super(costoMantencion, patente);
        this.color = "amarillo";
    }

    @Override
    public double calcularCostoMantencion() {
        double costo = 12 * this.costoMantencion;
        return costo;
    }
    
}
