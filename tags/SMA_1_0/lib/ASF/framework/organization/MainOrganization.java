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
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma organiza��o principal. 
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
     * Conjunto de pap�is de agente desempenhados dentro da organiza��o principal.
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
     * Conjunto de pap�is de objetos desempenhados dentro da organiza��o principal.
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
     * Conjunto de suborganiza��es que fazem parte da organiza��o principal.
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
     * Ambiente no qual a organiza��o principal habita.
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
     * Conjunto de objetivos da organiza��o principal.
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
     * Conjunto de cren�as da organiza��o principal.
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
     * Conjunto de planos da organiza��o principal.
     */
    protected Collection plans = new Vector();
    /**
     * @associates <{Action}>
     * @link association
     * @clientCardinality 0..*
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de a��es da organiza��o principal.
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
     * Conjunto de mensagens recebidas pela organiza��o principal.
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
     * Conjunto de mensagens enviadas pela organiza��o principal.
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
     * Conjunto de axiomas da organiza��o principal.
     */
    protected Collection axioms = new Vector();
    //To simulate STOP a thread
    /**
     * Usado para simular a parada de um thread.
     * True significa que est� parada e false caso contr�rio.
     */
    private boolean threadStopped = false;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Descri��o da organiza��o principal.
     */
    protected Description description;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Identificador da organiza��o principal.
     */
    protected ElementID elementId;
    /**
     * Message Transport Protocol respons�vel por enviar mensagens para outros agentes e/ou organiza��es.
     */
    protected MTP mtp;

    /**
     * Construtor da classe respons�vel por atribuir o ambiente no qual a organiza��o
     * principal habita, e o seu identificador global.
     * @param theEnvironment
     * Ambiente no qual a organiza��o principal habita.
     */
    public MainOrganization(MTS_Environment theEnvironment, ElementID elementId) {
        this.theEnvironment = theEnvironment;
        this.elementId = elementId;

        theEnvironment.registerOrganizations(this);

    }

    /**
     * Fornece o conjunto de pap�is executados pela organiza��o principal.
     * @return
     * Conjunto de pap�is sendo executados pela organiza��o principal.
     */
    public Collection getRolesBeingPlayed() {
        return null;
    }

    /**
     * Fornece o identificador da organiza��o principal.
     * @return
     * Identificador da organiza��o principal.
     */
    public ElementID getOrganizationName() {
        return elementId;
    }

    /**
     * Fornece o ambiente no qual a organiza��o principal habita.
     * @return
     * Ambiente no qual a organiza��o principal habita.
     */
    public MTS_Environment getEnvironment() {
        return theEnvironment;

    }

    /**
     * Fornece o conjunto de objetivos da organiza��o principal.
     * @return
     * Conjunto de objetivos da organiza��o principal.
     */
    public Collection getGoals() {
        return goals;
    }

    /**
     * Fornece o conjunto de cren�as da organiza��o principal.
     * @return
     * Conjunto de cren�as da organiza��o principal.
     */
    public Collection getBeliefs() {
        return beliefs;
    }

    /**
     * Fornece o conjunto de planos da organiza��o principal.
     * @return
     * Conjunto de planos da organiza��o principal.
     */
    public Collection getPlans() {
        return plans;
    }

    /**
     * Fornece o conjunto de a��es da organiza��o principal.
     * @return
     * Conjunto de a��es da organiza��o principal.
     */
    public Collection getActions() {
        return actions;
    }

    /**
     * Fornece o conjunto de mensagens recebidas pela organiza��o principal.
     * @return
     * Conjunto de mensagens recebidas pela organiza��o principal.
     */
    public Collection getInMessages() {
        return inMessages;
    }

    /**
     * Fornece o conjunto de mensagens enviadas pela organiza��o principal.
     * @return
     * Conjunto de mensagens enviadas pela organiza��o principal. 
     */
    public Collection getOutMessages() {
        return outMessages;
    }

    /**
     * Fornece o conjunto de axiomas da organiza��o principal.
     * @return
     * Conjunto de axiomas da organiza��o principal.
     */
    public Collection getAxioms() {
        return axioms;
    }

    /**
     * Fornece o conjunto de pap�is desempenhados dentro da organiza��o principal.
     * @return
     * Conjunto de pap�is desempenhados dentro da organiza��o principal.
     */
    public Collection getAgentRoles() {
        return agentRoles;
    }

    /**
     * Fornece o conjunto de pap�is de objetos desempenhados dentro da 
     * organiza��o principal.
     * @return
     * Conjunto de pap�is de objetos desempenhados dentro da organiza��o 
     * principal.
     */
    public Collection getObjectRoles() {
        return objectRoles;
    }

    /**
     * Fornece o conjunto de suborganiza��es que habitam a organiza��o principal.
     * @return
     * Conjunto de suborganiza��es que habitam a organiza��o principal.
     */
    public Collection getSubOrganizations() {
        return subOrganizations;
    }

    /**
     * Fornece a descri��o da organiza��o.
     * @return
     * Descri��o da organiza��o.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Atribui a descri��o da organiza��o.
     * @param description
     * Descri��o da organiza��o.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Atribui um novo identificador � organiza��o principal.
     * @param newName
     * Novo identificador da organiza��o principal.
     */
    public void setOrganizationName(ElementID elementId) {
        this.elementId = elementId;
    }

    /**
     * Atribui o ambiente no qual a organiza��o principal estar� habitando.
     * @param newEnv
     * Novo ambiente no qual a organiza��o principal estar� habitando. 
     */
    public void setEnvironment(MTS_Environment newEnv) {
        theEnvironment = newEnv;
    }

    /**
     * Atribui um novo objetivo ao conjunto de objetivos da organiza��o principal.
     * @param newGoal
     * Novo objetivo da organiza��o principal.
     */
    public void setGoal(Goal newGoal) {
        goals.add(newGoal);
    }

    /**
     * Atribui uma nova cren�a ao conjunto de cren�as da organiza��o principal.
     * @param newBelief
     * Nova cren�a da organiza��o principal.
     */
    public void setBelief(Belief newBelief) {
        beliefs.add(newBelief);
    }

    /**
     * Atribui um novo plano ao conjunto de planos da organiza��o principal.
     * @param newPlan
     * Novo plano da organiza��o principal.
     */
    public void setPlan(Plan newPlan) {
        plans.add(newPlan);
    }

    /**
     * Atribui uma nova a��o ao conjunto de a��es da organiza��o principal.
     * @param newAction
     * Nova a��o da organiza��o principal.
     */
    public void setAction(Action newAction) {
        actions.add(newAction);
    }

    /**
     * Atribui uma nova mensagem recebida ao conjunto de mensagens recebidas da 
     * organiza��o principal.
     * @param newMessage
     * Nova mensagem recebida da organiza��o principal.
     */
    public void setInMessage(Message newMessage) {
        inMessages.add(newMessage);
    }

    /**
     * Atribui uma nova mensagem enviada ao conjunto de mensagens enviadas da 
     * organiza��o principal.
     * @param newMessage
     * Nova mensagem enviada da organiza��o principal.
     */
    public void setOutMessage(Message newMessage) {
        outMessages.add(newMessage);
    }

    /**
     * Atribui um novo axioma ao conjunto de axiomas da organiza��o principal.
     * @param newAxiom
     * Novo axioma da organiza��o principal.
     */
    public void setAxiom(Axiom newAxiom) {
        axioms.add(newAxiom);
    }

    /**
     * Atribui um novo papel ao conjunto de pap�is desempenhados dentro da 
     * organiza��o principal.
     * @param newAgentRole
     * Novo papel desempenhado dentro da organiza��o principal. 
     */
    public void setAgentRole(AgentRole newAgentRole) {
        agentRoles.add(newAgentRole);
    }

    /**
     * Atribui um novo papel de objeto ao conjunto de pap�is de objeto 
     * desempenhados dentro da organiza��o principal.
     * @param newObjectRole
     * Novo papel de objeto desempenhado dentro da organiza��o principal.
     */
    public void setObjectRole(ObjectRole newObjectRole) {
        objectRoles.add(newObjectRole);
    }

    /**
     * Atribui uma nova sub-organiza��o ao conjunto de suborganiza��es da organiza��o principal. 
     * @param newSubOrg
     * Nova sub-organiza��o da organiza��o principal.
     */
    public void setSubOrganizations(MainOrganization newSubOrg) {
        subOrganizations.add(newSubOrg);
    }

    /**
     * M�todo respons�vel por retirar a associa��o de um papel com a organiza��o
     * principal. 
     * @param role
     * Papel a ser desassociado da organiza��o principal. 
     */
    public void removeAgentRole(AgentRole role) {
        agentRoles.remove(role);
    }

    /**
     * M�todo respons�vel por atribui para a vari�vel booleana que simula o funcionamento de uma thread 
     * o valor true que indica que a mainOrganization est� parada, isto �, n�o
     * est� em processamento.  
     */
    public void stopThread() {
        threadStopped = true;
    }

    /**
     * Fornece um valor booleano indicando se a organiza��o principal (thread)  
     * est� parada ou n�o.
     * @return
     * Valor booleano, indicando se a organiza��o principal est� parada ou n�o.
     * True se est� parada, caso contr�rio ser� false.
     */
    protected boolean checkIfStopped() {
        return threadStopped;
    }

    /**
     * M�todo principal da organiza��o. Este m�todo � executado por todas as 
     * threads da organiza��o.
     * Nele verificasse quais objetivos ainda n�o foram alcan�ados, para que assim
     * sejam escolhidos planos que ser�o respons�veis por executar a��es.
     * Quando um objetivo � alcan�ado, h� a verifica��o se a organiza��o tem mais algum 
     * pendente. Se n�o tiver, ela � destru�da.
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
     * M�todo respons�vel por destruir uma organiza��o. A sua destrui��o � 
     * caracterizada pela parada de todas as threads associadas a organiza��o,
     * pela destrui��o dos pap�is de agente e os objetos de pap�is executados na organiza��o, 
     * al�m do cancelamento do seu registro no ambiente em que estava habitando.
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
     * M�todo abstrato respons�vel por fornecer um plano para alcan�ar o objetivo 
     * passado por par�metro. Al�m do objetivo, o m�todo ainda recebe como par�metro 
     * a lista de todos os planos executados. 
     * @param vPlansExecuted
     * Conjunto de planos para tentar alcan�ar o objetivo.
     * @param goal
     * Objetivo a ser alcan�ado..
     * @return
     * Plano que tentar� alcan�ar o objetivo.
     */
    protected abstract Plan selectingPlan(Collection vPlansExecuted, Goal goal);

    /**
     * M�todo abstrato respons�vel por executar um plano. 
     * @param plan
     * Plano que ser� executado, visando o alcance de um objetivo.
     */
    protected abstract void executingPlan(Plan plan);

    /**
     * M�todo abstrato respons�vel por fornecer um objetivo da organiza��o para 
     * ser alcan�ado.
     * @return
     * Objetivo a ser alcan�ado.
     */
    protected abstract Goal selectingGoalToAchieve();

    /**
     * M�todo abstrato respons�vel por verificar se a organiza��o ir� continuar seu 
     * processamento.
     * @return
     * Valor booleano indicando se a organiza��o continuar� ou n�o o seu processamento.
     */
    protected abstract boolean checkIfWillContinue();

    /**
     * M�todo respons�vel por enviar uma mensagem para outros agentes e/ou organiza��es. 
     * Utiliza uma porta default estipulada pelo framework para a comunica��o. 
     * @param message
     * Mensagem a ser enviada.
     */
    public void send(Message message) {
        mtp.send(message);
    }

    /**
     * M�todo respons�vel por enviar uma mensagem para outros agentes e/ou organiza��es. Utiliza
     * uma porta fornecida por par�metro para a comunica��o. 
     * @param message
     * Mensagem a ser enviada.
     * @param port
     * Porta usada para realizar a comunica��o com um ou mais agentes e/ou organiza��es. 
     */
    public void send(int port, Message message) {
        mtp.send(port, message);
    }
}