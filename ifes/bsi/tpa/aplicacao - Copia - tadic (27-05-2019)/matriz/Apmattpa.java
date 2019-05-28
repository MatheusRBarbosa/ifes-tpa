/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic.aplicacao.matriz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Apmattpa {
    public static void main(String[] args) throws IOException {
        System.out.println("====== Programa iniciado ======\n");
        BufferedReader br = null;
        FileReader fr = null;
        String nome_arq = "E:\\BACKUP 04 - 10\\Desktop\\ativ-1-tpa-taddic\\bdaritmat.csv"; //Caminho do arquivo com operacoes
        String arqPath = "E:\\BACKUP 04 - 10\\Desktop\\ativ-1-tpa-taddic\\bdmatrizes\\"; //Caminho da pasta com as matrizes
        String arqMatriz;
        
        
        
        fr = new FileReader(nome_arq);
        br = new BufferedReader(fr);
        String line = br.readLine();
        arqMatriz = line+".txt";
        TADMatriz matriz = TADMatriz.carrega(arqPath+arqMatriz);
        TADMatriz matrizAux;
        System.out.println("Carregando arquivo: "+arqPath+arqMatriz);
        System.out.println("================ Matriz Inicial ================");
        matriz.imprimeMatriz();
        
        
        line = br.readLine();
        while( line != null){
            String splited[] = line.split(",");
            switch (splited[0]){
                case "+":
                    arqMatriz = splited[1]+".txt";
                    matrizAux = TADMatriz.carrega(arqPath+arqMatriz);
                    matriz = matriz.soma(matrizAux);
                    System.out.println("================ Matriz Somada ================");
                    matriz.imprimeMatriz();
                    break;
                case "-":
                    arqMatriz = splited[1]+".txt";
                    matrizAux = TADMatriz.carrega(arqPath+arqMatriz);
                    matriz = matriz.subtracao(matrizAux);
                    System.out.println("================ Matriz Subtraida ================");
                    matriz.imprimeMatriz();
                    break;
                case "*":
                    if(splited[1].matches("[0-9]+")){
                        Float multiplicador = Float.parseFloat(splited[1]);
                        matriz.vezesK(multiplicador);
                        System.out.println("================ Matriz VezesK ================");
                        matriz.imprimeMatriz();
                    }
                    else{
                        arqMatriz = splited[1]+".txt";
                        matrizAux = TADMatriz.carrega(arqPath+arqMatriz);
                        matriz = matriz.multi(matrizAux);
                        System.out.println("================ Matriz Multiplicada ================");
                        matriz.imprimeMatriz();
                    }
                    break;
                default:
                    matriz = matriz.transposta();
                    System.out.println("================ Matriz Transposta ================");
                    matriz.imprimeMatriz();
            }
            line = br.readLine();
        }
     
        System.out.println("================ Matriz Final ================");
        matriz.imprimeMatriz();
        matriz.salva(arqPath+"matrizFinal.txt");
        
    }
}

