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
public class Comprador extends Usuario{
    private ArrayList<Vehiculo> ofertasPorVehiculos;
    
    public Comprador(int id, String nombre, String apellidos, String email, String organizacion, String clave){
        super(id, nombre,apellidos,email,organizacion,clave);
        this.ofertasPorVehiculos=new ArrayList<>();
    }
    
}
