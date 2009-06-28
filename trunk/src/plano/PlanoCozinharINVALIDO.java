/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plano;

import framework.agentRole.AgentRole;
import framework.mentalState.Action;
import framework.mentalState.Condition;
import framework.mentalState.Plan;
import objetivo.ResidirFeliz;

/**
 *
 * @author heliokann
 */
public class PlanoCozinharINVALIDO extends Plan {

    public class AcaoFazerComida extends Action {

        public AcaoFazerComida() {
            this.setPreCondition(new Condition("boolean", "comida_pronta", false));
            this.setPostCondition(new Condition("boolean", "panela_suja", false));
            this.setPostCondition(new Condition("boolean", "comida_pronta", true));
            this.setPostCondition(new Condition("boolean", "panela_suja", true));
        }

        @Override
        public boolean execute() {
            return true;
        }
    }

    public class AcaoComer extends Action {

        public AcaoComer() {
            this.setPreCondition(new Condition("boolean", "comida_pronta", true));
            this.setPreCondition(new Condition("boolean", "louca_suja", false));
            this.setPostCondition(new Condition("boolean", "comida_pronta", false));
            this.setPostCondition(new Condition("boolean", "louca_suja", true));
        }

        @Override
        public boolean execute() {
            return true;
        }
    }

    public class AcaoLavarLouca extends Action {

        public AcaoLavarLouca() {
            this.setPreCondition(new Condition("boolean", "louca_suja", true));
            this.setPostCondition(new Condition("boolean", "louca_suja", false));
        }

        @Override
        public boolean execute() {
            return true;
        }
    }

    public class AcaoLavarPanela extends Action {

        public AcaoLavarPanela() {
            this.setPreCondition(new Condition("boolean", "panela_suja", true));
            this.setPostCondition(new Condition("boolean", "panela_suja", false));
        }

        @Override
        public boolean execute() {
            return true;
        }
    }

    public PlanoCozinharINVALIDO() {
        this.setGoal(new ResidirFeliz());
        this.setAction(new AcaoFazerComida());
        this.setAction(new AcaoComer());
        this.setAction(new AcaoLavarLouca());
        this.setAction(new AcaoLavarPanela());
    }

    @Override
    public void execute(AgentRole papel) {
//        System.out.println("Chamou");
//        Agent agente = papel.getAgentPlayingRole();
//
//        MTS_Environment ambiente = agente.getEnvironment();
//
//        if (!(ambiente instanceof Residencia)) {
//            return;
//        }
//        Residencia residencia = (Residencia) ambiente;
////        Comodo comodo = residencia.pegarComodoPorAgente(agente);
//
//        if(comodo.getTipo().compareTo("cozinha") != 0) {
//            return;
//        }
//
//        List<Action> listaAcoesPossiveis = new ArrayList<Action>();
//
//        for (Object object : this.getActions()) {
//            Action acao = (Action) object;
////            if(comodo.atendePreCondicoes(acao.getPreConditions())) {
//                listaAcoesPossiveis.add(acao);
////            }
//        }
//
//        Random random = new Random();
//        Action acaoEscolhida = listaAcoesPossiveis.get(random.nextInt(listaAcoesPossiveis.size()));
//
//        acaoEscolhida.execute();
//
//        System.out.println("Saindo do exe");
    }
}
