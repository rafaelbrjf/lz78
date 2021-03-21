/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz78;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 *
 * @author dutra
 */

public class LZ78 {
    List<String> dict = null; //Inicia dicionario vazio
    
public LZ78() {
    dict = new ArrayList<>();
    dict.add(null);
}

public List<Character> compressao(List<Integer> listaN){
    List<Character> lista = new ArrayList<>();
    String code = "";
    String caract = "";
    boolean bool = false;
    int pos = 0;
    for(int i=0; i <= listaN.size() + 1; i++){
        int num = listaN.get(i);
        caract = caract + (char)num;
        if(!dict.contains(caract)){   
            dict.add(caract);
            code = code + 0;
            lista.add('0');
            code = code + caract;
            char c1 = caract.charAt(0);
            lista.add(c1);
            caract = "";
        } else{
            while(dict.contains(caract)){
                i++;
                if(i >= listaN.size()){
                    bool = true;
                    if(!dict.contains(caract)){
                    lista.add('0');
                    char c2 = caract.charAt(0);
                    lista.add(c2);
                    }else{
                        int index = dict.indexOf(caract);
                        String c3 = "" + index;
                        for (int j = 0; j < c3.length(); j++) {
                            lista.add(c3.charAt(j));
                        }
                    }
                    break;
                }
                pos = dict.indexOf(caract);
                num = listaN.get(i);
                caract = caract + (char)num;
            }

            if(bool){
                break;
            }
            i++;

            dict.add(caract);
            caract = "" + caract.charAt(caract.length()-1);    
            code = code + pos;

            String numero = "" + pos;
            for (int j = 0; j < numero.length(); j++) {
                lista.add(numero.charAt(j));
            }

            code = code + caract;   
            char c1 = caract.charAt(0);
            lista.add(c1);

            caract = "";
            i--;
        }
    }
    System.out.println(code); // Imprime no console
    return lista;
}


public void comprime(File txtEntrada, File txtSaida) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(txtEntrada));
        try (BufferedWriter br = new BufferedWriter(new FileWriter(txtSaida))) {
            List<Integer> listN = new ArrayList<>();
            int i=0;
            int l = bf.read();
            while(l!=-1) {
                listN.add(l);
                i++;
                l = bf.read();
            }        
            List<Character> code = compressao(listN);
            char[] codigo = new char[code.size()];       
            for (int j = 0; j < code.size(); j++) {
                codigo[j] = code.get(j);        
                br.write(codigo[j]);
            }
        }
}

public static void main(String[] args) throws IOException {
        LZ78 lz78 = new LZ78();
        File txtEntrada, txtSaida;
        txtEntrada = new File("input.txt");  // Pega arquivo input com a entrada
        txtSaida = new File("output.txt"); // Gera arquivo output com a saida
        lz78.comprime(txtEntrada, txtSaida);
    }
}