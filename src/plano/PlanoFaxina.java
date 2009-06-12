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
import static util.ConstantesAplicacao.*;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class PlanoFaxina extends Plan implements Serializable {

    public PlanoFaxina() {
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setGoal(new TornarResidenciaHabitavel());
    }
    boolean aguardandoChamada = true;

    @Override
    public void execute(AgentRole role) {
//        System.out.println("===================PlanoFaxina.execute===================");
        int descansa = 500;
        long timeOut = QTD_COMODO * PONTUACAO_TOTAL_ARRUMADO * 500 * VELOCIDADE + 2 * descansa;
        List<Message> listaExecutada;

        Agent agente = role.getAgentPlayingRole();
        Principal tela = JDesktop.getTela(agente);
        tela.apendTexto("\n<<<--- Iniciando o plano Faxina --->>>");
        tela.apendTexto("Tempo Máximo de faxina -> " + timeOut);
        while (timeOut > 0) {

            CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>(agente.getInMessages());
            listaExecutada = new ArrayList(mensagens.size());

            int aguardandoMensagem = 300;

            try {
                if (aguardandoChamada) {
                    tela.apendTexto("Agente ------>>   AGUARDANDO CHAMADO");
                    aguardandoChamada = false;
                }
                Thread.sleep(aguardandoMensagem);

            } catch (InterruptedException ex) {
                Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Message mensagem : mensagens) {
                AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
                long tempoAcao = System.currentTimeMillis();
                boolean executou = acao.execute(agente, mensagem);
                timeOut -= (System.currentTimeMillis() - tempoAcao);

                if (executou) {
                    listaExecutada.add(mensagem);
                }

                try {
                    tela.apendTexto("\"Vou descancar um pouco...\"");
                    Thread.sleep(descansa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
                }

                timeOut -= descansa;
                // se escotou o tempo do agente fazer faxina ele vai embora sem pegar uma próxima ação
                if (timeOut <= 0) {
                    tela.apendTexto("\"Acabou meu tempo.\"");
                    break;
                }
            }

            synchronized (agente) {
                agente.getInMessages().removeAll(listaExecutada);
            }
        }

        goal.setAchieved(true);
        tela.apendTexto("Fiz tudo que deu, dentro do tempo! :)\"");
        aguardandoChamada = true;
        synchronized (agente) {
            agente.getInMessages().clear();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlanoFaxina);
    }
}
