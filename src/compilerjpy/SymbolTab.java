/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author marco
 */
public class SymbolTab {
    private HashMap<String,Object> mapa = new HashMap();
    private List <memberSymbols> membersymbols = new ArrayList<memberSymbols>();
    
    public void insertMemberSymbols(memberSymbols symbols){
        getMembersymbols().add(symbols);
    }
    
    public memberSymbols getSymbols(String name){
    
        for (memberSymbols symbols: getMembersymbols()) {
                if(symbols.getName() == null ? name == null : symbols.getName().equals(name)){
                    return symbols;
                }
        
        }  
     return null;
    }

    public List<memberSymbols> getMembersymbols() {
        return membersymbols;
    }

    public void setMembersymbols(List<memberSymbols> membersymbols) {
        this.membersymbols = membersymbols;
    }
    
    public void set(String nome) throws Exception{
        if(!mapa.containsKey(nome)){
            mapa.put(nome, mapa.size());
        }
        
    }
    
    
    
    public boolean get(String nome) throws Exception{
        if(!mapa.containsKey(nome)){
            throw new Exception("Variable "+nome+" not declared!");
        }
        else{
        return true;
        }
    }
}
