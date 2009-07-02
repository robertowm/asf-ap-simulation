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
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import framework.FIPA.AMS;
import framework.FIPA.AgentPlataformDescription;
import framework.FIPA.ElementID;
import framework.FIPA.EnvironmentDescription;
import framework.FIPA.communication.ReceivedObjectDescription;
import framework.environment.MTS_Environment;
import framework.mentalState.Message;

/**
 * Responsável por representar as características estruturais e comportamentais
 * do servidor da plataforma.
 */
public class HttpServer implements Runnable {
    /*#framework.FIPA.communication.ReceivedObjectDescription Dependency_Link1*/
    /*#framework.environment.MTS_Environment Dependency_Link2*/

    /**
     * Número da porta em que o servidor permitirá conexões de clientes.
     */
    private int port = 1500;
    /**
     * Socket do servidor.
     */
    private ServerSocket server;

    /**
     * Construtor da classe que atribui a porta que o servidor tentará abrir para
     * futuras conexões.
     */
    public HttpServer(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construtor da classe responsável por tentar abrir a porta padrão
     * para que permita conexões com clientes.
     */
    public HttpServer() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal da classe, responsável por receber pedidos de conexão
     * e instanciar objetos da classe AccServer, para tratar a troca de
     * informações com outro Acc.
     */
    public void run() {
        System.out.println("Servidor no ar ...");
        AccServer client = null;
        while (true) {
            try {
                client = new AccServer(server.accept());
                // Accept connection
                client.start();
                System.out.println(
                        "-->Servidor da plataforma " + AgentPlataformDescription.getInstance().getName() + " recebe conexao de um cliente<--");
            } catch (BindException b) {
                System.err.println("BindException - Server");
                b.printStackTrace();
            } catch (SocketException e) {
                System.err.println("SocketException - Server");
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Responsável por representar as características estruturais e comportamentais
     * do ACC do servidor para tratar mensagens enviadas por um outro ACC de um
     * cliente.
     */
    public class AccServer extends Thread {

        /**
         * Stream responsável por receber mensagens de um ACC.
         */
        private ObjectInputStream input;
        /**
         * Stream responsável por enviar mensagens para outros ACCs.
         */
        private ObjectOutputStream output;
        /**
         * Socket que permite a conexão do ACCserver com um outro ACC.
         */
        private Socket connection;

        /**
         * Construtor da classe responsável por atribuir o socket que permita
         * a conexão com um outro ACC.
         * @param socket
         * Socket que permite a conexão do ACCserver com um outro ACC.
         */
        public AccServer(Socket socket) {
            this.connection = socket;

            try {
                input = new ObjectInputStream(connection.getInputStream());
                output = new ObjectOutputStream(connection.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Método principal da classe que recebe as mensagens vindas de outro ACC,
         * as trata e depois envia mensagen(s) de resposta.
         */
        public void run() {
            AMS ams = AMS.getInstance();
            ArrayList descriptions =
                    (ArrayList) ams.getEnvironmentDescriptions();
            ArrayList envs = new ArrayList();
            int num = 0;
            Message message;

            while (num < descriptions.size()) {
                envs.add(
                        ((EnvironmentDescription) descriptions.get(num)).getEnvironment());
                num++;
            }

            num = 0;

            try {
                message = (Message) input.readObject();
                ams.setObjectsReceived(
                        new ReceivedObjectDescription(
                        server.getInetAddress().toString(),
                        connection.getInetAddress().toString()));
                if (message != null) {
                    ArrayList responses = sendMessage(envs, message);
                    if (responses != null) {
                        while (num < responses.size()) {
                            output.writeObject((Message) responses.get(num));
                            num++;
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        /**
         * Método responsável por chamar o serviço de entrega de mensagens.
         * Logo após, são geradas mensagens que indicam o sucesso ou falha
         * na entrega para que a ACC que enviou possa saber qual foi o resultado
         * final.
         * @param envs
         * Ambientes que oferecem o serviço de entrega de mensagens para agentes
         * ou organizações.
         * @param message
         * Mensagem a ser enviada para os seus receptores.
         * @return
         * Mensagens que indicam o sucesso ou falha na entrega para os seus
         * receptores.
         */
        private ArrayList sendMessage(ArrayList envs, Message message) {
            int num = 0;
            MTS_Environment env;
            ArrayList receiversAux = null;
            ArrayList receivers = new ArrayList();
            List to = message.getTo();
            ArrayList messages = new ArrayList();
            int control;

            //Guarda no array receivers o nome daqueles que receberam a mensagem
            while (num < envs.size()) {
                env = (MTS_Environment) envs.get(num);
                receiversAux = (ArrayList) env.sendMessage(message);

                control = 0;

                while (control < receiversAux.size()) {
                    receivers.add(
                            ((ElementID) receiversAux.get(control)).getName());
                    control++;
                }

                receiversAux = null;
                num++;
            }

            ElementID id;
            boolean value;

            List listAux = new ArrayList();
            listAux.add(message.getFrom());
            AgentPlataformDescription dsc = AgentPlataformDescription.getInstance();
            String plataformName = dsc.getName();
            String nameAux;

            StringTokenizer token;
            int qtdAgentsReceivers = 0;
            receiversAux = new ArrayList();
            ArrayList noReceivers = new ArrayList();

            control = 0;

            while (control < to.size()) {
                token =
                        new StringTokenizer(
                        ((ElementID) to.get(control)).getName(),
                        "@");
                token.nextToken();
                nameAux = token.nextToken();

                if (nameAux.compareTo(plataformName) == 0) {
                    qtdAgentsReceivers++;
                    receiversAux.add(to.get(control));
                } else {
                    noReceivers.add(to.get(control));
                }
                control++;
            }

            control = 0;
            Message aux;

            while (control < qtdAgentsReceivers) {
                id = (ElementID) receiversAux.get(control);
                num = 0;
                value = false;
                while (num < receivers.size()) {
                    if (id.getName().compareToIgnoreCase((String) receivers.get(num)) == 0) {
                        value = true;
                        break;
                    }
                    num++;
                }

                aux =
                        new Message(
                        "not necessary",
                        "not necessary",
                        (ElementID) to.get(control),
                        listAux);

                if (!value) {
                    aux.setPerformative("inform:fail");
                    aux.setComments(
                            ((ElementID) listAux.get(0)).getName() + " tentou enviar a mensagem para " + ((ElementID) to.get(control)).getName() + " mas não conseguiu, pois este não existe na plataforma " + plataformName + ".");
                } else {
                    aux.setPerformative("inform:sucess");
                    aux.setComments(
                            ((ElementID) listAux.get(0)).getName() + " enviou a mensagem para " + ((ElementID) to.get(control)).getName() + " com sucesso.");
                }

                messages.add(aux);
                control++;
            }
            control = 0;

            // Manda uma mensagem para aqueles que não pertencem a plataforma,
            // indicando que a passagem da mensagem foi efetuada.
            while (control < noReceivers.size()) {
                aux =
                        new Message(
                        "not necessary",
                        "not necessary",
                        (ElementID) noReceivers.get(control),
                        listAux);
                aux.setPerformative("inform:fail");
                aux.setComments(
                        ((ElementID) listAux.get(0)).getName() + " tentou enviar a mensagem para " + ((ElementID) to.get(control)).getName() + " mas este não pertence a plataforma " + plataformName + ".");
                messages.add(aux);
                control++;
            }
            return messages;
        }
    }
}