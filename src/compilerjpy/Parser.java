//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package compilerjpy;



//#line 2 "parser.y"
import compilerjpy.ast.*;
import java.io.*;



//#line 23 "Parser.java"




public class Parser
             extends Lexer
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short ERROR=259;
public final static short CIN=260;
public final static short COUT=261;
public final static short IF=262;
public final static short FOR=263;
public final static short FLOAT=264;
public final static short ELSE=265;
public final static short WHILE=266;
public final static short CHAR=267;
public final static short NEQ=268;
public final static short EQU=269;
public final static short NOT=270;
public final static short OUT=271;
public final static short INP=272;
public final static short STRING=273;
public final static short NUM=274;
public final static short INC=275;
public final static short AND=276;
public final static short OR=277;
public final static short NUM_FLOAT=278;
public final static short PRE_MAIS=279;
public final static short PRE_MENOS=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    6,    7,    3,    3,    4,    4,    4,
    4,    4,    4,    4,    5,    5,    5,    5,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,
};
final static short yylen[] = {                            2,
    2,    1,    4,    7,    7,    6,    3,    3,    4,    7,
   11,    7,   11,    3,    2,    1,    1,    3,    6,    6,
    1,    4,    4,    3,    3,    3,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    1,    4,    1,
    1,    3,    2,    2,
};
final static short yydefred[] = {                         0,
    0,   16,    0,    0,    0,    0,   17,    0,    0,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    0,   40,   41,    0,    0,    0,    0,    0,
    0,    0,   27,    0,    0,    8,    0,    0,    0,    0,
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    3,    0,    0,
    9,   25,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   22,
   23,    0,   39,    6,    0,    0,    0,    0,    0,    0,
    0,    4,    5,    0,    0,    0,   12,   20,   19,    0,
   15,    0,    0,    0,    0,    0,   11,   13,
};
final static short yydgoto[] = {                          9,
   10,   29,   11,   22,   16,   39,  106,
};
final static short yysindex[] = {                        30,
  -34,    0, -270, -250,  -14,   -3,    0,   20,   30,    0,
 -224,   56, -233,   56,  -27,  -56,   56, -194,   56,    0,
  -33,   35,  -21,    0,    0,   56,   56,   56,  -28,  -16,
   -4,   -7,    0,   76,   11,    0,    2,   42,   49,   24,
   56, -228,    0, -142, -137,   76,   76,   31,   56,   56,
   56,   56,   56,   56,   56,   56,   56,    0,  -50,   61,
    0,    0,   76,   19,   56,   56,   27,   76,   32,   41,
   60,   66,    0,   76,   76,   90,   90,    5,    5,    5,
 -136, -136,  108,   56,   56,   30,   76,   38,   30,    0,
    0, -215,    0,    0,   45,   69,   75,  -88,   97,   81,
   85,    0,    0,  -79,  -86,  151,    0,    0,    0,   70,
    0,   73,   30,   30,  117,  141,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   51,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -47,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  116,  122,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -39,    0,    0,    0,    0,   58,    0,    0,
   65,    0,    0,  124,  147,  157,  194,  135,  143,  167,
  102,  111,    0,    0,    0,    0,  140,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                       185,
  121,  394,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=479;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   10,   14,   36,   38,   38,   38,   38,   38,   55,   38,
   84,   28,   28,   53,   56,   26,   57,   27,   54,   26,
   15,   38,   38,   30,   38,   17,   12,   41,   69,   55,
   58,   51,   21,   52,   53,   56,   18,   57,   55,   54,
   31,  100,   64,   53,   56,   70,   57,   56,   54,   57,
   28,   61,   51,   26,   52,   27,   13,   42,  101,   19,
   55,   51,   38,   52,   67,   53,   56,   55,   57,   45,
   54,   73,   53,   56,   55,   57,   59,   54,   44,   53,
   56,   55,   57,   51,   54,   52,   53,   56,   60,   57,
   51,   54,   52,   43,   21,   28,   98,   51,   26,   52,
   27,   24,   65,  102,   51,   55,   52,   66,   18,   21,
   53,   56,   55,   57,   71,   54,   24,   53,   56,   72,
   57,   85,   54,   18,   90,   10,   55,  103,   51,   20,
   52,   53,   56,   91,   57,   51,   54,   52,   34,   49,
   50,   86,   34,   34,   34,   34,   34,   31,   34,   89,
   92,   31,   31,   31,   31,   31,   43,   31,   93,   43,
   34,   34,   44,   34,   36,   44,   94,   36,  105,   31,
   31,   32,   31,  108,   43,   32,   32,  109,   32,   33,
   44,   32,   36,   33,   33,  110,   33,   37,  111,   33,
   37,  112,  113,   32,   32,  114,   32,   29,   14,  104,
   29,   33,   33,   35,   33,   37,    0,   35,   35,    0,
   35,    0,    0,   35,   35,   29,   29,   20,   29,   20,
    0,  107,    0,   28,   83,   35,   35,    0,   35,   23,
    0,   26,    0,   38,   30,   20,   20,   30,   38,   38,
    0,  117,    0,    0,    0,   33,   24,   49,   50,    0,
   25,    0,   30,   30,    0,   30,    0,   10,   10,    0,
   10,   10,   10,   10,   10,  118,   10,   23,   49,   50,
   97,    0,    0,   99,    0,    0,    0,   49,   50,    0,
   49,   50,    0,   62,   24,    0,    1,    2,   25,    3,
    4,    5,    6,    7,    0,    8,    0,  115,  116,   49,
   50,    0,    0,    0,    0,    0,   49,   50,    0,    0,
    0,    0,   23,   49,   50,    0,    0,    0,    0,    0,
   49,   50,    0,    0,    0,    0,    0,    0,    0,   24,
    0,    1,    2,   25,    3,    4,    5,    6,    7,    0,
    8,    0,    0,    0,   49,   50,    0,    0,    0,    0,
    0,   49,   50,    1,    2,    0,    3,    4,    5,    6,
    7,    0,    8,    0,    0,   49,   50,    0,    0,    0,
    0,    0,   34,    1,    2,    0,    3,    4,    5,    6,
    7,   31,    8,    0,    0,    0,   43,    0,    0,    0,
    0,    0,   44,    0,   36,    0,    0,    1,    2,    0,
    3,    4,    5,    6,    7,   32,    8,   32,   34,    0,
   37,    0,   40,   33,    0,    0,    0,   37,    0,   46,
   47,   48,    0,    0,    0,    0,    0,   29,   63,    0,
    0,    0,    0,    0,   68,    0,    0,   35,    0,    0,
    0,    0,   74,   75,   76,   77,   78,   79,   80,   81,
   82,    0,    0,    0,    0,    0,    0,    0,   87,   88,
    0,    0,    0,    0,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   95,   96,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,  272,   59,   41,   42,   43,   44,   45,   37,   47,
   61,   59,   40,   42,   43,   43,   45,   45,   47,   59,
  271,   59,   60,  257,   62,   40,   61,   61,  257,   37,
   59,   60,  257,   62,   42,   43,   40,   45,   37,   47,
  274,  257,   41,   42,   43,  274,   45,   43,   47,   45,
   40,   59,   60,   43,   62,   45,   91,   91,  274,   40,
   37,   60,  257,   62,   41,   42,   43,   37,   45,   91,
   47,   41,   42,   43,   37,   45,   93,   47,   44,   42,
   43,   37,   45,   60,   47,   62,   42,   43,   93,   45,
   60,   47,   62,   59,   44,   40,   59,   60,   43,   62,
   45,   44,   61,   59,   60,   37,   62,   59,   44,   59,
   42,   43,   37,   45,  257,   47,   59,   42,   43,  257,
   45,   61,   47,   59,   93,  125,   37,   59,   60,    9,
   62,   42,   43,   93,   45,   60,   47,   62,   37,  276,
  277,  123,   41,   42,   43,   44,   45,   37,   47,  123,
   91,   41,   42,   43,   44,   45,   41,   47,   93,   44,
   59,   60,   41,   62,   41,   44,   59,   44,  257,   59,
   60,   37,   62,   93,   59,   41,   42,   93,   44,   37,
   59,   47,   59,   41,   42,  265,   44,   41,  275,   47,
   44,   41,  123,   59,   60,  123,   62,   41,   59,  125,
   44,   59,   60,   37,   62,   59,   -1,   41,   42,   -1,
   44,   -1,   -1,   47,  271,   59,   60,   97,   62,   99,
   -1,  125,   -1,  271,  275,   59,   60,   -1,   62,  257,
   -1,  271,   -1,  271,   41,  115,  116,   44,  276,  277,
   -1,  125,   -1,   -1,   -1,  273,  274,  276,  277,   -1,
  278,   -1,   59,   60,   -1,   62,   -1,  257,  258,   -1,
  260,  261,  262,  263,  264,  125,  266,  257,  276,  277,
   86,   -1,   -1,   89,   -1,   -1,   -1,  276,  277,   -1,
  276,  277,   -1,  273,  274,   -1,  257,  258,  278,  260,
  261,  262,  263,  264,   -1,  266,   -1,  113,  114,  276,
  277,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,
   -1,   -1,  257,  276,  277,   -1,   -1,   -1,   -1,   -1,
  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,
   -1,  257,  258,  278,  260,  261,  262,  263,  264,   -1,
  266,   -1,   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,
   -1,  276,  277,  257,  258,   -1,  260,  261,  262,  263,
  264,   -1,  266,   -1,   -1,  276,  277,   -1,   -1,   -1,
   -1,   -1,  271,  257,  258,   -1,  260,  261,  262,  263,
  264,  271,  266,   -1,   -1,   -1,  271,   -1,   -1,   -1,
   -1,   -1,  271,   -1,  271,   -1,   -1,  257,  258,   -1,
  260,  261,  262,  263,  264,  271,  266,   14,   15,   -1,
   17,   -1,   19,  271,   -1,   -1,   -1,  271,   -1,   26,
   27,   28,   -1,   -1,   -1,   -1,   -1,  271,   35,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   -1,  271,   -1,   -1,
   -1,   -1,   49,   50,   51,   52,   53,   54,   55,   56,
   57,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   65,   66,
   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   84,   85,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ID","INT","ERROR","CIN","COUT",
"IF","FOR","FLOAT","ELSE","WHILE","CHAR","NEQ","EQU","NOT","OUT","INP","STRING",
"NUM","INC","AND","OR","NUM_FLOAT","PRE_MAIS","PRE_MENOS",
};
final static String yyrule[] = {
"$accept : lst_comandos",
"lst_comandos : lst_comandos comando",
"lst_comandos : comando",
"comando : ID '=' expr ';'",
"comando : ID '[' ID ']' '=' expr ';'",
"comando : ID '[' NUM ']' '=' expr ';'",
"comando : ID '[' ID ']' INC ';'",
"comando : type lst_variaveis ';'",
"comando : COUT lst_cout ';'",
"comando : CIN INP expr ';'",
"comando : IF '(' expr ')' '{' lst_comandos '}'",
"comando : IF '(' expr ')' '{' lst_comandos '}' ELSE '{' lst_comandos '}'",
"comando : WHILE '(' expr ')' '{' lst_comandos '}'",
"comando : FOR '(' assign ';' expr ';' opr_inc_for ')' '{' lst_comandos '}'",
"assign : ID '=' expr",
"opr_inc_for : ID INC",
"type : INT",
"type : FLOAT",
"lst_variaveis : lst_variaveis ',' ID",
"lst_variaveis : lst_variaveis ',' ID '[' NUM ']'",
"lst_variaveis : lst_variaveis ',' ID '[' ID ']'",
"lst_variaveis : ID",
"lst_variaveis : ID '[' ID ']'",
"lst_variaveis : ID '[' NUM ']'",
"lst_variaveis : ID '=' expr",
"lst_cout : lst_cout OUT STRING",
"lst_cout : lst_cout OUT expr",
"lst_cout : OUT STRING",
"lst_cout : OUT expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '+' expr",
"expr : expr '%' expr",
"expr : expr AND expr",
"expr : expr OR expr",
"expr : ID",
"expr : ID '[' ID ']'",
"expr : NUM",
"expr : NUM_FLOAT",
"expr : '(' expr ')'",
"expr : '+' expr",
"expr : '-' expr",
};

