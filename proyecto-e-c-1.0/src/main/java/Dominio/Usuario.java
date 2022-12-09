/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Cefeg
 */
public class Usuario implements Serializable {
    
    private String rut;
    private String name;
    private String lastname;
    private String correo;
    private int contacto; 
    private String password;
    private String ciudad;
    private String comuna;
    private String direccion;
    private String image;

    public Usuario(String rut, String name, String lastname, String correo, int contacto, String password, String ciudad, String comuna, String direccion, String image) {
        this.rut = rut;
        this.name = name;
        this.lastname = lastname;
        this.correo = correo;
        this.contacto = contacto;
        this.password = password;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.direccion = direccion;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
        
    public void setUsuario(String rut, String name, String lastname, String correo, int contacto, String password, String ciudad, String comuna, String direccion, String image) {
        this.rut = rut;
        this.name = name;
        this.lastname = lastname;
        this.correo = correo;
        this.contacto = contacto;
        this.password = password;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.direccion = direccion;
        this.image = image;
    }
    
    
}
