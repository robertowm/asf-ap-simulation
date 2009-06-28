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
public class Funcionario extends Comportamento{
    
    public Funcionario(){
        
    }
    
    @Override
    protected void carregaCrencas() {
        crencas.add(new LeafBelief("Double", "arruma", new Double(20d)));
        crencas.add(new LeafBelief("Double", "dessarruma", new Double(15d)));
        crencas.add(new LeafBelief("Double", "suja", new Double(15d)));
        crencas.add(new LeafBelief("Double", "fazNada", new Double(50d)));
        crencas.add(new LeafBelief("boolean", "chamaEmpregada", true));
    }
    
    

}
