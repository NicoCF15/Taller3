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
import java.util.Iterator;

/**
 *
 * @author ncofr
 */
public class SistemaEnviosImpl implements SistemaEnvios{
    /*private ListaRepartidor listaRepartidores;
    private ArrayList<Envio> listaEnvios;
    private LinkedList<Zona> listaZonas;
    private ArrayList<Entrega> listaEntregas;*/
    private ListaRepartidor listaRepartidores;
    private List<Envio> listaEnvios;
    private List<Zona> listaZonas;
    private List<Entrega> listaEntregas;
    private List<Bicicleta> listaBicicletas;

    public SistemaEnviosImpl() {
        this.listaRepartidores = new ListaRepartidor();
        this.listaEnvios = new ArrayList<>();
        this.listaZonas = new LinkedList<>();
        this.listaEntregas = new ArrayList<>();
        this.listaBicicletas = new LinkedList<>();
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
    public void contratarRepartidor(Repartidor nuevoRepartidor) {
        this.listaRepartidores.insertarRepartidor(nuevoRepartidor);
    }
    
    // probar mañana
    @Override
    public boolean despedirRepartidor(String idRepartidor) {
        Nodo aux = this.listaRepartidores.getFirst();
        while(aux.getSiguiente() != null){
            if(aux.getRepartidor().getId().equalsIgnoreCase(idRepartidor)){
                StdOut.println("entre senpai 1 >w<");
                break;
            }
            aux = aux.getSiguiente();
        }
        String colorAux = aux.getRepartidor().getBicicleta().getColor();
        this.listaBicicletas.remove(aux.getRepartidor().getBicicleta());
        Iterator <Bicicleta> iterador = this.listaBicicletas.iterator();
        while(iterador.hasNext()){
            StdOut.println("entre senpai 2 >w<");
            if(iterador.next().getColor().equalsIgnoreCase(colorAux)){
                //todavia hay trabajadores con las bicicletas adecuadas para tomar los envios
                StdOut.println("elimine 3 >w<");
                this.listaEnvios.remove(aux.getRepartidor().getEnvio());
                boolean sePudo = this.listaRepartidores.eliminarRepartidor(aux.getRepartidor()); 
                return sePudo;
            }
            iterador.next();
        }
        for(Zona zona : this.listaZonas){
            StdOut.println("entre senpai 4 >w<");
            for(int i=0;i<this.listaEnvios.size();i++){
                if(zona.getNombre().equals(this.listaEnvios.get(i).getNombreZona()) && this.listaEnvios.get(i).getRepartidor() == null){
                    StdOut.println("elimine senpai 4 >w<");
                    this.listaEnvios.remove(i);
                }
            }
        }
        this.listaEnvios.remove(aux.getRepartidor().getEnvio());
        boolean sePudo = this.listaRepartidores.eliminarRepartidor(aux.getRepartidor()); 
        return sePudo;
    }

    @Override
    public void modificarRepartidor(String idRepartidor,String nombre, int edad, String direccion){
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
        Bicicleta nuevaBicicleta;
        Nodo nodoCambio = listaRepartidores.getFirst();
        while(!nodoCambio.getRepartidor().getId().equalsIgnoreCase(idRepartidor)){
            nodoCambio = nodoCambio.getSiguiente();
        }
        // encuentro al repartidor y pregunto el tipo de bicicleta
        if(tipo.equalsIgnoreCase("ruta")){
            nuevaBicicleta = new BicicletaRuta(CostoMantencion, patente);
            nodoCambio.getRepartidor().setBicicleta(nuevaBicicleta);
        } 
        if(tipo.equalsIgnoreCase("urbana")){
            nuevaBicicleta = new BicicletaUrbana(CostoMantencion, patente);
            nodoCambio.getRepartidor().setBicicleta(nuevaBicicleta);
        }
        if(tipo.equalsIgnoreCase("montania")){
            nuevaBicicleta = new MTB(CostoMantencion, patente);
            nodoCambio.getRepartidor().setBicicleta(nuevaBicicleta);
        } 

    }
    @Override
    // notificacion de datos por menu
    public String realizarEnvio(String nombreEmisor, String nombreReceptor, String direccionEmisor, String direccionReceptor, String zonaReceptor) {
        Nodo aux = this.listaRepartidores.getFirst();
        String mensaje;
        Envio nuevoEnvio;
        // busco zona para obtener su color
        for(int i = 0; i<this.listaZonas.size();i++){
            if(this.listaZonas.get(i).getNombre().equalsIgnoreCase(zonaReceptor)){
                for(Bicicleta bicicleta : this.listaBicicletas){
                    // verifico el si tengo una bicicleta con el color para realizar el envio
                    if(this.listaZonas.get(i).getColor().equalsIgnoreCase(bicicleta.getColor())){
                        // creo el envio
                        nuevoEnvio = new Envio(nombreEmisor,nombreReceptor,direccionEmisor,direccionReceptor,zonaReceptor);
                        nuevoEnvio.calcularMontoEnvio(this.listaZonas.get(i).getTasaEnvio(),aux.getRepartidor().getBicicleta().calcularCostoMantencion());
                        this.listaEnvios.add(nuevoEnvio);
                        int idEnvio = this.listaEnvios.indexOf(nuevoEnvio)+1;
                        nuevoEnvio.setId(idEnvio);
                        // busco un repartidor que cumpla con las condiciones para realizar el envio
                        while(aux.getSiguiente() != null){
                            if(aux.getRepartidor().getBicicleta().getColor().equalsIgnoreCase(this.listaZonas.get(i).getColor())){
                                if(aux.getRepartidor().getDisponibilidad() == true){
                                    // se le da el envio a un repartidor, cambiando su disponibilidad y asignandole el repartidor al envio
                                    nuevoEnvio.setRepartidor(aux.getRepartidor());
                                    aux.getRepartidor().setEnvio(nuevoEnvio);
                                    aux.getRepartidor().setDisponibilidad(false);
                                    mensaje = "Se envió la Bicicleta " + aux.getRepartidor().getBicicleta().getPatente() + " a " + aux.getRepartidor().getEnvio().getNombreZona() + " Dirección: " + aux.getRepartidor().getEnvio().getDireccionReceptor(); 
                                    return mensaje;
                                }
                            }
                            if(aux.getSiguiente().getSiguiente() == null){
                                if(aux.getSiguiente().getRepartidor().getBicicleta().getColor().equalsIgnoreCase(this.listaZonas.get(i).getColor()) && aux.getSiguiente().getRepartidor().getDisponibilidad() == true){
                                    // se le da el envio a un repartidor, cambiando su disponibilidad y asignandole el repartidor al envio
                                    nuevoEnvio.setRepartidor(aux.getRepartidor());
                                    aux.getSiguiente().getRepartidor().setEnvio(nuevoEnvio);
                                    aux.getSiguiente().getRepartidor().setDisponibilidad(false);
                                    mensaje = "Se envió la Bicicleta " + aux.getSiguiente().getRepartidor().getBicicleta().getPatente() + " a " + aux.getSiguiente().getRepartidor().getEnvio().getNombreZona() + " Dirección: " + aux.getSiguiente().getRepartidor().getEnvio().getDireccionReceptor(); 
                                    return mensaje;
                                }
                            }
                            aux = aux.getSiguiente();
                        }
                        mensaje = "El envio se añadio a la cola"; 
                        return mensaje;
                    }
                }
            }
        }
        return "No se pudo realizar el envio";
    }

    @Override
    //revisar con el ultimo
    public void recepcionRepartidor(String idRepartidor) {
        Nodo aux = this.listaRepartidores.getFirst();
        while(aux.getSiguiente() != null){
            // si el repartidor existe y tiene un envio asignado
            if(aux.getRepartidor().getId().equals(idRepartidor)&& aux.getRepartidor().getEnvio()!= null){
                // El repartidor vuelve a estar disponible
                aux.getRepartidor().setDisponibilidad(true);
                // Se entrega el envio
                aux.getRepartidor().getEnvio().setEstado(true);
                //antes de dejarlo libre, se crea y agrega la entrega al sistema
                Entrega nuevaEntrega = new Entrega(aux.getRepartidor(),aux.getRepartidor().getEnvio().getId(),aux.getRepartidor().getPatente(),aux.getRepartidor().getEnvio().getMontoEnvio());
                this.listaEntregas.add(nuevaEntrega);
                // El repartidor no tiene envios vigentes
                aux.getRepartidor().setEnvio(null);
                // reordenar los if
                // evalua los envios de los repartidores con los envios para ver
                for(Envio envio : this.getListaEnvios()){
                    for(Zona zona : this.listaZonas){
                        if(zona.getNombre().equals(envio.getNombreZona())){
                            String color = zona.getColor();
                            // Si el envio esta en la cola y los colores de la bicicleta con la zona son ideales
                            if(envio.getRepartidor() == null && aux.getRepartidor().getBicicleta().getColor().equals(color) && envio.getEstado() != true){
                                // asigno el envio
                                aux.getRepartidor().setEnvio(envio);
                                // cambio la disponibilidad del repartidor
                                aux.getRepartidor().setDisponibilidad(false);
                                StdOut.println("lo tome Juan senpai >w<");
                                return;
                            }
                        }
                    }
                }
            }
            aux = aux.getSiguiente();
        }
        StdOut.println("No lo tome senpai :(");
    }

    @Override
    public void cierreCaja() {
        
        double gananciasTotales = 0;
        Date fecha = new Date();
        String formato = " dd-MM-YYYY HHmm"; 
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        String fechaFormateada = formateador.format(fecha);
        try {
            ArchivoSalida archivo = new ArchivoSalida(fechaFormateada);
            for(Envio envio : this.listaEnvios){
                if(envio.getEstado() == true){
                    Registro registro1 = new Registro(7);
                    Envio envioAux = envio;
                    String campo1 = envioAux.getId()+","+envioAux.getEmisor()+","+envioAux.getReceptor()+","+envioAux.getDireccionEmisor()+","+envioAux.getDireccionReceptor()+","+envioAux.getNombreZona()+","+envioAux.getMontoEnvio();
                    registro1.agregarCampo(campo1);
                    archivo.writeRegistro(registro1);   
                }
            }
            for(Entrega entrega: this.listaEntregas ){
                Registro registro2 = new Registro(1);
                Entrega entregaAux = entrega;
                String campo2 = entregaAux.getRepartidor().getNombre()+","+entregaAux.getGanancia();
                registro2.agregarCampo(campo2);
                gananciasTotales += entrega.getGanancia();
                archivo.writeRegistro(registro2);
            }
            Registro registro3 = new Registro(1);
            String campo3 = String.valueOf(gananciasTotales);
            registro3.agregarCampo(campo3);
            archivo.writeRegistro(registro3);
            archivo.close();
        } 
        catch (IOException e) {
            StdOut.println("Lo sentimos, no se pudo crear el archivo");
        }
        
    }

    /**
     * Metodo que, independientemente de los 5 archivos que la empresa maneja,
     * los abre y almacena toda la informacion que estos contienen en el sistema
     * @param nombreArchivo El nombre del archivo a abrir
     */
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
                    int costoMantencion = Integer.parseInt(campo2);  
                    if(nombreArchivo.equals("Bicicletas_Urbana.txt")){
                            Bicicleta biciUrbana = new BicicletaUrbana(costoMantencion,patente);
                            this.listaBicicletas.add(biciUrbana);
                    }
                    if(nombreArchivo.equals("Bicicletas_MTB.txt")){
                            Bicicleta biciMTB = new MTB(costoMantencion,patente);
                            this.listaBicicletas.add(biciMTB);
                    }
                    if(nombreArchivo.equals("Bicicletas_Ruta.txt")){
                            Bicicleta biciRuta = new BicicletaRuta(costoMantencion,patente);
                            this.listaBicicletas.add(biciRuta);
                    }
                }
                /* En cambio, si el archivo contiene 3 campos, esta abriendo un 
                archivo con los datos de las zonas*/
                if(campo3 != null && campo4 == null && campo5 == null){
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
                    // se le asigna la bicicleta a sus respectivos repartidores
                    for(Bicicleta bicicleta : this.listaBicicletas){
                        if(bicicleta.getPatente().equals(patenteBicicleta)){
                            repartidor.setBicicleta(bicicleta);
                        }
                    }
                    this.listaRepartidores.insertarRepartidor(repartidor);
                }
            }
            archivo.close();
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

        while(!aux.getRepartidor().getId().equals(id) && aux.getSiguiente() != null){
            
            aux = aux.getSiguiente();
            
        }
        StdOut.println(aux.getRepartidor().getId());
        return aux.getRepartidor().getId().equals(id);
    }
    public boolean validarDatos(String datos,String tipo){
        Nodo aux = listaRepartidores.getFirst();
        while(aux.getSiguiente() != null){
            
            if(aux.getRepartidor().getNombre().equals(datos) && tipo.equals("nombreRepartidor")){
                StdOut.println("El repartidor ya se encuentra en el sistema, porfavor vuelva a intentarlo");
                return false;
            }
            if(aux.getRepartidor().getId().equalsIgnoreCase(datos) && tipo.equalsIgnoreCase("idRepartidor")){
                StdOut.println("Id existente, porfavor ingrese id valida (rut  sin guion ni digito verificador)");
                return false;
            }
            if(aux.getRepartidor().getPatente().equalsIgnoreCase(datos) && tipo.equalsIgnoreCase("Patente")){
                StdOut.println("Patente existente, porfavor ingrese patente valida");
                return false;
            }
 
            aux = aux.getSiguiente();
        }
        
        if(tipo.equals("nombreRepartidor")){
            return !aux.getRepartidor().getNombre().equals(datos);
        }
        if(tipo.equals("idRepartidor")){
            return !aux.getRepartidor().getId().equals(datos);
        }
        if(tipo.equals("Patente")){
            return !aux.getRepartidor().getPatente().equals(datos);
        }
        return true;
    }

    public ListaRepartidor getListaRepartidores() {
        return listaRepartidores;
    }        

    public List<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public List<Entrega> getListaEntregas() {
        return listaEntregas;
    }

    public List<Bicicleta> getListaBicicletas() {
        return listaBicicletas;
    }
    
}
