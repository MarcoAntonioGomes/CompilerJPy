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
public class ASTNoPrintExpr extends ASTNo {
    private ASTNo next;
    private ASTNo expression;

    public ASTNo getNext() {
        return next;
    }

    public void setNext(ASTNo next) {
        this.next = next;
    }

    public ASTNo getExpression() {
        return expression;
    }

    public void setExpression(ASTNo expression) {
        this.expression = expression;
    }
    
    public ASTNoPrintExpr(int line) {
        super(line);
    }

  

    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        if(getNext() != null){
            getNext().validateSemantic(symboltab,raize);
        }
        if(getExpression() != null){
            getExpression().validateSemantic(symboltab,raize);
        }
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
