package br.com.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {
	
	public static ResourceBundle text = null;
    
	/**
	 * Método responsável por criar um ResourceBudle 
	 */
	public static void init() {
    	/**
    	 * pega a internacionalização do sistema operacional
    	 */
        Locale locale = Locale.getDefault();
        text = ResourceBundle.getBundle("br.com.msg.messages",locale);
    }
	
    /**
     * Método responsável por pegar a menssagem através da chave passada.
     * 
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        if( text == null ) {
            init();
        }
        return text.getString(key);
    }
}
