package ambiente;

import agente.UsuarioAgente;
import agente.papel.Secretaria;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Comodo;
import sis_multagente.Main;
import util.GeradorAgentes;

public class CentralAtendimento extends Ambiente implements Serializable {

    class Pacote {

        String comodo;
        String ambiente;
        boolean ocupado;

        public Pacote(String comodo, String ambiente) {
            this.comodo = comodo;
            this.ambiente = ambiente;
            this.ocupado = false;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pacote)) {
                return false;
            }
            Pacote p = (Pacote) obj;
            return ambiente.equals(p.ambiente);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 13 * hash + (this.ambiente != null ? this.ambiente.hashCode() : 0);
            return hash;
        }

        @Override
        public String toString() {
            return comodo + "/" + ambiente + "/" + ocupado;
        }
    }
    private LinkedBlockingQueue<Pacote> quadroTarefas = null;
    private UsuarioAgente secretaria = null;
    private Semaphore semaforo = new Semaphore(1);

    public CentralAtendimento(ElementID aid) {
        super(aid);

        listaComodos.add(new Comodo("Área de Espera", this));
        listaComodos.add(new Comodo("Banheiro", this));
        listaComodos.add(new Comodo("Escritorio", this));

        quadroTarefas = new LinkedBlockingQueue<Pacote>();
    }

    public synchronized void adicionarTarefa(Comodo comodo) {
        try {
            String nomeAmbiente = comodo.getAmbiente().toString();
            semaforo.acquire();
            for (Pacote pacote : quadroTarefas) {
                if (pacote.ambiente.equals(nomeAmbiente)) {
                    semaforo.release();
                    return;
                }
            }
            quadroTarefas.add(new Pacote(comodo.toString(), nomeAmbiente));
            semaforo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(CentralAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarSecretaria() {
        this.secretaria = (UsuarioAgente) GeradorAgentes.gerarAgente("Secretária", new Secretaria(), this, Main.OrganizacaoPrincipal);
    }

    public synchronized Object[] pegarProximaTarefa(Agent agent) {
        try {
            semaforo.acquire();
            int count = quadroTarefas.size();
            while ( count-- > 0) {
                Pacote pacote = quadroTarefas.poll();
                quadroTarefas.add(pacote);
                if (!pacote.ocupado) {
                    pacote.ocupado = true;
                    Object[] obj = null;
                    obj = new Object[2];
                    obj[0] = pacote.ambiente;
                    obj[1] = pacote.comodo;
                    semaforo.release();
                    return obj;
                }
            }
            semaforo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(CentralAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized void avisarRetornoServico(Ambiente ambiente) {
        try {
            semaforo.acquire();
            Pacote pacote = null;
            for (Pacote p : quadroTarefas) {
                if (p.ambiente.equals(ambiente.toString())) {
                    pacote = p;
                    break;
                }
            }
            if (pacote != null) {
                quadroTarefas.remove(pacote);
            }
            semaforo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(CentralAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UsuarioAgente getSecretaria() {
        return secretaria;
    }
}