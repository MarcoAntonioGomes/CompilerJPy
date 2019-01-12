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
public class ASTNoLstCout extends ASTNo {

    private String string;
    private ASTNoExpr expr;
    private ASTNoLstCout lstCout;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ASTNoExpr getExpr() {
        return expr;
    }

    public void setExpr(ASTNoExpr expr) {
        this.expr = expr;
    }

    public ASTNoLstCout getLstCout() {
        return lstCout;
    }

    public void setLstCout(ASTNoLstCout lstCout) {
        this.lstCout = lstCout;
    }

    public ASTNoLstCout(int line) {
        super(line);
    }

    @Override
    public void validateSemantic(SymbolTab symboltab, ASTNo raize) throws Exception {
        if (getExpr() != null) {
            getExpr().validateSemantic(symboltab, raize);
        }
        if (getLstCout() != null) {
            getLstCout().validateSemantic(symboltab, raize);
        }

    }

    @Override
    public void generateCode(SymbolTab symboltab, ASTNo raize, BufferedWriter archCode, Parser p) throws Exception {
        String line = "getstatic java/lang/System/out Ljava/io/PrintStream;\n";
        archCode.append(line);
        line = "ldc " + getString();
        archCode.append(line);
        line = "invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n";
        archCode.append(line);
        if (getExpr() != null) {
            ASTNoExpr loop = (ASTNoExpr) getExpr().getLeft();
            ASTNoExpr dadNo = loop;
            while (loop != null) {
                if ((ASTNoExpr) loop.getLeft() == null) {
                    if (loop instanceof ASTNoId) {
                        if (dadNo.getOperator() == '+' || dadNo.getOperator() == '-') {
                            line = "iinc ";
                            archCode.append(line);
                            loop.generateCode(symboltab, raize, archCode, p);
                            ASTNoExpr loop2 = (ASTNoExpr) getExpr().getRight();
                            ASTNoExpr dadNo2 = loop2;
                            while (loop2 != null) {
                                if ((ASTNoExpr) loop2.getRight() == null) {
                                    if (loop2 instanceof ASTNoId) {
                                        loop2.generateCode(symboltab, raize, archCode, p);
                                        archCode.append("\n");
                                    }
                                    if (loop2 instanceof ASTNoConst) {
                                        line = ""+((ASTNoConst) loop2).getValue();
                                        archCode.append(line);
                                         archCode.append("\n");
                                    }
                                    
                                }
                                dadNo = loop2;
                                loop2 = (ASTNoExpr) loop2.getRight();
                            }
                        }

                    }

                }
                dadNo = loop;
                loop = (ASTNoExpr) loop.getLeft();
            }

        }
        if(getExpr() != null){
            getExpr().generateCode(symboltab, raize, archCode, p);
        }
    }

}
