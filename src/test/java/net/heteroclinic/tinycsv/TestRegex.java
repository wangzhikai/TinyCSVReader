package net.heteroclinic.tinycsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex {
	@Test
	public void testMultilinesRegex() {
		{
			System.out.println("Start");
			final String str1 = "a\nab\n \nc\n";
			String input = str1;
	
			//Pattern p = Pattern.compile("(?s)^$");
			//Pattern p = Pattern.compile("^[a]");
			//Pattern p = Pattern.compile("(?s)^\\s*\n");
			Pattern p = Pattern.compile("(?s)^\\s+");
			Matcher m = p.matcher(input);
			while (m.find()) {
				System.out.println(m.start()+" " +m.end());
				
			}
			
			System.out.println(str1);
		}
		{
			System.out.println("Start");
			final String str1 = "a    m\nb  b\n  ";
			String input = str1;
			// \\s white space 
			Pattern p = Pattern.compile("(?s)b\\s*b");
			Matcher m = p.matcher(input);
			while (m.find()) {
				System.out.println(m.start()+" " +m.end());
				
			}
			
			System.out.println(str1);	
		}
		{
			System.out.println("Start");
			// find a pattern spanning multiple lines
			final String str1 = "a    m\nb  b\n  ";
			String input = str1;
			// \\s white space 
			Pattern p = Pattern.compile("(?s)m.*b");
			Matcher m = p.matcher(input);
			while (m.find()) {
				System.out.println(m.start()+" " +m.end());
				String tmp = str1.substring(m.start(), m.end());
				System.out.println(tmp);				
			}
			
			//System.out.println(str1);	
		}

	}
	
	@Test
	public void testMatchFields() throws FileNotFoundException {
		//final String useMultiLines = "(?s)";
		final String useMultiLines = "(?s)";
		//! final String fieldDef1 = "[^\"][^,]*[,|$]";
		//! final String fieldDef1 = "^[^\\\"][^,]*[,|$]";
		final String fieldDef1 = "[^\\\"][^,]*[,|$]";
		final String fieldDef2 = "[\\\"]([\\\"][\\\"])*.*?[\\\"]([\\\"][\\\"])*[,|$]";

		{
			System.out.println("Start 1...");
			final String data = "a,b,c c,d1\na2,b2,c2,d2";
			Pattern p = Pattern.compile(useMultiLines+fieldDef1);
			String tmp = data;
			Matcher m = p.matcher(tmp);
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				System.out.print(field +"\t");
			}			
		}
		{
			// no meaning
			System.out.println("\n\nStart 2 ...");
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile(useMultiLines+fieldDef1);
			String tmp = data;
			Matcher m = p.matcher(tmp);
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				System.out.print(field +"\t");
			}			
		}
		{
			System.out.println("\n\nStart 3 ...");
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile(useMultiLines+fieldDef2);
			String tmp = data;
			Matcher m = p.matcher(tmp);
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				System.out.print(field +"\t");
			}			
		}
		
		// todo remove trailing ","
		// todo handle quote

	}
	
	@Test
	public void testReadFileToString() throws FileNotFoundException {
		String content = new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
		System.out.println(content);
	}
}
