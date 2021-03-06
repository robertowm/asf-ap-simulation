/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import comunicacao.ProtocoloTransporteMensagem;
import framework.FIPA.Description;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.mentalState.Plan;
import framework.mentalState.goal.Goal;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author heliokann
 */
public class UsuarioAgente extends Agent implements Serializable {

    private String nome;

    public UsuarioAgente(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID idElemento) {
        super(idElemento, ProtocoloTransporteMensagem.getInstancia());
        nome = idElemento.getName();
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UsuarioAgente)) {
            return false;
        }
        UsuarioAgente agente = ((UsuarioAgente) (obj));
        return agente.nome.equals(this.nome);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    @Override
    // COLOCAR CEREBRO!
    protected Plan selectingPlan(Collection planosExecutados, Goal objetivoAlvo) {
        List<Plan> planosUsados = (List<Plan>) planosExecutados;

        List<Plan> planosObjetivo = (List<Plan>) this.getPlans();

        for (Plan plan : planosObjetivo) {
            if (plan.getGoal().equals(objetivoAlvo)) {
                if (!planosUsados.contains(plan)) {
                    return plan;
                }
            }
        }

//        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'selectingPlan': retornando nulo -> nao encontrou nenhum plano que atendia ao objetivo " + objetivoAlvo.getName());
        return null;
    }

    @Override
    // VERIFICAR
    protected void executingPlan(Plan plano) {
//        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] " + plano.toString() + " Metodo 'executingPlan': executando na AgentRole " + this.getCurrentRole().getRoleName());
        plano.execute(this.getCurrentRole());
    }

    @Override
    protected Goal selectingGoalToAchieve() {
        if (this.getGoals().isEmpty()) {
            return null;
        }
        List<Goal> lista = (List<Goal>) this.getGoals();
        Goal objetivoSelecionado = lista.get(0);
        for (int valor = 1; valor < this.getGoals().size(); valor++) {
            Goal objetivo = (Goal) lista.get(valor);

            if (objetivo.getPriority() > objetivoSelecionado.getPriority()) {
                objetivoSelecionado = objetivo;
            }
        }

//        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'selectingGoalToAchieve': objetivo -> " + objetivoSelecionado);

        return objetivoSelecionado;
    }

    @Override
    protected boolean checkIfWillContinue() {
//        System.out.println("[UsuarioAgente:" + this.getAgentName().getName() + "] Metodo 'checkIfWillContinue': retorno -> " + plans.isEmpty() + " -> Motivo: NAO TEM! NAO TEM DOCUMENTACAO DESSE METODO!");
        return plans.isEmpty();
    }

    @Override
    public void setDescription(Description description) {
        this.description = description;
    }

    public void run() {
//        System.out.println("-----> Agent " + getAgentName().getName() + " beginning its execution <-----");
        // Parte nova!
        try {
            if (description == null) {
                Thread.currentThread().wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (description == null);
        description.setState(Agent.running);

        Vector vPlansExecuted = new Vector();
        boolean continueExecution = true;
        AgentRole currentRole = getCurrentRole();

        if (currentRole != null) {
            currentRole.setStatus(AgentRole.active);

            //Cheking if thread was stopped
            while (continueExecution && !checkIfStopped(currentRole)) {
                //Checking if thread was suspended
                checkIfSuspended(currentRole);

                //Selecting goal to be achieved
                Goal goal = selectingGoalToAchieve();
                /*
                System.err.println( goal );
                
                if( goal != null )
                {
                System.err.println( goal.getName() );
                }
                 */

                //Checking if thread was stopped
                while (goal != null) {
                    //Selecting plan to be executed
                    //System.err.println("goal :" + goal.getName());
                    Plan plan = selectingPlan(vPlansExecuted, goal);

                    //Cheking if thread was stopped
                    while (plan != null) {
                        //System.err.println("plan : " + plan);
                        //Checking if thread was suspended
                        checkIfSuspended(currentRole);

                        //Executing plan
                        executingPlan(plan);

                        checkIfSuspended(currentRole);
                        if (checkIfStopped(currentRole)) {
                            break;
                        }
                        vPlansExecuted.add(plan);
                        if (!goal.getAchieved()) {
                            //Selecting another plan

                            plan = selectingPlan(vPlansExecuted, goal);
                        } else {
                            //Goal achieved
                            //If goal type eguals maintaim, the agent must always try to achieve the goal
                            //but now it has low priority in order to let other goals to be achieved
                            if (goal.getGoalType().equals("maintain")) {
                                goal.setAchieved(false);
                                goal.setPriority(1);
                            }
                            plan = null;
                        }

                    }
                    //The goal was achieved or
                    //all plans associated with the goal were executed or
                    //there is not any plan associated with the goal
                    //Selecting another goal
                    if (checkIfStopped(currentRole)) {
                        break;
                    }
                    goal = selectingGoalToAchieve();
                    vPlansExecuted.clear();
                }

                //There is not any other goal to be achieved:
                //all goals where achived or
                //or the agent tried to achieve all goals.

                if (checkIfStopped(currentRole)) {
                    break;
                }
                continueExecution = checkIfWillContinue();
            //System.err.println( this.elementId.getName() + "  " + continueExecution );
            }
            // The thread was stopped

            //The current role must be removed from the list of the roles being played
            Collection vRoles = getRolesBeingPlayed();

            if (currentRole.getStatus().compareTo(AgentRole.inactive) != 0) {
                vRoles.remove(currentRole);
                //The current role must be destroyed
                currentRole.destroy();
            }

//            System.out.println("-----> Agent Thread " + Thread.currentThread().getName() + " is finishing its execution <-----");

            //Verify if the agent is playing other roles
            vRoles = getRolesBeingPlayed();

            if (vRoles.size() == 0) {
                //If the agent is not playing other roles, the agent must be destroyed
                destroy();
            //AMS ams = AMS.getInstance();
            //ams.deregister( description );				
            }
        }
    }

    protected AgentRole getCurrentRole() {
        Collection vRoles = getRolesBeingPlayed();
        for (Object object : vRoles) {
            AgentRole role = (AgentRole) object;
//            System.out.println(role.getRoleName());
//            System.out.println("********************************");
//            System.out.println(Thread.currentThread().getName());
            if (role.getAgentPlayingRole().equals(this)) {
                return role;
            }
        }

        return null;
    }
}