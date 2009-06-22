package ambiente;

import agente.UsuarioAgente;
import agente.papel.Secretaria;
import framework.FIPA.ElementID;
import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;
import objeto.Comodo;
import sis_multagente.Main;
import util.GeradorAgentes;

public class CentralAtendimento extends Ambiente implements Serializable {

    private ConcurrentLinkedQueue<Object[]> quadroTarefas = null;
    private UsuarioAgente secretaria = null;

    public CentralAtendimento(ElementID aid) {
        super(aid);

        listaComodos.add(new Comodo("Escritorio", this));
        listaComodos.add(new Comodo("Área de Espera", this));
        listaComodos.add(new Comodo("Banheiro", this));

        quadroTarefas = new ConcurrentLinkedQueue<Object[]>();
    }

    public synchronized void adicionarTarefa(Comodo comodo) {
        Object[] objeto = {comodo.getAmbiente(), comodo};
        if( !quadroTarefas.contains(objeto) ) {
            quadroTarefas.add(objeto);
        }
    }

    public void carregarSecretaria() {
        this.secretaria = (UsuarioAgente) GeradorAgentes.gerarAgente("Secretária", new Secretaria(), this, Main.OrganizacaoPrincipal);
    }

    public synchronized Object[] pegarProximaTarefa() {
        return quadroTarefas.poll();
    }

    public UsuarioAgente getSecretaria() {
        return secretaria;
    }
}