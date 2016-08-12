package br.com.thread;

import java.io.File;

import javax.swing.JOptionPane;

import br.com.util.ArquivoUtil;
import br.com.util.Constantes;
  
/**
 * Thread responsável por monitorar uma determinada pasta
 * 
 * 
 * @author Dalembert Menezes
 *
 */
public class MonitorThread implements Runnable {  
  
    private Thread threadMonitor;  
    private String dir;
  
    /**
     * Método responsável por iniciar a thread e setar o diretorio onde deve ser monitorado
     * 
     * @param diretorio - Local do sistema onde será monitorada
     */
    public void init(String diretorio) {  
          
        // diretorio onde vai ser feito a verificacao  
        setDir(diretorio);
        startThread();
    }  
    
    
    /**
     * Método responsável por "Startar" a thread. 
     */
    private void startThread(){
    	this.threadMonitor = new Thread(this);  
        this.threadMonitor.start();  
    }
    
    /**
     * Método responsável por "Parar" a thread. 
     */
    public void stopThread(){
    	this.threadMonitor = new Thread(this);  
        this.threadMonitor.stop();  
    }
    
    /**
     * Método responsável por exibir uma popup com a mensagem que encontrou arquivo</br>
     * na pasta monitorada.
     * 
     * @param file - nome do arquivo encontrado
     */
    private void showPopupMensageArquivoEncontrado(String file) {  
        JOptionPane.showMessageDialog(null, "evento -> novo arquivo encontrado : " + file);
    }  
  
    @Override  
    public void run() {  
        Thread currentThread = Thread.currentThread();  
          
        // thread que fica verificando a pasta  
        while (this.threadMonitor == currentThread) {  
              
        	procurarDentroDaPasta(getDir());  
              
            try {  
                Thread.sleep(1000 * 10); // 10 segundos  
            } catch (InterruptedException e) {  
            	
            	System.err.println("Erro na Therad" + e);
            	JOptionPane.showMessageDialog(null, "Erro na Therad." + e.getMessage());
            }  
        }  
    }  
    
    private void procurarDentroDaPasta(String dir) {  
        File arquivos[] = ArquivoUtil.procurarArquivos(getDir(), Constantes.TIPO_ARQUIVO_TXT);
    	
        for (int i = 0; i < arquivos.length; i++) {  
            // se encontrar algum arquivo txt na pasta   
            File file = arquivos[i];  
            showPopupMensageArquivoEncontrado(file.getName());  
        }  
          
    }  
    

    public String getDir() {  
        return dir;  
    }  
  
    public void setDir(String dir) {  
        this.dir = dir;  
    }  
    
    public Thread getThreadMonitor() {
		return threadMonitor;
	}
    
  
} 