/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import fabrica.FabricaAmbiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoVisitarResidencia extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        String informacoes = msg.getContent().toString();

        Ambiente ambiente = FabricaAmbiente.recuperarAmbientePorNome(informacoes.split("#")[1]);
        Comodo comodo = (Comodo) ambiente.getComodoPorNome(informacoes.split("#")[0]);
        
        ((Ambiente)agente.getEnvironment()).cancelAgentRegister(agente);
        
        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou visitar " + ambiente.toString() + ".\"");
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_VISITAR_RESIDENCIA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
        }

        agente.setEnvironment(ambiente);
        ambiente.registerAgents(agente);
        
        agente.getInMessages().clear();
        
        Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        agente.send(saida);

        return true;
    }
}
