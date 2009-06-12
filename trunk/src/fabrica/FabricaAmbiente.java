/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fabrica;

import ambiente.Residencia;
import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import framework.environment.MTS_Environment;
import framework.organization.MainOrganization;
import java.util.HashMap;
import java.util.Map;
import sis_multagente.Main;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class FabricaAmbiente {

    private static Map<String, Residencia> mapaAmbientes = new HashMap<String, Residencia>();    

    private static AMS ams = AMS.getInstance();

    public static Residencia getAmbiente(String nome, MainOrganization organizacao) {
        String nomeAmbiente = PREFIXO_NOME_AMBIENTE + nome;

        Residencia amb = mapaAmbientes.get(nomeAmbiente);

        if(amb != null) {
            return amb;
        }
        
        ElementID elementID = ams.createAgentElementId(nomeAmbiente, true);
        elementID.setAddress(LOCAL_HOST);
        
        Residencia ambiente = new Residencia(elementID);
        ams.createDescription(ambiente, elementID, "");

        mapaAmbientes.put(nomeAmbiente, ambiente);
        Principal p = new Principal();
        p.setVisible(true);
        p.setTitle(ambiente.toString());
        Main.desktop.add(p);

        return ambiente;
    }
    
    public static boolean existeAmbiente(String nome){
        return mapaAmbientes.containsKey(PREFIXO_NOME_AMBIENTE +nome);
    }
}
