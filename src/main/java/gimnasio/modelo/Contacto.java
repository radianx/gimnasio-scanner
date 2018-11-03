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
public class Contacto implements Serializable {
    @Id
    private int idContacto;
    @OneToMany
    private List<String> telefonos;
    @OneToMany
    private List<String> emails;
    @Column
    private String direccion;
    
    
    public Contacto(){
        
    }
    
    public Contacto(List<String>telefonos){
        this.telefonos = telefonos;
    }
    
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public List<String> getTelefonos(){
        return telefonos;
    }
    
    public void setTelefonos(List<String>telefonos){
        this.telefonos = telefonos;
    }
    
    public List<String> getEmails(){
        return emails;
    }
    
    public void setEmails(List<String>emails){
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Contacto{" + "idContacto=" + idContacto + ", telefonos=" + telefonos + ", emails=" + emails + ", direccion=" + direccion + '}';
    }
    
    
    
}
