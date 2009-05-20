/*
 * Created on 10/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de um identificador de agente, organiza��o, ambiente e de um papel. 
 */
public class ElementID implements Serializable
{
	/**
	 * Nome que identifica um agente, uma organiza��o, um ambiente ou um papel.
	 */
	private String name;
	
	/**
	 * Uma sequ�ncia ordenada de endere�os de transporte onde um agente, uma organiza��o, um ambiente ou
	 * um papel est� localizado(a). A ordem implica em uma rela��o de prefer�ncias dos endere�os.
	 */
	private Collection addresses = new ArrayList();
	
	/**
	 * Um sequ�ncia de ElementIDs ordenadas onde o nome dos servi�os solucionadores
	 * podem ser contactados pelo(a) agente, organiza��o, ambiente ou papel.
	 */
	private Collection resolvers = new ArrayList();
	
	/**
	 * Construtor da classe respons�vel por atribuir o nome completo de um agente, de uma organiza��o,
	 * de um ambiente ou de um papel.   
	 * @param name
	 * Nome completo de identifica��o de um agente, de uma organiza��o,
	 * de um ambiente ou de um papel.   
	 */
	public ElementID( String name )
	{
		this.name = name;
	}
	
	/**
	 * Construtor da classe respons�vel por verificar se
	 * o agente, a organiza��o, o ambiente ou o papel associado ao identificador, 
	 * possue sua origem de cria��o a partir da plataforma local. Dependendo da origem
	 * faz um tipo de atribui��o do nome que o(a) identificar�.
	 * @param name
	 * Nome de um agente, de uma organiza��o, de um ambiente ou de um papel. 
	 * @param isLocal
	 * Vari�vel booleana que indica se o agente, a organiza��o, o ambiente ou o papel 
	 * associado ao identificador, possui sua origem de cria��o a partir da plataforma local (true)
	 * ou de uma outra (false).
	 */
	public ElementID( String name, boolean isLocal )
	{
		if ( isLocal == true )
			setLocalName( name );
		else
			this.name = name;	
	}
	
	/**
	 * Cria o nome de um(a) agente, organiza��o, ambiente ou papel, atrav�s da 
	 * concatena��o de um prefixo passado por par�metro com um sufixo no formato 
	 * "@" + "hap_name", sendo hap_name o nome identificador da plataforma.
	 * @param name
	 * Prefixo do nome que ir� identificar um agente, uma organiza��o, um ambiente 
	 * ou um papel.
	 */
	private void setLocalName(String name) 
	{
		String hap;
		hap = AgentPlatformDescription.getInstance().getName();
		// initialize the static variable atHAP, if not yet initialized
		if (hap == null) 
			throw new RuntimeException("Unknown Platform Name");
		
		hap = "@"+hap;
		
		this.name = name.trim();
				
		this.name = name.concat(hap); 		
		
	}
	
	/**
	 * Fornece o nome de um agente, de uma organiza��o, de um ambiente ou de 
	 * um papel.
	 * @return
	 * Nome de um agente, de uma organiza��o, de um ambiente ou de um papel.
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Fornece o conjunto de endere�os de um agente, de uma organiza��o, de um ambiente ou 
	 * de um papel.
	 * @return
	 * Conjunto de endere�os de um agente, de uma organiza��o, de um ambiente ou 
	 * de um papel.
	 */
	public Collection getAddresses()
	{
		return addresses;
	}
	
	/**
	 * Atribui um novo nome de um agente, de uma organiza��o, de um ambiente ou de um
	 * papel.  
	 * @param name
	 * Novo nome de um agente, de uma organiza��o, de um ambiente ou de um papel.
	 * @param isLocal
	 * Vari�vel booleana que indica se o agente, a organiza��o, o ambiente ou o papel 
	 * associado ao elementId possui sua origem de cria��o a partir da plataforma local
	 * (true), ou de outra qualquer (false).
	 */
	public void setName( String name, boolean isLocal ) 
	{
		if ( isLocal == true )
			setLocalName( name );
		else
			this.name = name;
	}
	
	/**
	 * Atribui um novo endere�o que indica onde o agente, a organiza��o, o ambiente 
	 * ou o papel se encontra.
	 * @param address
	 * Novo endere�o que far� parte da cole��o de endere�os de um agente, de uma 
	 * organiza��o, de um ambiente ou de um papel. 
	 */
	public void setAddress( String address )
	{
		this.addresses.add( address );
	}
	
	public void setAllAddresses( Collection addresses )
	{
		this.addresses.addAll( addresses );
	}

	public Collection getResolvers() {
		return resolvers;
	}

	public void setResolvers(Collection resolvers) {
		this.resolvers = resolvers;
	}
	
	
}
