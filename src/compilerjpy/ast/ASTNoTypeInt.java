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
public class ASTNoTypeInt extends ASTNoType{
    
    public ASTNoTypeInt(int line) {
        super(line);
    }

    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
       symboltab.get("int");
    }

    
    
}
