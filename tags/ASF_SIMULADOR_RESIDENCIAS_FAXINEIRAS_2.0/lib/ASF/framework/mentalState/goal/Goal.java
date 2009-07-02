package framework.mentalState.goal;

import java.io.Serializable;
import java.util.*;

import framework.mentalState.Plan;
/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de um objetivo. 
 */
public abstract class Goal implements Serializable
{
	/**
	 * Tipo do objetivo.
	 */
    protected String valueType = null;
    /**
     * Nome do objetivo.
     */
    protected String name = null;
    /**
     * Objeto associado ao objetivo.
     */
    protected Object value = null;
    /**
     * Tipo do objetivo.
     */
    protected String goalType = null;
    /**
     * Indica se o objetivo foi alcan�ado ou n�o.
     */
    protected boolean achieved = false;
    /**
     * Indica se ocorreu a tentativa de alcan�ar o objetivo. 
     */
    protected boolean tryedToAchieve = false;
    /**
     * N�vel de prioridade para alcan�ar o objetivo. 
     */
    protected int priority = 0;
    /**
     * Conjunto de planos do objetivo.
     */
    protected Collection associatedPlans = new Vector ();
   
	
	/**
	 * Fornece o tipo do objetivo.
	 * @return
	 * Tipo do objetivo.
	 */
    public String getValueType ()
    {
        return this.valueType;
    }
    /**
     * Fornece o tipo do objetivo.
     * @return
     * Tipo do objetivo.
     */
    public String getGoalType ()
    {
        return this.goalType;
    }
    /**
     * Fornece o nome do objetivo.
     * @return
     * Nome do objetivo.
     */
    public String getName ()
    {
        return this.name;
    }
    /**
     * Fornece o objeto associado ao objetivo.
     * @return
     * Objeto associado ao objetivo.
     */
    public Object getValue ()
    {
        return this.value;
    }
    /**
     * Fornece um valor booleano indicando se o objetivo foi alcan�ado ou n�o.
     * @return
     * Valor booleano indicando se o objetivo foi alcan�ado ou n�o. 
     */
    public boolean getAchieved ()
    {
        return this.achieved;
    }
    /**
     * Fornece o conjunto de planos do objetivo.
     * @return
     * Cole��o de planos do objetivo.
     */
    public Collection getPlans ()
    {
        return this.associatedPlans;
    }
    /**
     * Fornece o conjunto de subobjetivos do objetivo.
     * @return
     * Subobjetivos do objetivo. 
     */
    public Collection getSubGoals ()
    {
        return null;
    }
    /**
     * Fornece o grau de prioridade para alcan�ar o objetivo.
     * @return
     * Grau de prioridade do objetivo.
     */
    public int getPriority ()
    {
        return priority;
    }
    /**
     * Fornece um valor booleano indicando se o objetivo tentou ser alcan�ado. 
     * @return
     * Valor bolleano indicando se o objetivo tentou ser alcan�ado.
     */
    public boolean getTryedToAchieve ()
    {
        return tryedToAchieve;
    }
    /**
     * Atribui um novo tipo ao objetivo.
     * @param type
     * Novo tipo do objetivo.
     */
    public void setValueType (String type)
    {
        this.valueType = type;
    }
    /**
     * Atribui um novo tipo ao objetivo.
     * @param type
     * Novo tipo do objetivo.
     */
    public void setGoalType (String type)
    {
        this.goalType = type;
    }
    /**
     * Atribui um novo nome ao objetivo.
     * @param name
     * Novo nome do objetivo.
     */
    public void setName (String name)
    {
        this.name = name;

    }
    /**
     * Atribui um novo objeto associado ao objetivo.
     * @param value
     * Novo objeto associado ao objetivo.
     */
    public void setValue (Object value)
    {
        this.value = (Object) value;

    }
    /**
     * Atribui um valor bolleano que indica se o objetivo foi alcan�ado ou n�o. 
     * @param achieved
     * Valor bolleano que indica se o objetivo foi alcan�ado ou n�o.
     */
    public void setAchieved (boolean achieved)
    {
        this.achieved = achieved;

    }
    /**
     * Atribui um novo plano ao conjunto de planos do objetivo.
     * @param associatedPlan
     * Novo plano do objetivo.
     */    
    public void setPlan (Plan associatedPlan)
    {
        this.associatedPlans.add (associatedPlan);

    }
    /**
     * Atribui o grau de prioridade de alcance do objetivo.
     * @param priority
     * Valor inteiro que indica a prioridade de alcance do objetivo.
     */
    public void setPriority (int priority)
    {
        this.priority = priority;
    }
    /**
     * Atribui um valor booleano na vari�vel tryedToAchieve, indicando
     * se houve alguma tentativa de alcan�ar o objetivo.   
     * @param tryedToAchieve
     * Valor booleano indicando se houve a tentativa de alcan�ar
     * o objetivo. Se for "true" houve a tentativa, caso contr�rio ser� "false". 
     */
    public void setTryedToAchieve (boolean tryedToAchieve)
    {
        this.tryedToAchieve = tryedToAchieve;
    }
}