//#line 301 "parser.y"
private  Object raiz;

private SymbolTab symbolTab = new SymbolTab();

public SymbolTab getSembolTab(){
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
//#line 382 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws IOException
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 21 "parser.y"
{
                ((ASTNoComand)val_peek(1)).setNext((ASTNoComand)val_peek(0)); yyval = val_peek(0);
		yyval = val_peek(0);
	}
break;
case 2:
//#line 25 "parser.y"
{
		if(raiz == null) raiz = val_peek(0); 
                yyval = val_peek(0); 
}
break;
case 3:
//#line 32 "parser.y"
{
                yyval = new ASTNoAtrib(getYYline()+1); 
                ((ASTNoAtrib)yyval).setName((String)val_peek(3));
                ((ASTNoAtrib)yyval).setExpression((ASTNoExpr)val_peek(1));
		
	}
break;
case 4:
//#line 39 "parser.y"
{
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(6));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(4));
            ((ASTNoVet)yyval).setExpr((ASTNoExpr)val_peek(1));
        }
break;
case 5:
//#line 47 "parser.y"
{
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(6));
            ((ASTNoVet)yyval).setIndexVet((Integer)val_peek(4));
            ((ASTNoVet)yyval).setExpr((ASTNoExpr)val_peek(1));
        }
