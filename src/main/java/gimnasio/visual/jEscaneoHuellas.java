/*
 * Copyright (C) 2018 Family
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
package gimnasio.visual;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_READY;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import gimnasio.visual.testConexionBD.ConexionBD;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Family
 */
public class jEscaneoHuellas extends javax.swing.JFrame {

    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate planilla;
    public static String TEMPLATE_PROPERTY = "template";
    
    protected void Iniciar(){
        lector.addDataListener(new DPFPDataAdapter(){
           public void dataAquired(final DPFPDataEvent e){
               SwingUtilities.invokeLater(() -> {
                   EnviarTexto("La huella digital se capturo con exito.");
                   ProcesarCaptura(e.getSample());
               });
           }
            
        });
        
        lector.addReaderStatusListener(new DPFPReaderStatusAdapter(){
           @Override
           public void readerConnected(final DPFPReaderStatusEvent e){
               SwingUtilities.invokeLater(() -> {
                   EnviarTexto("Sensor de huellas digitalPersona U.are.U 4500 conectado.");
               });
               };
           @Override
           public void readerDisconnected(final DPFPReaderStatusEvent e){
               SwingUtilities.invokeLater(() -> {
                   EnviarTexto("El sensor de huellas se ha desconectado o desactivado");
               });
           }
        });
        lector.addSensorListener(new DPFPSensorAdapter(){
            @Override
            public void fingerTouched(final DPFPSensorEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        EnviarTexto("El dedo ha sido colocado sobre el lector de huellas");
                    }
                    public void fingerGone(final DPFPSensorEvent e){
                        SwingUtilities.invokeLater(() -> {
                            EnviarTexto("El dedo ha sido quitado del Lector de huelas");
                        });
                    }
                });
            }
        });
        lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        EnviarTexto("Error: "+e.getError());
                    }
                });
            }
        });
    }   
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresvertification;
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try{
            return extractor.createFeatureSet(sample, purpose);
        }catch(DPFPImageQualityException e){
            return null; //no me convence
        }
    }
    
    public Image CrearImagenHuella(DPFPSample sample){
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }
    
    public void DibujarHuella(Image image){
        this.lblHuella.setIcon(new ImageIcon(
            image.getScaledInstance(this.lblHuella.getWidth(), lblHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
    }
    
    public void EstadoHuellas(){
        EnviarTexto("Muestra de Huellas Necesarias para Guardar Template " + reclutador.getFeaturesNeeded());
    }
    
    public void EnviarTexto(String string){
        this.txtArea.append(string + "\n");
    }
    
    public void start(){
        lector.startCapture();
        EnviarTexto("Utilizando el Lector de Huella Dactilar");
    }
    
    public void stop(){
        lector.stopCapture();
        EnviarTexto("No se esta usando el lector de Huellas Dactular");
    }
  
    public DPFPTemplate getTemplate(){
        return planilla;
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.planilla;
        this.planilla = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    public void ProcesarCaptura(DPFPSample sample){
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
    
        featuresvertification = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
    
        if(featuresinscripcion != null){
            try{
                System.out.println("Las caracteristicas de la huella han sido creadas");
                
                reclutador.addFeatures(featuresinscripcion);
                
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);
                
                this.btnVerificar.setEnabled(true);
                this.btnIdentificar.setEnabled(true);       
            } catch(DPFPImageQualityException ex){
                System.out.println("Error: "+ex.getMessage());
            } finally{
                EstadoHuellas();
                //comprueba si la plantilla se creo
                switch(reclutador.getTemplateStatus()){
                    case TEMPLATE_STATUS_READY://informe de exito y detiene la captura de huellas
                        stop();
                        setTemplate(reclutador.getTemplate());
                        EnviarTexto("La plantilla de la huella ha sido registrada");
                        this.btnIdentificar.setEnabled(false);
                        this.btnVerificar.setEnabled(false);
                        this.btnGuardar.setEnabled(true);
                        this.btnGuardar.grabFocus();
                        break;
                    
                    case TEMPLATE_STATUS_FAILED://informe de fallo y luego reinicio
                        reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(jEscaneoHuellas.this, "La plantilla sufrio una descompostura, no se capturo nada");
                        start();
                        break;
                        
                }
            }
        }
    }
    
    ConexionBD cn = new ConexionBD();
    
    public void guardarHuella() throws SQLException{
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(planilla.serialize());
        Integer tamanoHuella=planilla.serialize().length;
    //pregunta el nombre de la persona a la cual corresponde dicha huella
        String nombre = JOptionPane.showInputDialog("Nombre: ");
        try{
            //establecer los valores para sentencia sql
            Connection c=cn.conectar();
            PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO somhue(huenombre, huehuella) values(?,?)");
            guardarStmt.setString(1, nombre);
            guardarStmt.setBinaryStream(2, datosHuella, tamanoHuella);
            //Ejecuta la sentencia
            guardarStmt.execute();
            guardarStmt.close();
            JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
            cn.desconectar();
            this.btnGuardar.setEnabled(false);
            this.btnVerificar.grabFocus();
        }catch(SQLException ex){
            //Si ocurre un error lo indica en la consola
            System.err.println("Error al guardar los datos de la huella.");
        }finally{
            cn.desconectar();
        }        
    }
    
    public void verificarHuella(String nom){
        //Verifica la huella digital actual contra otra en la base de datos
    try{
        //Establece valores para la sentencia SQL
        Connection c=cn.conectar();
        //Obtiene la plantilla correspondiente a la persona indicada
        PreparedStatement verificarStmt = c.prepareCall("SELECT huehuella FROM somhue WHERE huenombre=?");
        verificarStmt.setString(1,nom);
        ResultSet rs = verificarStmt.executeQuery();
        
        //si se enuentra el nombre en la base de datos
        if(rs.next()){
            //leo la plantilla de la base de datos
            byte templateBuffer[] = rs.getBytes("huehuella");
            //Crea una nueva plantilla a partir de la guardada en la base de datos
            DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            setTemplate(referenceTemplate);
            
            //Compara las caracteristicas de la huella recientemente capturada
            //con la plantilla guardada al usuario especifico en la base de datos
            DPFPVerificationResult result = verificador.verify(this.featuresvertification, getTemplate());
            
            //compara las plantillas (actual vs bd)
            if(result.isVerified())
                JOptionPane.showMessageDialog(null, "La huella capturada coincide con la de "+nom,"Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No corresponde la huella con "+nom, "Verificacion de huella", JOptionPane.ERROR_MESSAGE);
        //Si no encuentra alguna huella correspondiente al nombre, le aviso con un mensaje    
        } else{
            JOptionPane.showMessageDialog(null, "No existe un registro de huella para "+nom, "Verificaciond e Huella", JOptionPane.ERROR_MESSAGE);            
        }
        } catch (SQLException e){
            //si ocurre un error lo indica en la consola
            System.err.println("Error al verificar los datos de la huella");
        } finally{
            cn.desconectar();
        }
    }
    
    public void identificarHuella() throws IOException, SQLException{
        
        
        try{
            //Establece los valores para la sentencia SQL
            Connection c=cn.conectar();
            //Obtiene todas las huellas de la bd
            PreparedStatement identificarSTMT = c.prepareStatement("SELECT huenombre,huehuella FROM somhue");
            ResultSet rs = identificarSTMT.executeQuery();
            //si se encuentra el nombre en la base de datos
            while(rs.next()){
                //lee la plantilla de la base de datos
                byte templateBuffer[];
                try {
                    templateBuffer = rs.getBytes("huehuella");
                } catch (SQLException ex) {
                    Logger.getLogger(jEscaneoHuellas.class.getName()).log(Level.SEVERE, null, ex);
                }
                String nombre=rs.getString("huenombre");
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contenedor de template del
                //componente de huella digital
                setTemplate(referenceTemplate);
                //Compara las caracteristicas de la huella recientemente capturada
                //con alguna plantilla guardada en la base de datos que coincide con ese tipo
                DPFPVerificationResult result = verificador.verify(this.featuresvertification, getTemplate());
                //compara las plantillas (actual vs bd)
                //Si encuentra correspondencia dibuja el mapa
                //e indica el nombre de la persona que coincidio.
                if(result.isVerified()){
                    //crea la imagen de los datos guardados de las huellas guardadas
                    //en la base de datos
                    JOptionPane.showMessageDialog(null, "La hulla capturada es de "+ nombre, "Verificacion de huella ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                    }
            }
            //Si no encuentra alguna hulla correspondiente al nombre lo indica con un mensaje
            JOptionPane.showMessageDialog(null, "No existe ningun registro que coincida con la huella", "Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
            setTemplate(null);
        } catch (SQLException e){
            //Si ocurre un error lo indica en la consola
            System.err.println("Error al identificar huella dactialr."+e.getMessage());
        } finally{
            cn.desconectar();
        }
    }
    
    
    /**
     * Creates new form jEscaneoHuellas
     */
    
    public jEscaneoHuellas() {
        initComponents();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt){
        Iniciar();
        start();
        EstadoHuellas();
        btnGuardar.setEnabled(false);
        btnIdentificar.setEnabled(false);
        btnVerificar.setEnabled(false);
        btnTuVieja.grabFocus();
    }
    
    private void fromWindowClosed(java.awt.event.WindowEvent evt){
        stop();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblHuella = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnVerificar = new javax.swing.JButton();
        btnIdentificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnTuVieja = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHuella, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnIdentificar.setText("Identificar");
        btnIdentificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdentificarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnTuVieja.setText("Tu Vieja");
        btnTuVieja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuViejaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIdentificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTuVieja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerificar)
                    .addComponent(btnGuardar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIdentificar)
                    .addComponent(btnTuVieja))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTuViejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuViejaActionPerformed
        this.txtArea.append("\n Tu vieja");
    }//GEN-LAST:event_btnTuViejaActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        String nombre = JOptionPane.showInputDialog("Nombre a verificar: ");
        verificarHuella(nombre);
        reclutador.clear();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            guardarHuella();
            reclutador.clear();
            this.lblHuella.setIcon(null);
            start();
        } catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnIdentificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIdentificarActionPerformed
        try{ 
            identificarHuella();
            reclutador.clear();
        } catch(IOException ex){
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(jEscaneoHuellas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIdentificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jEscaneoHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jEscaneoHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jEscaneoHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jEscaneoHuellas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jEscaneoHuellas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIdentificar;
    private javax.swing.JButton btnTuVieja;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHuella;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
