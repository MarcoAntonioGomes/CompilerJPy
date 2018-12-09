package compilerjpy;
import java.io.*;

%%
%class Lexer
%public
%byaccj
%debug

%{
public int getYYline(){
    return yyline;
}

public void setYYline(int num){
    yyline = num;
}
%}

letter = [a-zA-Z_]
digit = [0-9]
white=[\r|\n|\r\n] | [ \t\f]
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Exponent = [eE] [\+\-]? {digit}+
Float1 = {digit}+ \. {digit}+ {Exponent}?
Float2 = \. {digit}+ {Exponent}?
Float3 = {digit}+ \. {Exponent}?
Float4 = {digit}+ {Exponent}
numberfloat = ( {Float1} | {Float2} | {Float3} | {Float4} )
%%


{Comment} { /* ignore */ }
{white} {/*Ignore*/}
"if" {return Parser.IF;}
"for" {return Parser.FOR;}
"int" {return Parser.INT;}
"float" {return Parser.FLOAT;}
"cout" {return Parser.COUT;}
"cin" {return Parser.CIN;} 
"else" { return  Parser.ELSE; }
"while" { return  Parser.WHILE; }
"char" { return  Parser.CHAR;}
"endl" { return  Parser.ENDL;}
"!=" { return Parser.NEQ; }
"==" { return Parser.EQU; }
"<<" { return Parser.OUT;}
">>" { return Parser.INP;}
"++" { return Parser.INC;}
"&&" { return Parser.AND;}
"||" { return Parser.OR;}
L?\"(\\.|[^\\\"])*\" {((Parser)this).yylval = yytext().substring(1, yytext().length() - 1); return Parser.STRING; }
{letter}({letter}|{digit})* {((Parser)this).yylval = yytext(); return Parser.ID;}
 {numberfloat} {((Parser)this).yylval = Float.parseFloat(yytext()); return Parser.NUM_FLOAT;}
 ("(-"{digit }+")")|{digit}+ { ((Parser)this).yylval = new Integer(yytext()); return Parser.NUM;}
. { return (int) yytext().charAt(0); } 


