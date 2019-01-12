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
public final static short ENDL=279;
public final static short PRE_MAIS=280;
public final static short PRE_MENOS=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    6,    7,    3,    3,    4,
    4,    4,    4,    4,    4,    4,    5,    5,    5,    5,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,
};
final static short yylen[] = {                            2,
    2,    1,    4,    7,    7,    6,    3,    3,    4,    5,
    4,    7,   11,    7,   11,    3,    2,    1,    1,    3,
    6,    6,    1,    4,    4,    3,    3,    3,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    1,
    4,    1,    1,    1,    3,    2,    2,
};
final static short yydefred[] = {                         0,
    0,   18,    0,    0,    0,    0,   19,    0,    0,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    0,   44,   42,   43,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    8,    0,    0,
    0,    0,    0,    0,    7,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,
    0,    0,   11,    0,    0,    0,    9,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   45,    0,    0,    0,
    0,    0,    0,    0,   36,   33,    0,    0,    0,   10,
    0,    0,    0,    0,   24,   25,    0,   41,    6,    0,
    0,    0,    0,    0,    0,    0,    4,    5,    0,    0,
    0,   14,   22,   21,    0,   17,    0,    0,    0,    0,
    0,   13,   15,
};
final static short yydgoto[] = {                          9,
   10,   30,   11,   22,   16,   41,  111,
};
final static short yysindex[] = {                       258,
  -22,    0, -234, -253,  -13,   15,    0,   32,  258,    0,
 -176,   47, -254,   47,   69,  -48,   47, -174,   47,    0,
  -17,  -31,   -7,    0,    0,    0,   47,   47,   47,   59,
    2,    6,   68,    0,  148,   16,   27,    0,   92,   33,
   34,   99,   47, -245,    0, -154, -150,  148,  148,  106,
   47,   47,   47,   47,   47,   47,   47,   47,   47,    0,
  -59,   55,    0,    0,   49,  148,    0,   -6,   47,   47,
   -3,  148,   30,   31,   40,   39,    0,  254,  254,  254,
  254,  -19,  -19,  -19,    0,    0,   63,   47,   47,    0,
  258,  148,  113,  258,    0,    0, -220,    0,    0,  120,
  141,   93, -132,  145,   45,   52,    0,    0, -101, -106,
  129,    0,    0,    0,   48,    0,   51,  258,  258,  188,
  221,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -8,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -28,  -43,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -11,    9,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -2,    0,  -38,    0,    0,    0,    0,
    0,   18,    0,    0,   19,    0,    0,  155,  165,  185,
  194,    5,   29,   38,    0,    0,    0,    0,    0,    0,
    0,  117,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                       103,
  228,  474,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=563;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   12,   88,   31,   40,   40,   40,   40,   40,   44,   40,
   38,   73,   46,   44,   44,   30,   44,   15,   44,   32,
   28,   40,   40,   58,   40,   59,   17,   45,   74,   46,
   29,   44,   46,   44,   44,   23,  105,   14,   12,   44,
   44,   34,   44,   43,   44,   34,   34,   46,   34,   47,
   23,   34,   47,  106,   18,   29,   27,   44,   27,   44,
   28,   26,   20,   34,   34,   35,   34,   47,   13,   35,
   35,   19,   35,   44,   37,   35,   26,   20,   37,   37,
   21,   37,   40,   47,   37,   67,   29,   35,   35,   27,
   35,   28,   70,   69,   61,   57,   37,   37,   62,   37,
   55,   58,   75,   59,   57,   56,   76,   90,   29,   55,
   58,   27,   59,   28,   56,   89,   91,   60,   53,   94,
   54,   99,   95,   96,  110,   12,   63,   53,   57,   54,
   97,   98,   68,   55,   58,   57,   59,  113,   56,   71,
   55,   58,   57,   59,  114,   56,   77,   55,   58,   57,
   59,   53,   56,   54,   55,   58,   57,   59,   53,   56,
   54,   55,   58,  115,   59,   53,   56,   54,  116,  117,
  118,  103,   53,  119,   54,   16,    0,   57,  107,   53,
    0,   54,   55,   58,   57,   59,    0,   56,    0,   55,
   58,    0,   59,  102,   56,   38,  104,    0,   38,  108,
   53,    0,   54,    0,    0,   39,    0,   53,   39,   54,
    0,    0,    0,   38,   38,   87,   38,  109,    0,    0,
  120,  121,   36,   39,   39,   31,   39,   30,   31,    0,
   37,    0,   28,   40,   32,   30,   20,   32,   40,   40,
   28,   40,   29,   31,   31,    0,   31,   44,   44,    0,
   29,    0,   32,   32,    0,   32,    0,   12,   12,   46,
   12,   12,   12,   12,   12,    0,   12,   46,   27,  112,
    0,    0,   23,   44,   44,   34,   27,    0,    0,   47,
   34,   34,    0,   34,    0,    0,    0,   47,   64,   25,
   57,    0,    0,   26,   65,   55,   58,    0,   59,   35,
   56,    0,    0,   23,   35,   35,    0,   35,   37,    0,
    0,    0,  122,   37,   37,    0,   37,    0,    0,   24,
   25,    0,    0,    0,   26,   23,    0,    0,    0,   20,
    0,   20,    0,    0,   51,   52,    0,    0,    0,    0,
    0,   34,   25,   51,   52,  123,   26,   20,   20,    1,
    2,    0,    3,    4,    5,    6,    7,    0,    8,    0,
    0,    0,    0,    0,    0,    0,    0,   51,   52,    0,
    0,    0,    0,    0,   51,   52,    0,    0,    0,    0,
    0,   51,   52,    0,    0,    0,    0,    0,   51,   52,
    0,    0,    0,    0,    0,   51,   52,    0,    0,    0,
    0,    1,    2,    0,    3,    4,    5,    6,    7,    0,
    8,    0,    0,    0,    0,    0,   51,   52,    0,    0,
    0,    0,    0,   51,   52,   38,    0,    0,    0,    0,
   38,   38,    0,   38,    0,   39,    0,    0,    0,    0,
   39,   39,    0,   39,    1,    2,    0,    3,    4,    5,
    6,    7,    0,    8,    0,   31,    0,    0,    0,    0,
   31,   31,    0,   31,   32,    0,    0,    0,    0,   32,
   32,    0,   32,    0,    0,    0,    0,    1,    2,    0,
    3,    4,    5,    6,    7,    0,    8,   33,   35,    0,
   39,    0,   42,    0,    0,    0,    0,    0,    0,    0,
   48,   49,   50,    0,    0,    0,    0,    0,    0,   66,
    0,    0,    0,    0,    1,    2,   72,    3,    4,    5,
    6,    7,    0,    8,   78,   79,   80,   81,   82,   83,
   84,   85,   86,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   92,   93,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  100,  101,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   61,  257,   41,   42,   43,   44,   45,   37,   47,
   59,  257,   44,   42,   43,   59,   45,  271,   47,  274,
   59,   59,   60,   43,   62,   45,   40,   59,  274,   41,
   59,   60,   44,   62,   37,   44,  257,  272,   61,   42,
   43,   37,   45,   61,   47,   41,   42,   59,   44,   41,
   59,   47,   44,  274,   40,   40,   59,   60,   43,   62,
   45,   44,   44,   59,   60,   37,   62,   59,   91,   41,
   42,   40,   44,   91,   37,   47,   59,   59,   41,   42,
  257,   44,  257,   91,   47,   59,   40,   59,   60,   43,
   62,   45,   59,   61,   93,   37,   59,   60,   93,   62,
   42,   43,  257,   45,   37,   47,  257,   59,   40,   42,
   43,   43,   45,   45,   47,   61,  123,   59,   60,  123,
   62,   59,   93,   93,  257,  125,   59,   60,   37,   62,
   91,   93,   41,   42,   43,   37,   45,   93,   47,   41,
   42,   43,   37,   45,   93,   47,   41,   42,   43,   37,
   45,   60,   47,   62,   42,   43,   37,   45,   60,   47,
   62,   42,   43,  265,   45,   60,   47,   62,  275,   41,
  123,   59,   60,  123,   62,   59,   -1,   37,   59,   60,
   -1,   62,   42,   43,   37,   45,   -1,   47,   -1,   42,
   43,   -1,   45,   91,   47,   41,   94,   -1,   44,   59,
   60,   -1,   62,   -1,   -1,   41,   -1,   60,   44,   62,
   -1,   -1,   -1,   59,   60,  275,   62,  125,   -1,   -1,
  118,  119,  271,   59,   60,   41,   62,  271,   44,   -1,
  279,   -1,  271,  271,   41,  279,    9,   44,  276,  277,
  279,  279,  271,   59,   60,   -1,   62,  276,  277,   -1,
  279,   -1,   59,   60,   -1,   62,   -1,  257,  258,  271,
  260,  261,  262,  263,  264,   -1,  266,  279,  271,  125,
   -1,   -1,  257,  276,  277,  271,  279,   -1,   -1,  271,
  276,  277,   -1,  279,   -1,   -1,   -1,  279,  273,  274,
   37,   -1,   -1,  278,  279,   42,   43,   -1,   45,  271,
   47,   -1,   -1,  257,  276,  277,   -1,  279,  271,   -1,
   -1,   -1,  125,  276,  277,   -1,  279,   -1,   -1,  273,
  274,   -1,   -1,   -1,  278,  257,   -1,   -1,   -1,  102,
   -1,  104,   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,
   -1,  273,  274,  276,  277,  125,  278,  120,  121,  257,
  258,   -1,  260,  261,  262,  263,  264,   -1,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,
   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,
   -1,  276,  277,   -1,   -1,   -1,   -1,   -1,  276,  277,
   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,   -1,
   -1,  257,  258,   -1,  260,  261,  262,  263,  264,   -1,
  266,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,
   -1,   -1,   -1,  276,  277,  271,   -1,   -1,   -1,   -1,
  276,  277,   -1,  279,   -1,  271,   -1,   -1,   -1,   -1,
  276,  277,   -1,  279,  257,  258,   -1,  260,  261,  262,
  263,  264,   -1,  266,   -1,  271,   -1,   -1,   -1,   -1,
  276,  277,   -1,  279,  271,   -1,   -1,   -1,   -1,  276,
  277,   -1,  279,   -1,   -1,   -1,   -1,  257,  258,   -1,
  260,  261,  262,  263,  264,   -1,  266,   14,   15,   -1,
   17,   -1,   19,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   27,   28,   29,   -1,   -1,   -1,   -1,   -1,   -1,   36,
   -1,   -1,   -1,   -1,  257,  258,   43,  260,  261,  262,
  263,  264,   -1,  266,   51,   52,   53,   54,   55,   56,
   57,   58,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   69,   70,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   88,   89,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=281;
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
"NUM","INC","AND","OR","NUM_FLOAT","ENDL","PRE_MAIS","PRE_MENOS",
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
"comando : COUT lst_cout ENDL ';'",
"comando : COUT lst_cout OUT ENDL ';'",
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
"expr : STRING",
"expr : '(' expr ')'",
"expr : '+' expr",
"expr : '-' expr",
};

