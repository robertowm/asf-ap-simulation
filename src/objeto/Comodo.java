/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objeto;

import static util.ConstantesAplicacao.*;

import framework.agent.Agent;
import framework.mentalState.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author heliokann
 */
public class Comodo {

    private String nome;
    private String tipo;
//    private Map<String, Condition> mapaCondicoesComodo;
//    private Map<String, Agent> mapaAgentes = new HashMap<String, Agent>();
    private int pontuacaoLimpeza = 7;
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
//
//    public void atribuirAgente(Agent agente) {
//        mapaAgentes.put(agente.getAgentName().getName(), agente);
//    }
//
//    public void removerAgente(Agent agente) {
//        mapaAgentes.remove(agente.getAgentName().getName());
//    }
//
//    public boolean verificarAgente(Agent agente) {
//        return mapaAgentes.containsKey(agente.getAgentName().getName());
//    }
//
//    public Agent recuperarAgentePeloNome(String nome) {
//        return mapaAgentes.get(nome);
//    }

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
        setPontuacaoLimpeza(--pontuacaoLimpeza);
    }

    public void limpa() {
        setPontuacaoLimpeza(++pontuacaoLimpeza);

    }

    public void arruma() {
        setPontuacaoArrumacao(++pontuacaoArrumacao);
    }

    public void desarruma() {
        setPontuacaoArrumacao(--pontuacaoArrumacao);
    }

    public String getNivelLimpeza() {
        if (pontuacaoLimpeza > 8) {
            nivelLimpeza = LIMPO;
        } else if (pontuacaoLimpeza > 6) {
            nivelLimpeza = NORMAL_LIMPO;
        } else if (pontuacaoLimpeza > 4) {
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
        } else if (pontuacaoArrumacao > 4) {
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

    private synchronized void setPontuacaoLimpeza(int pontuacaoLimpeza) {
        if (pontuacaoLimpeza > -1 && pontuacaoLimpeza <= PONTUACAO_TOTAL_LIMPO) {
            this.pontuacaoLimpeza = pontuacaoLimpeza;
        }
    }

    public int getPontuacaoArrumacao() {
        return pontuacaoArrumacao;
    }

    private synchronized void setPontuacaoArrumacao(int pontuacaoArrumacao) {
        if (pontuacaoArrumacao > -1 && pontuacaoArrumacao < PONTUACAO_TOTAL_ARRUMADO) {
            this.pontuacaoArrumacao = pontuacaoArrumacao;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}
