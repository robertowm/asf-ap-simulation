/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agente.papel;

import static util.ConstantesAplicacao.*;

import framework.FIPA.AMS;
import framework.mentalState.belief.LeafBelief;
import framework.organization.MainOrganization;
import java.io.Serializable;
import sis_multagente.Main;

/**
 *
 * @author heliokann
 */
public class Empregada extends Papel implements Serializable{
    
    boolean manterResidenciaHabitavel;

    private AMS ams = AMS.getInstance();

    public Empregada(){
        super("Empregada");
        this.beliefs.add(new LeafBelief("int", "arruma", 50));
        this.beliefs.add(new LeafBelief("int", "limpa", 50));
        this.setOwner(Main.OrganizacaoPrincipal);
    }

    public Empregada(String nome, MainOrganization organizacao) {
        super(PREFIXO_PAPEL_MORADOR + nome);

        this.beliefs.add(new LeafBelief("int", "arruma", 50));
        this.beliefs.add(new LeafBelief("int", "limpa", 50));

        this.setOwner(organizacao);

        this.setRight(null);

//        this.setDuty( new Duty("teste"));
    }

    @Override
    public String toString() {
        return nome;
    }
}
