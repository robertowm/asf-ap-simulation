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

public class Residencia extends MTS_Environment implements Serializable {

    private List<Comodo> listaComodos = new ArrayList<Comodo>();
    private HashMap<Agent, Comodo> mapaAgentesComodo = new HashMap<Agent, Comodo>();
    private Vector<Agent> listaAgentes = new Vector<Agent>();
    private JAmbiente janela;

    public Residencia(ElementID aid) {
        super(aid);

        listaComodos.add(new Comodo("Quarto"/*, "Quarto", condicoes*/));
        listaComodos.add(new Comodo("Cozinha"/*, "cozinha", condicoes*/));
//        listaComodos.add(new Comodo("Sala"/*, "Sala", condicoes*/));
        listaComodos.add(new Comodo("Banheiro"/*, "Banheiro", condicoes*/));
//        listaComodos.add(new Comodo("Área"/*, "Área", condicoes*/));
    }

    public Residencia(ElementID aid, List<Comodo> listaComodos) {
        super(aid);
        this.listaComodos = listaComodos;
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

    public synchronized void trocarAgenteComodo(Agent agent, Comodo comodo) {
        mapaAgentesComodo.remove(agent);
        mapaAgentesComodo.put(agent, comodo);
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
}

