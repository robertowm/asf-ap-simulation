/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;

import framework.agentRole.AgentRole;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * da descri��o de um papel.
 */
public class AgentRoleDescription extends Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Papel da descri��o.
	 */
	private AgentRole role;

	/**
	 * Fornece o papel da descri��o.
	 * @return
	 * Papel da descri��o.
	 */
	public AgentRole getAgentRole() 
	{
		return role;
	}

	/**
	 * Atribui o papel da descri��o.
	 * @param agent
	 * Papel da descri��o.
	 */
	public void setAgentRole( AgentRole role ) 
	{
		this.role = role;
	}
}
