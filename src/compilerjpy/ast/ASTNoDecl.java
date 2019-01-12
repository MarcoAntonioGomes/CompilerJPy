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
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        getType().validateSemantic(symboltab,raize);
        getVariables().validateSemantic(symboltab,raize);
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
       
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        String line;
       
        if(!(p.getClassMainCreate())){
            line = ".class public code\n";
            archCode.append(line);
            line = ".super java/lang/Object\n";
            archCode.append(line);
            line = ".method public <init>()V\n";
            archCode.append(line);
            line = " aload_0\n";
            archCode.append(line);
            line = " invokenonvirtual java/lang/Object/<init>()V\n";
            archCode.append(line);
            line = " return\n";
            archCode.append(line);
            line = ".end method\n\n";
            archCode.append(line);
            line = ".method public static main([Ljava/lang/String;)V\n";
            archCode.append(line);
            line = " .limit stack 2\n";
            archCode.append(line);
            
            p.setClassMainCreate(true);
        }
        
        ASTNo loop = getVariables();
        
        while (loop != null){
        
        line = " .field";
        archCode.append(line);
        getVariables().generateCode(symboltab, raize, archCode, p);
        getType().generateCode(symboltab, raize, archCode, p);
        loop = ((ASTNoLstVariables)loop).getLstVariables();
        }
        if(getNext() == null){
            line = "return\n";
            archCode.append(line);
            line = ".end method\n";
            archCode.append(line);
        }
        else{
            getNext().generateCode(symboltab, raize, archCode, p);
        }
                
    }
    
}
