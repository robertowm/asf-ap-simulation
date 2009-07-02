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
 * Responsável por representar as características estruturais e comportamentais
 * de um identificador de agente, organização, ambiente e de um papel. 
 */
public class ElementID implements Serializable
{
	/**
	 * Nome que identifica um agente, uma organização, um ambiente ou um papel.
	 */
	private String name;
	
	/**
	 * Uma sequência ordenada de endereços de transporte onde um agente, uma organização, um ambiente ou
	 * um papel está localizado(a). A ordem implica em uma relação de preferências dos endereços.
	 */
	private Collection addresses = new ArrayList();
	
	/**
	 * Um sequência de ElementIDs ordenadas onde o nome dos serviços solucionadores
	 * podem ser contactados pelo(a) agente, organização, ambiente ou papel.
	 */
	private Collection resolvers = new ArrayList();
	
	/**
	 * Construtor da classe responsável por atribuir o nome completo de um agente, de uma organização,
	 * de um ambiente ou de um papel.   
	 * @param name
	 * Nome completo de identificação de um agente, de uma organização,
	 * de um ambiente ou de um papel.   
	 */
	public ElementID( String name )
	{
		this.name = name;
	}
	
	/**
	 * Construtor da classe responsável por verificar se
	 * o agente, a organização, o ambiente ou o papel associado ao identificador, 
	 * possue sua origem de criação a partir da plataforma local. Dependendo da origem
	 * faz um tipo de atribuição do nome que o(a) identificará.
	 * @param name
	 * Nome de um agente, de uma organização, de um ambiente ou de um papel. 
	 * @param isLocal
	 * Variável booleana que indica se o agente, a organização, o ambiente ou o papel 
	 * associado ao identificador, possui sua origem de criação a partir da plataforma local (true)
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
	 * Cria o nome de um(a) agente, organização, ambiente ou papel, através da 
	 * concatenação de um prefixo passado por parâmetro com um sufixo no formato 
	 * "@" + "hap_name", sendo hap_name o nome identificador da plataforma.
	 * @param name
	 * Prefixo do nome que irá identificar um agente, uma organização, um ambiente 
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
	 * Fornece o nome de um agente, de uma organização, de um ambiente ou de 
	 * um papel.
	 * @return
	 * Nome de um agente, de uma organização, de um ambiente ou de um papel.
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Fornece o conjunto de endereços de um agente, de uma organização, de um ambiente ou 
	 * de um papel.
	 * @return
	 * Conjunto de endereços de um agente, de uma organização, de um ambiente ou 
	 * de um papel.
	 */
	public Collection getAddresses()
	{
		return addresses;
	}
	
	/**
	 * Atribui um novo nome de um agente, de uma organização, de um ambiente ou de um
	 * papel.  
	 * @param name
	 * Novo nome de um agente, de uma organização, de um ambiente ou de um papel.
	 * @param isLocal
	 * Variável booleana que indica se o agente, a organização, o ambiente ou o papel 
	 * associado ao elementId possui sua origem de criação a partir da plataforma local
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
	 * Atribui um novo endereço que indica onde o agente, a organização, o ambiente 
	 * ou o papel se encontra.
	 * @param address
	 * Novo endereço que fará parte da coleção de endereços de um agente, de uma 
	 * organização, de um ambiente ou de um papel. 
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
