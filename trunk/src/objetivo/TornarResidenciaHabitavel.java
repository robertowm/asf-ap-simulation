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
    
//    public TornarResidenciaHabitavel(String tipo, String descricao, Object valor){
//        super(tipo, descricao, valor);
//    }
    
    public TornarResidenciaHabitavel(){
        super("boolean", "Tornar_Residencia_Habitavel",true);
    }
    
}