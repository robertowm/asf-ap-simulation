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
 * Responsável por representar as características estruturais e comportamentais
 * da descrição de um papel.
 */
public class AgentRoleDescription extends Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Papel da descrição.
	 */
	private AgentRole role;

	/**
	 * Fornece o papel da descrição.
	 * @return
	 * Papel da descrição.
	 */
	public AgentRole getAgentRole() 
	{
		return role;
	}

	/**
	 * Atribui o papel da descrição.
	 * @param agent
	 * Papel da descrição.
	 */
	public void setAgentRole( AgentRole role ) 
	{
		this.role = role;
	}
}
