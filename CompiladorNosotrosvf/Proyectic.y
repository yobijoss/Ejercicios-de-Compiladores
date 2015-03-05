%{
import java.io.*;
import objetos.*;
%}

//%token NL        
%token <dval> REAL 
%token <dval> DOBLE
%token <ival> ENTERO 
%token <sval> CADENA
%token <sval> ID
%token <sval> CARACTER
%token INT
%token FLOAT 
%token CHAR
%token DOUBLE
%token VOID
%token FOR
%token WHILE
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

//Precedencia

%right '=' MASIGUAL MENOSIGUAL DIVIGUAL MULTIGUAL MODUIGUAL
%left OR 
%left AND
%left IGUAL DIF
%left '<' MENIGU '>' MAYIGU
%left '+' '-'
%left '*' '/' '%'
%right INC DEC '!'
%left '(' ')' '[' ']' '{' '}'

%type <obj> tipo
%type <obj> lista_parametros
%type <obj> parametro
%type <obj> parametros

%start programa

%%

programa: lista_declaraciones 
		{
		System.out.println("Análisis sintáctico completo");
						
		if(!tabla_funciones.getLast().equals("main")){
			errorSem("Main no es la última función declarada");
		}
		};

lista_declaraciones: lista_declaraciones declaracion 
					| declaracion 


declaracion: declaracion_variables 
					| declaracion_funcion ;

declaracion_variables: tipo lista_variables ';'
					{
						// agregar a la lista de variables relacionada con la función

					}
					 ;

lista_variables: lista_variables ',' lista 
					{
						//if(!tabla_variables.exists())
					}
					| lista 
					{

					}
					;

lista: ID{
			//obtener lista de parametros de la función y
			// ver que el ID no se repita.
			//func_actual es la función actual;
			tabla_variables.add($1);

		} 
		| ID '[' ENTERO ']' 
		{
			tabla_variables.add($1);
		}
		;
tipo: INT {$$ = ListaTipos.getIdByTipo("int"); }
	|FLOAT {$$ = ListaTipos.getIdByTipo("float");}
	|DOUBLE {$$ = ListaTipos.getIdByTipo("double");}
	|CHAR {$$ = ListaTipos.getIdByTipo("char");}
	|VOID {$$ = ListaTipos.getIdByTipo("void");}
	|tipo_struct {$$ = null;};

tipo_struct: STRUCT ID '{' cuerpo_struct '}' 
					|STRUCT '{' cuerpo_struct '}'
					|STRUCT ID;

cuerpo_struct: cuerpo_struct declaracion_variables 
					|declaracion_variables;

declaracion_funcion: tipo 
					 ID 
					 '(' parametros ')' 
					 {
					 	int id_tipo = ((Tipo)$1).id;
					 	func_actual = $2;
					 	tabla_funciones.add($2);
					 	//agregamos a la lista de listas de parametros
					 	ListaParametros temp = ((ListaParametros)$4);
					 	lista_listas_parametros.add(temp);
					 	temp.imprimir();
					 	//agregar a la tabla de funciones
					 	// declaramos la función actual declarada.
					 	//obtenemos el tipo de la fucnión para meterlo en tabla de simbolos
					 	//num deparametros de la función
					 	cont_param = ((ListaParametros)$4).tam;
					 	// función que agrega la función a la lista de parametros
					 	// con id de la lista de parametros y el número de parametros.
					 	//si es 0 es voud 
					 	boolean agregar = tabla_simbolos.add(cont_simbolos,func_actual,id_tipo,cont_param,cont_lista_param,direccion_);
					 	direccion_++;// aumentamos la direcció
					 }
					 bloque 
					 ;

parametros: lista_parametros 
			{
				$$ = ((ListaParametros)$1);
			}
			| VOID 
			{
				$$ = new ListaParametros(cont_lista_param);
				((ListaParametros)$$).tam = 0;
				cont_lista_param++;

			}
			;

lista_parametros: lista_parametros ',' parametro
			{
				((ListaParametros)$$).tam = ((ListaParametros)$1).tam;
				((ListaParametros)$$).add((Parametro)$3);

			}
			| parametro
			{
				$$ = new ListaParametros(cont_lista_param);
				((ListaParametros)$$).add((Parametro)$1);
				((ListaParametros)$$).tam = 1;
			}
			;

