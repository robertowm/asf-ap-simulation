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

    private static final String[] listaNomes = {"Beto", "Kann", "Anna", "Mario", "Thiago", "Douglas", "Daniel", "Samuel", "Marcello", "Walanem", "Caio", "Fanara", "Pedro", "Jessica", "Jack", "Igor", "Vitor", "Leticia", "Carlos", "Henrique", "Wagner", "Calvao", "Bruno", "Gleyph"};
    private static int[] itens = new int[listaNomes.length];
    private static int posicao = 0;

    static {

        for (int i = 0; i < listaNomes.length; i++) {
            itens[i] = i;
        }

        int valor, temp;
        for (int i = 0; i < listaNomes.length; i++) {
            valor = GeradorRandomico.geraRandomico(listaNomes.length);

            temp = itens[i];
            itens[i] = itens[valor];
            itens[valor] = temp;
        }
    }

    public static String gerarNome() {
        try {
            return listaNomes[itens[posicao++]];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return "Nome" + posicao++;
        }
    }
}
