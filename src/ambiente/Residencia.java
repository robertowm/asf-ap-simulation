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

    public Residencia(ElementID aid) {
        super(aid);
    }

    public Residencia(ElementID aid, List<Comodo> listaComodos) {
        super(aid);
        this.listaComodos = listaComodos;
    }

    public Comodo pegarComodoPorAgente(Agent agente) {
        for (Comodo comodo : listaComodos) {
            if(comodo.verificarAgente(agente)) {
                return comodo;
            }
        }
        return null;
    }

    @Override
    public synchronized void registerAgents(Agent newAgent) {
        super.registerAgents(newAgent);

        Comodo comodo = listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
        comodo.atribuirAgente(newAgent);
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }
    
}

