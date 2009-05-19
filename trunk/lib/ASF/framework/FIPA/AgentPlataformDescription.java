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
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * da descri��o da plataforma de agente.
 */
public class AgentPlataformDescription 
{ 
	/**
	 * Nome da plataforma de agente.
	 */
	private String name;
	
	/**
	 * Conjunto de servi�os fornecidos pela plataforma de agente para os agentes 
	 * residentes.
	 */
	private Collection Apservices = new ArrayList();
	
	/**
	 * 
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */
	/**
	 * Inst�ncia da classe AgentPlataformDescription.
	 */
	private static AgentPlataformDescription instance;
	/**
	 * Message Transport Protocol respons�vel por enviar mensagens para outros agentes e/ou organiza��es. 
	 */
	private MTP mtp;
	/**
	 * M�todo respons�vel por fornecer a inst�ncia da classe AgentPlataformDescription.
	 * @return
	 * Inst�ncia da classe AgentPlataformDescription.
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
	 * M�todo respons�vel por permitir o recebimento de mensagens para os agentes e/ou organiza��es 
	 * presentes na plataforma. 
	 * @param port
	 * Porta que a plataforma receber� mensagens.
	 */
	public void activateServer(int port)
	{
		mtp = new MessageTransportProtocol();
		mtp.activateServer( port );
	}
	/**
	 * M�todo respons�vel por permitir o recebimento de mensagens para os agentes e/ou organiza��es 
	 * presentes na plataforma. A porta aberta para receber mensagens � definida pelo framework.
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
	 * Fornece o conjunto de servi�os oferecidos pela plataforma de agente.
	 * @return
	 * Conjunto de servi�os oferecidos pela plataforma de agente.
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
	 * Atribui um novo servi�o da plataforma. 
	 * @param service
	 * Novo servi�o da plataforma.
	 */
	public void setApservices( Object service ) 
	{
		Apservices.add( service );
	}
}
