/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Saida;

/**
 *
 * @author heliokann
 */
public class AcaoSujar extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Ambiente) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        comodo.adicionaAgente(agente);
        
        // suja de acordo com a personalidade

        int pontuacaoSujarDeAcordoComPersonalidade = 2;
        Saida tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou sujar, mas so um pouquinho...\"");
        do {
            try {
                // dormindo o tempo de sujar um ponto
                tela.apendTexto("       Sujando Comodo...");
                Thread.sleep(ConstantesAplicacao.TEMPO_SUJAR_UM_PONTO);
                comodo.suja();
            } catch (InterruptedException ex) {
                Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (--pontuacaoSujarDeAcordoComPersonalidade > 0);
        tela.apendTexto("Saindo do comodo ->"+comodo);
        tela.apendTexto("Situação de Limpeza ->"+comodo.getNivelLimpeza()+"\n");

        Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        agente.send(saida);

        comodo.removeAgente(agente);
        
        return true;
    }
}
