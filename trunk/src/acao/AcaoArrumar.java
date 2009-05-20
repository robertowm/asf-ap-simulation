/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public class AcaoArrumar extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();

        int pontuacaoArrumarDeAcordoComPersonalidade = 1;
        boolean empregada = false;
        for (Object object : agente.getRolesBeingPlayed()) {
            if (object instanceof Empregada) {
                empregada = true;
                break;
            }
        }

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

        Message saida = new Message("?" + Thread.currentThread().getName(), comodo, agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        agente.send(saida);

        return comodo.getNivelArrumacao().equals(Comodo.ARRUMADO);
    }
}
