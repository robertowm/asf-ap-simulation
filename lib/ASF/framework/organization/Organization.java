package framework.organization;

import java.io.Serializable;
import java.util.*;
import framework.objectRole.ObjectRole;
import framework.mentalState.goal.Goal;
import framework.mentalState.Plan;
import framework.FIPA.ElementID;
import framework.FIPA.StatusAgentandSubOrganization;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de uma organização. 
 */
public abstract class Organization extends MainOrganization implements StatusAgentandSubOrganization, Serializable {

    /**
     * @associates <{Environment}>
     * @link association
     * @label inhabits
     * @clientCardinality 1..*
     * @supplierCardinality 1
     * @undirected
     */
    /**
     * @associates <{AgentRole}>
     * @link association
     * @label plays
     * @clientRole sub_organization
     * @clientCardinality 0..1
     * @supplierCardinality 1..*
     * @undirected
     */
    /**
     * Conjunto de papéis sendo executados pela organização.
     */
    protected Collection rolesBeingPlayed = new Vector();
    /**
     * @associates <{framework.organization.MainOrganization}>
     * @clientCardinality 0..*
     * @directed 
     * @label plays_in
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de organizações associadas à organização.
     */
    protected Collection organizations = new Vector();

    /**
     * Construtor da classe que atribui um conjunto de caracteríticas estruturais para a 
     * organização. 
     * @param theEnvironment
     * Ambiente no qual a organização irá habitar.
     * @param initialOrg
     * Organização associada.
     * @param initialRole
     * Papel inicial que a organização irá processar.
     * @param aid
     * Identificador global da organização.
     */
    public Organization(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID aid) {

        super(theEnvironment, aid);

        if (initialOrg != null) {
            setRoleBeingPlayed(initialRole, initialOrg);
            initialOrg.setSubOrganizations(this);
        }

    }

    /**
     * Fornece o conjunto de papéis sendo executados da organização.
     * @return
     * Todos os papéis sendo executados da organização.
     */
    public Collection getRolesBeingPlayed() {
        return rolesBeingPlayed;
    }

    /**
     * Atribui um novo papel a ser executado ao conjunto de papéis sendo executados 
     * e a organização onde este papel será executado.
     * @param newRole
     * Novo papel sendo executado pela organização.
     * @param newOrg
     * Organização onde o papel passado por parâmetro será executado.
     */
    public void setRoleBeingPlayed(AgentRole newRole, MainOrganization newOrg) {
        rolesBeingPlayed.add(newRole);
        organizations.add(newOrg);

    }

    /**
     * Método responsável por verificar se a thread associada ao papel passado por parâmetro 
     * está em processamento ou não. 
     * @param role
     * Papel que será verificado.
     * @return
     * Valor booleano indicando se a thread associada ao papel parou de estar em processamento
     * ou não. True se a thread parou e false caso contrário.
     */
    protected boolean checkIfStopped(AgentRole role) {
        return role.threadStopped();
    }

    /**
     * Método responsável por verificar se a thread associada ao papel passado por parâmetro 
     * foi suspensa. 
     * @param role
     * Papel verificado.
     */
    protected void checkIfSuspended(AgentRole role) {
        if (role.threadSuspended()) {
            synchronized (this) {
                while (role.threadSuspended()) {
                    System.out.println("Suspended");
                }
                System.out.println("Resumed");
            }
        }
    }

