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
        crencas.add(new LeafBelief("int", "arruma", 20));
        crencas.add(new LeafBelief("int", "dessarruma", 15));
        crencas.add(new LeafBelief("int", "suja", 15));
        crencas.add(new LeafBelief("int", "fazNada", 50));
        crencas.add(new LeafBelief("boolean", "chamaEmpregada", true));
    }
    
    

}
