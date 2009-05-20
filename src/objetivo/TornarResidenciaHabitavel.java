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
public class TornarResidenciaHabitavel extends LeafGoal{
    
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
