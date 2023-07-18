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
public class Vendedor extends Usuario{

    
    
    public Vendedor(int id,String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id,nombres, apellidos, correo, organizacion, clave);
    }
    
    
    
    @Override
    public String toString(){
        return id+"|"+nombres+"|"+apellidos+"|"+correo+"|"+organizacion+"|"+clave+"|VENDEDOR";
    }
    
    
    
    public static void registrarVendedor(Scanner sc){
        System.out.println("INGRESE LOS NOMBRES DEL VENDEDOR: ");
        String nombres = sc.nextLine();
        System.out.println("INGRESE LOS APELLIDOS DEL VENDEDOR: ");
        String apellidos = sc.nextLine();
        
        String correo;
        do{
            System.out.println("INGRESE EL CORREO DEL VENDEDOR: ");
            correo=sc.nextLine();
            if(Utilitaria.validarCorreo("Vendedores.txt",correo))
                System.out.println("CORREO YA EN USO.");
        }
        while(Utilitaria.validarCorreo("Vendedores.txt",correo));
        
        System.out.println("INGRESE LA ORGANIZACION DEL VENDEDOR: ");
        String organizacion = sc.nextLine();
        System.out.println("INGRESE LA CLAVE DEL VENDEDOR: ");
        String clave=sc.nextLine();
        
        Vendedor vendedorNuevo=new Vendedor(Utilitaria.generarID("Vendedores.txt"),nombres,apellidos,correo,organizacion,Utilitaria.calcularHash(clave));
        Utilitaria.guardarEnArchivo("Vendedores.txt",vendedorNuevo.toString());
    }
    
    
    
    public static void ingresarVehiculo(Scanner sc){
        Vendedor vendedor=iniciarSesion(sc);
        if(vendedor!=null){
            System.out.println("BIENVENIDO/A, "+vendedor.nombres);
            String tipo;
            do{
                System.out.println("INGRESE EL TIPO DE VEHICULO: ");
                tipo = sc.nextLine().toUpperCase();
                if(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA"))
                    System.out.println("TIPO DE VEHICULO INVALIDO.");
            }
            while(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA"));
                
            String placa;
            do{
                System.out.println("INGRESE LA PLACA DEL VEHICULO: ");
                placa=sc.nextLine();
                if(Utilitaria.validarCorreo(vendedor.correo,"Vendedores.txt"))
                    System.out.println("PLACA YA REGISTRADA.");
            }
            while(Utilitaria.validarPlaca(placa));
                
            String precioPuesto;
            do{
                System.out.println("INGRESE EL PRECIO DEL VEHICULO: ");
                precioPuesto = sc.nextLine();
            }
            while(!Utilitaria.esNumeroPosi(precioPuesto));
            double precio=Double.parseDouble(precioPuesto);
                
            System.out.println("INGRESE LA MARCA DEL VEHICULO: ");
            String marca = sc.nextLine();
            System.out.println("INGRESE EL MODELO DEL VEHICULO: ");
            String modelo = sc.nextLine();
            System.out.println("INGRESE EL TIPO MOTOR DEL VEHICULO: ");
            String tipomotor = sc.nextLine();
                
            String añoPuesto;
            do{
                System.out.println("INGRESE EL AÑO DEL VEHICULO: ");
                añoPuesto = sc.nextLine();
            }
            while(!Utilitaria.esNumeroPosi(añoPuesto));
            int anio=Integer.parseInt(añoPuesto);

            String recorridoPuesto;
            do{
                System.out.println("INGRESE EL RECORRIDO DEL VEHICULO: ");
                recorridoPuesto = sc.nextLine();
            }
            while(!Utilitaria.esNumeroPosi(recorridoPuesto));
            double recorrido=Double.parseDouble(recorridoPuesto);
 
            System.out.println("INGRESE EL COLOR DEL VEHICULO: ");
            String color = sc.nextLine();
            System.out.println("INGRESE EL TIPO DE COMBUSTIBLE DEL VEHICULO: ");
            String tipoComb = sc.nextLine();
                
            Vehiculo nuevoVehiculo=new Vehiculo();
            if(TipoVehiculo.valueOf(tipo)==TipoVehiculo.MOTO)
                nuevoVehiculo=new Vehiculo(vendedor.id,placa,marca,modelo,tipomotor,anio,recorrido,color,tipoComb,precio);
                
            else{
                String numVidrios;
                do{
                System.out.println("INGRESE NUMERO DE VIDRIOS DEL VEHICULO: ");
                numVidrios = sc.nextLine();
                }
                while(!Utilitaria.esNumeroPosi(añoPuesto));
                int vidrios=Integer.parseInt(numVidrios);

                System.out.println("INGRESE TRANSMISION DEL VEHICULO: ");
                String trasmi=sc.next();
                
                if(TipoVehiculo.valueOf(tipo)==TipoVehiculo.CARRO)
                    nuevoVehiculo= new Carro(vendedor.id,placa,marca,modelo,tipomotor,anio,recorrido,color,tipoComb,precio,vidrios,trasmi);

                else if(TipoVehiculo.valueOf(tipo)==TipoVehiculo.CAMIONETA){
                    System.out.println("INGRESE TRACCION DEL VEHICULO: ");
                    String traccion=sc.nextLine();
                    
                    nuevoVehiculo=new Camioneta(vendedor.id,placa,marca,modelo,tipomotor,anio,recorrido,color,tipoComb,precio,vidrios,trasmi,traccion);
                }
            }
            Utilitaria.guardarEnArchivo("Vehiculos.txt",nuevoVehiculo.toString());
        }
        else{
            System.out.println("INICIO DE SESION SIN EXITO.");
        }
    }
     
    
    
    public static Vendedor iniciarSesion(Scanner sc){
        Vendedor vendedor=null;
        
        System.out.println("INGRESE SU CORREO: ");
        String correo=sc.nextLine();
        System.out.println("INGRESE SU CLAVE");
        String clave=sc.nextLine();
        String pass=Utilitaria.calcularHash(clave);
        
        ArrayList<String> usuarios=new ArrayList<>();
        try(Scanner sc2=new Scanner(new File("Vendedores.txt"))){
            while(sc2.hasNextLine())
                usuarios.add(sc2.nextLine());
            for(String usuario: usuarios){
                String[] datos=usuario.split("\\|");
                String correoUsuario=datos[3];
                String claveUsuario=datos[5];
                if(correoUsuario.equals(correo) && pass.equals(claveUsuario)){
                    vendedor=new Vendedor(Integer.parseInt(datos[0]),datos[1],datos[2],correoUsuario,datos[4],claveUsuario);
                }
            }
        }
        catch(Exception e){
        }

        return vendedor;
    }
    
    
    
    public static void aceptarOferta(Scanner sc){
        Vendedor vendedor=Vendedor.iniciarSesion(sc);
        if(vendedor==null)
            System.out.println("INICIO DE SESION SIN EXITO.");
        else{
            System.out.println("INGRESE LA PLACA DEL VEHÍCULO DEL CUAL QUIERE REVISAR SUS OFERTAS");
            String placa=sc.nextLine();
            if(!Utilitaria.validarPlaca(placa))
                System.out.println("PLACA NO EXISTE");
            else{
                Vehiculo v1=Utilitaria.obtenerPorPlaca(placa);
                if(v1.getId_vendedor()==vendedor.id){
                    System.out.println("Vehiculo "+v1.getMarca()+", modelo "+v1.getModelo()+", precio de venta: $"+v1.getPrecio());
                    ArrayList<Oferta> ofertas=v1.obtenerOfertas();
                    System.out.println("SE HAN REALIZADO "+ofertas.size()+" OFERTAS.");
                    int i=0;
                    boolean condicionWhile=true;
                    while (i<ofertas.size() && condicionWhile) {
                        System.out.println("OFERTA "+(i+1));
                        System.out.println("CORREO: "+ofertas.get(i).getComprador().getCorreo());
                        System.out.println("PRECIO OFERTADO: "+ofertas.get(i).getPrecioOferta());
                        if(i==0){
                            System.out.println("SELECCIONE UNA OPCION: \n1) SIGUIENTE OFERTA: \n2) ACEPTAR OFERTA");
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
                                    System.out.println("NOTIFICANDOLE AL COMPRADOR...");
                                    String destinatario=ofertas.get(i).getComprador().correo;
                                    String asunto="OFERTA ACEPTADA";
                                    String cuerpo="SU OFERTA POR "+ofertas.get(i).getVehiculo().visualizar()+" HA SIDO ACEPTADA, EL VENDEDOR PRONTO SE PONDRA EN CONTACTO.";
                                    Utilitaria.enviarConGMail(destinatario,asunto,cuerpo);
                                    Utilitaria.eliminarVehiculo(ofertas.get(i).getVehiculo());
                                    condicionWhile=false;
                                    break;
                            }
                        }else{
                            System.out.println("SELECCIONE UNA OPCION: \n1) SIGUIENTE OFERTA \n2) ANTERIOR OFERTA\n3) ACEPTAR OFERTA");
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
                                    System.out.println("NOTIFICANDOLE AL COMPRADOR...");
                                    String destinatario=ofertas.get(i).getComprador().correo;
                                    String asunto="OFERTA ACEPTADA";
                                    String cuerpo="SU OFERTA POR "+ofertas.get(i).getVehiculo().visualizar()+" HA SIDO ACEPTADA, EL VENDEDOR PRONTO SE PONDRA EN CONTACTO.";
                                    Utilitaria.enviarConGMail(destinatario,asunto,cuerpo);
                                    Utilitaria.eliminarVehiculo(ofertas.get(i).getVehiculo());
                                    condicionWhile=false;
                                    break;
                            }
                        }
                    }
                }
                else
                    System.out.println("PLACA NO REGISTRADA A SU NOMBRE.");
            }
        }
    }
    
    
    
    public static Vendedor obtenerPorId(int id){
        ArrayList<String> vendedores=new ArrayList<>();
        Vendedor retorno=null;
        try(Scanner sc=new Scanner(new File("Vendedores.txt"))){
            while(sc.hasNextLine())
                vendedores.add(sc.nextLine());
            for(String vendedor: vendedores){
                String[] datos=vendedor.split("\\|");
                int id_vendedor=Integer.parseInt(datos[0]);
                if(id==id_vendedor){
                    retorno=new Vendedor(id_vendedor,datos[1],datos[2],datos[3],datos[4],datos[5]);
                }
            }
        }
        catch(Exception e){}
        return retorno;
    }
}