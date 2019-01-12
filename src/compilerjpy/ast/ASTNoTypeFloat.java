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
public class ASTNoTypeFloat extends ASTNoType {
    
    public ASTNoTypeFloat(int line) {
        super(line);
    }

  
    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
       symboltab.get("float");
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        String line;
        line = "F.\n";
        archCode.append(line);
    }
    
}
