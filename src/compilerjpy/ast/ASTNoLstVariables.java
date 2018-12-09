/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

import compilerjpy.SymbolTab;

/**
 *
 * @author marco
 */
public class ASTNoLstVariables extends ASTNo{
    private ASTNoId idName;
    private int vetTamValue;
    private ASTNoExpr expr;
    private String vetIdName;
    private ASTNoLstVariables lstVariables;

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
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        if(getVetIdName() != null){
            symboltab.get(getVetIdName());
            
        }
        getIdName().validateSemantic(symboltab,raize);
      
        if(getExpr() != null){
            getExpr().validateSemantic(symboltab,raize);
            
        }
        
        
    }
    
}
