import java.io.*;

%%
%type String
%state CINCO,DIEZ

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

	public static void i(String i){
		System.out.println(i);
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

<YYINITIAL>{
	"Rem"(.)*$ {pw.write("<Rem>\n"); i(yytext());yybegin(YYINITIAL);}
	"Push"  {pw.write("<"+yytext()+">\n"); i(yytext() + "EN UNO");}
	"Add"  {pw.write("<"+yytext()+">\n"); i(yytext() + "EN UNO");}
	"Pop"  {pw.write("<"+yytext()+">\n"); i(yytext() + "EN UNO");}
	(" "|"  ")  {yybegin(CINCO); i("Voy a 5");}
	[\n\r\b] {}
}

<CINCO>{
	[a-zA-Z0-9]"    "	   {yybegin(DIEZ); pw.write("<"+yytext().substring(0,1)+">\n"); i(yytext() + "EN 5");}
	[a-zA-Z0-9]{2}"   "    {yybegin(DIEZ); pw.write("<"+yytext().substring(0,2)+">\n"); i(yytext()+ "EN 5");}
	[a-zA-Z0-9]{3}"  "     {yybegin(DIEZ); pw.write("<"+yytext().substring(0,3)+">\n"); i(yytext()+ "EN 5");}
	[a-zA-Z0-9]{4}" "      {yybegin(DIEZ); pw.write("<"+yytext().substring(0,4)+">\n"); i(yytext()+ "EN 5");}
	[a-zA-Z0-9]{5}         {yybegin(DIEZ); pw.write("<"+yytext()+">\n");}
	[a-zA-Z0-9]{1,5}\n       {yybegin(YYINITIAL); pw.write("<"+yytext().substring(0,yytext().length()-1)+">\n"); i(yytext()+ "EN 5");}
	[a-zA-Z0-9]{1,5}      {yybegin(YYINITIAL); pw.write("<"+yytext()+">\n"); i(yytext()+ "EN 5");}
}

<DIEZ>{
	[\n\t\r\b ]				{yybegin(YYINITIAL); pw.write(yytext()); i(yytext()+ "EN 10");}
	(.)+			{yybegin(YYINITIAL); pw.write("<"+yytext()+">\n"); i(yytext()+ "EN 10");}

}


