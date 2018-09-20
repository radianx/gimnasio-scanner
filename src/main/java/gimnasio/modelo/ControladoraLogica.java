/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author adrian
 */
public class ControladoraLogica {
     List<AsistenciaSocio> listaAsistencias = new ArrayList();
     List<CajaDiaria> listaCajasDiarias = new ArrayList();
     List<Clase> listaClases = new ArrayList();
     List<Cuota> listaCuotas = new ArrayList();
     List<Socio> listaSocios = new ArrayList();
     List<Usuario> listaUsuarios = new ArrayList();
     List<Producto> listaProductos = new ArrayList();
     List<RegistroLogueo> listalLogins = new ArrayList();
     List<Entrenamiento> listaEntrenamientos = new ArrayList();

    public List<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }

    public void setListaEntrenamientos(List<Entrenamiento> listaEntrenamientos) {
        this.listaEntrenamientos = listaEntrenamientos;
    }

    public void agregarEntrenamiento(Entrenamiento unEn){
        this.listaEntrenamientos.add(unEn);
    }
    
    public List<RegistroLogueo> getListalLogins() {
        return listalLogins;
    }

    public void setListalLogins(List<RegistroLogueo> listalLogins) {
        this.listalLogins = listalLogins;
    }
     
    public void agregarUsuario(Usuario unUsuario){
        this.listaUsuarios.add(unUsuario);
    }
     
    public List<AsistenciaSocio> getListaAsistencias() {
        return listaAsistencias;
    }

    public void setListaAsistencias(List<AsistenciaSocio> listaAsistencias) {
        this.listaAsistencias = listaAsistencias;
    }

    public List<CajaDiaria> getListaCajasDiarias() {
        return listaCajasDiarias;
    }

    public void setListaCajasDiarias(List<CajaDiaria> listaCajasDiarias) {
        this.listaCajasDiarias = listaCajasDiarias;
    }

    public List<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public List<Cuota> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(List<Cuota> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public List<Socio> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(List<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ControladoraLogica() {
            Usuario miUser = new Usuario(0001,"Jorge","Benitez", "Perez", "1234");
            Usuario miUser2 = new Usuario(0002, "Milton", "Villagran", "Friedman","1234" );
            Usuario miUser3 = new Usuario(0003, "Carl","Esteban" ,"Marx", "1234");
            Usuario miUser4 = new Usuario(0004, "Benito", "Carlos","Baldeza", "1234");
            this.agregarUsuario(miUser);
            this.agregarUsuario(miUser2);
            this.agregarUsuario(miUser3);
            this.agregarUsuario(miUser4);
            
            login("Jorge","1234");
            
            Entrenamiento entrenamiento1 = new Entrenamiento("Musculacion");
            Entrenamiento entrenamiento2 = new Entrenamiento("Anaerobico");
            Entrenamiento entrenamiento3 = new Entrenamiento("Regimen suave");
            Entrenamiento entrenamiento4 = new Entrenamiento("Regimen militar");
            
            this.agregarEntrenamiento(entrenamiento1);
            this.agregarEntrenamiento(entrenamiento2);
            this.agregarEntrenamiento(entrenamiento3);
            this.agregarEntrenamiento(entrenamiento4);
            
    }

    public boolean login(String user, String pass) {
        boolean encontrado = false;
        Usuario unUsuario = null;
        
        for(int i = 0; i < listaUsuarios.size(); i++){
            if(listaUsuarios.get(i).getUsuario().equals(user) && listaUsuarios.get(i).getPassword().equals(pass)){
                LocalDateTime dateTime = LocalDateTime.now();
                RegistroLogueo newLogin = new RegistroLogueo(true, dateTime, listaUsuarios.get(i).getNombre(), listaUsuarios.get(i).getApellido());
                listalLogins.add(newLogin);
                encontrado = true;
                i = listaUsuarios.size();
            }
        }
        
    return encontrado;
    }
}
