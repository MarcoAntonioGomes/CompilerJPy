package compilerjpy;
import static compilerjpy.Token.*;
import java.io.*;

%%
%class Lexer
%type Token
%public
%byaccj

%{

%}
 
letter = [a-zA-Z_]
digit = [0-9]



white=[ \t\r\n]

%{
public String lexeme;
%}
%%
{white} {/*Ignore*/}
"if" {return IF;}
"for" {return FOR;}
"int" {return INT;}
"float" {return FLOAT;}
"cout" {return COUT;}
"cin" {return CIN;} 
"else" { return  ELSE; }
"while" { return  WHILE; }
"char" { return  CHAR;}
"!=" { return NEQ; }
"==" { return EQU; }
"<<" { return OUT;}
">>" { return INP;}
\"~["\n]*\" {((Parser)this).yylval = yytext().substring(1, yytext().length() - 1); return Parser.STRING; }

. { return (int) yytext().charAt(0); } 
{letter}({letter}|{digit})* {((Parser)this).yylval = yytext(); return Parser.ID;}
 ("(-"{digit }+")")|{digit }+ { ((Parser)this).yylval = new Integer(yytext()); return Parser.NUM; }

. {return ERROR;}