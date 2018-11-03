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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author wolix
 */

@Entity
public class InscripcionGym implements Serializable{
    
    @Id
    private int idInscripcion;
    @Column
    private LocalDateTime fechaInscripcion;
    @Column
    private Double cuotaInicial;
    @OneToOne
    private Alumno alumnoInscripto;
    
    public InscripcionGym(){
        
    }
    
    public InscripcionGym(Alumno alumnoInscripto, LocalDateTime fechaInscripcion){
        this.alumnoInscripto = alumnoInscripto;
        this.fechaInscripcion = fechaInscripcion;
    }
    
    public InscripcionGym(Alumno alumnoInscripto, LocalDateTime fechaInscdripcion, Double cuotaInicial){
        this.alumnoInscripto = alumnoInscripto;
        this.fechaInscripcion = fechaInscripcion;
        this.cuotaInicial = cuotaInicial;
    }
    
    
    
    public int getIdInsripcion(){
        return idInscripcion;
    }
    
    public void setIdInscripcion(int idInscripcion){
        this.idInscripcion = idInscripcion;
    }
    
    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(Double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public Alumno getAlumnoInscripto() {
        return alumnoInscripto;
    }

    public void setAlumnoInscripto(Alumno alumnoInscripto) {
        this.alumnoInscripto = alumnoInscripto;
    }
    
    
    
}
