import java.io.*;
/*Escribir un programa lex que encripte un texto en el que hay letras mayusculas y minusculas,
comas, espacios, puntos y retornos de carro. Para ello, con cada palabra se hara lo siguiente:
 Si termina en una vocal, cada una de sus letras se cambiara por la posterior en el
alfabeto. Despues de la zeta se supone que viene la a.
 Si la palabra termina en una consonante, cada una de sus letras se cambiara por la
anterior en el alfabeto. Antes de la a, se considera que va la z.
 El resto de los caracteres se quedan igual.*/
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
		scanner.openFileWriter("salida5.txt");

		try{
			while(scanner.yylex()!= null){}
		}catch(Exception e){}
	}

	public String codificaVocal(String x){
		String res = "";
		for(char a : x.toCharArray()){
			if (a == 'z'){
				res += 'a';
			}else if( a == 'Z'){
				res += 'A';
			}else{
				res += (char)(((int) a) + 1);
			}
		}
		return res;
	}

	public String codificaConsonante(String x){
		String res = "";
		for(char a : x.toCharArray()){
			if (a == 'a'){
				res += 'z';
			}else if( a == 'A'){
				res += 'Z';
			}else{
				res += (char)(((int) a) - 1);
			}
		}

		return res;
	}


	
%}

%eofval{
	pw.write("\n");
	return null;
%eofval}

%eof{
	pw.close();
	try{fw.close();}
	catch(Exception e){}
%eof}

%%
[\n\r\t\b ]				{pw.write(yytext());}
[a-zA-Z]*[aeiou]		{   pw.write(codificaVocal(yytext()));
							System.out.println("termina en vocal");
						}
[a-zA-Z]*[b-df-hj-np-tv-z]          {	pw.write(codificaConsonante(yytext()));
										System.out.println("termina en consonante");
									}





