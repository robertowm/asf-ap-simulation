/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fabrica;

import ambiente.Ambiente;
import ambiente.Residencia;
import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import framework.organization.MainOrganization;
import java.util.HashMap;
import java.util.Map;
import sis_multagente.Main;
import visual.JAmbiente;

/**
 *
 * @author heliokann
 */
public class FabricaAmbiente {

    private static Map<String, Ambiente> mapaAmbientes = new HashMap<String, Ambiente>();

    private static AMS ams = AMS.getInstance();

    public static Ambiente getAmbiente(String nome, MainOrganization organizacao) {
        String nomeAmbiente = PREFIXO_NOME_AMBIENTE + nome;

        Ambiente amb = mapaAmbientes.get(nomeAmbiente);

        if(amb != null) {
            return amb;
        }
        Ambiente ambiente = criarResidencia(nomeAmbiente);

        adicionarAmbiente(ambiente);

        return ambiente;
    }

    public static void adicionarAmbiente(Ambiente ambiente) {
        mapaAmbientes.put(ambiente.getEnvironmentName(), ambiente);
        JAmbiente p = new JAmbiente(ambiente);
//        p.setVisible(true);
        p.setTitle(ambiente.toString());
        Main.desktop.add(p);
    }
    
    public static boolean existeAmbiente(String nome){
        return mapaAmbientes.containsKey(PREFIXO_NOME_AMBIENTE +nome);
    }

    public static Ambiente recuperarAmbientePorNome(String nome){
        Ambiente ambiente = mapaAmbientes.get(PREFIXO_NOME_AMBIENTE +nome);
        if(ambiente == null) {
            ambiente = mapaAmbientes.get(nome);
        }
        return ambiente;
    }

    private static Ambiente criarResidencia(String nomeAmbiente) {
        ElementID elementID = ams.createAgentElementId(nomeAmbiente, true);
        elementID.setAddress(LOCAL_HOST);
        Ambiente ambiente = new Residencia(elementID);
        ams.createDescription(ambiente, elementID, "");
        return ambiente;
    }
}
