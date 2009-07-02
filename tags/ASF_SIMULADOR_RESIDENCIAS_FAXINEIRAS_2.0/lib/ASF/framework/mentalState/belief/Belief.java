package framework.mentalState.belief;

import java.io.Serializable;

/**
 * Classe abstrata respons�vel por representar as caracter�sticas estruturais e comportamentais
 * de uma cren�a.
 */
public abstract class Belief implements Serializable
{
    /**
     * Tipo da cren�a.
     */
    protected String type = null;
    /**
     * Nome da cren�a.
     */
    protected String name = null;
    /**
     * Objeto associado � cren�a.
     */
    protected Object value = null;
    /**
     * Fornece o tipo da cren�a.
     * @return
     * Tipo da cren�a.
     */
    public String getType ()
    {
        return this.type;
    }
    /**
     * Fornece o nome da cren�a.
     * @return
     * Nome da cren�a.
     */
    public String getName ()
    {
        return this.name;

    }
    /**
     * Fornece o objeto associado � cren�a.
     * @return
     * Objeto associado � cren�a.
     */
    public Object getValue ()
    {
        return this.value;
    }
    /**
     * Atribui um novo tipo � cren�a.
     * @param type
     * Novo tipo da cren�a.
     */
    public void setType (String type)
    {
        this.type = type;

    }
    /**
     * Atribui um novo nome � cren�a.
     * @param name
     * Novo nome da cren�a.
     */
    public void setName (String name)
    {
        this.name = name;

    }
    /**
     * Atribui um novo objeto associado � cren�a.
     * @param value
     * Novo objeto associado � cren�a.
     */
    public void setValue (Object value)
    {
        this.value = value;

    }
}

