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

        CentralAtendimento central = (CentralAtendimento) agente.getEnvironment();

        central.adicionarTarefa(comodo);
        
        return true;
    }
}
