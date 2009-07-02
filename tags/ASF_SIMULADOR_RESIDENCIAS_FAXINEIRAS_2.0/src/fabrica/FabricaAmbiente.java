/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

import ambiente.Ambiente;
import ambiente.CentralAtendimento;
import ambiente.Residencia;
import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import java.util.HashMap;
import java.util.Map;
import sis_multagente.Main;
import util.ConstantesAplicacao;
import visual.JAmbiente;

/**
 *
 * @author heliokann
 */
public class FabricaAmbiente {

    private static Map<String, Ambiente> mapaAmbientes = new HashMap<String, Ambiente>();
    private static AMS ams = AMS.getInstance();

    public static Ambiente getAmbiente(String nome, String tipo) {
        String nomeAmbiente = PREFIXO_NOME_AMBIENTE + nome;

        Ambiente amb = mapaAmbientes.get(nomeAmbiente);

        if (amb != null) {
            return amb;
        }
        Ambiente ambiente = null;
        
         ElementID elementID = ams.createAgentElementId(nomeAmbiente, true);
        elementID.setAddress(LOCAL_HOST);
        
        if (tipo.equals(ConstantesAplicacao.TIPO_AMBIENTE_RESIDENCIA)) {
            ambiente = new Residencia(elementID);
        } else if (tipo.equals(ConstantesAplicacao.TIPO_AMBIENTE_CENTRAL_ATENDIMENTO)) {
            ambiente = new CentralAtendimento(elementID);
        }
        
        ams.createDescription(ambiente, elementID, "");

        adicionarAmbiente(ambiente);

        return ambiente;
    }

    public static void adicionarAmbiente(Ambiente ambiente) {
        mapaAmbientes.put(ambiente.getEnvironmentName(), ambiente);
        JAmbiente p = new JAmbiente(ambiente);
        p.setVisible(true);
        p.setTitle(ambiente.toString());
        Main.desktop.add(p);
    }

    public static boolean existeAmbiente(String nome) {
        return mapaAmbientes.containsKey(PREFIXO_NOME_AMBIENTE + nome);
    }

    public static Ambiente recuperarAmbientePorNome(String nome) {
        Ambiente ambiente = mapaAmbientes.get(PREFIXO_NOME_AMBIENTE + nome);
        if (ambiente == null) {
            ambiente = mapaAmbientes.get(nome);
        }
        return ambiente;
    }

//    private static Ambiente criarResidencia(String nomeAmbiente) {
//        ElementID elementID = ams.createAgentElementId(nomeAmbiente, true);
//        elementID.setAddress(LOCAL_HOST);
//        Ambiente ambiente = new Residencia(elementID);
//        ams.createDescription(ambiente, elementID, "");
//        return ambiente;
//    }
}
