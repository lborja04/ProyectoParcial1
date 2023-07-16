/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto.classes;

import ec.edu.espol.proyectovehiculos.modelo.Vendedor;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.getSHA;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.readFile;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.searchByCorreo;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.toHexString;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
        System.out.println("INGRESE EL CORREO DEL COMPRADOR: ");
        String correo = sc.nextLine();
        if(searchByCorreo(readFile(nomfile),correo)==null){
            System.out.println("INGRESE LA ORGANIZACION DEL COMPRADOR: ");
            String organizacion = sc.nextLine();
            System.out.println("INGRESE LA CLAVE DEL COMPRADOR: ");
            String clave="";
            try{
                clave = clave+toHexString(getSHA(sc.nextLine()));
            }catch(NoSuchAlgorithmException e){
            }
            int id=Util.nextID(nomfile);
            Vendedor v1=new Vendedor(id, nombres, apellidos, correo, organizacion, clave);
            v1.saveFile("Vendedores.txt");
        }else{
            System.out.println("VENDEDOR YA EXISTE, OPERACION INVALIDA");
        }
    }
}
