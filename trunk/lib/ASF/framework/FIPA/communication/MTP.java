/*
 * Created on 22/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA.communication;

import framework.mentalState.Message;

/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais 
 * e comportamentais do protocolo de transporte de mensagem.
 */
public abstract class MTP 
{
	/**
	 * M�todo respons�vel por ativar o servidor da plataforma local.
	 * @param port
	 * N�mero da porta que ser� aberta, permitindo que haja conex�es com clientes.
	 */
	public abstract void activateServer( int port );
	/**
	 * M�todo respons�vel por ativar o servidor, considerando uma porta padr�o.
	 */
	public abstract void activateServer();
	/**
	 * M�todo respons�vel por criar um cliente que ir� se comunicar com um servidor
	 * e com isso, transmitir uma mensagem para ele.
	 * @param port
	 * N�mero da porta do servidor que receber� uma mensagem do cliente.
	 * @param message
	 * Mensagem que ser� enviada para um servidor.
	 */
	public abstract void send( int port, Message message );
	/**
	 * M�todo respons�vel por criar um cliente que ir� se comunicar com um servidor 
	 * atrav�s de uma porta padr�o e com isso transmitir uma mensagem.
	 * @param message
	 * Mensagem que ser� enviada para um servidor.
	 */
	public abstract void send( Message message );

}
