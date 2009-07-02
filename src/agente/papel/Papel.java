/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import acao.AcaoArrumar;
import acao.AcaoDesarrumar;
import acao.AcaoLimpar;
import acao.AcaoSujar;
import framework.agentRole.AgentRole;
import framework.mentalState.belief.Belief;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sis_multagente.Main;
import util.Arquivo;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public abstract class Papel extends AgentRole implements Serializable {

    protected String nome;
    private DecimalFormat formatador = new DecimalFormat("0.####");

    public Papel(String nome) {
        super();
        this.nome = this.nome = ConstantesAplicacao.PREFIXO_NOME_PAPEL + nome;
    }

    public synchronized void atualizarStatusAcoes(Arquivo retorno) {
        Double arrumar = Double.NaN, dessarrumar = Double.NaN, limpar = Double.NaN, sujar = Double.NaN;
        long tempoInicial = Main.tempoInicio;
        if (tempoInicial == 0) {
            tempoInicial = System.currentTimeMillis();
        }
        long tempo = System.currentTimeMillis();

        for (Object object : getBeliefs()) {
            Belief belief = (Belief) object;
            if (!(belief.getValue() instanceof Double)) {
                continue;
            }
            if (belief.getName().equals("arruma")) {
                arrumar = (Double) belief.getValue();
            } else if (belief.getName().equals("dessarruma")) {
                dessarrumar = (Double) belief.getValue();
            } else if (belief.getName().equals("limpa")) {
                limpar = (Double) belief.getValue();
            } else if (belief.getName().equals("suja")) {
                sujar = (Double) belief.getValue();
            }
        }

        retorno.append((tempo - Main.tempoInicio)+ ";"+ formatarNumero(arrumar)+ ";" + formatarNumero(dessarrumar)+ ";"+ formatarNumero(limpar)+ ";" + formatarNumero(sujar)+ ";");
    }

    private String formatarNumero(Double numero) {
        return formatador.format(numero);
    }

    public void atualizarComportamento(Map<String, Double> mapaAcoes) {
        List<Belief> chanceAcoes = (List<Belief>) this.beliefs;
        Map<String, Double> mapaFinal = new HashMap<String, Double>();
        double total = 0;

        for (String key : mapaAcoes.keySet()) {
            String chaveCorreta = null;
            if(key.equals(AcaoArrumar.class.getName())){
                chaveCorreta = "arruma";
            } else if (key.equals(AcaoDesarrumar.class.getName())) {
                chaveCorreta = "dessarruma";
            } else if (key.equals(AcaoLimpar.class.getName())) {
                chaveCorreta = "limpa";
            } else if (key.equals(AcaoSujar.class.getName())) {
                chaveCorreta = "suja";
            }
            
            if(chaveCorreta != null) {
                Double valor = mapaAcoes.get(key);
                mapaFinal.put(chaveCorreta, valor);
                total += valor;
            }
        }

        for (Map.Entry<String, Double> entry : mapaFinal.entrySet()) {
            entry.setValue((entry.getValue() / total) * 100);
        }

        if (Main.heuristica) {
            if (calcularValorHeuristico(chanceAcoes) > calcularValorHeuristico(mapaFinal)) {
                return;
            }
        }

        for (Belief belief : chanceAcoes) {
            Double valor = mapaFinal.get(belief.getName());
            if (valor != null) {
                belief.setValue((((Double) belief.getValue()) + valor) / 2);
            }
        }
    }

    private double calcularValorHeuristico(List<Belief> lista) {
        double retorno = 0;
        Double valor = null;
        for (Belief belief : lista) {
            if (!(belief.getValue() instanceof Double)) {
                continue;
            }
            valor = (Double) belief.getValue();
            if (belief.getName().equals("arruma") || belief.getName().equals("limpa")) {
                retorno += 2 * valor;
            } else {
                retorno += valor;
            }
        }
        return retorno;
    }

    private double calcularValorHeuristico(Map<String, Double> mapa) {
        double retorno = 0;
        for (Entry<String, Double> entry : mapa.entrySet()) {
            if (entry.getKey().equals("arruma") || entry.getKey().equals("limpa")) {
                retorno += 2 * entry.getValue();
            } else {
                retorno += entry.getValue();
            }
        }
        return retorno;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Papel)) {
            return false;
        }
        Papel papel = (Papel) obj;
        return this.nome.equals(papel.nome);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }
}