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
public class ASTNoDecl extends ASTNoComand{
    private ASTNoType type;
    private ASTNoLstVariables variables;

    public ASTNoType getType() {
        return type;
    }

    public void setType(ASTNoType type) {
        this.type = type;
    }

    public ASTNoLstVariables getVariables() {
        return variables;
    }

    public void setVariables(ASTNoLstVariables variables) {
        this.variables = variables;
    }
    

    public ASTNoDecl() {
        super(0);
    }
    
    public ASTNoDecl(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
