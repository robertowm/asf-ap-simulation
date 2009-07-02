package ambiente;

import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import objeto.Comodo;
import util.GeradorRandomico;
import visual.JAmbiente;

/**
 *
 * @author robertowm
 */
public class Ambiente extends MTS_Environment implements Serializable {

    protected List<Comodo> listaComodos = new ArrayList<Comodo>();
    protected HashMap<Agent, Comodo> mapaAgentesComodo = new HashMap<Agent, Comodo>();
    protected Vector<Agent> listaAgentes = new Vector<Agent>();
    protected JAmbiente janela = null;
    private String nome;

    public Ambiente(ElementID aid) {
        this(aid, new ArrayList<Comodo>());
    }

    public Ambiente(ElementID aid, List<Comodo> listaComodos) {
        super(aid);
        this.listaComodos = listaComodos;
        nome = elementId.getName();
    }

    public synchronized boolean isAmbienteArrumadoLimpo() {
        for (Comodo comodo : listaComodos) {
            if(!comodo.getNivelArrumacao().equals(Comodo.ARRUMADO) || !comodo.getNivelLimpeza().equals(Comodo.LIMPO)) {
                return false;
            }
        }
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
//        System.out.println("#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$#$");
        return true;
    }

    public Comodo pegarComodoPorAgente(Agent agente) {
        return mapaAgentesComodo.get(agente);
    }

    public synchronized void colocarAgenteComodo(Agent agent, Comodo comodo) {
        mapaAgentesComodo.put(agent, comodo);
    }

    public synchronized Comodo getComodoPorNome(String nome) {
        for (Comodo comodo : listaComodos) {
            if (nome.equals(comodo.getNome())) {
                return comodo;
            }
        }
        return null;
    }

    public void setJanela(JAmbiente jAmbiente) {
        janela = jAmbiente;
    }

    public synchronized void trocarAgenteComodo(Agent agent, Comodo antigo, Comodo novo) {
        mapaAgentesComodo.remove(agent);
        mapaAgentesComodo.put(agent, novo);
        antigo.adicionaRemoveAgente(agent, false);
        novo.adicionaRemoveAgente(agent, true);
    }

    public List<Comodo> getListaComodos() {
        return listaComodos;
    }

    @Override
    public synchronized void registerAgents(Agent newAgent) {
        super.registerAgents(newAgent);
        mapaAgentesComodo.put(newAgent, pegarComodoAleatoriamente());
        listaAgentes.add(newAgent);
        janela.setListAgentes(listaAgentes);
    }
    
    @Override
    public synchronized void cancelAgentRegister(Agent newAgent) {
        super.cancelAgentRegister(newAgent);
        mapaAgentesComodo.remove(newAgent);
        listaAgentes.remove(newAgent);
        janela.setListAgentes(listaAgentes);
    }

    public Comodo pegarComodoAleatoriamente() {
        return listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
    }

    public Comodo pegarOutroComodoAleatoriamente(Comodo comodo) {
        Comodo c = null;
        do{
            c = listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
        }while(c.equals(comodo));

        return c;
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Ambiente)){
            return false;
        }
        return nome.equals(((Ambiente)obj).nome);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }
}