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
public class Comprador extends Usuario{
    
    private ArrayList<Oferta> ofertasPorVehiculo;

    public Comprador(int id, String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id, nombres, apellidos, correo, organizacion, clave);
        this.ofertasPorVehiculo=new ArrayList<>();
    }

    public ArrayList<Oferta> getOfertasPorVehiculo() {
        return ofertasPorVehiculo;
    }

    public void setOfertasPorVehiculo(ArrayList<Oferta> ofertasPorVehiculo) {
        this.ofertasPorVehiculo = ofertasPorVehiculo;
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
        
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File("Compradores.txt"),true))){
            pw.println(Utilitaria.generarID("Compradores.txt")+"|"+nombres+"|"+apellidos+"|"+correo+"|"+organizacion+"|"+Utilitaria.calcularHash(clave)+"|COMPRADOR");
        }catch(Exception e){
            System.out.println(e.getMessage());
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
                    Vehiculo vehiculo=Utilitaria.obtenerPorPlaca(placa_vehiculo);
                    ofertas.add(new Oferta(this.id,this,placa_vehiculo,vehiculo,precio_oferta));
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
    
//    public static void ofertarVehiculo(){
//        Scanner sc=new Scanner(System.in);
//        ArrayList<Vehiculo> v=new ArrayList<>();
//        System.out.println("ingrese el tipo del vehiculo: ");
//        String tipo=sc.nextLine();
//         System.out.println("Ingrese el primer elemento del rango de recorrido: ");
//        int reco=sc.nextInt();
//        System.out.println("Ingrese el 2do elemento del rango de recorrido: ");
//        int reco1=sc.nextInt();
//        System.out.println("Ingrese el primer elemento del rango de año: ");
//        int año=sc.nextInt();
//        System.out.println("Ingrese el primer elemento del rango de año: ");
//        int año1=sc.nextInt();
//        System.out.println("Ingrese el primer precio del rango de recorrido: ");
//        double pre=sc.nextDouble();
//        System.out.println("Ingrese el 2do precio del rango de recorrido: ");
//        double pre1=sc.nextDouble();
//         for(int i=0;i<v.size();i++) {
//            Vehiculo v2 = vehiculo.get(vehiculoa); 
//            System.out.println("Vehiculo "+i);
//            utili.mostrar(tipo, reco, reco1, año, año1, pre, pre1);
//            Oferta o=new Oferta(Integer.parseInt(id),Integer.parseInt(id_vendedor),Double.parse.Double(precio));
//            
//            utili.registraroferta(o);
//            if(i==0){
//                System.out.println("SELECCIONE UNA OPCION: \n1) SIGUIENTE Vehiculo \n2) ANTERIOR Vehiculo");
//                int opcion=sc.nextInt();
//                if(opcion==1){
//                    i++;
//                    if(opcion==2){
//                        i-=1;
//                    }
//
//                    }
//                }else
//                    System.out.println("HA INGRESADO UNA OPCION NO VALIDA");
//            }
//
//
//
//    }

}


