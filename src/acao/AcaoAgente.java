/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package acao;

import framework.agent.Agent;
import framework.mentalState.Action;
import framework.mentalState.Message;

/**
 *
 * @author heliokann
 */
public abstract class AcaoAgente extends Action{

    public abstract boolean execute(Agent agente, Message msg);

}
