/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.espol.proyectovehiculos.modelo;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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
        int id;
        ArrayList<String> archivo=leerArchivo(nomArchivo);
        id=archivo.size()+1;
        return id;
    }
    
    public static ArrayList<String> leerArchivo(String nomArchivo){
        ArrayList<String> retorno=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomArchivo))){
            while(sc.hasNextLine()){
                retorno.add(sc.nextLine());
            }
        }catch(Exception e){
        }
        return retorno;
    }
    
    public static boolean validarCorreo(String correo){
        ArrayList<String> usuarios=leerArchivo("Usuarios.txt");
        for(String usuario: usuarios){
            String[] datos=usuario.split("|");
            String correoUsuario=datos[3];
            if(correo.equals(correoUsuario))
                return true;
        }
        return false;
    }
    
     public static boolean validarPlaca(String placa){
        ArrayList<String> vehiculos=leerArchivo("Vehiculos.txt");
        for(String vehiculo: vehiculos){
            String[] datos=vehiculo.split("|");
            String placaVehiculo=datos[3];
            if(placa.equals(placaVehiculo))
                return true;
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

    
}

