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

  

    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
      
        getExpr().validateSemantic(symboltab,raize);
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }
    
}
