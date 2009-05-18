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
        String nivelLimpeza = comodo.getNivelLimpeza();
        // enquanto o nivel de limpeza nao esta limpo
        while (!nivelLimpeza.equals(Comodo.LIMPO)) {
            int sleep = comodo.getTempoLimpeza();
            int pontuacaoLimpeza = comodo.getTempoLimpeza();
            do {
                try {                    
                    sleep = -ConstantesAplicacao.TEMPO_LIMPAR_UM_PONTO;
                    Thread.sleep(sleep);
                    // cada ponto de limpeza demora 200 ms para executar
                    comodo.setPontuacaoLimpeza(++pontuacaoLimpeza);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (sleep > 1);
            nivelLimpeza = comodo.getNivelLimpeza();
        }
        return comodo.getNivelLimpeza().equals(Comodo.LIMPO);
    }
}
