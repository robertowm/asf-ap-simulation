package framework.objectRole;

import java.io.Serializable;
import java.util.*;

import framework.FIPA.AgentPlatformDescription;
import framework.organization.MainOrganization;

/**
 * Classe abstrata responsável por representar as características estruturais e comportamentais
 * de um papel de objeto. 
 */
public abstract class ObjectRole implements Serializable {

    /**
     * Nome do papel de objeto.
     */
    protected String name = null;
    /**
     * @associates <{framework.organization.MainOrganization}>
     * @clientCardinality 1..*
     * @directed true
     * @label owned_by
     * @link association
     * @supplierCardinality 1
     */
    /**
     * Organização onde o papel de objeto está sendo desempenhado.
     */
    protected MainOrganization owner = null;
    /**
     * @associates <{Object}>
     * @link association
     * @label played_by
     * @clientCardinality 1..*
     * @supplierCardinality 1
     */
    /**
     * Objeto associado ao papel de objeto.
     */
    protected Object theObject = null;

    /**
     * Fornece a organização em que o papel de objeto está sendo desempenhado.
     * @return
     * Organização onde o papel de objeto está sendo desempenhado.
     */
    public MainOrganization getOwner() {
        return this.owner;
    }

    /**
     * Atribui a organização onde o papel de objeto será desempenhado.
     * @param newOwner
     * Organização onde o papel de objeto será desempenhado.
     */
    public void setOwner(MainOrganization newOwner) {
        this.owner = newOwner;

    }

    /**
     * Fornece o objeto que está associado ao papel de objeto.
     * @return
     * Objeto associado ao papel de objeto.
     */
    public Object getObject() {
        return this.theObject;

    }

    /**
     * Atribui o objeto que está associado ao papel de objeto.
     * @param newObject
     * Objeto associado ao papel de objeto.
     */
    public void setObject(Object newObject) {
        this.theObject = newObject;

    }

    /**
     * Método responsável por realizar a destruição do papel de objeto.
     */
    public void destroy() {
        //agent playing role
        setObject(null);

        //deleting role of the organization where it was being played
        MainOrganization organization = getOwner();
        Collection vRoles = organization.getObjectRoles();
        Iterator enumvRoles = vRoles.iterator();
        while (enumvRoles.hasNext()) {
            ObjectRole roleAux = (ObjectRole) enumvRoles.next();
            if (roleAux == this) {
                vRoles.remove(roleAux);
            }
        }

    }

    /**
     * Construtor da classe responsável por atribuir um valor booleano que indica se
     * o papel de objeto relacionado ao identificador, tem sua origem de criação
     * da plataforma local ou não, e além disso, atribui o seu nome.
     * @param name
     * Nome do papel de objeto. 
     * @param isLocal
     * Variável booleana que indica se o papel de objeto 
     * relacionado ao identificador, possui sua origem da plataforma local (true), 
     * ou de outra qualquer (false).
     */
    public void setObjectRoleName(String name, boolean isLocal) {
        if (!isLocal) {
            this.name = name;
        } else {
            String hap;
            hap = AgentPlatformDescription.getInstance().getName();
            // initialize the static variable atHAP, if not yet initialized
            if (hap == null) {
                throw new RuntimeException("Unknown Platform Name");
            }
            hap = "@" + hap;

            this.name = name.trim();

            this.name = name.concat(hap);

        }
    }

    /**
     * Fornece o nome que identifica o papeld e objeto.
     * @return
     * Nome que identifica o papel de objeto.
     */
    public String getObjectRoleName() {
        return name;
    }
}

