/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.time.LocalDateTime;
import javax.persistence.*;

/**
 *
 * @author adrian
 */

@Entity
public class Cuota {
    
    @Id
    @Column
    private int codigo_cuota;
    
    @Column
    private LocalDateTime fechaCuota;
    
    @Column
    private LocalDateTime fechaVencimiento;
    
    @Column
    private float montoCuota;

    
    
    
    public LocalDateTime getFechaCuota() {
        return fechaCuota;
    }

    public void setFechaCuota(LocalDateTime fechaCuota) {
        this.fechaCuota = fechaCuota;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(float montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Cuota(LocalDateTime fechaCuota, LocalDateTime fechaVencimiento, float montoCuota) {
        this.fechaCuota = fechaCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.montoCuota = montoCuota;
    }

    public Cuota() {
    }
}
