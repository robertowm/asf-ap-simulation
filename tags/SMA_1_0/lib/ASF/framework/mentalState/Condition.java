package framework.mentalState;

import java.io.Serializable;

/**
 * Responsável por representar as características estruturais e comportamentais
 * de uma condição. 
 */
public class Condition implements Serializable
{
    /**
     * Tipo da condição. 
     */
    private String type = null;
    /**
     * Nome da condição.
     */
    private String name = null;
    /**
     * Valor da condição.
     */
    private Object value = null;
    
    /**
     * Construtor da classe responsável por atribuir o tipo, nome e o objeto
     * associado à condição.
     * @param type
     * Tipo da condição.
     * @param name
     * Nome da condição.
     * @param value
     * Objeto associado à condição.
     */
    public Condition (String type, String name, Object value)
    {
        this.type = type;
        this.name = name;
        this.value = value;

    }
    /**
     * Fornece o tipo da condição.
     * @return
     * Tipo da condição.
     */
    public String getType ()
    {
        return this.type;

    }
    /**
     * Fornece o nome da condição.
     * @return
     * Nome da condição.
     */
    public String getName ()
    {
        return this.name;

    }
    /**
     * Fornece o objeto associado à condição.
     * @return
     * Objeto associado à condição.
     */
    public Object getValue ()
    {
        return this.value;

    }
    /**
     * Atribui um novo tipo à condição.
     * @param type
     * Novo tipo da condição. 
     */
    public void setType (String type)
    {
        this.type = type;

    }
    /**
     * Atribui um novo nome à condição. 
     * @param name
     * Novo nome da condição.
     */
    public void setName (String name)
    {
        this.name = name;

    }
    /**
     * Atribui um novo objeto a condição.
     * @param value
     * Novo objeto associado à condição.
     */
    public void setValue (Object value)
    {
        this.value = value;

    }
}

