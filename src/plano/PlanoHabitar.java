/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plano;

import acao.AcaoArrumar;
import acao.AcaoChamarEmpregada;
import acao.AcaoDesarrumar;
import acao.AcaoLimpar;
import acao.AcaoSujar;
import acao.AcaoVerificarComodo;
import framework.agentRole.AgentRole;
import framework.mentalState.Action;
import framework.mentalState.Plan;
import framework.organization.MainOrganization;
import objetivo.ResidirFeliz;
import objetivo.TornarResidenciaHabitavel;

/**
 *
 * @author heliokann
 */
public class PlanoHabitar extends Plan{

    private Action acaoChamarEmpregada;
    private Action acaoArrumar;
    private Action acaoDesarrumar;
    private Action acaoLimpar;
    private Action acaoSujar;
    private Action acaoVerificarComodo;

    public PlanoHabitar() {
        this.setAction(new AcaoChamarEmpregada());
        this.setAction(new AcaoDesarrumar());
        this.setAction(new AcaoArrumar());
        this.setAction(new AcaoLimpar());
        this.setAction(new AcaoSujar());
        this.setAction(new AcaoVerificarComodo());
        
        this.setGoal(new ResidirFeliz());
    }

    @Override
    public void execute(AgentRole role) {
//        super.execute(role);

    }

    @Override
    public void execute(MainOrganization organization) {
        System.out.println("[PlanoHabitar] chamada do metodo 'public void execute(MainOrganization organization)'");
    }

    @Override
    public boolean equals(Object obj) {
        return  (obj instanceof PlanoHabitar);
    }
    
    




}
