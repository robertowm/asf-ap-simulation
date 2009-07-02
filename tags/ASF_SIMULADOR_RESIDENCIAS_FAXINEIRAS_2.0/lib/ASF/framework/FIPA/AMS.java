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
 * Responsável por representar as características estruturais e comportamentais
 * do sistema gerenciador de agente.
 */
public class AMS implements Serializable {

    /** 
     * @clientCardinality 1
     * @supplierCardinality 1
     */
    /**
     * Instância da classe AMS.
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
     * Instância que possui a descrição da plataforma de agente.
     */
    private AgentPlatformDescription apDescription = AgentPlatformDescription.getInstance();
    /**
     * Conjunto de descrições dos agentes residentes na plataforma de agente. 
     */
    private Collection agentDescriptions = new ArrayList();
    /**
     * Conjunto de descrições das organizações residentes na plataforma de agente.
     */
    private Collection organizationDescriptions = new ArrayList();
    /**
     * Conjunto de descrições dos ambientes residentes na plataforma de agente.
     */
    private Collection environmentDescriptions = new ArrayList();
    /**
     * Descrição dos objetos que indicam as mensagens recebidas pela plataforma.  
     */
    private Collection objectsReceived = new ArrayList();

    /**
     * Fornece a instância da classe AMS - aplicação do padrão singleton.
     * @return
     * Instância da classe AMS.
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
     * Método responsável por verificar se é possível criar um identificador
     * para um agente. 
     * @param name
     * Nome que deverá identificar um agente.
     * @param isLocal
     * Indicação se o agente relacionado ao identificador possui sua origem de
     * criação da plataforma local(true) ou não (false).
     * @return
     * Identificador de um agente.
     */
    public ElementID createAgentElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) agentDescriptions, "agent", isLocal);
        return elementId;
    }

    /**
     * Método responsável por verificar se é possível criar um identificador 
     * para uma organização. 
     * @param name
     * Nome que deverá identificar uma organização.
     * @param isLocal
     * Indicação se a organização relacionada ao identificador possui sua origem de
     * criação da plataforma local(true) ou não (false).
     * @return
     * Identificador de uma organização.
     */
    public ElementID createOrganizationElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) organizationDescriptions, "organization", isLocal);
        return elementId;
    }

    /**
     * Método responsável por verificar se é possível criar um identificador
     * para um ambiente. 
     * @param name
     * Nome que deverá identificar um ambiente.
     * @param isLocal
     * Indicação se o ambiente relacionado ao identificador possui sua origem de
     * criação da plataforma local(true) ou não (false).
     * @return
     * Identificador de um ambiente.
     */
    public ElementID createEnvironmentElementId(String name, boolean isLocal) {
        ElementID elementId = (ElementID) searchName(name, (ArrayList) environmentDescriptions, "environment", isLocal);
        return elementId;
    }

    /**
     * Método responsável por criar a descrição de um agente, de uma organização ou 
     * de um ambiente.
     * @param object
     * Objeto que identifica se deverá ser criada a instância de um agente,
     * de uma organização ou de um ambiente.
     * @param elementId
     * Identificador de um agente, de uma organização ou de um ambiente.
     * @param ownership
     * Proprietário de um agente, de uma organização ou de um ambiente.
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
     * Método responsável por realizar a busca de agentes a partir de uma descrição
     * passada por parâmetro.
     * @param template
     * Descrição de agente contendo as informações que serão usadas na busca.    
     * @param maxResults
     * Quantidade máxima possível de agentes a serem obtidos na busca.
     * @return
     * Descrições dos agentes encontrados na busca. 
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
     * Método responsável por fazer um match em cima das informações passadas 
     * por parâmetro.
     * @param name
     * Informação a ser verificada em cima de outra.
     * @param mainName
     * Informação principal que terá um match feito em cima dela.
     * @return
     * Retorna o valor true se a informação "name" encontrasse dentro da "mainName"
     * ou se é igual a ela, caso contrário retornará false.
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
     * Fornece as descrições de agentes residentes na plataforma.
     * @return
     * Descrições de agentes residentes na plataforma.
     */
    public Collection getAgentDescriptions() {
        return agentDescriptions;
    }

    /**
     * Fornece a descrição da plataforma.
     * @return
     * Descrição da plataforma.
     */
    public AgentPlatformDescription getDescriptionPlataform() {
        return apDescription;
    }

    /**
     * Método responsável por realizar mudanças no registro de um agente.
     * @param dsc
     * Descrição usada para remover alguma outra decrição registrada no AMS que tenha o mesmo
     * agente.
     */
    public void modify(AMSAgentDescription dsc) {
        int num = 0;
        AMSAgentDescription aux;
        ArrayList agentDescriptions = (ArrayList) this.agentDescriptions;
        while (num < agentDescriptions.size()) {
            aux = (AMSAgentDescription) agentDescriptions.get(num);
            if (dsc.getAgent() == aux.getAgent() && dsc != aux) {
                System.out.println("***Modificação em uma descrição!***");
                deregister(aux);
                break;
            }
            num++;
        }
    }

    /**
     * Método responsável por criar um identificador  de um agente, de uma organização ou de um ambiente.
     * @param name
     * Nome que deverá estar contido no identificador a ser criado.
     * @param collection
     * Conjunto de descrições. Dependendo do tipo de identificador a ser criado, poderá ser
     * uma coleção de descrições de agentes, organizações ou ambientes.
     * @param type
     * Indicação se o identificador a ser criado será de um agente, de uma organização ou de um ambiente.
     * @param isLocal
     * Indicação se o agente relacionado ao identificador a ser criado tem sua origem na plataforma local (true) ou não 
     * (false).
     * @return
     * Caso seja possível instanciar um identificador a partir das informações passadas por 
     * parâmetro, ele será fornecido, caso contrário, o valor retornado será 
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
     * Fornece os agentes que estão desempenhando algum papel da organização fornecida por parâmetro.
     * @param organization
     * Organização que hospeda alguns papéis. 
     * @return
     * Conjunto de agentes que desempenham algum papel hospedado pela organização fornecida por parâmetro.
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
     * Método responsável por verificar se um agente já foi inserço em coleção de agentes. 
     * @param agent
     * Agente que será verificado se foi inserço na coleção de agentes passada por parâmetro. 
     * @param agents
     * Coleção de agentes.
     * @return
     * Valor booleano indicando se o agente já foi inserço na coleção (true), ou se ainda não foi (false).
     */
    private boolean verifyRepetitionAgent(Agent agent, Collection agents) {
        int num = 0;
        ArrayList allAgents = (ArrayList) agents;
        Agent aux;
        while (num < allAgents.size()) {
            aux = (Agent) allAgents.get(num);
            if (agent == aux) {
                return true; //Houve repetição

            }
            num++;
        }
        return false; //Não houve repetição

    }

    /**
     * Fornece todos os agentes que desempenham um tipo de papel específico de uma organização. 
     * @param organization
     * Organização que possui o papel.
     * @param role
     * Papel que deve estar sendo desempenhado por agentes.
     * @return
     * Agentes que desempenham um tipo de papel específico de uma organização.
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
     * Método responsável por informar se um agente está desempenhando um tipo de papel específico. 
     * @param agent
     * Agente a ser verificado.
     * @param role
     * Papel que deverá ser desempenhado pelo agente.
     * @return
     * Valor booleano indicando se o agente está desempenhando o papel (true), ou se não está (false). 
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
     * Fornece as instâncias de todos os agentes registrados na plataforma.
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
     * Fornece o agente que possui no seu identificador o nome fornecido por parâmetro. 
     * @param name
     * Nome que deverá estar no identificador de algum agente.
     * @return
     * Agente que possui no identificador o nome iniciado com o parâmetro fornecido pela função.
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
     * Fornece a organização que possui no seu identificador o nome fornecido por parâmetro. 
     * @param name
     * Nome que deverá estar no identificador de alguma organização.
     * @return
     * Organização que possui no identificador o nome iniciado com o parâmetro fornecido pela função.
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
     * Fornece o ambiente que possui no seu identificador o nome fornecido por parâmetro. 
     * @param name
     * Nome que deverá estar no identificador de algum ambiente.
     * @return
     * Ambiente que possui no identificador o nome iniciado com o parâmetro fornecido pela função.
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
     * Fornece as instâncias de todos os ambientes registrados na plataforma.
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
     * Fornece as instâncias de todas as organizações registradas na plataforma.
     * @return
     * Conjunto de organizações registradas na plataforma. 
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
     * Método responsável por realizar o resgitro de um agente, de uma organização 
     * ou de um ambiente.
     * @param object
     * Descrição de um agente, de uma organização ou de um ambiente.
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
     * Método responsável por realizar o dês-registro de um agente,
     * de uma organização ou de um ambiente.
     * @param object
     * Descrição de um agente, de uma organização ou de um ambiente.
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
     * Fornece o conjunto de descrições dos ambientes presentes na plataforma.
     * @return
     * Conjunto de descrições dos ambientes presentes na plataforma.
     */
    public Collection getEnvironmentDescriptions() {
        return environmentDescriptions;
    }

    /**
     * Fornece o conjunto de descrições das organizações presentes na plataforma.
     * @return
     * Conjunto de descrições das organizações presentes na plataforma.
     */
    public Collection getOrganizationDescriptions() {
        return organizationDescriptions;
    }

    /**
     * Fornece o conjunto de descrições dos objetos recebidos pela plataforma.
     * @return
     * Conjunto de descrições dos objetos recebidos pela plataforma.
     */
    public Collection getObjectsReceived() {
        return objectsReceived;
    }

    /**
     * Atribui uma nova descrição de objeto recebida pela plataforma.
     * @param object
     * Descrição de objeto recebida pela plataforma. 
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
