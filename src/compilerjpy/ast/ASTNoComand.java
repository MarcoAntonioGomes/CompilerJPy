/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

import compilerjpy.SymbolTab;
import java.io.PrintWriter;

/**
 *
 * @author marco
 */
public abstract class ASTNoComand extends ASTNo{
    private ASTNoComand next;
    private ASTNoComand previous;

    public ASTNoComand getPrevious() {
        return previous;
    }

    public void setPrevious(ASTNoComand previous) {
        this.previous = previous;
    }
    
    public ASTNoComand getNext() {
        return next;
    }

    public void setNext(ASTNoComand next) {
        this.next = next;
    }

   
    
    public ASTNoComand(int line) {
        super(line);
    }
    
    @Override
    public void  validateSemantic(SymbolTab symboltab, ASTNo raize) throws Exception{
     
        getNext().validateSemantic(symboltab, raize);
    }

    
    
}
