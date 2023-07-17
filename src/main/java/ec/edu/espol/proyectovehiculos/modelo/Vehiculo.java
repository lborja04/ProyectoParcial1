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
 * @author luisa
 */
public class Vehiculo {
    protected int id_vendedor;
    protected Vendedor vendedor;
    protected TipoVehiculo tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoMotor;
    protected int anio;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;
    protected ArrayList<Oferta> ofertas;
    
    public Vehiculo(int id_vendedor, String placa, String marca, String modelo, String tipoMotor,int anio, double reco, String color, String tipoComb,double precio){
        this.id_vendedor=id_vendedor;
        this.tipo=TipoVehiculo.MOTO;
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.tipoMotor=tipoMotor;
        this.anio=anio;
        this.recorrido=reco;
        this.color=color;
        this.tipoCombustible=tipoComb;
        this.precio=precio;
        this.ofertas=new ArrayList<>();
    }

    public Vehiculo(){}
    
    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public String getModelo(){
        return this.modelo;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    @Override
    public String toString(){
        return id_vendedor+"|"+tipo+"|"+placa+"|"+marca+"|"+modelo+"|"+tipoMotor+"|"+anio+"|"+recorrido+"|"+color+"|"+tipoCombustible+"|"+precio;
    }
    
    public void registrarVehiculo(String nomfile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(this.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Vehiculo obtenerPorPlaca(String placa){
        ArrayList<String> vehiculos=new ArrayList<>();
        Vehiculo vehiculoRetorno=null;
        try(Scanner sc=new Scanner(new File("Vehiculos.txt"))){
            while(sc.hasNextLine())
                vehiculos.add(sc.nextLine());
            for(String vehiculo: vehiculos){
                String[] datos=vehiculo.split("|");
                String placaVehiculo=datos[2];
                if(placaVehiculo.equals(placa)){
                    TipoVehiculo tipo=TipoVehiculo.valueOf(datos[2]);
                    switch(tipo){
                    case MOTO:
                        vehiculoRetorno= new Vehiculo(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]));
                        break;
                    case CARRO:
                        vehiculoRetorno= new Carro(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]),Integer.parseInt(datos[12]),datos[13]);
                        break;
                    case CAMIONETA:
                        vehiculoRetorno= new Camioneta(Integer.parseInt(datos[1]),datos[3],datos[4],datos[5],datos[6],Integer.parseInt(datos[7]),Double.parseDouble(datos[8]),datos[9],datos[10],Double.parseDouble(datos[11]),Integer.parseInt(datos[12]),datos[13],datos[14]);
                        break;
                    }
                }
            }
        }
        catch(Exception e){
        }
        return vehiculoRetorno; 
    }
    
    public ArrayList<Oferta> obtenerOfertas(){
        ArrayList<String> ofertasPuestas=new ArrayList<>();
        ArrayList<Oferta> ofertas=new ArrayList<>();
        try(Scanner sc=new Scanner(new File("Ofertas.txt"))){
            while(sc.hasNextLine())
                ofertasPuestas.add(sc.nextLine());
            for(String oferta: ofertasPuestas){
                String[] datos=oferta.split("|");
                int id_comprador=Integer.parseInt(datos[0]);
                String placa_vehiculo=datos[1];
                double precio_oferta=Double.parseDouble(datos[2]);
                if(this.placa.equals(placa_vehiculo)){
                    Comprador comprador=Comprador.obtenerPorId(id_comprador);
                    ofertas.add(new Oferta(id_comprador,comprador,placa_vehiculo,this,precio_oferta));
                }
            }
        }
        catch(Exception e){}
        return ofertas;
    }
}