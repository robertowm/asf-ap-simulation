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

/**
 *
 * @author heliokann
 */
public class UsuarioAgente extends Agent {

    boolean manterResidenciaHabitavel;

    public UsuarioAgente(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID idElemento) {
        super(idElemento, ProtocoloTransporteMensagem.getInstancia());
    }

    @Override
    protected Plan selectingPlan(Collection arg0, Goal arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executingPlan(Plan arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
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
