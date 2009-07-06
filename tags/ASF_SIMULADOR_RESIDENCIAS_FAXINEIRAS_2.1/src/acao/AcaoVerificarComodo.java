/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.UsuarioAgente;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.util.List;
import objeto.Comodo;
import agente.papel.Empregada;
import agente.papel.Morador;
import agente.papel.Secretaria;
import ambiente.Ambiente;
import ambiente.CentralAtendimento;
import framework.mentalState.belief.Belief;
import java.io.Serializable;
import util.GeradorRandomico;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Saida;

/**
 *
 * @author heliokann
 */
public class AcaoVerificarComodo extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Ambiente) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());

//        comodo.adicionaAgente(agente);

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        boolean empregada = false;
        boolean morador = false;
        boolean secretaria = false;

        String conversionId = "?" + Thread.currentThread().getName();

        Saida tela = JDesktop.getTela(agente);
        Message saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());

        for (AgentRole agentRole : papeis) {
            empregada = (agentRole instanceof Empregada);
            morador = (agentRole instanceof Morador);
            secretaria = (agentRole instanceof Secretaria);

            try {
                Thread.sleep(ConstantesAplicacao.TEMPO_VERIFICAR_COMODO);
            } catch (InterruptedException ex) {
            }

            if (empregada) {
                exibirStatusComodo(tela, comodo);

//                Ambiente ambiente = (Ambiente) agente.getEnvironment();
//                if (ambiente instanceof CentralAtendimento) {
//                    saida.setPerformative(ConstantesAplicacao.ACAO_PEGAR_FAXINA);
//                    agente.send(saida);
//                } else {
                Belief crenca = GeradorRandomico.getBelief(agente.getBeliefs());
                if (crenca.getName().equals("limpa")) {
                    // acao limpa
                    saida.setPerformative(ConstantesAplicacao.ACAO_LIMPAR);
                    agente.send(saida);
                } else if (crenca.getName().equals("arruma")) {
                    // acao arruma
                    saida.setPerformative(ConstantesAplicacao.ACAO_ARRUMAR);
                    agente.send(saida);
                }
//                }
            }
            if (morador || secretaria) {

                exibirStatusComodo(tela, comodo);
                final List<Comodo> comodos = ((Ambiente) agente.getEnvironment()).getListaComodos();
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
                    saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
                    saida.setPerformative(ConstantesAplicacao.ACAO_CHAMAR_EMPREGADA);
                    agente.send(saida);
                }

                if (GeradorRandomico.geraPercentual() < 30) {
                    saida.setPerformative(ConstantesAplicacao.ACAO_TROCAR_COMODO);
                    agente.send(saida);
                } else {
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
                        saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
                        saida.setPerformative(ConstantesAplicacao.ACAO_CHAMAR_EMPREGADA);
                        agente.send(saida);
                    } else if (crenca.getName().equals("fazNada")) {
                        // acao fazNada
                        saida = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
                        saida.setPerformative(ConstantesAplicacao.ACAO_FAZ_NADA);
                        agente.send(saida);
                    }
                }
            }
            break;

        }
        comodo.removeAgente(agente);

        return true;
    }

    private void exibirStatusComodo(Saida tela, Comodo comodo) {
        tela.apendTexto("Verificando comodo: " + comodo.getNome());
        tela.apendTexto(" - Nivel de limpeza   = " + comodo.getNivelLimpeza());
        tela.apendTexto(" - Nivel de arrumacao = " + comodo.getNivelArrumacao());
    }
}
