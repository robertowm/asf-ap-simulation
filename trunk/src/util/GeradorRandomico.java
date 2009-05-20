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
    
    public static Belief getBelief(List<Belief> crencas) {
        int probabilidade = geraPercentual();
        
        for (Belief crenca : crencas) {
            Integer valor = (Integer)crenca.getValue();
            if(probabilidade <= valor) {
                System.out.println("RETORNANDO A CRENCA ALEATORIAMENTE --->>"+crenca.getName());
                return crenca;
            }
            probabilidade -= valor;
        }
        return null;
    }

    public static Belief getBelief(Collection beliefs) {
        return getBelief((List<Belief>)beliefs);
    }
}
