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
    
    public static void registrarComprador(Scanner sc, String nomfile){
        System.out.println("INGRESE LOS NOMBRES DEL COMPRADOR: ");
        String nombres = sc.nextLine();
        System.out.println("INGRESE LOS APELLIDOS DEL COMPRADOR: ");
        String apellidos = sc.nextLine();
        
        String correo;
        do{
            System.out.print("INGRESE EL CORREO DEL COMPRADOR: ");
            correo=sc.nextLine();
            if(Utilitaria.validarCorreo(correo))
                System.out.println("CORREO YA EN USO.");
        }
        while(Utilitaria.validarCorreo(correo));
        
        System.out.println("INGRESE LA ORGANIZACION DEL COMPRADOR: ");
        String organizacion = sc.nextLine();
        System.out.println("INGRESE LA CLAVE DEL COMPRADOR: ");
        String clave=sc.nextLine();
        
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File("Usuarios.txt"),true))){
            pw.println(Utilitaria.generarID("Usuarios.txt")+"|"+nombres+"|"+apellidos+"|"+correo+"|"+organizacion+"|"+Utilitaria.calcularHash(clave)+"|COMPRADOR");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}


