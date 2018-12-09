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
    5,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,
};
final static short yylen[] = {                            2,
    2,    1,    4,    7,    7,    6,    3,    3,    4,    5,
    4,    7,   11,    7,   11,    3,    2,    1,    1,    3,
    6,    6,    1,    4,    4,    3,    3,    3,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    1,    4,    1,    1,    1,    3,    2,    2,
};
final static short yydefred[] = {                         0,
    0,   18,    0,    0,    0,    0,   19,    0,    0,    2,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    0,   45,   43,   44,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    8,    0,    0,
    0,    0,    0,    0,    7,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,
    0,    0,   11,   31,    0,    0,    0,    9,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   10,    0,    0,    0,    0,   24,   25,    0,   42,    6,
    0,    0,    0,    0,    0,    0,    0,    4,    5,    0,
    0,    0,   14,   22,   21,    0,   17,    0,    0,    0,
    0,    0,   13,   15,
};
final static short yydgoto[] = {                          9,
   10,   30,   11,   22,   16,   41,  112,
};
final static short yysindex[] = {                       267,
  -58,    0, -254, -247,  -10,   15,    0,   23,  267,    0,
 -201,   46, -246,   46,   71,  -46,   46, -193,   46,    0,
  -45,  -23,  -17,    0,    0,    0,   46,   46,   46,   92,
  -13,   -9,   99, -186,  229,   38,   33,    0,  123,   52,
   61,  130,   46, -245,    0, -133, -126,  229,  229,  137,
   46,   46,   46,   46,   46,   46,   46,   46,   46,    0,
  -59,   72,    0,    0,    0,   73,  229,    0,   17,   46,
   46,   20,  229,   45,   54,   57,   56,    0,  229,  229,
  172,  172,   16,   16,   16, -250, -250,   86,   46,   46,
    0,  267,  229,  144,  267,    0,    0, -237,    0,    0,
  151,  185,   93, -107,  121,   60,   62,    0,    0, -109,
 -118,  122,    0,    0,    0,   53,    0,   77,  267,  267,
  131,  173,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -6,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   28,    9,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   58,  194,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -28,    0,   39,    0,    0,    0,
    0,    0,    3,    0,    0,   49,    0,    0,  211,  216,
   63,  161,   35,   59,   68,   -2,    7,    0,    0,    0,
    0,    0,  103,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                       189,
  349,  464,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=554;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   12,   89,   12,   41,   41,   41,   41,   41,   45,   41,
   31,   74,   38,   45,   45,   43,   45,   14,   45,  106,
   46,   41,   41,   15,   41,   51,   52,   32,   75,   17,
   27,   45,   13,   45,   37,   45,  107,   23,   37,   37,
   37,   37,   37,   34,   37,   44,   26,   34,   34,   34,
   34,   34,   23,   34,   18,   21,   37,   37,   58,   37,
   59,   26,   19,   40,   45,   34,   34,   30,   34,   45,
   45,   35,   45,   47,   45,   35,   35,   29,   35,   61,
   27,   35,   28,   62,   64,   29,   29,   45,   27,   45,
   28,   68,   20,   35,   35,   36,   35,   28,   47,   36,
   36,   47,   36,   32,   38,   36,   32,   20,   38,   38,
   29,   38,   70,   27,   38,   28,   47,   36,   36,   71,
   36,   32,   32,   76,   32,   12,   38,   38,   57,   38,
   77,   91,   90,   55,   58,   57,   59,   96,   56,   92,
   55,   58,   95,   59,  100,   56,   97,   98,   99,  111,
   60,   53,  114,   54,  115,  116,  117,   63,   53,   57,
   54,   16,  118,   69,   55,   58,   57,   59,    0,   56,
   72,   55,   58,   57,   59,  119,   56,   78,   55,   58,
   57,   59,   53,   56,   54,   55,   58,   57,   59,   53,
   56,   54,   55,   58,    0,   59,   53,   56,   54,  120,
    0,   33,  104,   53,   33,   54,    0,    0,   57,  108,
   53,    0,   54,   55,   58,   88,   59,  110,   56,   33,
   33,   57,   33,    0,   36,    0,   55,   58,    0,   59,
    0,   56,   37,   41,   48,    0,    0,   48,   41,   41,
    0,   41,   27,  109,   53,  113,   54,   45,   45,    0,
   27,   39,   48,    0,   39,  123,   40,   12,   12,   40,
   12,   12,   12,   12,   12,   57,   12,    0,   37,   39,
   55,   58,    0,   59,   40,   56,   37,   34,    0,   30,
  103,    0,    0,  105,    0,   34,    0,   30,   53,    0,
   54,   51,   52,    0,   23,    0,    0,  124,    0,    0,
    0,    0,   23,   45,   45,   35,   29,  121,  122,   28,
   65,   25,    0,   35,    0,   26,   66,   28,   24,   25,
    0,    0,    0,   26,    0,    0,    0,   23,   47,   36,
    0,    0,    0,   32,    0,    0,   47,   36,   38,    0,
    0,   32,    0,   34,   25,    0,   38,    0,   26,    1,
    2,    0,    3,    4,    5,    6,    7,   20,    8,    0,
    0,    0,    0,    0,    0,    0,    0,   51,   52,    0,
    0,    0,    0,    0,   51,   52,    0,    1,    2,    0,
    3,    4,    5,    6,    7,    0,    8,    1,    2,    0,
    3,    4,    5,    6,    7,    0,    8,    0,   51,   52,
    0,    0,    0,    0,    0,   51,   52,    0,    0,    0,
    0,    0,   51,   52,    0,    0,    0,    0,    0,   51,
   52,    0,    0,    0,    0,    0,   51,   52,    0,    1,
    2,   33,    3,    4,    5,    6,    7,    0,    8,   33,
    0,    0,    0,    0,    0,    0,    0,   51,   52,    0,
    0,   20,    0,   20,    0,    0,    0,    0,    0,    0,
   51,   52,    0,    0,   48,    0,    0,    0,    0,   20,
   20,    0,   48,    0,    0,    0,    0,   33,   35,    0,
   39,   39,   42,    0,    0,    0,   40,    0,    0,   39,
   48,   49,   50,    0,   40,    0,    0,    0,    0,   67,
    0,    0,    0,    0,   51,   52,   73,    0,    0,    0,
    0,    0,    0,    0,   79,   80,   81,   82,   83,   84,
   85,   86,   87,    1,    2,    0,    3,    4,    5,    6,
    7,    0,    8,   93,   94,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  101,  102,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   61,   61,   41,   42,   43,   44,   45,   37,   47,
  257,  257,   59,   42,   43,   61,   45,  272,   47,  257,
   44,   59,   60,  271,   62,  276,  277,  274,  274,   40,
   59,   60,   91,   62,   37,   59,  274,   44,   41,   42,
   43,   44,   45,   37,   47,   91,   44,   41,   42,   43,
   44,   45,   59,   47,   40,  257,   59,   60,   43,   62,
   45,   59,   40,  257,   37,   59,   60,   59,   62,   42,
   43,   37,   45,   91,   47,   41,   42,   40,   44,   93,
   43,   47,   45,   93,  271,   40,   59,   60,   43,   62,
   45,   59,   44,   59,   60,   37,   62,   59,   41,   41,
   42,   44,   44,   41,   37,   47,   44,   59,   41,   42,
   40,   44,   61,   43,   47,   45,   59,   59,   60,   59,
   62,   59,   60,  257,   62,  125,   59,   60,   37,   62,
  257,   59,   61,   42,   43,   37,   45,   93,   47,  123,
   42,   43,  123,   45,   59,   47,   93,   91,   93,  257,
   59,   60,   93,   62,   93,  265,  275,   59,   60,   37,
   62,   59,   41,   41,   42,   43,   37,   45,   -1,   47,
   41,   42,   43,   37,   45,  123,   47,   41,   42,   43,
   37,   45,   60,   47,   62,   42,   43,   37,   45,   60,
   47,   62,   42,   43,   -1,   45,   60,   47,   62,  123,
   -1,   41,   59,   60,   44,   62,   -1,   -1,   37,   59,
   60,   -1,   62,   42,   43,  275,   45,  125,   47,   59,
   60,   37,   62,   -1,  271,   -1,   42,   43,   -1,   45,
   -1,   47,  279,  271,   41,   -1,   -1,   44,  276,  277,
   -1,  279,  271,   59,   60,  125,   62,  276,  277,   -1,
  279,   41,   59,   -1,   44,  125,   41,  257,  258,   44,
  260,  261,  262,  263,  264,   37,  266,   -1,  271,   59,
   42,   43,   -1,   45,   59,   47,  279,  271,   -1,  271,
   92,   -1,   -1,   95,   -1,  279,   -1,  279,   60,   -1,
   62,  276,  277,   -1,  257,   -1,   -1,  125,   -1,   -1,
   -1,   -1,  257,  276,  277,  271,  279,  119,  120,  271,
  273,  274,   -1,  279,   -1,  278,  279,  279,  273,  274,
   -1,   -1,   -1,  278,   -1,   -1,   -1,  257,  271,  271,
   -1,   -1,   -1,  271,   -1,   -1,  279,  279,  271,   -1,
   -1,  279,   -1,  273,  274,   -1,  279,   -1,  278,  257,
  258,   -1,  260,  261,  262,  263,  264,    9,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,
   -1,   -1,   -1,   -1,  276,  277,   -1,  257,  258,   -1,
  260,  261,  262,  263,  264,   -1,  266,  257,  258,   -1,
  260,  261,  262,  263,  264,   -1,  266,   -1,  276,  277,
   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,   -1,
   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,   -1,  276,
  277,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,  257,
  258,  271,  260,  261,  262,  263,  264,   -1,  266,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,
   -1,  103,   -1,  105,   -1,   -1,   -1,   -1,   -1,   -1,
  276,  277,   -1,   -1,  271,   -1,   -1,   -1,   -1,  121,
  122,   -1,  279,   -1,   -1,   -1,   -1,   14,   15,   -1,
   17,  271,   19,   -1,   -1,   -1,  271,   -1,   -1,  279,
   27,   28,   29,   -1,  279,   -1,   -1,   -1,   -1,   36,
   -1,   -1,   -1,   -1,  276,  277,   43,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   51,   52,   53,   54,   55,   56,
   57,   58,   59,  257,  258,   -1,  260,  261,  262,  263,
  264,   -1,  266,   70,   71,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   89,   90,
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
"lst_cout : OUT STRING OUT",
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

//#line 329 "parser.y"
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
//#line 406 "Parser.java"
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
                ((ASTNoComand)val_peek(1)).setNext((ASTNoComand)val_peek(0)); yyval = val_peek(0);
		
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
   
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(5));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(3));
            ((ASTNoVet)yyval).setOperatorInc("++");
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
//#line 76 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        }
break;
case 10:
//#line 80 "parser.y"
{
         yyval = new ASTNoCout(getYYline()+1);
         ((ASTNoCout)yyval).setLstCout((ASTNoLstCout)val_peek(3));
        }