break;
case 6:
//#line 54 "parser.y"
{
   
            yyval  = new ASTNoVetInc((Integer)getYYline()+1);
            ((ASTNoVetInc)yyval).setName((String)val_peek(5));
            ((ASTNoVetInc)yyval).setVetIdName((String)val_peek(3));
         
        }
break;
case 7:
//#line 62 "parser.y"
{

         yyval = new ASTNoDecl(getYYline()+1);
         ((ASTNoDecl)yyval).setType((ASTNoType)val_peek(2));
         ((ASTNoDecl)yyval).setVariables((ASTNoLstVariables)val_peek(1));
       
       }
break;
case 8:
//#line 71 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(1));
        }
break;
case 9:
//#line 77 "parser.y"
{
         yyval = new ASTNoCin(getYYline()+1);
            ((ASTNoCin)yyval).setExpr((ASTNoExpr)val_peek(1));
       }
break;
case 10:
//#line 81 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(4),(ASTNoComand)val_peek(1),getYYline()+1); }
break;
case 11:
//#line 82 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(8),(ASTNoComand)val_peek(5),(ASTNoComand)val_peek(1),getYYline()+1); }
break;
case 12:
//#line 83 "parser.y"
{ yyval = new ASTNoWhile(getYYline()+1); 
                ((ASTNoWhile)yyval).setCondition((ASTNoExpr)val_peek(4));
                ((ASTNoWhile)yyval).setWhileComands((ASTNoComand)val_peek(1));
               
              }
