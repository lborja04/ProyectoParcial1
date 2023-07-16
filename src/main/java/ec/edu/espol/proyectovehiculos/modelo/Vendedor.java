/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

import ec.edu.espol.proyecto.classes.Usuario;
import ec.edu.espol.proyecto.classes.Vehiculo;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Vendedor extends Usuario{
    private ArrayList<Vehiculo> vehiculosEnVenta;

    public Vendedor(ArrayList<Vehiculo> vehiculosEnVenta, int id,String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id,nombres, apellidos, correo, organizacion, clave);
        this.vehiculosEnVenta = vehiculosEnVenta;
    }
    public Vendedor(int id,String nombres, String apellidos, String correo, String organizacion, String clave) {
        super(id,nombres, apellidos, correo, organizacion, clave);
        this.vehiculosEnVenta = new ArrayList<>();
    }
    public Vendedor(){
    }

    public ArrayList<Vehiculo> getVehiculosEnVenta() {
        return this.vehiculosEnVenta;
    }

    public void setVehiculosEnVenta(ArrayList<Vehiculo> vehiculosEnVenta) {
        this.vehiculosEnVenta = vehiculosEnVenta;
    }
    public static ArrayList<Vendedor> readFile(String nomfile){
        ArrayList<Vendedor> vendedores=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                //linea= "id|nombres|apellidos|correo|organizacion|clave"
                String linea=sc.nextLine();
                String[] tokens=linea.split("\\|");
                Vendedor v1= new Vendedor(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                vendedores.add(v1);
            }
        }catch(Exception e){
        }
        return vendedores;
    }
    public static Vendedor searchByCorreo(ArrayList<Vendedor>vendedores,String correo){
        for(Vendedor v1:vendedores){
            if(v1.getCorreo().equals(correo))
                return v1;
        }
        return null;
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    public static void registrarVendedor(Scanner sc, String nomfile){
        System.out.println("INGRESE LOS NOMBRES DEL VENDEDOR: ");
        String nombres = sc.nextLine();
        System.out.println("INGRESE LOS APELLIDOS DEL VENDEDOR: ");
        String apellidos = sc.nextLine();
        System.out.println("INGRESE EL CORREO DEL VENDEDOR: ");
        String correo = sc.nextLine();
        if(searchByCorreo(readFile(nomfile),correo)==null){
            System.out.println("INGRESE LA ORGANIZACION DEL VENDEDOR: ");
            String organizacion = sc.nextLine();
            System.out.println("INGRESE LA CLAVE DEL VENDEDOR: ");
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
    public static void ingresarVehiculo(Scanner sc,String nomfile){
        System.out.println("Ingrese su correo electronico: ");
        String correo=sc.nextLine();
        Vendedor v1=searchByCorreo(readFile("Vendedores.txt"),correo);
        if(v1==null)
            System.out.println("Este email no esta registrado");
        else{
            v1=searchByCorreo(readFile("Vendedores.txt"),correo);
            System.out.println("Ingrese su contrasena: ");
            String clave="";
            try{
                clave = clave+toHexString(getSHA(sc.nextLine()));
            }catch(NoSuchAlgorithmException e){
            }
            if(v1.getClave().equals(clave)){
                System.out.println("Bienvenido/a, "+v1.getNombres().split(" ")[0]);
                System.out.println("INGRESE EL TIPO DE VEHICULO: ");
                String tipo = sc.nextLine().toLowerCase();
                String mot=TipoVehiculo.MOTO.toString().toLowerCase();
                String carro=TipoVehiculo.CARRO.toString().toLowerCase();
                String camio=TipoVehiculo.CAMIONETA.toString().toLowerCase();
                try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomfile),true))){
                if(tipo.equals(mot)){
                System.out.println("INGRESE LA PLACA DEL VEHICULO: ");
                String placa = sc.nextLine();
                if(Utili.buscarPlaca(readFile(nomfile), placa)==null){
                    System.out.println("INGRESE EL PRECIO DEL VEHICULO: ");
                    Double precio = sc.nextDouble();
                    System.out.println("INGRESE LA MARCA DEL VEHICULO: ");
                    String marca = sc.nextLine();
                    System.out.println("INGRESE EL MODELO DEL VEHICULO: ");
                    String modelo = sc.nextLine();
                    System.out.println("INGRESE EL TIPO MOTOR DEL VEHICULO: ");
                    String tipomotor = sc.nextLine();
                    System.out.println("INGRESE EL AÑO DEL VEHICULO: ");
                    int año = sc.nextInt();
                    System.out.println("INGRESE EL RECORRIDO DEL VEHICULO: ");
                    int recorrido = sc.nextInt();
                    System.out.println("INGRESE EL COLOR DEL VEHICULO: ");
                    String color = sc.nextLine();
                    System.out.println("INGRESE EL TIPO DE COMBUSTIBLE DEL VEHICULO: ");
                    String tipoComb = sc.nextLine();
                    int id=Utili.nextID(nomfile);
                    pw.println(id+","+marca+","+modelo+","+tipomotor+","+año+","+recorrido+","+color+","+tipoComb+","+precio+","+tipo);
                }else{
                        System.out.println("placa ya creada");
                }
            }else if(tipo.equals(carro)){
                System.out.println("INGRESE LA PLACA DEL VEHICULO: ");
                String placa = sc.nextLine();
                if(Utili.buscarPlaca(readFile(nomfile), placa)==null){
                    System.out.println("Ingrese la marca del vehiculo: ");
                    String marca=sc.nextLine();
                    System.out.println("Ingrese el modelo del vehiculo: ");
                    String modelo=sc.nextLine();
                    System.out.println("Ingrese el tipo motor del vehiculo: ");
                    String mo=sc.nextLine();
                    System.out.println("Ingrese el año del vehiculo: ");
                    int año=sc.nextInt();
                    System.out.println("Ingrese el recorrido del vehiculo: ");
                    double reco=sc.nextDouble();
                    System.out.println("Ingrese el color del vehiculo: ");
                    String colo=sc.next();
                    System.out.println("Ingrese el tipo de combustible del vehiculo: ");
                    String tipoc =sc.next();
                    System.out.println("Ingrese el precio del vehiculo: ");
                    Double precio =sc.nextDouble();
                    System.out.println("Ingrese vidrios: ");
                    String vidrio=sc.next();
                    System.out.println("Ingrese trasmision: ");
                    String trasmi=sc.next();
                    int id=Utili.nextID(nomfile);
                    pw.println(id+","+marca+","+modelo+","+mo+","+año+","+reco+","+colo+","+tipoc+","+precio+","+vidrio+","+trasmi+","+tipo);
                }else{
                    System.out.println("La Placa ya existe");
            }
                }else if(tipo.equals(camio)){
                    System.out.println("ingrese la placa del vehiculo: ");
                    String placa=sc.nextLine();
                    if(Utili.buscarPlaca(Utili.leerArchivo(nomfile), placa)==null){
                        System.out.println("Ingrese la marca del vehiculo: ");
                        String marca=sc.nextLine();
                        System.out.println("Ingrese el modelo del vehiculo: ");
                        String modelo=sc.nextLine();
                        System.out.println("Ingrese el tipo motor del vehiculo: ");
                        String mo=sc.nextLine();
                        System.out.println("Ingrese el año del vehiculo: ");
                        int año=sc.nextInt();
                        System.out.println("Ingrese el recorrido del vehiculo: ");
                        double reco=sc.nextDouble();
                        System.out.println("Ingrese el color del vehiculo: ");
                        String colo=sc.next();
                        System.out.println("Ingrese el tipo de combustible del vehiculo: ");
                        String tipoc =sc.next();
                        System.out.println("Ingrese el precio del vehiculo: ");
                        Double precio =sc.nextDouble();
                        System.out.println("Ingrese vidrios: ");
                        String vidrio=sc.next();
                        System.out.println("Ingrese trasmision: ");
                        String trasmi=sc.next();
                        System.out.println("Ingrese traccion: ");
                        String trac=sc.next();
                        int id=Utili.nextID(nomfile);
                         pw.println(id+","+marca+","+modelo+","+mo+","+año+","+reco+","+colo+","+tipoc+","+precio+","+vidrio+","+trasmi+","+trac+","+tipo);
            }else{
                 System.out.println("La Placa ya existe");
                }
                }
                    }catch(Exception e){
            System.out.println(e.getMessage());
        }
                
            }else
                System.out.println("Clave erronea.");
        }
    }
}
