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
import javax.persistence.OneToOne;

/**
 *
 * @author wolix
 */

@Entity
public class Usuario implements Serializable{
    @Id
    private int idUsuario;
    @Column
    private String nombreUsuario;
    @Column
    private String contrasenia;
    @OneToOne
    private Huella huellaUsuario;
    
    public Usuario(){
    }
    
    public Usuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public Usuario(String nombreUsuario, String contrasenia){
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    
    public Usuario(String nombreUsuario, String contrasenia, Huella huellaUsuario){
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.huellaUsuario= huellaUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Huella getHuellaUsuario() {
        return huellaUsuario;
    }

    public void setHuellaUsuario(Huella huellaUsuario) {
        this.huellaUsuario = huellaUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", huellaUsuario=" + huellaUsuario + '}';
    }
    
    
    
    
}
