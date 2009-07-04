/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import acao.AcaoAgente;
import acao.AcaoAtenderRequisicao;
import acao.AcaoAtualizarQuadroTarefas;
import acao.AcaoChamarEmpregada;
import acao.command.ComandoAcao;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import framework.mentalState.Plan;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetivo.TornarResidenciaHabitavel;
import visual.JDesktop;
import visual.Saida;

/**
 *
 * @author heliokann
 */
public class PlanoSecretaria extends Plan implements Serializable {

    public PlanoSecretaria() {
//        this.setAction(new AcaoVerificarComodo());
        this.setAction(new AcaoChamarEmpregada());
        this.setAction(new AcaoAtenderRequisicao());
        this.setAction(new AcaoAtualizarQuadroTarefas());

        this.setGoal(new TornarResidenciaHabitavel());
    }

    @Override
    public void execute(AgentRole role) {
        List<Message> listaExecutada;
        Agent agente = role.getAgentPlayingRole();

        int descansa = 400;
        Saida tela = JDesktop.getTela(agente);
        CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>(agente.getInMessages());
        if (mensagens.isEmpty()) {
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
                boolean executou = acao.execute(agente, mensagem);

                if (executou) {
                    listaExecutada.add(mensagem);
                }

                try {
                    Thread.sleep(descansa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
            synchronized (agente) {
                agente.getInMessages().removeAll(listaExecutada);
            }
        }
//        goal.setAchieved(true);
    }

    @Override
    public void execute(MainOrganization organization) {
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlanoSecretaria);
    }
}
