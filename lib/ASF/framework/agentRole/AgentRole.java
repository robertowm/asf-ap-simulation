package framework.agentRole;

import java.io.Serializable;
import java.util.*;
import framework.mentalState.belief.*;
import framework.mentalState.goal.*;
import framework.FIPA.Description;
import framework.FIPA.ElementID;
import framework.FIPA.StatusAgentRole;
import framework.agent.*;
import framework.organization.*;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de um agentRole (papel). 
 */
public abstract class AgentRole implements StatusAgentRole, Serializable {

    /**
     * Identificador global do papel.
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Identificador do papel.
     */
    protected ElementID elementId;
    /**
     * @associates <{Agent}>
     * @clientCardinality 1..*
     * @directed true
     * @label played_by
     * @link association
     * @supplierCardinality 0..1
     */
    /**
     * Agente que está desempenhando o papel.
     */
    protected Agent agentPlayingRole = null;
    /**
     * @associates <{framework.organization.Organization}>
     * @clientCardinality 1..*
     * @directed true
     * @label played_by
     * @link association
     * @supplierCardinality 0..1
     */
    /**
     * Organização que está desempenhando o papel.
     */
    protected Organization organizationPlayingRole = null;
    /**
     * @associates <{framework.organization.MainOrganization}>
     * @clientCardinality 0..*
     * @directed true
     * @label owned_by
     * @link association
     * @supplierCardinality 1
     */
    /**
     * Organização que define o papel.
     */
    protected MainOrganization owner = null;
    /**
     * @associates <{Goal}>
     * @clientCardinality 1..*
     * @directed true
     * @link association
     * @supplierCardinality 1..*
     */
    /**
     * Conjunto de objetivos a serem alcançados pelo papel.
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
     * Conjunto de crenças do papel. 
     */
    protected Collection beliefs = new Vector();
    /**
     * @associates <{framework.agentRole.Duty}>
     * @clientCardinality 1..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de deveres do papel.
     */
    protected Collection duties = new Vector();
    /**
     * @associates <{framework.agentRole.Right}>
     * @clientCardinality 1..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de direitos do papel.
     */
    protected Collection rights = new Vector();
    /**
     * @associates <{framework.agentRole.Protocol}>
     * @clientCardinality 1..*
     * @directed true
     * @link association
     * @supplierCardinality 0..*
     */
    /**
     * Conjunto de protocolos do papel. 
     */
    protected Collection protocols = new Vector();
    //To simulate STOP a thread
    /**
     * Responsável por simular a parada de uma thread em execução. Este conceito de 
     * parada está relacionado ao ciclo de vida de um agente.
     */
    private boolean threadStopped = false;
    //To simulate SUSPEND a thread
    /**
     * Responsável por simular a suspensão de uma thread em execução.
     * O conceito de suspensão está relacionado ao ciclo de vida de um agente.  
     */
    private boolean threadSuspended = false;
    /**
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Descrição do papel.
     */
    protected Description description;
    protected String status;

    protected AgentRole() {
        this.status = AgentRole.start;
    }

    /**
     * Fornece o agente que está desempenhando o papel.
     * @return
     * Agente que está desempenhando o papel.
     */
    public Agent getAgentPlayingRole() {
        return this.agentPlayingRole;
    }

    /**
     * Fornece a organização que está desempenhando o papel.
     * @return
     * Organização que está desempenhando o papel.
     */
    public MainOrganization getOrganizationPlayingRole() {
        return this.organizationPlayingRole;
    }

    /**
     * Fornece o nome do papel.
     * @return
     * Nome do papel.
     */
    public String getRoleName() {
        if (elementId == null) {
            throw new RuntimeException("Unknown ElementID");
        }
        return elementId.getName();
    }

    /**
     * Fornece a organização que define o papel. 
     * @return
     * Organização que define o papel.
     */
    public MainOrganization getOwner() {
        return this.owner;
    }

    /**
     * Fornece os objetivos do papel.
     * @return
     * Todos os objetivos do papel.
     */
    public Collection getGoals() {
        return this.goals;

    }

    /**
     * Fornece as crenças do papel.
     * @return
     * Todas as crenças do papel.
     */
    public Collection getBeliefs() {
        return this.beliefs;

    }

    /**
     * Fornece os deveres do papel.
     * @return
     * Todos os deveres do papel.
     */
    public Collection getDuties() {
        return this.duties;

    }

    /**
     * Fornece os direitos do papel.
     * @return
     * Todos os direitos do papel.
     */
    public Collection getRights() {
        return this.rights;

    }

