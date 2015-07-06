package net.heteroclinic.tinycsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex {
	@Test 
	public void testRemoveFieldLoops() throws FileNotFoundException {
		final String endOfLineSymbol = System.getProperty("line.separator");
		final String useMultiLines = "(?s)";
		//! final String fieldDef1 = useMultiLines+"^[^\\\"][^,]*[,|$|"+endOfLineSymbol+"]";
		/*
|d3
a4,|__p1
		 */
		//!final String fieldDef1 = useMultiLines+"^[^\\\"].*?[,|$|"+endOfLineSymbol+"]";
/*
|
,|__p1
----
b5,,d5	
 */
		//TODO handle empty cell first ^[,|$|"+endOfLineSymbol+"|\\\n|\\\Z]
		//TODO handle \\\Z
		//TODO define field separator
		final String fieldDef1 = useMultiLines+"^[^\\\"].*?[,|$|"+endOfLineSymbol+"|\\\n]";
		final String fieldDef2 = useMultiLines+"^[\\\"]([\\\"][\\\"])*.*?[\\\"]([\\\"][\\\"])*[,|$|"+endOfLineSymbol+"]";
		final String fieldDef3 = useMultiLines+"^[,|$|"+endOfLineSymbol+"]";
		{
			System.out.println("\n\nStart 1 ... ");
			// This case is wrong.
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			boolean endOfFile = false;
			String tmp = data;
			while (!endOfFile) {
				Pattern p1 = Pattern.compile(fieldDef1);
				Matcher m1 = p1.matcher(tmp);
				if (m1.find()) {
					String field = tmp.substring(m1.start(),m1.end());
					tmp = tmp.replaceFirst(fieldDef1,"");
					System.out.println("=====");
					System.out.println("|"+field+"|__p1");
					System.out.println("----");
					System.out.println(tmp);
					System.out.println("=====");
					//break;
				} else {
					Pattern p2 = Pattern.compile(fieldDef2);
					Matcher m2 = p2.matcher(tmp);
					if (m2.find()) {
						String field = tmp.substring(m2.start(),m2.end());
						tmp = tmp.replaceFirst(fieldDef2,"");
						System.out.println("=====");
						System.out.println("|"+field+"|__p2");
						System.out.println("----");
						System.out.println(tmp);
						System.out.println("=====");
						//break;
					} else {
						break;
					}
				}
				
			}

		}	
		
	}
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
		// if a field does neither start with double quote nor start with a character that is not a double quote,
		// then the field is an empty string.
		final String fieldDef3 = "^[,|$]";
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
		{
			System.out.println("\n\nStart 4 ... ");
			// This case is wrong.
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile(useMultiLines+"("+fieldDef1+")|("+fieldDef2+")");
			String tmp = data;
			Matcher m = p.matcher(tmp);
			int count = 0;
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				//System.out.print(field +"\t");
				Pattern p1 = Pattern.compile(useMultiLines+fieldDef1);
				Matcher m1 = p.matcher(field);
				if (m1.find()) {
					System.out.print(field +"-p1|");
				} else {
					Pattern p2 = Pattern.compile(useMultiLines+fieldDef2);
					Matcher m2 = p2.matcher(field);
					if (m2.find()) {
						System.out.print(field +"-p2|");
					} else {
						System.out.print(field +"-p3|");
					}
				}
				count++;
			}
			System.out.println("count = "+count);
		}
		
		{
			System.out.println("\n\nStart 5 ... ");
			// This case is wrong.
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile(useMultiLines+"("+fieldDef1+"|"+fieldDef2+")");
			String tmp = data;
			Matcher m = p.matcher(tmp);
			int count = 0;
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				//System.out.print(field +"\t");
				Pattern p1 = Pattern.compile(useMultiLines+fieldDef1);
				Matcher m1 = p.matcher(field);
				if (m1.find()) {
					System.out.print(field +"-p1|");
				} else {
					Pattern p2 = Pattern.compile(useMultiLines+fieldDef2);
					Matcher m2 = p2.matcher(field);
					if (m2.find()) {
						System.out.print(field +"-p2|");
					} else {
						System.out.print(field +"-p3|");
					}
				}
				count++;
			}
			System.out.println("count = "+count);
		}
		{
			System.out.println("\n\nStart 6 ... ");
			// This case is wrong.
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile(useMultiLines+""+fieldDef1+"|"+fieldDef2+"");
			String tmp = data;
			Matcher m = p.matcher(tmp);
			int count = 0;
			while (m.find()) {
				String field = tmp.substring(m.start(),m.end());
				//System.out.print(field +"\t");
				Pattern p1 = Pattern.compile(useMultiLines+fieldDef1);
				Matcher m1 = p.matcher(field);
				if (m1.find()) {
					System.out.print(field +"-p1|");
				} else {
					Pattern p2 = Pattern.compile(useMultiLines+fieldDef2);
					Matcher m2 = p2.matcher(field);
					if (m2.find()) {
						System.out.print(field +"-p2|");
					} else {
						System.out.print(field +"-p3|");
					}
				}
				count++;
			}
			System.out.println("count = "+count);
		}	
		// TODO remove trailing ","
		// TODO handle quote

	}
	
	@Test
	public void testReadFileToString() throws FileNotFoundException {
		String content = new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();
		System.out.println(content);
	}
}
