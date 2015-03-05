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






//#line 1 "Proyectic.y"

import java.io.*;
//#line 20 "Parser.java"




public class Parser
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
final static int YYSTACKSIZE = 999;  //maximum stack size
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
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short REAL=258;
public final static short ENTERO=259;
public final static short CADENA=260;
public final static short ID=261;
public final static short CARACTER=262;
public final static short INT=263;
public final static short FLOAT=264;
public final static short CHAR=265;
public final static short DOUBLE=266;
public final static short VOID=267;
public final static short FOR=268;
public final static short WHILE=269;
public final static short ELSE=270;
public final static short CASE=271;
public final static short SWITCH=272;
public final static short BREAK=273;
public final static short DO=274;
public final static short DEFAULT=275;
public final static short SCAN=276;
public final static short PRINT=277;
public final static short RETURN=278;
public final static short STRUCT=279;
public final static short IF=280;
public final static short NEG=292;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    6,    6,    7,    7,
    5,    5,    5,    5,    5,    5,    8,    8,    8,    9,
    9,    4,   10,   10,   12,   12,   13,   13,   11,   14,
   14,   15,   15,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   17,   17,   18,   28,   28,   19,
   20,   20,   21,   29,   29,   31,   30,   30,   22,   23,
   32,   32,   25,   26,   24,   24,   27,   27,   34,   34,
   34,   34,   34,   34,   33,   33,   35,   35,   35,   38,
   38,   36,   36,   36,   36,   36,   36,   37,   37,   39,
   39,   40,   40,   41,   41,   41,   42,   42,   42,   42,
   42,   42,   42,   42,   42,   42,   43,   44,   44,   45,
   45,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    1,    4,
    1,    1,    1,    1,    1,    1,    5,    4,    2,    2,
    1,    6,    1,    1,    3,    1,    4,    2,    4,    2,
    0,    2,    0,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    2,    0,    6,    2,    1,    5,
    6,    1,    8,    2,    1,    5,    4,    0,    9,    2,
    2,    2,    5,    5,    2,    3,    3,    1,    1,    1,
    1,    1,    1,    1,    1,    4,    3,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    3,    1,    1,
    1,    3,    1,    1,    1,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    2,    2,    4,    1,    0,    3,
    1,
};
final static short yydefred[] = {                         0,
   11,   12,   14,   13,   15,    0,    0,    0,    3,    4,
    5,    0,   16,    0,    0,    2,    0,    0,    8,    0,
   21,    0,    0,    0,    0,    6,    0,    0,    0,   18,
   20,    0,    0,    0,    0,   26,    0,    7,   17,    0,
    0,    0,   10,    0,   31,   22,   25,   27,    0,   30,
    0,  100,  103,    0,  102,  101,  104,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   29,
   52,   42,   32,   34,   35,   36,   37,   38,   39,   40,
   41,   43,   44,    0,    0,    0,    0,    0,   93,   98,
    0,    0,    0,    0,    0,   60,    0,    0,    0,   65,
    0,    0,  106,  105,    0,   45,   69,   70,   71,   73,
   72,   74,    0,   81,   80,   86,   87,   84,   82,   85,
   83,    0,    0,   90,   91,    0,   94,   95,   96,    0,
  111,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   66,    0,   99,   67,   97,    0,    0,    0,   92,  107,
    0,   76,    0,    0,    0,    0,    0,    0,    0,  110,
    0,   50,    0,    0,   64,   63,    0,    0,    0,    0,
   55,   51,    0,   49,   47,    0,    0,    0,    0,    0,
   54,   48,   61,   62,    0,    0,    0,   53,   59,    0,
    0,   56,   57,
};
final static short yydgoto[] = {                          7,
    8,    9,   10,   11,   22,   18,   19,   13,   23,   34,
   72,   35,   36,   49,   51,   73,   74,   75,   76,   77,
   78,   79,   80,   81,   82,   83,   84,  175,  170,  180,
  171,  177,   85,  113,   86,  122,   87,  123,  126,   88,
  130,   89,   90,  132,  133,
};
final static short yysindex[] = {                       -85,
    0,    0,    0,    0,    0,  -97,    0,  -85,    0,    0,
    0, -220,    0,  -71,  -85,    0,  -19,    2,    0,  -85,
    0, -202, -105,  -80, -180,    0, -202,  -62,  -18,    0,
    0,    0, -179,   47,   46,    0,    6,    0,    0,   12,
  -14,  -85,    0,   21,    0,    0,    0,    0,  -85,    0,
   88,    0,    0,  -13,    0,    0,    0,   65,   79,   82,
   70,  110,   90,   92,  155,   94,   10,   10,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   81,  -43,  -59,   -7,   69,    0,    0,
   10,   10,   10,   10, -123,    0, -128, -119,   10,    0,
   85,   10,    0,    0,  104,    0,    0,    0,    0,    0,
    0,    0,   10,    0,    0,    0,    0,    0,    0,    0,
    0,   10,   10,    0,    0,   10,    0,    0,    0,   10,
    0,  107,  105,   64,   95,  122,  125,  127,  129,  130,
    0,  134,    0,    0,    0,   -7,   -7,   69,    0,    0,
   10,    0,   10,  110,   45,   10,  114,  117,  110,    0,
  131,    0,  -82,  151,    0,    0,  -47,  -68,  -63, -207,
    0,    0,  110,    0,    0, -186,  156,  140,  148,   83,
    0,    0,    0,    0,  110,  110,  110,    0,    0,  -66,
  -66,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  209,    0,    0,
    0,    0,    0,  -51,    0,    0,    3,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,
    0,  -39,    0,    0,  171,    0,    0,    0,    0,  -11,
    0,    0,    0,    0,    0,    0,    0,    0,  132,    0,
    0,    0,    0,  -37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -28,  266,   -2,   42,    0,    0,
  174,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  175,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,   33,   53,    0,    0,
    0,    0,    0, -114,    0,    0,    0,    0,  -52,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   96,
    0,    0, -114,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -114,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  211,   61,    0,   29,    0,  193,    0,  204,    0,
  184,    0,  192,    0,    0,  -34,    0,    0,    0,    0,
    0,    0,  -73,    0,    0,    0,  355,    0,    0,    0,
   56,    0,    1,    0,    0,    0,   14,    0,    0,  109,
    0,  106,    0,    0,    0,
};
final static int YYTABLESIZE=511;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         75,
  118,   24,  120,   75,   75,   75,   75,   75,   97,   75,
   46,  174,   97,   97,   97,   97,   97,  107,   97,   30,
   24,   75,   75,   75,   75,   15,   91,   97,   12,   28,
   97,   97,   28,   97,   79,  124,   12,  125,   79,   79,
   17,   79,   68,   77,   79,   27,    9,   77,   77,   69,
   77,   20,   33,   77,   67,   75,   79,   79,   29,   79,
   26,    9,   39,  169,   97,   77,   77,  179,   77,   78,
   33,   25,   25,   78,   78,   21,   78,   92,   37,   78,
   21,   40,   89,   31,   89,   89,   89,   41,   31,   42,
   79,   78,   78,   88,   78,   88,   88,   88,   43,   77,
   89,   89,   44,   89,   93,  129,  183,  184,   45,   50,
  127,   88,   88,   48,   88,  128,  192,  193,   94,  162,
   68,   95,  145,  145,  167,   78,  145,   69,   96,   98,
  145,   99,   67,  102,   89,  146,  147,  137,  182,  106,
  138,  139,   68,  141,  143,   88,   71,  150,  151,   69,
  189,  190,  191,  153,   67,   46,  152,    1,    2,    3,
    4,    5,  154,   14,   33,  155,  156,  163,   71,  157,
  158,   33,  165,    6,  159,  166,   33,    1,    2,    3,
    4,    5,    1,    2,    3,    4,   32,   68,  169,  168,
   33,  172,  176,    6,   69,  178,  185,  186,    6,   67,
    1,    2,    3,    4,    5,  187,   61,  188,    1,   19,
   45,   23,   70,  100,  109,  108,    6,   46,   16,   38,
   58,   15,  173,   28,   46,  181,  114,  115,  116,  117,
  119,  121,   45,   47,  148,  149,    0,  108,  109,  110,
  111,  112,    0,   75,   75,   75,   75,   75,   75,   75,
   75,   75,   75,   75,   33,    0,   33,   97,   97,   97,
   97,   97,   97,    0,    0,    0,    0,    0,   52,   53,
   54,   55,    0,   56,    0,   57,    0,    0,    0,    0,
    0,    0,    0,   79,   79,   79,   79,   79,   79,    0,
    0,    0,   77,   77,   77,   77,   77,   77,    0,    0,
    0,    0,   68,    0,    0,    0,   68,   68,   68,   68,
   68,    0,   68,    0,    0,    0,    0,    0,   78,   78,
   78,   78,   78,   78,   68,    0,    0,   89,   89,   89,
   89,   89,   89,    0,    0,    0,    0,    0,   88,   88,
   88,   88,   88,   88,    0,    0,   52,   53,   54,   55,
    0,   56,    0,   57,    0,   58,   59,    0,   68,   60,
   61,   62,    0,   63,   64,   65,    0,   66,   52,   53,
   54,   55,    0,   56,    0,   57,    0,   58,   59,    0,
    0,   60,   61,   62,    0,   63,   64,   65,    0,   66,
   33,   33,   33,   33,    0,    0,    0,    0,    0,   33,
   33,    0,    0,   33,   33,   33,    0,   33,   33,   33,
    0,   33,    0,   52,   53,   54,   55,    0,   56,  101,
   57,  103,  104,  105,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  131,  134,  135,  136,    0,
    0,    0,    0,  140,    0,    0,  142,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  144,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  160,    0,  161,    0,    0,
  164,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   60,   41,   62,   41,   42,   43,   44,   45,   37,   47,
  125,   59,   41,   42,   43,   44,   45,   61,   47,  125,
   40,   59,   60,   61,   62,  123,   40,   62,    0,   41,
   59,   60,   44,   62,   37,   43,    8,   45,   41,   42,
  261,   44,   33,   37,   47,   44,   44,   41,   42,   40,
   44,  123,   24,   47,   45,   93,   59,   60,  261,   62,
   59,   59,  125,  271,   93,   59,   60,  275,   62,   37,
   42,   91,   91,   41,   42,   15,   44,   91,  259,   47,
   20,  261,   41,   23,   43,   44,   45,   41,   28,   44,
   93,   59,   60,   41,   62,   43,   44,   45,   93,   93,
   59,   60,   91,   62,   40,   37,  293,  294,  123,   49,
   42,   59,   60,   93,   62,   47,  190,  191,   40,  154,
   33,   40,  122,  123,  159,   93,  126,   40,   59,   40,
  130,   40,   45,   40,   93,  122,  123,  261,  173,   59,
  269,  261,   33,   59,   41,   93,   59,   41,   44,   40,
  185,  186,  187,   59,   45,  270,   93,  263,  264,  265,
  266,  267,   41,  261,   33,   41,   40,  123,   59,   41,
   41,   40,   59,  279,   41,   59,   45,  263,  264,  265,
  266,  267,  263,  264,  265,  266,  267,   33,  271,   59,
   59,   41,  261,  279,   40,  259,   41,   58,  279,   45,
  263,  264,  265,  266,  267,   58,  273,  125,    0,  261,
  123,   41,  125,   59,   41,   41,  279,  270,    8,   27,
  125,  261,  270,   20,   41,  170,  286,  287,  288,  289,
  290,  291,  123,   42,  126,  130,   -1,  281,  282,  283,
  284,  285,   -1,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  291,  123,   -1,  125,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,   -1,   -1,  259,  260,
  261,  262,   -1,  264,   -1,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   59,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,  259,  260,  261,  262,
   -1,  264,   -1,  266,   -1,  268,  269,   -1,   93,  272,
  273,  274,   -1,  276,  277,  278,   -1,  280,  259,  260,
  261,  262,   -1,  264,   -1,  266,   -1,  268,  269,   -1,
   -1,  272,  273,  274,   -1,  276,  277,  278,   -1,  280,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,
  269,   -1,   -1,  272,  273,  274,   -1,  276,  277,  278,
   -1,  280,   -1,  259,  260,  261,  262,   -1,  264,   65,
  266,   67,   68,   69,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   92,   93,   94,   -1,
   -1,   -1,   -1,   99,   -1,   -1,  102,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  113,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  151,   -1,  153,   -1,   -1,
  156,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=294;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
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
null,null,null,null,null,null,null,null,null,"NL","REAL","ENTERO","CADENA","ID",
"CARACTER","INT","FLOAT","CHAR","DOUBLE","VOID","FOR","WHILE","ELSE","CASE",
"SWITCH","BREAK","DO","DEFAULT","SCAN","PRINT","RETURN","STRUCT","IF","\"+=\"",
"\"-=\"","\"/=\"","\"*=\"","\"%=\"","\"||\"","\"&&\"","\"==\"","\"!=\"",
"\"<=\"","\">=\"","NEG","\"++\"","\"--\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_declaraciones",
"lista_declaraciones : lista_declaraciones declaracion",
"lista_declaraciones : declaracion",
"declaracion : declaracion_variables",
"declaracion : declaracion_funcion",
"declaracion_variables : tipo lista_variables ';'",
"lista_variables : lista_variables ',' lista",
"lista_variables : lista",
"lista : ID",
"lista : ID '[' ENTERO ']'",
"tipo : INT",
"tipo : FLOAT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : VOID",
"tipo : tipo_struct",
"tipo_struct : STRUCT ID '{' cuerpo_struct '}'",
"tipo_struct : STRUCT '{' cuerpo_struct '}'",
"tipo_struct : STRUCT ID",
"cuerpo_struct : cuerpo_struct declaracion_variables",
"cuerpo_struct : declaracion_variables",
"declaracion_funcion : tipo ID '(' parametros ')' bloque",
"parametros : lista_parametros",
"parametros : VOID",
"lista_parametros : lista_parametros ',' parametro",
"lista_parametros : parametro",
"parametro : tipo ID '[' ']'",
"parametro : tipo ID",
"bloque : '{' declaraciones_locales lista_sentencias '}'",
"declaraciones_locales : declaraciones_locales declaracion_variables",
"declaraciones_locales :",
"lista_sentencias : lista_sentencias sentencia",
"lista_sentencias :",
"sentencia : sentencia_exp",
"sentencia : sentencia_if",
"sentencia : sentencia_while",
"sentencia : sentencia_do",
"sentencia : sentencia_switch",
"sentencia : sentencia_for",
"sentencia : sentencia_break",
"sentencia : sentencia_return",
"sentencia : bloque",
"sentencia : sentencia_imprime",
"sentencia : sentencia_lee",
"sentencia_exp : expresion ';'",
"sentencia_exp :",
"sentencia_if : IF '(' expresion ')' sentencia sentencia_else",
"sentencia_else : ELSE sentencia",
"sentencia_else : ';'",
"sentencia_while : WHILE '(' expresion ')' sentencia",
"sentencia_do : DO sentencia WHILE '(' expresion ')'",
"sentencia_do : ';'",
"sentencia_switch : SWITCH '(' ID ')' '{' lista_casos case_default '}'",
"lista_casos : lista_casos sentencia_case",
"lista_casos : sentencia_case",
"sentencia_case : CASE ENTERO ':' sentencia sentencia_break",
"case_default : DEFAULT ':' sentencia sentencia_break",
"case_default :",
"sentencia_for : FOR '(' expresion ';' expresion ';' sentencia_incremento ')' sentencia",
"sentencia_break : BREAK ';'",
"sentencia_incremento : ID \"++\"",
"sentencia_incremento : ID \"--\"",
"sentencia_imprime : PRINT '(' expresion ')' ';'",
"sentencia_lee : SCAN '(' ID ')' ';'",
"sentencia_return : RETURN ';'",
"sentencia_return : RETURN expresion ';'",
"expresion : variable operador_asignacion expresion",
"expresion : expresion_simple",
"operador_asignacion : '='",
"operador_asignacion : \"+=\"",
"operador_asignacion : \"-=\"",
"operador_asignacion : \"*=\"",
"operador_asignacion : \"/=\"",
"operador_asignacion : \"%=\"",
"variable : ID",
"variable : ID '[' expresion ']'",
"expresion_simple : expresion_simple operador_relacional operando",
"expresion_simple : expresion_simple operador_logico operando",
"expresion_simple : operando",
"operador_logico : \"&&\"",
"operador_logico : \"||\"",
"operador_relacional : \"<=\"",
"operador_relacional : \">=\"",
"operador_relacional : '<'",
"operador_relacional : '>'",
"operador_relacional : \"==\"",
"operador_relacional : \"!=\"",
"operando : operando operador_adicion termino",
"operando : termino",
"operador_adicion : '+'",
"operador_adicion : '-'",
"termino : termino operador_mul factor",
"termino : factor",
"operador_mul : '*'",
"operador_mul : '/'",
"operador_mul : '%'",
"factor : variable",
"factor : llamada",
"factor : '(' expresion ')'",
"factor : ENTERO",
"factor : FLOAT",
"factor : CARACTER",
"factor : CADENA",
"factor : DOUBLE",
"factor : '!' expresion",
"factor : '-' expresion",
"llamada : ID '(' argumentos ')'",
"argumentos : lista_argumentos",
"argumentos :",
"lista_argumentos : lista_argumentos ',' expresion",
"lista_argumentos : expresion",
};

//#line 192 "Proyectic.y"


/* A reference to the lexer object */
private Yylex lexer;

/* Interface to the lexer */
private int yylex(){
	int yyl_return = -1;
	try{
		yyl_return = lexer.yylex();
		//System.out.println("Token Leido" +yyl_return);
	}catch(IOException e){
		System.err.println("IO  error: " +e);
	}
	return yyl_return;
}

/* Error reporting */
public void yyerror(String error){
	System.err.println("Error: "+ error);
}


/* Lexer is created in the constructor */
public Parser(Reader r){
	lexer = new Yylex(r, this);
	yydebug= true;

}


public static void main(String args[]){
	Parser yyparser = null;
	try{
		yyparser = new Parser(new FileReader(args[0]));
	}catch(Exception e){
		System.out.println("Error");
		e.printStackTrace();
	}
	//Método que realiza el proceso de analisis
	yyparser.yyparse();

}

//#line 525 "Parser.java"
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
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