break;
case 11:
//#line 85 "parser.y"
{
         yyval = new ASTNoCin(getYYline()+1);
            ((ASTNoCin)yyval).setExpr((ASTNoExpr)val_peek(1));
       }
break;
case 12:
//#line 89 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(4),(ASTNoComand)val_peek(1),getYYline()+1); }
break;
case 13:
//#line 90 "parser.y"
{ yyval = new ASTNoIf((ASTNoExpr)val_peek(8),(ASTNoComand)val_peek(5),(ASTNoComand)val_peek(1),getYYline()+1); }
break;
case 14:
//#line 91 "parser.y"
{ yyval = new ASTNoWhile(getYYline()+1); 
                ((ASTNoWhile)yyval).setCondition((ASTNoExpr)val_peek(4));
                ((ASTNoWhile)yyval).setWhileComands((ASTNoComand)val_peek(1));
               
              }
break;
case 15:
//#line 96 "parser.y"
{
            yyval = new ASTNoFor(getYYline()+1);
                ((ASTNoFor)yyval).setAtrib((ASTNoAtrib)val_peek(8));
                ((ASTNoFor)yyval).setCondition((ASTNoExpr)val_peek(6));
                ((ASTNoFor)yyval).setIncPosDec((ASTNoIncFor)val_peek(4));
                ((ASTNoFor)yyval).setForComands((ASTNoComand)val_peek(1));
        }
