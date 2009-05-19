/*
 * Created on 23/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA.communication;

import java.util.Date;


/**
 * Responsável por representar as características estruturais e comportamentais
 * de uma descrição de uma mensagem que foi transmitida. 
 */
public class ReceivedObjectDescription 
{
	/**
	 * Ip da máquina que possui o ACC receptor de uma mensagem.
	 */
	private String by;
	/**
	 * Ip da máquina que possui o ACC transmissor de uma mensagem.
	 */
	private String from;
	/**
	 * Data de quando a mensagem foi recebida.
	 */
	private Date date;
	/**
	 * Identificador único de uma mensagem.
	 */
	private String id;
	/**
	 * Tipo de protocolo de transporte de mensagem (MTP) que uma mensagem foi enviada. 
	 */
	private String via="fipa.mts.mtp.http.std";
	
	/**
	 * Construtor da classe responsável por atribuir o ip 
	 * da máquina que possui o ACC do receptor de uma mensagem.
	 * @param by
	 * Ip da máquina que possui o ACC do receptor de uma mensagem.
	 */
	public ReceivedObjectDescription( String by )
	{
		this.by = by;
		this.date = new Date();
		
	}

	/**
	 * Construtor da classe responsável por atribuir o ip da máquina que possui o
	 * ACC receptor e daquela que possui o ACC transmissor da mensagem, além de 
	 * indicar a data em que foi recebida a mensagem.
	 * @param by
	 * Ip da máquina que possui o ACC do receptor de uma mensagem.
	 * @param from
	 * Ip da máquina que possui o ACC do transmissor de uma mensagem.
	 * 
	 */
	public ReceivedObjectDescription( String by, String from )
	{
		this.by = by;
		this.from = from;
		this.date = new Date();
	}
	
	/**
	 * Construtor da classe responsável por atribuir o ip da máquina que possui o
	 * ACC receptor e daquela que possui o ACC transmissor da mensagem, 
	 * o identificador de uma mensagem, o tipo de MTP que
	 * ela foi enviada, além de indicar a data em que foi feito o recebimento.
	 * @param by
	 * Ip da máquina que possui o ACC receptor de uma mensagem.
	 * @param from
	 * Ip da máquina que possui o ACC transmissor de uma mensagem.
	 * @param date
	 * Data de recebimento da mensagem.
	 * @param id
	 * Identificador de uma mensagem.
	 * @param via
	 * Tipo de MTP que a mensagem foi enviada.
	 */
	public ReceivedObjectDescription( String by, String from, String id, String via )
	{
		this.by = by;
		this.from = from;
		this.date = new Date();
		this.id = id;
		this.via = via;
	}
	
	/**
	 * Fornece o ip da máquina que possui o ACC receptor de uma mensagem.
	 * @return
	 * Ip da máquina que possui o ACC receptor de uma mensagem.
	 */
	public String getBy() 
	{
		return by;
	}

	/**
	 * Fornece a data em que uma mensagem foi recebida.
	 * @return
	 * Data em que uma mensagem foi recebida.
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * Fornece o ip da máquina que possui o ACC transmissor de uma mensagem. 
	 * @return
	 * Ip da máquina que possui o ACC transmissor de uma mensagem.
	 */
	public String getFrom() 
	{
		return from;
	}

	/**
	 * Fornece o identificador de uma mensagem.
	 * @return
	 * Identificador de uma mensagem.
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * Fornece o tipo de MTP em que uma mensagem foi enviada.
	 * @return
	 * Tipo de MTP em que uma mensagem foi enviada.
	 */
	public String getVia() 
	{
		return via;
	}

	/**
	 * Atribui o ip da máquina que possui o ACC receptor de uma mensagem.
	 * @param url
	 * Ip da máquina que possui o ACC receptor de uma mensagem.
	 */
	public void setBy(String url) 
	{
		by = url;
	}

	/**
	 * Atribui o ip da máquina que possui o ACC transmissor de uma mensagem. 
	 * @param url
	 * Ip da máquina que possui o ACC transmissor de uma mensagem.
	 */
	public void setFrom(String url) 
	{
		from = url;
	}

	/**
	 * Atribui o identificador de uma mensagem.
	 * @param string
	 * Identificador de uma mensagem.
	 */
	public void setId(String string)
	{
		id = string;
	}

	/**
	 * Atribui o tipo de MTP que a mensagem foi enviada.
	 * @param string
	 * Tipo de MTP que a mensagem foi enviada.
	 */
	public void setVia(String string) 
	{
		via = string;
	}

}
