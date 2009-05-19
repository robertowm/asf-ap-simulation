/*
 * Created on 24/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA.communication.http;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import framework.FIPA.AMS;
import framework.FIPA.ElementID;
import framework.FIPA.EnvironmentDescription;
import framework.FIPA.communication.ReceivedObjectDescription;
import framework.environment.MTS_Environment;
import framework.mentalState.Message;

/**
 * Responsável por representar as características estruturais e comportamentais
 * do comunicador do cliente com o servidor.
 */
public class HttpClient {
    /*#framework.FIPA.communication.ReceivedObjectDescription Dependency_Link1*/
    /*#framework.environment.MTS_Environment Dependency_Link2*/

    /**
     * Número da porta de um ou mais servidores que receberão uma mensagem.
     */
    private int port = 1500;
    /**
     * 
     * @clientCardinality 0..*
     * @supplierCardinality 1
     */
    /**
     * Mensagem a ser transmitida.
     */
    private Message message;
    /**
     * Conjunto de receptores (agentes e/ou organizações) da mensagem.
     */
    private List receivers = null;
    /**
     * Tempo em que uma conexão de cliente irá tentar se comunicar com um servidor.
     */
    private int timeout = 7000;
    /**
     * Nomes das plataformas que irão receber uma mensagem.
     */
    private ArrayList plataformsName = new ArrayList();

    /**
     * Construtor da classe que atribui uma série de informações.
     * @param port
     * Número da porta de um ou mais servidores que estão aguardando conexões
     * de clientes.
     * @param message
     * Mensagem a ser transmitida.
     */
    public HttpClient(int port, Message message) {
        this.port = port;
        this.message = message;
        initClient();
    }

    /**
     * Construtor da classe que atribui a mensagem que será enviada a um ou
     * mais servidores.
     * @param message
     * Mensagem a ser enviada.
     */
    public HttpClient(Message message) {
        this.message = message;
        initClient();
    }

    /**
     * Método responsável por verificar para quantos servidores deverá ser enviada
     * a mensagem. Após a verificação, são criadas instâncias da classe 
     * AccClient para cada servidor, com a finalidade de
     * realizar as conexões.
     */
    private void initClient() {
        int num = 0;
        receivers = message.getTo();

        StringTokenizer str;
        ArrayList names = new ArrayList();
        //System.err.println(arrayTo.size());
        String name;
        String namePlataform;

        // Guardo as plataformas que irao receber a mensagem e os nomes dos agentes 
        // ou organizações que irão receber a mensagem.
        while (num < receivers.size()) {
            //System.err.println(arrayTo.get( num ));
            str = new StringTokenizer(((ElementID) receivers.get(num)).getName(), "@");

            while (str.hasMoreTokens()) {
                name = str.nextToken();
                namePlataform = str.nextToken();
                //receivers.put( name, ( String ) arrayTo.get(num)  );		
                plataformsName.add(namePlataform);
                names.add(name + "@" + namePlataform);

            }
            num++;
        }

        ArrayList aux = new ArrayList();
        AccClient client;
        boolean control = false;

        //Manda uma mensagem para cada plataforma diferente.
        for (int i = 0; i < plataformsName.size(); i++) {
            for (int j = 0; j < aux.size(); j++) {
                if (((String) plataformsName.get(i)).compareTo((String) aux.get(j)) == 0) {
                    control = true;
                    break;
                }
            }

            if (!control) {
                aux.add(plataformsName.get(i));
                client =
                        new AccClient(
                        getAddress((String) names.get(i), message),
                        (String) plataformsName.get(i));
            } else {
                control = false;
            }
        }
    }

