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
public  class ASTNoExpr extends ASTNoComand {
        private char operator;
        private String operatorLogicLogic;
        private ASTNo left;
	private ASTNo right;
        private int   tipoOp; // 0 = A operação me devolveu um numero - 1 A operação me devolveu um valor logico

    public int getTipoOp() {
        return tipoOp;
    }

    public void setTipoOp(int tipoOp) {
        this.tipoOp = tipoOp;
    }

    public String getOperatorLogicLogic() {
        return operatorLogicLogic;
    }

    public void setOperatorLogicLogic(String operatorLogicLogic) {
        this.operatorLogicLogic = operatorLogicLogic;
    }
	

    public char getOperator() {
        return operator;
    }

    public ASTNoExpr(int line) {
        super(line);
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public ASTNo getLeft() {
        return left;
    }

    public void setLeft(ASTNo left) {
        this.left = left;
    }

    public ASTNo getRight() {
        return right;
    }

    public void setRight(ASTNo right) {
        this.right = right;
    }

        @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
       
        
       
        if((getLeft()!= null)&& (getRight() != null)){
           getLeft().validateSemantic(symboltab,raize);
           getRight().validateSemantic(symboltab,raize);
                if((((ASTNoExpr)getLeft()).getTipoOp() == 0) && ((getRight() instanceof ASTNoConst) || (getRight() instanceof ASTNoFloat))){
                     switch (getOperator()){
                        case '+':
                        case '-':
                        case '/':
                        case '*':
                        case '%':    
                        setTipoOp(0);
                       }
                     if(getOperatorLogicLogic() != null){
                        setTipoOp(1);
                      }
                }
                if((((ASTNoExpr)getRight()).getTipoOp() == 0) && ((getLeft() instanceof ASTNoConst) || (getLeft() instanceof ASTNoFloat))){
                     switch (getOperator()){
                        case '+':
                        case '-':
                        case '/':
                        case '*':
                        case '%':    
                        setTipoOp(0);
                       }
                     if(getOperatorLogicLogic() != null){
                        setTipoOp(1);
                     }
                     if(getOperator() == '<' ||getOperator() == '>'){
                           setTipoOp(1);
                                            
                     }
                }
               
                
                if((getLeft() instanceof ASTNoVet) && (getRight() instanceof ASTNoVet)){
                   
                    if(((ASTNoVet)getLeft()).getName() == null ? ((ASTNoVet)getRight()).getName() == null : ((ASTNoVet)getLeft()).getName().equals(((ASTNoVet)getRight()).getName())){
                          ASTNo loop = raize;
                          
                        while(loop != null){
                            
                            if(loop instanceof ASTNoDecl){

                                if(((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt){

                                    ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                                    while(lst_variable != null){

                                        if(lst_variable.getIdName().getName() == null ? ((ASTNoVet)getLeft()).getName() == null : lst_variable.getIdName().getName().equals(((ASTNoVet)getLeft()).getName())){
                                           
                                            switch (getOperator()){
                                                case '+':
                                                case '-':
                                                case '/':
                                                case '*':
                                                case '%':
                                                    
                                                setTipoOp(0);
                                            }
                                            if(getOperatorLogicLogic() != null){
                                                setTipoOp(1);
                                            }
                                            if(getOperator() == '<' ||getOperator() == '>'){
                                                setTipoOp(1);
                                            
                                            }

                                        }

                                        lst_variable = lst_variable.getLstVariables();
                                     }

                                }

                            }
                  
                           loop = ((ASTNoComand)loop).getNext();

                        } 
                
                
                }
                
                
                
            }
            
             if((getLeft() instanceof ASTNoId) && ((getRight() instanceof ASTNoConst)|| (getRight() instanceof ASTNoFloat))){
               ASTNo loop = raize;
                          
                        while(loop != null){
                            
                            if(loop instanceof ASTNoDecl){

                                if((((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt)||(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat)){

                                    ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                                    while(lst_variable != null){

                                        if(lst_variable.getIdName().getName() == null ? ((ASTNoId)getLeft()).getName() == null : lst_variable.getIdName().getName().equals(((ASTNoId)getLeft()).getName())){
                                           
                                            switch (getOperator()){
                                                case '+':
                                                case '-':
                                                case '/':
                                                case '*':
                                                case '%':
                                                    
                                                setTipoOp(0);
                                            }
                                            if(getOperatorLogicLogic() != null){
                                                setTipoOp(1);
                                            }
                                            if(getOperator() == '<' ||getOperator() == '>'){
                                                setTipoOp(1);
                                            
                                            }

                                        }

                                        lst_variable = lst_variable.getLstVariables();
                                     }

                                }

                            }
                  
                           loop = ((ASTNoComand)loop).getNext();

                        } 
             
             }    
             if((getRight() instanceof ASTNoId) && ((getLeft() instanceof ASTNoConst)|| (getLeft() instanceof ASTNoFloat))){
              ASTNo loop = raize;
                          
                        while(loop != null){
                            
                            if(loop instanceof ASTNoDecl){

                                if((((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt)||(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat)){

                                    ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                                    while(lst_variable != null){

                                        if(lst_variable.getIdName().getName() == null ? ((ASTNoId)getRight()).getName() == null : lst_variable.getIdName().getName().equals(((ASTNoId)getRight()).getName())){
                                           
                                            switch (getOperator()){
                                                case '+':
                                                case '-':
                                                case '/':
                                                case '*':
                                                case '%':
                                                    
                                                setTipoOp(0);
                                            }
                                            if(getOperatorLogicLogic() != null){
                                                setTipoOp(1);
                                            }
                                            if(getOperator() == '<' ||getOperator() == '>'){
                                                setTipoOp(1);
                                            
                                            }

                                        }

                                        lst_variable = lst_variable.getLstVariables();
                                     }

                                }

                            }
                  
                           loop = ((ASTNoComand)loop).getNext();

                        } 
                        
             }
             if((getRight() instanceof ASTNoId) && ((getLeft() instanceof ASTNoId))){
                ASTNo loop = raize;
                boolean typeIDRight = false;         
                        while(loop != null){
                            
                            if(loop instanceof ASTNoDecl){

                                if((((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt)||(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat)){

                                    ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                                    while(lst_variable != null){

                                        if(lst_variable.getIdName().getName() == null ? ((ASTNoId)getRight()).getName() == null : lst_variable.getIdName().getName().equals(((ASTNoId)getRight()).getName())){
                                           
                                            typeIDRight = true;

                                        }

                                        lst_variable = lst_variable.getLstVariables();
                                     }

                                }

                            }
                  
                           loop = ((ASTNoComand)loop).getNext();

                        } 
                        
                        loop = raize;
                          
                        while(loop != null){
                            
                            if(loop instanceof ASTNoDecl){

                                if((((ASTNoDecl) loop).getType() instanceof ASTNoTypeInt)||(((ASTNoDecl) loop).getType() instanceof ASTNoTypeFloat)){

                                    ASTNoLstVariables lst_variable = ((ASTNoDecl) loop).getVariables();
                                    while(lst_variable != null){

                                        if((lst_variable.getIdName().getName() == null ? ((ASTNoId)getLeft()).getName() == null : lst_variable.getIdName().getName().equals(((ASTNoId)getLeft()).getName())) && typeIDRight == true){
                                           
                                            switch (getOperator()){
                                                case '+':
                                                case '-':
                                                case '/':
                                                case '*':
                                                case '%':
                                                    
                                                setTipoOp(0);
                                            }
                                            if(getOperatorLogicLogic() != null){
                                                setTipoOp(1);
                                            }
                                            if(getOperator() == '<' ||getOperator() == '>'){
                                                setTipoOp(1);
                                            
                                            }

                                        }

                                        lst_variable = lst_variable.getLstVariables();
                                     }

                                }

                            }
                  
                           loop = ((ASTNoComand)loop).getNext();

                        } 
             
             }  
           if(((getLeft() instanceof ASTNoConst) || (getLeft() instanceof ASTNoFloat)) &&((getRight() instanceof ASTNoConst) || (getRight() instanceof ASTNoFloat))){
                
               switch (getOperator()){
                    case '+':
                    case '-':
                    case '/':
                    case '*':
                    case '%':    
                    setTipoOp(0);
                }
               if(getOperatorLogicLogic() != null){
                     setTipoOp(1);
                }
               if(getOperator() == '<' ||getOperator() == '>'){
                   setTipoOp(1);

               }
            
             }
                
        }
        
       
        
    }

    
    
     @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception{}

   
        
  
    
}
