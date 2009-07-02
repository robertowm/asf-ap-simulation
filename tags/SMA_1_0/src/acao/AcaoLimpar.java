/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acao;

import agente.papel.Empregada;
import ambiente.Residencia;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.mentalState.Message;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import util.ConstantesAplicacao;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class AcaoLimpar extends AcaoAgente implements Serializable{

    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean execute(Agent agente, Message msg) {
        Comodo comodo = ((Residencia) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());
//        comodo = ((Residencia) agente.getEnvironment()).atualizarComodo(agente, comodo);

        List<AgentRole> papeis = (List<AgentRole>) agente.getRolesBeingPlayed();
        int pontuacaoLimparDeAcordoComPersonalidade = 1;
        boolean empregada = false;
        for (AgentRole agentRole : papeis) {
            empregada = (agentRole instanceof Empregada);
            if(empregada) break;
        }
        
        Principal tela = JDesktop.getTela(agente);
        
        tela.apendTexto("\n\"Vou limpar aqui!\"");
        do { // se empregada, limpa enquanto o nivel de limpeza nao esta limpo

//            tela.apendTexto(" ---> N�vel de Limpeza comodo ->"+comodo.getNivelLimpeza());
            do {
                try {
                    tela.apendTexto("       Limpando o comodo...");
                    Thread.sleep(ConstantesAplicacao.TEMPO_LIMPAR_UM_PONTO);
                    comodo.limpa();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (--pontuacaoLimparDeAcordoComPersonalidade > 0);

            pontuacaoLimparDeAcordoComPersonalidade = comodo.getPontosFaltaLimpo();

        } while (empregada && !comodo.getNivelLimpeza().equals(Comodo.LIMPO));
        tela.apendTexto("Saindo do comodo ->"+comodo);
        tela.apendTexto("Situa��o de Limpeza ->"+comodo.getNivelLimpeza()+"\n");
        

        
        Message saida = new Message("?" + Thread.currentThread().getName(), comodo.toString(), agente.getAgentName(), agente.getAgentName());
        if(empregada){
            saida.setPerformative(ConstantesAplicacao.ACAO_ARRUMAR);
            if (comodo.getNivelArrumacao().equals(Comodo.ARRUMADO)) {
                Residencia residencia = (Residencia) agente.getEnvironment();
                Comodo c = residencia.pegarComodoAleatoriamente();
                if(!c.equals(residencia.pegarComodoPorAgente(agente))) {
                    residencia.trocarAgenteComodo(agente, c);
                    saida.setContent(c.toString());
                    saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
                    tela.apendTexto("\"Irei para o comodo " + c + "\"");
                    tela.apendTexto("       Mudando de comodo...");
                }
            }
        }else{
            saida.setPerformative(ConstantesAplicacao.ACAO_VERIFICAR_COMODO);
        }
        agente.send(saida);

        return comodo.getNivelLimpeza().equals(Comodo.LIMPO);
    }
}