package asp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LL1Table {
	TokenList terminales;
	TokenList noTerminales;
	List<TokenList> table;
	File file;
	BufferedReader br;
	public LL1Table(File f, BufferedReader b) throws Exception
	{
		//file = new File("C:\\Users\\carlo\\eclipse-workspace\\AnalisisSintacticoPredictivoBueno\\src\\asp\\input.txt");
		//br = new BufferedReader(new FileReader(file)); 
		file = f;
		br = b;
		table= new ArrayList<TokenList>();	
		noTerminales=getList("noTerminales");
		terminales=getList("terminales");
		makeTable();
		printTable();
	}
	public TokenList getList(String listName) throws Exception
	{
		TokenList list = new TokenList();
		System.out.println("Dime los " + listName + " separados por comas");
		Scanner sc = new Scanner(System.in);
		String s;
		
		s = br.readLine(); 
		
		String[] arr = s.split(",");
		Token t;
		for (String string : arr) {
			t = new Token();
			t.value = string;
			t.tClass = listName;
			list.tl.add(t);
		}
		return list;
	}
	public void makeTable() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime la tabla, cada elemento de la produccion separada por espacios, para indicar que es casilla vacia, solo por un espacio, para indicar epsilon escribe 'epsilon':");
		TokenList aux = new TokenList();
		aux.tl = new ArrayList<Token>();
		String saux;
		for (Token nt : noTerminales.tl) {
			System.out.println("\t" + nt.value + ":");
			for (Token t : terminales.tl) {
				System.out.println("\t\t"+t.value+":");
				saux = br.readLine(); 
				table.add(getCell(saux));
			}
		}
	}
	public TokenList getCell(String s) 
	{
		String[] arr = s.split(" ");
		TokenList ret = new TokenList();
		ret.tl = new ArrayList<Token>();
		Token t;
		for (String string : arr) {
			t = new Token();
			t.value = string;
			ret.tl.add(t);
		}
		return ret;
	}
	public String printCell(int i) 
	{
		String s="";
		for (Token t : table.get(i).tl) {
			s=s+t.value;
		}
		return s;
	}
	public void printTable() 
	{
		System.out.println("Tu tabla:");
		int j;
		j = 0;
		for (Token nt : noTerminales.tl) {
			System.out.println(nt.value + ":");
			for (Token t : terminales.tl) {
				System.out.println("\t" + t.value + ":");
				System.out.println("\t\t" + printCell(j++));
			}
		}
	}
	public int getIndex(String terminal, String noTerminal) 
	{
		int res;
		int i=0; 
		int j=0;
		for (Token t : noTerminales.tl) {
			if(t.value.equals(noTerminal))
				break;
			i++;
		}
		for (Token t : terminales.tl) {
			if(t.value.equals(terminal))
				break;
			j++;
		}
		return i*terminales.tl.size() + j;
	}
	public boolean isTeminal(String s) 
	{
		for (Token t : terminales.tl) {
			if(t.value.equals(s))
				return true;
		}
		return false;
	}
}
