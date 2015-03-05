import java.io.*;

%%
/* ---------------------- Sección de Directivas ------------------------------*/

//%full
//%cup
%byaccj
%line
%char
%public
//%int

%state COMENTARIO

%eofval{

	// Al llegar al fin de archivo validamos que no se haya quedado un comentario
	// de varias lineas sin cerrar
	if(coment){
		System.out.println("ERROR LEXICO: no se ha cerrado un comentario");
		coment = false;
	}

	/*else{  
         System.out.println("--El Token leido es :"+yytext()); return YYEOF;
		System.out.println("--El Token leido es :"+yytext()); return tokenNuevpo(sym.EOF);
	}*/
	

%eofval}

%{
	boolean coment = false;	
	
        /* Metodos que ayudan a procesar el retorno de los tokens al
	   metodo next_token*/

	/*private Symbol tokenNuevo(int type, Object lex){
		int linea = yyline + 1;
		System.out.println("token : "+ lex + "," + type);
                System.out.println("--El Token leido es :"+yytext()); return new Symbol(type,yyline, yycolumn, lex);
	}
	
	private Symbol tokenNuevo(int type){
                System.out.println("--El Token leido es :"+yytext()); return tokenNuevpo(type, yytext());
	}*/

	private Parser yyparser;
	
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}
%}

//%yyeof

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
RETURN="System.out.println("--El Token leido es :"+yytext()); return"
STRUCT="struct"
ID=[A-Za-z_]+[0-9]*
ENTERO=[0-9]+
REAL = [0-9]* ("." [0-9]+)?
NL  = \n | \r | \r\n 
espacio = [\t\r\n ]

%%
/* ------------------Sección de Expresiones Regulares ------------------------*/

<YYINITIAL>{
"//".*              {System.out.println("Encontro un comentario");}
//operadores aritmeticos
"+"|"-"|"*"|"/"|"%" {System.out.println("--El Token leido es :"+yytext()); return (int) yycharat(0);}
//op. relacionales
">"|"<"|"<="|">="|"=="|"!=" {System.out.println("--El Token leido es :"+yytext()); return 20;}
//op. logicos
"||"|"&&"|"!" {System.out.println("--El Token leido es :"+yytext()); return 21;}
//op. de asignacion
'='|"+="|"-="|"*="|"/="|"%=" {System.out.println("--El Token leido es :"+yytext()); return 22;}
//op. de incremento
"++"|"--" {System.out.println("--El Token leido es :"+yytext()); return 23;}
//delimitadores
"("|")"|"["|"]"|";"|":"|","|"’"|"{"|"}"  {System.out.println("--El Token leido es :"+yytext()); return (int) yycharat(0);}

{CADENA} { System.out.println("--El Token leido es :"+yytext());  yyparser.yyval = new ParserVal(yytext());return Parser.CADENA;}
{CARACTER} {System.out.println("--El Token leido es :"+yytext());  yyparser.yyval = new ParserVal(yytext()); return Parser.CARACTER;}
{INT} {System.out.println("--El Token leido es :"+yytext()); return Parser.INT;}
{FLOAT} {System.out.println("--El Token leido es :"+yytext()); return Parser.FLOAT;}
{CHAR} {System.out.println("--El Token leido es :"+yytext()); return Parser.CHAR;}
{DOUBLE} {System.out.println("--El Token leido es :"+yytext()); return Parser.DOUBLE;}
{VOID} {System.out.println("--El Token leido es :"+yytext()); return Parser.VOID;}
{FOR} {System.out.println("--El Token leido es :"+yytext()); return Parser.FOR;}
{WHILE} {System.out.println("--El Token leido es :"+yytext()); return Parser.WHILE;}
{IF} {System.out.println("--El Token leido es :"+yytext()); return Parser.IF;}
{ELSE} {System.out.println("--El Token leido es :"+yytext()); return Parser.ELSE;}
{CASE} {System.out.println("--El Token leido es :"+yytext()); return Parser.CASE;}
{SWITCH} {System.out.println("--El Token leido es :"+yytext()); return Parser.SWITCH;}
{BREAK} {System.out.println("--El Token leido es :"+yytext()); return Parser.BREAK;}
{DO} {System.out.println("--El Token leido es :"+yytext()); return Parser.DO;}
{DEFAULT} {System.out.println("--El Token leido es :"+yytext()); return Parser.DEFAULT;}
{SCAN} {System.out.println("--El Token leido es :"+yytext()); return Parser.SCAN;}
{PRINT} {System.out.println("--El Token leido es :"+yytext()); return Parser.PRINT;}
{RETURN} {System.out.println("--El Token leido es :"+yytext()); return Parser.RETURN;}
{STRUCT} {System.out.println("--El Token leido es :"+yytext()); return Parser.STRUCT;}
{ID} { System.out.println("--El Token leido es :"+yytext());yyparser.yyval = new ParserVal(yytext()); return Parser.ID;}
{ENTERO} {yyparser.yyval = new ParserVal(Integer.parseInt(yytext())); System.out.println("--El Token leido es :"+yytext()); return Parser.ENTERO;}
{REAL} {System.out.println("--El Token leido es :"+yytext()); yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); System.out.println("--El Token leido es :"+yytext()); return Parser.REAL;}
{NL} {System.out.println("--El Token leido es :"+yytext()); return Parser.NL;}
"/*"			{yybegin(COMENTARIO); coment= true;}

}
<YYINITIAL, COMENTARIO> {espacio}+ {}
<YYINITIAL, COMENTARIO> "\n"+     {}

<COMENTARIO>{
[^/*]		{}
[/*]		{}
"*/"		{System.out.println("Encontro un comentario"); yybegin(YYINITIAL); coment=false;}
}
