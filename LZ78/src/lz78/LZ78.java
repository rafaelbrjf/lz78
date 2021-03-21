/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz78;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dutra
 */
public class LZ78 {

    public static String comprime(String input) {
        ArrayList<String> dicionario = new ArrayList();
        ArrayList<String> resultado = new ArrayList();
        String saida = new String();
        int offset = 0;
        while (offset < input.length()) {
            boolean flag = false;
            for (int i = input.length(); i > offset; i--) {
                if (dicionario.contains(input.substring(offset, i))) {
                    flag = true;
                    if (i + 1 > input.length()) {
                        resultado.add("(" + (dicionario.indexOf(input.substring(offset, i)) + 1) + ", EOF)");
                        offset = offset + input.substring(offset, i).length();
                        break;
                    }

                    dicionario.add(input.substring(offset, i + 1));
                    resultado.add("(" + (dicionario.indexOf(input.substring(offset, i)) + 1) + ", " + input.substring(i, i + 1) + ")");
                    offset = offset + input.substring(offset, i + 1).length();
                    break;
                }
            }
            if (!flag) {
                resultado.add("(0," + input.substring(offset, offset + 1) + ")");
                dicionario.add(input.substring(offset, offset + 1));
                offset++;
            }

        }
        for (String r : resultado) {
            saida = saida + r;
        }
        return saida;
    }

    public static void main(String[] args) throws IOException {
        String dataset = "input.txt";
        String input = new String();
        try (BufferedReader br = new BufferedReader(new FileReader(dataset))) {
            String l;
            while ((l = br.readLine()) != null) {
                input = input + l;
            }
        }
        String resultado = comprime(input);
        System.out.print(resultado);
    }

}
