/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fabrica;

import framework.mentalState.belief.Belief;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class FabricaCrenca {

    private static Map<String, Belief> mapaCrencas = new HashMap<String, Belief>();

    public static Belief getCrenca(String nomeCrenca) {
        Belief crenca = mapaCrencas.get(nomeCrenca);

        if(crenca != null) {
            return crenca;
        }

        mapaCrencas.put(nomeCrenca, crenca);
        return crenca;
    }

}
