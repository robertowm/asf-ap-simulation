/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import ambiente.Ambiente;
import framework.agent.Agent;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Saida;

/**
 *
 * @author heliokann
 */
public class AcaoFazNada extends AcaoAgente implements Serializable {

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Ambiente) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());
        
        if(comodo== null){
            return false;
        }

        comodo.adicionaAgente(agente);
        
        Saida tela = JDesktop.getTela(agente);
        tela.apendTexto("\n\"Vou ficar parado um pouco...\"");
            try {
                Thread.sleep(ConstantesAplicacao.TEMPO_FAZ_NADA);
            } catch (InterruptedException ex) {
                Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
            }

//        Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
//        
//        boolean empregada = false;
//        for (Object roles : agente.getRolesBeingPlayed()) {
//            AgentRole papel = (AgentRole) roles;
//            if(papel instanceof Empregada) {
//                empregada = true;
//                break;
//            }
//        }
//        if(empregada) {
//            saida.setPerformative(ConstantesAplicacao.ACAO_PEGAR_FAXINA);
//        } else {
//            saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
//        }
//        agente.send(saida);

        comodo.removeAgente(agente);
        
        return true;
    }
}
