/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.papel;

import framework.agentRole.AgentRole;
import framework.mentalState.belief.LeafBelief;

/**
 *
 * @author heliokann
 */
public class Empregada extends AgentRole{
    
    boolean manterResidenciaHabitavel;

    public Empregada(){
        this.setBelief(new LeafBelief("int", "limpa", 50));
        this.setBelief(new LeafBelief("int", "arruma", 50));
    }
    
}
