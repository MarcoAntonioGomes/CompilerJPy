/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

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
      
    }
    
}
