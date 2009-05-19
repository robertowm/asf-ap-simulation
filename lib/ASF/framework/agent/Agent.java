package framework.agent;

import java.io.Serializable;
import java.util.*;
import framework.mentalState.Message;
import framework.mentalState.belief.Belief;
import framework.mentalState.Action;
import framework.mentalState.goal.Goal;
import framework.mentalState.Plan;
import framework.environment.MTS_Environment;
import framework.FIPA.ElementID;
import framework.FIPA.AMS;
import framework.FIPA.AgentPlatformDescription;
import framework.FIPA.AgentRoleDescription;
import framework.FIPA.Description;
import framework.FIPA.StatusAgentandSubOrganization;
import framework.FIPA.communication.MTP;
import framework.agentRole.AgentRole;
import framework.organization.MainOrganization;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de um agente.
 */
public abstract class Agent implements StatusAgentandSubOrganization, Runnable, Serializable {

    /**
     * @clientCardinality 1..*
     * @directed true
     * @label inhabits
     * @supplierCardinality 1
     */
    /**
     * Ambiente no qual o agente irá executar.
     */
    protected MTS_Environment environment = null;
    /**
     * @associates <{framework.organization.Organization}>
     * @clientCardinality 0..1
     * @directed true
     * @label plays_in
     * @link association
     * @supplierCardinality 1..*
     * @undirected 
     */
    /**
     * Conjunto de organizações onde o agente está desempenhando algum papel.
     */
    protected Collection organizations = new Vector();
    /**
     * @associates <{AgentRole}>
     * @link association
     * @label plays
     * @clientCardinality 0..1
     * @supplierCardinality 1..*
     * @undirected
     */
    /**
     * Papéis sendo executados pelo agente. Um agente poderá estar desempenhando 
     * mais de um papel.
     */
    protected Collection rolesBeingPlayed = new Vector();
    /**
     * @associates <{Goal}>
     * @clientCardinality 0..*
     * @directed true
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de objetivos que o agente deseja alcançar.
     */
    protected Collection goals = new Vector();
    /**
     * @associates <{framework.mentalState.belief.Belief}>
     * @clientCardinality 0..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de crenças do agente.
     */
    protected Collection beliefs = new Vector();
    /**
     * @associates <{Plan}>
     * @clientCardinality 0..*
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de planos do agente, visando o alcance dos objetivos.
     */
    protected Collection plans = new Vector();
    /**
     * @associates <{framework.mentalState.Action}>
     * @clientCardinality 0..*
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de ações do agente. 
     */
    protected Collection actions = new Vector();
    /**
     * @associates <{Message}>
     * @clientCardinality 0..*
     * @clientRole receiver
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     * @supplierRole inMsg
     */
    /**
     * Conjunto de mensagens recebidas pelo agente.
     */
    protected Collection inMessages = new Vector();
    /**
     * @associates <{Message}>
     * @clientCardinality 0..*
     * @clientRole sender
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     * @supplierRole outMsg
     */
    /**
     * Conjunto de mensagens enviadas pelo agente.
     */
    protected Collection outMessages = new Vector();
    /**
     * 
     * @clientCardinality 1 
     */
    /**
     * Descrição do agente.
     */
    protected Description description;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Identificador do agente.
     */
    protected ElementID elementId;
    /**
     * Message Transport Protocol responsável por enviar mensagens para outros agentes e/ou organizações.
     */
    protected MTP mtp;

    /**
     * Construtor da classe que atribui o identificador do agente.
     * @param elementId
     * Identificador do agente. 
     */
    protected Agent(ElementID elementId, MTP mtp) {
        this.elementId = elementId;
        this.mtp = mtp;
    }

    /**
     * Fornece o identificador do agente.
     * @return
     * Identificador do agente.
     */
    public ElementID getAgentName() {
        return elementId;
    }

