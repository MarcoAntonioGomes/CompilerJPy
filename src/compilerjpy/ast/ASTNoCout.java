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
public class ASTNoCout extends ASTNoComand{
    private ASTNoLstCout lstCout;

    public ASTNoLstCout getLstCout() {
        return lstCout;
    }

    public void setLstCout(ASTNoLstCout lstCout) {
        this.lstCout = lstCout;
    }

   
    public ASTNoCout(int line) {
        super(line);
    }


    @Override
    public void validateSemantic(SymbolTab symboltab,ASTNo raize) throws Exception {
        getLstCout().validateSemantic(symboltab,raize);
        if(getNext() != null){
            getNext().validateSemantic(symboltab, raize);
        }
    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        ASTNo loop = getLstCout();
        String line;
        while (loop != null){
          loop.generateCode(symboltab, raize, archCode, p);
          loop = ((ASTNoLstCout)loop).getLstCout();
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
