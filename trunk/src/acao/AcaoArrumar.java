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

        int pontuacaoArrumarDeAcordoComPersonalidade = 3;
        boolean empregada = true;

        do { // se for empregada repete a limpeza até ficar limpo o comodo

            do {
                try {
                    Thread.sleep(ConstantesAplicacao.TEMPO_ARRUMAR_UM_PONTO);
                    comodo.arruma();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (--pontuacaoArrumarDeAcordoComPersonalidade > 0);

            // caso seja empregada eh necessario verificar se ainda falta arrumar
            pontuacaoArrumarDeAcordoComPersonalidade = comodo.getPontosFaltaArrumado();

        } while (empregada && !comodo.getNivelArrumacao().equals(Comodo.ARRUMADO));

        return comodo.getNivelArrumacao().equals(Comodo.ARRUMADO);
    }
}
