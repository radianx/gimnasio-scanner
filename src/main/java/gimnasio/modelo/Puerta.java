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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author wolix
 */
@Entity
public class Puerta {
    @Id
    private int idPuerta;
    @Column
    private String descripcion;
    @Column
    private boolean estado;

    public Puerta() {
    }

    public Puerta(int idPuerta, String descripcion, boolean estado) {
        this.idPuerta = idPuerta;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdPuerta() {
        return idPuerta;
    }

    public void setIdPuerta(int idPuerta) {
        this.idPuerta = idPuerta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Puerta{" + "idPuerta=" + idPuerta + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
}
