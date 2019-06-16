/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;
import ucn.StdOut;
import ucn.StdIn;
import java.util.InputMismatchException;
import java.util.List;
/**
 *
 * @author ncofr
 */
public class App {
    // para asignar envio, recorrer lista repartidores y pegarse sus instanceof, o de otra manera, comparar colores
public static void main(String[] args) {
        SistemaEnviosImpl sistema = new SistemaEnviosImpl();
        ListaRepartidor listaRepartidores = sistema.getListaRepartidores();
        List<Envio> listaEnvios = sistema.getListaEnvios();
        List<Bicicleta> bicicletas = sistema.getListaBicicletas();
        sistema.correrApp();
        int optionElegida;
        while(true){
            optionElegida = menu();

            if(optionElegida==1){
                Repartidor nuevoRepartidor = pedirDatosRepartidor(sistema);
                sistema.contratarRepartidor(nuevoRepartidor);

            }
            if(optionElegida==2){
                StdOut.println("Ingrese id ");
                String id = StdIn.readString();
                boolean seElimino = sistema.despedirRepartidor(id);
                if(seElimino = false){
                    StdOut.println("No se elimino"); 
                }
                if(seElimino = true){
                    StdOut.println("se elimino"); 
                }
            }
            if(optionElegida==3){
                modificarRepartidor(sistema);

            }
            if(optionElegida==4){
                StdOut.println("Ingrese nombre del emisor");
                String nombreEmisor = StdIn.readString();
                StdOut.println("Ingrese nombre del receptor");
                String nombreReceptor = StdIn.readString();
                StdOut.println("Ingrese direccion del emisor");
                String direccionEmisor = StdIn.readString();
                StdOut.println("Ingrese direccion del receptor");
                String direccionReceptor = StdIn.readString();
                StdOut.println("Ingrese zona del receptor");
                String zonaReceptor = StdIn.readLine();
                zonaReceptor = StdIn.readLine();
                String mensaje = sistema.realizarEnvio(nombreEmisor, nombreReceptor, direccionEmisor, direccionReceptor, zonaReceptor);
                StdOut.println(mensaje);                 
            }
            if(optionElegida==5){
                StdOut.println("ingrese id del repartidor");
                String id = StdIn.readString();
                sistema.recepcionRepartidor(id);
            }
            if(optionElegida==6){
                sistema.cierreCaja();
            }
            if(optionElegida==7){
                Nodo aux = listaRepartidores.getFirst();
                while(aux.getSiguiente() != null){
                    StdOut.println(aux.getRepartidor().getNombre());
                    StdOut.println(aux.getRepartidor().getDireccion());
                    StdOut.println(aux.getRepartidor().getEdad());
                    StdOut.println(aux.getRepartidor().getPatente());
                    StdOut.println(aux.getRepartidor().getId());
                    StdOut.println(aux.getRepartidor().getBicicleta().getColor());
                    StdOut.println(aux.getRepartidor().getEnvio());
                    aux = aux.getSiguiente();
                }
                StdOut.println(aux.getRepartidor().getNombre());
                StdOut.println(aux.getRepartidor().getDireccion());
                StdOut.println(aux.getRepartidor().getEdad());
                StdOut.println(aux.getRepartidor().getPatente());
                StdOut.println(aux.getRepartidor().getId());
                StdOut.println(aux.getRepartidor().getBicicleta().getColor());
                StdOut.println(aux.getRepartidor().getEnvio());
                for(Envio envio : listaEnvios){
                    StdOut.println(envio.getEmisor());
                    StdOut.println(envio.getReceptor());
                    StdOut.println(envio.getDireccionEmisor());
                    StdOut.println(envio.getDireccionReceptor());
                    StdOut.println(envio.getId());
                    StdOut.println(envio.getMontoEnvio());
                    StdOut.println(envio.getRepartidor());
                    StdOut.println(envio.getNombreZona());
                    StdOut.println(envio.getEstado());
                }
                for(Bicicleta bicicleta : bicicletas){
                    StdOut.println(bicicleta.getPatente());
                }
                System.exit(0);
            }
        }
    }
    public static void modificarRepartidor(SistemaEnviosImpl sistema){
        StdOut.println("Ingrese id del repartidor a modificar");
                String idCambio= StdIn.readString();
                boolean existeId = sistema.existeRepartidor(idCambio);
                if(existeId)
                {

                    StdOut.println("Que desea modificar");
                    StdOut.println("[1] Modificar datos del repartidor");
                    StdOut.println("[2] Modificar patente de la bicicleta");
                    StdOut.println("[3] Cambiar bicicleta del repartidor");
                    String opcionSubmenu = StdIn.readString();
                    int opcionElegidaSubmenu = validarOpciones(opcionSubmenu,3);
                    while(opcionElegidaSubmenu == -1){
                        opcionSubmenu = StdIn.readString();
                        opcionElegidaSubmenu= validarOpciones(opcionSubmenu,3);
                        // valida que la opcion elegida este entre el rango de opciones
                    }
                    if(opcionElegidaSubmenu==1){
                        StdOut.println("Ingrese el nuevo nombre");
                        String nombreRepartidor = StdIn.readString();
                        boolean nombreValido = sistema.validarDatos(nombreRepartidor,"nombreRepartidor");
                        StdOut.println(nombreValido);
                        while(!nombreValido){
                            StdOut.println("Nombre ya existe, elija otro");
                            nombreRepartidor = StdIn.readLine();
                            nombreRepartidor = StdIn.readLine();
                            nombreValido = sistema.validarDatos(nombreRepartidor,"nombreRepartidor");
                        }
                        StdOut.println("Ingrese nueva edad");
                        boolean esNumero = false;
                        int edad;
                        String numero = "xd";
                        while(!esNumero){
                            numero = StdIn.readString();
                            esNumero = validarNumero(numero);
                        }
                        edad = Integer.parseInt(numero); //numero
                        StdOut.println("Ingrese calle ");
                        String nombreCalle = StdIn.readString();
                        StdOut.println("Ingrese N° de calle");
                        String numeroCalle = StdIn.readString();
                        String direccion = nombreCalle + " " + numeroCalle;

                        sistema.modificarRepartidor(idCambio,nombreRepartidor,edad,direccion);
                        StdOut.println("Cambios registrados en el sistema");
                    }
                    if(opcionElegidaSubmenu==2){
                        StdOut.println("Ingrese nueva patente");
                        String nuevaPatente = StdIn.readString();
                        boolean patenteValida = sistema.validarDatos(nuevaPatente,"Patente");
                        while(!patenteValida){
                            StdOut.println("Patente ya existente, intentelo denuevo");
                            nuevaPatente = StdIn.readString();
                            patenteValida =sistema.validarDatos(nuevaPatente,"Patente");
                        }
                        sistema.modificarPatente(idCambio,nuevaPatente);
                        StdOut.println("Cambios registrados en el sistema");

                    }
                    if(opcionElegidaSubmenu==3){

                        StdOut.println("Ingrese patente de la nueva bicicleta");
                        String patenteNuevaBicicleta= StdIn.readString();
                        boolean patenteValida = sistema.validarDatos(patenteNuevaBicicleta,"Patente");
                        while(!patenteValida){
                            StdOut.println("Patente ya existente, intentelo denuevo");
                            patenteNuevaBicicleta = StdIn.readString();
                            patenteValida =sistema.validarDatos(patenteNuevaBicicleta,"Patente");
                        }
                        StdOut.println("Ingrese el costo de mantencion de la bicicleta");
                        
                        
                        double costoMantencion;
                        String StringcostoMantencion= StdIn.readString();
                        boolean costoValido = ingresarDouble(StringcostoMantencion);
  
                        while(!costoValido){
                            StdOut.println("Ingrese costo adecuado");
                            StringcostoMantencion= StdIn.readString();
                            costoValido = ingresarDouble(StringcostoMantencion);
                    
                        }
                        costoMantencion = Double.parseDouble(StringcostoMantencion);
         

                        StdOut.println("De que tipo es la nueva bicicleta?");
                        StdOut.println("[1] Bicicleta de ruta");
                        StdOut.println("[2] Bicicleta urbana");
                        StdOut.println("[3] Mountain bike (MTB)");

                        String opcionSubmenuDelsubMenu = StdIn.readString();
                        int opcionElegidaSubmenuDelsubMenu = validarOpciones(opcionSubmenuDelsubMenu,3);
                        while(opcionElegidaSubmenuDelsubMenu == -1){
                            opcionSubmenuDelsubMenu = StdIn.readString();
                            opcionElegidaSubmenuDelsubMenu= validarOpciones(opcionSubmenuDelsubMenu,3);
                            // valida que la opcion elegida este entre el rango de opciones
                        }
                        if(opcionElegidaSubmenuDelsubMenu==1){
                            sistema.cambiarBicicleta(idCambio,costoMantencion, patenteNuevaBicicleta,"amarillo");
                        }
                        if(opcionElegidaSubmenuDelsubMenu==2){
                            sistema.cambiarBicicleta(idCambio,costoMantencion, patenteNuevaBicicleta,"verde");
                        }
                        if(opcionElegidaSubmenuDelsubMenu==2){
                            sistema.cambiarBicicleta(idCambio,costoMantencion ,patenteNuevaBicicleta,"rojo");
                        }
                        StdOut.println("Cambio realizado con exito");
                    }   
                }
                else{
                    StdOut.println("No existe id del repartidor que desea modificar");

                }
    }

