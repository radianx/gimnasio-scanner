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

/**
 *
 * @author wolix
 */

@Entity
public class Cobro implements Serializable{
    
    @Id
    private int idCobro;
    @Column
    private LocalDateTime fechaCobro;
    @Column
    private Double monto;
    
    public Cobro(){
        
    }
    
    public Cobro(int idCobro, LocalDateTime fechaCobro, Double monto) {
        this.idCobro = idCobro;
        this.fechaCobro = fechaCobro;
        this.monto = monto;
    }

    public int getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }

    public LocalDateTime getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(LocalDateTime fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Cobro{" + "idCobro=" + idCobro + ", fechaCobro=" + fechaCobro + ", monto=" + monto + '}';
    }
    
    
    
    
    
}
