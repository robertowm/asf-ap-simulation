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
import java.util.Collection;

/**
 *
 * @author heliokann
 */
public class RelatorioResultados {

    private Papel papel;
    private StringBuffer saida;

    public RelatorioResultados(Papel papel) {
        this.papel = papel;
        this.saida = new StringBuffer();
        inicializarRelatorio();
    }

    public RelatorioResultados(Collection papeis) {
        this((Papel) papeis.iterator().next());
    }

    public synchronized void atualizar() {
        this.papel.getStatusAcoes(saida);
    }

    public void exibirDados() {
        System.out.println();
        System.out.println();
        System.out.println(" ---- " + papel.getAgentPlayingRole().getAgentName().getName() + " ----");
        System.out.println(saida);
        System.out.println(" ------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    @Override
    public boolean equals(Object obj) {
        return papel.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.papel != null ? this.papel.hashCode() : 0);
        return hash;
    }

    public Papel getPapel() {
        return papel;
    }

    private void inicializarRelatorio() {
        this.saida.append("Tempo;");
        this.saida.append(AcaoArrumar.class.getName());
        this.saida.append(";");
        this.saida.append(AcaoDesarrumar.class.getName());
        this.saida.append(";");
        this.saida.append(AcaoLimpar.class.getName());
        this.saida.append(";");
        this.saida.append(AcaoSujar.class.getName());
        this.saida.append(";");
        this.saida.append("\n");
    }
}
