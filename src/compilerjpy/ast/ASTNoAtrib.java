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
public class ASTNoAtrib extends ASTNo{
    private ASTNo next;
    private String name;
    private ASTNo expression;

    public ASTNo getNext() {
        return next;
    }

    public void setNext(ASTNo next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ASTNo getExpression() {
        return expression;
    }

    public void setExpression(ASTNo expression) {
        this.expression = expression;
    }
    
    public ASTNoAtrib(int line) {
        super(line);
    }
    
}
