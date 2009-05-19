/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

/**
 * Interface que apresenta um conjunto de estados baseado nos ciclos de vida dos
 * agentes e sub-organizações.
 */
public interface StatusAgentandSubOrganization 
{
	/**
	 * Estado de inicio em relação ao ciclo de vida de um agente ou sub-organização.
	 */
	public static String start="Start";
	/**
	 * Estado de execução em relação ao ciclo de vida de um agente ou sub-organização.
	 */	
	public static String running="Running";
	/**
	 * Estado de congelado em relação ao ciclo de vida de um agente ou sub-organização.
	 */	
	public static String frozen="Frozen";
	/**
	 * Estado de morto em relação ao ciclo de vida de um agente ou sub-organização.
	 */	
	public static String death="Death";
	/**
	 * Estado de migrando em relação ao ciclo de vida de um agente ou sub-organização.
	 */	
	public static String migrating="Migrating";
	
}
