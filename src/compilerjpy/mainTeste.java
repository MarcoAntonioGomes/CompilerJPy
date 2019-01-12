/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author marco
 */
public class mainTeste {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Parser p = new Parser("teste.txt");
	
        p.yyparse();
        p.getRaiz().validateSemantic(p.getSymbolTab(),p.getRaiz());
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("code.j",true));
        //p.getRaiz().generateCode(p.getSymbolTab(), p.getRaiz(), buffWrite, p); //Só Funciona para o soma.scc
        //buffWrite.close();
        //System.out.println(p.getRaiz());
    }
    
}
