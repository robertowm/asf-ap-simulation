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
//        for (Comodo comodo : listaComodos) {
//            listaAgentesPorComodo.add(new ArrayList<Agent>());
//        }
    }

    public Comodo pegarComodoPorAgente(Agent agente) {
        return mapaAgentesComodo.get(agente);
    }

    @SuppressWarnings("empty-statement")
    public synchronized void colocarAgenteComodo(Agent agent, Comodo comodo) {
        mapaAgentesComodo.put(agent, comodo);
//        int i = 0;
//        for (i = 0; i < listaComodos.size(); i++) {
//            Comodo c = listaComodos.get(i);
////            if(c.equals(comodo)) {
////                listaAgentesPorComodo.get(i).add(agent);
////                break;
////            }
//
//        }
    }

    public synchronized void tirarAgenteComodo(Agent agent, Comodo comodo) {
        int i = 0;
        for (i = 0; i < listaComodos.size(); i++) {
            Comodo c = listaComodos.get(i);
//            if(c.equals(comodo)) {
//                listaAgentesPorComodo.get(i).remove(agent);
//                break;
//            }

        }
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

