/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.visual;

import gimnasio.controladoras.ControladoraVisual;
import gimnasio.modelo.RegistroLogueo;
import gimnasio.modelo.Usuario;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adrian
 */
public class TablasCargar extends javax.swing.JInternalFrame{
    DefaultTableModel modelo = new DefaultTableModel();
    ControladoraVisual miVisual = new ControladoraVisual();
    
    public TablasCargar(){
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Fecha Logueo");
        modelo.addColumn("Hora Logueo");
    }
    
    public void cargarTabla(javax.swing.JTable jTable){
        List<RegistroLogueo> listaLogueo = miVisual.getLogica().getListalLogins();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        
        Object[]fila = new Object[4];
        
        for(RegistroLogueo unRegistro:listaLogueo){
       
                fila[0]=unRegistro.getNombre();
                fila[1]=unRegistro.getApellido();
                fila[2]=unRegistro.getFechaLogin().format(formateador);
                fila[3]=unRegistro.getFechaLogin().getHour();
            
                modelo.addRow(fila);
        }
        jTable.setModel(modelo);
    }
    
}
