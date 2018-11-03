package gimnasio.visual;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class jDialogHuella extends javax.swing.JDialog {

    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPFeatureSet featuresinscripcion;
    private DPFPFeatureSet featuresvertification;
    private DPFPTemplate planilla;
    private String nombre;
   
    public jDialogHuella(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e){
                init();
                start();
            }
            
            @Override
            public void componentHidden(ComponentEvent e){
                
            }
        });
    }

    protected void init(){
        lector.addDataListener((DPFPDataEvent dpfpde) -> {
            procesarHuella(dpfpde.getSample());
        });
        
        lector.addReaderStatusListener(new DPFPReaderStatusListener(){
            @Override
            public void readerConnected(DPFPReaderStatusEvent dpfprs){
                txtArea.append("Lector de huellas conectado.\n");
            }
            @Override
            public void readerDisconnected(DPFPReaderStatusEvent dpfprs){
                txtArea.append("Lector de huellas desconectado.\n");
            }
        });
        
    }
    
    protected void procesarHuella(DPFPSample sample){
        //con las dos lineas siguientes muestro la huella en la pantalla
        Image image = DPFPGlobal.getSampleConversionFactory().createImage(sample);
        drawPicture(image);
        //con esta linea se pretende extraer una nueva huella para guardarla despues
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        //con esta linea le digo que voy a verificar la huella con otra
        featuresvertification = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        
        if(featuresinscripcion != null){
            try{
                txtArea.append("\nSe ha detectado una huella.\n");
                reclutador.addFeatures(featuresinscripcion);
            } catch(DPFPImageQualityException ex){
                txtArea.append("\nSe produjo el siguiente error: " + ex.getMessage());
            } finally{
                EstadoHuellas(); //comprueba si se creo la plantilla con exito
                switch(reclutador.getTemplateStatus()){
                    case TEMPLATE_STATUS_READY:
                        setTemplate(reclutador.getTemplate());
                        break;
                    case TEMPLATE_STATUS_FAILED:
                        reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(this, "La plantilla sufrio una descompostura, intente nuevamente");
                        start();
                        break;
                }
            }
        }
        
    }
    
    public void guardarHuella(){
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(planilla.serialize());
        Integer tamanoHuella=planilla.serialize().length;
        //pregunta el nombre de la persona a la cual corresponde dicha huella
        nombre = JOptionPane.showInputDialog("Nombre: ");
            
        JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
            this.btnGuardar.setEnabled(false);
            this.btnVerificar.grabFocus();
    }
    
    public void verificarHuella(){
     
            //Crea una nueva plantilla a partir de la guardada en la base de datos
            DPFPTemplate referenceTemplate = planilla;
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            //setTemplate(referenceTemplate);
            
            //Compara las caracteristicas de la huella recientemente capturada
            //con la plantilla guardada al usuario especifico en la base de datos
            DPFPVerificationResult result = verificador.verify(this.featuresvertification, getTemplate());
            
            if(result.isVerified()){
                JOptionPane.showMessageDialog(null, "La huella capturada coincide con la de "+nombre, "Verificacion de huella", JOptionPane.INFORMATION_MESSAGE);           
            } else{
                JOptionPane.showMessageDialog(null, "No corresponde la huella con "+ nombre, "Verificacion de huella", JOptionPane.ERROR_MESSAGE);
            } 
          
    }
    
    public void identificarHuella() throws IOException{
         //Crea una nueva plantilla a partir de la guardada en la base de datos
            DPFPTemplate referenceTemplate = planilla;
            //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
            //setTemplate(referenceTemplate);
            
    }
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try{
            return extractor.createFeatureSet(sample, purpose);
        }catch(DPFPImageQualityException e){
            return null;
        }
    }
    
    
    public void drawPicture(Image image){
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(image, 0, 0 , null);
        bGr.dispose();
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(180), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);
        lblHuella.setIcon(new ImageIcon(image.getScaledInstance(lblHuella.getWidth(), lblHuella.getHeight(), Image.SCALE_DEFAULT)));
    }
    
    public void stop(){
        lector.stopCapture();
        txtArea.append("No se esta usando el lector de Huellas Dactular\n");
    }
    
    public void EstadoHuellas(){
        txtArea.append("\nMuestra de Huellas Necesarias para Guardar Template " + reclutador.getFeaturesNeeded());
    }
    
    public void setTemplate(DPFPTemplate template){
        DPFPTemplate old = this.planilla;
        this.planilla = template;
        firePropertyChange("template", old, template);
    }
    
    public DPFPTemplate getTemplate(){
        return planilla;
    }
    
    public void start(){
        lector.startCapture();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblHuella = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(lblHuella, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });
        jPanel3.add(btnVerificar);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarHuella();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        verificarHuella();
    }//GEN-LAST:event_btnVerificarActionPerformed

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
            java.util.logging.Logger.getLogger(jDialogHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogHuella dialog = new jDialogHuella(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHuella;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
