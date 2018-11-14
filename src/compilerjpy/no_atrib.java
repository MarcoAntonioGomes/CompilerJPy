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
public class no_atrib extends No{
    private No next;
    private String name;
    private No expression;

    public no_atrib(int id, int line) {
        super(id, line);
    }

    public No getNext() {
        return next;
    }

    public void setNext(No next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public No getExpression() {
        return expression;
    }

    public void setExpression(No expression) {
        this.expression = expression;
    }
    
}
