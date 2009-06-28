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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sis_multagente.Main;
import util.ConstantesAplicacao;

/**
 *
 * @author heliokann
 */
public abstract class Papel extends AgentRole implements Serializable {

    protected String nome;
    
    public Papel(String nome) {
        super();
        this.nome = this.nome = ConstantesAplicacao.PREFIXO_NOME_PAPEL + nome;
    }
    
    public synchronized void getStatusAcoes(StringBuffer retorno) {
        Double arrumar = Double.NaN, dessarrumar = Double.NaN, limpar = Double.NaN, sujar = Double.NaN;
        long tempoInicial = Main.tempoInicio;
        if(tempoInicial == 0) tempoInicial = System.currentTimeMillis();
        
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

        retorno.append(tempo-Main.tempoInicio);
        retorno.append(";");
        
        retorno.append(formatarNumero(arrumar));
        retorno.append(";");
        retorno.append(formatarNumero(dessarrumar));
        retorno.append(";");
        retorno.append(formatarNumero(limpar));
        retorno.append(";");
        retorno.append(formatarNumero(sujar));
        retorno.append(";");
        retorno.append("\n");
    }
    
    DecimalFormat formatador = new DecimalFormat("0.####");
    private String formatarNumero(Double numero) {
        return formatador.format(numero);
    }

    public void atualizarComportamento(Map<String, Double> mapaAcoes) {
        List<Belief> chanceAcoes = (List<Belief>) this.beliefs;
        Map<String, Double> mapaFinal = new HashMap<String, Double>();
        double total = 0;

        for (Map.Entry<String, Double> entry : mapaAcoes.entrySet()) {
            if (entry.getKey().equals(AcaoArrumar.class.getName())) {
                mapaFinal.put("arruma", entry.getValue());
                total += entry.getValue();
            } else if (entry.getKey().equals(AcaoDesarrumar.class.getName())) {
                mapaFinal.put("dessarruma", entry.getValue());
                total += entry.getValue();
            } else if (entry.getKey().equals(AcaoLimpar.class.getName())) {
                mapaFinal.put("limpa", entry.getValue());
                total += entry.getValue();
            } else if (entry.getKey().equals(AcaoSujar.class.getName())) {
                mapaFinal.put("suja", entry.getValue());
                total += entry.getValue();
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
        for (Belief belief : lista) {
            if (belief.getName().equals("arruma") || belief.getName().equals("limpa")) {
                retorno += 2 * ((Integer) belief.getValue());
            } else {
                retorno += ((Integer) belief.getValue());
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
