/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.util.List;
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

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        int pontuacaoLimparDeAcordoComPersonalidade = 3;
        boolean empregada = false;
        for (AgentRole agentRole : papeis) {
            empregada = (agentRole instanceof Empregada);
            if(empregada) break;
        }

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
