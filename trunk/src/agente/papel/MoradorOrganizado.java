/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;


import agente.comportamento.Organizado;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heliokann
 */
public class MoradorOrganizado extends Morador implements Serializable{

    public MoradorOrganizado(){
        beliefs = new ArrayList(new Organizado().getCrencas());
    }

    public MoradorOrganizado(String nome, MainOrganization organizacao) {
        super(nome, organizacao);
        beliefs = new ArrayList(new Organizado().getCrencas());

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
