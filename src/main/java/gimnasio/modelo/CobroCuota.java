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
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author wolix
 */
@Entity
public class CobroCuota extends Cobro implements Serializable{
    
    @Column
    private Double interes;
    @OneToMany
    private List<Cuota> listaCuotas;

    public CobroCuota(int idCobro, LocalDateTime fechaCobro, Double monto) {
        super(idCobro, fechaCobro, monto);
    }
    
    
    public CobroCuota(Double interes){
        super();
        this.interes = interes;
    }
    
    
}
