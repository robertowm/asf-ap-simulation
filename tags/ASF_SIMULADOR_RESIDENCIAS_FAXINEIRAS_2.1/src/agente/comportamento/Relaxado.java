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
public class Relaxado extends Comportamento{
    
    public Relaxado(){
        
    }

    @Override
    protected void carregaCrencas() {
        crencas.add(new LeafBelief("Double", "arruma", new Double(10d)));
        crencas.add(new LeafBelief("Double", "dessarruma", new Double(40d)));
        crencas.add(new LeafBelief("Double", "limpa", new Double(10d)));
        crencas.add(new LeafBelief("Double", "suja", new Double(40d)));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }
   
}
