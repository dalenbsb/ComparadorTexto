package br.com.visao;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import br.com.thread.MonitorThread;
import br.com.util.ArquivoUtil;
import br.com.util.Constantes;
import br.com.util.MessageUtil;

/**
 * Classe principal, responsável por iniciar a aplicação.
 * 
 * @author Dalembert Menezes
 * @version 1.0
 * @since Release 01 da aplicação
 */
public class PrincipalVisao extends JFrame{

	private static final long serialVersionUID = 2568184681403208554L;
	private JLabel labelArq1;
    private JLabel labelArq2;
    
    private JButton btnArq1;
    private JButton btnArq2;
    
    private JTextField tfArq1;
    private JTextField tfArq2;
    
    private JTextPane taArq1;
    private JTextPane taArq2;
    
    private File arq1 = null;
	private File arq2 = null;
	
	private JButton btnComparar;
	private JButton btnLimparTudo;
	
	private JButton btnStartThread;
	private JButton btnStopThread;
	
	private MonitorThread monitorThread;
	
    public PrincipalVisao() {

    	this.getContentPane().setLayout(null);
    	this.getContentPane().setBackground(Constantes.COR_BACKGROUND_FRAME_PRINCIPAL);
        this.setSize(Constantes.WIDTH_FRAME_PRINCIPAL, Constantes.HEIGTH_FRAME_PRINCIPAL);
        this.setLocation(Constantes.LOCATION_X_FRAME_PRINCIPAL, Constantes.LOCATION_Y_FRAME_PRINCIPAL);
        this.setTitle(MessageUtil.getMessage("label.titlo.comparador.arquivos"));
        this.setResizable(false);
        
        labelArq1 = new JLabel(MessageUtil.getMessage("label.arquivo.1"));
        labelArq1.setBounds(new Rectangle(14, 67, 181, 13));
        this.getContentPane().add(labelArq1, null);
        
        labelArq2 = new JLabel(MessageUtil.getMessage("label.arquivo.2"));
        labelArq2.setBounds(new Rectangle(14, 145, 181, 13));
        this.getContentPane().add(labelArq2, null);
        
        btnArq1 = new JButton(MessageUtil.getMessage("label.btn.add.arquivo.1"));
        btnArq1.setBounds(new Rectangle(3, 24, 150, 30));
        this.getContentPane().add(btnArq1, null);
        
        btnArq1.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){
                	
                	arq1 = ArquivoUtil.escolherArquivo();
                	
                	if(arq1 != null){
                		tfArq1.setText(arq1.getPath());
                	}
                }
            } 		
        );
        
        btnArq2 = new JButton(MessageUtil.getMessage("label.btn.add.arquivo.2"));
        btnArq2.setBounds(new Rectangle(3, 105, 150, 30));
        this.getContentPane().add(btnArq2, null);
        
        btnArq2.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){

                	arq2 = ArquivoUtil.escolherArquivo();
                	
                	if(arq2 != null){
                		tfArq2.setText(arq2.getPath());
                	}
                }
            } 		
        );
        
        
        tfArq1 = new JTextField();
        tfArq1.setBounds(new Rectangle(86, 62, 500, 21));
        this.getContentPane().add(tfArq1, null);
        
        tfArq2 = new JTextField();
        tfArq2.setBounds(new Rectangle(86, 140, 500, 21));
        this.getContentPane().add(tfArq2, null);
        
        taArq1 = new JTextPane();
        taArq1.setBounds(new Rectangle(11, 207, 321, 328));
        this.getContentPane().add(taArq1, null);
        
        
        taArq2 = new JTextPane();
        taArq2.setBounds(new Rectangle(344, 210, 337, 330));
        this.getContentPane().add(taArq2, null);
        
        
        btnComparar = new JButton(MessageUtil.getMessage("label.btn.comparar"));
        btnComparar.setBounds(new Rectangle(3, 550, 150, 30));
        this.getContentPane().add(btnComparar, null);
        
        btnComparar.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){
                	compararArquivosSelecionados();
                }
            } 		
        );
        
        btnLimparTudo = new JButton(MessageUtil.getMessage("label.btn.limpar"));
        btnLimparTudo.setBounds(new Rectangle(160, 550, 150, 30));
        this.getContentPane().add(btnLimparTudo, null);
        
        btnLimparTudo.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   resetTela();
                }
            } 		
        );
        
        
        btnStartThread = new JButton(MessageUtil.getMessage("label.btn.start.monitor"));
        btnStartThread.setBounds(new Rectangle(317, 550, 150, 30));
        this.getContentPane().add(btnStartThread, null);
        
        btnStartThread.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   startThread();
                }
            } 		
        );
        
        
        btnStopThread = new JButton(MessageUtil.getMessage("label.btn.stop.monitor"));
        btnStopThread.setBounds(new Rectangle(474, 550, 150, 30));
        btnStopThread.setEnabled(false);
        this.getContentPane().add(btnStopThread, null);
        
        btnStopThread.addActionListener(
        		
    		new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   stopThread();
                }
            } 		
        );
        
        this.setVisible(true);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                aoFechar();
            }
        });
    }
    
    /**
     * Método responsável por executar a thread que monitora um determinado diretorio
     */
    private void startThread() {
    	File pastaMonitorada = ArquivoUtil.selecionarDiretorio();
    	getMonitorThread().init(pastaMonitorada.getPath());
    	btnStartThread.setEnabled(false);
    	btnStopThread.setEnabled(true);
	}
    
    /**
     * Método responsável por para a execução da thread
     */
    private void stopThread() {
    	getMonitorThread().stopThread();
    	btnStartThread.setEnabled(true);
    	btnStopThread.setEnabled(false);
	}
    
    /**
     * Método responsável por comparar os arquivos
     */
    private void compararArquivosSelecionados(){
    	if(validarSelecionouArquivos()){
            lerArquivoComparar(arq1, arq2);
        }else{
            JOptionPane.showMessageDialog(null, MessageUtil.getMessage("msg.voce.nao.selecionou.arquivo"));
        }
    }
    
    /**
     * Método responsavel por validar se os arquivos foram selecionados.
     * 
     * @return Retorna "true" se é válido ou "false" caso não seja Válido - Boolean 
     */
    private Boolean validarSelecionouArquivos(){
    	return (arq1 != null && arq2 != null);
    }
    
    
    /**
     * Método responsável por limpar todos os campos da tela. 
     */
    private void resetTela(){
    	arq1 = null;
        arq2 = null;
     	
        tfArq1.setText(null);
        tfArq2.setText(null);
        
        limparTextoJtextPanel();
    }

    
    /**
     * Método acionado ao no evento de fechar a janela 
     */
    private void aoFechar() {
        System.exit(0);
    }
    
	/**
	 * Método responsável por ler o arquivo e comparar linha por linha.
	 * 
	 * @param arq1
	 * @param arq2
	 */
	private void lerArquivoComparar(File arq1, File arq2){
		try {
			limparTextoJtextPanel();
			
			ArquivoUtil.escreverColorirJtextPane(taArq1, taArq2, arq1, arq2);
			ArquivoUtil.compararArquivosBinarios(arq1, arq2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void limparTextoJtextPanel(){
		taArq1.setText(null);
        taArq2.setText(null);
	}
	
    public MonitorThread getMonitorThread() {
    	if(monitorThread == null){
    		monitorThread = new MonitorThread();
    	}
		return monitorThread;
	}
   
   

}
