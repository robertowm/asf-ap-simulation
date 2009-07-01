/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

import ambiente.Ambiente;
import agente.UsuarioAgente;
import agente.papel.Empregada;
import agente.papel.Morador;
import agente.papel.Secretaria;
import framework.mentalState.belief.Belief;
import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.mentalState.belief.LeafBelief;
import framework.organization.MainOrganization;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import objetivo.ResidirFeliz;
import objetivo.TornarResidenciaHabitavel;
import plano.PlanoFaxina;
import plano.PlanoHabitar;
import plano.PlanoSecretaria;
import sis_multagente.Main;
import util.GeradorRelatorio;
import visual.JDesktop;
import visual.Principal;

/**
 *
 * @author heliokann
 */
public class FabricaAgente {

    private static Map<String, Agent> mapaAgentes = new HashMap<String, Agent>();
    private static AMS ams = AMS.getInstance();

    public static Agent getAgente(String nome, AgentRole regraAgente, Ambiente ambiente, MainOrganization organizacao) {
        String nomeAgente = PREFIXO_NOME_AGENTE + nome + ":" + regraAgente.toString();

        Agent agente = mapaAgentes.get(nomeAgente);

        if (agente != null) {
            return agente;
        }
        
        ElementID elementID = ams.createAgentElementId(nomeAgente, true);
        elementID.setAddress(LOCAL_HOST);

        ElementID elementIdRegra = new ElementID(regraAgente.toString(), true);
        elementIdRegra.setAddress(LOCAL_HOST);

        agente = new UsuarioAgente(ambiente, organizacao, regraAgente, elementID);

        agente.setEnvironment(ambiente);
        
//        List<Comodo> listaComodos = ambiente.getListaComodos();
//        int valor = GeradorRandomico.geraRandomico(listaComodos.size());
//        listaComodos.get(valor).adicionaRemoveAgente(agente, true);
//        ambiente.colocarAgenteComodo(agente, listaComodos.get(valor));
        
        if(regraAgente instanceof Empregada){
            Empregada papelEmpregada = new Empregada();
            agente.setGoal(new TornarResidenciaHabitavel());
            agente.setPlan(new PlanoFaxina());
            regraAgente = papelEmpregada;
        }else if(regraAgente instanceof Morador){
            Morador papelMorador = new Morador();
            papelMorador.setBeleafs(regraAgente.getBeliefs());
            agente.setGoal(new ResidirFeliz());
            agente.setPlan(new PlanoHabitar());
            regraAgente = papelMorador;
        }else if(regraAgente instanceof Secretaria){
            Secretaria papelSecretaria = new Secretaria();
            agente.setGoal(new TornarResidenciaHabitavel());
            agente.setPlan(new PlanoSecretaria());
            regraAgente = papelSecretaria;
            ambiente.colocarAgenteComodo(agente, ambiente.getComodoPorNome("Escritorio"));
        }
        
        for (Object belief : regraAgente.getBeliefs()) {
            Belief b = (Belief) belief;
            Belief crenca = new LeafBelief(b.getType(), b.getName(), b.getValue());
            agente.setBelief(crenca);
        }

        regraAgente.setRoleName(elementIdRegra);
        agente.setRolesBeingPlayed(regraAgente, organizacao);
        agente.createAgentRoleDescription(regraAgente, elementIdRegra, "");
        regraAgente.setAgent(agente);
        ams.createDescription(agente, elementID, "");

        mapaAgentes.put(nomeAgente, agente);
        Principal p = new Principal();
        p.setVisible(true);
        p.setTitle(agente.toString());
        p.apendTexto("Agente Criado");
        Main.desktop.add(p);
        JDesktop.telagentes.put(agente, p);
        
        GeradorRelatorio.criarRelatorio(regraAgente);

        return agente;
    }

    public static boolean existeAgente(String nome, MTS_Environment ambiente) {
        Agent a = mapaAgentes.get(PREFIXO_NOME_AGENTE + nome);
        if (a == null || !a.getEnvironment().equals(ambiente)) {
            return false;
        }
        return true;
    }

    public static Collection getListaAgentes() {
        return mapaAgentes.values();
    }
}
