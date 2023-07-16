/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author luisa
 */
public class Carro extends Vehiculo{
    protected String vidrios;
    protected String transmision;
    
    public Carro(int id, String placa, String marca,String modelo, String tipoMotor,int año, String color, String tipoComb,double reco,double precio,String vidrio,String trasmision){
        super(id,placa,marca,modelo,tipoMotor,año,color,tipoComb,reco,precio);
        this.tipo=TipoVehiculo.CARRO;
        this.vidrios=vidrios;
        this.transmision=transmision;
    }
     public void saveFile2(String nomfile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(this.id+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.anio+"|"+this.color+"|"+this.tipoCombustible+"|"+this.recorrido+"|"+this.precio+"|"+this.vidrios+"|"+this.transmision);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    }
    
}
