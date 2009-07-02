package framework.mentalState.goal;

import java.io.Serializable;

/**
 * Responsável por representar as características estruturais e comportamentais
 * de um objetivo simples. 
 */
public class LeafGoal extends Goal implements Serializable
{
	/**
	 * Construtor da classe responsável por atribuir o tipo, o nome e o 
	 * objeto associado ao objetivo simples. 
	 * ao objetivo.
	 * @param newType
	 * Tipo do objetivo simples.
	 * @param newName
	 * Nome do objetivo simples.
	 * @param newValue
	 * Objeto associado ao objetivo simples.
	 */
    public LeafGoal (String newType, String newName, Object newValue)
    {
        this.valueType = newType;
        this.name = newName;
        this.value = newValue;

    }
}

