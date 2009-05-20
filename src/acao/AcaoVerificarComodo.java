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
import objeto.Comodo;
import agente.papel.Empregada;
import agente.papel.Morador;
import framework.mentalState.belief.Belief;
import framework.organization.MainOrganization;
import sis_multagente.Main;
import sun.management.resources.agent;
import util.GeradorRandomico;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public class AcaoVerificarComodo extends AcaoAgente {
    private Collection organizacao;

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = (Comodo) msg.getContent();

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        boolean empregada = false;
        boolean morador = false;
        
        String comversionId = "?" + Thread.currentThread().getName();
        
        Message saida = new Message(comversionId, comodo, agente.getAgentName(), agente.getAgentName());
        
        for (AgentRole agentRole : papeis) {
            empregada = (agentRole instanceof Empregada);
            morador = (agentRole instanceof Morador);
            if (empregada) {
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
            }
            if (morador) {
                
                final List<Comodo> comodos =  (List<Comodo>) agente.getEnvironment().getObjects();
                int pontoLimpezaArrumacao = 0;
                boolean  chamarEmpregada = false;
                for (Comodo comodoAnalise : comodos) {
                    
                    if(comodoAnalise.getNivelArrumacao().equals(Comodo.INABITAVEL_DESARRUMADO)){
                        chamarEmpregada = true;
                        break;
                    }
                    
                    if(comodoAnalise.getNivelLimpeza().equals(Comodo.INABITAVEL_SUJO)){
                        chamarEmpregada = true;
                        break;
                    }
                    
                    pontoLimpezaArrumacao += comodoAnalise.getPontuacaoLimpeza() + comodoAnalise.getPontuacaoArrumacao();
                    
                }
                
                if(pontoLimpezaArrumacao <= ConstantesAplicacao.MEDIA_PONTOS_COMODOS){
                    chamarEmpregada = true;
                }
                
                if(chamarEmpregada){
                    saida = new Message(comversionId, comodo, agente.getAgentName(), Main.idEmpregada);
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
                    saida.setPerformative(ConstantesAplicacao.ACAO_CHAMAR_EMPREGADA);
                    agente.send(saida);
                }

            }
            
        }
        return true;
    }
}
