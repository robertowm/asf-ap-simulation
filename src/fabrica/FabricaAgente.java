/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fabrica;

import agente.UsuarioAgente;
import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.organization.MainOrganization;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class FabricaAgente {

    private static Map<String, Agent> mapaAgentes = new HashMap<String, Agent>();

    private static AMS ams = AMS.getInstance();

    public static Agent getAgente(String nome, AgentRole regraAgente, MTS_Environment ambiente, MainOrganization organizacao) {
        String nomeAgente = PREFIXO_NOME_AGENTE + nome;

        Agent agente = mapaAgentes.get(nomeAgente);

        if(agente != null) {
            return agente;
        }
        
        ElementID elementID = ams.createAgentElementId(nomeAgente, true);

        ElementID elementIdRegra = new ElementID("Viviane::Buyer", true);
        elementIdRegra.setAddress(LOCAL_HOST);
        regraAgente.setRoleName(elementIdRegra);
        regraAgente.setAgent(agente);

        agente = new UsuarioAgente(ambiente, organizacao, regraAgente, elementID);
        agente.createAgentRoleDescription(regraAgente, elementIdRegra, "");

        ams.createDescription(agente, elementID, "");

//        Thread threadAgente = new Thread(agente, agente.getAgentName().getName());

        /////////////////////
        //Beliefs do agente - COLOCAR
        ///////////////////

        mapaAgentes.put(nomeAgente, agente);

        return agente;
    }
}
