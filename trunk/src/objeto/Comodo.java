/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objeto;

import framework.agent.Agent;

/**
 *
 * @author heliokann
 */
public class Comodo{
    
    private String nome;
    private int pontuacaoLimpeza;
    private int pontuacaoArrumacao;
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
    
    
    public Comodo(){        
        
    }
    
    public void limpar(Agent agent){
        
    }
    public void sujar(Agent agent){
        
    }
    public void arrumar(Agent agent){
        
    }
    public void desarrumar(Agent agent){
        
    }
    
    public String getNivelLimpeza() {
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
    
    

}
