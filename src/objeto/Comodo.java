/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objeto;

import framework.agent.Agent;
import static util.ConstantesAplicacao.*;

/**
 *
 * @author heliokann
 */
public class Comodo {

    private String nome;
    // pontuacao vai de zero a 10 onde 0 é inabitavel e 10 Limpo
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

    public Comodo() {
    }

    // VERIFICAR CONCORRENCIA
    public int getTempoArrumacao() {
        return (PONTUACAO_TOTAL_ARRUMADO - pontuacaoArrumacao) * TEMPO_ARRUMAR_UM_PONTO;
    }
    // VERIFICAR CONCORRENCIA

    public int getTempoLimpeza() {
        return (PONTUACAO_TOTAL_LIMPO - pontuacaoArrumacao) * TEMPO_LIMPAR_UM_PONTO;
    }

    public void limpar(Agent agent) {
    }

    public void sujar(Agent agent) {
    }

    public void arrumar(Agent agent) {
    }

    public void desarrumar(Agent agent) {
    }

    // VERIFICAR CONCORRENCIA
    public String getNivelLimpeza() {
        if (pontuacaoLimpeza > 8) {
            nivelLimpeza = LIMPO;
        } else if (pontuacaoArrumacao > 6) {
            nivelLimpeza = NORMAL_LIMPO;
        } else if (pontuacaoArrumacao > 4) {
            nivelLimpeza = SUJO;
        } else {
            nivelLimpeza = INABITAVEL_SUJO;
        }
        return nivelLimpeza;
    }

    public void setNivelLimpeza(String nivelLimpeza) {
        this.nivelLimpeza = nivelLimpeza;
    }

    public String getNivelArrumacao() {
        return nivelArrumacao;
    }

    public void setNivelArrumacao(String nivelArrumacao) {
        this.nivelArrumacao = nivelArrumacao;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacaoLimpeza() {
        return pontuacaoLimpeza;
    }

    public void setPontuacaoLimpeza(int pontuacaoLimpeza) {
        if (pontuacaoLimpeza > -1 && pontuacaoLimpeza <=PONTUACAO_TOTAL_LIMPO) {
            this.pontuacaoLimpeza = pontuacaoLimpeza;
        }
    }

    public int getPontuacaoArrumacao() {
        return pontuacaoArrumacao;
    }

    public void setPontuacaoArrumacao(int pontuacaoArrumacao) {
        if (pontuacaoArrumacao > -1 && pontuacaoArrumacao < PONTUACAO_TOTAL_ARRUMADO) {
            this.pontuacaoArrumacao = pontuacaoArrumacao;
        }
    }
}
