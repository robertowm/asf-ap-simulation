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
        crencas.add(new LeafBelief("Double", "arruma", new Double(15d)));
        crencas.add(new LeafBelief("Double", "dessarruma", new Double(15d)));
        crencas.add(new LeafBelief("Double", "limpa", new Double(10d)));
        crencas.add(new LeafBelief("double", "suja", new Double(60d)));
        crencas.add(new LeafBelief("int", "chamaEmpregada", true));
    }

}
