package framework.organization;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import framework.FIPA.ElementID;
import framework.FIPA.Description;
import framework.FIPA.StatusMainOrganizationandEnvironment;
import framework.FIPA.communication.MTP;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.mentalState.Action;
import framework.mentalState.Message;
import framework.mentalState.Plan;
import framework.mentalState.belief.Belief;
import framework.mentalState.goal.Goal;
import framework.objectRole.ObjectRole;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de uma organização principal. 
 */
public abstract class MainOrganization implements StatusMainOrganizationandEnvironment, Runnable, Serializable {

    /**
     * @associates <{AgentRole}>
     * @clientCardinality 1
     * @clientRole owner
     * @directed true
     * @label owns
     * @link association
     * @supplierCardinality 0..*
     * @supplierRole member
     * @undirected 
     */
    /**
     * Conjunto de papéis de agente desempenhados dentro da organização principal.
     */
    protected Collection agentRoles = new Vector();
    /**
     * @associates <{ObjectRole}>
     * @clientCardinality 1
     * @clientRole owner
     * @directed true
     * @label owns
     * @link association
     * @supplierCardinality 0..*
     * @supplierRole member
     * @undirected 
     */
    /**
     * Conjunto de papéis de objetos desempenhados dentro da organização principal.
     */
    protected Collection objectRoles = new Vector();
    /**
     * @associates <{framework.organization.Organization}>
     * @link association
     * @clientCardinality 1..*
     * @supplierRole sub_organization
     * @supplierCardinality 0..*
     * @undirected
     */
    /**
     * Conjunto de suborganizações que fazem parte da organização principal.
     */
    protected Collection subOrganizations = new Vector();
    /**
     * @associates <{Environment}>
     * @link association
     * @label inhabits
     * @clientCardinality 1..*
     * @supplierCardinality 1
     * @undirected
     */
    /**
     * Ambiente no qual a organização principal habita.
     */
    protected MTS_Environment theEnvironment = null;
    /**
     * @associates <{Goal}>
     * @clientCardinality 0..*
     * @directed true
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de objetivos da organização principal.
     */
    protected Collection goals = new Vector();
    /**
     * @associates <{Belief}>
     * @clientCardinality 0..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de crenças da organização principal.
     */
    protected Collection beliefs = new Vector();
    /**
     * @associates <{Plan}>
     * @clientCardinality 0..*
     * @directed true
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de planos da organização principal.
     */
    protected Collection plans = new Vector();
    /**
     * @associates <{Action}>
     * @link association
     * @clientCardinality 0..*
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de ações da organização principal.
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
     * Conjunto de mensagens recebidas pela organização principal.
     */
    protected Collection inMessages = new Vector();
    /**
     * @associates <{framework.mentalState.Message}>
     * @clientCardinality 0..*
     * @clientRole sender
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     * @supplierRole outMsg
     */
    /**
     * Conjunto de mensagens enviadas pela organização principal.
     */
    protected Collection outMessages = new Vector();
    /**
     * @associates <{Axiom}>
     * @clientCardinality 1..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de axiomas da organização principal.
     */
    protected Collection axioms = new Vector();
    //To simulate STOP a thread
    /**
     * Usado para simular a parada de um thread.
     * True significa que está parada e false caso contrário.
     */
    private boolean threadStopped = false;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Descrição da organização principal.
     */
    protected Description description;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Identificador da organização principal.
     */
    protected ElementID elementId;
    /**
     * Message Transport Protocol responsável por enviar mensagens para outros agentes e/ou organizações.
     */
    protected MTP mtp;

    /**
     * Construtor da classe responsável por atribuir o ambiente no qual a organização
     * principal habita, e o seu identificador global.
     * @param theEnvironment
     * Ambiente no qual a organização principal habita.
     */
    public MainOrganization(MTS_Environment theEnvironment, ElementID elementId) {
        this.theEnvironment = theEnvironment;
        this.elementId = elementId;

        theEnvironment.registerOrganizations(this);

    }

    /**
     * Fornece o conjunto de papéis executados pela organização principal.
     * @return
     * Conjunto de papéis sendo executados pela organização principal.
     */
    public Collection getRolesBeingPlayed() {
        return null;
    }

    /**
     * Fornece o identificador da organização principal.
     * @return
     * Identificador da organização principal.
     */
    public ElementID getOrganizationName() {
        return elementId;
    }

    /**
     * Fornece o ambiente no qual a organização principal habita.
     * @return
     * Ambiente no qual a organização principal habita.
     */
    public MTS_Environment getEnvironment() {
        return theEnvironment;

    }

    /**
     * Fornece o conjunto de objetivos da organização principal.
     * @return
     * Conjunto de objetivos da organização principal.
     */
    public Collection getGoals() {
        return goals;
    }

