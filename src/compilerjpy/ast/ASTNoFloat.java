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
public class ASTNoFloat extends ASTNoExpr{
       float value;
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public ASTNoFloat(int line) {
        super(line);
    }
    
}
