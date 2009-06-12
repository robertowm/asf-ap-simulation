/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente.papel;


import agente.comportamento.NaoHigienico;
import framework.organization.MainOrganization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heliokann
 */
public class MoradorNaoHigienico extends Morador implements Serializable{

    public MoradorNaoHigienico(){
        beliefs = new ArrayList(new NaoHigienico().getCrencas());
    }

    public MoradorNaoHigienico(String nome, MainOrganization organizacao) {
        super(nome, organizacao);
        beliefs = new ArrayList(new NaoHigienico().getCrencas());

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
