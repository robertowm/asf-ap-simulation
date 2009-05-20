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
public class TornarResidenciaHabitavel extends LeafGoal implements Serializable{
    
    public TornarResidenciaHabitavel(){
        super("boolean", "Tornar_Residencia_Habitavel",true);
    }

    public TornarResidenciaHabitavel(boolean valor){
        super("boolean", "Tornar_Residencia_Habitavel",valor);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TornarResidenciaHabitavel)){
            return false;
        }
        TornarResidenciaHabitavel resi = (TornarResidenciaHabitavel) obj;
        return (resi.name.equals(this.name) && resi.value.equals(this.value));
    }
    
}
