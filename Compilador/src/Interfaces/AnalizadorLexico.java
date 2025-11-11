package Interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Realiza el análisis léxico separando el código fuente en Tokens.
 */
public class AnalizadorLexico {
    
    // Lista de palabras reservadas del lenguaje industrial [cite: 93]
    private static final List<String> PALABRAS_RESERVADAS = Arrays.asList(
        "VAR", "CONST", "SENSOR", "ACTUADOR", "INT", "FLOAT", "BOOL", "IF", "ELSE", 
        "ENDIF", "ACTIVATE", "DESACTIVATE", "MIENTRAS", "VERDADERO", "PROCESS", "TASK"
    );
    
    // Lista para almacenar los tokens generados
    private final ArrayList<Token> listaTokens;

    public AnalizadorLexico() {
        this.listaTokens = new ArrayList<>();
    }

    /**
     * Analiza el texto de código fuente y genera la lista de tokens.
     * @param codigoFuente El texto del JTextArea (txtEscritura).
     * @return La lista de tokens generados.
     */
    public ArrayList<Token> analizar(String codigoFuente) {
        listaTokens.clear();
        String[] lineas = codigoFuente.split("\n");

        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i].trim();
            int numLinea = i + 1;

            if (linea.isEmpty()) {
                continue; // Ignorar líneas vacías
            }

            // Separar la línea en "palabras" o "lexemas" simples (separando por espacios)
            // NOTA: Esta es una simplificación MUY GRANDE. Un analizador real usaría un 
            // autómata o regex para manejar operadores y delimitadores sin espacios.
            String[] lexemas = linea.split("\\s+"); 

            for (String lexema : lexemas) {
                if (lexema.isEmpty()) {
                    continue;
                }
                
                String tipoToken = clasificarLexema(lexema);
                listaTokens.add(new Token(tipoToken, lexema, numLinea));
            }
        }
        return listaTokens;
    }

    /**
     * Determina la categoría de un lexema dado (muy simplificado).
     */
    private String clasificarLexema(String lexema) {
        if (PALABRAS_RESERVADAS.contains(lexema.toUpperCase())) {
            // Reconocimiento de palabras reservadas (ej. IF, SENSOR) [cite: 93]
            return "PALABRA_RESERVADA";
        } else if (lexema.matches("(\\+|-|\\*|/|=|>=|<=|>|<|!=|==|\\{|\\}|;|:)")) {
             // Reconocimiento básico de operadores/delimitadores [cite: 66, 68, 70]
            return "OPERADOR_DELIMITADOR";
        } else if (lexema.matches("-?\\d+(\\.\\d+)?")) {
            // Reconocimiento de números enteros o flotantes [cite: 105]
            return "NUMERO";
        } else if (lexema.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            // Reconocimiento de identificadores (variables, sensores, etc.) [cite: 63]
            return "IDENTIFICADOR";
        } else {
            return "ERROR_LEXICO";
        }
    }
}