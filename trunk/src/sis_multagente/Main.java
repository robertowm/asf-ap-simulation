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

        //Mensagem Empregada
        Message msg = new Message("?" + idEmpregada.getName(), ambiente.pegarComodoPorAgente(e1), idEmpregada, idEmpregada);
        msg.setPerformative(ACAO_VERIFICAR_COMODO);
        e1.send(msg);

        //Mensagem Morador
        Message msgm = new Message("?" + m1.getAgentName().getName(), ambiente.pegarComodoPorAgente(m1), m1.getAgentName(), m1.getAgentName());
        msgm.setPerformative(ACAO_VERIFICAR_COMODO);
        m1.send(msgm);



//        for (Object obj : ambiente.getAgents()) {
//            Agent agent = (Agent) obj;
//            Thread agentThread = new Thread(agent, agent.getAgentName().getName());
//            agentThread.start();
//            List<Goal> goals = (List<Goal>) agent.getGoals();
//            for (Goal goal : goals) {
//                if(goal.getAchieved()){
//                    agent.setGoal(goal);
//                }
//            }
//        }

    }

    public void criandoAgentes_EXEMPLO(AMS ams) {
//        ElementID elementId;
//        if ((elementId = ams.createAgentElementId("UserAgent::Viviane", true)) != null) {
//            // Crio na mao o primeiro agentRole de um agente com seu respectivo elementId
//            AgentRole agRoleBuyer1 = new Buyer(mainOrg);
//            int numberOfRoles = mainOrg.getAgentRoles().size();
//
//            ElementID elementIdRole = new ElementID("Viviane::Buyer" + numberOfRoles, true);
//            agRoleBuyer1.setRoleName(elementIdRole);
//            elementId.setAddress("127.0.0.1");
//            //
        //
//            Agent agentViviane = new User_Agent(env, mainOrg, agRoleBuyer1, elementId);
//            //agentViviane.setAgentName("UserAgent::Viviane");
//            //agentViviane.setAgentName(elementId.getName());
//
//            //Cria a descriÁ?o do papel supracitado.
//            agentViviane.createAgentRoleDescription(agRoleBuyer1, elementIdRole, "");
//
//            // Parte Nova - Ocorre o registro do agente no AMS e a criaÁ?o da sua descriÁ?o.
//            ams.createDescription(agentViviane, elementId, "");
//            ams.createDescription(agentViviane, elementId, "");
//
//            Thread agentBVivianeThread = new Thread(agentViviane, agRoleBuyer1.getRoleName());
//            agRoleBuyer1.setAgent(agentViviane);
//
//            //User_Agent role -> Buyer2
//            //Agent agentViviane = new User_Agent (env,mainOrg,agRoleBuyer1,elementId);
//
//            AgentRole agRoleBuyer2 = new Buyer(mainOrg);
//            numberOfRoles = mainOrg.getAgentRoles().size();
//
//            if ((elementId = agentViviane.createAgentRoleElementID("Viviane::Buyer" + numberOfRoles, true)) != null) {
//                agRoleBuyer2.setRoleName(elementId);
//                agentViviane.setRolesBeingPlayed(agRoleBuyer2, mainOrg);
//                agentViviane.createAgentRoleDescription(agRoleBuyer2, elementId, "");
//
//                Thread agentBViviane2Thread = new Thread(agentViviane, agRoleBuyer2.getRoleName());
//                agRoleBuyer2.setAgent(agentViviane);
//                elementId.setAddress("127.0.0.1");
//
//                /////////////////////
//                //Beliefs do agente
//                ///////////////////
//                //Item que ele deseja comprar
//                Book book3 = new Book();
//                book3.setISBN("123");
//                book3.setPrice(10);
//                Belief belief = new LeafBelief("Book", "itemToBuy_123", book3);
//                agentViviane.setBelief(belief);
//
//                //Item que ele desejar comprar
//                Book book4 = new Book();
//                book4.setISBN("ABC");
//                book4.setPrice(15);
//                belief = new LeafBelief("Book", "itemToBuy_ABC", book4);
//                agentViviane.setBelief(belief);
//
//                Money money = new Money();
//                money.setValue(20);
//                belief = new LeafBelief("Money", "account", money);
//                agentViviane.setBelief(belief);
//
//                ///////////////////////////////////////
//                //Relating the role to the book to buy
//                //////////////////////////////////////
//                //Role Buyer01 goal is to buy the item 123
//                Collection vRoleGoals = agRoleBuyer1.getGoals();
//                Iterator enumRoleGoals = vRoleGoals.iterator();
//                while (enumRoleGoals.hasNext()) {
//                    Goal roleGoal = (Goal) enumRoleGoals.next();
//                    Collection vRoleSubGoals = roleGoal.getSubGoals();
//                    //NAO PRECISA DISSO
//                    if (vRoleSubGoals == null) {
//                        if (roleGoal.getName().equals("negotiate")) {
//                            roleGoal.setValue("itemToBuy_123");
//                        }
//                    } ///////
//                    else {
//                        Iterator enumRoleSubGoals = vRoleSubGoals.iterator();
//                        while (enumRoleSubGoals.hasNext()) {
//                            Goal roleSubGoal = (Goal) enumRoleSubGoals.next();
//                            if (roleSubGoal.getName().equals("negotiate")) {
//                                roleSubGoal.setValue("itemToBuy_123");
//                            }
//                        }
//                    }
//                }
//
//
//                //Role Buyer02 goal is to buy the item ABC
//                Collection vRoleGoals2 = agRoleBuyer2.getGoals();
//                Iterator enumRoleGoals2 = vRoleGoals2.iterator();
//                while (enumRoleGoals2.hasNext()) {
//                    Goal roleGoal = (Goal) enumRoleGoals2.next();
//                    Collection vRoleSubGoals = roleGoal.getSubGoals();
//                    if (vRoleSubGoals == null) {
//                        if (roleGoal.getName().equals("negotiate")) {
//                            roleGoal.setValue("itemToBuy_ABC");
//                        }
//                    } else {
//                        Iterator enumRoleSubGoals = vRoleSubGoals.iterator();
//                        while (enumRoleSubGoals.hasNext()) {
//                            Goal roleSubGoal = (Goal) enumRoleSubGoals.next();
//                            if (roleSubGoal.getName().equals("negotiate")) {
//                                roleSubGoal.setValue("itemToBuy_ABC");
//                            }
//                        }
//                    }
//                }
//
//                /////////////////////////////////////////////////////////////////////
//                //////////////////////////
//                ///Iniciando as Threads
//                agentBVivianeThread.start();
//                agentBViviane2Thread.start();
//
//            } else {
//                mainOrg.removeAgentRole(agRoleBuyer2);
//            }
//
//        /*
//        priority = 0; goal achieved
//        priority range from 0 to 3.
//         */
//        }
    }
}
