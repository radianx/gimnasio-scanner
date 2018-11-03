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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author wolix
 */

@Entity
public class DetalleVenta {
    @Id
    private int idDetVenta;
    @Column
    private int cantidadArticulo;
    @Column
    private Double precioUnitario;
    @Column
    private Double subtotal;
    @OneToOne
    private Articulo articulo;
    
    public DetalleVenta(int cantidadArticulo, Double precioUnitario, Double subtotal){
        this.cantidadArticulo = cantidadArticulo;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        
    }

    public DetalleVenta() {
    }

    public int getIdDetVenta() {
        return idDetVenta;
    }

    public void setIdDetVenta(int idDetVenta) {
        this.idDetVenta = idDetVenta;
    }

    public int getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(int cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    public Articulo getArticulo(){
        return articulo;
    }
    
    public void setArticulo(Articulo articulo){
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetVenta=" + idDetVenta + ", cantidadArticulo=" + cantidadArticulo + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + ", articulo=" + articulo + '}';
    }
    
    

    
    
}
