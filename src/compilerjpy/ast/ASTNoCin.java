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
public class ASTNoCin extends ASTNoComand {
    private ASTNoExpr expr;

    public ASTNoExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }

  
    
    public ASTNoCin(int line) {
        super(line);
    }
    
}