    /**
     * Método responsável por fornecer o endereço do agente
     * ou da organização que receberá uma mensagem.
     * @param name
     * Nome de um agente ou organização.
     * @param message
     * Mensagem a ser transmitida.
     * @return
     * Endereço do agente ou da organização que receberá uma mensagem.
     */
    private String getAddress(String name, Message message) {
        ElementID aux;
        int num = 0;

        while (num < receivers.size()) {
            aux = (ElementID) receivers.get(num);
            if (aux.getName().compareTo(name) == 0) {
                return (String) ((ArrayList) aux.getAddresses()).get(0);
            }
            num++;
        }
        return null;
    }

    /**
     * Responsável por representar as características estruturais e comportamentais
     * do ACC que irá transmitir uma mensagem para um servidor.
     */
    public class AccClient extends Thread {

        /**
         * Conector do ACC com um servidor.
         */
        private Socket connection;
        /**
         * Ip do servidor que receberá a conexão do ACC.
         */
        private String IpServidor;
        /**
         * Stream respónsável por receber objetos do servidor.
         */
        private ObjectInputStream input;
        /**
         * Stream responsável por enviar objetos ao servidor.
         */
        private ObjectOutputStream output;
        /**
         * Nome da plataforma dos receptores da mensagem.
         */
        private String plataformName;

        /**
         * Construtor da classe que atribui uma série de informações ao Acc e
         * procura fazer a comunicação com o servidor especificado por parâmetro.
         * @param IpServidor
         * Ip do servidor.
         * @param plataformName
         * Nome da plataforma dos receptores da mensagem.
         */
        public AccClient(String IpServidor, String plataformName) {
            this.IpServidor = IpServidor;
            this.plataformName = plataformName;

            try {
                connection = new Socket(InetAddress.getByName(IpServidor), port);
                connection.setSoTimeout(timeout);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());

            } //Ocorreu falha na conexao.
            catch (IOException e) {
                System.out.println(
                        "Não foi realizada a conexão com o servidor desejado!");
                return;

            }
            this.run();
        }

        /**
         * Método principal da classe, responsável por enviar a mensagem ao
         * servidor e depois receber mensagens que indiquem o sucesso ou falha
         * na transmissão.
         */
        public void run() {
            int num = 0;
            AMS ams = AMS.getInstance();
            Message messageAux = null;
            int qtdReceives = 0;

            while (num < plataformsName.size()) {
                if (((String) plataformsName.get(num)).compareTo(plataformName) == 0) {
                    qtdReceives++;
                }
                num++;
            }

            num = 0;

            try {
                output.writeObject(message);

                if (receivers != null) {
                    while (num < receivers.size()) {
                        try {
                            messageAux = (Message) input.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (messageAux != null) {
                            ams.setObjectsReceived(
                                    new ReceivedObjectDescription(
                                    connection.getInetAddress().toString()));
                            //System.err.println( connection.getInetAddress().toString());

                            //Envia mensagem para o agente ou organização que
                            //tinha enviado outra anteriormente para que seja
                            //informado o sucesso ou não da entrega.	
                            sendMessage(messageAux);
                            num++;
                        }
                        messageAux = null;
                    }
                }

            } catch (IOException io) {
                System.err.println("Erro ao enviar mensagem do cliente");
                io.printStackTrace();
            }
        }

        /**
         * Método responsável por chamar o serviço que irá entregar a mensagem
         * aos agentes e/ou organizações.
         * @param message
         * Mensagem a ser entregue aos agentes e/ou organizações.
         */
        private void sendMessage(Message message) {
            AMS ams = AMS.getInstance();
            ArrayList descriptions = (ArrayList) ams.getEnvironmentDescriptions();
            int num = 0;
            MTS_Environment env;

            //Indicará aqueles (agentes e/ou organizações) que receberam a mensagem. 
            ArrayList receiversAux;

            while (num < descriptions.size()) {
                env = (MTS_Environment) ((EnvironmentDescription) descriptions.get(num)).getEnvironment();
                receiversAux = (ArrayList) env.sendMessage(message);

                if (receiversAux.size() > 0) {
                    break;
                }
                num++;
            }
        }
    }
}