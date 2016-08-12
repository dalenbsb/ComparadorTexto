package br.com.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class ArquivoUtil {

	/**
	 * Método responsável por ler um arquivo e retornar os bytes
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] read(File file) throws IOException {

		byte[] content = null;
		int fileLength = (int) file.length();
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);

			BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
			content = new byte[fileLength];
			bufferedInput.read(content, 0, fileLength);
			bufferedInput.close();
		} finally {
			if (fileInput != null) {
				fileInput.close();
			}
		}

		return content;
	}
	
	
	 /**
     * Método Reponsável por comparar dois arquivos do tipo java.io.File
     * Caso os arquivos sejam iguais, é exibido uma popup
     * 
     * @param arq1 - arquivo do tipo 
     * @param arq2 - arquivo do tipo
     */
	public static Boolean compararBinariosArquivos(File arq1, File arq2) throws IOException{
		
		byte[] byteArquivo1 = read(arq1);
		byte[] byteArquivo2 = read(arq2);
		
		return Arrays.equals(byteArquivo1, byteArquivo2);
	}
	
	
	/**
	 * @param dir -> diretorio do computador - String
	 * @param tipo -> tipo de arquivo a ser monitorado - String
	 * 
	 * @return
	 */
	public static File[] procurarArquivos(String dir, String tipo){
		File diretorio = new File(dir);  
        
        // lista somente arquivos  
        File arquivos[] = diretorio.listFiles(
        	new FileFilter() {  
	            public boolean accept(File pathname) {  
	                return pathname.getName().toLowerCase().endsWith(".".concat(tipo));  
	            }  
        });  
        
        return arquivos;
	}
	
	
	/**
	 * Método acionado para abrir uma caixa de dialogo para escolher um arquivo na máquina
	 * 
	 * @return
	 */
	public static File escolherArquivo() {
		  JFileChooser fc = new JFileChooser();
		  File arquivo = null;
				  
          int res = fc.showOpenDialog(null);
          
          if(res == JFileChooser.APPROVE_OPTION){
               arquivo = fc.getSelectedFile();
          }else{
              JOptionPane.showMessageDialog(null, MessageUtil.getMessage("msg.voce.nao.selecionou.arquivo"));
          }
		return arquivo;
	}
	
	/**
	 * Método responsável por escrever dentro do JtextPanel e comparar linha por linha.
	 * Caso encontre diferenca na linha é alterada a cor do fundo
	 * 
	 * @param taArq1 - Variavel alteradas por referência - JTextPane
	 * @param taArq2 - Variavel alteradas por referência - JTextPane
	 * @param arq1 - File
	 * @param arq2 - File
	 * @throws IOException
	 */
	public static void escreverColorirJtextPane(JTextPane taArq1, JTextPane taArq2, File arq1, File arq2 ) throws IOException{
		
		FileReader file1 = new FileReader(arq1);
    	FileReader file2 = new FileReader(arq2);
    	
		BufferedReader lerArq1 = new BufferedReader(file1);
		BufferedReader lerArq2 = new BufferedReader(file2);
    	
    	String linha1 = lerArq1.readLine();
    	String linha2 = lerArq2.readLine();
    	
    	
    	while(linha1 != null || linha2 != null){
    		
    		if(linha1 != null && linha2 != null){
    			if(linha1.equals(linha2)){
    				JtextPanelUtil.mudarEstilo(taArq1, Constantes.COR_FUNDO_JTEXTPANE_DEFAULT, linha1);
    				JtextPanelUtil.mudarEstilo(taArq2, Constantes.COR_FUNDO_JTEXTPANE_DEFAULT, linha2);
    			}else{
    				JtextPanelUtil.mudarEstilo(taArq1, Constantes.COR_FUNDO_JTEXTPANE_DIFERENCA, linha1);
    				JtextPanelUtil.mudarEstilo(taArq2, Constantes.COR_FUNDO_JTEXTPANE_DIFERENCA, linha2);
    			}
    		}else if(linha1 != null){
    			JtextPanelUtil.mudarEstilo(taArq1, Constantes.COR_FUNDO_JTEXTPANE_DIFERENCA, linha1);
    		}else if(linha2 != null){
    			JtextPanelUtil.mudarEstilo(taArq2, Constantes.COR_FUNDO_JTEXTPANE_DIFERENCA, linha2);
    		}

    		if(linha1 != null){
    			linha1 = lerArq1.readLine();//lê a segunda até a última linha
    		}
    		
    		if(linha2 != null){
    			linha2 = lerArq2.readLine();//lê a segunda até a última linha
    		}
    	}
    	
	}
	
	/**
	 * Método responsável por comparar dois arquivos binarios.
	 * caso os arquivos seja iguais é lançado uma popup com uma mensagem informando o usuário 
	 * 
	 * @param arq1 - File
	 * @param arq2 - File
	 */
	public static void compararArquivosBinarios(File arq1, File arq2){
		try {
			if(ArquivoUtil.compararBinariosArquivos(arq1, arq2)){
				JOptionPane.showMessageDialog(null, MessageUtil.getMessage("msg.arquivos.iguais"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método responsável por selecionar um diretorio
	 * @return
	 */
	public static File selecionarDiretorio(){
		File diretorio = null;
		
		JFileChooser fc = new JFileChooser();
        
        // restringe a amostra a diretorios apenas
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int res = fc.showOpenDialog(null);
        
        if(res == JFileChooser.APPROVE_OPTION){
            diretorio = fc.getSelectedFile();
            JOptionPane.showMessageDialog(null, MessageUtil.getMessage("msg.pasta.monitorada")+ " " + diretorio.getName());
        } else {
            JOptionPane.showMessageDialog(null, MessageUtil.getMessage("msg.voce.nao.selecionou.arquivo"));
        }
        
        return diretorio;
	}
	
	
}
