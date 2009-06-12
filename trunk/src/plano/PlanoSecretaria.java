/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import acao.AcaoAgente;
import acao.AcaoArrumar;
import acao.AcaoChamarEmpregada;
import acao.AcaoDesarrumar;
import acao.AcaoLimpar;
import acao.AcaoSujar;
import acao.AcaoVerificarComodo;
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

/**
 *
 * @author heliokann
 */
public class PlanoSecretaria extends Plan implements Serializable {

    public PlanoSecretaria() {
        this.setAction(new AcaoChamarEmpregada());
        this.setAction(new AcaoDesarrumar());
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setAction(new AcaoSujar());
        this.setAction(new AcaoVerificarComodo());

        this.setGoal(new TornarResidenciaHabitavel());
    }

    @Override
    public void execute(AgentRole role) {
        List<Message> listaExecutada;
        Agent agente = role.getAgentPlayingRole();
//        boolean loop = true;
        int descansa = 400;
//        Principal tela = JDesktop.getTela(agente);

//        while (loop) {
            CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>( agente.getInMessages());
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
            synchronized(agente) {
                agente.getInMessages().removeAll(listaExecutada);
            }
//        }
//        goal.setAchieved(true);

    }

    @Override
    public void execute(MainOrganization organization) {
        System.out.println("[PlanoHabitar] chamada do metodo 'public void execute(MainOrganization organization)'");
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlanoSecretaria);
    }
}
