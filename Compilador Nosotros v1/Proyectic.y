%{
import java.io.*;
%}

%token NL          
%token <dval> REAL 
%token <ival> ENTERO 
%token <sval> CADENA
%token <sval> ID
%token <sval> CARACTER
%token <ival> INT
%token <dval> FLOAT 
%token <sval> CHAR
%token <dval> DOUBLE
%token VOID
%token FOR
%token WHILE
%token ID
%token ELSE
%token CASE
%token SWITCH
%token BREAK
%token DO
%token DEFAULT
%token SCAN
%token PRINT
%token RETURN
%token STRUCT
%token IF


%left '=' "+=" "-=" "/=" "*=" "%="
%left "||" 
%left "&&"
%left "==" "!="
%left '<' "<=" '>' ">="
%left '+' '-'
%left '*' '/' '%'
%left NEG  
%right "++" "--" "!"
%left '(' ')' '[' ']' '{' '}'

%start programa

%%

programa: lista_declaraciones;

lista_declaraciones: lista_declaraciones declaracion | declaracion ;

declaracion: declaracion_variables | declaracion_funcion ;

declaracion_variables: tipo lista_variables ';' ;

lista_variables: lista_variables ',' lista | lista ;

lista: ID | ID '[' ENTERO ']';

tipo: INT 
	 |FLOAT 
	 |DOUBLE
	 |CHAR
 	 |VOID 
 	 |tipo_struct;

tipo_struct: STRUCT ID '{' cuerpo_struct '}' 
		|STRUCT '{' cuerpo_struct '}'
		|STRUCT ID;

cuerpo_struct: cuerpo_struct declaracion_variables 
		|declaracion_variables;

declaracion_funcion: tipo ID '(' parametros ')' bloque;

parametros: lista_parametros 
		|VOID;

lista_parametros: lista_parametros ',' parametro
		|parametro;

parametro: tipo ID '[' ']' 
		|tipo ID;

bloque: '{' declaraciones_locales lista_sentencias '}' ;

declaraciones_locales: declaraciones_locales declaracion_variables
			| ;

lista_sentencias: lista_sentencias sentencia
			| ;

sentencia: sentencia_exp
		| sentencia_if 
		| sentencia_while 
		| sentencia_do 
		| sentencia_switch
		| sentencia_for 
		| sentencia_break 
		| sentencia_return 
		| bloque 
		| sentencia_imprime 
		| sentencia_lee ;

sentencia_exp: expresion ';' 
			| ;

sentencia_if: IF '(' expresion ')' sentencia sentencia_else ;

sentencia_else: ELSE sentencia
		| ';' ;

sentencia_while: WHILE '(' expresion ')' sentencia ;

sentencia_do: DO sentencia WHILE '(' expresion ')'
		|';' ;

sentencia_switch: SWITCH '(' ID ')'  '{' lista_casos case_default '}';

lista_casos: lista_casos sentencia_case 
		| sentencia_case ;

sentencia_case: CASE ENTERO ':' sentencia sentencia_break ;

case_default: DEFAULT ':' sentencia sentencia_break | ;

sentencia_for: FOR '(' expresion ';' expresion ';' sentencia_incremento ')' sentencia;

sentencia_break: BREAK ';' ;

sentencia_incremento: ID '++' | ID '--';

sentencia_imprime: PRINT '(' expresion ')'';' ;

sentencia_lee: SCAN '(' ID ')'';' ;

sentencia_return: RETURN ';' | RETURN expresion ';' ;

expresion: variable operador_asignacion expresion | expresion_simple;

operador_asignacion: '='
		| "+=" 
		| "-=" 
		| "*="
		| "/="
		| "%=";

variable: ID 
	  | ID '[' expresion ']' ;

expresion_simple: expresion_simple operador_relacional operando 
		| expresion_simple operador_logico operando
		| operando ;

operador_logico: "&&"
		|"||" ;

operador_relacional: "<=" 
			| ">="
			| "<" 
			| ">" 
			| "==" 
			| "!=" ;

operando: operando operador_adicion termino  | termino;

operador_adicion: '+' | '-';

termino: termino operador_mul factor | factor;

operador_mul: '*' 
		| '/' 
		| '%' ;

factor: variable 
	| llamada 
	| '(' expresion ')' 
	| ENTERO 
	| FLOAT
	| CARACTER 
	| CADENA
	| DOUBLE 
	| '!'expresion 
	| '-'expresion ;

llamada: ID '(' argumentos ')' ;

argumentos: lista_argumentos | ;

lista_argumentos: lista_argumentos ',' expresion | expresion ;


%%

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

