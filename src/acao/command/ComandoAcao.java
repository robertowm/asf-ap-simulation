/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package acao.command;

import acao.AcaoAgente;
import acao.AcaoArrumar;
import acao.AcaoChamarEmpregada;
import acao.AcaoConfirmarComACentral;
import acao.AcaoConvocarEmpregada;
import acao.AcaoDesarrumar;
import acao.AcaoLimpar;
import acao.AcaoSujar;
import acao.AcaoVerificarComodo;
import java.util.HashMap;
import java.util.Map;
import static util.ConstantesAplicacao.*;

/**
 *
 * @author heliokann
 */
public class ComandoAcao {

    private static Map<String, AcaoAgente> acoes = new HashMap();
    
    static {
        acoes.put(ACAO_ARRUMAR, new AcaoArrumar());
        acoes.put(ACAO_LIMPAR, new AcaoLimpar());
        acoes.put(ACAO_CHAMAR_EMPREGADA, new AcaoChamarEmpregada());
        acoes.put(ACAO_CONVOCAR_EMPREGADA, new AcaoConvocarEmpregada());
        acoes.put(ACAO_CONFIRMAR_SERVICO_COM_CENTRAL, new AcaoConfirmarComACentral());
        acoes.put(ACAO_DESARRUMAR, new AcaoDesarrumar());
        acoes.put(ACAO_SUJAR, new AcaoSujar());
        acoes.put(ACAO_VERIFICAR_COMODO, new AcaoVerificarComodo());
    }
    
    public static AcaoAgente getAcao(String msg){
        return acoes.get(msg);
    }

}
