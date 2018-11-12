/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author marco
 */
public class Scanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    Reader reader  = new BufferedReader(new FileReader("teste.txt"));
    Lexer lexer = new Lexer(reader);
    String result = "";
    while(true){
        Token token = lexer.yylex();
        if(token== null){
            result= result +"EOF";
            System.out.println(result);
            return;
        }
        switch(token){
            case ERROR:
                result = result + "Error, simbolo não reconhecido \n";
                break;
            case ID: case INT:
                 result = result +"TOKEN: " + token + lexer.lexeme + "\n";
            break;
            default:
                result = result +"TOKEN: " + token + "\n";
        }
        
    }
        
        
    }
    
}
