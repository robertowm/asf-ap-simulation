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
        
        if(secretaria){
            Message chamada = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
            chamada.setPerformative(ConstantesAplicacao.ACAO_CONVOCAR_EMPREGADA);
            agente.send(chamada);
            return true;
        }
        

        comodo.adicionaAgente(agente);
        



        Principal tela = JDesktop.getTela(agente);
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
//        if (morador) {
            /**
             * mensagem chamando a empregada
             */
            Message chamada = new Message(conversionId, comodo.toString(), agente.getAgentName(), empregada ? agente.getAgentName() : Main.idEmpregada);
            chamada.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
            agente.send(chamada);

//            if (!empregada) {
            /**
             * mensagem para si mesmo para verificar um quarto qualquer
             */
                Message saida = new Message(conversionId, ((Residencia) agente.getEnvironment()).pegarOutroComodoAleatoriamente(comodo).toString() , agente.getAgentName(), agente.getAgentName());
                saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
                agente.send(saida);
//            }
//        }

        tela.apendTexto("\"Nossa! Minha casa esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido!") + "\"");
        tela.apendTexto("\"Nossa! Este comodo esta uma bagunca! " + (empregada ? "Preciso comecar a limpar logo!" : "Preciso chamar a empregada rapido para limpar a/o " + comodo + "!") + "\"");
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_ESPERAR_EMPREGADA);
        } catch (InterruptedException ex) {            
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        comodo.removeAgente(agente);
        
        return true;
    }
}
