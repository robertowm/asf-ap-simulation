package framework.mentalState;

import java.io.Serializable;
import java.util.*;
/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de uma ação. 
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
	 * Conjunto de pós-condições da ação.
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
	 * Conjunto de pré-condições para que a ação aconteça.
	 */
    protected Collection postConditions = new Vector ();
    /**
     * Fornece o conjunto de pré-condições da ação.
     * @return
     * Conjunto de pré-condições da ação.
     */
    public Collection getPreConditions ()
    {
        return this.preConditions;
    }
    /**
     * Fornece o conjunto de pós-condições da ação.
     * @return
     * Conjunto de pós-condições da ação.
     */
    public Collection getPostConditions ()
    {
        return this.postConditions;
    }
    /**
     * Atribui uma nova pré-condição ao conjunto de pré-condições da ação. 
     * @param preCondition
     * Nova pré-condição da ação.
     */
    public void setPreCondition (Condition preCondition)
    {
        this.preConditions.add (preCondition);

    }
    /**
     * Atribui uma nova pós-condição ao conjunto de pós-condições da ação.
     * @param postCondition
     * Nova pós-condição da ação.
     */
    public void setPostCondition (Condition postCondition)
    {
        this.postConditions.add (postCondition);

    }
    /**
     * Método abstrato responsável por executar a ação,
     * e ao final deverá fornecer um valor do tipo boolean indicando 
     * o sucesso ou não da operação.
     * @return
     * Valor booleano, indicando o sucesso ou não da execução da ação.
     */
    public abstract boolean execute ();
}

