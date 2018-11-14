%{
import _no_const;
import _no_expr;
import _no_id;
import _no_print_expr;
import _no_comand;
import _no_atrib;
import No;

public void yyerror(String msg);   // Funçao de erro

No raiz;
No_Numbers nosIds;

%}


%union{
	double valor;
	String nome;
	No no;
}

%token <nome> ID
%token <valor> NUM
%type <no> lst_comandos comando expr
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
                $$ = No no = new _no_print_expr(nosIds.NO_ID_PRINT_EXPR.ordinal(),yylineno+1);
		(_no_print_expr)$$.setExpression($1);
                (_no_print_expr)$$.setNext(null);
	}
|	ID '=' expr {
                $$ = No no = new _no_atrib(nosIds.NO_ID_ATRIB.ordinal(),yylineno+1);
                (_no_atrib)$$.setName($1);
                (_no_atrib)$$.setExpression($3);
		(_no_atrib)$$.setNext(null);
	}
;

expr :
	expr '<' expr {
                $$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('<');
	}
|	expr '>' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('>');
	}
|	expr '-' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('-');
	}
|	expr '*' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('*');
	}
|	expr '/' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('/');
	}
|	expr '+' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('+');
	}
|	expr '%' expr {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
		(_no_expr)$$.setLeft($1);
		(_no_expr)$$.setRight($3);
		(_no_expr)$$.setOperator('%');
	}
|	ID {
		$$ = No no = new _no_id(nosIds.NO_ID_ID.ordinal(),yylineno+1);
                 (_no_id)$$.setName($1);
		
	}
|	NUM {
		$$ = No no = new _no_const(nosIds.NO_ID_CONST.ordinal(),yylineno+1);
		(_no_const)$$.setValue($1);
	}
|	'(' expr ')' {
		$$ = $2;
	}
|	'+' expr %prec PRE_MAIS {
		$$ = $2;
	}
|	'-' expr %prec PRE_MENOS {
		$$ = No no = new _no_expr(nosIds.NO_ID_EXPR.ordinal(),yylineno+1);
                (_no_expr)$$.setLeft($2);
		(_no_expr)$$.setRight(null);
		(_no_expr)$$.setOperator('-');
		
	}
;

%%

void yyerror(String msg) {
	 System.out.println(msg);
	 System.exit(1);
}