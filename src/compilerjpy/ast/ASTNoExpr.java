/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

import compilerjpy.SymbolTab;


/**
 *
 * @author marco
 */
public class ASTNoExpr extends ASTNoComand {
        private char operator;
        private String operatorLogicLogic;
        private ASTNo left;
	private ASTNo right;

    public String getOperatorLogicLogic() {
        return operatorLogicLogic;
    }

    public void setOperatorLogicLogic(String operatorLogicLogic) {
        this.operatorLogicLogic = operatorLogicLogic;
    }
	

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
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
      
        if((getLeft()!= null)&& (getRight() != null)){
           getLeft().validateSemantic(symboltab,raize);
           getRight().validateSemantic(symboltab,raize);
        }
        
       
        
    }

   
        
  
    
}
