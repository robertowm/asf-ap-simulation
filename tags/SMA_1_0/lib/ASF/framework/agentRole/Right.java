package framework.agentRole;

import java.io.Serializable;
import java.util.*;
/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
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
     * Conjunto de a��es que s�o associadas ao direito. Estas a��es s�o realizadas 
     * para concretizar planos, e assim, tentar alcan�ar objetivos.
     */
    private Collection actions = new Vector ();
    
    /**
     * Construtor da classe que recebe uma a��o que � associada a um 
     * direito.
     * @param newAction
     * Nova a��o que far� parte do conjunto das a��es.  
     */
    public Right (String newAction)
    {
        this.actions.add (newAction);

    }
    /**
     * Fornece o conjunto de a��es associadas ao direito.
     * @return
     * Todas as a��es associadas ao direito.
     */
    public Collection getActions ()
    {
        return this.actions;

    }
    /**
     * Atribui uma nova a��o ao conjunto de a��es associado ao direito.
     * @param newAction
     * Nova a��o associada ao direito.
     */
    public void setAction (String newAction)
    {
        this.actions.add (newAction);
    }
}

