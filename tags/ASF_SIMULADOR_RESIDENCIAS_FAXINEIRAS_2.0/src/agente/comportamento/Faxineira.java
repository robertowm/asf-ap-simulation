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
public class Faxineira extends Comportamento{
    
    public Faxineira(){
        
    }
    
    @Override
    protected void carregaCrencas() {
        crencas.add(new LeafBelief("Double", "arruma", new Double(50d)));
        crencas.add(new LeafBelief("Double", "limpa", new Double(50d)));
    }
    
    

}
