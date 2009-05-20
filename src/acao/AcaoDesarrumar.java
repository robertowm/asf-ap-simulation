/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Residencia;
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
public class AcaoDesarrumar extends AcaoAgente implements Serializable{

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();
        ((Residencia) agente.getEnvironment()).atualizarComodo(agente, comodo);

        // suja de acordo com a personalidade

        int pontuacaoDesarrumarDeAcordoComPersonalidade = 2;

        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\"Vou desarrumar, mas so um pouquinho...\"");
        do {
            try {
                // dormindo o tempo de sujar um ponto
                tela.apendTexto("\"Desarrumando Comodo...\"");
                Thread.sleep(ConstantesAplicacao.TEMPO_DESARRUMAR_UM_PONTO);
                comodo.desarruma();
            } catch (InterruptedException ex) {
                Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (--pontuacaoDesarrumarDeAcordoComPersonalidade > 0);

        Message saida = new Message("?" + Thread.currentThread().getName(), comodo, agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        agente.send(saida);

        return true;
    }
}
