package framework.mentalState;

import java.io.Serializable;
import java.util.*;
/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma a��o. 
 */
public abstract class Action implements Serializable
{
	/**
	 * @associates <{framework.mentalState.Condition}>
	 * @clientCardinality 1..*
	 * @directed true
	 * @link association
	 * @supplierCardinality 0..*
	 * @supplierRole preCondition
	 */
	/**
	 * Conjunto de p�s-condi��es da a��o.
	 */
    protected Collection preConditions = new Vector ();
	/**
	 * @associates <{framework.mentalState.Condition}>
	 * @clientCardinality 1..*
	 * @directed true
	 * @link association
	 * @supplierCardinality 0..*
	 * @supplierRole postCondition
	 */
	/**
	 * Conjunto de pr�-condi��es para que a a��o aconte�a.
	 */
    protected Collection postConditions = new Vector ();
    /**
     * Fornece o conjunto de pr�-condi��es da a��o.
     * @return
     * Conjunto de pr�-condi��es da a��o.
     */
    public Collection getPreConditions ()
    {
        return this.preConditions;
    }
    /**
     * Fornece o conjunto de p�s-condi��es da a��o.
     * @return
     * Conjunto de p�s-condi��es da a��o.
     */
    public Collection getPostConditions ()
    {
        return this.postConditions;
    }
    /**
     * Atribui uma nova pr�-condi��o ao conjunto de pr�-condi��es da a��o. 
     * @param preCondition
     * Nova pr�-condi��o da a��o.
     */
    public void setPreCondition (Condition preCondition)
    {
        this.preConditions.add (preCondition);

    }
    /**
     * Atribui uma nova p�s-condi��o ao conjunto de p�s-condi��es da a��o.
     * @param postCondition
     * Nova p�s-condi��o da a��o.
     */
    public void setPostCondition (Condition postCondition)
    {
        this.postConditions.add (postCondition);

    }
    /**
     * M�todo abstrato respons�vel por executar a a��o,
     * e ao final dever� fornecer um valor do tipo boolean indicando 
     * o sucesso ou n�o da opera��o.
     * @return
     * Valor booleano, indicando o sucesso ou n�o da execu��o da a��o.
     */
    public abstract boolean execute ();
}

