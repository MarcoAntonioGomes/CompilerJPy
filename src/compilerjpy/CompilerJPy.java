/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

import java.io.File;
import jflex.Main;
/**
 *
 * @author marco
 */
public class CompilerJPy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String path =  "C:/Users/marco/Documents/NetBeansProjects/CompilerJPy/src/compilerjpy/Lexer.flex";
       generateLexer(path);
    }
    
    public static void generateLexer(String path){
        File file=new File(path);
        Main.generate(file);
    }
}
