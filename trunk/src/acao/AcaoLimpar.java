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
public class AcaoLimpar extends AcaoAgente {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();

        int pontuacaoLimparDeAcordoComPersonalidade = 3;
        boolean empregada = true;

        do { // se empregada, limpa enquanto o nivel de limpeza nao esta limpo

            do {
                try {
                    Thread.sleep(ConstantesAplicacao.TEMPO_LIMPAR_UM_PONTO);
                    comodo.limpa();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (--pontuacaoLimparDeAcordoComPersonalidade > 0);

            pontuacaoLimparDeAcordoComPersonalidade = comodo.getPontosFaltaLimpo();

        } while (empregada && !comodo.getNivelLimpeza().equals(Comodo.LIMPO));

        return comodo.getNivelLimpeza().equals(Comodo.LIMPO);
    }
}
