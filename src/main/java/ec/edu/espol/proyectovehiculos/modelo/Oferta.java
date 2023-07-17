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
    private int placa_vehiculo;
    private Vehiculo vehiculo;
    private double precioOferta;
    
    public Oferta(int id_comprador, int id_vehiculo, double precioOferta){
        this.id_comprador=id_comprador;
        this.placa_vehiculo=id_vehiculo;
        this.precioOferta=precioOferta;
    }
    
    
}
