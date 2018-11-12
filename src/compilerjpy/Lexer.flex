package compilerjpy;
import static compilerjpy.Token.*;
%%
%class Lexer
%type Token
 
letter = [a-zA-Z_]
digit = [0-9]
white=[ \t\r\n]

%{
public String lexeme;
%}
%%
{white} {/*Ignore*/}
"=" {return ASSIGN;}
"+" {return SUM;}
"*" {return MULT;}
"-" {return SUBT;}
"/" {return DIV;}
{letter}({letter}|{digit})* {lexeme=yytext(); return ID;}
 ("(-"{digit }+")")|{digit }+ {lexeme=yytext(); return INT;}
. {return ERROR;}