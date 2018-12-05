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

    
    
    public ASTNoComand getNext() {
        return next;
    }

    public void setNext(ASTNoComand next) {
        this.next = next;
    }

   
    
    public ASTNoComand(int line) {
        super(line);
    }
    
    //public abstract void generateCode(PrintWriter out, SymbolTab symbolTab) throws Exception;
    
}
