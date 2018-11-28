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
public class ASTNoLstCout extends ASTNo {
    private String string;
    private ASTNoExpr expr;
    private ASTNoLstCout lstCout;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ASTNoExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }

    public ASTNoLstCout getLstCout() {
        return lstCout;
    }

    public void setLstCout(ASTNoLstCout lstCout) {
        this.lstCout = lstCout;
    }
    
    public ASTNoLstCout(int line) {
        super(line);
    }
    
}
