/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author adrian
 */
//la variable presentismo puede ser a (ausente) o p (presente)
public class AsistenciaSocio {
    private LocalDateTime fechaAsistencia;
    private char presentismo;

    public LocalDateTime getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDateTime fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public char getPresentismo() {
        return presentismo;
    }

    public void setPresentismo(char presentismo) {
        if(presentismo == 'a' || presentismo == 'p'){
            this.presentismo = presentismo;
        }
    }

    public AsistenciaSocio(LocalDateTime fechaAsistencia, char presentismo) {
        this.fechaAsistencia = fechaAsistencia;
        this.presentismo = presentismo;
    }

    public AsistenciaSocio() {
    }
}
