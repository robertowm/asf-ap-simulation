/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import framework.environment.MTS_Environment;

/**
 * Responsável por representar as características estruturais e comportamentais
 * da descrição de um ambiente.
 */
public class EnvironmentDescription extends Description 
{
	
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Ambiente da descrição.
	 */
	private MTS_Environment environment;

	/**
	 * Fornece o ambiente da descrição.
	 * @return
	 * Ambiente da descrição.
	 */
	public MTS_Environment getEnvironment() 
	{
		return environment;
	}

	/**
	 * Atribui o ambiente da descrição.
	 * @param environment
	 * Ambiente da descrição.
	 */
	public void setEnvironment( MTS_Environment environment ) 
	{
		this.environment = environment;
	}
}
