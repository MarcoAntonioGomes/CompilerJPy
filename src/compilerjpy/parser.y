%{
import compilerjpy.ast.*;
import java.io.*;



%}




%token   ID INT  ERROR CIN COUT IF FOR FLOAT  ELSE WHILE CHAR NEQ EQU NOT OUT INP STRING NUM INC AND OR NUM_FLOAT ENDL
%nonassoc PRE_MAIS PRE_MENOS

%left '<' '>'
%left '*' '/' '%'
%left '+' '-'

%%
lst_comandos :
	lst_comandos comando  {
                ((ASTNoComand)$1).setNext((ASTNoComand)$2); $$ = $2;
		
	}
|	comando  {
		if(raiz == null) raiz = $1; 
                $$ = $1; 
}
;
comando :
	
	ID '=' expr ';'{
                $$ = new ASTNoAtrib(getYYline()+1); 
                ((ASTNoAtrib)$$).setName((String)$1);
                ((ASTNoAtrib)$$).setExpression((ASTNoExpr)$3);
		
	}

|       ID '['ID']' '=' expr ';'{
   
            $$  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)$$).setName((String)$1);
            ((ASTNoVet)$$).setVetIdName((String)$3);
            ((ASTNoVet)$$).setExpr((ASTNoExpr)$6);
        }
        
|       ID '['NUM']' '=' expr ';'{
   
            $$  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)$$).setName((String)$1);
            ((ASTNoVet)$$).setIndexVet((Integer)$3);
            ((ASTNoVet)$$).setExpr((ASTNoExpr)$6);
        }
|       ID '['ID']' INC ';'{
   
            $$  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)$$).setName((String)$1);
            ((ASTNoVet)$$).setVetIdName((String)$3);
            ((ASTNoVet)$$).setOperatorInc("++");
        }         

|      type lst_variaveis ';'{

         $$ = new ASTNoDecl(getYYline()+1);
         ((ASTNoDecl)$$).setType((ASTNoType)$1);
         ((ASTNoDecl)$$).setVariables((ASTNoLstVariables)$2);
       
       }
      
         
|      COUT lst_cout ';' {
         $$ = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)$$).setLstCout((ASTNoLstCout)$2);
        }

|      COUT lst_cout ENDL';' {
         $$ = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)$$).setLstCout((ASTNoLstCout)$2);
        }
|      COUT lst_cout OUT ENDL';' {
         $$ = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)$$).setLstCout((ASTNoLstCout)$2);
        }     

|      CIN INP expr ';'{
         $$ = new ASTNoCin(getYYline()+1);
            ((ASTNoCin)$$).setExpr((ASTNoExpr)$3);
       }
|      IF '(' expr ')' '{' lst_comandos '}' { $$ = new ASTNoIf((ASTNoExpr)$3,(ASTNoComand)$6,getYYline()+1); }
|      IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'{ $$ = new ASTNoIf((ASTNoExpr)$3,(ASTNoComand)$6,(ASTNoComand)$10,getYYline()+1); }
|      WHILE '(' expr ')' '{' lst_comandos '}' { $$ = new ASTNoWhile(getYYline()+1); 
                ((ASTNoWhile)$$).setCondition((ASTNoExpr)$3);
                ((ASTNoWhile)$$).setWhileComands((ASTNoComand)$6);
               
              }
|      FOR '(' assign ';' expr ';' opr_inc_for ')''{' lst_comandos '}'{
            $$ = new ASTNoFor(getYYline()+1);
                ((ASTNoFor)$$).setAtrib((ASTNoAtrib)$3);
                ((ASTNoFor)$$).setCondition((ASTNoExpr)$5);
                ((ASTNoFor)$$).setIncPosDec((ASTNoIncFor)$7);
                ((ASTNoFor)$$).setForComands((ASTNoComand)$10);
        }


;

assign:
    ID '=' expr  {
                $$ = new ASTNoAtrib(getYYline()+1);
                ((ASTNoAtrib)$$).setName((String)$1);
                ((ASTNoAtrib)$$).setExpression((ASTNoExpr)$3);
		
	}
       
;

opr_inc_for:
    ID INC {
     $$ = new ASTNoIncFor(getYYline()+1);
         ((ASTNoIncFor)$$).setIdName((String)$1);
         ((ASTNoIncFor)$$).setOperator((String)$2);
    }

;


type:
   INT {
    $$ = new ASTNoTypeInt(getYYline()+1);
    getSymbolTab().set("int");
    }
    
|  FLOAT {
     $$ = new ASTNoTypeFloat(getYYline()+1);
     getSymbolTab().set("float");
    }
;

lst_variaveis:
    
   lst_variaveis ','  ID  {

    $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$3,getYYline()+1));
    getSymbolTab().set((String)$3);
    ((ASTNoLstVariables)$$).setLstVariables((ASTNoLstVariables)$1);

    }

