package framework.agentRole;

import java.io.Serializable;
import java.util.*;
/**
 * Responsável por representar as características estruturais e comportamentais
 * de um dever. 
 */
public class Duty implements Serializable
{
    /**
    * @associates <{Action}>
    * @link association
    * @clientCardinality 0..1
    * @supplierCardinality 1
    */
    /**
     * Conjunto de ações que são associadas ao dever. Estas ações são realizadas para 
     * concretizar planos, e assim, tentar alcançar objetivos.
     */
    private Collection actions = new Vector ();
    
    /**
     * Construtor da classe que recebe uma ação que é associada a um dever. 
     * @param newAction
     * Nova ação associada ao dever.
     */
    public Duty (String newAction)
    {
        this.actions.add (newAction);

    }
	/**
	* Fornece o conjunto de ações associadas ao dever.
	* @return
	* Todas as ações associadas ao dever.
	*/
    public Collection getActions ()
    {
        return this.actions;

    }
    
	/**
	* Atribui uma nova ação ao conjunto de ações associado ao dever. 
	* @param newAction
	* Nova ação associada ao dever.
	*/
    public void setAction (String newAction)
    {
        this.actions.add (newAction);
    }
}

