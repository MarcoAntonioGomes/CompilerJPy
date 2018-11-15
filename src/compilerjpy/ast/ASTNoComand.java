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
public class ASTNoComand extends ASTNo{
    private ASTNo next;

    public ASTNo getNext() {
        return next;
    }

    public void setNext(ASTNo next) {
        this.next = next;
    }
    
    public ASTNoComand(int line) {
        super(line);
    }
    
}
