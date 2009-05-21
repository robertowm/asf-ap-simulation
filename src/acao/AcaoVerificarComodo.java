/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import agente.papel.Empregada;
import agente.papel.Morador;
import ambiente.Residencia;
import framework.mentalState.belief.Belief;
import java.io.Serializable;
import sis_multagente.Main;
import util.GeradorRandomico;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoVerificarComodo extends AcaoAgente implements Serializable {

    private Collection organizacao;

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Residencia) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        boolean empregada = false;
        boolean morador = false;

        String comversionId = "?" + Thread.currentThread().getName();

        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("Verificando comodo: " + comodo.getNome());
        tela.apendTexto(" - Nivel de limpeza   = " + comodo.getNivelLimpeza());
        tela.apendTexto(" - Nivel de arrumacao = " + comodo.getNivelArrumacao());

        Message saida = new Message(comversionId, comodo, agente.getAgentName(), agente.getAgentName());

        for (AgentRole agentRole : papeis) {
            empregada = (agentRole instanceof Empregada);
            morador = (agentRole instanceof Morador);
            if (empregada) {
                Belief crenca = GeradorRandomico.getBelief(agente.getBeliefs());
                try {
                    Thread.sleep(ConstantesAplicacao.TEMPO_VERIFICAR_COMODO);
                } catch (InterruptedException ex) {
                }
                if (crenca.getName().equals("limpa")) {
                    // acao limpa
                    saida.setPerformative(ConstantesAplicacao.ACAO_LIMPAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("arruma")) {
                    // acao arruma
                    saida.setPerformative(ConstantesAplicacao.ACAO_ARRUMAR);
                    agente.send(saida);
                }
            }
            if (morador) {

//                final List<Comodo> comodos =  (List<Comodo>) agente.getEnvironment().getObjects();
                final List<Comodo> comodos = ((Residencia) agente.getEnvironment()).getListaComodos();
                int pontoLimpezaArrumacao = 0;
                boolean chamarEmpregada = false;

                for (Comodo comodoAnalise : comodos) {
                    if (comodoAnalise.getNivelArrumacao().equals(Comodo.INABITAVEL_DESARRUMADO)) {
                        chamarEmpregada = true;
                        break;
                    }

                    if (comodoAnalise.getNivelLimpeza().equals(Comodo.INABITAVEL_SUJO)) {
                        chamarEmpregada = true;
                        break;
                    }
                    pontoLimpezaArrumacao += comodoAnalise.getPontuacaoLimpeza() + comodoAnalise.getPontuacaoArrumacao();
                }

                if (pontoLimpezaArrumacao <= ConstantesAplicacao.MEDIA_PONTOS_COMODOS) {
                    chamarEmpregada = true;
                }

                if (chamarEmpregada) {
                    try {
                        Thread.sleep(ConstantesAplicacao.TEMPO_VERIFICAR_COMODO);
                    } catch (InterruptedException ex) {
                    }
                    saida = new Message(comversionId, comodo, agente.getAgentName(), agente.getAgentName());
                    saida.setPerformative(ConstantesAplicacao.ACAO_CHAMAR_EMPREGADA);
                    agente.send(saida);
                    break;
                }

                Belief crenca = GeradorRandomico.getBelief(agente.getBeliefs());
                if (crenca.getName().equals("dessarruma")) {
                    // acao dessarruma
                    saida.setPerformative(ConstantesAplicacao.ACAO_DESARRUMAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("arruma")) {
                    // acao arruma
                    saida.setPerformative(ConstantesAplicacao.ACAO_ARRUMAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("limpa")) {
                    // acao limpa
                    saida.setPerformative(ConstantesAplicacao.ACAO_LIMPAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("suja")) {
                    // acao suja
                    saida.setPerformative(ConstantesAplicacao.ACAO_SUJAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("chamaEmpregada")) {
                    // acao chamaEmpregada
                    saida = new Message(comversionId, comodo, agente.getAgentName(), agente.getAgentName());
                    saida.setPerformative(ConstantesAplicacao.ACAO_CHAMAR_EMPREGADA);
                    agente.send(saida);
                }

            }
            break;

        }
        return true;
    }
}
