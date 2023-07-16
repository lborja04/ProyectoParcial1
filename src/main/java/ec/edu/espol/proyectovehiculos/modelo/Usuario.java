/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectovehiculos.modelo;

/**
 *
 * @author luisa
 */
public class Usuario {
    private int id;
    protected String nombre;
    protected String apellidos;
    protected String correo;
    protected String organizacion;
    protected String clave;
    
    public Usuario(int id, String nombre, String apellidos, String correo, String organizacion, String clave){
        this.id=id;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.correo=correo;
        this.organizacion=organizacion;
        this.clave=clave;
    }
}
