/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.comportamento;

import framework.mentalState.belief.Belief;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author heliokann
 */
public abstract class Comportamento {
    
    protected Set<Belief> crencas;
    
    public Comportamento(){
        crencas = new HashSet<Belief>();
        carregaCrencas();
    }

    protected abstract void carregaCrencas();
    
    public Set<Belief> getCrencas(){
        return crencas;
    }
    
}
