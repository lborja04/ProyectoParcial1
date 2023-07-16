/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

/**
 *
 * @author luisa
 */
public class Carro extends Vehiculo{
    protected int vidrios;
    protected char transmision;
    
    public Carro(int id, int id_vendedor, Vendedor vendedor, String placa, String marca, String tipoMotor, int anio, double recorrido, String color, String tipoCombustible, double precio, int vidrios, char transmision){
        super(id, id_vendedor,vendedor,placa,marca,tipoMotor,anio,recorrido,color,tipoCombustible,precio);
        this.tipo=TipoVehiculo.CARRO;
        this.vidrios=vidrios;
        this.transmision=transmision;
    }
    
}
