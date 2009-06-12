/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.agentRole.AgentRole;
import framework.mentalState.belief.Belief;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.Collection;
import sis_multagente.Main;

/**
 *
 * @author heliokann
 */
public class Morador extends AgentRole implements Serializable{

    private AMS ams = AMS.getInstance();

    protected String nome;
    
    public Morador(){
        this.nome = PREFIXO_NOME_PAPEL + this.getClass().getSimpleName();
        this.setOwner(Main.OrganizacaoPrincipal);
    }

    public Morador(String nome, MainOrganization organizacao) {
        super();
        this.nome = PREFIXO_NOME_PAPEL + PREFIXO_PAPEL_MORADOR + nome;

//        this.setDuty(new Duty("AcaoVerificarComodo"));

//        this.setGoal(new ResidirFeliz());

        this.setOwner(organizacao);

//        this.setProtocol(null);

//        this.setRight(null);
//        this.startThread();
        
//        this.setRoleName("Morador");
//        this.setStatus(null);
    }



    @Override
    public String toString() {
        return nome;
    }
    
    
    public void setBeleafs(Collection<Belief> crencas){
        beliefs = crencas;
    }
}
