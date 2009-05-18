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

}
