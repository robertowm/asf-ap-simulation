/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import agente.comportamento.Funcionario;

import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;
import sis_multagente.Main;

/**
 *
 * @author heliokann
 */
public class Secretaria extends Papel implements Serializable {

    public Secretaria() {
        this("Secretária", Main.OrganizacaoPrincipal);
    }

    public Secretaria(String nome, MainOrganization organizacao) {
        super("Secretária"  + nome);

        this.beliefs = new ArrayList(new Funcionario().getCrencas());

        this.setOwner(organizacao);

        this.setRight(null);

//        this.setDuty( new Duty("teste"));
    }

    @Override
    
    public  String toString() {
        return nome;
    }
}
