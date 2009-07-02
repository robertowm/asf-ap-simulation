/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import ambiente.CentralAtendimento;
import fabrica.FabricaAmbiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import sis_multagente.Main;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoPegarFaxina extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public synchronized boolean execute(Agent agente, Message msg) {
        if (!(agente.getEnvironment() instanceof CentralAtendimento)) {
            Message saida = new Message("?" + Thread.currentThread().getName(), null, agente.getAgentName(), agente.getAgentName());
            saida.setPerformative(ConstantesAplicacao.ACAO_IR_PARA_A_CENTRAL);
            agente.send(saida);
            return true;
        }
        CentralAtendimento central = (CentralAtendimento) agente.getEnvironment();
        Comodo comodo = central.getComodoPorNome(msg.getContent().toString());
        Comodo escritorio = central.getComodoPorNome("Escritorio");

        escritorio.adicionaAgente(agente);

        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou ver se tem alguma faxina para fazer.\"");
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_PEGAR_FAXINA);
        } catch (InterruptedException ex) {
            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] tarefa = central.pegarProximaTarefa(agente);
        if (tarefa == null) {
            tela.apendTexto("\n\"Não tem faxina para fazer.\"");

            Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
            saida.setPerformative(ConstantesAplicacao.ACAO_FAZ_NADA);
            agente.send(saida);

//            saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
//            saida.setPerformative(ConstantesAplicacao.ACAO_PEGAR_FAXINA);
//            agente.send(saida);
        } else {
            agente.getInMessages().clear();
            Message saida = new Message("?" + Thread.currentThread().getName(), tarefa[1].toString() + "#" + tarefa[0].toString(), agente.getAgentName(), agente.getAgentName());
            saida.setPerformative(ConstantesAplicacao.ACAO_VISITAR_RESIDENCIA);
            agente.send(saida);
        }
        escritorio.removeAgente(agente);

        return true;
    }
}
