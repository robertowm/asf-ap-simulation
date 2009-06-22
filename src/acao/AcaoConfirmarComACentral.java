///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package acao;
//
//import ambiente.Residencia;
//import framework.agent.Agent;
//import framework.mentalState.Message;
//import java.io.Serializable;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import objeto.Comodo;
//import util.ConstantesAplicacao;
//import visual.JDesktop;
//import visual.Principal;
//
///**
// *
// * @author heliokann
// */
//public class AcaoConfirmarComACentral extends AcaoAgente implements Serializable {
//
//    @Override
//    public boolean execute() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public boolean execute(Agent agente, Message msg) {
//        Comodo comodo = ((Residencia) agente.getEnvironment()).getComodoPorNome(msg.getContent().toString());
//
//        /**
//         * se o serviço já foi aceito por alguma outra empregada, não faz nada (retorna)
//         */
//
//
//
//        /**
//         * CASO CONTRATIO, A EMPREGADA DEVE MUDAR-SE DE AMBIENTE
//         * E DEPOIS ENVIA UMA MENSAGEM A SI MESMA DE VERIFICAR UM COMODO NO AMBIENTE SOLICITADO
//         */
//        String conversionId = "?" + Thread.currentThread().getName();
//        Principal tela = JDesktop.getTela(agente);
//
//        tela.apendTexto("Estou indo trabalhar em ..." + "COLOCAR O NOME DO AMBIENTE");
//        Message chamada = new Message(conversionId, comodo.toString(), agente.getAgentName(), agente.getAgentName());
//        chamada.setPerformative(ConstantesAplicacao.ACAO_CONFIRMAR_SERVICO_COM_CENTRAL);
//        agente.send(chamada);
//
//        try {
//            Thread.sleep(ConstantesAplicacao.TEMPO_CHAMAR_EMPREGADA);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AcaoLimpar.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//
//        return true;
//    }
//}
