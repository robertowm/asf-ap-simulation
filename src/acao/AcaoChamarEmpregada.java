/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import agente.papel.Morador;
import agente.papel.Secretaria;
import ambiente.Ambiente;
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
        Comodo comodo = ((Ambiente) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        
        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        Principal tela = JDesktop.getTela(agente);
            tela.apendTexto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n\n\n chamando empregada para o comodo [" +
                    comodo+
                    "] [" +
                    comodo.getAmbiente()+
                    "]AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n\n\n");

        boolean morador = false;
        boolean empregada = false;
        boolean secretaria = false;
        for (AgentRole agentRole : papeis) {
            if (agentRole instanceof Morador) {
                morador = true;
            }
            if (agentRole instanceof Empregada) {
                empregada = true;
            }
            if (agentRole instanceof Secretaria) {
                secretaria = true;
            }
        }

        String conversionId = "?" + Thread.currentThread().getName();

        if (secretaria) {
            Message chamada = new Message(conversionId, comodo.toString() + "#" + comodo.getAmbiente().getEnvironmentName(), agente.getAgentName(), agente.getAgentName());
            chamada.setPerformative(ConstantesAplicacao.ACAO_ATUALIZAR_QUADRO_TAREFAS);
            agente.send(chamada);
            tela.apendTexto("#################################\n\n\n chamando empregada para o comodo [" +
                    comodo+
                    "] [" +
                    comodo.getAmbiente()+
                    "]#################################\n\n\n");

        } else if (morador) {
        
            comodo.adicionaAgente(agente);
            
            Message chamada = new Message(conversionId, comodo.toString() + "#" + comodo.getAmbiente().getEnvironmentName(), agente.getAgentName(), Main.ambienteCentral.getSecretaria().getAgentName());
            chamada.setPerformative(ConstantesAplicacao.ACAO_ATENDER_REQUISICAO);
            agente.send(chamada);

            Message saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
            saida.setPerformative(ConstantesAplicacao.ACAO_TROCAR_COMODO);
            agente.send(chamada);
        }

        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

//        tela.apendTexto("\"Nossa! Minha casa esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido!") + "\"");
        tela.apendTexto("\"Nossa! Este comodo esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido para limpar a/o " + comodo + "!") + "\"");

//        try {
//            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }

        comodo.removeAgente(agente);
        
        return true;
    }
}
