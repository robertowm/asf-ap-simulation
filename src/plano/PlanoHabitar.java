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
import agente.UsuarioAgente;
import agente.papel.Papel;
import ambiente.Ambiente;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import framework.mentalState.Plan;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetivo.ResidirFeliz;
import util.FluxoResultados;
import util.GerenciadorFluxos;
import util.ThreadAtualizacaoPlano;

/**
 *
 * @author heliokann
 */
public class PlanoHabitar extends Plan implements Serializable {

    private Map<String,Double> mapaAcoes = null;
    
    public PlanoHabitar() {
        this.setAction(new AcaoChamarEmpregada());
        this.setAction(new AcaoDesarrumar());
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setAction(new AcaoSujar());
        this.setAction(new AcaoVerificarComodo());

        this.setGoal(new ResidirFeliz());
        
        this.mapaAcoes = new HashMap<String,Double>();
    }

    @Override
    public synchronized void execute(AgentRole role) {
        List<Message> listaExecutada;
        UsuarioAgente agente = (UsuarioAgente) role.getAgentPlayingRole();
        Ambiente ambiente = (Ambiente) agente.getEnvironment();

//        boolean loop = true;
        int descansa = 400;
//        Principal tela = JDesktop.getTela(agente);

//        while (!goal.getAchieved()) {
        CopyOnWriteArrayList<Message> mensagens = new CopyOnWriteArrayList<Message>(agente.getInMessages());
        listaExecutada = new ArrayList(mensagens.size());
        for (Message mensagem : mensagens) {
            AcaoAgente acao = ComandoAcao.getAcao(mensagem.getPerformative());
            boolean executou = acao.execute(agente, mensagem);

            if (executou) {
                listaExecutada.add(mensagem);
                Double n = (Double)mapaAcoes.get(acao.getClass().getName());
                mapaAcoes.put(acao.getClass().getName(), (n == null) ? 0 : n + 1);
            }

            try {
                Thread.sleep(descansa);
            } catch (InterruptedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }

            if (ambiente.isAmbienteArrumadoLimpo()) {
                boolean achieved = goal.getAchieved();
                if(!achieved) {
                    goal.setAchieved(true);
                }
                Thread thread = new ThreadAtualizacaoPlano(mapaAcoes,(Papel) role);
                thread.start();
            } else {
                goal.setAchieved(false);
            }
        }
        synchronized (agente) {
            agente.getInMessages().removeAll(listaExecutada);
        }
//        }
    }

    @Override
    public void execute(MainOrganization organization) {
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlanoHabitar);
    }
}
