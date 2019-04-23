/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario.aplicacao;

import exerciciostpa.dicionario.Dicionario;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author barbosa
 */
public class Main {

    public static void main(String[] args) {
        Dicionario<String, String> dic = new Dicionario<>();

        String arquivoCSV = "E:\\BACKUP 04 - 10\\Documents\\NetBeansProjects\\TPA\\src\\exerciciostpa\\dicionario\\aplicacao\\arquivo.csv";

        BufferedReader br = null;

        String linha = "";
        String csvDivisor = ";";
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            while ((linha = br.readLine()) != null) {

                String[] dicionarioIngles = linha.split(csvDivisor);

                dic.insert(dicionarioIngles[0], dicionarioIngles[1]);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(dic.findByKey("run"));
        
        System.out.println(Arrays.toString(dic.getColisoes()));
    }
}