break;
case 16:
//#line 108 "parser.y"
{
                yyval = new ASTNoAtrib(getYYline()+1);
                ((ASTNoAtrib)yyval).setName((String)val_peek(2));
                ((ASTNoAtrib)yyval).setExpression((ASTNoExpr)val_peek(0));
		
	}
break;
case 17:
//#line 118 "parser.y"
{
     yyval = new ASTNoIncFor(getYYline()+1);
         ((ASTNoIncFor)yyval).setIdName((String)val_peek(1));
         ((ASTNoIncFor)yyval).setOperator((String)val_peek(0));
    }
break;
case 18:
//#line 128 "parser.y"
{
    yyval = new ASTNoTypeInt(getYYline()+1);
    getSymbolTab().set("int");
    }
break;
case 19:
//#line 133 "parser.y"
{
     yyval = new ASTNoTypeFloat(getYYline()+1);
     getSymbolTab().set("float");
    }
break;
case 20:
//#line 141 "parser.y"
{

    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));
    getSymbolTab().set((String)val_peek(0));
    ((ASTNoLstVariables)yyval).setLstVariables((ASTNoLstVariables)val_peek(2));

    }
break;
case 21:
//#line 150 "parser.y"
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
//#line 158 "parser.y"
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
//#line 167 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    getSymbolTab().set((String)val_peek(0));
   ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(0),getYYline()+1));		
   
    }
