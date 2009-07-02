/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import framework.organization.MainOrganization;

/**
 * Responsável por representar as características estruturais e comportamentais
 * da descrição de uma organização.
 */
public class OrganizationDescription extends Description 
{
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Organização da descrição.
	 */
	private MainOrganization organization;
	
	/**
	 * Fornece a organização da descrição.
	 * @return
	 * Organização da descrição.
	 */
	public MainOrganization getOrganization() 
	{
		return organization;
	}

	/**
	 * Atribui o organização da descrição.
	 * @param agent
	 * Organização da descrição.
	 */
	public void setOrganization( MainOrganization organization ) 
	{
		this.organization = organization;
	}
}
