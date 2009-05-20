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
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetivo.ResidirFeliz;

/**
 *
 * @author heliokann
 */
public class PlanoHabitar extends Plan implements Serializable{

    public PlanoHabitar() {
        this.setAction(new AcaoChamarEmpregada());
        this.setAction(new AcaoDesarrumar());
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setAction(new AcaoSujar());
        this.setAction(new AcaoVerificarComodo());
        
        this.setGoal(new ResidirFeliz());
    }

    @Override
    public void execute(AgentRole role) {
        List<Message> listaExecutada;
        Agent agente = role.getAgentPlayingRole();
        boolean loop = true;
        int descansa = 400;
        while (loop) {

            Collection<Message> mensagens = agente.getInMessages();
            listaExecutada = new ArrayList(mensagens.size());

            for (Message mensagem : mensagens) {
                AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
                boolean executou = acao.execute(agente, mensagem);

                if(executou){
                    listaExecutada.add(mensagem);
                }

                try {
                    Thread.sleep(descansa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            mensagens.removeAll(listaExecutada);
        }

        goal.setAchieved(true);

    }

    @Override
    public void execute(MainOrganization organization) {
        System.out.println("[PlanoHabitar] chamada do metodo 'public void execute(MainOrganization organization)'");
    }

    @Override
    public boolean equals(Object obj) {
        return  (obj instanceof PlanoHabitar);
    }
    
    




}
