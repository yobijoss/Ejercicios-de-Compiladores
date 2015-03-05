%{
import java.io.*;
%}

//Token declaration
%token NL 
%token <dval> NUM

%left '+' '-'
%left '*' '/'


//Non terminal declaration
%type <dval> exp

%start exp

%%

/*Gramar Section*/

exp : NUM {$$ = $1;}
	| exp '+' exp {$$ = $1 + $3;}
	| exp '-' exp {$$ = $1 - $3;}
	| exp '*' exp {$$ = $1 * $3;}
	| exp '/' exp {$$ = $1 / $3;}
	| '(' exp ')' {$$ = $2;};

%%

/* A reference to the lexer object */
private Yylex lexer;

/* Interface to the lexer */
private int yylex(){
	int yyl_return = -1;
	try{
		yyl_return = lexer.yylex();
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


public static void mian(String args[]){
	Parser yyparser = null;
	try{
		yyparser = new Parser(new FileReader(args[0]));

	}catch(Exception e){
		System.println("Error: "e);
	}
	//Método que realiza el proceso de analisis
	yyparser.yyparse();
}
