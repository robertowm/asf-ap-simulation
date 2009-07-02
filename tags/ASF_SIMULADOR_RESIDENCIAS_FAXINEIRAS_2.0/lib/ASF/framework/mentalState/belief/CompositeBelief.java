package framework.mentalState.belief;

import java.io.Serializable;
import java.util.*;
/**
 * Responsável por representar as características estruturais e comportamentais
 * de uma crença composta.  
 */
public class CompositeBelief extends Belief implements Serializable
{
    /**
    * @associates <{Belief}>
    * @link aggregation
    * @label composite
    * @clientCardinality 0..*
    * @supplierCardinality 1..*
    */
    /**
     * Conjunto de crenças que compõe a crença composta.
     */
    private Collection beliefs = new Vector ();
    /**
     * Construtor da classe responsável por atribuir o tipo, o nome e o objeto associado
     * à crença composta.
     * @param type
     * Tipo da crença composta.
     * @param name
     * Nome da crença composta.
     * @param value
     * Objeto associado à crença composta.
     */
    public CompositeBelief (String type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;
    }
    /**
     * Fornece o conjunto de crenças que compõe a crença composta. 
     * @return
     * Conjunto das crenças que compõe a crença composta.
     */
    public Collection getSubBeliefs ()
    {
        return beliefs;
    }
    /**
     * Atribui uma nova crença ao conjunto de crenças que compõe a crença composta.
     * @param newBelief
     * Nova crença que compõe a crença composta.
     */
    public void setSubBeliefs (Belief newBelief)
    {
        beliefs.add (newBelief);

    }
    /**
     * Remove uma crença do conjunto de crenças que compõe a crença composta. 
     * @param oldBelief
     * Crença que será removida do conjunto de crenças que compõe a crença composta.
     */
    public void removeSubBelief (Belief oldBelief)
    {
        beliefs.remove (oldBelief);

    }
    /**
     * Método responsável por procurar uma crença no conjunto de crenças que compõe
     * a crença composta. Caso a encontre, fornece a crença para a função chamadora, 
     * caso contrário retorna null.  
     * @param type
     * Tipo da crença procurada.
     * @param name
     * Nome da crença procurada. 
     * @param value
     * Objeto associado à crença procurada.
     * @return
     * A crença procurada caso a encontre ou o valor null, caso
     * não a encontre.
     */
    public Belief getSubBelief (String type, String name, Object value)
    {
        Belief beliefAux = null;
        Iterator enumBeliefs = this.beliefs.iterator();
        while (enumBeliefs.hasNext())
        {
            beliefAux = (Belief) enumBeliefs.next();
            if (beliefAux.getName ().equals (name)) if (beliefAux.getValue ().equals (type)) if (beliefAux.getValue ().equals (value))
              return beliefAux;
        }
        return null;
    }
}
