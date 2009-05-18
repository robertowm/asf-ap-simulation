/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import agente.papel.Morador;
import crenca.RepositorioCrencas;
import fabrica.FabricaAgente;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import framework.mentalState.goal.LeafGoal;
import framework.organization.MainOrganization;
import java.util.Random;

/**
 *
 * @author robertowm
 */
public class GeradorAgentes {

    private static Random random = new Random(System.currentTimeMillis());

    public static Agent gerarMorador(MTS_Environment ambiente, MainOrganization organizacao) {
        String nome = GeradorNomes.gerarNome();
        Morador papelMorador = new Morador(nome, organizacao);

        Agent agente = FabricaAgente.getAgente(nome, papelMorador, ambiente, organizacao);

        papelMorador.setAgent(agente);

        agente.setBelief(RepositorioCrencas.criarCrenca("gosta_casa_limpa", random.nextBoolean()));
        agente.setBelief(RepositorioCrencas.criarCrenca("gosta_casa_arrumada", random.nextBoolean()));
        agente.setBelief(RepositorioCrencas.criarCrenca("organizado", random.nextBoolean()));
        agente.setBelief(RepositorioCrencas.criarCrenca("higienico", random.nextBoolean()));

        agente.setGoal(new LeafGoal("boolean", "viver", "true"));

        GerenciadorFluxos.registrarFluxo(agente.getAgentName().getName(), agente);

        return agente;
    }

    public Agent gerarEmpregada() {

        return null;
    }

}
