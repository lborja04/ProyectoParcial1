/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

/**
 *
 * @author luisa
 */
public class Camioneta extends Carro{
    private String traccion;
    
    public Camioneta(int id, String placa, String marca,String modelo, String tipoMotor,int año, String color, String tipoComb,double reco,double precio,String vidrios, String transmision, String traccion){
        super(id,placa,marca,modelo,tipoMotor,año,color,tipoComb,reco,precio,vidrios,transmision);
        this.tipo=TipoVehiculo.CAMIONETA;
        this.traccion=traccion;
    }
    
}
