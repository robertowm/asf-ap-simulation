/*
 * Created on 22/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA.communication;

import framework.mentalState.Message;

/**
 * Classe abstrata responsável por representar as características estruturais 
 * e comportamentais do protocolo de transporte de mensagem.
 */
public abstract class MTP 
{
	/**
	 * Método responsável por ativar o servidor da plataforma local.
	 * @param port
	 * Número da porta que será aberta, permitindo que haja conexões com clientes.
	 */
	public abstract void activateServer( int port );
	/**
	 * Método responsável por ativar o servidor, considerando uma porta padrão.
	 */
	public abstract void activateServer();
	/**
	 * Método responsável por criar um cliente que irá se comunicar com um servidor
	 * e com isso, transmitir uma mensagem para ele.
	 * @param port
	 * Número da porta do servidor que receberá uma mensagem do cliente.
	 * @param message
	 * Mensagem que será enviada para um servidor.
	 */
	public abstract void send( int port, Message message );
	/**
	 * Método responsável por criar um cliente que irá se comunicar com um servidor 
	 * através de uma porta padrão e com isso transmitir uma mensagem.
	 * @param message
	 * Mensagem que será enviada para um servidor.
	 */
	public abstract void send( Message message );

}
