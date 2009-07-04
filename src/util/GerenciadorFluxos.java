/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.HashMap;
import java.util.Map;


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
        return registrarFluxo(nome, new Thread(thread, nome));
    }
    
    public static Thread registrarFluxo(String nome, Thread thread) {
        if(mapa.get(thread.getName()) == null){
            mapa.put(nome, thread);
            return thread;
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