    /**
     *
     * @param sistema
     * @return
     */
    public static Repartidor pedirDatosRepartidor(SistemaEnviosImpl sistema){
        StdOut.println("Ingrese nombre del repartidor");//falta ver si existe o no el nombre
        String nombreRepartidor = StdIn.readString();
        boolean nombreValido = sistema.validarDatos(nombreRepartidor,"nombreRepartidor");
        StdOut.println(nombreValido);
        while(!nombreValido){
            StdOut.println("Nombre ya existe, elija otro");
            nombreRepartidor = StdIn.readLine();
            nombreRepartidor = StdIn.readLine();
            nombreValido = sistema.validarDatos(nombreRepartidor,"nombreRepartidor");
        }
        StdOut.println("Ingrese rut del repartidor sin guion ni digito verificador");
        String id = StdIn.readString(); 
        boolean idValida = sistema.validarDatos(id,"idRepartidor");
        while(!idValida){
            StdOut.println("id ya existente, intentelo denuevo");
            id = StdIn.readString();
            idValida =sistema.validarDatos(id,"idRepartidor");
        }
        
        
        StdOut.println("Ingrese edad del repartidor");
        boolean esNumero = false;
        int edad;
        String numero = "xd";
        while(!esNumero){
            numero = StdIn.readString();
            esNumero = validarNumero(numero);
        }
        edad = Integer.parseInt(numero); //numero
        StdOut.println("Ingrese direccion del repartidor");
        String direccion= StdIn.readString();
        StdOut.println("Ingrese patente asociada a su bicicleta");
        String patente = StdIn.readString();
        boolean patenteValida = sistema.validarDatos(patente,"Patente");
        while(!patenteValida){
            StdOut.println("Patente ya existente, intentelo denuevo");
            patente = StdIn.readString();
            patenteValida =sistema.validarDatos(patente,"Patente");
        }
        Repartidor nuevoRepartidor = new Repartidor(id,nombreRepartidor,
        edad,direccion,patente);
        StdOut.println("Ingrese el tipo de bicicleta del repartidor(ruta, montania o urbana)");
        String tipoBicicleta;
        while(true){
            tipoBicicleta = StdIn.readString();
            if(tipoBicicleta.equalsIgnoreCase("ruta") || tipoBicicleta.equalsIgnoreCase("urbana") || tipoBicicleta.equalsIgnoreCase("montania")){
                break;
            } 
            StdOut.println("Ingrese tipo de bicicleta valido");
        }
        
        StdOut.println("Ingrese costo de mantencion de la bicicleta");
        boolean costoValido;
        double costoMantencion;
        String StringcostoMantencion= StdIn.readString();
        costoValido = ingresarDouble(StringcostoMantencion);
  
        while(!costoValido){
            StdOut.println("Ingrese costo adecuado");
            StringcostoMantencion= StdIn.readString();
            costoValido = ingresarDouble(StringcostoMantencion);
        
        }
        costoMantencion = Double.parseDouble(StringcostoMantencion);
         
        if(tipoBicicleta.equalsIgnoreCase("ruta")){
             Bicicleta bicicletaRepartidor = new BicicletaRuta(costoMantencion, patente);
            nuevoRepartidor.setBicicleta(bicicletaRepartidor);
        } 
        if(tipoBicicleta.equalsIgnoreCase("urbana")){
             Bicicleta bicicletaRepartidor = new BicicletaUrbana(costoMantencion, patente);
            nuevoRepartidor.setBicicleta(bicicletaRepartidor);
        }
        if(tipoBicicleta.equalsIgnoreCase("montania")){
            Bicicleta bicicletaRepartidor = new MTB(costoMantencion, patente);
            nuevoRepartidor.setBicicleta(bicicletaRepartidor);
        } 
        
        return nuevoRepartidor;
    }
    public static boolean validarNumero(String numero){
        int edad;
        try{

            edad = Integer.parseInt(numero);
        }catch(NumberFormatException e){
            StdOut.println("Intentelo denuevo");
            return false;
        }
        return true;
    }

