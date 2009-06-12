/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comunicacao;

import static util.ConstantesAplicacao.*;

import framework.FIPA.communication.http.MessageTransportProtocol;
import java.io.Serializable;

/**
 *
 * @author robertowm
 */
public class ProtocoloTransporteMensagem extends MessageTransportProtocol implements Serializable{

    private static ProtocoloTransporteMensagem instancia = null;

    private ProtocoloTransporteMensagem() {
        super();
        this.activateServer(MTP_PORTA);
    }

    public static ProtocoloTransporteMensagem getInstancia() {
        if(instancia == null) {
            instancia = new ProtocoloTransporteMensagem();
        }
        return instancia;
    }
}