//#line 333 "parser.y"
private  Object raiz;
private  Object noAux;
private boolean classMainCreate = false;

public void setClassMainCreate(boolean value){
    classMainCreate = value;
}

public boolean getClassMainCreate(){

    return classMainCreate;
}

public Object getNoAux(){

    return noAux;
}   


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
//#line 425 "Parser.java"
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
throws Exception
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
                 
                ((ASTNoComand)val_peek(1)).setNext((ASTNoComand)val_peek(0)); 
		((ASTNoComand)val_peek(0)).setPrevious((ASTNoComand)val_peek(1));
                yyval = val_peek(0);
	}
break;
case 2:
//#line 27 "parser.y"
{
                
		if(raiz == null) raiz = val_peek(0); 
                noAux = val_peek(0);
                yyval = val_peek(0); 
}
break;
case 3:
//#line 37 "parser.y"
{
                yyval = new ASTNoAtrib(getYYline()+1); 
                ((ASTNoAtrib)yyval).setName((String)val_peek(3));
                ((ASTNoAtrib)yyval).setExpression((ASTNoExpr)val_peek(1));
		
	}
break;
case 4:
//#line 44 "parser.y"
{
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(6));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(4));
            ((ASTNoVet)yyval).setExpr((ASTNoExpr)val_peek(1));
        }
