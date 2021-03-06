/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

import compilerjpy.Parser;
import compilerjpy.SymbolTab;
import java.io.BufferedWriter;

/**
 *
 * @author marco
 */
public class ASTNoFor extends ASTNoComand{
    private ASTNoAtrib atrib;
    private ASTNoExpr  condition;
    private ASTNoIncFor incPosDec;
    private ASTNoComand forComands;

    public ASTNoComand getForComands() {
        return forComands;
    }

    public void setForComands(ASTNoComand forComands) {
        this.forComands = forComands;
    }
    
    public ASTNoAtrib getAtrib() {
        return atrib;
    }

    public void setAtrib(ASTNoAtrib atrib) {
        this.atrib = atrib;
    }

   

    public ASTNoExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTNoExpr condition) {
        this.condition = condition;
    }

    public ASTNoIncFor getIncPosDec() {
        return incPosDec;
    }

    public void setIncPosDec(ASTNoIncFor incPosDec) {
        this.incPosDec = incPosDec;
    }

   
    public ASTNoFor(int line) {
        super(line);
    }

  
    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        getAtrib().validateSemantic(symboltab,raize);
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
        
        
        
        getIncPosDec().validateSemantic(symboltab,raize);
        getForComands().validateSemantic(symboltab,raize);
       // System.out.println( getForComands());
         //System.out.println( getForComands());
         //System.out.println(getForComands().getNext().getPrevious());
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
