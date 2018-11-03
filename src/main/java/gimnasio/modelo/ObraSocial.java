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

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author wolix
 */
@Entity
public class ObraSocial {
    @Id
    private int idObraSocial;
    @Column
    private String nombreObraSocial;
    @Column
    private List<String> telefonos;
    
    
    public ObraSocial(){
        
    }
    
    public ObraSocial(String nombreObraSocial, List<String> telefonos){
        this.nombreObraSocial = nombreObraSocial;
        this.telefonos = telefonos;
    }

    public int getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(int idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public String getNombreObraSocial() {
        return nombreObraSocial;
    }

    public void setNombreObraSocial(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "ObraSocial{" + "idObraSocial=" + idObraSocial + ", nombreObraSocial=" + nombreObraSocial + ", telefonos=" + telefonos + '}';
    }
    
    
    
    
}
