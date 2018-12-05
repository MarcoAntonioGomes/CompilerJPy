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
public class ASTNoCout extends ASTNoComand{
    private ASTNoLstCout lstCout;

    public ASTNoLstCout getLstCout() {
        return lstCout;
    }

    public void setLstCout(ASTNoLstCout lstCout) {
        this.lstCout = lstCout;
    }

   
    public ASTNoCout(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
