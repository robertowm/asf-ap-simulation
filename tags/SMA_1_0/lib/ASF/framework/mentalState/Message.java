package framework.mentalState;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import framework.FIPA.ElementID;

/**
 * Responsável por representar as características estruturais e comportamentais
 * de uma mensagem no formato ACL. 
 */
public class Message implements Serializable {
    // ACL parameters

    /**
     * Define o remetente da mensagem. 
     */
    private ElementID from = null;
    /**
     * Define o(s) destinatário(s) da mensagem.
     */
    private List to = new Vector();
    /**
     * Comentário da mensagem.
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
     * Contem a codificação na qual a linguagem do conteúdo é expressa.
     */
    private String payloadEncoding = null;
    /**
     * Contêm a data e a hora de criação da mensagem.
     */
    private Date date = null;
    /**
     * Conjunto de nome dos agentes a quem essa instância da mensagem deve ser 
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
     * Denota o conteúdo da mensagem. Equivalente ao objeto da ação.
     */
    private Object content = null;
    /**
     * Linguagem na qual o conteúdo é expresso.
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
     * Introduz uma expressão que deve ser usada para identificar uma seqüência de 
     * mensagens em uma conversação.
     */
    private String conversationId = null;
    /**
     * Indica a expressão/mensagem a qual a mensagem em questão está se referindo.
     */
    private String inReplyTo = null;
    /**
     * Introduz uma expressão que deve ser utilizada pelo agente receptor para 
     * responder a mensagem sendo enviada.
     */
    private String replyWith = null;
    /**
     * Detona o tipo de ato da fala utilizado na mensagem.
     */
    private String performative = null;
    /**
     * Denota a codificação na qual a linguagem do conteúdo é expressa.
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
     * Construtor da classe que atribui uma série de informações à mensagem.
     * @param conversationId
     * Identificador de uma sequência de mensagens de uma conversação.
     * @param newContent
     * Conteúdo da mensagem.
     * @param fromName
     * Nome do remetente da mensagem.
     * @param toName
     * Destinatário(s) da mensagem. 
     */
    public Message(String conversationId, Object newContent, ElementID fromName, List toName) {
        this.conversationId = conversationId;
        this.content = newContent;
        this.from = fromName;
        this.to = toName;
    }

    /**
     * Construtor da classe, que atribui uma série de informações a mensagem.
     * @param conversationId
     * Identificador de uma sequência de mensagens de uma conversação.
     * @param newContent
     * Conteúdo da mensagem.
     * @param fromName
     * Nome do remetente da mensagem.
     * @param newTo
     * Destinatário da mensagem.
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
     * Fornece o(s) destinatário(s) da mensagem.
     * @return
     * Destinatário(s) da mensagem.
     */
    public List getTo() {
        return this.to;
    }

    /**
     * Fornece o identificador de uma seqüência de mensagens de uma conversação.
     * @return
     * Identificador de uma seqüência de mensagens de uma conversação.
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
     * Fornece o conteúdo da mensagem.
     * @return
     * Conteúdo da mensagem.
     */
    public Object getContent() {
        return this.content;
    }

    /**
     * Fornece a linguagem na qual o conteúdo é expresso.
     * @return
     * Linguagem na qual o conteúdo é expresso.
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
     * Fornece uma expressão/mensagem a qual a mensagem em questão está se 
     * referindo.
     * @return
     * Expressão/mensagem a qual a mensagem em questão está se referindo.
     */
    public String getInReplyTo() {
        return this.inReplyTo;
    }

    /**
     * Fornece a expressão utilizada peloagente receptor para responder a mensagem
     * sendo enviada.
     * @return
     * Expressão utilizada peloagente receptor para responder a mensagem
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
     * Fornece a codificação na qual a linguagem do conteúdo é expressa.
     * @return
     * Codificação na qual a linguagem do conteúdo é expressa.
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
     * Fornece o comentário da mensagem.
     * @return
     * Comentário da mensagem.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Fornece a data e a hora da criação da mensagem.
     * @return
     * Data e a hora da criação da mensagem.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Fornece o conjunto de nome dos agentes a quem essa instância da mensagem deve ser 
     * entregue.
     * @return
     * Conjunto de nome dos agentes a quem essa instância da mensagem deve ser 
     * entregue.
     */
    public Collection getIntendedReceiver() {
        return intendedReceiver;
    }

    /**
     * Fornece a codificação na qual a linguagem do conteúdo é expressa.
     * @return
     * Codificação na qual a linguagem do conteúdo é expressa.
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
     * Fornece o stamp indicador da recepção da mensagem por um ACC.
     * @return
     * Stamp indicador da recepção da mensagem por um ACC.
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
     * Inclui o nome de um destinatário na lista que contêm todos aqueles que irão 
     * receber a mensagem.
     * @param toName
     * Nome de um novo destinatário.
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
     * Atribui um novo conteúdo a mensagem.
     * @param newContent
     * Novo conteúdo da mensagem. 
     */
    public void setContent(Object newContent) {
        this.content = newContent;
    }

    /**
     * Atribui a linguagem na qual o conteúdo da mensagem é expresso. 
     * @param language
     * Linguagem no qual o conteúdo da mensagem é expresso.
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
     * Atribui um identificador de uma seqüência de mensagens de uma conversação. 
     * @param conversationId
     * Identificador de uma seqüência de mensagens de uma conversação.
     */
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * Atribui a expressão/mensagem a qual a mensagem em questão está se referindo.
     * @param inReplyTo
     * Nova expressão/mensagem a qual a mensagem em questão está se referindo.
     */
    public void setInReplyTo(String inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    /**
     * Atribui uma expressão que deve ser utilizada pelo agente receptor 
     * para responder a mensagem sendo enviada. 
     * @param replyWith
     * Expressão que deve ser utilizada pelo agente receptor 
     * para responder a mensagem sendo enviada.
     */
    public void setReplyWith(String replyWith) {
        this.replyWith = replyWith;
    }

    /**
     * Atribui o tipo de ato da fala que será utilizado pela mensagem.
     * @param performative
     * Tipo de ato da fala que será utilizado pela mensagem.
     */
    public void setPerformative(String performative) {
        this.performative = performative;
    }

    /**
     * Atribui um novo codificador na qual a linguagem do conteúdo é expressa.
     * @param encoding
     * Codificador na qual a linguagem do conteúdo é expressa.
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
     * Atribui o comentário da mensagem.
     * @param string
     * Comentário da mensagem.
     */
    public void setComments(String string) {
        comments = string;
    }

    /**
     * Atribui a data e hora de criação da mensagem. 
     * 
     */
    public void setDate() {
        date = new Date();
    }

    /**
     * Atribui o conjunto de nome dos agentes a quem essa instância da mensagem deve ser 
     * entregue.
     * @param collection
     * Conjunto de nome dos agentes a quem essa instância da mensagem deve ser 
     * entregue.
     */
    public void setIntendedReceiver(Collection collection) {
        intendedReceiver = collection;
    }

    /**
     * Atribui a codificação na qual a linguagem do conteúdo é expressa.
     * @param string
     * Codificação na qual a linguagem do conteúdo é expressa.
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
     * Atribui o stamp indicador da recepção da mensagem por um ACC. 
     * @param object
     * Atribui o stamp indicador da recepção da mensagem por um ACC.
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
     * Data de criação da mensagem.
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

