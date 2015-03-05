import java.io.*;

%%

%byaccj

%{
	private Parser yyparser;
	
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}
%}

NUM = [0-9]+("."[0-9]+)?
NL = \n|\t|\r\n
ID = [A-Za-z_]

%%

"+" | "-" |"*" | "/" | "(" | ")" 	{return (int) yycharat(0); }
{NL}								{return Parser.NL;}
{NUM}								{ yyparser.yylval = new ParserVal(Double.	
parseDouble(yytext()));
									  return Parser.NUM;}

