/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic.aplicacao.matriz;

import ifes.bsi.tpa.dic.TADDicChain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TADMatriz {
    private int linhas;
    private int colunas;
    private TADDicChain dados;
    private List<ChaveMatriz> chaves;
    
    public TADMatriz(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.chaves = new ArrayList<>();
        dados = new TADDicChain();
    }
    
    public int quantLinhas(){
        return this.linhas;
    }
    
    public int quantColunas(){
        return this.colunas;
    }
    
    private ChaveMatriz findChaveMatriz(int linha, int coluna){
        for(int i=0; i < this.chaves.size();i++){
            if(this.chaves.get(i).getLinha() == linha && this.chaves.get(i).getColuna() == coluna)
                return this.chaves.get(i);
        }
        return null;
    }
    
    public Float setElem(int linha, int coluna, Float valor){
        if(valor == 0) return null;
        if(linha > this.linhas || coluna > this.colunas) return null;
        if(linha <= 0 || coluna <= 0) return null;
        ChaveMatriz chave = this.findChaveMatriz(linha, coluna);
        if(chave == null){
            chave = new ChaveMatriz(linha, coluna);
            this.chaves.add(chave);
            dados.insertItem(chave, valor);
            return valor;
        }
        dados.insertItem(chave, valor);
        return null;
    }
    
    public Float getElem(int linha, int coluna){
        if(linha > this.linhas || coluna > this.colunas) return null;
        if(linha <= 0 || coluna <= 0) return null;
        ChaveMatriz chave = this.findChaveMatriz(linha, coluna);
        if(chave != null)
            return (Float)dados.findElement(chave);
        return 0f;
    }
 
    public void imprimeMatriz(){
        for(int i=1; i <= this.linhas;i++){
            for(int j=1; j <= this.colunas;j++){
                System.out.print(this.getElem(i, j)+" | ");
            }
            System.out.println("");
        }
    }
    
    public void vezesK(float k){
        for(int i=0;i<this.chaves.size();i++){
            ChaveMatriz chave = this.chaves.get(i);
            Float valor = (Float)this.dados.findElement(chave);
            this.dados.insertItem(chave, valor*k);
        }
    }
    
    public TADMatriz soma(TADMatriz m){
        if(m.quantColunas() == this.colunas && m.quantLinhas() == this.linhas){
            TADMatriz result = new TADMatriz(this.linhas, this.colunas);
            for(int i=1; i <= this.linhas;i++){
                for(int j=1; j <= this.colunas;j++){
                    Float e = this.getElem(i, j) + m.getElem(i, j);
                    result.setElem(i, j, e);
                }
            }
            return result;
        }
        return null;
    }
    
    public TADMatriz subtracao(TADMatriz m){
        if(m.quantColunas() == this.colunas && m.quantLinhas() == this.linhas){
            TADMatriz result = new TADMatriz(this.linhas, this.colunas);
            for(int i=1; i <= this.linhas;i++){
                for(int j=1; j <= this.colunas;j++){
                    Float e = this.getElem(i, j) - m.getElem(i, j);
                    result.setElem(i, j, e);
                }
            }
            return result;
        }
        return null;
    }
    
    public TADMatriz multi(TADMatriz m){
        if(this.colunas != m.linhas) return null;
        TADMatriz result = new TADMatriz(this.linhas, m.colunas);
        for(int i=1; i<=this.linhas;i++){
            for(int j=1;j<=m.colunas;j++){
                for(int k=1;k<=this.colunas;k++){
                    Float e = result.getElem(i, j) + (this.getElem(i, k) * m.getElem(k, j));
                    result.setElem(i, j, e);
                }
            }
        }
        return result;
    }
    
    public TADMatriz transposta(){
        TADMatriz result = new TADMatriz(this.colunas, this.linhas);
        for(int i=1;i<=this.linhas;i++){
            for(int j=1;j<=this.colunas;j++){
                Float elem = this.getElem(i, j);
                result.setElem(j, i, elem);
            }
        }
        return result;
    }
    
    public static TADMatriz carrega(String nome_arq){
        
        BufferedReader br = null;
        FileReader fr = null;
        TADMatriz result;
        int colunas = 0;
        List<String> linhas = new ArrayList<>();
        try {
                fr = new FileReader(nome_arq);
                br = new BufferedReader(fr);
                String line = br.readLine();
                while( line != null){
                    linhas.add(line);
                    line = br.readLine();
                }
                String[] lineSplited = linhas.get(0).split("\\s+");
                for(int i=0;i<lineSplited.length;i++){
                    if(!lineSplited[i].equals(""))
                        colunas++;
                }
                
                if(colunas > 0 && linhas.size() > 0) result = new TADMatriz(linhas.size(), colunas);
                else return null;
                int k;
                for(int i=0;i<linhas.size();i++){
                    lineSplited = linhas.get(i).split("\\s+");
                    k=1;
                    for(int j=0; j<lineSplited.length;j++){
                        if(!lineSplited[j].equals("")){
                            Float v = Float.parseFloat(lineSplited[j]);
                            result.setElem(i+1, k, v);
                            k++;
                            
                        }
                    }
                }
                br.close();
                return result;
            } 
        catch (IOException e) {
             e.printStackTrace();
        }
        return null;
    }

    public String salva(String nome_arq){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(nome_arq);
            bw = new BufferedWriter(fw);
            String line;
            for(int i=1;i<=this.linhas;i++){
                line = "";
                for(int j=1;j<=this.colunas;j++){
                    line += this.getElem(i, j)+" ";
                }
                line += "\n";
                bw.write(line);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(TADMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        bw = new BufferedWriter(fw);
        return nome_arq;
    }
}
