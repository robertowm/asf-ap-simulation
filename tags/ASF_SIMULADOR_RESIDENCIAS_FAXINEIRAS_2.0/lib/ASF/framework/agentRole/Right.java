package framework.agentRole;

import java.io.Serializable;
import java.util.*;
/**
 * Responsável por representar as características estruturais e comportamentais
 * de um direito. 
 */
public class Right implements Serializable
{
    /**
    * @associates <{Action}>
    * @link association
    * @clientCardinality 0..1
    * @supplierCardinality 1
    */
    /**
     * Conjunto de ações que são associadas ao direito. Estas ações são realizadas 
     * para concretizar planos, e assim, tentar alcançar objetivos.
     */
    private Collection actions = new Vector ();
    
    /**
     * Construtor da classe que recebe uma ação que é associada a um 
     * direito.
     * @param newAction
     * Nova ação que fará parte do conjunto das ações.  
     */
    public Right (String newAction)
    {
        this.actions.add (newAction);

    }
    /**
     * Fornece o conjunto de ações associadas ao direito.
     * @return
     * Todas as ações associadas ao direito.
     */
    public Collection getActions ()
    {
        return this.actions;

    }
    /**
     * Atribui uma nova ação ao conjunto de ações associado ao direito.
     * @param newAction
     * Nova ação associada ao direito.
     */
    public void setAction (String newAction)
    {
        this.actions.add (newAction);
    }
}

