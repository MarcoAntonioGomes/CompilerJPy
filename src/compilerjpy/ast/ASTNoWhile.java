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
public class ASTNoWhile extends ASTNoComand{
    private ASTNoExpr condition;
    private ASTNoComand whileComands;

    public ASTNoExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTNoExpr condition) {
        this.condition = condition;
    }

    public ASTNoComand getWhileComands() {
        return whileComands;
    }

    public void setWhileComands(ASTNoComand whileComands) {
        this.whileComands = whileComands;
    }
    
    public ASTNoWhile(int line) {
        super(line);
    }
    
}