    /**
     * Método responsável por fornecer o papel que está sendo executado no momento pela
     * organização.  
     * @return
     * Papel que está sendo executado no momento pela organização, caso não haja nenhum, 
     * fornece o valor null.
     */
    protected AgentRole getCurrentRole() {
        Collection vRoles = getRolesBeingPlayed();
        Iterator enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            AgentRole role = (AgentRole) enumvRoles.next();
            if (role.getRoleName().equals(Thread.currentThread().getName())) {
                return role;
            }
        }
        return null;
    }

    /**
     * Método principal da organização. Este método é executado por todas as threads da 
     * organização. Cada thread representa um papel que este está executando. 
     * Nele verificasse quais objetivos ainda não foram alcançados, para que assim
     * sejam escolhidos planos que serão responsáveis por executar ações com a 
     * finalidade de alcançá-los.
     * Quando um objetivo é alcançado, há a verificação se a organização tem mais algum 
     * pendente.
     * Se todos tiverem sido alcançados, é feita a analize se há mais algum papel a ser 
     * executado pela organização. Caso não haja, ela será destruída, pois um 
     * dos seus pré-requisitos de vida é ter ao menos um papel associado à ela.   
     */
    public void run() {
        System.out.println("-----> Organization " + Thread.currentThread().getName() + " beginning its execution <-----");
        description.setState(MainOrganization.running);
        Collection vPlansExecuted = new Vector();
        boolean continueExecution = true;
        AgentRole currentRole = getCurrentRole();
        if (currentRole != null) {
            //Checking if thread was stopped
            while (continueExecution && !checkIfStopped(currentRole)) {
                //Checking if thread was suspended
                checkIfSuspended(currentRole);

                //Selecting goal to be achieved
                Goal goal = selectingGoalToAchieve();

                //Checking if thread was stopped
                while (goal != null) {
                    //Selecting plan to be executed
                    Plan plan = selectingPlan(vPlansExecuted, goal);

                    //Cheking if thread was stopped
                    while (plan != null) {
                        //Checking if thread was suspended
                        this.checkIfSuspended(currentRole);

                        //Executing plan
                        executingPlan(plan);

                        checkIfSuspended(currentRole);
                        if (checkIfStopped(currentRole)) {
                            break;
                        }
                        vPlansExecuted.add(plan);
                        if (!goal.getAchieved()) //Selecting another plan
                        {
                            plan = selectingPlan(vPlansExecuted, goal);
                        } else {
                            //Goal achieved
                            //If goal type eguals maintaim, the organization must always try to achieve the goal
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
                //or the organization tried to achieve all goals.
                if (checkIfStopped(currentRole)) {
                    break;
                }
                continueExecution = checkIfWillContinue();
            }
            // The thread was stopped

            //The current role must be removed from the list of the roles being played
            Collection vRoles = getRolesBeingPlayed();
            vRoles.remove(currentRole);

            //The current role must be destroyed
            currentRole.destroy();
            System.out.println("-----> Organization Thread " + Thread.currentThread().getName() + " is finishing its execution <-----");

            //Verify if the organization is playing other roles
            vRoles = getRolesBeingPlayed();
            if (vRoles == null) {
                //If the organization is not playing other roles, the organization must be destroyed
                destroy();
                description.setState(MainOrganization.death);
                System.out.println("-----> Organization " + getOrganizationName() + " is finishing its execution <-----");
            }
        }
    }

    /**
     * Método responsável por destruir uma organização. A destruição de uma organização é 
     * caracterizada pela parada de todas as threads associadas à ela 
     * (a organização pára de executar todos os papéis), pela desassociação dos objetos e papéis
     * a ela, e pela retirada da organização do ambiente onde esta estava executando.
     */
    public void destroy() {
        //Destroying the roles being played
        Collection vRoles = getRolesBeingPlayed();
        Iterator enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            AgentRole roleAux = (AgentRole) enumvRoles.next();
            vRoles.remove(roleAux);
            roleAux.destroy();
        }

        //Destroying the agent roles played in the organization
        //The thread of the entity playing the role must be stoped
        vRoles = getAgentRoles();
        enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            AgentRole roleAux = (AgentRole) enumvRoles.next();
            roleAux.stopThread();
            vRoles.remove(roleAux);
        }

        //Destroying the object roles played in the organization
        //It is not necessary to destroy the object playing the role
        vRoles = getObjectRoles();
        enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            ObjectRole objRoleAux = (ObjectRole) enumvRoles.next();
            vRoles.remove(objRoleAux);
        }

        //Canceling the register
        MTS_Environment env = getEnvironment();
        env.cancelOrganizationRegister(this);
    }
}
