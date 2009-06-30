/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import acao.AcaoAgente;
import acao.AcaoLimpar;
import acao.AcaoArrumar;
import acao.AcaoIrParaACentralAtendimento;
import acao.AcaoVisitarResidencia;
import acao.command.ComandoAcao;
import ambiente.Ambiente;
import fabrica.FabricaAmbiente;
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
import objeto.Comodo;
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
        long tempoFaxina = 20 * 1000;
        boolean fimDoExpediente = false;
        boolean limpando = false;
        List<Message> listaExecutada;

        Agent agente = role.getAgentPlayingRole();
        Principal tela = JDesktop.getTela(agente);

        while (!fimDoExpediente) {
            CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>(agente.getInMessages());

            if (mensagens.isEmpty()) {
                Ambiente ambiente = (Ambiente) agente.getEnvironment();
                Comodo comodo = ambiente.getComodoPorNome("Escritorio");
                if (comodo != null) {
                    Message msg = new Message("?" + agente.getAgentName().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
                    msg.setPerformative(ACAO_PEGAR_FAXINA);
                    agente.send(msg);
                }
                try {
                    tela.apendTexto("\"Vou descancar um pouco...\"");
                    Thread.sleep(descansa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                listaExecutada = new ArrayList(mensagens.size());
                for (Message mensagem : mensagens) {
                    AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
                    long tempoAcao = System.currentTimeMillis();

                    boolean executou = acao.execute(agente, mensagem);

                    if (limpando) {
                        tempoFaxina -= (System.currentTimeMillis() - tempoAcao);
                    }
                    if (acao instanceof AcaoVisitarResidencia) {
                        limpando = true;
                    }
                    if (executou) {
                        listaExecutada.add(mensagem);
                    }

                    try {
                        tela.apendTexto("\"Vou descancar um pouco...\"");
                        Thread.sleep(descansa);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PlanoFaxina.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                tempoFaxina -= descansa;
                    // se escotou o tempo do agente fazer faxina ele vai embora sem pegar uma próxima ação
                    if (tempoFaxina <= 0) {
                        tela.apendTexto("\"Acabou meu tempo.\"");
                        limpando = false;
                        fimDoExpediente = true;
                    }
                }
                synchronized (agente) {
                    agente.getInMessages().removeAll(listaExecutada);
                }
            }


        }

        goal.setAchieved(true);
        tela.apendTexto("Fiz tudo que deu, dentro do tempo! :)\"");
        aguardandoChamada = true;
        synchronized (agente) {
            agente.getInMessages().clear();

            Message saida = new Message("?" + Thread.currentThread().getName(), null, agente.getAgentName(), agente.getAgentName());
            saida.setPerformative(ConstantesAplicacao.ACAO_IR_PARA_A_CENTRAL);
            agente.send(saida);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlanoFaxina);
    }
}
