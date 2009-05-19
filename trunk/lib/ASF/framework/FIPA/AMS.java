/*
 * Created on 10/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.FIPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import framework.FIPA.communication.ReceivedObjectDescription;
import framework.agent.Agent;
import framework.agentRole.AgentRole;
import framework.environment.MTS_Environment;
import framework.organization.MainOrganization;

/**
 * Respons�vel por representar as caracter�sticas estruturais e comportamentais
 * do sistema gerenciador de agente.
 */
public class AMS implements Serializable {

    /** 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Inst�ncia da classe AMS.
     */
    private static AMS instance;
    private Collection addresses = null;
    private String name = null;
    /**
     * 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Inst�ncia que possui a descri��o da plataforma de agente.
     */
    private AgentPlatformDescription apDescription = AgentPlatformDescription.getInstance();
    /**
     * Conjunto de descri��es dos agentes residentes na plataforma de agente. 
     */
    private Collection agentDescriptions = new ArrayList();
    /**
     * Conjunto de descri��es das organiza��es residentes na plataforma de agente.
     */
    private Collection organizationDescriptions = new ArrayList();
    /**
     * Conjunto de descri��es dos ambientes residentes na plataforma de agente.
     */
    private Collection environmentDescriptions = new ArrayList();
    /**
     * Descri��o dos objetos que indicam as mensagens recebidas pela plataforma.  
     */
    private Collection objectsReceived = new ArrayList();

    /**
     * Fornece a inst�ncia da classe AMS - aplica��o do padr�o singleton.
     * @return
     * Inst�ncia da classe AMS.
     */
    public static AMS getInstance() {
        if (instance == null) {
            instance = new AMS();
            instance.setName();
        }

        return instance;
    }

    /**
     * 
     * @param addresses
     * @return
     */
    public static AMS getInstance(Collection addresses) {
        if (instance == null) {
            instance = new AMS();
            instance.setName();
            instance.setAddresses(addresses);
        }

        return instance;
    }

