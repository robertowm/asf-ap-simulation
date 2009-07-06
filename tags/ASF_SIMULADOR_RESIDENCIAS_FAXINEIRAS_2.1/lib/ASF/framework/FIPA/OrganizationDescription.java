/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import framework.organization.MainOrganization;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * da descri��o de uma organiza��o.
 */
public class OrganizationDescription extends Description 
{
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Organiza��o da descri��o.
	 */
	private MainOrganization organization;
	
	/**
	 * Fornece a organiza��o da descri��o.
	 * @return
	 * Organiza��o da descri��o.
	 */
	public MainOrganization getOrganization() 
	{
		return organization;
	}

	/**
	 * Atribui o organiza��o da descri��o.
	 * @param agent
	 * Organiza��o da descri��o.
	 */
	public void setOrganization( MainOrganization organization ) 
	{
		this.organization = organization;
	}
}