    /**
     * Fornece o ambiente no qual o agente irá executar.
     * @return
     * Ambiente no qual o agente irá executar.
     */
    public MTS_Environment getEnvironment() {
        return environment;
    }

    /**
     * Fornece o conjunto de papéis que estão sendo desempenhados pelo agente.
     * @return
     * Conjunto de papéis sendo desempenhados pelo agente.
     */
    public Collection getRolesBeingPlayed() {
        return rolesBeingPlayed;
    }

    /**
     * Fornece o conjunto de organizações onde o agente está desempenhando algum 
     * papel.
     * @return
     * Todas as organizações associadas com o agente.
     */
    public Collection getOrganizations() {
        return organizations;
    }

    /**
     * Fornece os objetivos que o agente deseja alcançar.
     * @return
     * Todos os objetivos do agente.
     */
    public Collection getGoals() {
        return goals;

    }

    /**
     * Fornece o conjunto de crenças do agente.
     * @return
     * Todas as crenças do agente.  
     */
    public Collection getBeliefs() {
        return beliefs;
    }

    /**
     * Fornece todos os planos do agente.
     * @return
     * Todos os planos do agente.
     */
    public Collection getPlans() {
        return plans;
    }

    /**
     * Fornece o conjunto de ações do agente. 
     * @return
     * Todas as ações do agente.
     */
    public Collection getActions() {
        return actions;
    }

    /**
     * Fornece as mensagens enviadas pelo agente.
     * @return
     * Todas as mensagens enviadas pelo agente.
     */
    public Collection getInMessages() {
        return inMessages;

    }

    /**
     * Fornece as mensagens recebidas pelo agente.
     * @return
     * Todas as mensagens recebidas pelo agente.
     */
    public Collection getOutMessages() {
        return outMessages;

    }

    /**
     * Fornece a descrição do agente.
     * @return
     * Descrição do agente.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Atribui o identificador do agente.
     * @param aid
     * Identificador do agente.
     */
    public void setAgentName(ElementID elementId) {
        this.elementId = elementId;
    }

    /**
     * Atribui o ambiente no qual o agente irá executar.
     * @param newEnv
     * Novo ambiente no qual o agente irá executar.
     */
    public void setEnvironment(MTS_Environment newEnv) {
        environment = newEnv;
    }

    /**
     * Atribui um novo papel ao agente indicando a organização na qual o papel 
     * será desempenhado.
     * @param newRole
     * Novo papel atribuído ao agente.
     * @param newOrg
     * Organização onde o papel passado por parâmetro está localizado.
     */
    public void setRolesBeingPlayed(AgentRole newRole, MainOrganization newOrg) {
        rolesBeingPlayed.add(newRole);
        organizations.add(newOrg);
    }

    /**
     * Atribui um novo objetivo ao conjunto de objetivos do agente. 
     * @param newGoal
     * Novo objetivo a ser alcançado pelo agente.
     */
    public void setGoal(Goal newGoal) {
        goals.add(newGoal);
    }

    /**
     * Atribui uma nova crença ao agente. 
     * @param newBelief
     * Nova crença do agente. 
     */
    public void setBelief(Belief newBelief) {
        beliefs.add(newBelief);
    }

    /**
     * Atribui um novo plano ao agente.
     * @param newplan
     * Novo plano do agente.
     */
    public void setPlan(Plan newplan) {
        plans.add(newplan);
    }

    /**
     * Atribui uma nova ação ao agente.
     * @param newAction
     * Nova ação do agente. 
     */
    public void setAction(Action newAction) {
        actions.add(newAction);
    }

    /**
     * Atribui uma nova mensagem enviada pelo agente à coleção de mensagens enviadas.
     * @param newMessage
     * Nova mensagem enviada pelo agente.
     */
    public void setInMessage(Message newMessage) {
        inMessages.add(newMessage);

    }

