/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import agente.comportamento.Bagunceiro;
import agente.comportamento.Comportamento;
import agente.comportamento.Equilibrado;
import agente.comportamento.Faxineira;
import agente.comportamento.Higienico;
import agente.comportamento.NaoHigienico;
import agente.comportamento.Organizado;
import agente.comportamento.Relaxado;
import agente.papel.Empregada;
import agente.papel.Morador;
import fabrica.FabricaAgente;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import framework.mentalState.belief.Belief;
import framework.organization.MainOrganization;
import objetivo.ResidirFeliz;
import objetivo.TornarResidenciaHabitavel;
import plano.PlanoFaxina;
import plano.PlanoHabitar;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author robertowm
 */
public class GeradorAgentes {


    public static Agent gerarEmpregada(MTS_Environment ambiente, MainOrganization organizacao) {
        String nome = GeradorNomes.gerarNome() + ":" + ConstantesAplicacao.PAPEL_EMPREGADA;
        Empregada papelEmpregada = new Empregada(nome, organizacao);

        Agent agente = FabricaAgente.getAgente(nome, papelEmpregada, ambiente, organizacao);
        papelEmpregada.setAgent(agente);

        agente.setGoal(new TornarResidenciaHabitavel());
        
        agente.setPlan(new PlanoFaxina());
        
        Faxineira f = new Faxineira();
        for (Belief belief : f.getCrencas()) {
            agente.setBelief(belief);
        }

        GerenciadorFluxos.registrarFluxo(agente.getAgentName().getName(), agente);
        

        ambiente.registerAgents(agente);
        

        return agente;
    }

    public static Agent gerarMorador(MTS_Environment ambiente, MainOrganization organizacao) {
        String nome = GeradorNomes.gerarNome() + ":" + ConstantesAplicacao.PAPEL_MORADOR;
        Morador papelMorador = new Morador(nome, organizacao);

        Agent agente = FabricaAgente.getAgente(nome, papelMorador, ambiente, organizacao);

        papelMorador.setAgent(agente);

        Principal tela = JDesktop.getTela(agente);

        Comportamento c = null;
        switch(GeradorRandomico.geraRandomico(6)) {
            case 0:
                c = new Bagunceiro();
                break;
            case 1:
                c = new Equilibrado();
                break;
            case 2:
                c = new Higienico();
                break;
            case 3:
                c = new NaoHigienico();
                break;
            case 4:
                c = new Organizado();
                break;
            case 5:
                c = new Relaxado();
                break;
        }
        tela.apendTexto("Comportamento: " + c.getClass().getSimpleName());
        for (Belief belief : c.getCrencas()) {
            agente.setBelief(belief);
        }

        agente.setGoal(new ResidirFeliz());
        
        agente.setPlan(new PlanoHabitar());

        GerenciadorFluxos.registrarFluxo(agente.getAgentName().getName(), agente);

        ambiente.registerAgents(agente);

        return agente;
    }
}