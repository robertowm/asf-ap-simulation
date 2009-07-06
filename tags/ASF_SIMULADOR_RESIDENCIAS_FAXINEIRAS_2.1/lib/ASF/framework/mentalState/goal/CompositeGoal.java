package framework.mentalState.goal;

import java.io.Serializable;
import java.util.*;
/**
 * Responsável por representar as características estruturais e comportamentais
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
     * Conjunto de objetivos que compõe o objetivo composto.
     */
    private Collection goals = new Vector ();
    /**
     * Construtor da classe responsável por atribuir o tipo, o nome e o objeto associado
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
     * Fornece o conjunto de objetivos que compõe o objetivo composto.
     * @return 
     * Conjunto de objetivos que compõe o objetivo composto.
     */
    public Collection getSubGoals()
    {
        return goals;
    }
    /**
     * Atribui um novo objetivo ao conjunto de objetivos que compõe o objetivo composto.
     * @param newGoal
     * Novo objetivo que irá compor o objetivo composto.
     */
    public void setSubGoal (Goal newGoal)
    {
        goals.add (newGoal);
    }
    /**
     * Realiza a remoção de um objetivo do conjunto de objetivos que compõe o objetivo 
     * composto.
     * @param oldGoal
     * Objetivo que será removido do conjunto de objetivos que compõe o objetivo 
     * composto.
     */
    public void removeSubGoal (Goal oldGoal)
    {
        goals.remove (oldGoal);

    }
    /**
     * Método responsável por procurar um objetivo a partir de informações passadas por 
     * parâmetro.
     * Caso o encontre, ele será fornecido à função chamadora, caso contrário retorna null.
     * @param type
     * Tipo do objetivo procurado.
     * @param name
     * Nome do objetivo procurado.
     * @param value
     * Objeto associado ao objetivo procurado.
     * @return
     * O objetivo procurado, ou null caso não o encontre.
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

