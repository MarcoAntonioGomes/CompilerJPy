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
public class ASTNoExpr extends ASTNoComand {
        private char operator;
        private String operatorLogicLogic;

    public String getOperatorLogicLogic() {
        return operatorLogicLogic;
    }

    public void setOperatorLogicLogic(String operatorLogicLogic) {
        this.operatorLogicLogic = operatorLogicLogic;
    }
	private ASTNo left;
	private ASTNo right;

    public char getOperator() {
        return operator;
    }

    public ASTNoExpr(int line) {
        super(line);
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public ASTNo getLeft() {
        return left;
    }

    public void setLeft(ASTNo left) {
        this.left = left;
    }

    public ASTNo getRight() {
        return right;
    }

    public void setRight(ASTNo right) {
        this.right = right;
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
  
    
}