parametro: tipo ID '[' ']'
		{
			tabla_variables.add($2);
			$$  = new Parametro($2,((Tipo)$1).id,1);
			tabla_simbolos.add(cont_simbolos++,$2,((Tipo)$1).id,-1,-1,direccion_++);
		}
		|tipo ID
		{
			tabla_variables.add($2);
			$$  = new Parametro($2,((Tipo)$1).id,0);
			tabla_simbolos.add(cont_simbolos++,$2,((Tipo)$1).id,-1,-1,direccion_++);
		}
		;

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
					|';' ;

sentencia_if: IF '(' expresion ')' sentencia sentencia_else ;

sentencia_else: ELSE sentencia
					| ;

sentencia_while: WHILE '(' expresion ')' sentencia ;

sentencia_do: DO sentencia WHILE '(' expresion ')'';';

sentencia_switch: SWITCH '(' ID ')'  '{' lista_casos case_default '}';

lista_casos: lista_casos sentencia_case 
					| sentencia_case ;

sentencia_case: CASE ENTERO ':' sentencia sentencia_break ;

case_default: DEFAULT ':' sentencia sentencia_break 
					| ;

sentencia_for: FOR '(' expresion ';' expresion ';' sentencia_incremento ')' sentencia;

sentencia_break: BREAK ';' ;

sentencia_incremento: ID INC 
					| ID DEC;

sentencia_imprime: PRINT '(' expresion ')'';' ;

sentencia_lee: SCAN '(' ID ')'';' ;

sentencia_return: RETURN ';' 
					| RETURN expresion ';' ;

expresion: variable operador_asignacion expresion 
					| expresion_simple;

operador_asignacion: '=' 
					| MASIGUAL	
					| MENOSIGUAL
					| MULTIGUAL	
					| DIVIGUAL	
					| MODUIGUAL ;

variable: ID 
			{
				p("La función donde está la variable "+$1+" es: "+func_actual);
				if(!tabla_variables.exists($1)){
					errorSem("Variable no declarada :"+ $1);
				}else{
					int x = tabla_simbolos.getParamsIdByVal(func_actual);
					if( x!= -1){
						// checar si $1 es parametro o si es declaracion
						ListaParametros temp = lista_listas_parametros.getListaParamsbyId(x);
						if(temp.exists($1) || tabla_variables.exists($1)){
							p("Si es parametro o declaración, no hoy problema");
						}else{
							//checar los parametros o variables en tabla de simbolos del metodo
							p("hay un error");
						}
					}else{
						p("El simbolo no tiene parametros");
					}

				}
			} 
		| ID '[' expresion ']'
			{
				if(!tabla_variables.exists($1)){
					errorSem("Variable no declarada :"+ $1);
				}else{
					int x = tabla_simbolos.getParamsIdByVal(func_actual);
					if( x!= -1){
						// checar si $1 es parametro o si es declaracion
						ListaParametros temp = lista_listas_parametros.getListaParamsbyId(x);
						if(temp.exists($1)){
							p("Si es parametro, no hoy problema");
						}
					}else{
						p("El simbolo no tiene parametros");
					}

				}
			};

expresion_simple: expresion_simple operador_relacional operando 
					| expresion_simple operador_logico operando	
					| operando ;

operador_logico: AND 
					|OR ;

operador_relacional: MENIGU 
					| MAYIGU
					| '<'
					| '>' 
					| IGUAL 
					| DIF ;

operando: operando operador_adicion termino  
					| termino;

operador_adicion: '+' 
					| '-';

termino: termino operador_mul factor 
					| factor;

operador_mul: '*' 
					| '/' 
					| '%' ;

factor: variable 
					| llamada 
					| '(' expresion ')' 
					| ENTERO 
					| REAL
					| CARACTER 
					| CADENA
					| DOBLE 
					| '!'expresion 
					| '-'expresion ;

llamada: ID '(' argumentos ')'
			{
				if(!tabla_funciones.exists($1)){
					errorSem($1 +" Función no declarada.");
				}else{
					
				}

			}
			;

argumentos: lista_argumentos 
					| ;

lista_argumentos: lista_argumentos ',' expresion 
					| expresion ;


%%

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

