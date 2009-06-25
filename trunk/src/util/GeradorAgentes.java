/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import agente.papel.Empregada;
import ambiente.Ambiente;
import fabrica.FabricaAgente;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.organization.MainOrganization;
import framework.mentalState.Message;
import static util.ConstantesAplicacao.*;

/**
 *
 * @author robertowm
 */
public class GeradorAgentes {

    public static Agent gerarAgente(String nomeAgente, AgentRole papelAgente, Ambiente ambiente, MainOrganization organizacao) {

        Agent agente = FabricaAgente.getAgente(nomeAgente, papelAgente, ambiente, organizacao);

//        Principal tela = JDesktop.getTela(agente);

        GerenciadorFluxos.registrarFluxo(agente.getAgentName().getName(), agente);

        ambiente.registerAgents(agente);

        enviarMensagemInicio(agente, ambiente);

        return agente;
    }

    private static void enviarMensagemInicio(Agent agente, Ambiente ambiente) {
        Message msgm = new Message("?" + agente.getAgentName().getName(), ambiente.pegarComodoPorAgente(agente).toString(), agente.getAgentName(), agente.getAgentName());
        boolean empregada = false;
        for (Object object : agente.getRolesBeingPlayed()) {
            if(object instanceof Empregada) {
                empregada = true;
                break;
            }
        }
        if(empregada) {
            msgm.setPerformative(ACAO_PEGAR_FAXINA);
        } else {
            msgm.setPerformative(ACAO_VERIFICAR_COMODO);
        }
        agente.send(msgm);
    }
}