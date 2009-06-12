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
public class NaoHigienico extends Comportamento{
    
    public NaoHigienico(){
        
    }

    @Override
    protected void carregaCrencas() {
       crencas.add(new LeafBelief("int", "dessarruma", 15));
        crencas.add(new LeafBelief("int", "arruma", 15));
        crencas.add(new LeafBelief("int", "limpa", 10));
        crencas.add(new LeafBelief("int", "suja", 60));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }

}
