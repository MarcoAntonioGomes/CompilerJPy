/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

import java.util.HashMap;

/**
 *
 * @author marco
 */
public class SymbolTab {
    private HashMap<String,Object> mapa;
    
    public void set(String nome){
        if(!mapa.containsKey(nome)){
            mapa.put(nome, mapa.size());
        }
    }
    
    public void get(String nome) throws Exception{
        if(!mapa.containsKey(nome)){
            throw new Exception("Variable "+nome+"not declared!");
        }
    }
}
