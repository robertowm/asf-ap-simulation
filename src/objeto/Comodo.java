/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objeto;

import ambiente.Ambiente;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import javax.swing.JList;
import static util.ConstantesAplicacao.*;

import framework.mentalState.Condition;
import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author heliokann
 */
public class Comodo implements Serializable {

    private String nome;
    private String tipo;
    private Ambiente ambiente;
//    private Map<String, Condition> mapaCondicoesComodo;
    private Vector<Agent> agentes = new Vector();
    private JList listaAgentesTela;
    private int pontuacaoLimpeza = 7;
    private javax.swing.JSlider jNivelArrumacao;
    private javax.swing.JSlider jNivelLimpeza;
    private int pontuacaoArrumacao = 7;
    private String nivelLimpeza;
    private String nivelArrumacao;
    public static final String LIMPO = "Limpo";
    public static final String NORMAL_LIMPO = "Normal";
    public static final String POUCO_SUJO = "Pouco sujo";
    public static final String SUJO = "Sujo";
    public static final String MUITO_SUJO = "Muito sujo";
    public static final String INABITAVEL_SUJO = "Inabitável";
    public static final String ARRUMADO = "Arrumado";
    public static final String NORMAL_ARRUMADO = "Normal";
    public static final String POUCO_DESARRUMADO = "Pouco desarrumado";
    public static final String DESARRUMADO = "Desarrumado";
    public static final String MUITO_DESARRUMADO = "Muito desarrumado";
    public static final String INABITAVEL_DESARRUMADO = "Inabitável";

    public Comodo(String nome, Ambiente ambiente) {
        this(nome, "", ambiente, new HashMap<String, Condition>());
    }

// TORNAR PUBLICO DEPOIS
    private Comodo(String nome, String tipo, Ambiente ambiente, Map<String, Condition> condicoes) {
        this.nome = nome;
        this.tipo = tipo;
        this.ambiente = ambiente;
//        this.mapaCondicoesComodo = condicoes;
    }

    public void adicionaAgente(Agent agente) {
        adicionaRemoveAgente(agente, true);
    }

    public synchronized void adicionaRemoveAgente(Agent agente, boolean adiciona) {
        if (adiciona) {
            agentes.add(agente);
            System.out.println(this.nome + "  ADICIONA -----------------------------------------");
        } else {
            System.out.println(this.nome + "  REMOVE -----------------------------------------");
            agentes.remove(agente);
        }

        listaAgentesTela.setListData(agentes);
        for (Agent agent : agentes) {
            System.out.print(agent.getAgentName().getName() + "  ");
        }
        System.out.println("");
    }

    public void removeAgente(Agent agente) {
        adicionaRemoveAgente(agente, false);
    }

    public void setJListaAgentes(JList jListAgentesTela) {
        this.listaAgentesTela = jListAgentesTela;
    }

    public void suja() {
        adicionaRemovePontuacaoLimpeza(false);
    }

    public void limpa() {
        adicionaRemovePontuacaoLimpeza(true);

    }

    public void arruma() {
        adicionaRemovePontuacaoArrumacao(true);
    }

    public void desarruma() {
        adicionaRemovePontuacaoArrumacao(false);
    }

    public String getNivelLimpeza() {
        if (pontuacaoLimpeza > 8) {
            nivelLimpeza = LIMPO;
        } else if (pontuacaoLimpeza > 6) {
            nivelLimpeza = NORMAL_LIMPO;
        } else if (pontuacaoLimpeza > 2) {
            nivelLimpeza = SUJO;
        } else {
            nivelLimpeza = INABITAVEL_SUJO;
        }
        return nivelLimpeza;
    }

    // VERIFICAR CONCORRENCIA
    public String getNivelArrumacao() {
        if (pontuacaoArrumacao > 8) {
            nivelArrumacao = ARRUMADO;
        } else if (pontuacaoArrumacao > 6) {
            nivelArrumacao = NORMAL_ARRUMADO;
        } else if (pontuacaoArrumacao > 2) {
            nivelArrumacao = DESARRUMADO;
        } else {
            nivelArrumacao = INABITAVEL_DESARRUMADO;
        }
        return nivelArrumacao;
    }

