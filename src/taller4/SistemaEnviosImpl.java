/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ucn.StdOut;
import ucn.ArchivoSalida;
import ucn.Registro;
import ucn.ArchivoEntrada;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import ucn.StdIn;

/**
 *
 * @author ncofr
 */
public class SistemaEnviosImpl implements SistemaEnvios{
    private ListaRepartidor listaRepartidores;
    private ArrayList<Envio> listaEnvios;
    private LinkedList<Zona> listaZonas;
    private ArrayList<Entrega> listaEntregas;

    public SistemaEnviosImpl() {
        this.listaRepartidores = new ListaRepartidor();
        this.listaEnvios = new ArrayList<>();
        this.listaZonas = new LinkedList<>();
        this.listaEntregas = new ArrayList<>();
    }
    public void correrApp(){
       almacenarInfo("Bicicletas_Urbana.txt");
       almacenarInfo("Bicicletas_MTB.txt");
       almacenarInfo("Bicicletas_Ruta.txt");
       almacenarInfo("Repartidores.txt");
       almacenarInfo("Zonas.txt");
       /*
       StdOut.println("Ingrese el de que tipo es su bicicleta (bicicleta urbana, de montaña o ruta)");
       String tipo = StdIn.readString().toLowerCase().trim();
       while(!tipo.equals("bicicletamontaña")||!tipo.equals("bicicletaruta")||!tipo.equals("bicicletaurbana")){
            String tipo = StdIn.readString().toLowerCase().trim(); 
       }
       */
}
    
    @Override
    public void contratarRepartidor(String nombreRepartidor, int edadRepartidor, String direccionRepartidor, String idRepartidor, String tipoBicicleta, String patenteBicicleta) {
        //falta implementar (afuera del subprograma se pregunta lo que le ingresa y dentro los datos de la bici o sino nos raja)
        Repartidor nuevoRepartidor = new Repartidor(idRepartidor,nombreRepartidor,edadRepartidor, direccionRepartidor, patenteBicicleta);
        this.listaRepartidores.insertarRepartidor(nuevoRepartidor);

    }

    @Override
    public boolean despedirRepartidor(String idRepartidor) {
        /* porsiacaso
        Nodo aux = this.listaRepartidores.getFirst();
        while(!aux.getRepartidor().getId().equals(idRepartidor) && aux.getSiguiente() != null){
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(aux.getSiguiente());
        */
        Repartidor repartidor = new Repartidor(idRepartidor,null,0,null,null);
        boolean sePudo = this.listaRepartidores.eliminarRepartidor(repartidor);
        return sePudo;
    }

    @Override
    public void modificarRepartidor(String idRepartidor,String nombre, int edad, String direccion) 
    {
        // Como ya se valido anteriormente no se valida denuevo, solo se busca
        Nodo nodoCambio = listaRepartidores.getFirst();
        while(!nodoCambio.getRepartidor().getId().equalsIgnoreCase(idRepartidor)){
            nodoCambio = nodoCambio.getSiguiente();
        }
        
        nodoCambio.getRepartidor().setNombre(nombre);
        nodoCambio.getRepartidor().setEdad(edad);
        nodoCambio.getRepartidor().setDireccion(direccion);
        
        
    }

    @Override
    public void modificarPatente(String idRepartidor,String patenteNueva) {
        Nodo nodoCambio = listaRepartidores.getFirst();
        while(!nodoCambio.getRepartidor().getId().equalsIgnoreCase(idRepartidor)){
            nodoCambio = nodoCambio.getSiguiente();
        }
        nodoCambio.getRepartidor().getBicicleta().setPatente(patenteNueva);
        
    }

    @Override
    public void cambiarBicicleta(String idRepartidor,double CostoMantencion,String patente, String tipo) {
        if(tipo.equalsIgnoreCase("amarillo")){
            BicicletaRuta nuevaBicicleta = new BicicletaRuta()
        } 
        Nodo nodoCambio = listaRepartidores.getFirst();
        while(!nodoCambio.getRepartidor().getId().equalsIgnoreCase(idRepartidor)){
            nodoCambio = nodoCambio.getSiguiente();
        }
        nodoCambio.getRepartidor().getBicicleta()       
    }

    @Override
    public boolean realizarEnvio(String nombreEmisor, String nombreReceptor, String direccionEmisor, String direccionReceptor, String zonaReceptor) {
        Nodo aux = this.listaRepartidores.getFirst();
        for(int i = 0; i<this.listaZonas.size();i++){
            if(this.listaZonas.get(i).getNombre().equals(zonaReceptor)){
                while(aux.getRepartidor().getDisponibilidad() == false && !aux.getRepartidor().getBicicleta().getColor().equals(listaZonas.get(i).getColor())){
                    aux = aux.getSiguiente();
                    // recorrio toda la lista y no encontro la igualdad de colores
                    if (aux.getSiguiente() == null)
                        return false;
                }
                // si salimos del while significa que se puede realizar el envio
                Envio nuevoEnvio = new Envio(nombreEmisor,nombreReceptor,direccionEmisor,direccionReceptor,zonaReceptor);
                aux.getRepartidor().setDisponibilidad(false);
                this.listaEnvios.add(nuevoEnvio);
                int idEnvio = this.listaEnvios.indexOf(nuevoEnvio)+1;
                nuevoEnvio.setId(idEnvio);
                aux.getRepartidor().setEnvio(nuevoEnvio);
                return true;
            }
        }
        // la empresa no cuenta con la bicicleta necesaria para realizar el envio
        return false;
    }

