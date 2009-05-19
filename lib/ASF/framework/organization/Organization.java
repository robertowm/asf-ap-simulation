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
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma organiza��o. 
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
     * Conjunto de pap�is sendo executados pela organiza��o.
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
     * Conjunto de organiza��es associadas � organiza��o.
     */
    protected Collection organizations = new Vector();

    /**
     * Construtor da classe que atribui um conjunto de caracter�ticas estruturais para a 
     * organiza��o. 
     * @param theEnvironment
     * Ambiente no qual a organiza��o ir� habitar.
     * @param initialOrg
     * Organiza��o associada.
     * @param initialRole
     * Papel inicial que a organiza��o ir� processar.
     * @param aid
     * Identificador global da organiza��o.
     */
    public Organization(MTS_Environment theEnvironment, MainOrganization initialOrg, AgentRole initialRole, ElementID aid) {

        super(theEnvironment, aid);

        if (initialOrg != null) {
            setRoleBeingPlayed(initialRole, initialOrg);
            initialOrg.setSubOrganizations(this);
        }

    }

    /**
     * Fornece o conjunto de pap�is sendo executados da organiza��o.
     * @return
     * Todos os pap�is sendo executados da organiza��o.
     */
    public Collection getRolesBeingPlayed() {
        return rolesBeingPlayed;
    }

    /**
     * Atribui um novo papel a ser executado ao conjunto de pap�is sendo executados 
     * e a organiza��o onde este papel ser� executado.
     * @param newRole
     * Novo papel sendo executado pela organiza��o.
     * @param newOrg
     * Organiza��o onde o papel passado por par�metro ser� executado.
     */
    public void setRoleBeingPlayed(AgentRole newRole, MainOrganization newOrg) {
        rolesBeingPlayed.add(newRole);
        organizations.add(newOrg);

    }

    /**
     * M�todo respons�vel por verificar se a thread associada ao papel passado por par�metro 
     * est� em processamento ou n�o. 
     * @param role
     * Papel que ser� verificado.
     * @return
     * Valor booleano indicando se a thread associada ao papel parou de estar em processamento
     * ou n�o. True se a thread parou e false caso contr�rio.
     */
    protected boolean checkIfStopped(AgentRole role) {
        return role.threadStopped();
    }

    /**
     * M�todo respons�vel por verificar se a thread associada ao papel passado por par�metro 
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
     * M�todo respons�vel por fornecer o papel que est� sendo executado no momento pela
     * organiza��o.  
     * @return
     * Papel que est� sendo executado no momento pela organiza��o, caso n�o haja nenhum, 
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
     * M�todo principal da organiza��o. Este m�todo � executado por todas as threads da 
     * organiza��o. Cada thread representa um papel que este est� executando. 
     * Nele verificasse quais objetivos ainda n�o foram alcan�ados, para que assim
     * sejam escolhidos planos que ser�o respons�veis por executar a��es com a 
     * finalidade de alcan��-los.
     * Quando um objetivo � alcan�ado, h� a verifica��o se a organiza��o tem mais algum 
     * pendente.
     * Se todos tiverem sido alcan�ados, � feita a analize se h� mais algum papel a ser 
     * executado pela organiza��o. Caso n�o haja, ela ser� destru�da, pois um 
     * dos seus pr�-requisitos de vida � ter ao menos um papel associado � ela.   
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
     * M�todo respons�vel por destruir uma organiza��o. A destrui��o de uma organiza��o � 
     * caracterizada pela parada de todas as threads associadas � ela 
     * (a organiza��o p�ra de executar todos os pap�is), pela desassocia��o dos objetos e pap�is
     * a ela, e pela retirada da organiza��o do ambiente onde esta estava executando.
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
