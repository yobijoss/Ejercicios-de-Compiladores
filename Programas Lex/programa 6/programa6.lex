import java.io.*;

/*Escribir un fuente lex que tenga un comportamiento parecido a la utilidad wc que acompa~na al
sistema operativo UNIX. Debe leer un fichero de entrada y mostrar el numero de caracteres
que contiene, el numero de palabras (caracteres delimitados por espacios en blanco) y el
numero total de lineas.*/
%%
%init{
	num_palabras=0;

%init}
%char
%line
%{
	File f;
	FileWriter fw;
	PrintWriter pw;
	int num_palabras;
	
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
		scanner.openFileWriter("salida6.txt");

		try{
			while(scanner.yylex()!= null){}
		}catch(Exception e){}
	}
	
%}

%eofval{
	return null;
%eofval}

%eof{
	pw.write("Numero de caracteres: "+yychar);
	pw.write("\nNumero de palabras: "+num_palabras);
	pw.write("\nNumero de lineas: "+yyline);
	pw.close();
	try{fw.close();}
	catch(Exception e){}
%eof}

%%
[A-Za-z]+			{num_palabras++;}
(.)				{}
[\n\r\t\b ]				{}





