/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

/**
 *
 * @author marco
 */
public class _no_print_expr extends No{
    private No next;
    private No expression;

    public _no_print_expr(int id, int line) {
        super(id, line);
    }

    public No getNext() {
        return next;
    }

    public void setNext(No next) {
        this.next = next;
    }

    public No getExpression() {
        return expression;
    }

    public void setExpression(No expression) {
        this.expression = expression;
    }
    
    
}