break;
case 13:
//#line 88 "parser.y"
{
            yyval = new ASTNoFor(getYYline()+1);
                ((ASTNoFor)yyval).setAtrib((ASTNoAtrib)val_peek(8));
                ((ASTNoFor)yyval).setCondition((ASTNoExpr)val_peek(6));
                ((ASTNoFor)yyval).setIncPosDec((ASTNoIncFor)val_peek(4));
                ((ASTNoFor)yyval).setForComands((ASTNoComand)val_peek(1));
        }
break;
case 14:
//#line 100 "parser.y"
{
                yyval = new ASTNoAtrib(getYYline()+1);
                ((ASTNoAtrib)yyval).setName((String)val_peek(2));
                ((ASTNoAtrib)yyval).setExpression((ASTNoExpr)val_peek(0));
		
	}
break;
case 15:
//#line 110 "parser.y"
{
     yyval = new ASTNoIncFor(getYYline()+1);
         ((ASTNoIncFor)yyval).setIdName((String)val_peek(1));
         ((ASTNoIncFor)yyval).setOperator((String)val_peek(0));
    }
break;
case 16:
//#line 120 "parser.y"
{
    yyval = new ASTNoTypeInt(getYYline()+1);
    }
break;
case 17:
//#line 124 "parser.y"
{
     yyval = new ASTNoTypeFloat(getYYline()+1);
    }
break;
case 18:
//#line 131 "parser.y"
{

    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(2));

    }
break;
case 19:
//#line 139 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(5));
    
    }
break;
case 20:
//#line 146 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(5));
    ((ASTNoLstVariables)yyval).setVetIdName((String)val_peek(1));
    }
break;
case 21:
//#line 153 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
   ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));		
   
    }
break;
case 22:
//#line 159 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetIdName((String)val_peek(1));
    }
break;
case 23:
//#line 166 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1));
    
    }
break;
case 24:
//#line 174 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(2),getYYline()+1));	
    ((ASTNoLstVariables)yyval).setExpr((ASTNoExpr)val_peek(0));	
    }
break;
case 25:
//#line 183 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    }
break;
case 26:
//#line 188 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 27:
//#line 194 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    
    }
break;
case 28:
//#line 200 "parser.y"
{
         yyval  = new ASTNoLstCout((Integer)getYYline()+1);
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 29:
//#line 209 "parser.y"
{
                yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('<');
	}
break;
case 30:
//#line 215 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('>');
	}
break;
case 31:
//#line 221 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
	}
break;
case 32:
//#line 227 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('*');
	}
break;
case 33:
//#line 233 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('/');
	}
break;
case 34:
//#line 239 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('+');
	}
break;
case 35:
//#line 245 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('%');
	}
break;
case 36:
//#line 251 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"&&");
	}
break;
case 37:
//#line 257 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"||");
	}
break;
case 38:
//#line 264 "parser.y"
{
		yyval  = new ASTNoId((Integer)getYYline()+1);
                 ((ASTNoId)yyval).setName((String)val_peek(0));
		
	}
break;
case 39:
//#line 269 "parser.y"
{
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(3));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(1));
	}
break;
case 40:
//#line 275 "parser.y"
{
		yyval  = new ASTNoConst((Integer)getYYline()+1);
		((ASTNoConst)yyval).setValue((Integer)val_peek(0));
	}
break;
case 41:
//#line 280 "parser.y"
{
        yyval  = new ASTNoFloat((Integer)getYYline()+1);
	     ((ASTNoFloat)yyval).setValue((Float)val_peek(0));
         
        }
break;
case 42:
//#line 286 "parser.y"
{
		yyval = val_peek(1);
	}
break;
case 43:
//#line 289 "parser.y"
{
		yyval = val_peek(0);
	}
break;
case 44:
//#line 292 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
                ((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
		
	}
break;
//#line 894 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
