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
    public int getPontosFaltaArrumado() {
        return (PONTUACAO_TOTAL_ARRUMADO - pontuacaoArrumacao);
    }
    // VERIFICAR CONCORRENCIA

    public int getPontosFaltaLimpo() {
        return (PONTUACAO_TOTAL_LIMPO - pontuacaoLimpeza);
    }

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

    // VERIFICAR CONCORRENCIA
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

    private synchronized  void setPontuacaoLimpeza(int pontuacaoLimpeza) {
        if (pontuacaoLimpeza > -1 && pontuacaoLimpeza <=PONTUACAO_TOTAL_LIMPO) {
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
}
