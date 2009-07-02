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
public class Bagunceiro extends Comportamento{
    
    public Bagunceiro(){
        
    }
    
    @Override
    protected void carregaCrencas() {
        crencas.add(new LeafBelief("int", "dessarruma", 60));
        crencas.add(new LeafBelief("int", "arruma", 15));
        crencas.add(new LeafBelief("int", "limpa", 15));
        crencas.add(new LeafBelief("int", "suja", 10));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }
    
    

}