/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

/**
 *
 * @author marco
 */
public class ASTNoVet extends ASTNo{
    private ASTNoId id;
    private int vetTamValue;

    public ASTNoId getId() {
        return id;
    }

    public void setId(ASTNoId id) {
        this.id = id;
    }

    public int getVetTamValue() {
        return vetTamValue;
    }

    public void setVetTamValue(int vetTamValue) {
        this.vetTamValue = vetTamValue;
    }
    
    public ASTNoVet(int line) {
        super(line);
    }
    
}
