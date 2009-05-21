/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizacao;

import framework.FIPA.ElementID;
import framework.FIPA.communication.MTP;
import framework.environment.MTS_Environment;
import framework.mentalState.Plan;
import framework.mentalState.goal.Goal;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author heliokann
 */
public class Habitacao extends MainOrganization implements Serializable {

    public Habitacao(MTS_Environment ambiente, ElementID idElemento, MTP protocoloTransporteMensagem) {
        super(ambiente, idElemento, protocoloTransporteMensagem);
    }

    @Override
    protected Plan selectingPlan(Collection arg0, Goal arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void executingPlan(Plan arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Goal selectingGoalToAchieve() {
        Goal objetivoSelecionado = null;

        for (Object object : this.getGoals()) {
            Goal objetivo = (Goal) object;

            if (objetivo.getPriority() > objetivoSelecionado.getPriority()) {
                objetivoSelecionado = objetivo;
            }
        }

//        System.out.println("[Habitacao:" + this.getDescription().getElementId().getName() + "] Metodo 'selectingGoalToAchieve': objetivo -> " + objetivoSelecionado);

        return objetivoSelecionado;
    }

    @Override
    protected boolean checkIfWillContinue() {
//        System.out.println("[Habitacao:" + this.getDescription().getElementId().getName() + "] Metodo 'checkIfWillContinue': retorno -> " + false + " -> Motivo: NAO TEM! NAO TEM DOCUMENTACAO DESSE METODO!");
        return false;
    }
}