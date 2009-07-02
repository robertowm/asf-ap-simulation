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
* Responsável por representar as características estruturais e comportamentais
* de um protocolo de transporte de mensagem.
*/
public class MessageTransportProtocol extends MTP {
	/*#framework.FIPA.communication.http.HttpServer Dependency_Link1*/
	/*#framework.FIPA.communication.http.HttpClient Dependency_Link2*/

	/**
	* Método responsável por ativar o servidor.
	* @param port
	* Porta que o servidor irá abrir para conexões.
	*/
	public void activateServer(int port) {
		HttpServer server = new HttpServer(port);
		Thread serverExecute = new Thread( server );
		serverExecute.start();
	}
	/**
	* Método responsável por ativar o servidor com a porta padrão definida na
	* classe HttpServer.
	*/
	public void activateServer() {
		HttpServer server = new HttpServer();
		Thread serverExecute = new Thread( server );
		serverExecute.start();
	}
	/**
	* Método responsável por ativar um cliente que irá enviar uma mensagem
	* para um ou mais receptores.
	* @param port
	* Porta em que o(s) servidor(es) permitira(m) a conexão com cliente(s).
	* @param message
	* Mensagem a ser transmitida.
	*/
	public void send(int port, Message message) {
		HttpClient client = new HttpClient(port, message);
	}
	/**
	* Método responsável por ativar um cliente que irá enviar uma mensagem
	* para um ou mais receptores. Neste caso há uma porta padrão do(s)
	* servidore(s) envolvido(s) na comunicação.
	* @param message
	* Mensagem a ser transmitida.
	*/
	public void send(Message message) {
		HttpClient client = new HttpClient(message);
	}

}
