/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;
import java.util.ArrayList;
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
    
    public Vehiculo(int id, int id_vendedor, Vendedor vendedor, String placa, String marca, String tipoMotor, int anio, double recorrido, String color, String tipoCombustible, double precio){
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
        this.ofertas=new ArrayList<>();
    }
    
}