    /**
     * Atribui uma nova mensagem recebida pelo agente à coleção de mensagens recebidas. 
     * @param newMessage
     * Nova mensagem recebida pelo agente.
     */
    public void setOutMessage(Message newMessage) {
        outMessages.add(newMessage);
    }

    /**
     * Atribui a descrição do agente.
     * @param description
     * Descrição do agente.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Método responsável por verificar se a thread associada ao papel passado por parâmetro de entrada 
     * foi parada.
     * 
     * @param role
     * Papel que será verificado se está em execução.
     * @return
     * Valor do tipo boolean, indicando se o papel está em execução ou não.
     */
    protected boolean checkIfStopped(AgentRole role) {
        return role.threadStopped();
    }

    /**
     * Método responsável por verificar se a thread associada ao papel passado como parâmetro de entrada foi 
     * suspensa. 
     * 
     * @param role
     * Papel que será analisado se está suspenso. 
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
     * Método responsável por destruir um agente. A destruição de um agente é 
     * caracterizada pela parada de todas as threads associadas à ele 
     * (o agente pára de executar todos os papéis), pelo seu dês-registro
     * no AMS e pela retirada do agente do ambiente onde este estava executando.
     */
    public void destroy() {
        //Stopping all thre  ads
        Collection vRoles = getRolesBeingPlayed();
        Iterator enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            AgentRole role = (AgentRole) enumvRoles.next();
            role.stopThread();
            role.setStatus(AgentRole.death);
            if (role.getStatus().compareTo(AgentRole.inactive) == 0) {
                role.destroy();
            }
        }

        AMS ams = AMS.getInstance();
        ams.deregister(this.description);

        //Canceling the register
        MTS_Environment env = getEnvironment();
        env.cancelAgentRegister(this);

