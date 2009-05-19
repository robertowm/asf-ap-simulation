/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;

/**
 * Classe abstrata responsável por representar as características estruturais 
 * e comportamentais de uma descrição.
 */
public abstract class Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * ElementId de um agente, de uma organização, de um ambiente ou de um papel.
	 */
	protected ElementID elementId = null;
	
	/**
	 * Proprietário de um agente, de uma organização, de um ambiente ou de um papel
	 */
	protected String ownership = null;
	
	/**
	 * Estado do agente, da organização, do ambiente ou do papel associado(a) à descrição.
	 */
	protected String state = null;

	/**
	 * Fornece o elementId do agente, da organização, do papel ou do ambiente associado(a) à descrição.
	 * @return
	 * ElementId do agente, da organização, do papel ou do ambiente associado(a) à descrição.
	 */
	public ElementID getElementId() 
	{
		return elementId;
	}

	/**
	 * Fornece o proprietário do agente, da organização, do papel ou do ambiente associado(a) à 
	 * descrição.
	 * @return
	 * Proprietário do agente, da organização, do papel ou do ambiente associado(a) à descrição.
	 */
	public String getOwnership() 
	{
		return ownership;
	}

	/**
	 * Fornece o estado do ciclo de vida do agente, da organização, do papel ou do ambiente
	 * associado(a) à descrição. 
	 * 
	 * @return
	 * Estado do ciclo de vida do agente, da organização, do papel ou do ambiente associado(a) 
	 * à descrição.
	 */
	public String getState() 
	{
		return state;
	}

	/**
	 * Atribui o elementId de um agente, de uma organização, de um papel ou 
	 * de um ambiente.
	 * @param elementId
	 * ElementId de um agente, de uma organização, de um papel ou de um ambiente.
	 */
	public void setElementId( ElementID elementId ) 
	{
		this.elementId = elementId;
	}

	/**
	 * Atribui o proprietário do agente, da organização, do papel ou do ambiente.
	 * @param ownership
	 * Proprietário do agente, da organização, do papel ou do ambiente associado(a) 
	 * com a descrição.
	 */
	public void setOwnership( String ownership ) 
	{
		this.ownership = ownership;
	}
	
	/**
	 * Atribui o estado do agente, da organização, do papel ou do ambiente.
	 * @param state
	 * Estado do agente, da organização, do papel ou do ambiente.
	 */
	public void setState( String state ) 
	{
		this.state = state;
	}
	
}
