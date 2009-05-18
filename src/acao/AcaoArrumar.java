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
public class AcaoArrumar extends AcaoAgente {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();
        String nivelArrumacao = comodo.getNivelArrumacao();
        // enquanto o nivel de arrumacao nao esta arrumado
        while (!nivelArrumacao.equals(Comodo.ARRUMADO)) {
            int sleep = comodo.getTempoArrumacao();
            int pontuacaoArrumacao = comodo.getPontuacaoArrumacao();
            do {
                try {
                    sleep = -ConstantesAplicacao.TEMPO_ARRUMAR_UM_PONTO;
                    Thread.sleep(sleep);
                    // cada ponto de arrumacao demora 300 ms para executar
                    comodo.setPontuacaoArrumacao(++pontuacaoArrumacao);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (sleep > 1);
            nivelArrumacao = comodo.getNivelArrumacao();
        }
        return comodo.getNivelArrumacao().equals(Comodo.ARRUMADO);
    }
}
