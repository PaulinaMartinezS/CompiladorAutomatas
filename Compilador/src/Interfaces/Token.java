package Interfaces;

/**
 * Represen ta la unidad mínima del lenguaje reconocida por el Analizador Léxico.
 */
public class Token {
    
    // Categoría del token (ej. "PALABRA_RESERVADA", "IDENTIFICADOR", "OPERADOR")
    private String tipo; 
    
    // El texto original (lexema) encontrado en el código (ej. "IF", "flujo", "=")
    private String lexema; 
    
    // Línea donde fue encontrado
    private int linea; 

    public Token(String tipo, String lexema, int linea) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linea = linea;
    }

    public String getTipo() { return tipo; }
    public String getLexema() { return lexema; }
    public int getLinea() { return linea; }

    /**
     * Devuelve el token en un formato legible para la consola.
     */
    @Override
    public String toString() {
        return String.format("<%s, \"%s\"> (Línea: %d)", tipo, lexema, linea);
    }
}