    /**
     * Fornece o conjunto de crenças da organização principal.
     * @return
     * Conjunto de crenças da organização principal.
     */
    public Collection getBeliefs() {
        return beliefs;
    }

    /**
     * Fornece o conjunto de planos da organização principal.
     * @return
     * Conjunto de planos da organização principal.
     */
    public Collection getPlans() {
        return plans;
    }

    /**
     * Fornece o conjunto de ações da organização principal.
     * @return
     * Conjunto de ações da organização principal.
     */
    public Collection getActions() {
        return actions;
    }

    /**
     * Fornece o conjunto de mensagens recebidas pela organização principal.
     * @return
     * Conjunto de mensagens recebidas pela organização principal.
     */
    public Collection getInMessages() {
        return inMessages;
    }

    /**
     * Fornece o conjunto de mensagens enviadas pela organização principal.
     * @return
     * Conjunto de mensagens enviadas pela organização principal. 
     */
    public Collection getOutMessages() {
        return outMessages;
    }

    /**
     * Fornece o conjunto de axiomas da organização principal.
     * @return
     * Conjunto de axiomas da organização principal.
     */
    public Collection getAxioms() {
        return axioms;
    }

    /**
     * Fornece o conjunto de papéis desempenhados dentro da organização principal.
     * @return
     * Conjunto de papéis desempenhados dentro da organização principal.
     */
    public Collection getAgentRoles() {
        return agentRoles;
    }

    /**
     * Fornece o conjunto de papéis de objetos desempenhados dentro da 
     * organização principal.
     * @return
     * Conjunto de papéis de objetos desempenhados dentro da organização 
     * principal.
     */
    public Collection getObjectRoles() {
        return objectRoles;
    }

    /**
     * Fornece o conjunto de suborganizações que habitam a organização principal.
     * @return
     * Conjunto de suborganizações que habitam a organização principal.
     */
    public Collection getSubOrganizations() {
        return subOrganizations;
    }

    /**
     * Fornece a descrição da organização.
     * @return
     * Descrição da organização.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Atribui a descrição da organização.
     * @param description
     * Descrição da organização.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Atribui um novo identificador à organização principal.
     * @param newName
     * Novo identificador da organização principal.
     */
    public void setOrganizationName(ElementID elementId) {
        this.elementId = elementId;
    }

    /**
     * Atribui o ambiente no qual a organização principal estará habitando.
     * @param newEnv
     * Novo ambiente no qual a organização principal estará habitando. 
     */
    public void setEnvironment(MTS_Environment newEnv) {
        theEnvironment = newEnv;
    }

    /**
     * Atribui um novo objetivo ao conjunto de objetivos da organização principal.
     * @param newGoal
     * Novo objetivo da organização principal.
     */
    public void setGoal(Goal newGoal) {
        goals.add(newGoal);
    }

    /**
     * Atribui uma nova crença ao conjunto de crenças da organização principal.
     * @param newBelief
     * Nova crença da organização principal.
     */
    public void setBelief(Belief newBelief) {
        beliefs.add(newBelief);
    }

    /**
     * Atribui um novo plano ao conjunto de planos da organização principal.
     * @param newPlan
     * Novo plano da organização principal.
     */
    public void setPlan(Plan newPlan) {
        plans.add(newPlan);
    }

    /**
     * Atribui uma nova ação ao conjunto de ações da organização principal.
     * @param newAction
     * Nova ação da organização principal.
     */
    public void setAction(Action newAction) {
        actions.add(newAction);
    }

    /**
     * Atribui uma nova mensagem recebida ao conjunto de mensagens recebidas da 
     * organização principal.
     * @param newMessage
     * Nova mensagem recebida da organização principal.
     */
    public void setInMessage(Message newMessage) {
        inMessages.add(newMessage);
    }

    /**
     * Atribui uma nova mensagem enviada ao conjunto de mensagens enviadas da 
     * organização principal.
     * @param newMessage
     * Nova mensagem enviada da organização principal.
     */
    public void setOutMessage(Message newMessage) {
        outMessages.add(newMessage);
    }

    /**
     * Atribui um novo axioma ao conjunto de axiomas da organização principal.
     * @param newAxiom
     * Novo axioma da organização principal.
     */
    public void setAxiom(Axiom newAxiom) {
        axioms.add(newAxiom);
    }

    /**
     * Atribui um novo papel ao conjunto de papéis desempenhados dentro da 
     * organização principal.
     * @param newAgentRole
     * Novo papel desempenhado dentro da organização principal. 
     */
    public void setAgentRole(AgentRole newAgentRole) {
        agentRoles.add(newAgentRole);
    }

