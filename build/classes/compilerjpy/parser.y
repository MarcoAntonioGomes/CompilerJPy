%{
package compilerjpy;
import compilerjpy.ast.*;


public void yyerror(String msg);   // Funçao de erro

No raiz;
No_Numbers nosIds;

%}




%token   ID INT SUM SUBT DIV MULT ASSIGN ERROR CIN COUT IF FOR FLOAT LEFT_PARAMETER RIGHT_PARAMETER LEFT_BRACKETS RIGHT_BRACKETS ELSE WHILE CHAR SEMICOLON GREATER LESS NEQ EQU NOT OUT INP STRING
%nonassoc PRE_MAIS PRE_MENOS

%left '<' '>'
%left '*' '/' '%'
%left '+' '-'

%%
lst_comandos :
	lst_comandos comando ';' {
                ((ASTNoComand)$1).setProximo($2); $$ = $2;
		$$ = $2;
	}
|	comando ';' {
		if(raiz == null) raiz = $1; 
                $$ = $1; 
}
;
comando :
	expr {
                $$ = new ASTNoPrintExpr(yylineno+1);
		((ASTNoPrintExpr)$$).setExpression($1);
                
	}
|	ID '=' expr {
                $$ = new ASTNoAtrib(yylineno+1);
                ((ASTNoAtrib)$$).setName($1);
                ((ASTNoAtrib)$$).setExpression($3);
		
	}

|      decl {

       
       }
      
         
|      COUT OUT STRING  {
        $$ = new ASTNoCoutWithoutExpr(yylineno+1);
             ((ASTNoCoutWithoutExpr)$$).setString($3);
        }

|      COUT OUT STRING OUT expr{
        $$ = new ASTNoCoutExpr(yylineno+1);
             ((ASTNoCoutExpr)$$).setString($3);
             ((ASTNoCoutExpr)$$).setexpr($4);
        }

|      CIN INP expr{
         $$ = new ASTNoCin(yylineno+1);
            ((ASTNoCin)$$).setExpr($3);
       }
|      IF '(' expr ')' '{' lst_comando '}' { $$ = new ASTNoIf($3,$6,yylineno+1); }
|      IF '(' expr ')' '{' lst_comando '}' ELSE '{' lst_comando '}'{ $$ = new ASTNoIf($3,$6,$10,yylineno+1); }
|      WHILE '(' expr ')' '{' lst_comando '}' { $$ = new ASTNoWhile(yylineno+1); 
                ((ASTNoWhile)$$).setCondition($3);
                ((ASTNoWhile)$$).setWhileComands($6);
               
              }
|      FOR '(' assign ';'  expr ';' assign ')''{' lst_comando '}'{
            $$ = new ASTNoFor(yylineno+1);
                ((ASTNoFor)$$).setAtrib($3);
                ((ASTNoFor)$$).setCondition($5);
                ((ASTNoFor)$$).setIncrement($7);
                ((ASTNoFor)$$).setForComands($10);
        }


;

assign:
    ID '=' expr {
                $$ = new ASTNoAtrib(yylineno+1);
                ((ASTNoAtrib)$$).setName($1);
                ((ASTNoAtrib)$$).setExpression($3);
		
	}
;


decl: 
    type var_decl var_rep:

;

type:
   INT {
    $$ = new ASTNoTypeInt(yylineno+1);
    }
    
|  FLOAT {
     $$ = new ASTNoTypeFloat(yylineno+1);
    }
;

var_decl:
    ID {
    $$  = new ASTNoId(yylineno+1);
    ((ASTNoId)$$).setName($1);		
    }

|  ID '['NUM']'{
       $$  = new ASTNoVet(yylineno+1);
       ((ASTNoVet)$$).setID($1);	
       ((ASTNoVet)$$).setVetTamValue($3);
    }

;

var_rep:
   ',' ID {
     $$  = new ASTNoId(yylineno+1);
    ((ASTNoId)$$).setName($1);	
    }
|  ',' ID '['NUM']'{
        $$  = new ASTNoVet(yylineno+1);
       ((ASTNoVet)$$).setID($1);	
       ((ASTNoVet)$$).setVetTamValue($3);
    }
|  var_rep
|
;


expr :
	expr '<' expr {
                $$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('<');
	}
|	expr '>' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('>');
	}
|	expr '-' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('-');
	}
|	expr '*' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('*');
	}
|	expr '/' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('/');
	}
|	expr '+' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('+');
	}
|	expr '%' expr {
		$$  = new ASTNoExpr(yylineno+1);
		((ASTNoExpr)$$).setLeft($1);
		((ASTNoExprr)$$).setRight($3);
		((ASTNoExpr)$$).setOperator('%');
	}
|	ID {
		$$  = new ASTNoId(yylineno+1);
                 ((ASTNoId)$$).setName($1);
		
	}
|	NUM {
		$$  = new ASTNoConst(yylineno+1);
		((ASTNoConst)$$).setValue($1);
	}
|	'(' expr ')' {
		$$ = $2;
	}
|	'+' expr %prec PRE_MAIS {
		$$ = $2;
	}
|	'-' expr %prec PRE_MENOS {
		$$  = ASTNoExpr(yylineno+1);
                ((ASTNoExpr)$$).setLeft($2);
		((ASTNoExpr)$$).setOperator('-');
		
	}
;

%%

void yyerror(String msg) {
	 System.out.println(msg);
	 System.exit(1);
}

private Lexer lexer;
public Parser(Lexer lexer){
  this.lexer = lexer;
} 
public int yylex()throws Exception{
  return this.scanner.yylex();
}