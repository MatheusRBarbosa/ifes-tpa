/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic;

/**
 *
 * @author User
 */

class Pessoa{
    public int nome;
    public int idade;
    
    public Pessoa(int n, int i){
        this.nome = n;
        this.idade = i;
    }
}
class Chave{
    public int i;
    public int j;
    
    public Chave(){};
    public Chave(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class main {
    public static void main(String[] args) {
        Chave c = new Chave();
        TADDicChain dic = new TADDicChain();
        
        dic.insertItem(null, new Pessoa(1,123123));
        dic.insertItem(2, new Pessoa(2,654654));
        dic.insertItem(3, new Pessoa(3,989898));
        dic.insertItem(4, new Pessoa(4,4));
        dic.insertItem(5, new Pessoa(5,3333333));
        
        c.i = 1;c.j=1;
        dic.insertItem(c, 123);
        
        
        
        System.out.println(dic.findElement(c));
        //System.out.println(((Pessoa)(dic.elements().get(0))).idade);        
    }
}
