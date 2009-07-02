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
import objeto.Comodo;
import java.io.Serializable;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoAtenderRequisicao extends AcaoAgente implements Serializable {

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
        
        String conversionId = "?" + Thread.currentThread().getName();

        Principal tela = JDesktop.getTela(agente);
        agente.getInMessages().clear();
        Message saida = new Message(conversionId, comodo.toString() + "#" + ambiente.getEnvironmentName(), agente.getAgentName(), agente.getAgentName());
        saida.setPerformative(ConstantesAplicacao.ACAO_ATUALIZAR_QUADRO_TAREFAS);
        try {
            Thread.sleep(ConstantesAplicacao.TEMPO_ATENDER_REQUISICAO);
        } catch (InterruptedException ex) {
        }
        agente.send(saida);

        tela.apendTexto("\"Irei atualizar o quadro de tarefas com mais uma requisição: Limpar a casa de " + agente.getAgentName().getName() + "\"");

        escritorio.removeAgente(agente);
        
        return true;
    }

//    private void exibirStatusComodo(Principal tela, Comodo comodo) {
//        tela.apendTexto("Verificando comodo: " + comodo.getNome());
//        tela.apendTexto(" - Nivel de limpeza   = " + comodo.getNivelLimpeza());
//        tela.apendTexto(" - Nivel de arrumacao = " + comodo.getNivelArrumacao());
//    }
}
