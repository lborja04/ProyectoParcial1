/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.getSHA;
import static ec.edu.espol.proyectovehiculos.modelo.Vendedor.toHexString;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author luisa
 */
public class Vehiculo {
    protected int id;
    protected int id_vendedor;
    protected Vendedor vendedor;
    protected TipoVehiculo tipo;
    protected String placa;
    protected String marca;
    protected String tipoMotor;
    protected int anio;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;
    protected ArrayList<Oferta> ofertas;
    protected String modelo;
    
    public Vehiculo(int id, String placa, String marca,String modelo, String tipoMotor,int año, String color, String tipoComb,double reco,double precio){
        this.id=id;
        this.id_vendedor=id_vendedor;
        this.tipo=TipoVehiculo.MOTO;
        this.vendedor=vendedor;
        this.placa=placa;
        this.marca=marca;
        this.tipoMotor=tipoMotor;
        this.anio=anio;
        this.recorrido=recorrido;
        this.color=color;
        this.tipoCombustible=tipoCombustible;
        this.precio=precio;
        this.modelo=modelo;
        this.ofertas=new ArrayList<>();
    }

    public String getModelo() {
        return modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    public static void saveFileM(String nomfile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(getId+"|"+getPlaca()+"|"+getMarca()+"|"+getModelo()+"|"+getTipoMotor()+"|"+getAnio()+"|"+getColor()+"|"+getTipoCombustible()+"|"+getRecorrido()+"|"+getPrecio());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    }
    


