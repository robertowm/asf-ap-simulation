/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import agente.papel.Morador;
import agente.papel.Secretaria;
import ambiente.Residencia;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import sis_multagente.Main;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoConvocarEmpregada extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Residencia) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        List<Agent> empregadas = new ArrayList<Agent>();
        List<Agent> agentes = (List<Agent>) agente.getEnvironment().getAgents();
        
        for (Agent ag : agentes) {
            List<AgentRole> papeis = (List<AgentRole>) ag.getRolesBeingPlayed();
            for (AgentRole agentRole : papeis) {
                if (agentRole instanceof Empregada) {
                    empregadas.add(ag);
                    break;
                }
            }

        }
        
        String conversionId = "?" + Thread.currentThread().getName();
        Principal tela = JDesktop.getTela(agente);

        tela.apendTexto("Alertando Diaristas ...");
        for (Agent agEmpregada : empregadas) {
            tela.apendTexto("       "+agEmpregada.toString());
            Message chamada = new Message(conversionId, comodo.toString(), agente.getAgentName(), agEmpregada.getAgentName());
            chamada.setPerformative(ConstantesAplicacao.ACAO_CONFIRMAR_SERVICO_COM_CENTRAL);
            agente.send(chamada);
        }

        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
