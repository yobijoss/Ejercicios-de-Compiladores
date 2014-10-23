import java.io.*;
%%
%states 
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
		FileReader fr = null;
		try{
			fr =  new FileReader(f);
		}catch(Exception e){}
		Yylex scanner = new Yylex(fr);
		scanner.openFileWriter("salida.txt");

		try{
			while(scanner.yylex()!= null){}
		}catch(Exception e){}
	}
	
%}

%eofval{
	return null;
%eofval}

%eof{
	pw.close();
	try{fw.close();}
	catch(Exception e){}
%eof}

%%
/*
8. Obtener la especicacion en lex del reconocer lexico para un sencillo lenguaje de programacion
con las siguientes caracteristicas:
(a) Palabras reservadas: begin, if, then, else, end. Pueden aparecer en mayusculas, minusculas
o una mezcla de ambas. listo
(b) Simbolos especiales: ( ) := . listo
(c) Identicadores, formados por letras, digitos y los caracteres @, + , -, * y /. Los identi
cadores pueden empezar por cualquiera de estos caracteres, pero no pueden estar
compuestos integramente por digitos. listo 
(d) Numeros enteros y 
otantes en notacion de coma fija. 
(e) En el lenguaje se ignoran todos los caracteres en blanco, esto es: el espacio, el tabulador,
el salto de linea y el salto de pagina.
*/

[\n\r\t\b ]				{}
(b|B)(e|E)(g|G)(i|I)(n|N)		{pw.write("<PR | "+yytext()+">\n");}
(e|E)(n|N)(d|D)					{pw.write("<PR | "+yytext()+">\n");}
(e|E)(l|L)(s|S)(e|E)			{pw.write("<PR | "+yytext()+">\n");}
(t|T)(h|H)(e|E)(n|N)			{pw.write("<PR | "+yytext()+">\n");}
(i|I)(f|F)						{pw.write("<PR | "+yytext()+">\n");}
("\("|"\)"|"."|":=")			{pw.write("<SE | "+yytext()+">\n");}
([a-zA-Z\@\+\-\*]|(\/\.))([a-zA-Z0-9\@\+\-\*]|(\/\.))*   {pw.write("<ID | "+yytext()+">\n");}
(0-9]+\,[0-9]+|\,[0-9]+|[0-9]+) {pw.write("<CTE | "+yytext()+">\n");}
.								{pw.write("simbolo raro del mal "+yytext()+"\n");}


