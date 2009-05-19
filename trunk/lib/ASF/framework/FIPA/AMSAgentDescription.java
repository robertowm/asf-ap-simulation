/*
 * Created on 10/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;

import framework.agent.Agent;

/**
 * Responsável por representar as características estruturais e comportamentais
 * da descrição de um agente.
 */
public class AMSAgentDescription extends Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * Agente da descrição.
	 */
	private Agent agent;
	
	/**
	 * Fornece o agente da descrição.
	 * @return
	 * Agente da descrição.
	 */
	public Agent getAgent() 
	{
		return agent;
	}

	/**
	 * Atribui o agente da descrição.
	 * @param agent
	 * Agente da descrição.
	 */
	public void setAgent( Agent agent ) 
	{
		this.agent = agent;
	}

}
