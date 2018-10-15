package asp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class ASPRunner {

	public static void main(String[] args) {
		try {
			File file;
			BufferedReader br;
			file = new File("src\\asp\\input.txt");
			br = new BufferedReader(new FileReader(file));
			LL1Table table = new LL1Table(file, br);
			TokenList tl = new TokenList(true, file, br);
			tl.tl.add(new Token("", "$"));
			Stack<Token> st = new Stack<Token>();
			st.push(table.noTerminales.tl.get(0));
			solve(table, tl, st);
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public static void solve(LL1Table tableInfo, TokenList tlInfo, Stack<Token> st) 
	{
		int ptr = 0;
		Token currentToken;
		Token currentStackToken;
		TokenList nextRule = new TokenList();
		String coincidencia = "";
		boolean error=false;
		
		while(st.size() > 0) {
			currentToken = tlInfo.tl.get(ptr);
			currentStackToken = st.peek();
			System.out.println("--------------");
			System.out.println("COINCIDENCIA:");
			for(int i=0; i<ptr; i++)
				System.out.print(tlInfo.tl.get(i).value);
			System.out.println();
			
			System.out.println("STACK:");
			for(int i=0; i<st.size(); i++)
				System.out.print(st.get(i).value);
			System.out.println();
			
			System.out.println("ENTRADA:");
			for(int i=ptr; i<tlInfo.tl.size(); i++)
				System.out.print(tlInfo.tl.get(i).value);
			System.out.println();
			
			System.out.println("ACCION:");
			if(currentStackToken.value.equals(currentToken.value) & tableInfo.isTeminal(currentToken.value)) 
			{
				coincidencia = coincidencia + currentToken.value;
				st.pop();
				ptr++;
				System.out.println("coincide " + currentToken.value);
				continue;
			}else 
			{
				nextRule = tableInfo.table.get(tableInfo.getIndex(currentToken.value, currentStackToken.value));
				if(nextRule.tl.size() <= 1 ) {
					System.out.println("Error");
					error = true;
					break;
				}
				if(nextRule.tl.size() == 3 && nextRule.tl.get(2).value.equals("epsilon")) 
				{
					st.pop();
					for (Token t : nextRule.tl) {
						System.out.print(t.value);
					}
					System.out.println("");
					continue;
				}
				else 
				{
					for (Token t : nextRule.tl) {
						System.out.print(t.value);
					}
					System.out.println("");
					
					st.pop();
					for (int i = nextRule.tl.size()-1; i >=0 ; i--) {
						if(i>1 & !nextRule.tl.get(i).value.equals("epsilon"))
							st.push(nextRule.tl.get(i));
					}
				}
			}
		}
		if(error)
			System.out.println("NO ACEPTADA");
		else
			if(tlInfo.tl.size()-1 == ptr)
				System.out.println("ACEPTADA");
			else
				System.out.println("NO ACEPTADA");
	}
	
	public static void solveOriginal(LL1Table tableInfo, TokenList tlInfo, Stack<Token> st) 
	{
		int ptr = 0;
		Token currentToken;
		Token currentStackToken;
		TokenList nextRule = new TokenList();
		String coincidencia = "";
		boolean error=false;
		
		while(st.size() > 0) {
			currentToken = tlInfo.tl.get(ptr);
			currentStackToken = st.peek();
			System.out.println("--------------");
			System.out.println("COINCIDENCIA:");
			for(int i=0; i<ptr; i++)
				System.out.print(tlInfo.tl.get(i).value);
			System.out.println();
			
			System.out.println("STACK:");
			for(int i=0; i<st.size(); i++)
				System.out.print(st.get(i).value);
			System.out.println();
			
			System.out.println("ENTRADA:");
			for(int i=ptr; i<tlInfo.tl.size(); i++)
				System.out.print(tlInfo.tl.get(i).value);
			System.out.println();
			
			System.out.println("ACCION:");
			if(currentStackToken.value.equals(currentToken.value) & tableInfo.isTeminal(currentToken.value)) 
			{
				coincidencia = coincidencia + currentToken.value;
				st.pop();
				ptr++;
				System.out.println("coincide " + currentToken.value);
				continue;
			}else 
			{
				if(nextRule.tl.size() == 3 && nextRule.tl.get(2).value.equals("epsilon")) 
				{
					st.pop();
					ptr++;
					for (Token t : nextRule.tl) {
						System.out.print(t.value);
					}
					System.out.println("");
					continue;
				}
				else {
					nextRule = tableInfo.table.get(tableInfo.getIndex(currentToken.value, currentStackToken.value));
					for (Token t : nextRule.tl) {
						System.out.print(t.value);
					}
					System.out.println("");
				}
			}
				
			if(nextRule.tl.size() <= 1 ) {
				System.out.println("Error");
				error = true;
				break;
			}
			else 
			{
				st.pop();
				for (int i = nextRule.tl.size()-1; i >=0 ; i--) {
					if(i>1 & !nextRule.tl.get(i).value.equals("epsilon"))
						st.push(nextRule.tl.get(i));
				}
			}
		}
		if(error)
			System.out.println("NO ACEPTADA");
		else
			System.out.println("ACEPTADA");
	}
//ya sube
}
