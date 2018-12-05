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
public class ASTNoFor extends ASTNoComand{
    private ASTNoAtrib atrib;
    private ASTNoExpr  condition;
    private ASTNoIncFor incPosDec;
    private ASTNoComand forComands;

    public ASTNoComand getForComands() {
        return forComands;
    }

    public void setForComands(ASTNoComand forComands) {
        this.forComands = forComands;
    }
    
    public ASTNoAtrib getAtrib() {
        return atrib;
    }

    public void setAtrib(ASTNoAtrib atrib) {
        this.atrib = atrib;
    }

   

    public ASTNoExpr getCondition() {
        return condition;
    }

    public void setCondition(ASTNoExpr condition) {
        this.condition = condition;
    }

    public ASTNoIncFor getIncPosDec() {
        return incPosDec;
    }

    public void setIncPosDec(ASTNoIncFor incPosDec) {
        this.incPosDec = incPosDec;
    }

   
    public ASTNoFor(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
