/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package acao;

import framework.agent.Agent;
import framework.mentalState.Action;
import framework.mentalState.Message;
import java.io.Serializable;

/**
 *
 * @author heliokann
 */
public abstract class AcaoAgente extends Action implements Serializable{

    public abstract boolean execute(Agent agente, Message msg);

}
