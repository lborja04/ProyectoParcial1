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
    private int id_vehiculo;
    private Vehiculo vehiculo;
    private double precioOferta;
    
    public Oferta(int id_comprador, Comprador comprador, int id_vehiculo, Vehiculo vehiculo, double precioOferta){
        this.id_comprador=id_comprador;
        this.comprador=comprador;
        this.id_vehiculo=id_vehiculo;
        this.vehiculo=vehiculo;
        this.precioOferta=precioOferta;
    }
    
}
