package framework.mentalState.belief;

import java.io.Serializable;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de uma crença.
 */
public abstract class Belief implements Serializable
{
    /**
     * Tipo da crença.
     */
    protected String type = null;
    /**
     * Nome da crença.
     */
    protected String name = null;
    /**
     * Objeto associado à crença.
     */
    protected Object value = null;
    /**
     * Fornece o tipo da crença.
     * @return
     * Tipo da crença.
     */
    public String getType ()
    {
        return this.type;
    }
    /**
     * Fornece o nome da crença.
     * @return
     * Nome da crença.
     */
    public String getName ()
    {
        return this.name;

    }
    /**
     * Fornece o objeto associado à crença.
     * @return
     * Objeto associado à crença.
     */
    public Object getValue ()
    {
        return this.value;
    }
    /**
     * Atribui um novo tipo à crença.
     * @param type
     * Novo tipo da crença.
     */
    public void setType (String type)
    {
        this.type = type;

    }
    /**
     * Atribui um novo nome à crença.
     * @param name
     * Novo nome da crença.
     */
    public void setName (String name)
    {
        this.name = name;

    }
    /**
     * Atribui um novo objeto associado à crença.
     * @param value
     * Novo objeto associado à crença.
     */
    public void setValue (Object value)
    {
        this.value = value;

    }
}

