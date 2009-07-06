/*
 * Created on 17/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

/**
 * Interface que apresenta um conjunto de estados baseado no ciclo de vida dos
 * pap�is.
 */
public interface StatusAgentRole 
{
	/**
	 * Estado de inicio em rela��o ao ciclo de vida de um papel.
	 */
	public static String start="Start";
	/**
	 * Estado de ativo em rela��o ao ciclo de vida de um papel.
	 */
	public static String active="Active";
	/**
	 * Estado de inativo em rela��o ao ciclo de vida de um papel.
	 */
	public static String inactive="Inactive";
	/**
	 * Estado de morto em rela��o ao ciclo de vida de um papel.
	 */
	public static String death="Death";
}
