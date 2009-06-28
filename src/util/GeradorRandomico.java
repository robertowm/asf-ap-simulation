/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import framework.mentalState.belief.Belief;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author heliokann
 */
public class GeradorRandomico {

    static Random r = new Random();
    public static int geraRandomico(int max) {
        return r.nextInt(max);
    }
    public static int geraPercentual() {
        return r.nextInt(100);
    }
    
    public static double geraPercentualDouble() {
        return r.nextDouble()*100;
    }
    
    public static Belief getBelief(List<Belief> crencas) {
        double probabilidade = geraPercentualDouble();
        
        for (Belief crenca : crencas) {
            if(!(crenca.getValue() instanceof Double)) continue;
            Double valor = (Double)crenca.getValue();
            if(probabilidade <= valor) {
                return crenca;
            }
            probabilidade -= valor;
        }
        return crencas.get(crencas.size()-1);
    }

    public static Belief getBelief(Collection beliefs) {
        return getBelief((List<Belief>)beliefs);
    }
}
