/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import acao.AcaoArrumar;
import acao.AcaoDesarrumar;
import acao.AcaoLimpar;
import acao.AcaoSujar;
import agente.papel.Papel;
import framework.agentRole.AgentRole;
import framework.mentalState.belief.Belief;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class GeradorRelatorio {

    private static final String EXTENSAO = ".csv";
    private static final String PASTA_RELATORIO = "relatorios\\";
    private static Map<String, Arquivo> mapaRelatorios = new HashMap<String, Arquivo>();

    public static void criarRelatorio(AgentRole a) {
        mapaRelatorios.put(a.getAgentPlayingRole().toString(), inicializarRelatorio(a));
    }

    public static synchronized Arquivo getArquivoAprendizado(Papel p) {
        return mapaRelatorios.get(p.getAgentPlayingRole().toString());
    }
    

    /**
     * Toda vez que iniciar a aplicação apaga os relatorios antigos caso existam
     */
    static {
        File dir = new File(PASTA_RELATORIO);
        if (dir.exists()) {
            String[] children = dir.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    new File(dir, children[i]).delete();
                }
            }
        } else {
            dir.mkdir();
        }
    }


    private static Arquivo inicializarRelatorio(AgentRole ar) {
        String nomeArquivo = ar.getAgentPlayingRole().toString();
        Arquivo saida = new Arquivo(PASTA_RELATORIO + nomeArquivo.replaceAll("@", "_").replaceAll(":", "_") + EXTENSAO);
        saida.append("Tempo;" + AcaoArrumar.class.getName() + ";" + AcaoDesarrumar.class.getName() + ";" + AcaoLimpar.class.getName() + ";" + AcaoSujar.class.getName() + ";");
        String arrumar = null;
        String desarrumar = null;
        String sujar = null;
        String limpar = null;

        for (Object object : ar.getBeliefs()) {
            Belief belief = (Belief) object;
            if (!(belief.getValue() instanceof Double)) {
                continue;
            }

            if (belief.getName().equals("arruma")) {
                arrumar = belief.getValue().toString();
            } else if (belief.getName().equals("dessarruma")) {
                desarrumar = belief.getValue().toString();
            } else if (belief.getName().equals("limpa")) {
                limpar = belief.getValue().toString();
            } else if (belief.getName().equals("suja")) {
                sujar = belief.getValue().toString();
            }
        }

        saida.append("0;" + arrumar + ";" + desarrumar + ";" + limpar + ";" + sujar);

        return saida;
    }
}
