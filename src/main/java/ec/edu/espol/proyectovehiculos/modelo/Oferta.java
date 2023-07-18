/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

/**
 *
 * @author luisa
 */
public class Oferta {
    
    
    
    private int id_comprador;
    private Comprador comprador;
    private String placa_vehiculo;
    private Vehiculo vehiculo;
    private double precioOferta;
    
    
    
    public Oferta(int id_comprador, String id_vehiculo, double precioOferta){
        this.id_comprador=id_comprador;
        this.comprador=Comprador.obtenerPorId(id_comprador);
        this.placa_vehiculo=id_vehiculo;
        this.vehiculo=Utilitaria.obtenerPorPlaca(placa_vehiculo);
        this.precioOferta=precioOferta;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }
    
}


