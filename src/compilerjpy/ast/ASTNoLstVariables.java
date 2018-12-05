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
public class ASTNoLstVariables extends ASTNo{
    private ASTNoId idName;
    private int vetTamValue;
    private ASTNoExpr expr;
    private String vetIdName;

    public ASTNoId getIdName() {
        return idName;
    }

    public void setIdName(ASTNoId idName) {
        this.idName = idName;
    }

    public String getVetIdName() {
        return vetIdName;
    }

    public void setVetIdName(String vetIdName) {
        this.vetIdName = vetIdName;
    }
    
   

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }

    public ASTNoExpr getExpr() {
        return expr;
    }
    private ASTNoLstVariables lstVariables;

   

    public int getVetTamValue() {
        return vetTamValue;
    }

    public void setVetTamValue(int vetTamValue) {
        this.vetTamValue = vetTamValue;
    }

    public ASTNoLstVariables getLstVariables() {
        return lstVariables;
    }

    public void setLstVariables(ASTNoLstVariables lstVariables) {
        this.lstVariables = lstVariables;
    }
    
    
    public ASTNoLstVariables(int line) {
        super(line);
    }

    @Override
    public void validateSemantic() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
