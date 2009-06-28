/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import agente.papel.Papel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heliokann
 */
public class FluxoResultados extends Thread {

    private List<RelatorioResultados> relatorios;
    public boolean running = true;

    public FluxoResultados(List<RelatorioResultados> relatorios) {
        this.relatorios = relatorios;
    }

    @Override
    public void run() {
        atualizarRelatorios();
        while (running) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FluxoResultados.class.getName()).log(Level.SEVERE, null, ex);
            }
            exibirDados();
        }
    }

    public void atualizarRelatorios() {
        for (RelatorioResultados relatorio : relatorios) {
            relatorio.atualizar();
        }
    }
    
    public void atualizarRelatorio(Papel papel) {
        for (RelatorioResultados relatorio : relatorios) {
            if(relatorio.getPapel().equals(papel)) {
                relatorio.atualizar();
                break;
            }
        }
    }

    public void exibirDados() {
        for (RelatorioResultados relatorio : relatorios) {
            relatorio.exibirDados();
        }
    }
}
