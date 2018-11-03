/*
 * Copyright (C) 2018 wolix
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gimnasio.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author wolix
 */

@Entity
public class Clase implements Serializable {
    @Id    
    private int idClase;
    @Column
    private LocalDateTime horarioInicio;
    @Column
    private LocalDateTime horarioFin;
    @Column
    private int cupoMaximo;
    @Column
    private int cupoActual;
    @Column
    private String estadoClase;
    @OneToMany
    private List<Alumno> listaAlumnos;
    @OneToMany
    private List<Profesor> listaProfesores;
    @OneToMany
    private List<Modalidad> listaModalidades;
    
    public Clase(){
        
    }
    
    public Clase(LocalDateTime horarioInicio, LocalDateTime horarioFin){
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
    }

    public Clase(LocalDateTime horarioInicio, LocalDateTime horarioFin, int cupoMaximo, int cupoActual, String estadoClase) {
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = cupoActual;
        this.estadoClase = estadoClase;
    }

    public Clase(List<Profesor> listaProfesores, List<Modalidad> listaModalidades) {
        this.listaProfesores = listaProfesores;
        this.listaModalidades = listaModalidades;
    }

    
    
    
    public Clase(int idClase, LocalDateTime horarioInicio, LocalDateTime horarioFin, int cupoMaximo, int cupoActual, String estadoClase, List<Alumno> listaAlumnos, List<Profesor> listaProfesores, List<Modalidad> listaModalidades) {
        this.idClase = idClase;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = cupoActual;
        this.estadoClase = estadoClase;
        this.listaAlumnos = listaAlumnos;
        this.listaProfesores = listaProfesores;
        this.listaModalidades = listaModalidades;
    }
    
    

    
    
    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

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

    public String getEstadoClase() {
        return estadoClase;
    }

    public void setEstadoClase(String estadoClase) {
        this.estadoClase = estadoClase;
    }

    @Override
    public String toString() {
        return "Clase{" + "idClase=" + idClase + ", horarioInicio=" + horarioInicio + ", horarioFin=" + horarioFin + ", cupoMaximo=" + cupoMaximo + ", cupoActual=" + cupoActual + ", estadoClase=" + estadoClase + '}';
    }
    
    
    
    
    
}
