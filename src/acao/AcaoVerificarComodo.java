/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package acao;

import framework.agent.Agent;
import framework.mentalState.Message;

/**
 *
 * @author heliokann
 */
public class AcaoVerificarComodo extends AcaoAgente{
    
    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean execute(Agent agente, Message msg) {
        /**
         * tem que analisar o quarto de acordo com a personalidade e papel do agente
         * por exemplo, se  agente é empregada ele vai querer limpar ou arrumar, de acordo com a condição do comodo
         * se for um morador ele pode arrumar, limpar, desarrumar, sujar, ou chamar a empregada, dependendo de sua personalidade
         * para chamar a empregada a média dos cômodos tem que estar inferior a 5 ou um comodo estar inabitavel
         */
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
