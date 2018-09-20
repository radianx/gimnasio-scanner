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
//creada para clases de zumba, samba, crofi, peaMoa
public class Clase {
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFin;
    private int cupoMaximo;
    private int cupoActual;

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalDateTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoActual() {
        return cupoActual;
    }

    public void setCupoActual(int cupoActual) {
        this.cupoActual = cupoActual;
    }

    public Clase(LocalDateTime horarioInicio, LocalDateTime horarioFin, int cupoMaximo) {
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = 0;
    }

    public Clase() {
    }
}
