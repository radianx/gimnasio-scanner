/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.util.List;

/**
 *
 * @author adrian
 */
//la variable activo es falsa cuando el socio deja de asistir por un tiempo
//mediante el constructor, activo siempre se inicializa en true
public class Socio extends Persona{
    private List<Cuota> cuotas;
    private List<AsistenciaSocio> asistencias;
    private String certificadoMedico;
    private boolean activo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getCertificadoMedico() {
        return certificadoMedico;
    }

    public void setCertificadoMedico(String certificadoMedico) {
        this.certificadoMedico = certificadoMedico;
    }

    public Socio(List<Cuota> cuotas, List<AsistenciaSocio> asistencias, String certificadoMedico) {
        this.cuotas = cuotas;
        this.asistencias = asistencias;
        this.certificadoMedico = certificadoMedico;
        this.activo = true;
    }
    
    public void nuevaAsistencia(AsistenciaSocio unaAsistencia){
        this.asistencias.add(unaAsistencia);
    }
    
    public void nuevaCuota(Cuota unaCuota){
        this.cuotas.add(unaCuota);
    }

    public List<AsistenciaSocio> getAsistencias() {
        return asistencias;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public void setAsistencias(List<AsistenciaSocio> asistencias) {
        this.asistencias = asistencias;
    }

    public Socio(List<AsistenciaSocio> asistencias) {
        this.asistencias = asistencias;
    }

    public Socio() {
    }
}
