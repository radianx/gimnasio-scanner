/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.visual;

import gimnasio.modelo.ControladoraLogica;
import java.awt.Dimension;
/**
 *
 * @author adrian
 */
public class ControladoraVisual{
    
    ControladoraLogica logica = new ControladoraLogica();
    static TablasCargar cargadorDeTablas = new TablasCargar();
    
    public ControladoraLogica getLogica(){
        return this.logica;
    }
    
    public static void main(String args[]){
            ControladoraLogica logica = new ControladoraLogica();
            MainMenu menu = new MainMenu(cargadorDeTablas, logica);
            menu.setMinimumSize(new Dimension(800,800));
            ControladorLogueo login = new ControladorLogueo(menu.getjInternalLogueo(), logica, menu);
            menu.setVisible(true);
            
            
    
    }
    
   
   
   
}
