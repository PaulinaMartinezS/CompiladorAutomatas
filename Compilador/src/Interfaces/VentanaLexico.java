package Interfaces;

import Interfaces.Token;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaLexico extends JFrame {

    private JTable tablaTokens;
    private DefaultTableModel modeloTabla;

    public VentanaLexico(ArrayList<Token> tokens) {
        // 1. Configuración básica de la ventana
        this.setTitle("Resultado del Análisis Léxico");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null); // Centrar la ventana
        
        // 2. Definir el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"Lexema", "Tipo", "Línea"});
        
        // 3. Llenar la tabla con los datos de los Tokens
        cargarDatos(tokens);
        
        // 4. Crear la JTable y el JScrollPane
        tablaTokens = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaTokens);
        
        // 5. Agregar el scrollPane a la ventana
        this.add(scrollPane);
    }
    
    /**
     * Llena el modelo de la tabla con la lista de tokens.
     */
    private void cargarDatos(ArrayList<Token> tokens) {
        for (Token t : tokens) {
            String tipo = t.getTipo();
            String lexema = t.getLexema();
            int linea = t.getLinea();
            
            // Si hay errores, resaltarlos de alguna manera (opcional)
            if (tipo.equals("ERROR_LEXICO")) {
                 lexema = "⚠️ " + lexema;
                 tipo = "ERROR LÉXICO";
            }
            
            // Añadir la fila a la tabla
            modeloTabla.addRow(new Object[]{lexema, tipo, linea});
        }
    }

    public void mostrar() {
        this.setVisible(true);
    }
}