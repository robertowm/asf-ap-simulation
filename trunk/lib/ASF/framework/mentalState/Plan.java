package framework.mentalState;

import java.io.Serializable;
import java.util.*;
import framework.agentRole.AgentRole;
import framework.organization.MainOrganization;
import framework.mentalState.goal.Goal;
/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de um plano. 
 */
public abstract class Plan implements Serializable
{
	/**
	 * @associates <{Action}>
	 * @clientCardinality 0..*
	 * @directed true
	 * @link association
	 * @supplierCardinality 1..*
	 */
	/**
	 * Conjunto de a��es visando o alcance de algum objetivo.
	 */
    protected Collection actions = new Vector ();
	/**
	 * @associates <{framework.mentalState.goal.Goal}>
	 * @clientCardinality 1..*
	 * @directed true
	 * @link association
	 * @supplierCardinality 0..*
	 */
	/**
	 * Objetivo do plano.
	 */
    protected Goal goal = null;
	
	/**
	 * Fornece o conjunto de a��es do plano.
	 * @return
	 * Conjunto de a��es do plano.
	 */
	public Collection getActions ()
    {
        return this.actions;

    }
    /**
     * Fornece o objetivo do plano.
     * @return
     * Objetivo do plano.
     */
    public Goal getGoal ()
    {
        return this.goal;

    }
 
	/**
	 * Atribui uma nova a��o ao conjunto de a��es do plano.
	 * @param newAction
	 * Nova a��o do plano.
	 */
	public void setAction (Action newAction)
    {
        this.actions.add (newAction);

    }
    /**
     * Atribui um novo objetivo ao plano.
     * @param newGoal
     * Novo objetivo do plano.
     */
    public void setGoal (Goal newGoal)
    {
        this.goal = newGoal;

    }
    /**
     * M�todo respons�vel por executar as a��es de um plano.
     * @param role
     * Papel de um agente respons�vel por executar as a��es de um plano. 
     */
    public void execute (AgentRole role)
    {
        /* CAN BE REIMPLEMENTED BY THE CONCRETE_PLANS */
        Action actionAux;
        Iterator enumActions = this.actions.iterator();
        while (enumActions.hasNext())
        {
            actionAux = (Action) enumActions.next ();
            actionAux.execute ();
        }
    }
    /**
     * M�todo respons�vel por executar as a��es de um plano.
     * @param organization
     * Organiza��o respons�vel por executar as a��es de um plano.
     */
    public void execute (MainOrganization organization)
    {
        /* CAN BE REIMPLEMENTED BY THE CONCRETE_PLANS */
        Action actionAux;
        Iterator enumActions = this.actions.iterator();
        while (enumActions.hasNext())
        {
            actionAux = (Action) enumActions.next();
            actionAux.execute ();
        }
    }
}

