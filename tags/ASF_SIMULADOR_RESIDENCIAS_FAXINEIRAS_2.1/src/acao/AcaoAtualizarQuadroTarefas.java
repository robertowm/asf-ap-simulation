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
import objeto.Comodo;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public class AcaoAtualizarQuadroTarefas extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        String informacoes = (String) msg.getContent();

        Ambiente ambiente = FabricaAmbiente.recuperarAmbientePorNome(informacoes.split("#")[1]);
        Comodo comodo = (Comodo) ambiente.getComodoPorNome(informacoes.split("#")[0]);
        Comodo escritorio = ((CentralAtendimento)agente.getEnvironment()).getComodoPorNome("Escritorio");
        
        escritorio.adicionaAgente(agente);
        
        CentralAtendimento central = (CentralAtendimento) agente.getEnvironment();

        central.adicionarTarefa(comodo);
        
//        String conversionId = "?" + Thread.currentThread().getName();
//        Message saida = new Message(conversionId, escritorio.toString(), agente.getAgentName(), agente.getAgentName());
//        saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
//        agente.send(saida);
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_ATUALIZAR_QUADRO_TAREFAS);
        } catch (InterruptedException ex) {
        }
        
        escritorio.removeAgente(agente);
        
        return true;
    }
}
