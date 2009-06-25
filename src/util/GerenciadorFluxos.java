/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author robertowm
 */
public class GerenciadorFluxos {

    private static Map<String, Thread> mapa = new HashMap<String, Thread>();

    public static Thread recuperarFluxo(String nome) {
        return mapa.get(nome);
    }

    public static Thread registrarFluxo(String nome, Runnable thread) {
        if(mapa.get(nome) == null){
            Thread novaThread = new Thread(thread, nome);
            mapa.put(nome, novaThread);
        }

        return null;
    }
    
    public static void iniciarFluxo(){
        for (String str : mapa.keySet()) {
            mapa.get(str).start();
        }
    }
    
    public static void pausarFluxo(){
        for (String str : mapa.keySet()) {
                mapa.get(str).suspend();
        }
    }
    
    public static void continuaFluxo(){
        for (String str : mapa.keySet()) {
            mapa.get(str).resume();
        }
    }
    
    
}