package framework.mentalState;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import framework.FIPA.ElementID;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma mensagem no formato ACL. 
 */
public class Message implements Serializable {
    // ACL parameters

    /**
     * Define o remetente da mensagem. 
     */
    private ElementID from = null;
    /**
     * Define o(s) destinat�rio(s) da mensagem.
     */
    private List to = new Vector();
    /**
     * Coment�rio da mensagem.
     */
    private String comments = null;
    /**
     * Nome da sintax que representa a mensagem payload.
     */
    private String aclRepresentation = "fipa.acl.rep.string.std";
    /**
     * Tamanho em bytes da mensagem payload.
     */
    private String payloadLength = null;
    /**
     * Contem a codifica��o na qual a linguagem do conte�do � expressa.
     */
    private String payloadEncoding = null;
    /**
     * Cont�m a data e a hora de cria��o da mensagem.
     */
    private Date date = null;
    /**
     * Conjunto de nome dos agentes a quem essa inst�ncia da mensagem deve ser 
     * entregue.
     */
    private Collection intendedReceiver = null;
    /**
     * Stamp que representa que a mensagem foi recebida por um ACC.
     */
    private Object received = null;
    /**
     * Requirementos de transporte da mensagem.
     */
    private Object transportBehavior = null;
    /**
     * Indica para quem o receptor da mensagem corrente deve enviar sua resposta.
     */
    private List replyTo = new Vector();
    /**
     * Denota o conte�do da mensagem. Equivalente ao objeto da a��o.
     */
    private Object content = null;
    /**
     * Linguagem na qual o conte�do � expresso.
     */
    private String language = null;
    /**
     * Denota a ontologia dos termos utilizados na mensagem.
     */
    private String ontology = null;
    /**
     * Protocolo utilizado na troca de mensagem.
     */
    private String protocol = null;
    /**
     * Introduz uma express�o que deve ser usada para identificar uma seq��ncia de 
     * mensagens em uma conversa��o.
     */
    private String conversationId = null;
    /**
     * Indica a express�o/mensagem a qual a mensagem em quest�o est� se referindo.
     */
    private String inReplyTo = null;
    /**
     * Introduz uma express�o que deve ser utilizada pelo agente receptor para 
     * responder a mensagem sendo enviada.
     */
    private String replyWith = null;
    /**
     * Detona o tipo de ato da fala utilizado na mensagem.
     */
    private String performative = null;
    /**
     * Denota a codifica��o na qual a linguagem do conte�do � expressa.
     */
    private int encoding;
    /**
     * Indica o tempo de resposta esperado pelo agente que enviou a mensagem.
     */
    private String replyBy = null;

    /** 
     * Construtor da classe Message.
     */
    public Message() {
    }

    /**
     * Construtor da classe que atribui uma s�rie de informa��es � mensagem.
     * @param conversationId
     * Identificador de uma sequ�ncia de mensagens de uma conversa��o.
     * @param newContent
     * Conte�do da mensagem.
     * @param fromName
     * Nome do remetente da mensagem.
     * @param toName
     * Destinat�rio(s) da mensagem. 
     */
    public Message(String conversationId, Object newContent, ElementID fromName, List toName) {
        this.conversationId = conversationId;
        this.content = newContent;
        this.from = fromName;
        this.to = toName;
    }

    /**
     * Construtor da classe, que atribui uma s�rie de informa��es a mensagem.
     * @param conversationId
     * Identificador de uma sequ�ncia de mensagens de uma conversa��o.
     * @param newContent
     * Conte�do da mensagem.
     * @param fromName
     * Nome do remetente da mensagem.
     * @param newTo
     * Destinat�rio da mensagem.
     */
    public Message(String conversationId, Object newContent, ElementID fromName, ElementID newTo) {
        this.conversationId = conversationId;
        this.content = newContent;
        this.from = fromName;
        this.to.add(newTo);
    }

    /**
     * Fornece o remetente da mensagem.
     * @return
     * Remetente da mensagem.
     */
    public ElementID getFrom() {
        return this.from;
    }

    /**
     * Fornece o(s) destinat�rio(s) da mensagem.
     * @return
     * Destinat�rio(s) da mensagem.
     */
    public List getTo() {
        return this.to;
    }

    /**
     * Fornece o identificador de uma seq��ncia de mensagens de uma conversa��o.
     * @return
     * Identificador de uma seq��ncia de mensagens de uma conversa��o.
     */
    public String getConversationId() {
        return this.conversationId;
    }

