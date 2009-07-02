/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;

/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais 
 * e comportamentais de uma descri��o.
 */
public abstract class Description implements Serializable
{
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * ElementId de um agente, de uma organiza��o, de um ambiente ou de um papel.
	 */
	protected ElementID elementId = null;
	
	/**
	 * Propriet�rio de um agente, de uma organiza��o, de um ambiente ou de um papel
	 */
	protected String ownership = null;
	
	/**
	 * Estado do agente, da organiza��o, do ambiente ou do papel associado(a) � descri��o.
	 */
	protected String state = null;

	/**
	 * Fornece o elementId do agente, da organiza��o, do papel ou do ambiente associado(a) � descri��o.
	 * @return
	 * ElementId do agente, da organiza��o, do papel ou do ambiente associado(a) � descri��o.
	 */
	public ElementID getElementId() 
	{
		return elementId;
	}

	/**
	 * Fornece o propriet�rio do agente, da organiza��o, do papel ou do ambiente associado(a) � 
	 * descri��o.
	 * @return
	 * Propriet�rio do agente, da organiza��o, do papel ou do ambiente associado(a) � descri��o.
	 */
	public String getOwnership() 
	{
		return ownership;
	}

	/**
	 * Fornece o estado do ciclo de vida do agente, da organiza��o, do papel ou do ambiente
	 * associado(a) � descri��o. 
	 * 
	 * @return
	 * Estado do ciclo de vida do agente, da organiza��o, do papel ou do ambiente associado(a) 
	 * � descri��o.
	 */
	public String getState() 
	{
		return state;
	}

	/**
	 * Atribui o elementId de um agente, de uma organiza��o, de um papel ou 
	 * de um ambiente.
	 * @param elementId
	 * ElementId de um agente, de uma organiza��o, de um papel ou de um ambiente.
	 */
	public void setElementId( ElementID elementId ) 
	{
		this.elementId = elementId;
	}

	/**
	 * Atribui o propriet�rio do agente, da organiza��o, do papel ou do ambiente.
	 * @param ownership
	 * Propriet�rio do agente, da organiza��o, do papel ou do ambiente associado(a) 
	 * com a descri��o.
	 */
	public void setOwnership( String ownership ) 
	{
		this.ownership = ownership;
	}
	
	/**
	 * Atribui o estado do agente, da organiza��o, do papel ou do ambiente.
	 * @param state
	 * Estado do agente, da organiza��o, do papel ou do ambiente.
	 */
	public void setState( String state ) 
	{
		this.state = state;
	}
	
}
