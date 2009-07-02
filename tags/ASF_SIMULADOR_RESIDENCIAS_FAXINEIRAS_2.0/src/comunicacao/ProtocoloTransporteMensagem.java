/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import static util.ConstantesAplicacao.*;

import framework.FIPA.communication.http.MessageTransportProtocol;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robertowm
 */
public class ProtocoloTransporteMensagem extends MessageTransportProtocol implements Serializable {

    private static int portaDisponivel = MTP_PORTA;
//    private static Map<String, ProtocoloTransporteMensagem> mapaProtocolos = new HashMap();
    private static ProtocoloTransporteMensagem instancia = null;

    private ProtocoloTransporteMensagem() {
        super();
    }
    /**
     * 
     * código comentado para funcionar em um mesmo PC, caso em PCs diferentes é necessario refatorar
     */

    public static synchronized ProtocoloTransporteMensagem getInstancia() {
//        ProtocoloTransporteMensagem instancia = mapaProtocolos.get(ambiente);
        if (instancia == null) {
            instancia = new ProtocoloTransporteMensagem();
//            portaDisponivel += 20;
            instancia.activateServer(portaDisponivel);
//            mapaProtocolos.put(ambiente, instancia);
        }
        return instancia;
    }
}