    /**
     * M�todo respons�vel por verificar se � poss�vel criar um identificador
     * para um agente. 
     * @param name
     * Nome que dever� identificar um agente.
     * @param isLocal
     * Indica��o se o agente relacionado ao identificador possui sua origem de
     * cria��o da plataforma local(true) ou n�o (false).
     * @return
     * Identificador de um agente.
     */
    public ElementID createAgentElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) agentDescriptions, "agent", isLocal);
        return elementId;
    }

    /**
     * M�todo respons�vel por verificar se � poss�vel criar um identificador 
     * para uma organiza��o. 
     * @param name
     * Nome que dever� identificar uma organiza��o.
     * @param isLocal
     * Indica��o se a organiza��o relacionada ao identificador possui sua origem de
     * cria��o da plataforma local(true) ou n�o (false).
     * @return
     * Identificador de uma organiza��o.
     */
    public ElementID createOrganizationElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) organizationDescriptions, "organization", isLocal);
        return elementId;
    }

    /**
     * M�todo respons�vel por verificar se � poss�vel criar um identificador
     * para um ambiente. 
     * @param name
     * Nome que dever� identificar um ambiente.
     * @param isLocal
     * Indica��o se o ambiente relacionado ao identificador possui sua origem de
     * cria��o da plataforma local(true) ou n�o (false).
     * @return
     * Identificador de um ambiente.
     */
    public ElementID createEnvironmentElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) environmentDescriptions, "environment", isLocal);
        return elementId;
    }

    /**
     * M�todo respons�vel por criar a descri��o de um agente, de uma organiza��o ou 
     * de um ambiente.
     * @param object
     * Objeto que identifica se dever� ser criada a inst�ncia de um agente,
     * de uma organiza��o ou de um ambiente.
     * @param elementId
     * Identificador de um agente, de uma organiza��o ou de um ambiente.
     * @param ownership
     * Propriet�rio de um agente, de uma organiza��o ou de um ambiente.
     */
    public void createDescription(Object object, ElementID elementId, String ownership) {
        Description description;

        if (object instanceof Agent) {
            description = new AMSAgentDescription();
            ((AMSAgentDescription) description).setAgent((Agent) object);
            register((AMSAgentDescription) description);
            ((Agent) object).setDescription(description);
        } else if (object instanceof MainOrganization) {
            description = new OrganizationDescription();
            ((OrganizationDescription) description).setOrganization((MainOrganization) object);
            register((OrganizationDescription) description);
            ((MainOrganization) object).setDescription(description);
        } else {
            description = new EnvironmentDescription();
            ((EnvironmentDescription) description).setEnvironment((MTS_Environment) object);
            register((EnvironmentDescription) description);
            ((MTS_Environment) object).setDescription(description);
        }

        description.setElementId(elementId);
        description.setOwnership(ownership);
        description.setState("Start");

    }

    /**
     * M�todo respons�vel por realizar a busca de agentes a partir de uma descri��o
     * passada por par�metro.
     * @param template
     * Descri��o de agente contendo as informa��es que ser�o usadas na busca.    
     * @param maxResults
     * Quantidade m�xima poss�vel de agentes a serem obtidos na busca.
     * @return
     * Descri��es dos agentes encontrados na busca. 
     */
    public Collection search(AMSAgentDescription template, long maxResults) {
        Collection result = new ArrayList();
        boolean control = false;
        int num = 0;
        int qtd = 0;
        ArrayList agentDescriptions = (ArrayList) this.agentDescriptions;
        AMSAgentDescription dsc;

        while (qtd < maxResults && num < agentDescriptions.size()) {
            dsc = (AMSAgentDescription) agentDescriptions.get(num);

            if (template.getElementId() != null) {
                //System.err.println("Teste1: " + template.getElementId().getName());
                //System.err.println("Teste2: " + dsc.getElementId().getName());

                if (searchMatch(template.getElementId().getName(), dsc.getElementId().getName())) {
                    control = true;
                }
            }

            if ((searchMatch(template.getOwnership(), dsc.getOwnership()) ||
                    (template.getState() != null &&
                    template.getState().compareTo(dsc.getState()) == 0)) &&
                    control == false) {
                control = true;
            }
            if (control) {
                result.add(dsc);
                qtd++;
            }

            control = false;
            num++;
        }

        return result;

    }

    /**
     * M�todo respons�vel por fazer um match em cima das informa��es passadas 
     * por par�metro.
     * @param name
     * Informa��o a ser verificada em cima de outra.
     * @param mainName
     * Informa��o principal que ter� um match feito em cima dela.
     * @return
     * Retorna o valor true se a informa��o "name" encontrasse dentro da "mainName"
     * ou se � igual a ela, caso contr�rio retornar� false.
     */
    private boolean searchMatch(String name, String mainName) {

        for (int i = 0; i <= mainName.length() - 1; i++) {
            for (int j = i + 1; j <= mainName.length(); j++) {
                if ((mainName.substring(i, j).toLowerCase()).compareTo(name.toLowerCase()) == 0) {
                    System.out.println(mainName);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Fornece as descri��es de agentes residentes na plataforma.
     * @return
     * Descri��es de agentes residentes na plataforma.
     */
    public Collection getAgentDescriptions() {
        return agentDescriptions;
    }

    /**
     * Fornece a descri��o da plataforma.
     * @return
     * Descri��o da plataforma.
     */
    public AgentPlatformDescription getDescriptionPlataform() {
        return apDescription;
    }

    /**
     * M�todo respons�vel por realizar mudan�as no registro de um agente.
     * @param dsc
     * Descri��o usada para remover alguma outra decri��o registrada no AMS que tenha o mesmo
     * agente.
     */
    public void modify(AMSAgentDescription dsc) {
        int num = 0;
        AMSAgentDescription aux;
        ArrayList agentDescriptions = (ArrayList) this.agentDescriptions;
        while (num < agentDescriptions.size()) {
            aux = (AMSAgentDescription) agentDescriptions.get(num);
            if (dsc.getAgent() == aux.getAgent() && dsc != aux) {
                System.out.println("***Modifica��o em uma descri��o!***");
                deregister(aux);
                break;
            }
            num++;
        }
    }

    /**
     * M�todo respons�vel por criar um identificador  de um agente, de uma organiza��o ou de um ambiente.
     * @param name
     * Nome que dever� estar contido no identificador a ser criado.
     * @param collection
     * Conjunto de descri��es. Dependendo do tipo de identificador a ser criado, poder� ser
     * uma cole��o de descri��es de agentes, organiza��es ou ambientes.
     * @param type
     * Indica��o se o identificador a ser criado ser� de um agente, de uma organiza��o ou de um ambiente.
     * @param isLocal
     * Indica��o se o agente relacionado ao identificador a ser criado tem sua origem na plataforma local (true) ou n�o 
     * (false).
     * @return
     * Caso seja poss�vel instanciar um identificador a partir das informa��es passadas por 
     * par�metro, ele ser� fornecido, caso contr�rio, o valor retornado ser� 
     * um valor nulo.
     */
    private Object searchName(String name, ArrayList collection, String type, boolean isLocal) {
        int num = 0;
        String auxName;

        while (num < collection.size()) {
            if (type.compareTo("organization") == 0) {
                OrganizationDescription object = (OrganizationDescription) collection.get(num);
                auxName = object.getElementId().getName();
            } else if (type.compareTo("environment") == 0) {
                EnvironmentDescription object = (EnvironmentDescription) collection.get(num);
                ;
                auxName = object.getElementId().getName();
            } else //agent
            {
                AMSAgentDescription object = (AMSAgentDescription) collection.get(num);
                auxName = object.getElementId().getName();
            }

            if ((auxName.toLowerCase()).compareTo(name.toLowerCase()) == 0) {
                return null;
            }
            num++;
        }

        ElementID elementId = new ElementID(name, isLocal);
        return elementId;
    }

    /**
     * Fornece os agentes que est�o desempenhando algum papel da organiza��o fornecida por par�metro.
     * @param organization
     * Organiza��o que hospeda alguns pap�is. 
     * @return
     * Conjunto de agentes que desempenham algum papel hospedado pela organiza��o fornecida por par�metro.
     */
    public Collection searchAgentsbyOrganization(MainOrganization organization) {
        ArrayList allAgents = (ArrayList) getAgents();

        int num = 0;
        Agent agent;
        MainOrganization org;
        ArrayList agentsOfOrganization = new ArrayList();
        int num2 = 0;

        while (num < allAgents.size()) {
            agent = (Agent) allAgents.get(num);
            Vector orgs = (Vector) agent.getOrganizations();

            while (num2 < orgs.size()) {
                org = (MainOrganization) orgs.get(num2);

                if (org.getClass() == organization.getClass()) {
                    if (!verifyRepetitionAgent(agent, agentsOfOrganization)) {
                        agentsOfOrganization.add(agent);
                    }
                    break;
                }
                num2++;
            }
            num2 = 0;
            num++;
        }

        return agentsOfOrganization;
    }

    /**
     * M�todo respons�vel por verificar se um agente j� foi inser�o em cole��o de agentes. 
     * @param agent
     * Agente que ser� verificado se foi inser�o na cole��o de agentes passada por par�metro. 
     * @param agents
     * Cole��o de agentes.
     * @return
     * Valor booleano indicando se o agente j� foi inser�o na cole��o (true), ou se ainda n�o foi (false).
     */
    private boolean verifyRepetitionAgent(Agent agent, Collection agents) {
        int num = 0;
        ArrayList allAgents = (ArrayList) agents;
        Agent aux;
        while (num < allAgents.size()) {
            aux = (Agent) allAgents.get(num);
            if (agent == aux) {
                return true; //Houve repeti��o

            }
            num++;
        }
        return false; //N�o houve repeti��o

    }

    /**
     * Fornece todos os agentes que desempenham um tipo de papel espec�fico de uma organiza��o. 
     * @param organization
     * Organiza��o que possui o papel.
     * @param role
     * Papel que deve estar sendo desempenhado por agentes.
     * @return
     * Agentes que desempenham um tipo de papel espec�fico de uma organiza��o.
     */
    public Collection searchAgentsbyRole(MainOrganization organization, AgentRole role) {
        Collection agents = new ArrayList();
        int num = 0;
        Agent agent;
        ArrayList agentsOfOrganization = (ArrayList) searchAgentsbyOrganization(organization);

        while (num < agentsOfOrganization.size()) {
            agent = (Agent) agentsOfOrganization.get(num);

            if (searchAgentbyRoleAux(agent, role)) {
                agents.add(agent);
            }
            num++;
        }

        return agents;
    }

    /**
     * M�todo respons�vel por informar se um agente est� desempenhando um tipo de papel espec�fico. 
     * @param agent
     * Agente a ser verificado.
     * @param role
     * Papel que dever� ser desempenhado pelo agente.
     * @return
     * Valor booleano indicando se o agente est� desempenhando o papel (true), ou se n�o est� (false). 
     */
    private boolean searchAgentbyRoleAux(Agent agent, AgentRole role) {
        Vector roles = (Vector) agent.getRolesBeingPlayed();
        AgentRole roleAux;
        int num = 0;

        while (num < roles.size()) {
            roleAux = (AgentRole) roles.get(num);
            System.err.println(role.getClass());
            if (role.getClass() == roleAux.getClass()) {
                return true;
            }
            num++;
        }
        return false;

    }

    /**
     * Fornece as inst�ncias de todos os agentes registrados na plataforma.
     * @return
     * Conjunto de agentes registrados na plataforma. 
     */
    public Collection getAgents() {
        Collection agents = new ArrayList();
        AMSAgentDescription dsc;
        int num = 0;
        ArrayList descriptions = (ArrayList) agentDescriptions;
        while (num < descriptions.size()) {
            dsc = (AMSAgentDescription) descriptions.get(num);
            agents.add(dsc.getAgent());
            num++;
        }

        return agents;
    }

    /**
     * Fornece o agente que possui no seu identificador o nome fornecido por par�metro. 
     * @param name
     * Nome que dever� estar no identificador de algum agente.
     * @return
     * Agente que possui no identificador o nome iniciado com o par�metro fornecido pela fun��o.
     */
    public Agent getAgentByName(String name) {
        Agent agent = null;

        ArrayList agents = (ArrayList) getAgents();
        String aux;
        int num = 0;

        while (num < agents.size()) {
            agent = (Agent) agents.get(num);
            aux = agent.getAgentName().getName();

            if (aux.startsWith(name) == true) {
                return agent;
            }
            num++;
        }

        return null;
    }

    /**
     * Fornece a organiza��o que possui no seu identificador o nome fornecido por par�metro. 
     * @param name
     * Nome que dever� estar no identificador de alguma organiza��o.
     * @return
     * Organiza��o que possui no identificador o nome iniciado com o par�metro fornecido pela fun��o.
     */
    public MainOrganization getOrganizationByName(String name) {
        MainOrganization org = null;

        ArrayList orgs = (ArrayList) getOrganizations();
        String aux;
        int num = 0;

        while (num < orgs.size()) {
            org = (MainOrganization) orgs.get(num);
            aux = org.getOrganizationName().getName();

            if (aux.startsWith(name) == true) {
                return org;
            }
            num++;
        }

        return null;
    }

    /**
     * Fornece o ambiente que possui no seu identificador o nome fornecido por par�metro. 
     * @param name
     * Nome que dever� estar no identificador de algum ambiente.
     * @return
     * Ambiente que possui no identificador o nome iniciado com o par�metro fornecido pela fun��o.
     */
    public MTS_Environment getEnvironmentByName(String name) {
        MTS_Environment env = null;
        ArrayList envs = (ArrayList) getEnvironments();
        String aux;
        int num = 0;

        while (num < envs.size()) {
            env = (MTS_Environment) envs.get(num);
            aux = env.getEnvironmentName();

            if (aux.startsWith(name) == true) {
                return env;
            }
            num++;
        }

        return null;
    }

    /**
     * Fornece as inst�ncias de todos os ambientes registrados na plataforma.
     * @return
     * Conjunto de ambientes registrados na plataforma. 
     */
    public Collection getEnvironments() {
        Collection envs = new ArrayList();
        EnvironmentDescription dsc;
        int num = 0;
        ArrayList descriptions = (ArrayList) environmentDescriptions;
        while (num < descriptions.size()) {
            dsc = (EnvironmentDescription) descriptions.get(num);
            envs.add(dsc.getEnvironment());
            num++;
        }

        return envs;
    }

    /**
     * Fornece as inst�ncias de todas as organiza��es registradas na plataforma.
     * @return
     * Conjunto de organiza��es registradas na plataforma. 
     */
    public Collection getOrganizations() {
        Collection orgs = new ArrayList();
        OrganizationDescription dsc;
        int num = 0;
        ArrayList descriptions = (ArrayList) organizationDescriptions;
        while (num < descriptions.size()) {
            dsc = (OrganizationDescription) descriptions.get(num);
            orgs.add(dsc.getOrganization());
            num++;
        }

        return orgs;
    }

    /**
     * M�todo respons�vel por realizar o resgitro de um agente, de uma organiza��o 
     * ou de um ambiente.
     * @param object
     * Descri��o de um agente, de uma organiza��o ou de um ambiente.
     */
    public void register(Object object) {
        if (object instanceof AMSAgentDescription) {
            agentDescriptions.add(object);
        } else if (object instanceof OrganizationDescription) {
            organizationDescriptions.add(object);
        } else if (object instanceof EnvironmentDescription) {
            environmentDescriptions.add(object);
        }
        System.out.println("***Ocorreu um registro!***");
    }

    /**
     * M�todo respons�vel por realizar o d�s-registro de um agente,
     * de uma organiza��o ou de um ambiente.
     * @param object
     * Descri��o de um agente, de uma organiza��o ou de um ambiente.
     */
    public void deregister(Object object) {
        if (object instanceof AMSAgentDescription) {
            agentDescriptions.remove(object);
        } else if (object instanceof OrganizationDescription) {
            organizationDescriptions.remove(object);
        } else if (object instanceof EnvironmentDescription) {
            environmentDescriptions.remove(object);
        }
    }

    /**
     * Fornece o conjunto de descri��es dos ambientes presentes na plataforma.
     * @return
     * Conjunto de descri��es dos ambientes presentes na plataforma.
     */
    public Collection getEnvironmentDescriptions() {
        return environmentDescriptions;
    }

    /**
     * Fornece o conjunto de descri��es das organiza��es presentes na plataforma.
     * @return
     * Conjunto de descri��es das organiza��es presentes na plataforma.
     */
    public Collection getOrganizationDescriptions() {
        return organizationDescriptions;
    }

    /**
     * Fornece o conjunto de descri��es dos objetos recebidos pela plataforma.
     * @return
     * Conjunto de descri��es dos objetos recebidos pela plataforma.
     */
    public Collection getObjectsReceived() {
        return objectsReceived;
    }

    /**
     * Atribui uma nova descri��o de objeto recebida pela plataforma.
     * @param object
     * Descri��o de objeto recebida pela plataforma. 
     */
    public void setObjectsReceived(ReceivedObjectDescription object) {
        objectsReceived.add(object);
    }

    ///////////////////////////// Novo /////////////////////////////////
    /**
     * @return Returns the addresses.
     */
    public Collection getAddresses() {
        return addresses;
    }

    /**
     * @param addresses The addresses to set.
     */
    public void setAddresses(Collection addresses) {
        this.addresses = addresses;
    }

    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList();
        }
        this.addresses.add(address);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    private void setName() {
        String hap = AgentPlatformDescription.getInstance().getName();
        hap = "@" + hap;
        this.name = "ams" + hap;
    }
}
