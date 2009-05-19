/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

/**
 * Interface que apresenta um conjunto de estados baseado nos ciclos de vida das
 * organiza��es principais e dos ambientes.
 */
public interface StatusMainOrganizationandEnvironment 
{
	/**
	 * Estado de inicio em rela��o ao ciclo de vida de uma organiza��o principal
	 * ou de um ambiente.
	 */
	public static String start="Start";
	/**
	 * Estado de execu��o em rela��o ao ciclo de vida de uma organiza��o principal
	 * ou de um ambiente.
	 */
	public static String running="Running";
	/**
	 * Estado de morto em rela��o ao ciclo de vida de uma organiza��o principal
	 * ou de um ambiente.
	 */
	public static String death="Death";
}
