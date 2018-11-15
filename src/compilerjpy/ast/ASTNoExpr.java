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
public class ASTNoExpr extends ASTNo {
        private char operator;
	private ASTNo left;
	private ASTNo right;

    public char getOperator() {
        return operator;
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
        
    public ASTNoExpr(int line, int column) {
        super(line, column);
    }
    
    
}
