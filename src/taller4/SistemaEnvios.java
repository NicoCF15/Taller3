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
public interface SistemaEnvios {
    public void contratarRepartidor(String nombreRepartidor, int edadRepartidor, 
            String direccionRepartidor, String idRepartidor, String tipoBicicleta, 
            String patenteBicicleta );
    public boolean despedirRepartidor(String idRepartidor);
    public void modificarRepartidor(String idRepartidor,String nombre, int edad, String direccion);
    public void modificarPatente(String idRepartidor, String patenteNueva);
    public void cambiarBicicleta(String idRepartidor,String patente, String tipo);
    public boolean realizarEnvio(String nombreEmisor, String nombreReceptor, 
            String direccionEmisor, String direccionReceptor, String zonaReceptor);
    public void recepcionRepartidor(String idRepartidor);
    public void cierreCaja();
    public void salir();
    
}