break;
case 24:
//#line 174 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    getSymbolTab().set((String)val_peek(3));
    ((ASTNoLstVariables)yyval).setVetIdName((String)val_peek(1));
    }
break;
case 25:
//#line 182 "parser.y"
{

     yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(3),getYYline()+1));
    ((ASTNoLstVariables)yyval).setVetTamValue((Integer)val_peek(1)); 
    getSymbolTab().set((String)val_peek(3));
    getSymbolTab().insertMemberSymbols(new memberSymbols((String)val_peek(3),(Integer)val_peek(1)));

    }
break;
case 26:
//#line 192 "parser.y"
{
    yyval  = new ASTNoLstVariables(getYYline()+1);
    ((ASTNoLstVariables)yyval).setIdName(new ASTNoId((String)val_peek(2),getYYline()+1));	
    ((ASTNoLstVariables)yyval).setExpr((ASTNoExpr)val_peek(0));
    getSymbolTab().set((String)val_peek(2));	
    }
break;
case 27:
//#line 202 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    }
break;
case 28:
//#line 207 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setLstCout((ASTNoLstCout)val_peek(2));
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 29:
//#line 214 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setString((String)val_peek(0));
    
    }
break;
case 30:
//#line 220 "parser.y"
{
         yyval  = new ASTNoLstCout((Integer)getYYline()+1);
        ((ASTNoLstCout)yyval).setExpr((ASTNoExpr)val_peek(0));
    
    }
break;
case 31:
//#line 225 "parser.y"
{
        yyval  = new ASTNoLstCout(getYYline()+1);
        ((ASTNoLstCout)yyval).setString((String)val_peek(1));
    
    }
break;
case 32:
//#line 233 "parser.y"
{
                yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('<');
	}
break;
case 33:
//#line 239 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('>');
	}
break;
case 34:
//#line 245 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
	}
break;
case 35:
//#line 251 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('*');
	}
break;
case 36:
//#line 257 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('/');
	}
break;
case 37:
//#line 263 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('+');
	}
break;
case 38:
//#line 269 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('%');
	}
break;
case 39:
//#line 275 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"&&");
	}
break;
case 40:
//#line 281 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
		((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(2));
		((ASTNoExpr)yyval).setRight((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperatorLogicLogic((String)"||");
	}
break;
case 41:
//#line 288 "parser.y"
{
		yyval  = new ASTNoId((Integer)getYYline()+1);
                 ((ASTNoId)yyval).setName((String)val_peek(0));
		
	}
break;
case 42:
//#line 293 "parser.y"
{
            yyval  = new ASTNoVet((Integer)getYYline()+1);
            ((ASTNoVet)yyval).setName((String)val_peek(3));
            ((ASTNoVet)yyval).setVetIdName((String)val_peek(1));
	}
break;
case 43:
//#line 299 "parser.y"
{
		yyval  = new ASTNoConst((Integer)getYYline()+1);
		((ASTNoConst)yyval).setValue((Integer)val_peek(0));
	}
break;
case 44:
//#line 304 "parser.y"
{
        yyval  = new ASTNoFloat((Integer)getYYline()+1);
	     ((ASTNoFloat)yyval).setValue((Float)val_peek(0));
         
        }
break;
case 45:
//#line 309 "parser.y"
{
        yyval  = new ASTNoString(getYYline()+1);
        ((ASTNoString)yyval).setValueString((String)val_peek(0));
        }
break;
case 46:
//#line 314 "parser.y"
{
		yyval = val_peek(1);
	}
break;
case 47:
//#line 317 "parser.y"
{
		yyval = val_peek(0);
	}
break;
case 48:
//#line 320 "parser.y"
{
		yyval  = new ASTNoExpr((Integer)getYYline()+1);
                ((ASTNoExpr)yyval).setLeft((ASTNo)val_peek(0));
		((ASTNoExpr)yyval).setOperator('-');
		
	}
break;
//#line 958 "Parser.java"
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
