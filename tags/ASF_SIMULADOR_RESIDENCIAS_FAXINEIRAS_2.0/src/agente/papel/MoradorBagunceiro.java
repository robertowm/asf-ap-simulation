/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import agente.comportamento.Bagunceiro;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heliokann
 */
public class MoradorBagunceiro extends Morador implements Serializable {

    public MoradorBagunceiro() {
        this("", null);
        beliefs = new ArrayList(new Bagunceiro().getCrencas());
    }

    public MoradorBagunceiro(String nome, MainOrganization organizacao) {
        super("Bagunceiro" + nome, organizacao);
        beliefs = new ArrayList(new Bagunceiro().getCrencas());

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
