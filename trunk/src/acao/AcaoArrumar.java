/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import ambiente.Ambiente;
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
public class AcaoArrumar extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Ambiente) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        comodo.adicionaAgente(agente);

        int pontuacaoArrumarDeAcordoComPersonalidade = 1;
        boolean empregada = false;
        for (Object object : agente.getRolesBeingPlayed()) {
            if (object instanceof Empregada) {
                empregada = true;
                break;
            }
        }
        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou arrumar aqui!\"");
        do { // se for empregada repete a limpeza até ficar limpo o comodo

            do {
                try {
                    tela.apendTexto("       Arrumando comodo...");
                    Thread.sleep(ConstantesAplicacao.TEMPO_ARRUMAR_UM_PONTO);
                    comodo.arruma();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (--pontuacaoArrumarDeAcordoComPersonalidade > 0);

            // caso seja empregada eh necessario verificar se ainda falta arrumar
            pontuacaoArrumarDeAcordoComPersonalidade = comodo.getPontosFaltaArrumado();

        } while (empregada && !comodo.getNivelArrumacao().equals(Comodo.ARRUMADO));

        tela.apendTexto("Saindo do comodo ->" + comodo);
        tela.apendTexto("Situação de Arrumacao ->" + comodo.getNivelArrumacao() + "\n");

        Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());

        if (empregada) {
            if (comodo.getNivelLimpeza().equals(Comodo.LIMPO)) {
                saida.setPerformative(ConstantesAplicacao.ACAO_TROCAR_COMODO);
            } else {
                saida.setPerformative(ConstantesAplicacao.ACAO_LIMPAR);
            }
        } else {
            saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        }
        
        agente.send(saida);

        comodo.removeAgente(agente);

        return comodo.getNivelArrumacao().equals(Comodo.ARRUMADO);
    }
}
