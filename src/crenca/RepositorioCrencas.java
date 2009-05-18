/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crenca;

import framework.mentalState.belief.Belief;
import framework.mentalState.belief.LeafBelief;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class RepositorioCrencas {

    private static Map<String, TipoCrenca> mapaCrencas = new HashMap<String, TipoCrenca>();

    // Por enquanto, a lista de crencas sera amarrada ao codigo. Em breve, via XML.
    static {
        mapaCrencas.put("dinheiro", new TipoCrenca("double","dinheiro"));
        mapaCrencas.put("chamar_empregada", new TipoCrenca("boolean","chamar_empregada"));
        mapaCrencas.put("gosta_casa_limpa", new TipoCrenca("boolean","gosta_casa_limpa"));
        mapaCrencas.put("gosta_casa_arrumada", new TipoCrenca("boolean","gosta_casa_arrumada"));
        mapaCrencas.put("organizado", new TipoCrenca("boolean","organizado"));
        mapaCrencas.put("higienico", new TipoCrenca("boolean","higienico"));
    }
    
    public static Belief criarCrenca(String nome, Object valor) {
        TipoCrenca tipoCrenca = mapaCrencas.get(nome);
        try {
        return new LeafBelief(tipoCrenca.getRetorno(), nome, valor);
        } catch (NullPointerException ex) {
            throw new RuntimeException("[ERRO] Crenca nao existente: " + nome + " = " + valor);
        }
    }
}
