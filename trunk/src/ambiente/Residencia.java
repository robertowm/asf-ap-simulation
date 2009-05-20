package ambiente;

import framework.FIPA.ElementID;
import framework.agent.Agent;
import framework.environment.MTS_Environment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import objeto.Comodo;
import util.GeradorRandomico;

public class Residencia extends MTS_Environment implements Serializable{

    private List<Comodo> listaComodos = new ArrayList<Comodo>();
    private List<ArrayList<Agent>> listaAgentesPorComodo = new ArrayList<ArrayList<Agent>>();

    public Residencia(ElementID aid) {
        super(aid);
    }

    public Residencia(ElementID aid, List<Comodo> listaComodos) {
        super(aid);
        this.listaComodos = listaComodos;
        for (Comodo comodo : listaComodos) {
            listaAgentesPorComodo.add(new ArrayList<Agent>());
        }
    }

    public synchronized void adicionarComodo(Comodo comodo) {
        listaComodos.add(comodo);
        listaAgentesPorComodo.add(new ArrayList<Agent>());
    }

    public synchronized void removerComodo(Comodo comodo) {
        int valor = 0;
        for (int i = 0; i < listaComodos.size(); i++) {
            if(listaComodos.get(i).equals(comodo)) {
                valor = i;
                break;
            }
        }
        listaComodos.remove(valor);
        listaAgentesPorComodo.remove(valor);
    }

    @SuppressWarnings("empty-statement")
    public synchronized Comodo pegarComodoPorAgente(Agent agente) {
        int i = 0, j = 0;
        for (i = 0; i < listaAgentesPorComodo.size(); i++) {
            List<Agent> lista = listaAgentesPorComodo.get(i);
            for (j = 0; j < lista.size(); j++) {
                if(lista.get(j).equals(agente)) {
                    return listaComodos.get(i);
                }
            }
        }
        return null;
    }

    @SuppressWarnings("empty-statement")
    public synchronized void colocarAgenteComodo(Agent agent, Comodo comodo) {
        int i = 0;
        for (i = 0; i < listaComodos.size(); i++) {
            Comodo c = listaComodos.get(i);
            if(c.equals(comodo)) {
                listaAgentesPorComodo.get(i).add(agent);
                break;
            }

        }
    }

    public synchronized void tirarAgenteComodo(Agent agent, Comodo comodo) {
        int i = 0;
        for (i = 0; i < listaComodos.size(); i++) {
            Comodo c = listaComodos.get(i);
            if(c.equals(comodo)) {
                listaAgentesPorComodo.get(i).remove(agent);
                break;
            }

        }
    }



    @Override
    public synchronized void registerAgents(Agent newAgent) {
        super.registerAgents(newAgent);

        Comodo comodo = listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
        colocarAgenteComodo(newAgent, comodo);
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }
    
}