        System.out.println("-----> Agent " + getAgentName().getName() + " is finishing its execution <-----");
    }

    /**
     * Método principal do agente. Este método é executado por todas as threads do 
     * agente. Cada thread representa um papel que este está executando. 
     * Nele verificasse quais objetivos do seu papel corrente
     * ainda não foram alcançados, para que assim sejam escolhidos planos.
     * Importante ressaltar que durante o processamento da thread, o agente pode estar em 
     * qualquer estado do seu ciclo de vida, como por exemplo suspenso e
     * parado.
     * A thread associada ao papel irá parar de executar quando não existirem 
     * objetivos a serem alcançados, devido o agente ter os alcançado, ou pelo
     * agente ter tentado os alcançar.
     * Após a parada, o papel corrente do agente é destruído.
     * Já a suspensão de uma thread é definida pelos seus planos. 
     */
    //Methods that can or must be implemented in the application agents
    public void run() {
        System.out.println("-----> Agent " + getAgentName().getName() + " beginning its execution <-----");
        // Parte nova!
        while (description == null);
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

            System.out.println("-----> Agent Thread " + Thread.currentThread().getName() + " is finishing its execution <-----");

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

    /**
     * Fornece o papel associado a thread corrente do agente.
     * @return
     * Papel corrente do agente.
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
     * Método abstrato responsável por fornecer um plano para alcançar o objetivo 
     * passado por parâmetro. Além do objetivo, o método ainda recebe como parâmetro 
     * a lista de todos os planos executados. 
     * 
     * @param vPlansExecuted
     * Planos executados pelo agente. Essa informação poderá ser útil para
     * alguma política de seleção de plano estipulada pelo desenvolvedor. 
     * @param goal
     * Objetivo a ser alcançado.
     * @return
     * Plano que tentará alcançar o objetivo.
     */
    protected abstract Plan selectingPlan(Collection vPlansExecuted, Goal goal);

    /**
     * Método abstrato responsável por executar um plano. 
     * 
     * @param plan
     * Plano que será executado, visando o alcance de um objetivo.
     */
    protected abstract void executingPlan(Plan plan);

    /**
     * Método abstrato responsável por fornecer um objetivo do agente para ser 
     * alcançado.
     * 
     * @return
     * Objetivo a ser alcançado.
     */
    protected abstract Goal selectingGoalToAchieve();

    /**
     * Método abstrato responsável por verificar se o agente irá continuar seu processamento.
     * @return
     * Valor booleano indicando se o agente continuará ou não o seu processamento.
     */
    protected abstract boolean checkIfWillContinue();

    /**
     * Método responsável por procurar algum papel que o agente esteja executando.
     * @param name
     * Nome do papel a ser procurado.
     * @return
     * Valor booleano. True se encontrou algum papel com o mesmo nome do parâmetro 
     * fornecido e false caso contrário.
     */
    public boolean searchRoleName(String name) {
        int num = 0;
        AgentRole elementIdName;
        Vector collection = (Vector) rolesBeingPlayed;
        while (num < collection.size()) {
            elementIdName = (AgentRole) collection.get(num);
            if ((name.toLowerCase()).compareTo(elementIdName.getRoleName().toLowerCase()) == 0) {
                return true;
            }
            num++;
        }

        return false; // nao encontrou

    }

    /**
     * Responsável por criar um identificador de um papel que será executado
     * pelo agente.
     * @param name
     * Nome do papel que o agente irá executar.
     * @param isLocal
     * Variável booleana que indica se o papel a ser executado pelo agente
     * é local(true) ou não(false).
     * @return
     * Se não existir nenhum outro papel com o mesmo nome sendo executado pelo agente,
     * retorna o elementId do novo papel, caso contrário, retorna null.
     */
    public ElementID createAgentRoleElementID(String name, boolean isLocal) {
        String name2;
        if (isLocal) {
            name2 = name + "@" + AgentPlatformDescription.getInstance().getName();
        } else {
            name2 = name;
        }
        if (!searchRoleName(name2)) {
            //System.err.println("Teste");
            ElementID aid = new ElementID(name, isLocal);
            return aid;
        }
        return null;
    }

    /**
     * Método responsável por criar a descrição de um papel.
     * @param agentRole
     * Papel que terá sua descrição criada.
     * @param elementId
     * Identificador de papel.
     * @param ownership
     * Proprietário do agente. 
     * @return
     * Valor booleano indicando o sucesso da criação da descrição (true), ou a falha 
     * (false).
     */
    public boolean createAgentRoleDescription(AgentRole agentRole, ElementID elementId, String ownership) {
        if (!searchRoleName(agentRole.getRoleName())) {
            AgentRoleDescription description = new AgentRoleDescription();

            description.setAgentRole(agentRole);
            description.setElementId(elementId);
            description.setOwnership(ownership);
            description.setState(AgentRole.start);

            agentRole.setDescription(description);
            rolesBeingPlayed.add(description);

            return true;
        }
        return false;
    }

    /**
     * Método responsável por atribuir a todos os papéis desempenhados pelo agente um estado de ciclo de vida.
     * @param status
     * Estado atribuído a todos os papéis desempenhados pelo agente.
     */
    public void setStatusAgentRoles(String status) {
        Iterator num = this.rolesBeingPlayed.iterator();
        AgentRole role = null;

        while (num.hasNext()) {
            role = (AgentRole) num.next();

            role.setStatus(AgentRole.death);
        }

    }

    /**
     * Método responsável por enviar uma mensagem para outros agentes e/ou organizações. 
     * Utiliza uma porta default estipulada pelo framework para a comunicação. 
     * @param message
     * Mensagem a ser enviada.
     */
    public void send(Message message) {
        mtp.send(message);
    }

    /**
     * Método responsável por enviar uma mensagem para outros agentes e/ou organizações. Utiliza
     * uma porta fornecida por parâmetro para a comunicação. 
     * @param message
     * Mensagem a ser enviada.
     * @param port
     * Porta usada para realizar a comunicação com um ou mais agentes e/ou organizações. 
     */
    public void send(int port, Message message) {
        mtp.send(port, message);
    }
}
