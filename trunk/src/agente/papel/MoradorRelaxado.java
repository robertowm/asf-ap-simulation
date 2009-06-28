/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import agente.comportamento.Relaxado;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heliokann
 */
public class MoradorRelaxado extends Morador implements Serializable {

    public MoradorRelaxado() {
        this("", null);
        beliefs = new ArrayList(new Relaxado().getCrencas());
    }

    public MoradorRelaxado(String nome, MainOrganization organizacao) {
        super("Relaxado" + nome, organizacao);
        beliefs = new ArrayList(new Relaxado().getCrencas());

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
