/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.papel;

import static util.ConstantesAplicacao.*;

import framework.agentRole.AgentRole;
import framework.mentalState.belief.LeafBelief;
import framework.organization.MainOrganization;
import java.io.Serializable;
import sis_multagente.Main;

/**
 *
 * @author heliokann
 */
public class Secretaria extends AgentRole implements Serializable{
    
    private String nome;
    
    public Secretaria(){
        this.beliefs.add(new LeafBelief("int", "suja", 10));
        this.beliefs.add(new LeafBelief("int", "desarumma", 10));
        this.beliefs.add(new LeafBelief("int", "fazNada", 80));
        this.setOwner(Main.OrganizacaoPrincipal);
        nome = PREFIXO_NOME_PAPEL + "Secretária";
    }

    public Secretaria(String nome, MainOrganization organizacao) {
        super();
        this.nome = PREFIXO_NOME_PAPEL + PREFIXO_PAPEL_MORADOR + nome;
        
        this.beliefs.add(new LeafBelief("int", "suja", 10));
        this.beliefs.add(new LeafBelief("int", "desarumma", 10));
        this.beliefs.add(new LeafBelief("int", "fazNada", 80));
        this.setOwner(organizacao);

        this.setRight(null);

//        this.setDuty( new Duty("teste"));
    }

    @Override
    public String toString() {
        return nome;
    }
}
