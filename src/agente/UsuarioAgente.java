/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import comunicacao.ProtocoloTransporteMensagem;
import framework.FIPA.ElementID;
import framework.FIPA.communication.http.MessageTransportProtocol;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.mentalState.Plan;
import framework.mentalState.goal.Goal;
import framework.organization.MainOrganization;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author heliokann
 */
public class UsuarioAgente extends Agent {

    private boolean manterResidenciaHabitavel;

    public UsuarioAgente(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID idElemento) {
        super(idElemento, ProtocoloTransporteMensagem.getInstancia());
    }

    @Override
    // COLOCAR CEREBRO!
    protected Plan selectingPlan(Collection colecaoPlanos, Goal objetivoAlvo) {
        List<Plan> planos = (List<Plan>) colecaoPlanos;

        for (Plan plano : planos) {
            if(plano.getGoal().equals(objetivoAlvo)) {
                System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'selectingPlan': retornando o plano " + plano.toString() + " para o objetivo " + objetivoAlvo.getName());
                return plano;
            }
        }
        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'selectingPlan': retornando nulo -> nao encontrou nenhum plano que atendia ao objetivo " + objetivoAlvo.getName());
        return null;
    }

    @Override
    // VERIFICAR
    protected void executingPlan(Plan plano) {
        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'executingPlan': executando na AgentRole " + this.getCurrentRole().getRoleName());
        plano.execute(this.getCurrentRole());
    }

    @Override
    protected Goal selectingGoalToAchieve() {
        Goal objetivoSelecionado = null;

        for (Object object : this.getGoals()) {
            Goal objetivo = (Goal) object;

            if (objetivo.getPriority() > objetivoSelecionado.getPriority()) {
                objetivoSelecionado = objetivo;
            }
        }

        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'selectingGoalToAchieve': objetivo -> " + objetivoSelecionado);

        return objetivoSelecionado;
    }

    @Override
    protected boolean checkIfWillContinue() {
        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'checkIfWillContinue': retorno -> " + false + " -> Motivo: NAO TEM! NAO TEM DOCUMENTACAO DESSE METODO!");
        return false;
    }
}
