/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
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
public class AcaoIrParaACentralAtendimento extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public synchronized boolean execute(Agent agente, Message msg) {
        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou para a Central de Atendimento.\"");
        Ambiente ambiente = (Ambiente)agente.getEnvironment();
        ambiente.cancelAgentRegister(agente);
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_IR_PARA_A_CENTRAL);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
        }

        agente.setEnvironment(Main.ambienteCentral);
        Main.ambienteCentral.registerAgents(agente);
        
        Main.ambienteCentral.avisarRetornoServico(ambiente);
        
        Message saida = new Message("?" + Thread.currentThread().getName(), Main.ambienteCentral.pegarComodoAleatoriamente().toString(), agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_PEGAR_FAXINA);
        agente.send(saida);

        return true;
    }
}
