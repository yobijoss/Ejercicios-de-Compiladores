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
import objetos.*;
//#line 21 "Parser.java"




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
public final static short REAL=257;
public final static short DOBLE=258;
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
public final static short MASIGUAL=281;
public final static short MENOSIGUAL=282;
public final static short DIVIGUAL=283;
public final static short MULTIGUAL=284;
public final static short MODUIGUAL=285;
public final static short OR=286;
public final static short AND=287;
public final static short IGUAL=288;
public final static short DIF=289;
public final static short MENIGU=290;
public final static short MAYIGU=291;
public final static short INC=292;
public final static short DEC=293;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    5,    5,    6,    6,    7,    9,    9,   10,   10,
    1,    1,    1,    1,    1,    1,   11,   11,   11,   12,
   12,   14,    8,    4,    4,    2,    2,    3,    3,   13,
   15,   15,   16,   16,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   18,   18,   19,   29,   29,
   20,   21,   22,   30,   30,   32,   31,   31,   23,   24,
   33,   33,   26,   27,   25,   25,   28,   28,   35,   35,
   35,   35,   35,   35,   34,   34,   36,   36,   36,   39,
   39,   37,   37,   37,   37,   37,   37,   38,   38,   40,
   40,   41,   41,   42,   42,   42,   43,   43,   43,   43,
   43,   43,   43,   43,   43,   43,   44,   45,   45,   46,
   46,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    1,    4,
    1,    1,    1,    1,    1,    1,    5,    4,    2,    2,
    1,    0,    7,    1,    1,    3,    1,    4,    2,    4,
    2,    0,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    2,    1,    6,    2,    0,
    5,    7,    8,    2,    1,    5,    4,    0,    9,    2,
    2,    2,    5,    5,    2,    3,    3,    1,    1,    1,
    1,    1,    1,    1,    1,    4,    3,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    3,    1,    1,
    1,    3,    1,    1,    1,    1,    1,    1,    3,    1,
    1,    1,    1,    1,    2,    2,    4,    1,    0,    3,
    1,
};
final static short yydefred[] = {                         0,
   11,   12,   14,   13,   15,    0,    0,    0,    0,    3,
    4,    5,   16,    0,    0,    0,    0,    8,    2,    0,
    0,   21,    0,    0,    0,    6,    0,    0,    0,   18,
   20,    0,    0,    0,   27,    0,    0,    7,   17,    0,
    0,   22,   10,    0,   26,    0,   28,   32,   23,    0,
   31,    0,  101,  104,  100,  103,    0,  102,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   30,   47,   43,   33,   35,   36,   37,   38,   39,   40,
   41,   42,   44,   45,    0,    0,    0,    0,    0,   93,
   98,    0,    0,    0,    0,    0,   60,    0,    0,    0,
   65,    0,    0,  106,  105,    0,   46,   69,   70,   71,
   73,   72,   74,    0,   81,   80,   86,   87,   84,   82,
   85,   83,    0,    0,   90,   91,    0,   94,   95,   96,
    0,  111,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,    0,   99,   67,   97,    0,    0,    0,   92,
  107,    0,   76,    0,    0,    0,    0,    0,    0,    0,
  110,    0,   51,    0,    0,   64,   63,    0,    0,    0,
    0,   55,    0,    0,   48,    0,    0,    0,    0,    0,
   54,   52,   49,   61,   62,    0,    0,    0,   53,   59,
    0,    0,   56,   57,
};
final static short yydgoto[] = {                          7,
   21,   34,   35,   36,    9,   10,   11,   12,   17,   18,
   13,   23,   73,   46,   50,   52,   74,   75,   76,   77,
   78,   79,   80,   81,   82,   83,   84,   85,  175,  171,
  180,  172,  177,   86,  114,   87,  123,   88,  124,  127,
   89,  131,   90,   91,  133,  134,
};
final static short yysindex[] = {                      -156,
    0,    0,    0,    0,    0, -111,    0, -214, -156,    0,
    0,    0,    0,  -87, -156,  -20,  -41,    0,    0, -156,
 -206,    0, -123,  -47, -183,    0, -206, -104,    8,    0,
    0,    0, -147,   72,    0,   76,   25,    0,    0,   29,
 -156,    0,    0,   32,    0,    6,    0,    0,    0, -156,
    0,   88,    0,    0,    0,    0,  -12,    0,   90,   91,
   94,   77,  169,   97,   98,   19,   99,  193,  193,  193,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   89,  -60,  -19,   39,   26,    0,
    0,  193,  193,  193,  193, -112,    0, -118, -108,  193,
    0,   96,  193,    0,    0,  117,    0,    0,    0,    0,
    0,    0,    0,  193,    0,    0,    0,    0,    0,    0,
    0,    0,  193,  193,    0,    0,  193,    0,    0,    0,
  193,    0,  124,  122,   74,  109,  129,  131,  133,  135,
  136,    0,  137,    0,    0,    0,   39,   39,   26,    0,
    0,  193,    0,  193,  169,   56,  193,  121,  123,  169,
    0,  126,    0,  -84,  152,    0,    0,  -72,  -62,  -58,
 -225,    0,  144,  169,    0, -266,  163,  149,  150,   80,
    0,    0,    0,    0,    0,  169,  169,  169,    0,    0,
  -63,  -63,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,  212,    0,
    0,    0,    0,  -46,    0,   -6,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   -6,    0,
    0,  -30,    0,  186,    0,    0,    0,    0,    0,   62,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  112,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -28,  147,   -2,   42,    0,
    0,  188,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  189,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    7,   33,   53,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  141,    0,    0,
  111,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   81,    0,  198,    0,    0,  225,  104,    0,    0,  214,
    0,  222,  197,    0,    0,    0,    9,    0,    0,    0,
    0,    0,    0, -130,    0,    0,    0,  366,    0,    0,
    0,   84,    0,  -94,    0,    0,    0,  -35,    0,    0,
  130,    0,  125,    0,    0,    0,
};
final static int YYTABLESIZE=523;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         75,
  108,   30,   27,   75,   75,   75,   75,   75,   97,   75,
   25,   15,   97,   97,   97,   97,   97,   26,   97,   24,
   39,   75,   75,   75,   75,  184,  185,   92,  146,  146,
   97,   97,  146,   97,   79,   20,  146,    9,   79,   79,
  119,   79,  121,   77,   79,  170,   16,   77,   77,  179,
   77,   69,    9,   77,   29,   75,   79,   79,   70,   79,
  193,  194,  130,   68,   97,   77,   77,  128,   77,   78,
   25,   98,  129,   78,   78,   37,   78,  101,   93,   78,
    8,  125,   89,  126,   89,   89,   89,  147,  148,    8,
   79,   78,   78,   88,   78,   88,   88,   88,   25,   77,
   89,   89,   29,   89,   33,   29,    1,    2,    3,    4,
    5,   88,   88,   40,   88,   41,   42,   43,   22,   44,
   69,   33,    6,   22,   47,   78,   31,   70,   48,   94,
   95,   31,   68,   96,   89,   97,   99,  100,  103,    1,
    2,    3,    4,    5,   34,   88,   72,  107,  138,   14,
  139,   34,  140,   51,  142,    6,   34,  144,    1,    2,
    3,    4,    5,  163,  151,  152,  153,  154,  168,  155,
   34,  156,  157,   50,    6,  158,  159,  160,  164,  166,
   50,  167,  183,   68,  169,   50,  170,   68,   68,   68,
   68,   68,  173,   68,  190,  191,  192,  174,  176,   50,
  178,   69,  182,  186,  189,   68,  187,  188,   70,   62,
   48,    1,   71,   68,   19,    1,    2,    3,    4,   32,
  109,  110,  111,  112,  113,   69,   24,   72,  109,  108,
   15,    6,   70,   19,   34,   58,   34,   68,   45,   68,
   38,   28,   49,   75,   75,   75,   75,   75,   75,   75,
   75,   75,   75,   75,  181,  150,  149,   97,   97,   97,
   97,   97,   97,   50,    0,   50,  115,  116,  117,  118,
  120,  122,    0,    0,    0,   53,   54,   55,   56,   57,
   58,    0,    0,   79,   79,   79,   79,   79,   79,    0,
    0,   48,   77,   77,   77,   77,   77,   77,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   78,   78,
   78,   78,   78,   78,    0,    0,    0,   89,   89,   89,
   89,   89,   89,    0,    0,    0,    0,    0,   88,   88,
   88,   88,   88,   88,   53,   54,   55,   56,   57,   58,
    0,    0,    0,    0,    0,   59,   60,    0,    0,   61,
   62,   63,    0,   64,   65,   66,    0,   67,   34,   34,
   34,   34,   34,   34,    0,    0,    0,    0,    0,   34,
   34,    0,    0,   34,   34,   34,    0,   34,   34,   34,
    0,   34,    0,    0,    0,    0,    0,   50,   50,   50,
   50,   50,   50,    0,    0,    0,    0,    0,   50,   50,
    0,    0,   50,   50,   50,    0,   50,   50,   50,    0,
   50,    0,    0,    0,    0,   53,   54,   55,   56,   57,
   58,  102,    0,  104,  105,  106,   59,   60,    0,    0,
   61,   62,   63,    0,   64,   65,   66,    0,   67,   53,
   54,   55,   56,   57,   58,    0,    0,  132,  135,  136,
  137,    0,    0,    0,    0,  141,    0,    0,  143,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  145,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  161,    0,  162,
    0,    0,  165,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   61,  125,   44,   41,   42,   43,   44,   45,   37,   47,
   41,  123,   41,   42,   43,   44,   45,   59,   47,   40,
  125,   59,   60,   61,   62,  292,  293,   40,  123,  124,
   59,   60,  127,   62,   37,  123,  131,   44,   41,   42,
   60,   44,   62,   37,   47,  271,  261,   41,   42,  275,
   44,   33,   59,   47,  261,   93,   59,   60,   40,   62,
  191,  192,   37,   45,   93,   59,   60,   42,   62,   37,
   91,   63,   47,   41,   42,  259,   44,   59,   91,   47,
    0,   43,   41,   45,   43,   44,   45,  123,  124,    9,
   93,   59,   60,   41,   62,   43,   44,   45,   91,   93,
   59,   60,   41,   62,   24,   44,  263,  264,  265,  266,
  267,   59,   60,  261,   62,   44,   41,   93,   15,   91,
   33,   41,  279,   20,   93,   93,   23,   40,  123,   40,
   40,   28,   45,   40,   93,   59,   40,   40,   40,  263,
  264,  265,  266,  267,   33,   93,   59,   59,  261,  261,
  269,   40,  261,   50,   59,  279,   45,   41,  263,  264,
  265,  266,  267,  155,   41,   44,   93,   59,  160,   41,
   59,   41,   40,   33,  279,   41,   41,   41,  123,   59,
   40,   59,  174,   37,   59,   45,  271,   41,   42,   43,
   44,   45,   41,   47,  186,  187,  188,  270,  261,   59,
  259,   33,   59,   41,  125,   59,   58,   58,   40,  273,
  123,    0,  125,   45,  261,  263,  264,  265,  266,  267,
  281,  282,  283,  284,  285,   33,   41,   59,   41,   41,
  261,  279,   40,    9,  123,  125,  125,   45,   41,   93,
   27,   20,   46,  281,  282,  283,  284,  285,  286,  287,
  288,  289,  290,  291,  171,  131,  127,  286,  287,  288,
  289,  290,  291,  123,   -1,  125,  286,  287,  288,  289,
  290,  291,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,
   -1,  123,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,  268,  269,   -1,   -1,  272,
  273,  274,   -1,  276,  277,  278,   -1,  280,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,
  269,   -1,   -1,  272,  273,  274,   -1,  276,  277,  278,
   -1,  280,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,  269,
   -1,   -1,  272,  273,  274,   -1,  276,  277,  278,   -1,
  280,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   66,   -1,   68,   69,   70,  268,  269,   -1,   -1,
  272,  273,  274,   -1,  276,  277,  278,   -1,  280,  257,
  258,  259,  260,  261,  262,   -1,   -1,   92,   93,   94,
   95,   -1,   -1,   -1,   -1,  100,   -1,   -1,  103,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  114,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  152,   -1,  154,
   -1,   -1,  157,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=293;
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
null,null,null,null,null,null,null,null,null,"REAL","DOBLE","ENTERO","CADENA",
"ID","CARACTER","INT","FLOAT","CHAR","DOUBLE","VOID","FOR","WHILE","ELSE",
"CASE","SWITCH","BREAK","DO","DEFAULT","SCAN","PRINT","RETURN","STRUCT","IF",
"MASIGUAL","MENOSIGUAL","DIVIGUAL","MULTIGUAL","MODUIGUAL","OR","AND","IGUAL",
"DIF","MENIGU","MAYIGU","INC","DEC",
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
"$$1 :",
"declaracion_funcion : tipo ID '(' parametros ')' $$1 bloque",
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
"sentencia_exp : ';'",
"sentencia_if : IF '(' expresion ')' sentencia sentencia_else",
"sentencia_else : ELSE sentencia",
"sentencia_else :",
"sentencia_while : WHILE '(' expresion ')' sentencia",
"sentencia_do : DO sentencia WHILE '(' expresion ')' ';'",
"sentencia_switch : SWITCH '(' ID ')' '{' lista_casos case_default '}'",
"lista_casos : lista_casos sentencia_case",
"lista_casos : sentencia_case",
"sentencia_case : CASE ENTERO ':' sentencia sentencia_break",
"case_default : DEFAULT ':' sentencia sentencia_break",
"case_default :",
"sentencia_for : FOR '(' expresion ';' expresion ';' sentencia_incremento ')' sentencia",
"sentencia_break : BREAK ';'",
"sentencia_incremento : ID INC",
"sentencia_incremento : ID DEC",
"sentencia_imprime : PRINT '(' expresion ')' ';'",
"sentencia_lee : SCAN '(' ID ')' ';'",
"sentencia_return : RETURN ';'",
"sentencia_return : RETURN expresion ';'",
"expresion : variable operador_asignacion expresion",
"expresion : expresion_simple",
"operador_asignacion : '='",
"operador_asignacion : MASIGUAL",
"operador_asignacion : MENOSIGUAL",
"operador_asignacion : MULTIGUAL",
"operador_asignacion : DIVIGUAL",
"operador_asignacion : MODUIGUAL",
"variable : ID",
"variable : ID '[' expresion ']'",
"expresion_simple : expresion_simple operador_relacional operando",
"expresion_simple : expresion_simple operador_logico operando",
"expresion_simple : operando",
"operador_logico : AND",
"operador_logico : OR",
"operador_relacional : MENIGU",
"operador_relacional : MAYIGU",
"operador_relacional : '<'",
"operador_relacional : '>'",
"operador_relacional : IGUAL",
"operador_relacional : DIF",
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
"factor : REAL",
"factor : CARACTER",
"factor : CADENA",
"factor : DOBLE",
"factor : '!' expresion",
"factor : '-' expresion",
"llamada : ID '(' argumentos ')'",
"argumentos : lista_argumentos",
"argumentos :",
"lista_argumentos : lista_argumentos ',' expresion",
"lista_argumentos : expresion",
};