    /**
     * Fornece para quem o receptor da mensagem corrente deve enviar sua resposta.
     * @return
     * Para quem o receptor da mensagem corrente deve enviar sua resposta.
     */
    public List getReplyTo() {
        return this.replyTo;
    }

    /**
     * Fornece o conte�do da mensagem.
     * @return
     * Conte�do da mensagem.
     */
    public Object getContent() {
        return this.content;
    }

    /**
     * Fornece a linguagem na qual o conte�do � expresso.
     * @return
     * Linguagem na qual o conte�do � expresso.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Fornece a ontologia da mensagem.
     * @return
     * Ontologia da mensagem.
     */
    public String getOntology() {
        return this.ontology;
    }

    /**
     * Fornece o protocolo utilizado na troca de mensagem.
     * @return
     * Protocolo utilizado na troca de mensagem.
     */
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * Fornece uma express�o/mensagem a qual a mensagem em quest�o est� se 
     * referindo.
     * @return
     * Express�o/mensagem a qual a mensagem em quest�o est� se referindo.
     */
    public String getInReplyTo() {
        return this.inReplyTo;
    }

    /**
     * Fornece a express�o utilizada peloagente receptor para responder a mensagem
     * sendo enviada.
     * @return
     * Express�o utilizada peloagente receptor para responder a mensagem
     * sendo enviada.
     */
    public String getReplyWith() {
        return this.replyWith;
    }

    /**
     * Fornece o tipo de ato da fala utilizado na mensagem.
     * @return
     * Tipo de ato da fala utilizado na mensagem.
     */
    public String getPerformative() {
        return this.performative;
    }

    /**
     * Fornece a codifica��o na qual a linguagem do conte�do � expressa.
     * @return
     * Codifica��o na qual a linguagem do conte�do � expressa.
     */
    public int getEncoding() {
        return this.encoding;
    }

    /**
     * Fornece nome da sintax que representa a mensagem payload.
     * @return
     * Nome da sintax que representa a mensagem payload.
     */
    public String getAclRepresentation() {
        return aclRepresentation;
    }

    /**
     * Fornece o coment�rio da mensagem.
     * @return
     * Coment�rio da mensagem.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Fornece a data e a hora da cria��o da mensagem.
     * @return
     * Data e a hora da cria��o da mensagem.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Fornece o conjunto de nome dos agentes a quem essa inst�ncia da mensagem deve ser 
     * entregue.
     * @return
     * Conjunto de nome dos agentes a quem essa inst�ncia da mensagem deve ser 
     * entregue.
     */
    public Collection getIntendedReceiver() {
        return intendedReceiver;
    }

    /**
     * Fornece a codifica��o na qual a linguagem do conte�do � expressa.
     * @return
     * Codifica��o na qual a linguagem do conte�do � expressa.
     */
    public String getPayloadEncoding() {
        return payloadEncoding;
    }

    /**
     * Fornece o tamanho em bytes da mensagem payload.
     * @return
     * Tamanho em bytes da mensagem payload.
     */
    public String getPayloadLength() {
        return payloadLength;
    }

    /**
     * Fornece o stamp indicador da recep��o da mensagem por um ACC.
     * @return
     * Stamp indicador da recep��o da mensagem por um ACC.
     */
    public Object getReceived() {
        return received;
    }

    /**
     * Fornece os requerimentos de transporte da mensagem.
     * @return
     * Requerimentos de transporte da mensagem.
     */
    public Object getTransportBehavior() {
        return transportBehavior;
    }

    /**
     * Fornece o tempo de resposta esperado pelo agente que enviou a mensagem.
     * @return
     * Tempo de resposta esperado pelo agente que enviou a mensagem.
     */
    public String getReplyBy() {
        return replyBy;
    }

    /**
     * Atribui o nome do remetente.
     * @param fromName
     * Nome do remetente.
     */
    public void setFrom(ElementID fromName) {
        this.from = fromName;
    }

    /**
     * Inclui o nome de um destinat�rio na lista que cont�m todos aqueles que ir�o 
     * receber a mensagem.
     * @param toName
     * Nome de um novo destinat�rio.
     */
    public void setTo(String toName) {
        this.to.add(toName);
    }

    /**
     * Inclui um identificador de quem o receptor da mensagem corrente deve enviar 
     * sua resposta. 
     * da mensagem.
     * @param newReplyTo
     * Identificador de quem o receptor da mensagem corrente deve enviar 
     * sua resposta.
     */
    public void setReplyTo(ElementID newReplyTo) {
        this.replyTo.add(newReplyTo);
    }

