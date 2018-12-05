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
public class ASTNoId extends ASTNoExpr{

    public ASTNoId(String name, int line) {
        super(line);
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public ASTNoId(int line) {
        super(line);
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ASTNoId(int line, int column) {
        super(line, column);
    }
    
}
