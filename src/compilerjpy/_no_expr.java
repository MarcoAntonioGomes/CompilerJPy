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
public class _no_expr extends No {
        private char operator;
	private No left;
	private No right;

    public _no_expr(int id, int line) {
        super(id, line);
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }



    public No getLeft() {
        return left;
    }

    public void setLeft(No left) {
        this.left = left;
    }

    public No getRight() {
        return right;
    }

    public void setRight(No right) {
        this.right = right;
    }
        
        
}
