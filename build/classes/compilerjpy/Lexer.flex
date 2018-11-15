package compilerjpy;
import static compilerjpy.Token.*;

%%
%class Lexer
%type Token
 
letter = [a-zA-Z_]
digit = [0-9]
string = \"{letter}+\"
int = {digit}+
variavel = {letter}+


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
"(" {return LEFT_PARAMETER;}
")" {return RIGHT_PARAMETER;}    
"[" {return LEFT_BRACKETS;}
"]" {return RIGHT_BRACKETS;}    
"else" { return  ELSE; }
"while" { return  WHILE; }
"char" { return  CHAR;}
";"  { return  SEMICOLON; }
">"  { return GREATER; }
"<"  { return LESS; }
"!=" { return NEQ; }
"==" { return EQU; }
"!" { return NOT; }
"=" {return ASSIGN;}
"+" {return SUM;}
"*" {return MULT;} 
"-" {return SUBT;}
"/" {return DIV;}

. { return yytext().getChar(0); }
{letter}({letter}|{digit})* {lexeme=yytext(); return ID;}
 ("(-"{digit }+")")|{digit }+ {lexeme=yytext(); return INT;}
. {return ERROR;}