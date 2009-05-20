/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objetivo;

import framework.mentalState.goal.LeafGoal;
import java.io.Serializable;

/**
 *
 * @author heliokann
 */
public class ResidirFeliz extends LeafGoal implements Serializable{
    
//    public ResidirFeliz(String tipo, String descricao, Object valor){
//        super(tipo, descricao, valor);
//    }
    
    public ResidirFeliz(){
        super("boolean", "Residir_Feliz",true);
    }

    public ResidirFeliz(boolean valor) {
        super("boolean", "Residir_Feliz",valor);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ResidirFeliz)){
            return false;
        }
        ResidirFeliz resi = (ResidirFeliz) obj;
        return (resi.name.equals(this.name) && resi.value.equals(this.value));
    }
    
    
    
}
