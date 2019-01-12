/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy.ast;

import compilerjpy.Parser;
import compilerjpy.SymbolTab;
import java.io.BufferedWriter;

/**
 *
 * @author marco
 */
public class ASTNoIncFor extends ASTNo{
    private String idName;
    private String operator;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public ASTNoIncFor(int line) {
        super(line);
    }

   
   
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
       symboltab.get(getIdName());
       
       ASTNo loop = raize;
           
                while(loop != null){
                    
                    if(loop instanceof ASTNoDecl){
                       
                        if(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat){
                             
                            ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                            while(lst_variable != null){
                               
                                if(lst_variable.getIdName().getName() == null ? getIdName() == null : lst_variable.getIdName().getName().equals(getIdName())){
                                     
                                    throw new Exception("This variable "+getIdName()+" is type float, this operation only works with integer ");
                                    
                                }
                                
                                lst_variable = lst_variable.getLstVariables();
                             }

                        }

                    }
                  
                  loop = ((ASTNoComand)loop).getNext();

                }
            
            
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