    /**
     * Atribui um novo conte�do a mensagem.
     * @param newContent
     * Novo conte�do da mensagem. 
     */
    public void setContent(Object newContent) {
        this.content = newContent;
    }

    /**
     * Atribui a linguagem na qual o conte�do da mensagem � expresso. 
     * @param language
     * Linguagem no qual o conte�do da mensagem � expresso.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Atribui uma ontologia a mensagem.
     * @param ontology
     * Ontologia da mensagem.
     */
    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    /**
     * Atribui um protocolo a mensagem.
     * @param protocol
     * Protocolo da mensagem. 
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Atribui um identificador de uma seq��ncia de mensagens de uma conversa��o. 
     * @param conversationId
     * Identificador de uma seq��ncia de mensagens de uma conversa��o.
     */
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * Atribui a express�o/mensagem a qual a mensagem em quest�o est� se referindo.
     * @param inReplyTo
     * Nova express�o/mensagem a qual a mensagem em quest�o est� se referindo.
     */
    public void setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    /**
     * Atribui uma express�o que deve ser utilizada pelo agente receptor 
     * para responder a mensagem sendo enviada. 
     * @param replyWith
     * Express�o que deve ser utilizada pelo agente receptor 
     * para responder a mensagem sendo enviada.
     */
    public void setReplyWith(String replyWith) {
        this.replyWith = replyWith;
    }

    /**
     * Atribui o tipo de ato da fala que ser� utilizado pela mensagem.
     * @param performative
     * Tipo de ato da fala que ser� utilizado pela mensagem.
     */
    public void setPerformative(String performative) {
        this.performative = performative;
    }

    /**
     * Atribui um novo codificador na qual a linguagem do conte�do � expressa.
     * @param encoding
     * Codificador na qual a linguagem do conte�do � expressa.
     */
    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }

    /**
     * Atribui o nome da sintax que representa a mensagem payload.
     * @param string
     * Nome da sintax que representa a mensagem payload.
     */
    public void setAclRepresentation(String string) {
        aclRepresentation = string;
    }

    /**
     * Atribui o coment�rio da mensagem.
     * @param string
     * Coment�rio da mensagem.
     */
    public void setComments(String string) {
        comments = string;
    }

    /**
     * Atribui a data e hora de cria��o da mensagem. 
     * 
     */
    public void setDate() {
        date = new Date();
    }

    /**
     * Atribui o conjunto de nome dos agentes a quem essa inst�ncia da mensagem deve ser 
     * entregue.
     * @param collection
     * Conjunto de nome dos agentes a quem essa inst�ncia da mensagem deve ser 
     * entregue.
     */
    public void setIntendedReceiver(Collection collection) {
        intendedReceiver = collection;
    }

    /**
     * Atribui a codifica��o na qual a linguagem do conte�do � expressa.
     * @param string
     * Codifica��o na qual a linguagem do conte�do � expressa.
     */
    public void setPayloadEncoding(String string) {
        payloadEncoding = string;
    }

    /**
     * Atribui o tamanho em bytes da mensagem payload.
     * @param string
     * Tamanho em bytes da mensagem payload.
     */
    public void setPayloadLength(String string) {
        payloadLength = string;
    }

    /**
     * Atribui o stamp indicador da recep��o da mensagem por um ACC. 
     * @param object
     * Atribui o stamp indicador da recep��o da mensagem por um ACC.
     */
    public void setReceived(Object stamp) {
        received = stamp;
    }

    /**
     * Atribui o conjunto de receptores da mensagem.
     * @param list
     * Conjunto de receptores da mensagem.
     */
    public void setTo(List list) {
        to = list;
    }

    /**
     * Atribui os requerimentos de transporte da mensagem.
     * @param object
     * Requerimentos de transporte da mensagem.
     */
    public void setTransportBehavior(Object object) {
        transportBehavior = object;
    }

    /**
     * Atribui o tempo de resposta esperado pelo agente que enviou a mensagem.
     * @param replyBy
     * Tempo de resposta esperado pelo agente que enviou a mensagem.
     */
    public void setReplyBy(String replyBy) {
        this.replyBy = replyBy;
    }

    /**
     * Atribui a data da mensagem no formato dd/MM/yyyy.
     * @param date
     * Data de cria��o da mensagem.
     */
    public void setDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat();
        //"dd/MM/yyyy"
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

