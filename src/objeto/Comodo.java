/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objeto;

import framework.agent.Agent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import static util.ConstantesAplicacao.*;

import framework.mentalState.Condition;
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

    public Comodo(String nome) {
        this(nome, "", new HashMap<String, Condition>());
    }

//    public Comodo(String nome, String tipo) {
//        this(nome, tipo, new HashMap<String, Condition>());
//    }
// TORNAR PUBLICO DEPOIS
    private Comodo(String nome, String tipo, Map<String, Condition> condicoes) {
        this.nome = nome;
        this.tipo = tipo;
//        this.mapaCondicoesComodo = condicoes;
    }

    public void adicionaAgente(Agent agente) {
            adicionaRemoveAgente(agente, true);
    }

    public synchronized void adicionaRemoveAgente(Agent agente, boolean adiciona) {
        if (adiciona) {
            agentes.add(agente);
        } else {
            agentes.remove(agente);
        }
        listaAgentesTela.setListData(agentes);
    }

    public  void removeAgente(Agent agente) {
            adicionaRemoveAgente(agente, false);
    }

    public void setJListaAgentes(JList jListAgentesTela) {
        this.listaAgentesTela = jListAgentesTela;
    }

//    public void atribuirAgente(Agent agente) {
////        mapaAgentes.put(agente.toString(), agente);
//        agentes.add(agente);
//    }
//
//    public boolean atendePreCondicoes(Collection<Condition> condicoes) {
//        for (Condition condicaoAcao : condicoes) {
//            Condition condicaoComodo = mapaCondicoesComodo.get(condicaoAcao.getName());
//            if (condicaoComodo == null || !condicaoComodo.getValue().equals(condicaoAcao.getValue()) || !condicaoComodo.getType().equals(condicaoAcao.getType())) {
//                return false;
//            }
//        }
//        return true;
//    }
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
    
    public void setPontuacaoLimpeza(int pontuacaoLimpeza){
        this.pontuacaoLimpeza = pontuacaoLimpeza;
        jNivelLimpeza.setValue(pontuacaoLimpeza);
    }

    private synchronized void adicionaRemovePontuacaoLimpeza(boolean adiciona) {
        int temp = (adiciona? pontuacaoLimpeza+1: pontuacaoLimpeza-1);
        if (temp > -1 && temp <= PONTUACAO_TOTAL_LIMPO) {
            pontuacaoLimpeza = temp;
            jNivelLimpeza.setValue(pontuacaoLimpeza);
        }
    }

    private synchronized void adicionaRemovePontuacaoArrumacao(boolean adiciona) {
        int temp = (adiciona? pontuacaoArrumacao+1: pontuacaoArrumacao-1);
        if (temp > -1 && temp <= PONTUACAO_TOTAL_ARRUMADO) {
            pontuacaoArrumacao = temp;
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
        return c.nome.equals(this.nome);

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
}
