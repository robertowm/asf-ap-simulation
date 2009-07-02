/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import objeto.Comodo;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoTrocarComodo extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public synchronized boolean execute(Agent agente, Message msg) {
        Ambiente ambiente = (Ambiente) agente.getEnvironment();
        Comodo comodo = ambiente.getComodoPorNome(msg.getContent().toString());
        Comodo novoComodo = ambiente.pegarOutroComodoAleatoriamente(comodo);

        comodo.adicionaAgente(agente);
        
        Principal tela = JDesktop.getTela(agente);

        tela.apendTexto("\n\"Vou ir para outro comodo. Irei para " + novoComodo + "\"");

        String conversionId = "?" + Thread.currentThread().getName();
        Message chamada = new Message(conversionId, novoComodo.toString(), agente.getAgentName(), agente.getAgentName());
        chamada.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        agente.send(chamada);


        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_TROCAR_COMODO);
        } catch (InterruptedException ex) {
            return false;
        }

        comodo.removeAgente(agente);
        
        return true;
    }
}
