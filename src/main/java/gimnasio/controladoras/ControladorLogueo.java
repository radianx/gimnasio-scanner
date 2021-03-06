/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controladoras;

import gimnasio.controladoras.ControladoraLogica;
import gimnasio.visual.MainMenu;
import gimnasio.visual.jInternalLogueo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class ControladorLogueo implements ActionListener{
    private jInternalLogueo login;
    private ControladoraLogica logica;
    private MainMenu main;
    
    public ControladorLogueo(jInternalLogueo login, ControladoraLogica logica, MainMenu main){
        this.login = login;
        this.logica = logica;
        this.main = main;
        addListener();
    }
    //asdf
    private void addListener(){
        this.login.getAceptar().addActionListener(this);
        this.login.getCancelar().addActionListener(this);
    }
    
    private void showMessage(String msg){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(login, msg);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
