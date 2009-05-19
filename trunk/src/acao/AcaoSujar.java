/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import framework.agent.Agent;
import framework.mentalState.Message;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public class AcaoSujar extends AcaoAgente {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();

        // suja de acordo com a personalidade

        int pontuacaoSujarDeAcordoComPersonalidade = 3;

        do {
            try {
                // dormindo o tempo de sujar um ponto
                Thread.sleep(ConstantesAplicacao.TEMPO_SUJAR_UM_PONTO);
                comodo.suja();
            } catch (InterruptedException ex) {
                Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (--pontuacaoSujarDeAcordoComPersonalidade > 0);


        return true;
    }
}
