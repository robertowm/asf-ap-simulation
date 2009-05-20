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
        crencas.add(new LeafBelief("int", "dessarruma", 40));
        crencas.add(new LeafBelief("int", "arruma", 10));
        crencas.add(new LeafBelief("int", "limpa", 10));
        crencas.add(new LeafBelief("int", "suja", 40));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }
   
}
