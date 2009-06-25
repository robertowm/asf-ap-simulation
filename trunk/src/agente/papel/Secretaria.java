/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.papel;

import agente.comportamento.Funcionario;
import static util.ConstantesAplicacao.*;

import framework.agentRole.AgentRole;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;
import sis_multagente.Main;

/**
 *
 * @author heliokann
 */
public class Secretaria extends AgentRole implements Serializable{
    
    private String nome;
    
    public Secretaria(){
        this("Secretária", Main.OrganizacaoPrincipal);
    }

    public Secretaria(String nome, MainOrganization organizacao) {
        super();
        this.nome = PREFIXO_NOME_PAPEL + nome;
        
        this.beliefs = new ArrayList(new Funcionario().getCrencas());
        
        this.setOwner(organizacao);

        this.setRight(null);

//        this.setDuty( new Duty("teste"));
    }

    @Override
    public String toString() {
        return nome;
    }
}
