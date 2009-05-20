/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import acao.AcaoAgente;
import acao.AcaoLimpar;
import acao.command.ComandoAcao;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Plan;
import java.util.Collection;
import framework.mentalState.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public class PlanoFaxina extends Plan {

    @Override
    public void execute(AgentRole role) {
        int descansa = 500;
        long timeOut = ConstantesAplicacao.QTD_COMODO*ConstantesAplicacao.PONTUACAO_TOTAL_ARRUMADO*2 + 2*descansa;
        List<Message> listaExecutada;
        
        Agent agente = role.getAgentPlayingRole();
        System.out.println("Iniciando o plano faxina --->>> "+agente);
        
        while (timeOut > 0) {
            
            Collection<Message> mensagens = agente.getInMessages();
            listaExecutada = new ArrayList(mensagens.size());
            
            for (Message mensagem : mensagens) {
                AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
                long tempoAcao = System.currentTimeMillis();
                boolean executou = acao.execute(agente, mensagem);
                timeOut -= ( System.currentTimeMillis() - tempoAcao );
                
                if(executou){
                    listaExecutada.add(mensagem);
                }
                
                try {
                    Thread.sleep(descansa);                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                timeOut -= descansa;
                // se escotou o tempo do agente fazer faxina ele vai embora sem pegar uma próxima ação
                if( timeOut <=0 ) return;
            }
            
            
            mensagens.removeAll(listaExecutada);
        }
        
        goal.setAchieved(true);

    }
}