break;
case 5:
//#line 52 "parser.y"
{
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(6));
            ((ASTNoVet)yyval).setIndexVet((Integer)val_peek(4));
            ((ASTNoVet)yyval).setExpr((ASTNoExpr)val_peek(1));
        }
break;
case 6:
//#line 59 "parser.y"
{
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(5));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(3));
            ((ASTNoVet)yyval).setOperatorInc("++");
        }
break;
case 7:
//#line 67 "parser.y"
{

         yyval = new ASTNoDecl(getYYline()+1);
         ((ASTNoDecl)yyval).setType((ASTNoType)val_peek(2));
         ((ASTNoDecl)yyval).setVariables((ASTNoLstVariables)val_peek(1));
       
       }
break;
case 8:
//#line 76 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(1));
        }
break;
case 9:
//#line 81 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        }
break;
case 10:
//#line 85 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(3));
        }
break;
case 11:
//#line 90 "parser.y"
{
         yyval = new ASTNoCin(getYYline()+1);
            ((ASTNoCin)yyval).setExpr((ASTNoExpr)val_peek(1));
       }
break;
case 12:
//#line 94 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(4),(ASTNoComand)val_peek(1),getYYline()+1);}
break;
case 13:
//#line 95 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(8),(ASTNoComand)val_peek(5),(ASTNoComand)val_peek(1),getYYline()+1);}
break;
case 14:
//#line 96 "parser.y"
{ yyval = new ASTNoWhile(getYYline()+1); 
                ((ASTNoWhile)yyval).setCondition((ASTNoExpr)val_peek(4));
                ((ASTNoWhile)yyval).setWhileComands((ASTNoComand)val_peek(1));
                 
              }
