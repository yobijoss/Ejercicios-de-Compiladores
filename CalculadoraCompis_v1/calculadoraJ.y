%{
import java.io.*;
%}

//Token declaration
%token NL 
%token <dval> NUM
token id

%left '+' '-'
%left '*' '/'


//Non terminal declaration


%start exp

%%

/*Gramar Section*/

exp : NUM
	| exp '+' exp
	| exp '-' exp
	| exp '*' exp
	| exp '/' exp
	| '(' exp ')';

%%

/* A reference to the lexer object */
private Yylex lexer;

/* Interface to the lexer */
private int yylex(){
	int yyl_return = -1;
	try{
		yyl_return = lexer.yylex();
	}catch(IOException e){
		System.err.println("IO  error: "+yyl_return+" "+e);
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


public static void main(String args[]){
	Parser yyparser = null;
	try{
		yyparser = new Parser(new FileReader(args[0]));

	}catch(Exception e){
		System.out.println("Error: "+e);
	}
	//Método que realiza el proceso de analisis
	yyparser.yyparse();
}
