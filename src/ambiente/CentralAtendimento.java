package ambiente;

import agente.UsuarioAgente;
import agente.papel.Secretaria;
import fabrica.FabricaAmbiente;
import framework.FIPA.ElementID;
import framework.agent.Agent;
import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objeto.Comodo;
import sis_multagente.Main;
import util.GeradorAgentes;

public class CentralAtendimento extends Ambiente implements Serializable {

    class Pacote {
        String comodo;
        String ambiente;

        public Pacote(String comodo, String ambiente) {
            this.comodo = comodo;
            this.ambiente = ambiente;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pacote)) {
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
        
    }
    
    private LinkedBlockingQueue<Pacote> quadroTarefas = null;
    private Vector<Ambiente> ambientesOcupados = null;
    private UsuarioAgente secretaria = null;
    private Semaphore semaforo = new Semaphore(1);

    public CentralAtendimento(ElementID aid) {
        super(aid);

        listaComodos.add(new Comodo("Escritorio", this));
        listaComodos.add(new Comodo("Área de Espera", this));
        listaComodos.add(new Comodo("Banheiro", this));

        quadroTarefas = new LinkedBlockingQueue<Pacote>();
        ambientesOcupados = new Vector<Ambiente>();
    }

    public synchronized void adicionarTarefa(Comodo comodo) {
        try {
            semaforo.acquire();
            if(!ambientesOcupados.contains(comodo.getAmbiente())) {
                quadroTarefas.add(new Pacote(comodo.toString(), comodo.getAmbiente().toString()));
            }
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
            Pacote pacote = quadroTarefas.poll();
            Object[] obj = null;
            if (pacote != null) {
                ambientesOcupados.add(FabricaAmbiente.recuperarAmbientePorNome(pacote.ambiente));
                obj = new Object[2];
                obj[0] = pacote.ambiente;
                obj[1] = pacote.comodo;
            }
            semaforo.release();
            return obj;
        } catch (InterruptedException ex) {
            Logger.getLogger(CentralAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized void avisarRetornoServico(Ambiente ambiente) {
        try {
            semaforo.acquire();
//            if(!
                    ambientesOcupados.remove(ambiente);
//                    ){
//              JOptionPane.showMessageDialog(null, "Não removeu" );  
//            }
            semaforo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(CentralAtendimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public UsuarioAgente getSecretaria() {
        return secretaria;
    }
}