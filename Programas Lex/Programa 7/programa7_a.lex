import java.io.*;
/*Escribir un fuente lex que permita reconocer las cadenas de caracteres y los comentarios del
lenguaje C. Realizar el ejercicio usando tan solo expresiones regulares y despues repetirlo
utilizando algunas de las caracteristicas avanzadas de lex.*/
%%

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
		scanner.openFileWriter("salida7_a.txt");

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
[\n\r\t\b ]				{}
[a-zA-Z]+				{pw.write("Es cadena :"+yytext());}
"/*"[^/*]*"*/"			{pw.write("Es comentario :"+yytext());}
.						{}



