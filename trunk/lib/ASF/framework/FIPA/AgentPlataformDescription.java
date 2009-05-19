/*
 * Created on 10/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.util.ArrayList;
import java.util.Collection;

import framework.FIPA.communication.MTP;
import framework.FIPA.communication.http.MessageTransportProtocol;

/**
 * Responsável por representar as características estruturais e comportamentais
 * da descrição da plataforma de agente.
 */
public class AgentPlataformDescription 
{ 
	/**
	 * Nome da plataforma de agente.
	 */
	private String name;
	
	/**
	 * Conjunto de serviços fornecidos pela plataforma de agente para os agentes 
	 * residentes.
	 */
	private Collection Apservices = new ArrayList();
	
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * Instância da classe AgentPlataformDescription.
	 */
	private static AgentPlataformDescription instance;
	/**
	 * Message Transport Protocol responsável por enviar mensagens para outros agentes e/ou organizações. 
	 */
	private MTP mtp;
	/**
	 * Método responsável por fornecer a instância da classe AgentPlataformDescription.
	 * @return
	 * Instância da classe AgentPlataformDescription.
	 */
	public static AgentPlataformDescription getInstance()
	{
		if ( instance == null )
		{
			instance = new AgentPlataformDescription();			
		}
			
		
		return instance;
	}	
	/**
	 * Método responsável por permitir o recebimento de mensagens para os agentes e/ou organizações 
	 * presentes na plataforma. 
	 * @param port
	 * Porta que a plataforma receberá mensagens.
	 */
	public void activateServer(int port)
	{
		mtp = new MessageTransportProtocol();
		mtp.activateServer( port );
	}
	/**
	 * Método responsável por permitir o recebimento de mensagens para os agentes e/ou organizações 
	 * presentes na plataforma. A porta aberta para receber mensagens é definida pelo framework.
	 */
	public void activateServer()
	{
		mtp = new MessageTransportProtocol();
		mtp.activateServer();
	}
	/**
	 * Fornece o nome da plataforma de agente.
	 * @return
	 * Nome da plataforma de agente.
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Fornece o conjunto de serviços oferecidos pela plataforma de agente.
	 * @return
	 * Conjunto de serviços oferecidos pela plataforma de agente.
	 */
	public Collection getApservices() {
		return Apservices;
	}

	/**
	 * Atribui o nome da plataforma de agente.
	 * @param name
	 * Novo nome da plataforma de agente.
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Atribui um novo serviço da plataforma. 
	 * @param service
	 * Novo serviço da plataforma.
	 */
	public void setApservices( Object service ) 
	{
		Apservices.add( service );
	}
}
