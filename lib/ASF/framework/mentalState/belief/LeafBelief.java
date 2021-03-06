/* Generated by Together */

package framework.mentalState.belief;

import java.io.Serializable;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma cren�a simples.  
 */
public class LeafBelief extends Belief implements Serializable 
{
	/**
	 * Construtor da classe respons�vel por atribuir o tipo, o nome e o objeto
	 * associado � cren�a simples.
	 * 
	 * @param type
	 * Tipo da cren�a simples.
	 * @param name
	 * Nome da cren�a simples.
	 * @param value
	 * Objeto associado � cren�a simples.
	 */
    public LeafBelief (String type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
