/*
 * Created on Jun 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import framework.FIPA.ElementID;
import framework.FIPA.Description;
import framework.FIPA.StatusMainOrganizationandEnvironment;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import framework.organization.MainOrganization;

/**
 * @author andrew
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class MTS_Environment implements StatusMainOrganizationandEnvironment, Serializable {

    /**
     * @associates <{framework.organization.MainOrganization}>
     * @clientCardinality 1
     * @label inhabits
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de agentes que habitam o ambiente.
     */
    protected Collection agents = new ArrayList();
    /**
     * @associates <{framework.organization.MainOrganization}>
     * @clientCardinality 1
     * @clientRole habitat
     * @label inhabited_by
     * @link association
     * @supplierCardinality 1..*
     * @supplierRole member
     */
    /**
     * Conjunto de organizações que habitam o ambiente.
     */
    protected Collection organizations = new ArrayList();
    /**
     * @associates <{Object}>
     * @link association
     * @label inhabited_by
     * @clientRole habitat
     * @clientCardinality 1
     * @supplierRole member
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de objetos que habitam o ambiente.
     */
    protected Collection objects = new ArrayList();
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Descrição do ambiente. 
     */
    protected Description description;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Identificador do ambiente.
     */
    protected ElementID elementId;

    /**
     * Construtor da classe que atribui o identificador do ambiente.
     * @param elementId
     * Identificador do ambiente.
     */
    protected MTS_Environment(ElementID elementId) {
        this.elementId = elementId;
    }

    /**
     * Fornece o nome do ambiente.
     * @return
     * Nome do ambiente.
     */
    public synchronized String getEnvironmentName() {
        return elementId.getName();
    }

    /**
     * Fornece o conjunto de agentes do ambiente.
     * @return
     * Todos os agentes do ambiente.
     */
    public synchronized Collection getAgents() {
        return this.agents;

    }

    /**
     * Fornece o conjunto de organizações do ambiente.
     * @return
     * Todas as organizações do ambiente.
     */
    public synchronized Collection getOrganizations() {
        return this.organizations;

    }

    /**
     * Fornece o conjunto de objetos do ambiente.
     * @return
     * Todos os objetos que habitam o ambiente.
     */
    public synchronized Collection getObjects() {
        return this.objects;
    }

    /**
     * Fornece a descrição do ambiente.
     * @return
     * Descrição do ambiente.
     */
    public synchronized Description getDescription() {
        return description;
    }

    /**
     * Atribui a descrição do ambiente.
     * @param description
     * Descrição do ambiente.
     */
    public synchronized void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Atribui o identificador do ambiente.
     * @param id
     * Novo identificador do ambiente.
     */
    public synchronized void setEnvironmentName(ElementID id) {
        this.elementId = id;
    }

    /**
     * Atribui um novo agente ao conjunto de agentes do ambiente, realizando assim
     * o seu registro.
     * @param newAgent
     * Novo agente do ambiente.
     */
    public synchronized void registerAgents(Agent newAgent) {
        this.agents.add(newAgent);
    }

    /**
     * Atribui uma nova organização ao conjunto de organizações do ambiente, realizando 
     * assim o seu registro. 
     * @param newOrg
     * Nova organização do ambiente.
     */
    public synchronized void registerOrganizations(MainOrganization newOrg) {
        this.organizations.add(newOrg);

    }

    /**
     * Atribui um novo objeto ao conjunto de objetos do ambiente, realizando assim
     * o seu registro.
     * @param newObj
     * Novo objeto do ambiente.
     */
    public synchronized void registerObjects(Object newObj) {
        this.objects.add(newObj);
    }

    /**
     * Método responsável por executar a troca de mensagens entre agentes e organizações. 
     * Este recebe uma mensagem por parâmetro 
     * e tem como responsabilidade entregá-la ao receptor pré-definido 
     * nela. 
     * A identificação do receptor é feita através do nome que cada um possui. 
     * Além do envio de mensagens, este método pode ser utilizado para parar ou 
     * suspender uma thread (agente ou organização). É a mensagem recebida por 
     * parâmetro de entrada que define se ela deve ser enviada ou se ela deve ser 
     * utilizada para parar ou suspender um thread.
     *
     * @param msg
     * Mensagem a ser tratada pelo ambiente.
     * @return
     * Conjunto de identificadores dos agentes e/ou organizações que receberam a 
     * mensagem de forma adequada. 
     */
    public synchronized Collection sendMessage(Message msg) {
        ElementID theReceiver;
        List listReceiver = msg.getTo();
        //System.err.println("Lista: " + listReceiver);
        Iterator iReceiver = listReceiver.iterator();
        Collection receivers = new ArrayList();

        //System.err.println("Tamanho: "+ ((ElementID)msg.getTo().get(0)).getAddresses().size());
        while (iReceiver.hasNext()) {
            theReceiver = (ElementID) iReceiver.next();

            //Sending messages to organizations
            Iterator enumOrg = this.organizations.iterator();
            while (enumOrg.hasNext()) {
                MainOrganization theOrg = (MainOrganization) enumOrg.next();

                if (theReceiver.getName().compareTo(theOrg.getOrganizationName().getName()) == 0) {
                    //System.err.println(theReceiver.getName() + " recebe mensagem para ser processada.");

                    receivers.add(theReceiver);

                    if (msg.getPerformative().equals("inform:stop")) {
                        Collection vRoles = theOrg.getRolesBeingPlayed();
                        if (vRoles != null) {
                            Iterator enumvRoles = vRoles.iterator();
                            while (enumvRoles.hasNext()) {
                                AgentRole role = (AgentRole) enumvRoles.next();
                                if (msg.getConversationId().startsWith(role.getRoleName())) {
                                    role.stopThread();
                                }
                            }
                        } else {
                            theOrg.stopThread();
                        }
                    } else {
                        if (msg.getPerformative().equals("inform:suspend")) {
                            Collection vRoles = theOrg.getRolesBeingPlayed();
                            //Suspend cannot occur in main-organizations
                            Iterator enumvRoles = vRoles.iterator();
                            while (enumvRoles.hasNext()) {
                                AgentRole role = (AgentRole) enumvRoles.next();
                                if (msg.getConversationId().startsWith(role.getRoleName())) {
                                    role.supendThread();
                                }
                            }
                        } else {
                            if (msg.getPerformative().equals("inform:resume")) {
                                Collection vRoles = theOrg.getRolesBeingPlayed();
                                //Resume cannot occur in main-organizations
                                Iterator enumvRoles = vRoles.iterator();
                                while (enumvRoles.hasNext()) {
                                    AgentRole role = (AgentRole) enumvRoles.next();
                                    if (msg.getConversationId().startsWith(role.getRoleName())) {
                                        role.resumeThread();
                                    }
                                }
                            } else if (!verifyPerformative(msg)) {
                                theOrg.setInMessage(msg);
                            }
                        }
                    }
                }
            }

            //Sending messages to agents
            //Iterator enumAgent = getAgents().iterator();
            Agent theAgent;
            ArrayList agents = (ArrayList) getAgents();
            int num = 0;
            //while (enumAgent.hasNext())
            while (num < agents.size()) {
                theAgent = (Agent) agents.get(num);

                if (theReceiver.getName().compareTo(theAgent.getAgentName().getName()) == 0) {
                    //System.err.println(theReceiver.getName() + " recebe mensagem para ser processada.");
                    receivers.add(theReceiver);
                    if (msg.getPerformative().equals("inform:stop")) {
                        Collection vRoles = theAgent.getRolesBeingPlayed();
                        Iterator enumvRoles = vRoles.iterator();
                        while (enumvRoles.hasNext()) {
                            AgentRole role = (AgentRole) enumvRoles.next();
                            if (msg.getConversationId().startsWith(role.getRoleName())) {
                                role.stopThread();
                            }
                        }
                    } else {
                        if (msg.getPerformative().equals("inform:suspend")) {
                            Collection vRoles = theAgent.getRolesBeingPlayed();
                            Iterator enumvRoles = vRoles.iterator();
                            while (enumvRoles.hasNext()) {
                                AgentRole role = (AgentRole) enumvRoles.next();
                                if (msg.getConversationId().startsWith(role.getRoleName())) {
                                    role.supendThread();
                                }
                            }
                        } else {
                            if (msg.getPerformative().equals("inform:resume")) {
                                Collection vRoles = theAgent.getRolesBeingPlayed();
                                Iterator enumvRoles = vRoles.iterator();
                                while (enumvRoles.hasNext()) {
                                    AgentRole role = (AgentRole) enumvRoles.next();
                                    if (msg.getConversationId().startsWith(role.getRoleName())) {
                                        role.resumeThread();
                                    }
                                }
                            } else if (!verifyPerformative(msg)) {
                                theAgent.setInMessage(msg);
                            }
                        }
                    }
                }
                num++;
            }


        }
        return receivers;
    }

    /**
     * Método responsável por verificar se a mensagem passada por parâmetro
     * possui alguma performativa que indique que ela é a resposta de uma 
     * trasmissão de mensagem. 
     * @param msg
     * Mensagem que terá sua performativa verificada.
     * @return
     * Indica que a mensagem é uma resposta de uma outra anteriormente enviada.
     */
    private synchronized boolean verifyPerformative(Message msg) {
        boolean result = false;

        //Ocorreu sucesso na transmissão.
        if (msg.getPerformative().equals("inform:sucess") ||
                msg.getPerformative().equals("inform:fail")) {
            System.err.println(msg.getComments());
            result = true;
        }
        return result;
    }

    /**
     * Método responsável por cancelar o registro de uma organização que habita um ambiente.
     * @param org
     * Organização a ser removida do conjunto de organizações que habitam
     * o ambiente.
     */
    public synchronized void cancelOrganizationRegister(MainOrganization org) {
        /*
        Collection vOrganization = getOrganizations();
        Iterator enumvOrganization = vOrganization.iterator();
        while (enumvOrganization.hasNext()) {			
        MainOrganization orgAux = (MainOrganization)enumvOrganization.next();
        
        System.err.println(orgAux.getOrganizationName().getName());
        
        if (orgAux == org);
        vOrganization.remove(org);
        
        }
         */

        ArrayList vOrganization = (ArrayList) getOrganizations();


        int num = 0;
        while (num < vOrganization.size()) {
            MainOrganization orgAux = (MainOrganization) vOrganization.get(num);

            if (orgAux == org);
            vOrganization.remove(org);
            num++;

        }

    }

    /**
     * Método responsável por cancelar o registro de um agente que habita um ambiente.
     * @param agent
     * Agente a ser removido do conjunto de agentes do ambiente.
     */
    public synchronized void cancelAgentRegister(Agent agent) {

        this.agents.remove(agent);

    /*
    Collection vAgents = getAgents();
    Iterator enumvAgents = vAgents.iterator();
    while (enumvAgents.hasNext()) {
    Agent agentAux = (Agent)enumvAgents.next();
    if (agentAux == agent)
    vAgents.remove(agent);
    }
     */
    }

    /**
     * Método responsável por cancelar o registro de um objeto que habita o ambiente.
     * @param object
     * Objeto a ser removido do conjunto de objetos do ambiente.
     */
    public synchronized void cancelObjectRegister(Object object) {
        Collection vObject = getObjects();
        Iterator enumvObj = vObject.iterator();
        while (enumvObj.hasNext()) {
            Object objAux = enumvObj.next();
            if (objAux == object) {
                vObject.remove(object);
            }
        }
    }

    /**
     * Método responsável por destruir o ambiente, através da desassociação
     * de todos aqueles que habitam o ambiente. 
     * Os desassociados são os seguintes: agentes, organizações e objetos.
     */
    public synchronized void destroy() {
        //Destroying the agents
        Collection vAgents = getAgents();
        if (vAgents != null) {
            Iterator enumvAgents = vAgents.iterator();
            while (enumvAgents.hasNext()) {
                Agent agentAux = (Agent) enumvAgents.next();
                agentAux.destroy();
            }

        }
        //Destroying the organizations
        Collection vOrganizations = getOrganizations();
        if (vOrganizations != null) {
            Iterator enumvOrg = vOrganizations.iterator();
            while (enumvOrg.hasNext()) {
                MainOrganization orgAux = (MainOrganization) enumvOrg.next();
                orgAux.destroy();
            }

        }
        //Destroying the objects
        Collection vObjects = getObjects();
        if (vObjects != null) {
            Iterator enumvObjects = vObjects.iterator();
            while (enumvObjects.hasNext()) {
                Object objAux = enumvObjects.next();
                cancelObjectRegister(objAux);
            }
        }
    }
}
