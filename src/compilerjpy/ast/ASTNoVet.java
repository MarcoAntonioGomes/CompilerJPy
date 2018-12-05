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
public class ASTNoVet extends ASTNoExpr {
    private String name;
    private String vetIdName; 
    private ASTNoExpr expr;
    private int indexVet;

    public int getIndexVet() {
        return indexVet;
    }

    public void setIndexVet(int indexVet) {
        this.indexVet = indexVet;
    }
    
    public ASTNoExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetVetIdName() {
        return vetIdName;
    }

    public void setVetIdName(String setVetIdName) {
        this.vetIdName = setVetIdName;
    }
    public ASTNoVet(int line) {
        super(line);
    }
    
}
