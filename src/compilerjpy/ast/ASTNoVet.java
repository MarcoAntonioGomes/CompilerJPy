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
public class ASTNoVet extends ASTNoExpr {
    private String name;
    private String vetIdName; 
    private ASTNoExpr expr;
    private int indexVet;
    private String operatorInc;

    public String getOperatorInc() {
        return operatorInc;
    }

    public void setOperatorInc(String operatorInc) {
        this.operatorInc = operatorInc;
    }
    
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

    public String getVetIdName() {
        return vetIdName;
    }

    public void setVetIdName(String setVetIdName) {
        this.vetIdName = setVetIdName;
    }
    public ASTNoVet(int line) {
        super(line);
    }
    
    @Override
    public  void  validateSemantic(SymbolTab symboltab,ASTNo raize)throws Exception{
        symboltab.get(getName());
         
        if(getVetIdName() != null){
           symboltab.get(getVetIdName());
        }
        else{
            ASTNo loop = raize;
            
            if((getExpr() instanceof ASTNoFloat)||(getExpr().getLeft()instanceof ASTNoFloat )||(getExpr().getRight()instanceof ASTNoFloat )){
                 
                while(loop != null){
                    
                    if(loop instanceof ASTNoDecl){
                       
                        if(((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt){
                             
                            ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                            while(lst_variable != null){
                               
                                if(lst_variable.getIdName().getName() == null ? getName() == null : lst_variable.getIdName().getName().equals(getName())){
                                     
                                    throw new Exception("This vector "+getName()+" is type int don't suport conversion for float ");
                                    
                                }
                                
                                lst_variable = lst_variable.getLstVariables();
                             }

                        }

                    }
                  
                  loop = ((ASTNoComand)loop).getNext();

                }
            
            }
            
            if((getExpr() != null)&&(getIndexVet() >= symboltab.getSymbols(getName()).getVetLength())){
                throw new Exception("This index vector "+getName()+" is off limits ");
            }            
        }
         
        
        if(!((getExpr() instanceof ASTNoConst)||(getExpr() == null))){
         
            
            getExpr().validateSemantic(symboltab,raize);
            
        }
        ASTNo loop = raize;
         while(loop != null){
                    
                    if(loop instanceof ASTNoDecl){
                       
                        if(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat){
                             
                            ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                            while(lst_variable != null){
                               
                                if(lst_variable.getIdName().getName() == null ? getName() == null : lst_variable.getIdName().getName().equals(getName())){
                                     
                                    throw new Exception("This vector "+getName()+" is type float, this operation only works with integer. ");
                                    
                                }
                                
                                lst_variable = lst_variable.getLstVariables();
                             }

                        }

                    }
                  
                  loop = ((ASTNoComand)loop).getNext();

            }
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }
}
