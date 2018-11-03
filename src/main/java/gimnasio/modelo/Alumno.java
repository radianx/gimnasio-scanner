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

/**
 *
 * @author wolix
 */
@Entity
public class Alumno extends Persona implements Serializable{
    
    
    @Column
    private Double peso;
    @Column
    private Double altura;;
    
    
    public Alumno(){
        super();
    }
    
    public Alumno(Double altura){
        super();
        this.altura = altura;
    }
    
    public Alumno(Double altura, Double peso){
        super();
        this.altura = altura;
        this.peso =peso;
    }

    
    
    public Double getAltura() {
        return altura;
    }
    
    public Double getPeso(){
        return peso;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
    
    public void setPeso(Double peso){
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "peso=" + peso + ", altura=" + altura + '}';
    }
    
    
    
    
}
