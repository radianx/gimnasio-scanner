/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import javax.persistence.*;

/**
 *
 * @author adrian
 */

@Entity
public class CajaDiaria {
    
    @Id
    @Column
    private int idCaja;
    
    @Column
    private float montoInicial;
    
    @Column
    private float montoTotal;

    
    
    
    public void agregarMontoACaja(float monto){
        this.montoTotal += monto;
    }
    
    public CajaDiaria() {
    }

    public CajaDiaria(float montoInicial) {
        this.montoInicial = montoInicial;
        this.montoTotal = this.montoInicial;
    }

    public float getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(float montoInicial) {
        this.montoInicial = montoInicial;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
    
}