break;
case 15:
//#line 101 "parser.y"
{
     
                yyval = new ASTNoFor(getYYline()+1);
                ((ASTNoFor)yyval).setAtrib((ASTNoAtrib)val_peek(8));
                ((ASTNoFor)yyval).setCondition((ASTNoExpr)val_peek(6));
                ((ASTNoFor)yyval).setIncPosDec((ASTNoIncFor)val_peek(4));
                ((ASTNoFor)yyval).setForComands((ASTNoComand)getNoAux());
                
                
}
break;
case 16:
//#line 116 "parser.y"
{
                yyval = new ASTNoAtrib(getYYline()+1);
                ((ASTNoAtrib)yyval).setName((String)val_peek(2));
                ((ASTNoAtrib)yyval).setExpression((ASTNoExpr)val_peek(0));
		
	}
break;
case 17:
//#line 126 "parser.y"
{
     yyval = new ASTNoIncFor(getYYline()+1);
         ((ASTNoIncFor)yyval).setIdName((String)val_peek(1));
         ((ASTNoIncFor)yyval).setOperator((String)val_peek(0));
    }
break;
case 18:
//#line 136 "parser.y"
{
    yyval = new ASTNoTypeInt(getYYline()+1);
    getSymbolTab().set("int");
    }
break;
case 19:
//#line 141 "parser.y"
{
     yyval = new ASTNoTypeFloat(getYYline()+1);
     getSymbolTab().set("float");
    }
break;
case 20:
//#line 149 "parser.y"
{

    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));
    getSymbolTab().set((String)val_peek(0));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(2));

    }
break;
case 21:
//#line 158 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1));
    getSymbolTab().set((String)val_peek(3));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(5));
    getSymbolTab().insertMemberSymbols(new memberSymbols((String)val_peek(3),(Integer)val_peek(1)));
    }
break;
case 22:
//#line 166 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(5));
     getSymbolTab().set((String)val_peek(3));
    ((ASTNoLstVariables)yyval).setVetIdName((String)val_peek(1));
    
    }
break;
case 23:
//#line 175 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    getSymbolTab().set((String)val_peek(0));
   ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));		
   
    }
break;
case 24:
//#line 182 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    getSymbolTab().set((String)val_peek(3));
    ((ASTNoLstVariables)yyval).setVetIdName((String)val_peek(1));
    }
break;
case 25:
//#line 190 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1)); 
    getSymbolTab().set((String)val_peek(3));
    getSymbolTab().insertMemberSymbols(new memberSymbols((String)val_peek(3),(Integer)val_peek(1)));

    }
break;
case 26:
//#line 200 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(2),getYYline()+1));	
    ((ASTNoLstVariables)yyval).setExpr((ASTNoExpr)val_peek(0));
    getSymbolTab().set((String)val_peek(2));	
    }
break;
case 27:
//#line 210 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    }
break;
case 28:
//#line 215 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 29:
//#line 222 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    
    }
break;
case 30:
//#line 228 "parser.y"
{
         yyval  = new ASTNoLstCout((Integer)getYYline()+1);
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 31:
//#line 237 "parser.y"
{
                yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('<');
	}
break;
case 32:
//#line 243 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('>');
	}
break;
case 33:
//#line 249 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
	}
break;
case 34:
//#line 255 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('*');
	}
break;
case 35:
//#line 261 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('/');
	}
break;
case 36:
//#line 267 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('+');
	}
break;
case 37:
//#line 273 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('%');
	}
break;
case 38:
//#line 279 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"&&");
	}
break;
case 39:
//#line 285 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"||");
	}
break;
case 40:
//#line 292 "parser.y"
{
		yyval  = new ASTNoId((Integer)getYYline()+1);
                 ((ASTNoId)yyval).setName((String)val_peek(0));
		
	}
break;
case 41:
//#line 297 "parser.y"
{
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(3));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(1));
	}
break;
case 42:
//#line 303 "parser.y"
{
		yyval  = new ASTNoConst((Integer)getYYline()+1);
		((ASTNoConst)yyval).setValue((Integer)val_peek(0));
	}
break;
case 43:
//#line 308 "parser.y"
{
        yyval  = new ASTNoFloat((Integer)getYYline()+1);
	     ((ASTNoFloat)yyval).setValue((Float)val_peek(0));
         
        }
break;
case 44:
//#line 313 "parser.y"
{
        yyval  = new ASTNoString(getYYline()+1);
        ((ASTNoString)yyval).setValueString((String)val_peek(0));
        }
break;
case 45:
//#line 318 "parser.y"
{
		yyval = val_peek(1);
	}
break;
case 46:
//#line 321 "parser.y"
{
		yyval = val_peek(0);
	}
break;
case 47:
//#line 324 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
                ((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
		
	}
break;
//#line 976 "Parser.java"
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
