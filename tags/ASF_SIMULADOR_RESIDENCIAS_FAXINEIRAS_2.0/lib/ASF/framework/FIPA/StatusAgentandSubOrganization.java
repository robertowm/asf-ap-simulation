/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

/**
 * Interface que apresenta um conjunto de estados baseado nos ciclos de vida dos
 * agentes e sub-organiza��es.
 */
public interface StatusAgentandSubOrganization 
{
	/**
	 * Estado de inicio em rela��o ao ciclo de vida de um agente ou sub-organiza��o.
	 */
	public static String start="Start";
	/**
	 * Estado de execu��o em rela��o ao ciclo de vida de um agente ou sub-organiza��o.
	 */	
	public static String running="Running";
	/**
	 * Estado de congelado em rela��o ao ciclo de vida de um agente ou sub-organiza��o.
	 */	
	public static String frozen="Frozen";
	/**
	 * Estado de morto em rela��o ao ciclo de vida de um agente ou sub-organiza��o.
	 */	
	public static String death="Death";
	/**
	 * Estado de migrando em rela��o ao ciclo de vida de um agente ou sub-organiza��o.
	 */	
	public static String migrating="Migrating";
	
}
