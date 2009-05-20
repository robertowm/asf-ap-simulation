/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.comportamento;

import framework.mentalState.belief.LeafBelief;

/**
 *
 * @author heliokann
 */
public class Equilibrado extends Comportamento{    
    
    public Equilibrado(){
        
    }

    @Override
    protected void carregaCrencas() {
        crencas.add(new LeafBelief("int", "dessarruma", 25));
        crencas.add(new LeafBelief("int", "arruma", 25));
        crencas.add(new LeafBelief("int", "limpa", 25));
        crencas.add(new LeafBelief("int", "suja", 25));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }

}
