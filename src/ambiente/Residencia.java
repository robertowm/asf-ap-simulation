package ambiente;

import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import objeto.Comodo;
import util.GeradorRandomico;

public class Residencia extends MTS_Environment implements Serializable{

    private List<Comodo> listaComodos = new ArrayList<Comodo>();
    private HashMap<Agent, Comodo> mapaAgentesComodo = new HashMap<Agent, Comodo>();

    public Residencia(ElementID aid) {
        super(aid);
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

    public synchronized void trocarAgenteComodo(Agent agent, Comodo comodo) {
       mapaAgentesComodo.remove(agent);
       mapaAgentesComodo.put(agent, comodo);
    }



    @Override
    public synchronized void registerAgents(Agent newAgent) {
        super.registerAgents(newAgent);
        Comodo comodo = listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
        mapaAgentesComodo.put(newAgent, comodo);
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }
    
}

