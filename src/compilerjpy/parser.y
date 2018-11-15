%{
import compilerjpy.ast.*;

public void yyerror(String msg);   // Funçao de erro

No raiz;
No_Numbers nosIds;

%}




%token   ID INT SUM SUBT DIV MULT ASSIGN ERROR CIN COUT IF FOR FLOAT LEFT_PARAMETER RIGHT_PARAMETER LEFT_BRACKETS RIGHT_BRACKETS ELSE WHILE CHAR SEMICOLON GREATER LESS NEQ EQU NOT
%nonassoc PRE_MAIS PRE_MENOS

%left '<' '>'
%left '*' '/' '%'
%left '+' '-'

%%
lst_comandos :
	lst_comandos comando ';' {
                (no_comand)$1.setNext($2);
		$$ = $2;
	}
|	comando ';' {
		raiz = $1;
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