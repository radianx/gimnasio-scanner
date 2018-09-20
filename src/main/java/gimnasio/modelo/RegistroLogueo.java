/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author adrian
 */
public class RegistroLogueo {
    private boolean login;
    private LocalDateTime fechaLogin;
    private String nombre;
    private String apellido;
    private String codigo;

    public RegistroLogueo() {
    }

    public RegistroLogueo(boolean login, LocalDateTime fechaLogin, String nombre, String apellido) {
        if(login){
            this.apellido = apellido;
            this.nombre = nombre;
            this.login = login;
            this.fechaLogin = fechaLogin;
        }       
    }
    
    public RegistroLogueo(String a){
        this.login = false;
        this.fechaLogin = LocalDateTime.now();
        this.nombre = a;
        this.apellido = "No Registrado";
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public LocalDateTime getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(LocalDateTime fechaLogin) {
        this.fechaLogin = fechaLogin;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