|  lst_variaveis ',' ID '['NUM']'   {
    $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$3,getYYline()+1));
    ((ASTNoLstVariables)$$).setVetTamValue((Integer)$5);
    getSymbolTab().set((String)$3);
    ((ASTNoLstVariables)$$).setLstVariables((ASTNoLstVariables)$1);
    getSymbolTab().insertMemberSymbols(new memberSymbols((String)$3,(Integer)$5));
    }
|  lst_variaveis ',' ID '['ID']'   {
    $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$3,getYYline()+1));
    ((ASTNoLstVariables)$$).setVetTamValue((Integer)$5);
    ((ASTNoLstVariables)$$).setLstVariables((ASTNoLstVariables)$1);
     getSymbolTab().set((String)$3);
    ((ASTNoLstVariables)$$).setVetIdName((String)$5);
    
    }
|   ID {
    $$  = new ASTNoLstVariables(getYYline()+1);
    getSymbolTab().set((String)$1);
   ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$1,getYYline()+1));		
   
    }

|  ID '['ID']'{

     $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$1,getYYline()+1));
    getSymbolTab().set((String)$1);
    ((ASTNoLstVariables)$$).setVetIdName((String)$3);
    }

|  ID '['NUM']'{

     $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$1,getYYline()+1));
    ((ASTNoLstVariables)$$).setVetTamValue((Integer)$3); 
    getSymbolTab().set((String)$1);
    getSymbolTab().insertMemberSymbols(new memberSymbols((String)$1,(Integer)$3));

    }

|   ID '=' expr {
    $$  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)$$).setIdName(new ASTNoId((String)$1,getYYline()+1));	
    ((ASTNoLstVariables)$$).setExpr((ASTNoExpr)$3);
    getSymbolTab().set((String)$1);	
    }
;

lst_cout:

    lst_cout OUT STRING {
        $$  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)$$).setLstCout((ASTNoLstCout)$1);
        ((ASTNoLstCout)$$).setString((String)$3);
    }
|    lst_cout OUT expr {
        $$  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)$$).setLstCout((ASTNoLstCout)$1);
        ((ASTNoLstCout)$$).setExpr((ASTNoExpr)$3);
    
    }

|   OUT STRING  {
        $$  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)$$).setString((String)$2);
    
    }

|   OUT expr{
         $$  = new ASTNoLstCout((Integer)getYYline()+1);
        ((ASTNoLstCout)$$).setExpr((ASTNoExpr)$2);
    
    }
|   OUT STRING OUT{
        $$  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)$$).setString((String)$2);
    
    }
;

expr :
	expr '<' expr {
                $$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('<');
	}
|	expr '>' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('>');
	}
|	expr '-' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('-');
	}
|	expr '*' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('*');
	}
|	expr '/' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('/');
	}
|	expr '+' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('+');
	}
|	expr '%' expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperator('%');
	}
|	expr AND expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperatorLogicLogic((String)"&&");
	}
|	expr OR expr {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)$$).setLeft((ASTNo)$1);
		((ASTNoExpr)$$).setRight((ASTNo)$3);
		((ASTNoExpr)$$).setOperatorLogicLogic((String)"||");
	}

|	ID {
		$$  = new ASTNoId((Integer)getYYline()+1);
                 ((ASTNoId)$$).setName((String)$1);
		
	}
|	ID '['ID']' {
            $$  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)$$).setName((String)$1);
            ((ASTNoVet)$$).setVetIdName((String)$3);
	}

|	NUM {
		$$  = new ASTNoConst((Integer)getYYline()+1);
		((ASTNoConst)$$).setValue((Integer)$1);
	}

|       NUM_FLOAT {
        $$  = new ASTNoFloat((Integer)getYYline()+1);
	     ((ASTNoFloat)$$).setValue((Float)$1);
         
        }
|       STRING{
        $$  = new ASTNoString(getYYline()+1);
        ((ASTNoString)$$).setValueString((String)$1);
        }

|	'(' expr ')' {
		$$ = $2;
	}
|	'+' expr %prec PRE_MAIS {
		$$ = $2;
	}
|	'-' expr %prec PRE_MENOS {
		$$  = new ASTNoExpr((Integer)getYYline()+1);
                ((ASTNoExpr)$$).setLeft((ASTNo)$2);
		((ASTNoExpr)$$).setOperator('-');
		
	}
;

%%
private  Object raiz;

private SymbolTab symbolTab = new SymbolTab();

public SymbolTab getSymbolTab(){
    return symbolTab;
}

public Parser(String filename) throws Exception {
	super(new FileReader(filename));
}

public ASTNoComand getRaiz(){
  return (ASTNoComand) raiz;
}



public void yyerror(String s) {
	System.out.println("Erro sintático: "+s);

}
