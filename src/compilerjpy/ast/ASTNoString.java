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
public class ASTNoString extends ASTNoExpr{
    private String valueString;

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
    
    public ASTNoString(int line) {
        super(line);
    }
    
}
