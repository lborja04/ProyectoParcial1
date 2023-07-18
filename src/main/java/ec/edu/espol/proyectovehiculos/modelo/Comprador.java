/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.espol.proyectovehiculos.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Comprador extends Usuario{

    
    
    public Comprador(int id, String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id, nombres, apellidos, correo, organizacion, clave);
    }

    

    @Override
    public String toString(){
        return id+"|"+nombres+"|"+apellidos+"|"+correo+"|"+organizacion+"|"+clave+"|COMPRADOR";
    }    
    
    
    
    public static void registrarComprador(Scanner sc){
        System.out.println("INGRESE LOS NOMBRES DEL COMPRADOR: ");
        String nombres = sc.nextLine();
        System.out.println("INGRESE LOS APELLIDOS DEL COMPRADOR: ");
        String apellidos = sc.nextLine();
        
        String correo;
        do{
            System.out.print("INGRESE EL CORREO DEL COMPRADOR: ");
            correo=sc.nextLine();
            if(Utilitaria.validarCorreo("Compradores.txt",correo))
                System.out.println("CORREO YA EN USO.");
        }
        while(Utilitaria.validarCorreo("Compradores.txt",correo));
        
        System.out.println("INGRESE LA ORGANIZACION DEL COMPRADOR: ");
        String organizacion = sc.nextLine();
        System.out.println("INGRESE LA CLAVE DEL COMPRADOR: ");
        String clave=sc.nextLine();
        
        Comprador compradorNuevo=new Comprador(Utilitaria.generarID("Compradores.txt"),nombres,apellidos,correo,organizacion,Utilitaria.calcularHash(clave));
        Utilitaria.guardarEnArchivo("Compradores.txt",compradorNuevo.toString());
    }
    
    
    
    public ArrayList<Oferta> obtenerOfertas(){
        ArrayList<String> ofertasPuestas=new ArrayList<>();
        ArrayList<Oferta> ofertas=new ArrayList<>();
        try(Scanner sc=new Scanner(new File("Ofertas.txt"))){
            while(sc.hasNextLine())
                ofertasPuestas.add(sc.nextLine());
            for(String oferta: ofertasPuestas){
                String[] datos=oferta.split("\\|");
                int id_oferta=Integer.parseInt(datos[0]);
                String placa_vehiculo=datos[1];
                double precio_oferta=Double.parseDouble(datos[2]);
                if(this.id==id_oferta){
                    ofertas.add(new Oferta(this.id,placa_vehiculo,precio_oferta));
                }
            }
        }
        catch(Exception e){}
        return ofertas;
    }
    
    
    
    public static Comprador obtenerPorId(int id){
        ArrayList<String> compradores=new ArrayList<>();
        Comprador retorno=null;
        try(Scanner sc=new Scanner(new File("Compradores.txt"))){
            while(sc.hasNextLine())
                compradores.add(sc.nextLine());
            for(String comprador: compradores){
                String[] datos=comprador.split("\\|");
                int id_comprador=Integer.parseInt(datos[0]);
                if(id==id_comprador){
                    retorno=new Comprador(id_comprador,datos[1],datos[2],datos[3],datos[4],datos[5]);
                }
            }
        }
        catch(Exception e){}
        return retorno;
    }
    
    
    
    public static void ofertarVehiculo(){
        Scanner sc=new Scanner(System.in);
        String tipo;
        do{
            System.out.println("INGRESE TIPO DE VEHICULO DESEADO (PRESIONE ENTER SI DESEA SALTAR ESTE PARAMETRO): ");
            tipo = sc.nextLine().toUpperCase();
            if(!tipo.isEmpty()){
                if(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA"))
                    System.out.println("TIPO DE VEHICULO INVALIDO.");
            }
        }
        while(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA") && !tipo.isEmpty());

        System.out.println("INGRESE RANGO DE RECORRIDO DESEADO (PRESIONE ENTER SI DESEA SALTAR ESTE PARAMETRO)");
        String recorridoInicioPuesto;
        String recorridoFinPuesto="";
        boolean condicionRecorrido=false;
        do{
            System.out.println("INGRESE INICIO DEL RANGO: ");
            recorridoInicioPuesto = sc.nextLine();
            if(Utilitaria.esNumeroPosi(recorridoInicioPuesto)){
                System.out.println("INGRESE FIN DEL RANGO: ");
                recorridoFinPuesto = sc.nextLine();
                if(!Utilitaria.esNumeroPosi(recorridoFinPuesto))
                    recorridoFinPuesto="0";
                else
                    condicionRecorrido=Double.parseDouble(recorridoFinPuesto)>Double.parseDouble(recorridoInicioPuesto);
            }
        }
        while(!condicionRecorrido && !recorridoFinPuesto.isEmpty());
     
        System.out.println("INGRESE RANGO DE AÑO DESEADO (PRESIONE ENTER SI DESEA SALTAR ESTE PARAMETRO)");
        String anioInicioPuesto;
        String anioFinPuesto="";
        boolean condicionAnio=false;
        do{
            System.out.println("INGRESE INICIO DEL RANGO: ");
            anioInicioPuesto = sc.nextLine();
            if(Utilitaria.esNumeroPosi(anioInicioPuesto)){
                System.out.println("INGRESE FIN DEL RANGO: ");
                anioFinPuesto = sc.nextLine();
                if(!Utilitaria.esNumeroPosi(anioFinPuesto))
                    anioFinPuesto="0";
                else
                    condicionAnio=Double.parseDouble(anioFinPuesto)>Double.parseDouble(anioInicioPuesto);
            }
        }
        while(!condicionAnio && !anioFinPuesto.isEmpty());
        
        System.out.println("INGRESE RANGO DE PRECIO DESEADO (PRESIONE ENTER SI DESEA SALTAR ESTE PARAMETRO)");
        String precioInicioPuesto;
        String precioFinPuesto="";
        boolean condicionPrecio=false;
        do{
            System.out.println("INGRESE INICIO DEL RANGO: ");
            precioInicioPuesto = sc.nextLine();
            if(Utilitaria.esNumeroPosi(precioInicioPuesto)){
                System.out.println("INGRESE FIN DEL RANGO: ");
                precioFinPuesto = sc.nextLine();
                if(!Utilitaria.esNumeroPosi(precioFinPuesto))
                    precioFinPuesto="0";
                else
                    condicionPrecio=Double.parseDouble(precioFinPuesto)>Double.parseDouble(precioInicioPuesto);
            }
        }
        while(!condicionPrecio && !precioFinPuesto.isEmpty());
        
        
        ArrayList<String> vehiculosString=new ArrayList<>();
        ArrayList<Vehiculo> vehiculosDisponibles=new ArrayList<>();
        try(Scanner sc1=new Scanner(new File("Vehiculos.txt"))){
            while(sc1.hasNextLine())
                vehiculosString.add(sc1.nextLine());

            for(String vehiculo: vehiculosString){
                String[] datos=vehiculo.split("\\|");
                TipoVehiculo tipoVehiculo=TipoVehiculo.valueOf(datos[1]);
                switch(tipoVehiculo){
                    case MOTO:
                        vehiculosDisponibles.add(new Vehiculo(Integer.parseInt(datos[0]),datos[2],datos[3],datos[4],datos[5],Integer.parseInt(datos[6]),Double.parseDouble(datos[7]),datos[8],datos[9],Double.parseDouble(datos[10])));
                        break;
                    case CARRO:
                        vehiculosDisponibles.add(new Carro(Integer.parseInt(datos[0]),datos[2],datos[3],datos[4],datos[5],Integer.parseInt(datos[6]),Double.parseDouble(datos[7]),datos[8],datos[9],Double.parseDouble(datos[10]),Integer.parseInt(datos[11]),datos[12]));
                        break;
                    case CAMIONETA:
                        vehiculosDisponibles.add(new Camioneta(Integer.parseInt(datos[0]),datos[2],datos[3],datos[4],datos[5],Integer.parseInt(datos[6]),Double.parseDouble(datos[7]),datos[8],datos[9],Double.parseDouble(datos[10]),Integer.parseInt(datos[11]),datos[12],datos[13]));
                        break;
                }
            }
        }
        catch(Exception e){}
        
        
        if(!tipo.isEmpty())
            vehiculosDisponibles=Utilitaria.filtrarVehiculos(vehiculosDisponibles, "tipo", tipo);
        
        if(!recorridoInicioPuesto.isEmpty()){
            String rangoRecorrido=recorridoInicioPuesto+"-"+recorridoFinPuesto;
            vehiculosDisponibles=Utilitaria.filtrarVehiculos(vehiculosDisponibles, "recorrido", rangoRecorrido);
        }
        
        if(!anioInicioPuesto.isEmpty()){
            String rangoAnio=anioInicioPuesto+"-"+anioFinPuesto;
            vehiculosDisponibles=Utilitaria.filtrarVehiculos(vehiculosDisponibles, "anio", rangoAnio);
        }
        
        if(!precioInicioPuesto.isEmpty()){
            String rangoPrecio=precioInicioPuesto+"-"+precioFinPuesto;
            vehiculosDisponibles=Utilitaria.filtrarVehiculos(vehiculosDisponibles, "precio", rangoPrecio);
        }
        
        System.out.println("+".repeat(50));
        System.out.println("PRESENTANDO VEHICULOS");
        System.out.println("+".repeat(50));
        System.out.println("SE HAN ENCONTRADO "+vehiculosDisponibles.size()+" VEHICULOS ACORDE A SUS PARAMETROS.");
        int i=0;
        boolean condicionWhile=true;
        while (i<vehiculosDisponibles.size() && condicionWhile) {
            System.out.println("VEHICULO "+(i+1));
            System.out.println(vehiculosDisponibles.get(i).visualizar());
            if (i==0) {
                System.out.println("SELECCIONE UNA OPCION: \n1) SIGUIENTE VEHICULO: \n2)OFERTAR POR VEHICULO");
                String opcionPuesta;
                do{
                    System.out.print("INGRESE OPCION: ");
                    opcionPuesta=sc.nextLine();
                }
                while(!opcionPuesta.equals("1") && !opcionPuesta.equals("2"));
                int opcion=Integer.parseInt(opcionPuesta);
                switch (opcion) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        Comprador comprador=Comprador.iniciarSesion(sc);
                        if(comprador!=null){
                            String precioOfertadoPuesto;
                            do{
                                System.out.println("INGRESE PRECIO QUE OFERTA: ");
                                precioOfertadoPuesto=sc.nextLine();
                            }
                            while(!Utilitaria.esNumeroPosi(precioOfertadoPuesto));
                            double precioOfertado=Double.parseDouble(precioOfertadoPuesto);
                            Utilitaria.guardarEnArchivo("Ofertas.txt",comprador.id+"|"+vehiculosDisponibles.get(i).getPlaca()+"|"+precioOfertado);
                            
                        }
                        else
                            System.out.println("INICIO DE SESION SIN EXITO");
                        condicionWhile=false;
                        break;
                }
            } else {
                System.out.println("SELECCIONE UNA OPCION: \n1) SIGUIENTE VEHICULO \n2) ANTERIOR VEHICULO\n3)OFERTAR POR VEHICULO");
                String opcionPuesta;
                do{
                    System.out.print("INGRESE OPCION: ");
                    opcionPuesta=sc.nextLine();
                }
                while(!opcionPuesta.equals("1") && !opcionPuesta.equals("2") && !opcionPuesta.equals("3"));
                int opcion=Integer.parseInt(opcionPuesta);
                switch (opcion) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        i--;
                        break;
                    case 3:
                        Comprador comprador=Comprador.iniciarSesion(sc);
                        if(comprador!=null){
                            String precioOfertadoPuesto;
                            do{
                                System.out.println("INGRESE PRECIO QUE OFERTA: ");
                                precioOfertadoPuesto=sc.nextLine();
                            }
                            while(!Utilitaria.esNumeroPosi(precioOfertadoPuesto));
                            double precioOfertado=Double.parseDouble(precioOfertadoPuesto);
                            Utilitaria.guardarEnArchivo("Ofertas.txt",comprador.id+"|"+vehiculosDisponibles.get(i).getPlaca()+"|"+precioOfertado);
                        }
                        else
                            System.out.println("INICIO DE SESION SIN EXITO");
                        condicionWhile=false;
                        break;
                }
            }
        }       
    }
    
    
    
    public static Comprador iniciarSesion(Scanner sc){
        Comprador comprador=null;
        
        System.out.println("INGRESE SU CORREO: ");
        String correo=sc.nextLine();
        System.out.println("INGRESE SU CLAVE");
        String clave=sc.nextLine();
        String pass=Utilitaria.calcularHash(clave);
        
        ArrayList<String> usuarios=new ArrayList<>();
        try(Scanner sc2=new Scanner(new File("Compradores.txt"))){
            while(sc2.hasNextLine())
                usuarios.add(sc2.nextLine());
            for(String usuario: usuarios){
                String[] datos=usuario.split("\\|");
                String correoUsuario=datos[3];
                String claveUsuario=datos[5];
                if(correoUsuario.equals(correo) && pass.equals(claveUsuario)){
                    comprador=new Comprador(Integer.parseInt(datos[0]),datos[1],datos[2],correoUsuario,datos[4],claveUsuario);
                }
            }
        }
        catch(Exception e){
        }

        return comprador;
    }

}


