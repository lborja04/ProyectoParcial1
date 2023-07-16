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
public class Vendedor extends Usuario{
    private ArrayList<Vehiculo> vehiculosEnVenta;
    
    public Vendedor(int id, String nombre, String apellidos, String correo, String organizacion, String clave){
        super(id, nombre,apellidos,correo,organizacion,clave);
        this.vehiculosEnVenta=new ArrayList<>();
    }
    
}
