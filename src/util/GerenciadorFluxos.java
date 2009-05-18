/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import framework.agent.Agent;
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
        if(mapa.get(nome) == null){
            Thread novaThread = new Thread(thread, nome);
            novaThread.start();
            
            mapa.put(nome, novaThread);
        }

        return null;
    }
}