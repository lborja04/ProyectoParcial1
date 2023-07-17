/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.espol.proyectovehiculos.modelo;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luisa
 */
public class Utilitaria {
    
    public static int generarID(String nomArchivo){
        ArrayList<String> usuarios=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomArchivo))){
            while(sc.hasNextLine())
                usuarios.add(sc.nextLine());
        }
        catch(Exception e){}
        return usuarios.size()+1;
    }
    
    public static boolean validarCorreo(String nomfile, String correo){
        ArrayList<String> usuarios=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
                usuarios.add(sc.nextLine());
            for(String usuario: usuarios){
                String[] datos=usuario.split("|");
                String correoUser=datos[3];
                if(correoUser.equals(correo))
                    return true;
            }
        }
        catch(Exception e){
        }
        return false; 
    }
    
    public static boolean validarPlaca(String placa){
        ArrayList<String> vehiculos=new ArrayList<>();
        try(Scanner sc=new Scanner(new File("Vehiculos.txt"))){
            while(sc.hasNextLine())
                vehiculos.add(sc.nextLine());
            for(String vehiculo: vehiculos){
                String[] datos=vehiculo.split("\\|");
                String placaVehiculo=datos[2];
                if(placaVehiculo.equals(placa))
                    return true;
            }
        }
        catch(Exception e){
        }
        return false; 
    }
    
    
    
    public static String calcularHash(String contra) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytesHash = md.digest(contra.getBytes(StandardCharsets.UTF_8));
            BigInteger numero = new BigInteger(1, bytesHash);
            StringBuilder hashString = new StringBuilder(numero.toString(16));

            while (hashString.length() < 64) {
                hashString.insert(0, '0');
            }

            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static boolean esNumeroPosi(String str) {
        try {
            double num=Double.parseDouble(str);
            if(num>=0)
                return true;
        } catch (NumberFormatException e) { 
        }
        return false;
    }

    public static void eliminarOferta(Oferta oferta, String nomfile){
        ArrayList<String> ofertas=new ArrayList<>();
        try (Scanner sc=new Scanner(new File("Ofertas.txt"))){
            while(sc.hasNextLine())
                ofertas.add(sc.nextLine());
            
            Path ruta = Paths.get("Vehiculos.txt");
              
            for(String line:ofertas){
                String datos[]= line.split("\\|");
                if(datos[0].equals(oferta.getId_comprador()))
                    ofertas.set(ofertas.indexOf(line), "*");
            }
            Files.write(ruta, ofertas);
            }
            catch (IOException e) {
            }
    }
    
    public static void eliminarVehiculo(Vehiculo vehiculo, String nomfile){
        ArrayList<String> vehiculos=new ArrayList<>();
        try (Scanner sc=new Scanner(new File("Vehiculos.txt"))){
            while(sc.hasNextLine())
                vehiculos.add(sc.nextLine());
            
            Path ruta = Paths.get("Vehiculos.txt");
              
            for(String line:vehiculos){
                String datos[]= line.split("\\|");
                if(datos[2].equals(vehiculo.getPlaca()))
                    vehiculos.set(vehiculos.indexOf(line), "");
            }
            Files.write(ruta, vehiculos);
            }
            catch (IOException e) {
            }
    }
    public static Vehiculo obtenerPorPlaca(String placa){
        ArrayList<String> vehiculos=new ArrayList<>();
        try(Scanner sc=new Scanner(new File("Vehiculos.txt"))){
            while(sc.hasNextLine())
                vehiculos.add(sc.nextLine());
            for(String vehiculo: vehiculos){
                String[] datos=vehiculo.split("\\|");
                String placaVehiculo=datos[2];
                if(placaVehiculo.equals(placa)){
                    TipoVehiculo tipo=TipoVehiculo.valueOf(datos[1]);
                    switch(tipo){
                    case MOTO:
                        Vehiculo vehiculoRetorno1=new Vehiculo(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]));
                        return vehiculoRetorno1;
                    case CARRO:
                        Vehiculo vehiculoRetorno2=new Carro(Integer.parseInt(datos[0]),datos[2],datos[3],datos[4],datos[5],Integer.parseInt(datos[6]),Double.parseDouble(datos[7]),datos[8],datos[9],Double.parseDouble(datos[10]),Integer.parseInt(datos[11]),datos[12]);
                        return vehiculoRetorno2;
                    case CAMIONETA:
                        Vehiculo vehiculoRetorno3=new Camioneta(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]),Integer.parseInt(datos[12]),datos[13],datos[14]);
                        return vehiculoRetorno3;
                    }
                }
            }
        }
        catch(Exception e){
        }
        return null;
    }
}

