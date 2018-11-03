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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author wolix
 */

@Entity
public class Modalidad implements Serializable{
    @Id
    private int idModalidad;
    @Column
    private String nombreModalidad;    
    @Column
    private String descripcionModalidad;
    @Column
    private Double precioHora;
    
    
    
    
    public Modalidad(){
        
    }
    
    public Modalidad(String nombreModalidad){
        this.nombreModalidad = nombreModalidad;
    }
    
    public Modalidad(String nombreModalidad, Double precioHora){
        this.nombreModalidad = nombreModalidad;
        this.precioHora = precioHora;
    }
    
    public Modalidad(String nombreModalidad, String descripcionModalidad, Double precioHora){
        this.nombreModalidad = descripcionModalidad;
        this.descripcionModalidad = descripcionModalidad;
        this.precioHora = precioHora;
    }
    
    
    public String getNombreModalidad(){
        return nombreModalidad;
    }
    
    public void setNombreModalidad(String nombreModalidad){
        this.nombreModalidad = nombreModalidad;
    }
    
    public Double getPrecioHora(){
        return precioHora;
    }
    
    public void setPrecioHora(Double precioHora){
        this.precioHora = precioHora;
    }

    public int getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(int idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getDescripcionModalidad() {
        return descripcionModalidad;
    }

    public void setDescripcionModalidad(String descripcionModalidad) {
        this.descripcionModalidad = descripcionModalidad;
    }

    @Override
    public String toString() {
        return "Modalidad{" + "idModalidad=" + idModalidad + ", nombreModalidad=" + nombreModalidad + ", descripcionModalidad=" + descripcionModalidad + ", precioHora=" + precioHora + '}';
    }
    
    
    
}
