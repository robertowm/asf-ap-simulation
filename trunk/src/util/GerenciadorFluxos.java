/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import agente.UsuarioAgente;
import fabrica.FabricaAgente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//>>>
        List<RelatorioResultados> relatorios = new ArrayList<RelatorioResultados>();
        for (Object object : FabricaAgente.getListaAgentes()) {
            UsuarioAgente agente = (UsuarioAgente) object;
            
            relatorios.add(new RelatorioResultados(agente.getRolesBeingPlayed()));
        }
        
        registrarFluxo("relatorios", new FluxoResultados(relatorios));
//<<<        
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