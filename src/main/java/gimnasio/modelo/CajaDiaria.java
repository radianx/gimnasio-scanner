/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

/**
 *
 * @author adrian
 */
public class CajaDiaria {
    private float montoInicial;
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
