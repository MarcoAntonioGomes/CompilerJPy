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
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        getCondition().validateSemantic(symboltab,raize);
        
        if((!((getCondition().getLeft() instanceof ASTNoConst)) && (!((getCondition().getRight() instanceof ASTNoConst))))&&(!((getCondition().getLeft() instanceof ASTNoFloat)) && (!((getCondition().getRight() instanceof ASTNoFloat))))){
            if((getCondition().getLeft() instanceof ASTNoExpr) &&(getCondition().getRight() instanceof ASTNoExpr)){

                if((((ASTNoExpr)getCondition().getLeft()).getTipoOp() == 1) && (((ASTNoExpr)getCondition().getRight()).getTipoOp() == 1)){
                    switch (((ASTNoExpr)getCondition().getLeft()).getOperator()){
                        case '+':
                        case '-':
                        case '/':
                        case '*':
                        case '%':
                        throw new Exception("Error! The expression generated a numerical value, it was expected a logical value!!");
                    }
                    if(getCondition().getOperatorLogicLogic() != null){
                        getCondition().setTipoOp(1);
                    }
                    if(getCondition().getOperator() == '<' ||getCondition().getOperator() == '>'){
                         getCondition().setTipoOp(1);

                    }
                }
                if((((ASTNoExpr)getCondition().getLeft()).getTipoOp() == 0) && (((ASTNoExpr)getCondition().getRight()).getTipoOp() == 0)){
                    getCondition().setTipoOp(1);

                }   
            }
        }
        if(getCondition().getTipoOp() == 0){
            throw new Exception("Error! The expression generated a numerical value, it was expected a logical value!!");
        }
        
        getIfComands().validateSemantic(symboltab,raize);
        if(getElseComands() != null){
            getElseComands().validateSemantic(symboltab,raize);
        }
         
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }
    
}
