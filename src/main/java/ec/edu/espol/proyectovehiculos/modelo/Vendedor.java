/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.espol.proyectovehiculos.modelo;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Vendedor extends Usuario{
    
    private ArrayList<Vehiculo> vehiculosEnVenta;

    public Vendedor(int id,String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id,nombres, apellidos, correo, organizacion, clave);
        this.vehiculosEnVenta = new ArrayList<>();
    }
    
    public Vendedor(){}
    
    public ArrayList<Vehiculo> getVehiculosEnVenta() {
        return this.vehiculosEnVenta;
    }

    public void setVehiculosEnVenta(ArrayList<Vehiculo> vehiculosEnVenta) {
        this.vehiculosEnVenta = vehiculosEnVenta;
    }
    
    public static void registrarVendedor(Scanner sc){
        System.out.println("INGRESE LOS NOMBRES DEL VENDEDOR: ");
        String nombres = sc.nextLine();
        System.out.println("INGRESE LOS APELLIDOS DEL VENDEDOR: ");
        String apellidos = sc.nextLine();
        
        String correo;
        do{
            System.out.print("INGRESE EL CORREO DEL VENDEDOR: ");
            correo=sc.nextLine();
            if(Utilitaria.validarCorreo(correo))
                System.out.println("CORREO YA EN USO.");
        }
        while(Utilitaria.validarCorreo(correo));
        
        System.out.println("INGRESE LA ORGANIZACION DEL VENDEDOR: ");
        String organizacion = sc.nextLine();
        System.out.println("INGRESE LA CLAVE DEL VENDEDOR: ");
        String clave=sc.nextLine();
        
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File("Usuarios.txt"),true))){
            pw.println(Utilitaria.generarID("Vendedores.txt")+"|"+nombres+"|"+apellidos+"|"+correo+"|"+organizacion+"|"+Utilitaria.calcularHash(clave)+"|VENDEDOR");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void ingresarVehiculo(Scanner sc,String nomfile){
        Vendedor vendedor=iniciarSesion(sc);
        if(vendedor!=null){
                System.out.println("BIENVENIDO/A, "+vendedor.nombres);
                String tipo;
                do{
                    System.out.print("INGRESE EL TIPO DE VEHICULO: ");
                    tipo = sc.nextLine().toUpperCase();
                    if(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA"))
                        System.out.println("TIPO DE VEHICULO INVALIDO.");
                }
                while(!tipo.equals("MOTO") && !tipo.equals("CARRO") && !tipo.equals("CAMIONETA"));
                
                String placa;
                do{
                    System.out.print("INGRESE LA PLACA DEL VEHICULO: ");
                    placa=sc.nextLine();
                    if(Utilitaria.validarCorreo(vendedor.correo))
                        System.out.println("PLACA YA REGISTRADA.");
                }
                while(Utilitaria.validarPlaca(placa));
                
                String precioPuesto;
                do{
                    System.out.println("INGRESE EL PRECIO DEL VEHICULO: ");
                    precioPuesto = sc.nextLine();
                }
                while(Utilitaria.esNumeroPosi(precioPuesto));
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
                while(Utilitaria.esNumeroPosi(añoPuesto));
                int anio=Integer.parseInt(añoPuesto);

                String recorridoPuesto;
                do{
                    System.out.println("INGRESE EL RECORRIDO DEL VEHICULO: ");
                    recorridoPuesto = sc.nextLine();
                }
                while(Utilitaria.esNumeroPosi(añoPuesto));
                int recorrido=Integer.parseInt(añoPuesto);
 
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
                    while(Utilitaria.esNumeroPosi(añoPuesto));
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
                vendedor.vehiculosEnVenta.add(nuevoVehiculo);
                nuevoVehiculo.registrarVehiculo("Vehiculos.txt");
        }
    }
    
    public void cargarVehiculos(){
        ArrayList<String> vehiculos=Utilitaria.leerArchivo("Vehiculos.txt");
        for(String vehiculo: vehiculos){
            String[] datos=vehiculo.split("|");
            TipoVehiculo tipo=TipoVehiculo.valueOf(datos[2]);
            switch(tipo){
                case MOTO:
                    this.vehiculosEnVenta.add(new Vehiculo(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11])));
                    break;
                case CARRO:
                    this.vehiculosEnVenta.add(new Carro(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]),Integer.parseInt(datos[12]),datos[13]));
                    break;
                case CAMIONETA:
                    this.vehiculosEnVenta.add(new Camioneta(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]),Integer.parseInt(datos[12]),datos[13],datos[14]));
                    break;
            }
        }
    }
    
    public static Vendedor iniciarSesion(Scanner sc){
        Vendedor vendedor=null;
        
        System.out.println("INGRESE SU CORREO: ");
        String correo=sc.nextLine();
        System.out.println("INGRESE SU CLAVE");
        String clave=sc.nextLine();
        String pass=Utilitaria.calcularHash(clave);
        
        ArrayList<String> usuarios=Utilitaria.leerArchivo("Usuarios.txt");
 
        for(String usuario: usuarios){
            String[] datos=usuario.split("|");
            String correoUsuario=datos[3];
            String claveUsuario=datos[5];
            if(correo.equals(correoUsuario) && pass.equals(claveUsuario)){
                vendedor=new Vendedor(Integer.parseInt(datos[0]),datos[1],datos[2],correoUsuario,datos[4],claveUsuario);
                vendedor.cargarVehiculos();
            }
        }
        return vendedor;
    }
}