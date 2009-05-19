/*
 * Created on Jun 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.environment;

import java.io.Serializable;

import framework.FIPA.ElementID;


/**
 * @author andrew
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class MTS_Environment_Active extends MTS_Environment implements Runnable, Serializable
{

    /**
     * @param elementId
     */
    protected MTS_Environment_Active( ElementID elementId )
    {
        super( elementId );
        // TODO Auto-generated constructor stub
    }
	
}
