package framework.mentalState.goal;

import java.io.Serializable;
import java.util.*;
/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de um objetivo composto. 
 */
public class CompositeGoal extends Goal implements Serializable
{
    /**
    * @associates <{Goal}>
    * @link aggregation
    * @label composite
    * @clientCardinality 0..*
    * @supplierCardinality 1..*
    */
    /**
     * Conjunto de objetivos que comp�e o objetivo composto.
     */
    private Collection goals = new Vector ();
    /**
     * Construtor da classe respons�vel por atribuir o tipo, o nome e o objeto associado
     * ao objetivo composto.
     * @param type
     * Tipo do objetivo composto.
     * @param name
     * Nome do objetivo composto
     * @param value
     * Objeto associado ao objetivo composto.
     */
    public CompositeGoal (String type, String name, Object value)
    {
        this.valueType = type;
        this.name = name;
        this.value = value;

    }
    /**
     * Fornece o conjunto de objetivos que comp�e o objetivo composto.
     * @return 
     * Conjunto de objetivos que comp�e o objetivo composto.
     */
    public Collection getSubGoals()
    {
        return goals;
    }
    /**
     * Atribui um novo objetivo ao conjunto de objetivos que comp�e o objetivo composto.
     * @param newGoal
     * Novo objetivo que ir� compor o objetivo composto.
     */
    public void setSubGoal (Goal newGoal)
    {
        goals.add (newGoal);
    }
    /**
     * Realiza a remo��o de um objetivo do conjunto de objetivos que comp�e o objetivo 
     * composto.
     * @param oldGoal
     * Objetivo que ser� removido do conjunto de objetivos que comp�e o objetivo 
     * composto.
     */
    public void removeSubGoal (Goal oldGoal)
    {
        goals.remove (oldGoal);

    }
    /**
     * M�todo respons�vel por procurar um objetivo a partir de informa��es passadas por 
     * par�metro.
     * Caso o encontre, ele ser� fornecido � fun��o chamadora, caso contr�rio retorna null.
     * @param type
     * Tipo do objetivo procurado.
     * @param name
     * Nome do objetivo procurado.
     * @param value
     * Objeto associado ao objetivo procurado.
     * @return
     * O objetivo procurado, ou null caso n�o o encontre.
     */
    public Goal getSubGoal (String type, String name, Object value)
    {
        Goal goalAux = null;
        Iterator enumGoals = this.goals.iterator();
        while (enumGoals.hasNext())
        {
            goalAux = (Goal) enumGoals.next();
            if (goalAux.getName ().equals (name)) if (goalAux.getValueType ().equals (type)) if (goalAux.getValue ().equals (value))
              return goalAux;

        }
        return null;

    }
/*    public boolean checkSubGoalsName (String name)
    {
        Goal goalAux = null;
        Enumeration enumGoals = this.goals.elements ();
        while (enumGoals.hasMoreElements ())
        {
            goalAux = (Goal) enumGoals.nextElement ();
            if (goalAux.getName ().equals (name))
                return true;

        }
        return false;

    }
  */
/*    public void setAchieved (boolean achieved)
    {
        this.achieved = achieved;
        Goal goalAux = null;
        Enumeration enumGoals = this.goals.elements ();
        while (enumGoals.hasMoreElements ())
        {
            goalAux = (Goal) enumGoals.nextElement ();
            goalAux.setAchieved(achieved);
        }
    }
    public void setTryedToAchieve (boolean tryedToAchieve)
    {
        this.tryedToAchieve = tryedToAchieve;
        Goal goalAux = null;
        Enumeration enumGoals = this.goals.elements ();
        while (enumGoals.hasMoreElements ())
        {
            goalAux = (Goal) enumGoals.nextElement ();
            goalAux.setd(achieved);
        }
    }
    */
/*    public Hashtable getAchievedSubGoals ()
    {
        Hashtable hash = new Hashtable ();
        Goal goalAux = null;
        boolean achieved = false;
        Enumeration enumGoals = this.goals.elements ();
        while (enumGoals.hasMoreElements ())
        {
            goalAux = (Goal) enumGoals.nextElement ();
            achieved = goalAux.getAchieved();
            if (achieved)
	            hash.put(goalAux,"true");
            else
	            hash.put(goalAux,"false");
        }
        return hash;
    }
*/
}

