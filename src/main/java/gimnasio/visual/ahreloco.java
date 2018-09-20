///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package gimnasio.visual;
//
///**
// *
// * @author adrian
// */
//public class ahreloco {
//    
//
//public class TablasCargar extends javax.swing.JInternalFrame {
// 
// DefaultTableModel Modelo= new DefaultTableModel(); //DEBO DEFINIR UN MODELO DE TABLA PARA PODER LUEGO UTILIZARLO CON MI JTABLE
//
// ControladoraVisual miLogica= new ControladoraVisual(); //DEFINO UN ATRIBUTO DE TIPO DE MI CONTROLADORA DE LA CAPA DE VISTAS
//
// public TablasCargar() {
//       
//       //AGREGO COLUMNAS AL MODELO QUE LUEGO SERAN VISTOS EN NUESTRA TABLA A CARGAR
//         Modelo.addColumn("Numero Comprobante");
//         Modelo.addColumn("Tipo de letra");
//         Modelo.addColumn("Punto de venta");
//         Modelo.addColumn("Numero Comprobante Easy");
//        
//        initComponents();
//       
//
//
//      }
//
////METODO QUE CARGA UNA TABLA
//
// public void cargarTabla(){
//
//         List<Cabecera> Lista= miLogica.dameMisCabeceras(); ///CARGO EN UNA COLECCION LOS ELEMENTOS QUE DESEO CARGAR; EN ESTE CASO LOS TRAIGO DESDE LA CONTROLADORA 													    VISUAL
//         Object[]fila= new Object[4];   ///GENERO UN VECTOR DE TIPO OBJECT DADO QUE EN EL VOY A CARGAR DISTINTOS TIPOS DE DATOS
//
//         for(Cabecera miCabecera:Lista){ ///RECORRO LA LISTA UTILIZANDO UN FOR EACH
//
//         ////AQUI LE ASIGNO A CADA ELEMENTO DE UN VECTOR LOS DATOS QUE QUIERO QUE SE MUESTREN
//
//             fila[0]=miCabecera.getNumCompRemLaCho().substring(5, 13);
//             fila[1]=miCabecera.getNumCompRemLaCho().substring(4, 5);
//             fila[2]=miCabecera.getNumCompRemLaCho().substring(0, 4);
//             fila[3]=miCabecera.getNumCompEasy();
//            
//             Modelo.addRow(fila);  ////AGREGO A MI MODELO UNA FILA (ES IMPORTANTE SABER QUE CADA VECTOR ES UNA FILA DA LA TABLA)
//          
//           
//         }
//         tblMiTabla.setModel(Modelo); ////UNA VEZ FINALIZADO LE ASIGNO A MI TABLA EL MODELO Y ESTO MOSTRARIA LOS DATOS 
//    }
//
////METODO PARA DESCARGAR O "LIMPIAR" UNA TABLA
//
//     public void limpiarTabla(){
//         
//         DefaultTableModel modelo2=(DefaultTableModel) tblMiTabla.getModel(); //GENERO UN NUEVO TABLE MODEL.. AL CUAL LE ASIGNO EL MODELO DE LA TABLA QUE CARGAMOS 																			CON ANTERIORIDAD
//         
//          int filas=tblMiTabla.getRowCount(); ///GENERO UN INDICE PARA SABER CUANTAS FILAS TIENE MI TABLA
//         
//         for(int i=0; i<filas;i++){    ////RECORRO EL INDICE A TRAVES DE UN CICLO FOR
//
//             modelo2.removeRow(0);   /////DE ESTA MANERA VOY QUITANDO EL SIEMPRE LA PRIMER FILA DEL MODELO...ESTO UNA VEZ FINALIZADO EL RECORRIDO DEL FOR NOS 								     ELIMINA TODOS LOS ELEMENTOS DE LA TABLA
//         
//         }
//
//    }
//
//    }
//
//
//
//
//    METODOS PARA CARGAR Y LIMPIAR UN JCOMBOBOX
//
//PARA LOS COMBOS ES SIMILAR SOLO QUE CAMBIAMOS ALGUNAS PALABRAS....
//
//public class ComboCargar extends javax.swing.JInternalFrame {
//
//	  DefaultComboBoxModel misPacientes= new DefaultComboBoxModel();
//      
//      ControladoraVisual miLogica= new ControladoraVisual();
//
//
//public ComboCargar(){
//	
//	cargarCombo();
//}
//
// public void cargarCombo(){
//
//    List<Paciente> misPaciente= miLogica.dameMisPacientes();
//    
//    for(Paciente miPaciente:misPaciente){
//    
//       misPacientes.addElement(miPaciente.getNombreApellido());
//      
//    }
//    
//    cmbPaciente.setModel(misPacientes);
//}
//
//public void limpiarCombo(){
//         
//         DefaultComboBoxModel modelo2=(DefaultComboBoxModel) cmbPaciente.getModel();
//
//          int filas=cmbPaciente.getItemCount(); 
//         
//         for(int i=0; i<filas;i++){    
//
//             modelo2.removeElementAt(0);   
//         
//         }
//
//
//}
//}
