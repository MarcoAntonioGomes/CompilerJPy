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
public class ASTNoAtrib extends ASTNoComand{
   
    private String name;
    private ASTNoExpr expression;

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ASTNo getExpression() {
        return expression;
    }

    public void setExpression(ASTNoExpr expression) {
        this.expression = expression;
    }
    
    public ASTNoAtrib(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
