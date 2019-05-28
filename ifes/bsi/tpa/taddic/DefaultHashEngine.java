/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic;

/**
 *
 * @author Matheus Barbosa
 */
public class DefaultHashEngine extends Hash_engine{
    
    public DefaultHashEngine(){
        super();
    }

    @Override
    public Long hash_func(Object key) {
        String newKey = key.toString();
        long hash = 0;
        for(int i=0; i<newKey.length();i++){
            hash += (int)newKey.charAt(i);
        }
        return hash;
    }
    
}