    public void setNivelLimpeza(String nivelLimpeza) {
        this.nivelLimpeza = nivelLimpeza;
    }

    public void setNivelArrumacao(String nivelArrumacao) {
        this.nivelArrumacao = nivelArrumacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    // VERIFICAR CONCORRENCIA
    public int getPontosFaltaArrumado() {
        return (PONTUACAO_TOTAL_ARRUMADO - pontuacaoArrumacao);
    }

    // VERIFICAR CONCORRENCIA
    public int getPontosFaltaLimpo() {
        return (PONTUACAO_TOTAL_LIMPO - pontuacaoLimpeza);
    }

    // VERIFICAR CONCORRENCIA
    public int getPontuacaoLimpeza() {
        return pontuacaoLimpeza;
    }

    public void setPontuacaoLimpeza(int pontuacaoLimpeza) {
        this.pontuacaoLimpeza = pontuacaoLimpeza;
        jNivelLimpeza.setValue(pontuacaoLimpeza);
    }

    private synchronized void adicionaRemovePontuacaoLimpeza(boolean adiciona) {
        int temp = (adiciona ? pontuacaoLimpeza + 1 : pontuacaoLimpeza - 1);
        if (temp > -1 && temp <= PONTUACAO_TOTAL_LIMPO) {
            pontuacaoLimpeza = temp;
            String nLimpeza = getNivelLimpeza();

            if (nLimpeza.equals(LIMPO)) {
                jNivelLimpeza.setBackground(Color.GREEN);
            } else if (nLimpeza.equals(NORMAL_LIMPO)) {
                jNivelLimpeza.setBackground(Color.YELLOW);
            } else if (nLimpeza.equals(SUJO)) {
                jNivelLimpeza.setBackground(Color.ORANGE);
            } else {
                jNivelLimpeza.setBackground(Color.RED);
            }

            jNivelLimpeza.setValue(pontuacaoLimpeza);
        }
    }

    private synchronized void adicionaRemovePontuacaoArrumacao(boolean adiciona) {
        int temp = (adiciona ? pontuacaoArrumacao + 1 : pontuacaoArrumacao - 1);
        if (temp > -1 && temp <= PONTUACAO_TOTAL_ARRUMADO) {
            pontuacaoArrumacao = temp;
            String nArrumacao = getNivelArrumacao();

            if (nArrumacao.equals(ARRUMADO)) {
                jNivelArrumacao.setBackground(Color.GREEN);
            } else if (nArrumacao.equals(NORMAL_ARRUMADO)) {
                jNivelArrumacao.setBackground(Color.YELLOW);
            } else if (nArrumacao.equals(DESARRUMADO)) {
                jNivelArrumacao.setBackground(Color.ORANGE);
            } else {
                jNivelArrumacao.setBackground(Color.RED);
            }

            jNivelArrumacao.setValue(pontuacaoArrumacao);
        }
    }

    public int getPontuacaoArrumacao() {
        return pontuacaoArrumacao;
    }

    public void setPontuacaoArrumacao(int pontuacaoArrumacao) {
        this.pontuacaoArrumacao = pontuacaoArrumacao;
        jNivelArrumacao.setValue(pontuacaoArrumacao);
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Comodo)) {
            return false;
        }
        Comodo c = (Comodo) obj;
        return c.nome.equals(this.nome) && c.ambiente.equals(this.ambiente);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 89 * hash + (this.ambiente != null ? this.ambiente.hashCode() : 0);
        return hash;
    }

    public javax.swing.JSlider getJNivelArrumacao() {
        return jNivelArrumacao;
    }

    public void setJNivelArrumacao(javax.swing.JSlider jNivelArrumacao) {
        this.jNivelArrumacao = jNivelArrumacao;
    }

    public javax.swing.JSlider getJNivelLimpeza() {
        return jNivelLimpeza;
    }

    public void setJNivelLimpeza(javax.swing.JSlider jNivelLimpeza) {
        this.jNivelLimpeza = jNivelLimpeza;
    }

    public MTS_Environment getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
    
}
