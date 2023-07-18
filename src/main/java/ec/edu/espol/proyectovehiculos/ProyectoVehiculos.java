/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ec.edu.espol.proyectovehiculos;

import ec.edu.espol.proyectovehiculos.modelo.*;

import java.util.Scanner;

/**
 *
 * @author luisa
 */
public class ProyectoVehiculos {
    
    
    
    public static void main(String[] args){

        ejecutarInterfaz();
        
    }

    
    
    public static void ejecutarInterfaz(){
        String opcion1;
        
            do{
                String opcion2="4";
                String opcion3="3";
                Scanner sc = new Scanner(System.in);
                System.out.println("-".repeat(50));
                System.out.println("              VENTA DE VEHICULOS               ");
                System.out.println("-".repeat(50));
                System.out.println(" ");
                System.out.println("ELIJA UNA OPCION: ");
                System.out.println("1. VENDEDOR");
                System.out.println("2. COMPRADOR");
                System.out.println("3. SALIR");
                do{
                    System.out.print("INGRESE OPCION: ");
                    opcion1=sc.nextLine();
                }
                while(!opcion1.equals("1") && !opcion1.equals("2") && !opcion1.equals("3"));
                do{    
                    
                    if(opcion1.equals("1")){ 
                        System.out.println("-".repeat(50));
                        System.out.println("              MENU VENDEDOR               ");
                        System.out.println("-".repeat(50));
                        System.out.println("1. REGISTRAR UN NUEVO VENDEDOR");
                        System.out.println("2. REGISTRAR UN NUEVO VEHICULO");
                        System.out.println("3. ACEPTAR OFERTA");
                        System.out.println("4. REGRESAR");
                        
                        do{
                            System.out.print("INGRESE OPCION: ");
                            opcion2=sc.nextLine();
                        }
                        while(!opcion2.equals("1") && !opcion2.equals("2") && !opcion2.equals("3") && !opcion2.equals("4"));

                        switch (opcion2) {
                            case "1":
                                Vendedor.registrarVendedor(sc);
                                break;
                            case "2":
                                Vendedor.ingresarVehiculo(sc);
                                break;
                            case "3":
                                Vendedor.aceptarOferta(sc);
                                break;
                        }
                        
                    }
                    
                    else if(opcion1.equals("2")){
                        System.out.println("-".repeat(50));
                        System.out.println("              MENU COMPRADOR               ");
                        System.out.println("-".repeat(50));
                        System.out.println("1. REGISTRAR UN NUEVO COMPRADOR");
                        System.out.println("2. OFERTAR POR UN VEHICULO");
                        System.out.println("3. REGRESAR");

                        do{
                            System.out.print("INGRESE OPCION: ");
                            opcion3=sc.nextLine();
                        }
                        while(!opcion3.equals("1") && !opcion3.equals("2") && !opcion3.equals("3"));

                        switch(opcion3){
                            case "1":
                                Comprador.registrarComprador(sc);
                                break;
                            case "2":
                                Comprador.ofertarVehiculo();
                                break;
                        }
                        
                    }
                    
                }
                while(!opcion2.equals("4") || !opcion3.equals("3"));
       
            }
            while(!opcion1.equals("3"));
            
            System.out.println("-".repeat(50));
            System.out.println("SALIENDO DEL PROGRAMA..........");
    }
}
