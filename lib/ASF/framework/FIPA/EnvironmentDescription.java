/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import framework.environment.MTS_Environment;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * da descri��o de um ambiente.
 */
public class EnvironmentDescription extends Description 
{
	
	/**
	 * 
	 * @clientCardinality 0..1
	 * @supplierCardinality 1
	 */
	/**
	 * Ambiente da descri��o.
	 */
	private MTS_Environment environment;

	/**
	 * Fornece o ambiente da descri��o.
	 * @return
	 * Ambiente da descri��o.
	 */
	public MTS_Environment getEnvironment() 
	{
		return environment;
	}

	/**
	 * Atribui o ambiente da descri��o.
	 * @param environment
	 * Ambiente da descri��o.
	 */
	public void setEnvironment( MTS_Environment environment ) 
	{
		this.environment = environment;
	}
}
