/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import objeto.Comodo;

/**
 *
 * @author robertowm
 */
public interface ConstantesAplicacao {

    // >>> Plataforma <<<
    public static final String PLATAFORMA_NOME = "PlataformaResidencia";


    // >>> Ambientes <<<
    // >>>>>> APJAVA
    public static final String AMBIENTE_APJAVA_NOME = "Residencia_APJVA";


    // >>> Organizacoes <<<
    // >>>>>> Habitacao
    public static final String ORGANIZACAO_HABITACAO_NOME = "Habitacao";

    // >>> MessageTransportProtocol <<<
    public static final int MTP_PORTA = 1500;
    
    // >>> Tempos em ms<<<
    public static final int VELOCIDADE = 1;
    public static final int TEMPO_LIMPAR_UM_PONTO = 200 * VELOCIDADE;
    public static final int TEMPO_ARRUMAR_UM_PONTO = 300 * VELOCIDADE;
    public static final int TEMPO_SUJAR_UM_PONTO = 600 * VELOCIDADE;
    public static final int TEMPO_DESARRUMAR_UM_PONTO = 900 * VELOCIDADE;
    public static final int TEMPO_CHAMAR_EMPREGADA = 500 * VELOCIDADE;
    public static final int TEMPO_ESPERAR_EMPREGADA = 1000 * VELOCIDADE;;
    public static final int TEMPO_VERIFICAR_COMODO = 100 * VELOCIDADE;
    
    // >>> Pontuacoes <<<
    public static final int PONTUACAO_TOTAL_LIMPO = 10;
    public static final int PONTUACAO_TOTAL_ARRUMADO = 10;
    
    // >>> NumeroComodos <<<
    public static final int QTD_COMODO = 2;
    public static final int MEDIA_PONTOS_COMODOS = (PONTUACAO_TOTAL_ARRUMADO + PONTUACAO_TOTAL_LIMPO)/QTD_COMODO ;
    
    // >>> Papel <<<
    public static final String PAPEL_EMPREGADA = "Empregada";
    public static final String PAPEL_MORADOR = "Morador";
    
    // >>> Acoes <<<
    public static final String ACAO_LIMPAR = "Limpar";
    public static final String ACAO_SUJAR = "Sujar";
    public static final String ACAO_DESARRUMAR = "Desarrumar";
    public static final String ACAO_ARRUMAR = "Arrumar";
    public static final String ACAO_CHAMAR_EMPREGADA = "Chamar Empregada";
    public static final String ACAO_VERIFICAR_COMODO = "Verificar Comodo";

    // >> Outros <<
    public static final String LOCAL_HOST = "127.0.0.1";
    public static final String THREAD_ORGANIZACAO_PRINCIPAL = "ThreadOrganizacao";

    public static final String PREFIXO_NOME_AGENTE = "Agente::";
    public static final String PREFIXO_NOME_PAPEL = "Papel::";
    public static final String PREFIXO_PAPEL_MORADOR = "Morador::";
}