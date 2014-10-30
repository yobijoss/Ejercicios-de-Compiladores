package parser;
import java.io.*;
%%

%public

%type Token

%line
%column


%{
	File f;
	FileWriter fw;
	PrintWriter pw;
	
	
	public  void  openFileWriter(String nombre){
		try{
            fw = new FileWriter(nombre);
            pw = new PrintWriter(fw);            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	
	public static void main(String... args){
		File f = new File(args[0]);
		FileReader fw = null;
		try{
			fw =  new FileReader(f);
		}catch(Exception e){}
		Yylex scanner = new Yylex(fw);
		scanner.openFileWriter("salida.txt");
		try{
			while(scanner.yylex().lex != "EOF"){
			}
		}catch(Exception e){}

	}

	public static void i(String i){
		System.out.println(i);
	}
	
%}

%eofval{
	return new Token("EOF", -1, 'e',yyline, yycolumn);
%eofval}

%eof{
	pw.close();
	try{fw.close();}
	catch(Exception e){}
%eof}

%init{
yyline++;
%init}


letra=[a-zA-Z_]

digito = [0-9]

%%
"int"							{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 1, 'p',yyline, yycolumn);} //palabra reservada int, clase 1 para palabras reservadas
"float"							{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 1, 'p',yyline, yycolumn);} //palabra reservada float
"double"						{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 1, 'p',yyline, yycolumn);} //palabra reservada double
"char"							{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 1, 'p',yyline, yycolumn);} //palabra reservada char
{letra}({letra}|{digito})+		{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 2, 'i',yyline, yycolumn);} //identificador, clase 2 para identificadores
{digito}+						{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 3, 'n',yyline, yycolumn);} //Entero, clase 3 para números naturales
{digito}*"."{digito}+			{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 4, 'r',yyline, yycolumn);} //Doble, clase 4 para números reales
{digito}*"."{digito}+"f"		{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 4, 'r',yyline, yycolumn);} //Flotante
{letra}							{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(yytext(), 5, 'c',yyline, yycolumn);} //Caracter, clase 5 para cadenas
"+"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token("+", 6, '+',yyline, yycolumn);}
"-"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token("-", 7, '-',yyline, yycolumn);}
"*"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token("*", 8, '*',yyline, yycolumn);}
"/"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token("/", 9, '/',yyline, yycolumn);}
":="							{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(":=", 10, '=',yyline, yycolumn);}
";"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(";", 11, ';',yyline, yycolumn);}
"("								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token("(", 12, '(',yyline, yycolumn);}
")"								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(")", 13, ')',yyline, yycolumn);}
","								{	System.out.println(yytext());
									fw.write(yytext()+"\n");
									return new Token(",", 14, ',',yyline, yycolumn);}
[\r\t\b\n ]						{}