    /**
     * Atribui um novo papel de objeto ao conjunto de papéis de objeto 
     * desempenhados dentro da organização principal.
     * @param newObjectRole
     * Novo papel de objeto desempenhado dentro da organização principal.
     */
    public void setObjectRole(ObjectRole newObjectRole) {
        objectRoles.add(newObjectRole);
    }

    /**
     * Atribui uma nova sub-organização ao conjunto de suborganizações da organização principal. 
     * @param newSubOrg
     * Nova sub-organização da organização principal.
     */
    public void setSubOrganizations(MainOrganization newSubOrg) {
        subOrganizations.add(newSubOrg);
    }

    /**
     * Método responsável por retirar a associação de um papel com a organização
     * principal. 
     * @param role
     * Papel a ser desassociado da organização principal. 
     */
    public void removeAgentRole(AgentRole role) {
        agentRoles.remove(role);
    }

    /**
     * Método responsável por atribui para a variável booleana que simula o funcionamento de uma thread 
     * o valor true que indica que a mainOrganization está parada, isto é, não
     * está em processamento.  
     */
    public void stopThread() {
        threadStopped = true;
    }

    /**
     * Fornece um valor booleano indicando se a organização principal (thread)  
     * está parada ou não.
     * @return
     * Valor booleano, indicando se a organização principal está parada ou não.
     * True se está parada, caso contrário será false.
     */
    protected boolean checkIfStopped() {
        return threadStopped;
    }

    /**
     * Método principal da organização. Este método é executado por todas as 
     * threads da organização.
     * Nele verificasse quais objetivos ainda não foram alcançados, para que assim
     * sejam escolhidos planos que serão responsáveis por executar ações.
     * Quando um objetivo é alcançado, há a verificação se a organização tem mais algum 
     * pendente. Se não tiver, ela é destruída.
     */
    public void run() {
        System.out.println("-----> Organization " + Thread.currentThread().getName() + " beginning its execution <-----");
        description.setState(MainOrganization.running);

        Collection vPlansExecuted = new Vector();
        boolean continueExecution = true;
        //Checking if thread was stopped
        while (continueExecution && !checkIfStopped()) {
            //Selecting goal to be achieved
            Goal goal = selectingGoalToAchieve();

            //Checking if thread was stopped
            while (goal != null) {
                //Selecting plan to be executed
                Plan plan = selectingPlan(vPlansExecuted, goal);

                //Checking if thread was stopped
                while (plan != null) {
                    //Executing plan
                    executingPlan(plan);

                    if (checkIfStopped()) {
                        break;
                    }
                    vPlansExecuted.add(plan);
                    if (!goal.getAchieved()) //Selecting another plan
                    {
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
                if (checkIfStopped()) {
                    break;
                }
                goal = selectingGoalToAchieve();
                vPlansExecuted.clear();
            }
            //There is not any other goal to be achieved:
            //all goals where achieved or
            //or the agent tried to achieve all goals.
            if (checkIfStopped()) {
                break;
            }
            continueExecution = checkIfWillContinue();
        }
        /* The thread was stopped */
        destroy();
        description.setState(MainOrganization.death);
    }

    /**
     * Método responsável por destruir uma organização. A sua destruição é 
     * caracterizada pela parada de todas as threads associadas a organização,
     * pela destruição dos papéis de agente e os objetos de papéis executados na organização, 
     * além do cancelamento do seu registro no ambiente em que estava habitando.
     */
    public void destroy() {
        //Destroying the agent roles played in the organization
        //The thread of the entity playing the role must be stopped
        Collection vRoles = getAgentRoles();
        Iterator enumvRoles = vRoles.iterator();

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

    /**
     * Método abstrato responsável por fornecer um plano para alcançar o objetivo 
     * passado por parâmetro. Além do objetivo, o método ainda recebe como parâmetro 
     * a lista de todos os planos executados. 
     * @param vPlansExecuted
     * Conjunto de planos para tentar alcançar o objetivo.
     * @param goal
     * Objetivo a ser alcançado..
     * @return
     * Plano que tentará alcançar o objetivo.
     */
    protected abstract Plan selectingPlan(Collection vPlansExecuted, Goal goal);

    /**
     * Método abstrato responsável por executar um plano. 
     * @param plan
     * Plano que será executado, visando o alcance de um objetivo.
     */
    protected abstract void executingPlan(Plan plan);

    /**
     * Método abstrato responsável por fornecer um objetivo da organização para 
     * ser alcançado.
     * @return
     * Objetivo a ser alcançado.
     */
    protected abstract Goal selectingGoalToAchieve();

    /**
     * Método abstrato responsável por verificar se a organização irá continuar seu 
     * processamento.
     * @return
     * Valor booleano indicando se a organização continuará ou não o seu processamento.
     */
    protected abstract boolean checkIfWillContinue();

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