/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.papel;

import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.agentRole.AgentRole;
import framework.mentalState.belief.LeafBelief;
import framework.organization.MainOrganization;
import java.io.Serializable;

/**
 *
 * @author heliokann
 */
public class Empregada extends AgentRole implements Serializable{
    
    boolean manterResidenciaHabitavel;

    private AMS ams = AMS.getInstance();

    private String nome;

    public Empregada(String nome, MainOrganization organizacao) {
        super();
        this.nome = PREFIXO_NOME_PAPEL + PREFIXO_PAPEL_MORADOR + nome;

        this.setBelief(new LeafBelief("int", "limpa", 50));
        this.setBelief(new LeafBelief("int", "arruma", 50));

        this.setOwner(organizacao);

        this.setRight(null);

//        this.setDuty( new Duty("teste"));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
