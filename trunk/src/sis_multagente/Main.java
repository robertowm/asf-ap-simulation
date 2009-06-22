/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sis_multagente;

import ambiente.CentralAtendimento;
import static util.ConstantesAplicacao.*;

import ambiente.Residencia;
import comunicacao.ProtocoloTransporteMensagem;
import fabrica.FabricaAmbiente;
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
import visual.JDesktop;

/**
 *
 * @author heliokann
 */
public class Main {

    public static ElementID idEmpregada = null;
    public static JDesktop desktop;
    public static MainOrganization OrganizacaoPrincipal;
    public static CentralAtendimento ambienteCentral = null;

    public static void main(String[] args) {

        desktop = new JDesktop();
        desktop.setVisible(true);

        AMS ams = AMS.getInstance();
        AgentPlatformDescription descricaoPlataforma = ams.getDescriptionPlatform();
        descricaoPlataforma.setName(PLATAFORMA_NOME);
        OrganizacaoPrincipal = null;

        ElementID elementID = null;

        elementID = ams.createEnvironmentElementId(AMBIENTE_CENTRAL_ATENDIMENTO, true);
        try {
            ambienteCentral = new CentralAtendimento(elementID);
            ams.createDescription(ambienteCentral, elementID, "");
            elementID.setAddress(LOCAL_HOST);
        } catch (NullPointerException ex) {
            System.out.println("[ERRO] Valor nulo durante a inicializacao do ambiente Central de Atendimento (" + AMBIENTE_CENTRAL_ATENDIMENTO + ")");
            ex.printStackTrace();
        }

        elementID = ams.createOrganizationElementId(ORGANIZACAO_HABITACAO_NOME, true);
        try {
            OrganizacaoPrincipal = new Habitacao(ambienteCentral, elementID, ProtocoloTransporteMensagem.getInstancia());
            ams.createDescription(OrganizacaoPrincipal, elementID, "");
            elementID.setAddress(LOCAL_HOST);
        } catch (NullPointerException ex) {
            System.out.println("[ERRO] Valor nulo durante a inicializacao da organizacao Habitacao (" + ORGANIZACAO_HABITACAO_NOME + ")");
            ex.printStackTrace();
        }

        ambienteCentral.carregarSecretaria();
        FabricaAmbiente.adicionarAmbiente(ambienteCentral);
        
        Thread mainOrgThread = new Thread(OrganizacaoPrincipal, THREAD_ORGANIZACAO_PRINCIPAL);
        mainOrgThread.start();

//        Agent e1 = GeradorAgentes.gerarEmpregada(ambienteCentral, OrganizacaoPrincipal);
//        idEmpregada = ambienteCentral.getDescription().getElementId();

//        Agent m1 = GeradorAgentes.gerarMorador(ambienteCentral, OrganizacaoPrincipal);
//        Agent m2 = GeradorAgentes.gerarMorador(ambienteCentral, OrganizacaoPrincipal);
//        Agent m3 = GeradorAgentes.gerarMorador(ambienteCentral, OrganizacaoPrincipal);
        
//        Mensagem Empregada
        
//        Message msg = new Message("?" + idEmpregada.getName(), ambiente.pegarComodoPorAgente(e1).toString(), idEmpregada, idEmpregada);
//        msg.setPerformative(ACAO_VERIFICAR_COMODO);
//        e1.send(msg);
        
        //Mensagem Morador        
//        enviarMensagemInicio(m1,ambienteCentral);
//        enviarMensagemInicio(m2,ambienteCentral);
//        enviarMensagemInicio(m3,ambienteCentral);
    }

    private static void enviarMensagemInicio(Agent agente, Residencia residencia) {
        Message msgm = new Message("?" + agente.getAgentName().getName(), residencia.pegarComodoPorAgente(agente).toString(), agente.getAgentName(), agente.getAgentName());
        msgm.setPerformative(ACAO_VERIFICAR_COMODO);
        agente.send(msgm);
    }
}
