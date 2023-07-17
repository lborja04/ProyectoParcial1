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
    protected String transmision;
    
    public Carro(int id_vendedor, int id, String placa, String marca,String modelo, String tipoMotor,int año, String color, String tipoComb,double reco,double precio,int vidrios,String transmision){
        super(id_vendedor,id,placa,marca,modelo,tipoMotor,año,color,tipoComb,reco,precio);
        this.tipo=TipoVehiculo.CARRO;
        this.vidrios=vidrios;
        this.transmision=transmision;
    }
    
    @Override
    public String toString(){
        return super.toString()+"|"+vidrios+"|"+transmision;
    }
}