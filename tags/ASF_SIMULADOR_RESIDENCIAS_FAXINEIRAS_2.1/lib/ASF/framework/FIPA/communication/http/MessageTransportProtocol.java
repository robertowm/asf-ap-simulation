/*
 * Created on 24/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA.communication.http;

import framework.FIPA.communication.MTP;
import framework.mentalState.Message;

/**
* Respons�vel por representar as caracter�sticas estruturais e comportamentais
* de um protocolo de transporte de mensagem.
*/
public class MessageTransportProtocol extends MTP {
	/*#framework.FIPA.communication.http.HttpServer Dependency_Link1*/
	/*#framework.FIPA.communication.http.HttpClient Dependency_Link2*/

	/**
	* M�todo respons�vel por ativar o servidor.
	* @param port
	* Porta que o servidor ir� abrir para conex�es.
	*/
	public void activateServer(int port) {
		HttpServer server = new HttpServer(port);
		Thread serverExecute = new Thread( server );
		serverExecute.start();
	}
	/**
	* M�todo respons�vel por ativar o servidor com a porta padr�o definida na
	* classe HttpServer.
	*/
	public void activateServer() {
		HttpServer server = new HttpServer();
		Thread serverExecute = new Thread( server );
		serverExecute.start();
	}
	/**
	* M�todo respons�vel por ativar um cliente que ir� enviar uma mensagem
	* para um ou mais receptores.
	* @param port
	* Porta em que o(s) servidor(es) permitira(m) a conex�o com cliente(s).
	* @param message
	* Mensagem a ser transmitida.
	*/
	public void send(int port, Message message) {
		HttpClient client = new HttpClient(port, message);
	}
	/**
	* M�todo respons�vel por ativar um cliente que ir� enviar uma mensagem
	* para um ou mais receptores. Neste caso h� uma porta padr�o do(s)
	* servidore(s) envolvido(s) na comunica��o.
	* @param message
	* Mensagem a ser transmitida.
	*/
	public void send(Message message) {
		HttpClient client = new HttpClient(message);
	}

}
