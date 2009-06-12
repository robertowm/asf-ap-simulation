package framework.mentalState.belief;

import java.io.Serializable;
import java.util.*;
/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma cren�a composta.  
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
     * Conjunto de cren�as que comp�e a cren�a composta.
     */
    private Collection beliefs = new Vector ();
    /**
     * Construtor da classe respons�vel por atribuir o tipo, o nome e o objeto associado
     * � cren�a composta.
     * @param type
     * Tipo da cren�a composta.
     * @param name
     * Nome da cren�a composta.
     * @param value
     * Objeto associado � cren�a composta.
     */
    public CompositeBelief (String type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;
    }
    /**
     * Fornece o conjunto de cren�as que comp�e a cren�a composta. 
     * @return
     * Conjunto das cren�as que comp�e a cren�a composta.
     */
    public Collection getSubBeliefs ()
    {
        return beliefs;
    }
    /**
     * Atribui uma nova cren�a ao conjunto de cren�as que comp�e a cren�a composta.
     * @param newBelief
     * Nova cren�a que comp�e a cren�a composta.
     */
    public void setSubBeliefs (Belief newBelief)
    {
        beliefs.add (newBelief);

    }
    /**
     * Remove uma cren�a do conjunto de cren�as que comp�e a cren�a composta. 
     * @param oldBelief
     * Cren�a que ser� removida do conjunto de cren�as que comp�e a cren�a composta.
     */
    public void removeSubBelief (Belief oldBelief)
    {
        beliefs.remove (oldBelief);

    }
    /**
     * M�todo respons�vel por procurar uma cren�a no conjunto de cren�as que comp�e
     * a cren�a composta. Caso a encontre, fornece a cren�a para a fun��o chamadora, 
     * caso contr�rio retorna null.  
     * @param type
     * Tipo da cren�a procurada.
     * @param name
     * Nome da cren�a procurada. 
     * @param value
     * Objeto associado � cren�a procurada.
     * @return
     * A cren�a procurada caso a encontre ou o valor null, caso
     * n�o a encontre.
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
