/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plano;

import acao.AcaoAgente;
import acao.command.ComandoAcao;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Plan;
import java.util.Collection;
import framework.mentalState.Message;

/**
 *
 * @author heliokann
 */
public class PlanoFaxina extends Plan{
    
    @Override
    public void execute(AgentRole role) {
        
        Agent agente = role.getAgentPlayingRole();
        Collection<Message> mensagens = agente.getInMessages();
        for (Message mensagem : mensagens) {
            AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
            boolean executou = acao.execute(agente, mensagem);
            
        }
        
        
    }
}
