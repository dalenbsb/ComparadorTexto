package br.com.util;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class JtextPanelUtil {
	
    /**
     * Altera o atributo de cor de texto e cor do fundo para as posições das linhas
     * 
     * @param textPane - Variável passada por Referencia - JTextPane
     * @param corTexto - Color
     * @param corFundo - Color
     * @param posicaoInicio - Posição incial da linha a ser marcarda - int
     * @param posicaoFim - posição final da linha a ser marcada - int
     */
    public static void mudarEstilo(JTextPane textPane,Color corFundo, String linha){
    	StyledDocument doc = textPane.getStyledDocument();

        Style style = textPane.addStyle("estilo", null);
        StyleConstants.setBackground(style, corFundo);
         
        try {
			doc.insertString(doc.getLength(), linha.concat("\n") ,style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
        
    }
   
}
