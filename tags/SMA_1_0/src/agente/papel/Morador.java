/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import static util.ConstantesAplicacao.*;

import crenca.RepositorioCrencas;
import framework.FIPA.AMS;
import framework.agentRole.AgentRole;
import framework.organization.MainOrganization;
import java.io.Serializable;

/**
 *
 * @author heliokann
 */
public class Morador extends AgentRole implements Serializable{

    private AMS ams = AMS.getInstance();

    private String nome;

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
        return super.toString();
    }
}
