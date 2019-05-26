/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic.aplicacao.matriz;

import ifes.bsi.tpa.dic.TADDicChain;
import java.util.ArrayList;
import java.util.List;

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
        if(linha > this.linhas || coluna > this.colunas) return null;
        if(linha <= 0 || coluna <= 0) return null;
        for(int i=0; i < this.chaves.size();i++){
            if(this.chaves.get(i).getLinha() == linha && this.chaves.get(i).getColuna() == coluna)
                return this.chaves.get(i);
        }
        return null;
    }
    
    public Float setElem(int linha, int coluna, Float valor){
        if(valor == 0) return null;
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
        ChaveMatriz chave = this.findChaveMatriz(linha, coluna);
        if(chave != null)
            return (Float)dados.findElement(chave);
        if(linha > this.linhas || coluna > this.colunas) return null;
        if(linha <= 0 || coluna <= 0) return null;
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
}
