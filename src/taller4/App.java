/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;
import java.io.IOException;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdOut;
/**
 *
 * @author ncofr
 */
public class App {
    // para asignar envio, recorrer lista repartidores y pegarse sus instanceof, o de otra manera, comparar colores
    public static void main(String[] args) {
        SistemaEnviosImpl sistema = new SistemaEnviosImpl();
        sistema.correrApp();
    }
}
