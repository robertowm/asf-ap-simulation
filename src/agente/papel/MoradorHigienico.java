/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;

import agente.comportamento.Higienico;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heliokann
 */
public class MoradorHigienico extends Morador implements Serializable {

    public MoradorHigienico() {
        this("", null);
        beliefs = new ArrayList(new Higienico().getCrencas());
    }

    public MoradorHigienico(String nome, MainOrganization organizacao) {
        super("Higienico" + nome, organizacao);
        beliefs = new ArrayList(new Higienico().getCrencas());

        this.setOwner(organizacao);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
