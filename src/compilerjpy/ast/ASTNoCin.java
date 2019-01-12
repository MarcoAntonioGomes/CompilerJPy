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
public class ASTNoCin extends ASTNoComand {
    private ASTNoExpr expr;

    public ASTNoExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }

  
    
    public ASTNoCin(int line) {
        super(line);
    }

  

    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
      
        getExpr().validateSemantic(symboltab,raize);
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
       String line = "new java/util/Scanner\n";
       archCode.append(line);
       line = "dup\n";
       archCode.append(line);
       line = "getstatic java/lang/System/in Ljava/io/InputStream;\n";
       archCode.append(line);
       line = "invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n";
       archCode.append(line);
       line = " astore ";
       archCode.append(line);
       getExpr().generateCode(symboltab, raize, archCode, p);
       line = "\n";
       archCode.append(line);
       
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
