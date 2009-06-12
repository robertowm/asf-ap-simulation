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
import visual.JDesktop;

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
    
    public synchronized Comodo getComodoPorNome(String nome){
        for (Comodo comodo : listaComodos) {
            if(nome.equals(comodo.getNome())){
                return comodo;
            }
        }
        return null;
    }
    
//    public synchronized void atualizarComodo(Agent agent, Comodo comodo) {
//        Comodo c = mapaAgentesComodo.get(agent);
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNivelArrumacao());
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNivelLimpeza());
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNome());
//        comodo.setNivelArrumacao(c.getNivelArrumacao());
//        comodo.setNivelLimpeza(c.getNivelLimpeza());
//        comodo.setNome(c.getNome());
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNivelArrumacao());
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNivelLimpeza());
////        JDesktop.saidas.apendTexto(agent+"  -->>"+comodo.getNome());
//        
//    }

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
    }

    public Comodo pegarComodoAleatoriamente() {
        return listaComodos.get(GeradorRandomico.geraRandomico(listaComodos.size()));
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }
    
}

