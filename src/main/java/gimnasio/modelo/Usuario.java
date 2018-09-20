/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author adrian
 */
public class Usuario {
    private int codigoUsuario;
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
//    private List<RegistroLogueo> listaLogin = new ArrayList<>();


    public Usuario(int codigoUsuario, String usuario, String nombre, String apellido, String password) {
        this.codigoUsuario = codigoUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
    public Usuario(int codigoUsuario, String nombre, String apellido) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario(int codigoUsuario, String nombre, String apellido, String password) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Usuario() {
    }
   

//    public void addLogin() {
//        LocalDateTime dateTime = LocalDateTime.now();
//        RegistroLogueo newLogin = new RegistroLogueo(true, dateTime);
//        this.listaLogin.add(newLogin);
//    }
}