    @Override
    public void recepcionRepartidor(String idRepartidor) {
        Nodo aux = this.listaRepartidores.getFirst();
        while(aux.getSiguiente() != null){
            if(aux.getRepartidor().getId().equals(idRepartidor)){
                aux.getRepartidor().setDisponibilidad(true);
                aux.getRepartidor().getEnvio().setEstado(true);
                aux.getRepartidor().setEnvio(null);
                for(int i = 0;i<this.listaEnvios.size();i++){
                    for(int j = 0;j<this.listaZonas.size();j++){
                        if(this.listaZonas.get(j).getNombre().equals(this.listaEnvios.get(i).getNombreZona())){
                            String color = this.listaZonas.get(j).getColor();
                            if(this.listaEnvios.get(i).getEstado() == false && aux.getRepartidor().getBicicleta().getColor().equals(color)){
                                aux.getRepartidor().setEnvio(this.listaEnvios.get(i));
                                aux.getRepartidor().getEnvio().setEstado(true);
                                aux.getRepartidor().setDisponibilidad(false);
                            }
                        }
                    }
                }
            }
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void cierreCaja() {
        /*
        Date fecha = new Date();
        String formato = "dd/MM/YYYY HH:mm";
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        String fechaFormateada = formateador.format(fecha);
        StdOut.println(fechaFormateada);
        Registro registro = new Registro(3);
        try {
            ArchivoSalida archivo = new ArchivoSalida(fechaFormateada);
        } 
        catch (IOException e) {
            StdOut.println("No se pudo crear el archivo");
        }
        */
    }

    @Override
    public void salir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void almacenarInfo(String nombreArchivo){
        try{
            ArchivoEntrada archivo = new ArchivoEntrada(nombreArchivo);
            while(!archivo.isEndFile()){
                Registro registro = archivo.getRegistro();
                String campo1 = registro.getString();
                String campo2 = registro.getString();
                String campo3 = registro.getString();
                String campo4 = registro.getString();
                String campo5 = registro.getString();
                /* Si el archivo contiene 2 campos, esta abriendo un archivo con los 
                datos de la bicicleta*/
                if(campo3 == null && campo4 == null && campo5 == null){
                    String patente = campo1;
                    double costoMantencion = Double.parseDouble(campo2);                    
                    Nodo aux = this.listaRepartidores.getFirst();
                    // habria que almacenarlos en un arraylist o linkedlist
                    // habria que almacenar las bicis para recorrerlas comparando patentes y un set bicicleta al repartidor
                    // otra manera seria recorrer la lista de repartidores y preguntar por la patente y hacerle el set(o un for)
                    while(aux.getSiguiente() != null){
                        if(nombreArchivo.equals("Bicicletas_Urbana.txt")){
                            Bicicleta biciUrbana = new BicicletaUrbana(costoMantencion,patente);
                            aux.getRepartidor().setBicicleta(biciUrbana);
                            break;
                        }
                        if(nombreArchivo.equals("Bicicletas_MTB.txt")){
                            Bicicleta biciMTB = new MTB(costoMantencion,patente);
                            aux.getRepartidor().setBicicleta(biciMTB);
                            break;
                        }
                        if(nombreArchivo.equals("Bicicletas_Ruta.txt")){
                            Bicicleta biciRuta = new BicicletaRuta(costoMantencion,patente);
                            aux.getRepartidor().setBicicleta(biciRuta);
                            break;
                        }
                        aux = aux.getSiguiente();
                    }
                }
                /* En cambio, si el archivo contiene 3 campos, esta abriendo un 
                archivo con los datos de las zonas*/
                if(campo4 == null && campo5 == null){
                    String nombreZona = campo1;
                    String color = campo2;
                    double tasaEnvio = Double.parseDouble(campo3);
                    Zona zona = new Zona(nombreZona,color,tasaEnvio);
                    this.listaZonas.add(zona);
                }
                /* Por ultimo, si todos los campos tienen informacion, el sistema 
                almacenara todo lo relacionado a los repartidores */
                if(campo4 != null && campo5 != null){
                    String idRepartidor = campo1;
                    String nombreRepartidor = campo2;
                    int edadRepartidor = Integer.parseInt(campo3);
                    String direccionRepartidor = campo4;
                    String patenteBicicleta = campo5;
                    Repartidor repartidor = new Repartidor(idRepartidor,nombreRepartidor,edadRepartidor,direccionRepartidor,patenteBicicleta);
                    this.listaRepartidores.insertarRepartidor(repartidor);
                }
            }
        }
        catch(IOException e){
            StdOut.println("Lo sentimos, el archivo que trata de abrir lamentablemente no existe");
        }
    }
    public boolean existeRepartidor(String id){
        Nodo aux = listaRepartidores.getFirst();
        
        if(aux==null){
            return false;
        }
        
        while(!aux.getRepartidor().getId().equalsIgnoreCase(id) && aux.getSiguiente()!=null){
            aux = aux.getSiguiente();
        }
        
        return aux.getSiguiente() != null;
    }
}
