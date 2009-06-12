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
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * da descri��o de um agente.
 */
public class AMSAgentDescription extends Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * Agente da descri��o.
	 */
	private Agent agent;
	
	/**
	 * Fornece o agente da descri��o.
	 * @return
	 * Agente da descri��o.
	 */
	public Agent getAgent() 
	{
		return agent;
	}

	/**
	 * Atribui o agente da descri��o.
	 * @param agent
	 * Agente da descri��o.
	 */
	public void setAgent( Agent agent ) 
	{
		this.agent = agent;
	}

}
