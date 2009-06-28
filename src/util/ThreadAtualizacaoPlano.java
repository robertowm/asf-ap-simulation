/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import agente.papel.Papel;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class ThreadAtualizacaoPlano extends Thread {

    private Map<String, Double> mapa;
    private Papel papel;

    public ThreadAtualizacaoPlano(Map<String, Double> mapa, Papel papel) {
        this.mapa = mapa;
        this.papel = papel;
    }

    @Override
    public void run() {
        papel.atualizarComportamento(mapa);
        FluxoResultados fr = (FluxoResultados) GerenciadorFluxos.recuperarFluxo("relatorios");
        fr.atualizarRelatorio(papel);
    }
}
