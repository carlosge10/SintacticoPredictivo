package asp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TokenList {
	List<Token> tl;
	public TokenList() 
	{
		tl = new ArrayList<Token>();
	}
	File file;
	BufferedReader br;

	public TokenList(boolean bo, File f, BufferedReader b) throws Exception
	{
		tl = new ArrayList<Token>();
		//file = new File("C:\\Users\\carlo\\eclipse-workspace\\AnalisisSintacticoPredictivoBueno\\src\\asp\\input.txt");
		//br = new BufferedReader(new FileReader(file));
		file = f;
		br = b;
		String len;
		len = br.readLine();
		int i = Integer.parseInt(len.trim());
		System.out.println("Dime la lista de tokens separados por comas");
		String aux;
		while(i-->0) 
		{
			aux = br.readLine();
			String[] arr = aux.split(",");
			tl.add(new Token(arr[1], arr[0]));
		}
	}
}
