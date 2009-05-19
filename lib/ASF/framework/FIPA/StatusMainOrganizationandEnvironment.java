/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

/**
 * Interface que apresenta um conjunto de estados baseado nos ciclos de vida das
 * organizações principais e dos ambientes.
 */
public interface StatusMainOrganizationandEnvironment 
{
	/**
	 * Estado de inicio em relação ao ciclo de vida de uma organização principal
	 * ou de um ambiente.
	 */
	public static String start="Start";
	/**
	 * Estado de execução em relação ao ciclo de vida de uma organização principal
	 * ou de um ambiente.
	 */
	public static String running="Running";
	/**
	 * Estado de morto em relação ao ciclo de vida de uma organização principal
	 * ou de um ambiente.
	 */
	public static String death="Death";
}
