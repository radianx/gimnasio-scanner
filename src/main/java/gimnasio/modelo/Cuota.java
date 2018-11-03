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
import javax.persistence.OneToMany;

/**
 *
 * @author wolix
 */
@Entity
public class Cuota {
    @Id
    private int idCUota;
    @Column
    private String estadoCUota;
    @Column
    private Double monto;
    @OneToMany
    private List<Clase> listaClases;


    public Cuota(){
        
    }
    
    public Cuota(Double monto){
        this.monto = monto;
    }
    
    public Cuota (Double monto, List<Clase> listaClase){
        this.monto = monto;
        this.listaClases = listaClase;
    }

    public Cuota(int idCUota, String estadoCUota, Double monto, List<Clase> listaClases) {
        this.idCUota = idCUota;
        this.estadoCUota = estadoCUota;
        this.monto = monto;
        this.listaClases = listaClases;
    }

    public int getIdCUota() {
        return idCUota;
    }

    public void setIdCUota(int idCUota) {
        this.idCUota = idCUota;
    }

    public String getEstadoCUota() {
        return estadoCUota;
    }

    public void setEstadoCUota(String estadoCUota) {
        this.estadoCUota = estadoCUota;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public List<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    @Override
    public String toString() {
        return "Cuota{" + "idCUota=" + idCUota + ", estadoCUota=" + estadoCUota + ", monto=" + monto + ", listaClases=" + listaClases + '}';
    }
    
    
    
    
    
}
