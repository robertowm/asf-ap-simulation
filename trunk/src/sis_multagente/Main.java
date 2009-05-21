/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sis_multagente;

import static util.ConstantesAplicacao.*;

import ambiente.Residencia;
import comunicacao.ProtocoloTransporteMensagem;
import framework.FIPA.AMS;
import framework.FIPA.AgentPlatformDescription;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.mentalState.Message;
import framework.organization.MainOrganization;
import java.util.ArrayList;
import java.util.List;
import objeto.Comodo;
import organizacao.Habitacao;
import util.GeradorAgentes;
import visual.JDesktop;

/**
 *
 * @author heliokann
 */
public class Main {

    public static ElementID idEmpregada = null;
    public static JDesktop desktop;

    public static void main(String[] args) {

        desktop = new JDesktop();
        desktop.setVisible(true);

        AMS ams = AMS.getInstance();
        AgentPlatformDescription descricaoPlataforma = ams.getDescriptionPlatform();
        descricaoPlataforma.setName(PLATAFORMA_NOME);

        ElementID elementID = null;

        elementID = ams.createEnvironmentElementId(AMBIENTE_APJAVA_NOME, true);
//        elementID.setAddress(LOCAL_HOST);

        Residencia ambiente = null;
        try {
            List<Comodo> comodos = new ArrayList<Comodo>();
//            Map<String, Condition> condicoes = new HashMap<String, Condition>();
//            condicoes.put("comida_pronta", new Condition("boolean", "comida_pronta", false));
//            condicoes.put("louca_suja", new Condition("boolean", "louca_suja", false));
//            condicoes.put("panela_suja", new Condition("boolean", "panela_suja", false));
//
            comodos.add(new Comodo("Cozinha"/*, "cozinha", condicoes*/));
            comodos.add(new Comodo("Quarto"/*, "cozinha", condicoes*/));
            comodos.add(new Comodo("Sala"/*, "cozinha", condicoes*/));
//
            ambiente = new Residencia(elementID, comodos);
            ams.createDescription(ambiente, elementID, "");
            elementID.setAddress(LOCAL_HOST);
        } catch (NullPointerException ex) {
            System.out.println("[ERRO] Valor nulo durante a inicializacao do ambiente Residencia (" + AMBIENTE_APJAVA_NOME + ")");
            ex.printStackTrace();
        }

        elementID = ams.createOrganizationElementId(ORGANIZACAO_HABITACAO_NOME, true);

        MainOrganization mainOrg = null;
        try {
            mainOrg = new Habitacao(ambiente, elementID, ProtocoloTransporteMensagem.getInstancia());
            ams.createDescription(mainOrg, elementID, "");
            elementID.setAddress(LOCAL_HOST);
        } catch (NullPointerException ex) {
            System.out.println("[ERRO] Valor nulo durante a inicializacao da organizacao Habitacao (" + ORGANIZACAO_HABITACAO_NOME + ")");
            ex.printStackTrace();
        }

        Thread mainOrgThread = new Thread(mainOrg, THREAD_ORGANIZACAO_PRINCIPAL);
        mainOrgThread.start();

        Agent e1 = GeradorAgentes.gerarEmpregada(ambiente, mainOrg);
        idEmpregada = e1.getAgentName();

        Agent m1 = GeradorAgentes.gerarMorador(ambiente, mainOrg);
        Agent m2 = GeradorAgentes.gerarMorador(ambiente, mainOrg);
        Agent m3 = GeradorAgentes.gerarMorador(ambiente, mainOrg);
        
//        Mensagem Empregada
        
//        Message msg = new Message("?" + idEmpregada.getName(), ambiente.pegarComodoPorAgente(e1).toString(), idEmpregada, idEmpregada);
//        msg.setPerformative(ACAO_VERIFICAR_COMODO);
//        e1.send(msg);
        
        //Mensagem Morador        
        enviarMensagemInicio(m1,ambiente);
        enviarMensagemInicio(m2,ambiente);
        enviarMensagemInicio(m3,ambiente);
    }

    private static void enviarMensagemInicio(Agent agente, Residencia residencia) {
        Message msgm = new Message("?" + agente.getAgentName().getName(), residencia.pegarComodoPorAgente(agente).toString(), agente.getAgentName(), agente.getAgentName());
        msgm.setPerformative(ACAO_VERIFICAR_COMODO);
        agente.send(msgm);
    }
}
