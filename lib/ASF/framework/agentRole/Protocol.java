package framework.agentRole;

import java.util.*;
import framework.mentalState.Message;

/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e 
 * comportamentais de um protocolo. 
 */
public abstract class Protocol
{
    /**
    * @associates <{Message}>
    * @link aggregation
    * @clientCardinality 0..*
    * @supplierCardinality 0..*
    */
    /**
     * Conjunto de mensagens tratadas pelo protocolo. 
     */
    protected Collection messages = new Vector ();
    /**
     * Construtor da classe Protocol.
     */
    public Protocol ()
    {

    }
    
    /**
     * Fornece o conjunto de mensagens do protocolo.
     * @return
     * Todas as mensagens tratadas pelo protocolo.
     */
    public Collection getMessages ()
    {
        return this.messages;

    }
    
    /**
     * Atribui uma nova mensagem ao conjunto de mensagens do protocolo.
     * @param newMessage
     * Nova mensagem do protocolo.
     */
    public void setMessage (Message newMessage)
    {
        this.messages.add (newMessage);

    }
    
    /**
     * M�todo abstrato respons�vel por fornecer um conjunto de mensagens a partir de 
     * uma mensagem que foi recebida pelo agente. � este m�todo que especifica a 
     * ordem de troca de mensagens em um protocolo.
     * @param object
     * Agente ou organiza��o que recebeu a mensagem.
     * @param inMsg
     * Mensagem recebida pelo agente.
     * @return
     * Conjunto de mensagens criadas a partir de uma mensagem que foi recebida 
     * pelo agente.
     */
    public abstract Collection execute (Object object, Message inMsg);
}

