/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente;

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
public class UsuarioAgente extends Agent{
    
    boolean manterResidenciaHabitavel;
    
    public UsuarioAgente(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID idElemento){
        super(idElemento,new MessageTransportProtocol());
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean checkIfWillContinue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
