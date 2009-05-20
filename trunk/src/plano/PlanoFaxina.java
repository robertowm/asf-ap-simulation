/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import acao.AcaoAgente;
import acao.AcaoLimpar;
import acao.AcaoArrumar;
import acao.command.ComandoAcao;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Plan;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetivo.TornarResidenciaHabitavel;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class PlanoFaxina extends Plan implements Serializable{
    
    public PlanoFaxina(){
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setGoal(new TornarResidenciaHabitavel());
    }

    @Override
    public void execute(AgentRole role) {
//        System.out.println("===================PlanoFaxina.execute===================");
        int descansa = 500;
        long timeOut = ConstantesAplicacao.QTD_COMODO*ConstantesAplicacao.PONTUACAO_TOTAL_ARRUMADO*600 + 2*descansa;
        List<Message> listaExecutada;
        
        Agent agente = role.getAgentPlayingRole();
        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("<<<--- Iniciando o plano faxina --->>>");
        tela.apendTexto("Tempo Máximo de faxina -> "+timeOut);
        while (timeOut > 0) {
            
            CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>( agente.getInMessages());
            listaExecutada = new ArrayList(mensagens.size());
            
//            Iterator<Message> e =  mensagens.iterator();
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            int aguardandoMensagem = 300;
            
            try {
                    tela.apendTexto("Agente ------>>   AGUARDANDO MENSAGEM");
                    Thread.sleep(aguardandoMensagem);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            for(Message mensagem: mensagens) {
//                Message mensagem = e.next();                
                AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
                long tempoAcao = System.currentTimeMillis();
                tela.apendTexto("   INICIANDO A ACAO --->"+acao.getClass().getSimpleName());
                boolean executou = acao.execute(agente, mensagem);
                timeOut -= ( System.currentTimeMillis() - tempoAcao );
                
                if(executou){
                    listaExecutada.add(mensagem);
                }
                
                try {
                    tela.apendTexto("Agente ------>>   DESCANSANDO");
                    Thread.sleep(descansa);                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                timeOut -= descansa;
                // se escotou o tempo do agente fazer faxina ele vai embora sem pegar uma próxima ação
                if( timeOut <=0 ) {
                    tela.apendTexto("TEMPO ESGOTADO DE FAZER FAXINA --> abandonando plano");
                    return;
                }
            }
            
            synchronized(agente) {
                agente.getInMessages().removeAll(listaExecutada);
            }
//            CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>( agente.getInMessages());
        }
        
        goal.setAchieved(true);
        tela.apendTexto("Plano FAXINA cumprido com sucesso");

    }
    
    @Override
    public boolean equals(Object obj) {
        return  (obj instanceof PlanoFaxina);
    }
}