    public static int menu(){
        String String = null;//variable que acepta todo tipo de datos
        boolean isCorrect = false;//boolean que permite que funcione el while
        int numberSelected;// Numero que va a ser retornado
        // Instrucciones
        StdOut.println("[1] Contratar repartidor");
        StdOut.println("[2] Despedir repartidor");
        StdOut.println("[3] Modificar repartidor");
        StdOut.println("[4] Realizar envio");
        StdOut.println("[5] Recepcion repartidor");
        StdOut.println("[6] Cierre de caja");
        StdOut.println("[7] Salir");


        while(isCorrect == false){ 

            String = StdIn.readString();
            if("1".equals(String) || "2".equals(String) 
            || "3".equals(String) || "4".equals(String) ||
            "5".equals(String) || "6".equals(String) || "7".equals(String)){

                isCorrect=true;
            }
            else{
                StdOut.println("Error ingrese numero entre las opciones");


            }  
        }
        numberSelected = Integer.parseInt(String);
        return numberSelected;
    }
    public static int validarOpciones(String numeroIngresado, int rangoMaximo){
        try{
            int opcion = Integer.parseInt(numeroIngresado);
                if(opcion<=rangoMaximo && opcion>0){
                    return opcion;  
                }
                else{
                    StdOut.println("Elija un numero entre las opciones");
                    return -1;
                }
            }
            catch(NumberFormatException e){
                StdOut.println("Ingrese un numero porfavor");
                return -1;
            }
    }
    public static boolean ingresarDouble(String valor){
       
        try{
            double valorDouble;
            valorDouble= Double.parseDouble(valor);
            return true;
        }catch(NumberFormatException  e){
            return false;
        }
    }

    
}
