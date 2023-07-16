/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public abstract class Usuario {
    protected int id=0;
    protected String nombres;
    protected String apellidos;
    protected String correo;
    protected String organizacion;
    protected String clave;

    public Usuario(int id,String nombres, String apellidos, String correo, String organizacion, String clave) {
        this.id=id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.organizacion = organizacion;
        this.clave = clave;
    }
    public Usuario(){
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOrganizacion() {
        return this.organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public void saveFile(String nomfile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            pw.println(this.id+"|"+this.nombres+"|"+this.apellidos+"|"+this.correo+"|"+this.organizacion+"|"+this.clave);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
