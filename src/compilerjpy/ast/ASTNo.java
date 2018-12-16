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
public abstract class  ASTNo {
     private int line;
     private int column;

    public ASTNo(int line) {
        this.line = line;
    }

     public abstract void  validateSemantic(SymbolTab symboltab, ASTNo raize)throws Exception;
     
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public ASTNo(int line, int column) {
        this.line = line;
        this.column = column;
    }
     
     
}
