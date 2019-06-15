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
public class BicicletaUrbana extends Bicicleta{

    public BicicletaUrbana(double costoMantencion, String patente) {
        super(costoMantencion, patente);
        this.color = "verde";
    }

    @Override
    public double calcularCostoMantencion() {
        double costo = this.costoMantencion * 1.5;
        return costo;
    }
    
}
