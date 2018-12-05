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
public class ASTNoIf extends ASTNoComand {
        private ASTNoExpr condition;
	private ASTNoComand ifComands;
	private ASTNoComand elseComands;

    public ASTNoIf(ASTNoExpr condition, ASTNoComand ifComands, int line) {
        super(line);
        this.condition = condition;
        this.ifComands = ifComands;
    }

    public ASTNoIf(ASTNoExpr condition, ASTNoComand ifComands, ASTNoComand elseComands, int line) {
        super(line);
        this.condition = condition;
        this.ifComands = ifComands;
        this.elseComands = elseComands;
    }

    public ASTNoExpr getCondition() {
        return condition;
    }

    public ASTNoComand getIfComands() {
        return ifComands;
    }

    public ASTNoComand getElseComands() {
        return elseComands;
    }
        
    public ASTNoIf(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