    /**
     * Fornece os protocolos do papel.
     * @return
     * Todos os protocolos do papel.
     */
    public Collection getProtocols() {
        return this.protocols;

    }

    /**
     * Fornece a descrição do papel.
     * @return
     * Descrição do papel.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Atribui o agente que estará desempenhando o papel.
     * @param newAgent
     * Agente que estará desempenhando o papel.
     */
    public void setAgent(Agent newAgent) {
        this.agentPlayingRole = newAgent;
    }

    /**
     * Atribui a organização que estará desempenhando o papel.
     * @param newOrganization
     * Organização que estará desempenhando o papel. 
     */
    public void setOrganization(Organization newOrganization) {
        this.organizationPlayingRole = newOrganization;

    }

    /**
     * Atribui o identificador do papel.
     * @param name
     * Identificador do papel.
     */
    public void setRoleName(ElementID elementId) {
        this.elementId = elementId;
    }

    /**
     * Atribui a organização que define o papel.
     * @param newOwner
     * Organização que define o papel.
     */
    public void setOwner(MainOrganization newOwner) {
        this.owner = newOwner;

    }

    /**
     * Atribui um novo objetivo ao conjunto de objetivos do papel.
     * @param newGoal
     * Novo objetivo do papel.
     */
    public void setGoal(Goal newGoal) {
        this.goals.add(newGoal);

    }

    /**
     * Atribui uma nova crença ao conjunto de crenças do papel.
     * @param newBelief
     * Nova crença do papel. 
     */
    public void setBelief(Belief newBelief) {
        this.beliefs.add(newBelief);

    }

    /**
     * Atribui um novo dever ao conjunto de deveres do papel. 
     * @param newDuty
     * Novo dever do papel.
     */
    public void setDuty(Duty newDuty) {
        this.duties.add(newDuty);

    }

    /**
     * Atribui um novo direito ao conjunto de direitos do papel.
     * @param newRight
     * Novo direito do papel.
     */
    public void setRight(Right newRight) {
        this.rights.add(newRight);

    }

    /**
     * Atribui um novo protocolo ao conjunto de protocolos do papel.
     * @param newProtocol
     * Novo protocolo do papel.
     */
    public void setProtocol(Protocol newProtocol) {
        this.protocols.add(newProtocol);

    }

    /**
     * Atribui a descrição do papel.
     * @param description
     * Descrição do papel.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Fornece um valor do tipo boolean, indicando se a thread associada ao papel 
     * está em execução ou se está parada.
     * @return
     * Um valor booleano, indicando se a thread está parada ou não. 
     */
    public boolean threadStopped() {
        return threadStopped;
    }

    /**
     * Atribui o valor "true" a variável que simula a parada da thread associada
     * ao papel. Com essa atribuição há a indicação de que ela está parada.
     */
    public synchronized void stopThread() {
        threadStopped = true;
    }

    public synchronized void startThread() {
        threadStopped = false;
    }

    /**
     * Fornece um valor booleano indicando se a thread (agente) associada ao papel 
     * foi suspensa ou não.
     * @return
     * Um valor booleano indicando se o agente que estava executando
     * o papel foi suspenso ou não.
     */
    public boolean threadSuspended() {
        return threadSuspended;
    }

    /**
     * Atribui o valor "true" a variável que simula a suspensão da thread associada
     * ao papel. Com essa atribuição há a indicação de que ela foi suspensa.
     */
    public synchronized void supendThread() {
        threadSuspended = true;
    }

    /**
     * Atribui o valor "false" a variável que simula a suspensão da thread associada
     * ao papel. Com essa atribuição há a indicação de que ela não está mais suspensa.
     */
    public synchronized void resumeThread() {
        threadSuspended = false;
    }

    /**
     * Método responsável por destruir um papel. A destruição de um papel é 
     * caracterizada pelas desassociações do agente e da organização que a estavam
     * executando.
     */
    public void destroy() {
        //agent playing role
        setAgent(null);
        //organization playing role
        setOrganization(null);

        //deleting role of the organization where it was being played
        MainOrganization organization = getOwner();
        Vector vRoles = (Vector) organization.getAgentRoles();
        Enumeration enumvRoles = vRoles.elements();
        while (enumvRoles.hasMoreElements()) {
            AgentRole roleAux = (AgentRole) enumvRoles.nextElement();
            if (roleAux == this) {
                vRoles.remove(roleAux);
            }
        }

        this.status = AgentRole.death;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}


