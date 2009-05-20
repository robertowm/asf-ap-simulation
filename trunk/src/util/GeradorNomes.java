/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

/**
 *
 * @author robertowm
 */
class GeradorNomes {

    private static final String[] listaNomes = {"Beto", "Kann", "Mario", "Thiago", "Douglas", "Daniel", "Samuel", "Marcello", "Walanem", "Caio", "Fanara", "Pedro", "Jessica", "Jack", "Igor", "Vitor", "Leticia", "Carlos", "Henrique", "Wagner", "Calvao", "Bruno", "Gleyph"};

    public static String gerarNome() {
        return listaNomes[GeradorRandomico.geraRandomico(listaNomes.length)];
    }
}