//#line 342 "Proyectic.y"


/* A reference to the lexer object */
private Yylex lexer;

/* Interface to the lexer */
private int yylex(){
	int yyl_return = -1;
	try{
		yyl_return = lexer.yylex();
		//System.out.println("Token Leido " + yyl_return);
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
}

/*Declaraciones felices*/

int cont_funcion = 0;
int cont_variable = 0;

int cont_param=0;
int cont_lista_param = 0;
int cont_simbolos=0;
int direccion_ = 0;

String currentId;

public ListaListasParametros lista_listas_parametros = new ListaListasParametros();
public ListaListasVariables lista_listas_variables = new ListaListasVariables();
public ListaTipos lista_tipos = new ListaTipos();
public static TablaSimbolos tabla_simbolos = new TablaSimbolos();
public int tipo_actual;

/*Declaracíon de variables globales a utilizar*/
static String func_actual;
static Tabla<String> tabla_funciones ;
static Tabla<String> tabla_variables ;

public static void main(String args[]){
	Parser yyparser = null;


	try{
		tabla_funciones = new Tabla<String>("Funciones");
		tabla_variables = new Tabla<String>("Variables");
		tabla_funciones.add("print");
		tabla_funciones.add("scan");
		yyparser = new Parser(new FileReader(args[0]));
			yyparser.yydebug = false;

	}catch(Exception e){
		System.out.println("Error");
		e.printStackTrace();
	}
	//Método que realiza el proceso de analisis
	yyparser.yyparse();

	tabla_funciones.printTable();
	tabla_variables.printTable();
	tabla_simbolos.imprime();
}

private void errorSem(String error){
	System.out.println("--------------------------");
	System.out.println("Error semántico");
	System.out.println(error);
	System.out.println("--------------------------");

}

private void p(String x){
	System.out.println(x);
}

//#line 583 "Parser.java"
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
case 1:
//#line 54 "Proyectic.y"
{
		System.out.println("Análisis sintáctico completo");
						
		if(!tabla_funciones.getLast().equals("main")){
			errorSem("Main no es la última función declarada");
		}
		}
break;
case 6:
//#line 70 "Proyectic.y"
{
						/* agregar a la lista de variables relacionada con la función*/

					}
break;
case 7:
//#line 77 "Proyectic.y"
{
						/*if(!tabla_variables.exists())*/
					}
break;
case 8:
//#line 81 "Proyectic.y"
{

					}
break;
case 9:
//#line 86 "Proyectic.y"
{
			/*obtener lista de parametros de la función y*/
			/* ver que el ID no se repita.*/
			/*func_actual es la función actual;*/
			tabla_variables.add(val_peek(0).sval);

		}
break;
case 10:
//#line 94 "Proyectic.y"
{
			tabla_variables.add(val_peek(3).sval);
		}
break;
case 11:
//#line 98 "Proyectic.y"
{yyval.obj = ListaTipos.getIdByTipo("int"); }
break;
case 12:
//#line 99 "Proyectic.y"
{yyval.obj = ListaTipos.getIdByTipo("float");}
break;
case 13:
//#line 100 "Proyectic.y"
{yyval.obj = ListaTipos.getIdByTipo("double");}
break;
case 14:
//#line 101 "Proyectic.y"
{yyval.obj = ListaTipos.getIdByTipo("char");}
break;
case 15:
//#line 102 "Proyectic.y"
{yyval.obj = ListaTipos.getIdByTipo("void");}
break;
case 16:
//#line 103 "Proyectic.y"
{yyval.obj = null;}
break;
case 22:
//#line 115 "Proyectic.y"
{
					 	int id_tipo = ((Tipo)val_peek(4).obj).id;
					 	func_actual = val_peek(3).sval;
					 	p("------- LA FUNCION ACTUAL ES "+func_actual);
					 	tabla_funciones.add(val_peek(3).sval);
					 	/*agregamos a la lista de listas de parametros*/
					 	ListaParametros temp = ((ListaParametros)val_peek(1).obj);
					 	lista_listas_parametros.add(temp);
					 	temp.imprimir();
					 	/*agregar a la tabla de funciones*/
					 	/* declaramos la función actual declarada.*/
					 	/*obtenemos el tipo de la fucnión para meterlo en tabla de simbolos*/
					 	/*num deparametros de la función*/
					 	cont_param = ((ListaParametros)val_peek(1).obj).tam;
					 	/* función que agrega la función a la lista de parametros*/
					 	/* con id de la lista de parametros y el número de parametros.*/
					 	/*si es 0 es voud */
					 	boolean agregar = tabla_simbolos.add(cont_simbolos,func_actual,id_tipo,cont_param,cont_lista_param,direccion_);
					 	direccion_++;/* aumentamos la direcció*/
					 }
break;
case 24:
//#line 139 "Proyectic.y"
{
				yyval.obj = ((ListaParametros)val_peek(0).obj);
			}
break;
case 25:
//#line 143 "Proyectic.y"
{
				yyval.obj = new ListaParametros(cont_lista_param);
				((ListaParametros)yyval.obj).tam = 0;
				cont_lista_param++;

			}
break;
case 26:
//#line 152 "Proyectic.y"
{
				((ListaParametros)yyval.obj).tam = ((ListaParametros)val_peek(2).obj).tam;
				((ListaParametros)yyval.obj).add((Parametro)val_peek(0).obj);

			}
break;
case 27:
//#line 158 "Proyectic.y"
{
				yyval.obj = new ListaParametros(cont_lista_param);
				((ListaParametros)yyval.obj).add((Parametro)val_peek(0).obj);
				((ListaParametros)yyval.obj).tam = 1;
			}
break;
case 28:
//#line 166 "Proyectic.y"
{
			tabla_variables.add(val_peek(2).sval);
			yyval.obj  = new Parametro(val_peek(2).sval,((Tipo)val_peek(3).obj).id,1);
			tabla_simbolos.add(cont_simbolos++,val_peek(2).sval,((Tipo)val_peek(3).obj).id,-1,-1,direccion_++);
		}
break;
case 29:
//#line 172 "Proyectic.y"
{
			tabla_variables.add(val_peek(0).sval);
			yyval.obj  = new Parametro(val_peek(0).sval,((Tipo)val_peek(1).obj).id,0);
			tabla_simbolos.add(cont_simbolos++,val_peek(0).sval,((Tipo)val_peek(1).obj).id,-1,-1,direccion_++);
		}
break;
case 75:
//#line 246 "Proyectic.y"
{
				p("La función donde está la variable "+val_peek(0).sval+" es: "+func_actual);
				if(!tabla_variables.exists(val_peek(0).sval)){
					errorSem("Variable no declarada :"+ val_peek(0).sval);
				}else{
					int x = tabla_simbolos.getParamsIdByVal(func_actual);
					if( x!= -1){
						/* checar si $1 es parametro o si es declaracion*/
						ListaParametros temp = lista_listas_parametros.getListaParamsbyId(x);
						if(temp.exists(val_peek(0).sval) || tabla_variables.exists(val_peek(0).sval)){
							p("Si es parametro o declaración, no hoy problema");
						}else{
							/*checar los parametros o variables en tabla de simbolos del metodo*/
							p("hay un error");
						}
					}else{
						p("El simbolo no tiene parametros");
					}

				}
			}
break;
case 76:
//#line 268 "Proyectic.y"
{
				if(!tabla_variables.exists(val_peek(3).sval)){
					errorSem("Variable no declarada :"+ val_peek(3).sval);
				}else{
					int x = tabla_simbolos.getParamsIdByVal(func_actual);
					if( x!= -1){
						/* checar si $1 es parametro o si es declaracion*/
						ListaParametros temp = lista_listas_parametros.getListaParamsbyId(x);
						if(temp.exists(val_peek(3).sval)){
							p("Si es parametro, no hoy problema");
						}
					}else{
						p("El simbolo no tiene parametros");
					}

				}
			}
break;
case 107:
//#line 325 "Proyectic.y"
{
				if(!tabla_funciones.exists(val_peek(3).sval)){
					errorSem(val_peek(3).sval +" Función no declarada.");
				}else{

				}

			}
break;
//#line 926 "Parser.java"
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
