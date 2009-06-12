package framework.organization;

import java.io.Serializable;

/**
 * Responsável por representar as características estruturais e comportamentais
 * de um axioma. 
 */
public class Axiom implements Serializable
{
	/**
	 * Tipo do axioma.
	 */
    private String type = null;
    /**
     * Nome do axioma.
     */
    private String name = null;
    /**
     * Objeto associado ao axioma.
     */
    private Object value = null;
    /**
     * Construtor da classe responsável por atribuir o tipo, o nome e o objeto 
     * associado ao axioma.
     * @param type
     * Tipo do axioma.
     * @param name
     * Nome do axioma.
     * @param value
     * Objeto associado ao axioma.
     */
    public Axiom (String type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;
    }
    /**
     * Fornece o tipo do axioma.
     * @return
     * Tipo do axioma.
     */
    public String getType ()
    {
        return this.type;

    }
    /**
     * Fornece o nome do axioma.
     * @return
     * Nome do axioma.
     */
    public String getName ()
    {
        return this.name;

    }
    /**
     * Fornece o objeto associado ao axioma.
     * @return
     * Objeto associado ao axioma.
     */
    public Object getValue ()
    {
        return this.value;

    }
    /**
     * Atribui um novo tipo ao axioma.
     * @param type
     * Novo tipo do axioma.
     */
    public void setType (String type)
    {
        this.type = type;

    }
    /**
     * Atribui um novo nome ao axioma.
     * @param name
     * Novo nome do axioma.
     */
    public void setName (String name)
    {
        this.name = name;

    }
    /**
     * Atribui um novo objeto associado ao axioma.
     * @param value
     * Novo objeto associado ao axioma. 
     */
    public void setValue (Object value)
    {
        this.value = value;

    }
}

