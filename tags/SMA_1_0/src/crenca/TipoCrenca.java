/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crenca;

import java.io.Serializable;

/**
 *
 * @author robertowm
 */
public class TipoCrenca implements Serializable{

    private String nome;
    private String retorno;

    public TipoCrenca(String retorno, String nome) {
        this.nome = nome;
        this.retorno = retorno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
}