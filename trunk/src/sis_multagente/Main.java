/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sis_multagente;

import ambiente.Ambiente;
import ambiente.CentralAtendimento;
import static util.ConstantesAplicacao.*;

import comunicacao.ProtocoloTransporteMensagem;
import fabrica.FabricaAmbiente;
import framework.FIPA.AMS;
import framework.FIPA.AgentPlatformDescription;
import framework.FIPA.ElementID;
import framework.organization.MainOrganization;
import organizacao.Habitacao;
import util.ConstantesAplicacao;
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
//        try {
        ambienteCentral = (CentralAtendimento) FabricaAmbiente.getAmbiente(AMBIENTE_CENTRAL_ATENDIMENTO, ConstantesAplicacao.TIPO_AMBIENTE_CENTRAL_ATENDIMENTO);
//                    new CentralAtendimento(elementID);
//            ams.createDescription(ambienteCentral, elementID, "");
//            elementID.setAddress(LOCAL_HOST);
//        } catch (NullPointerException ex) {
//            System.out.println("[ERRO] Valor nulo durante a inicializacao do ambiente Central de Atendimento (" + AMBIENTE_CENTRAL_ATENDIMENTO + ")");
//            ex.printStackTrace();
//        }

        elementID = ams.createOrganizationElementId(ORGANIZACAO_HABITACAO_NOME, true);
        try {
            OrganizacaoPrincipal = new Habitacao(new Ambiente(elementID), elementID, ProtocoloTransporteMensagem.getInstancia());
            ams.createDescription(OrganizacaoPrincipal, elementID, "");
            elementID.setAddress(LOCAL_HOST);
        } catch (NullPointerException ex) {
            System.out.println("[ERRO] Valor nulo durante a inicializacao da organizacao Habitacao (" + ORGANIZACAO_HABITACAO_NOME + ")");
            ex.printStackTrace();
        }
        
        ambienteCentral.carregarSecretaria();
        
        Thread mainOrgThread = new Thread(OrganizacaoPrincipal, THREAD_ORGANIZACAO_PRINCIPAL);
        mainOrgThread.start();


    }

//    private static void enviarMensagemInicio(Agent agente, Residencia residencia) {
//        Message msgm = new Message("?" + agente.getAgentName().getName(), residencia.pegarComodoPorAgente(agente).toString(), agente.getAgentName(), agente.getAgentName());
//        msgm.setPerformative(ACAO_VERIFICAR_COMODO);
//        agente.send(msgm);
//    }
}
