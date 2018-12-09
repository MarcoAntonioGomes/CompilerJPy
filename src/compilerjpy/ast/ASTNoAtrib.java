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
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
      
        if(symboltab.get(getName())){
            ASTNo loop = raize;
            while(loop != null){
                if(loop instanceof  ASTNoDecl){
                    ASTNoLstVariables lst_variables = ((ASTNoDecl)loop).getVariables();
                    while(lst_variables != null){
                        
                        if(lst_variables.getIdName().getName() == null ? getName() == null : lst_variables.getIdName().getName().equals(getName())){
                            if((((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt)){
                                if(getExpression() instanceof ASTNoFloat){
                                    throw new Exception("Error, variable "+getName()+" is the type int, not convert float to int");
                                }
                                if(getExpression() instanceof ASTNoString){
                                    throw new Exception("Error, variable "+getName()+" is the type int, not convert String to int");
                                }
                                if(lst_variables.getExpr() != null){
                                    switch (lst_variables.getExpr().getOperator()){
                                        case '<':
                                        case '>':
                                        throw new Exception("Error, variable "+getName()+" is the type int, and operation is type logic");
                                    }
                                    switch (lst_variables.getExpr().getOperatorLogicLogic()){
                                        case "!=":
                                        case "==":
                                        case "&&":
                                        case "||":
                                        throw new Exception("Error, variable "+getName()+" is the type int, and operation is type logic");
                                    }

                                    if((lst_variables.getExpr().getLeft() instanceof ASTNoFloat) || (lst_variables.getExpr().getRight() instanceof ASTNoFloat) || (lst_variables.getExpr() instanceof ASTNoFloat)){
                                        throw new Exception("Error, variable "+getName()+" is the type int, not convert float to int");
                                    }
                                }
                            }   
                            if(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat){
                               if(lst_variables.getExpr() != null){
                                    switch (lst_variables.getExpr().getOperator()){
                                        case '<':
                                        case '>':
                                        throw new Exception("Error, variable "+getName()+" is the type float, and operation is type logic");

                                    }
                                    switch (lst_variables.getExpr().getOperatorLogicLogic()){
                                        case "!=":
                                        case "==":
                                        case "&&":
                                        case "||":
                                        throw new Exception("Error, variable "+getName()+" is the type float, and operation is type logic");
                                    }
                               }
                                
                            }
                        }
                        
                        
                       lst_variables = lst_variables.getLstVariables();  
                        
                    }
                }
            loop = ((ASTNoComand)loop).getNext();
            
            }
        
        
        }
        
        
       getExpression().validateSemantic(symboltab,raize);
       if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }
    
}
