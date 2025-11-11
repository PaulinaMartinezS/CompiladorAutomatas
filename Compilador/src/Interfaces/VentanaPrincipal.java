
package Interfaces;
import Interfaces.AnalizadorLexico;
import Interfaces.VentanaLexico;
import Interfaces.Token;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author pauli
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    private AnalizadorLexico analizadorLexico;
    
    public VentanaPrincipal() {
        initComponents();
        NumeroDeLinea tln = new NumeroDeLinea(txtEscritura);
        jScrollPane2.setRowHeaderView(tln);
        this.setLocationRelativeTo(null);
        txtConsola.setEditable(false);
        txtEscritura.setEditable(true);
        analizadorLexico = new AnalizadorLexico();
        System.out.println("Inicio");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEscritura = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        mGuardar = new javax.swing.JMenuItem();
        mGuardarComo = new javax.swing.JMenuItem();
        mCompilar = new javax.swing.JMenu();
        mAnaLex = new javax.swing.JMenuItem();
        mAnaSin = new javax.swing.JMenuItem();
        mTS = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtConsola.setBackground(new java.awt.Color(255, 255, 204));
        txtConsola.setColumns(20);
        txtConsola.setRows(5);
        jScrollPane1.setViewportView(txtConsola);

        txtEscritura.setColumns(20);
        txtEscritura.setRows(5);
        txtEscritura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEscrituraKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtEscritura);

        mArchivo.setText("Archivo");

        mGuardar.setText("Guardar");
        mArchivo.add(mGuardar);

        mGuardarComo.setText("Guardar como...");
        mArchivo.add(mGuardarComo);

        jMenuBar1.add(mArchivo);

        mCompilar.setText("Compilar");

        mAnaLex.setText("Analizador léxico");
        mAnaLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAnaLexActionPerformed(evt);
            }
        });
        mCompilar.add(mAnaLex);

        mAnaSin.setText("Analizador sintáctico");
        mAnaSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAnaSinActionPerformed(evt);
            }
        });
        mCompilar.add(mAnaSin);

        jMenuBar1.add(mCompilar);

        mTS.setText("Tabla de Símbolos");

        jMenuItem1.setText("Fija");
        mTS.add(jMenuItem1);

        jMenuItem2.setText("Arreglos");
        mTS.add(jMenuItem2);

        jMenuItem3.setText("Variable");
        mTS.add(jMenuItem3);

        jMenuBar1.add(mTS);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mAnaLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAnaLexActionPerformed
                                      
    txtConsola.setText("Iniciando Análisis Léxico...\n");
    String codigoFuente = txtEscritura.getText();
    
    if (codigoFuente.trim().isEmpty()) {
        txtConsola.append("Error: No hay código para analizar.");
        return;
    }

    // Ejecutar el Analizador Léxico
    // Asegúrate de que tu 'analizadorLexico' esté inicializado
    ArrayList<Token> tokens = analizadorLexico.analizar(codigoFuente);

    VentanaLexico ventana = new VentanaLexico(tokens);
    ventana.mostrar();
    
    txtConsola.append("Análisis Léxico completado. Resultados mostrados en la nueva ventana.");

    }//GEN-LAST:event_mAnaLexActionPerformed
    
    /*private int obtenerNumeroLinea(JTextArea tex){
        //estoy probando esta función
        String texto = tex.getText();
        int numLinea = 1;
        numLinea = numLinea+texto.split("\n").length;
        System.out.println("Número de líneas: "+numLinea);
        return numLinea;
    }*/
    
    private void mAnaSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAnaSinActionPerformed
        System.out.println("Analisis sintactico");
        //txtConsola.setEditable(false);
    }//GEN-LAST:event_mAnaSinActionPerformed

    private void txtEscrituraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEscrituraKeyReleased
        char c = (char) evt.getKeyCode();
        if (c == evt.VK_ENTER) {
            System.out.println("Se presiono la letra enter");
            //txtEscritura.setText(txtEscritura.getText()+"\n"+obtenerNumeroLinea(txtEscritura));
            txtEscritura.setText(txtEscritura.getText());
        }
    }//GEN-LAST:event_txtEscrituraKeyReleased

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mAnaLex;
    private javax.swing.JMenuItem mAnaSin;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mCompilar;
    private javax.swing.JMenuItem mGuardar;
    private javax.swing.JMenuItem mGuardarComo;
    private javax.swing.JMenu mTS;
    private javax.swing.JTextArea txtConsola;
    private javax.swing.JTextArea txtEscritura;
    // End of variables declaration//GEN-END:variables
}
