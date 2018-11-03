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

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author wolix
 */

@Entity
public class CajaDiaria {
 
    @Id
    private int idCaja;
    @Column
    private Double montoInicial;
    @Column
    private Double montoFinal;
    @Column
    private LocalDateTime fechaInicial;
    @Column
    private LocalDateTime fechaFinal;

    public CajaDiaria(Double montoInicial, Double montoFinal, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Double getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }

    public Double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public LocalDateTime getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "CajaDiaria{" + "idCaja=" + idCaja + ", montoInicial=" + montoInicial + ", montoFinal=" + montoFinal + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + '}';
    }
    
    
    
    
    
}
