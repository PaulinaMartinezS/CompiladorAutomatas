package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

/*
 esta clase es para que los numeros de linea salgan fuera de la caja de texto, 
le daba enter rapidamente cuando escribia y se sobreponia con los numeros
 */
public class NumeroDeLinea extends JPanel implements PropertyChangeListener, DocumentListener {

    private final static Color LUZ_TENUE = new Color(150, 150, 150);
    private final static int MARGEN_IZQUIERDO = 5;
    private final static int MARGEN_DERECHO = 5;
    private final static int MINIMO_DIGITOS = 3;

    private JTextComponent component;

    public NumeroDeLinea(JTextComponent component) {
        this.component = component;
        // Escucha cambios en el texto
        component.getDocument().addDocumentListener(this);
        // Escucha cambios en el tamaño o fuente del componente
        component.addPropertyChangeListener("font", this);
        component.addPropertyChangeListener("document", this);
        // Inicializa el tamaño
        setFont(component.getFont());
        setPreferredSize();
    }

    private void setPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int ancho = fm.stringWidth("1") * MINIMO_DIGITOS;
        Insets insets = getInsets();
        ancho += insets.left + insets.right + MARGEN_IZQUIERDO + MARGEN_DERECHO;
        
        Dimension d = getPreferredSize();
        d.setSize(ancho, component.getHeight());
        setPreferredSize(d);
        setSize(d);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(LUZ_TENUE);
        g.setFont(component.getFont());
        
        // 2. Obtener la información del texto
        FontMetrics fm = getFontMetrics(g.getFont());
        int fontHeight = fm.getHeight();
        int currentLine = -1;

        try {
            Rectangle viewRect = getVisibleRect();
            
            // Determina la primera y última línea visible
            int startOffset = component.viewToModel(new Point(0, viewRect.y));
            int endOffset = component.viewToModel(new Point(0, viewRect.y + viewRect.height));
            
            Document doc = component.getDocument();
            Element root = doc.getDefaultRootElement();
            int startLine = root.getElementIndex(startOffset);
            int endLine = root.getElementIndex(endOffset);

            for (int i = startLine; i <= endLine; i++) {
                // Obtiene la posición de la línea en la pantalla
                Element element = root.getElement(i);
                Rectangle r = component.modelToView(element.getStartOffset());

                if (r == null) {
                    continue;
                }

                int y = r.y + r.height - fm.getDescent();
                
                String text = String.valueOf(i + 1);
                int anchoTexto = fm.stringWidth(text);
                int x = getInsets().left + MARGEN_IZQUIERDO + (getPreferredSize().width - MARGEN_IZQUIERDO - MARGEN_DERECHO - anchoTexto);
                
                g.drawString(text, x, y);
            }
        } catch (BadLocationException e) {
            System.err.println("Error al obtener ubicación: " + e.getMessage());
        }
    }

    // --- Métodos de sincronización (DocumentListener y PropertyChangeListener) ---

    private void textChanged() {
        // Redibuja el JScrollPane entero cuando cambia el texto
        revalidate();
        repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) { textChanged(); }
    @Override
    public void insertUpdate(DocumentEvent e) { textChanged(); }
    @Override
    public void removeUpdate(DocumentEvent e) { textChanged(); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Actualiza el tamaño si cambia la fuente (para que el ancho se ajuste)
        if ("document".equals(evt.getPropertyName())) {
            Document doc = (Document) evt.getOldValue();
            if (doc != null) {
                doc.removeDocumentListener(this);
            }

            doc = (Document) evt.getNewValue();
            if (doc != null) {
                doc.addDocumentListener(this);
            }
        } else if ("font".equals(evt.getPropertyName())) {
            setFont(component.getFont());
            setPreferredSize();
        }
    }
}