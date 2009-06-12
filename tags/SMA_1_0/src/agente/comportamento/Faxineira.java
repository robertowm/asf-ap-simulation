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
        crencas.add(new LeafBelief("int", "arruma", 50));
        crencas.add(new LeafBelief("int", "limpa", 50));
    }
    
    

}
