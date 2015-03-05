import java.io.*;

%%
/* ---------------------- Sección de Directivas ------------------------------*/

%byaccj
%line
%char
%public

%state COMENTARIO

%eofval{

	// Al llegar al fin de archivo validamos que no se haya quedado un comentario
	// de varias lineas sin cerrar
	if(coment){
		System.out.println("ERROR LEXICO: no se ha cerrado un comentario");
		coment = false;
	}
	System.out.println("Análisis Léxico completo");
    return YYEOF;

	

%eofval}

%{
	boolean coment = false;	
	
        /* Metodos que ayudan a procesar el retorno de los tokens al
	   metodo next_token*/

	/*private Symbol tokenNuevo(int type, Object lex){
		int linea = yyline + 1;
		System.out.println("token : "+ lex + "," + type);
                return new Symbol(type,yyline, yycolumn, lex);
	}
	
	private Symbol tokenNuevo(int type){
                return tokenNuevpo(type, yytext());
	}*/

	private Parser yyparser;
	
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}
%}

%yyeof

CADENA = \"[^\n\"]+\"
CARACTER="'"[A-Za-z]"'"
INT="int"
FLOAT="float"
CHAR="char"
DOUBLE="double"
VOID="void"
FOR="for"
WHILE="while"
IF="if"
ELSE="else"
CASE="case"
SWITCH="switch"
BREAK="break"
DO="do"
DEFAULT="default"
SCAN="scan"
PRINT="print"
RETURN="return"
STRUCT="struct"
ID=[A-Za-z_]+[0-9]*
ENTERO=[0-9]+
REAL = {ENTERO}("."{ENTERO})?(E[+-]{ENTERO})?f
DOBLE = {ENTERO}("."{ENTERO})(E[+-]{ENTERO})?
NL  = \n | \r | \r\n 
espacio = [\t\r ]

//op. relacionales
MENIGU = "<="
MAYIGU = ">="
IGUAL = "=="
DIF = "!="

//op. logicos
AND = "&&"
OR = "||"

//op. de asignacion
MASIGUAL = "+="
MENOSIGUAL = "-="
MULTIGUAL = "*="
DIVIGUAL = "/="
MODUIGUAL = "%="

//op. de incremento/decremento
INC = "++"
DEC = "--"

%%
/* ------------------Sección de Expresiones Regulares ------------------------*/

<YYINITIAL>{

//inicio de un comentario de una linea
"//".*  {System.out.println("Encontro un comentario");}
//operadores aritmeticos

"+"|"-"|"*"|"/"|"%" {return (int) yycharat(0);}

//op. relacionales
">"|"<" 	{return (int) yycharat(0);}
{MENIGU} 	{return Parser.MENIGU;}
{MAYIGU} 	{return Parser.MAYIGU;}
{IGUAL} 	{return Parser.IGUAL;}
{DIF} 		{return Parser.DIF;}

//op. logicos
{OR}	{return Parser.OR;}
{AND}	{return Parser.AND;}
 "!" 	{return (int) yycharat(0);}

//op. de asignacion
"="				{return (int) yycharat(0);}
{MASIGUAL}		{return Parser.MASIGUAL;}
{MENOSIGUAL} 	{return Parser.MENOSIGUAL;}
{MULTIGUAL}		{return Parser.MULTIGUAL;}
{DIVIGUAL}		{return Parser.DIVIGUAL;}
{MODUIGUAL}		{return Parser.MODUIGUAL;}

//op. de incremento/decremento
{INC}		{return Parser.INC;}		
{DEC}		{return Parser.DEC;}

//delimitadores
"("|")"|"["|"]"|";"|":"|"\""|","|"’"|"{"|"}"  {return (int) yycharat(0);}

{CADENA} {  yyparser.yylval = new ParserVal(yytext());return Parser.CADENA;}
{CARACTER} { yyparser.yylval = new ParserVal(yytext()); return Parser.CARACTER;}
{INT} {return Parser.INT;}
{FLOAT} {return Parser.FLOAT;}
{CHAR} {return Parser.CHAR;}
{DOUBLE} {return Parser.DOUBLE;}
{VOID} {return Parser.VOID;}
{FOR} {return Parser.FOR;}
{WHILE} {return Parser.WHILE;}
{IF} {return Parser.IF;}
{ELSE} {return Parser.ELSE;}
{CASE} {return Parser.CASE;}
{SWITCH} {return Parser.SWITCH;}
{BREAK} {return Parser.BREAK;}
{DO} {return Parser.DO;}
{DEFAULT} {return Parser.DEFAULT;}
{SCAN} {return Parser.SCAN;}
{PRINT} {return Parser.PRINT;}
{RETURN} {return Parser.RETURN;}
{STRUCT} {return Parser.STRUCT;}
{ID} { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;}
{ENTERO} {yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); return Parser.ENTERO;}
{DOBLE} {yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.DOBLE;}
{REAL} {yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.REAL;}
{NL} {}
{espacio} {}

//inicio de un comentario
"/*"			{yybegin(COMENTARIO); coment= true;}
. {}
}
<YYINITIAL, COMENTARIO> {espacio}+ {}
<YYINITIAL, COMENTARIO> "\n"+     {}

<COMENTARIO>{
[^/*]		{}
[/*]		{}
"*/"		{System.out.println("Encontro un comentario"); yybegin(YYINITIAL); coment=false;}
}
