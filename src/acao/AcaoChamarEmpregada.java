/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import agente.papel.Morador;
import ambiente.Residencia;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.io.Serializable;
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
public class AcaoChamarEmpregada extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Residencia) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();

        String conversionId = "?" + Thread.currentThread().getName();

        // tem que enviar mensagem para a empregada

        boolean morador = false;
        boolean empregada = false;
        for (AgentRole agentRole : papeis) {
            if (agentRole instanceof Morador) {
                morador = true;
            }
            if (agentRole instanceof Empregada) {
                empregada = true;
            }
        }
        Principal tela = JDesktop.getTela(agente);
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if (morador) {
            Message chamada = new Message(conversionId, comodo.toString(), agente.getAgentName(), empregada ? agente.getAgentName() : Main.idEmpregada);
            chamada.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
            agente.send(chamada);

            if (!empregada) {
                Message saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
                saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
                agente.send(saida);
            }
        }

        tela.apendTexto("\"Nossa! Minha casa esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido!") + "\"");
        tela.apendTexto("\"Nossa! Este comodo esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido para limpar a/o " + comodo + "!") + "\"");
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_ESPERAR_EMPREGADA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
