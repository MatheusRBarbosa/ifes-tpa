/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.aplicacao.matriz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class Apmattpa {
    public static void main(String[] args) throws IOException {
        System.out.println("====== Programa iniciado ======\n");
        BufferedReader br = null;
        FileReader fr = null;
        
        String csvFile = "bdaritmat.csv";
        
        Path relativePath = FileSystems.getDefault().getPath("src","ifes","bsi","tpa","taddic","aplicacao","matriz",csvFile); // Arquivo csv com as operacoes
        String csvPath = relativePath.toAbsolutePath().toString();
        
        Path relativeMatPath = FileSystems.getDefault().getPath("src","ifes","bsi","tpa","taddic","aplicacao","matriz","bdmatrizes");
        String matFiles = relativeMatPath.toAbsolutePath().toString();
        
        String arqMatriz;
        
        
        
        fr = new FileReader(csvPath);
        br = new BufferedReader(fr);
        String line = br.readLine();
        arqMatriz = line+".txt";
        TADMatriz matriz = TADMatriz.carrega(matFiles+"/"+arqMatriz);
        TADMatriz matrizAux;
        System.out.println("Carregando arquivo: "+matFiles+"/"+arqMatriz);
        System.out.println("================ Matriz Inicial ================");
        //matriz.imprimeMatriz();
        
        Float multiplicador;
        
        line = br.readLine();
        while( line != null){
            String splited[] = line.split(",");
            switch (splited[0]){
                case "+":
                    arqMatriz = splited[1]+".txt";
                    matrizAux = TADMatriz.carrega(matFiles+"/"+arqMatriz);
                    matriz = matriz.soma(matrizAux);
                    System.out.println("================ Matriz Somada ================");
                    matriz.imprimeMatriz();
                    break;
                case "-":
                    arqMatriz = splited[1]+".txt";
                    matrizAux = TADMatriz.carrega(matFiles+"/"+arqMatriz);
                    multiplicador = Float.parseFloat("-1");
                    matrizAux.vezesK(multiplicador);
                    matriz = matriz.soma(matrizAux);
                    System.out.println("================ Matriz Subtraida ================");
                    matriz.imprimeMatriz();
                    break;
                case "*":
                    if(splited[1].matches("[0-9]+")){
                        multiplicador = Float.parseFloat(splited[1]);
                        matriz.vezesK(multiplicador);
                        System.out.println("================ Matriz VezesK ================");
                        matriz.imprimeMatriz();
                    }
                    else{
                        arqMatriz = splited[1]+".txt";
                        matrizAux = TADMatriz.carrega(matFiles+"/"+arqMatriz);
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
        matriz.salva(matFiles+"/matrizFinal.txt");
        
    }
}

