/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objetivo;

import framework.mentalState.goal.LeafGoal;

/**
 *
 * @author heliokann
 */
public class ResidirFeliz extends LeafGoal{
    
//    public ResidirFeliz(String tipo, String descricao, Object valor){
//        super(tipo, descricao, valor);
//    }
    
    public ResidirFeliz(){
        super("boolean", "Residir_Feliz",true);
    }
    
}
