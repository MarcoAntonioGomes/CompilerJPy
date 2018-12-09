/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

/**
 *
 * @author marco
 */
public class memberSymbols {
    private String name;
    private int vetLength;

    public memberSymbols(String name, int vetLength) {
        this.name = name;
        this.vetLength = vetLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    public int getVetLength() {
        return vetLength;
    }

    public void setVetLength(int vetLength) {
        this.vetLength = vetLength;
    }
    
}
