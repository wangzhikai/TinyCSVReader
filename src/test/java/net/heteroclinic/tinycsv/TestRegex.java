package net.heteroclinic.tinycsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
// Note : http://docs.oracle.com/javase/tutorial/essential/regex/bounds.html -- 20150706
// says $ is The end of a line in fact in (?s) mode it is end of String
// end of line should be system property line separator.
public class TestRegex {
	//TODO Find two quotes each time then new line
	@Test 
	public void testFindTwoQuotesNewLineLoop () throws FileNotFoundException {
		{
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();
			//final String data = "asbc\ndef\n\"cdf\ntpq";

			System.out.println("=== 5 find 1. string has even quotes + new line or 2. string has no quotes with + new line added last match===");
			// the or conditions should be mutual exclude or the parser may be confused. maybe that is former cases are not expected.
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(\\G"
					+ "([^\\\"]*?)"
					+ "([\\\n]|$))"
					+ "|"
					+ "(\\G"
					+ "((([^\\\n]*?)[\\\"](.*?)[\\\"]([^\\\n]*?))+?)"
					+ "([\\\n]|$))"
					+ ")" );
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}
		{
			//final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();
			final String data = "asbc\ndef\n\"cdf\ntpq";

			System.out.println("=== 4 find string has no quote end with new line since last match===");
			// the or conditions should be mutual exclude or the parser may be confused. maybe that is former cases are not expected.
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "\\G"
					+ "([^\\\"]*?)"
					+ "([\\\n]|$)"
					+ ")" );
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}
		{
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 3 find string has no quote end with new line ===");
			// the or conditions should be mutual exclude or the parser may be confused. maybe that is former cases are not expected.
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "([^\\\"]*?)"
					+ "([\\\n]|$)"
					+ ")" );
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 2 find string has exactly two quotes ===");
			// the or conditions should be mutual exclude or the parser may be confused. maybe that is former cases are not expected.
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(.*?)[\\\"](.*?)[\\\"]"
					+ ")" );
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 1 find 1. string has two quotes or 2. no quotes with end of line===");
			// the or conditions should be mutual exclude or the parser may be confused. maybe that is former cases are not expected.
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(.*?)[\\\"](.*?)[\\\"]"
					+ ")"
					+ "|"
					+ "(([^\\\"].*?)([\\\n]))");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
		
	}
	@Test 
	public void testFindTwoQuotes () throws FileNotFoundException {
		
		// not working well
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 4 find quotes appearing even times (including zero?) and nearest return===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "((([^\\\"]*?)[\\\"]([^\\\"]*?)[\\\"]([^\\\"]*?))*?)"
					+ "([\\\n]|$)"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 3 find two quotes ONLY and nearest return===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "([^\\\"]*?)[\\\"]([^\\\"]*?)[\\\"]([^\\\"]*?)[\\\n]"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 2 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(.*?)[\\\"](.*?)[\\\"](.*?)[\\\n]"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
		{
			
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 1 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(.*?)[\\\"](.*?)[\\\"]"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}		
	}
	

	@Test 
	public void testParseRecords () throws FileNotFoundException {
		{//missing line
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 4 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					//+ "((((.*?)[\\\"](.*?))((.*?)[\\\"](.*?)))*?)"
					+ "((((.*?)[\\\"](.*?)[\\\"](.*?))*?)|([^\\\"]*?))"
					+ "([\\\n]|$)"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}

		{
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 3 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					//+ "((((.*?)[\\\"](.*?))((.*?)[\\\"](.*?)))*?)"
					+ "((((.*?)[\\\"](.*?)[\\\"](.*?))*?)|([^\\\"]*?))"
					+ "([\\\n]|$)"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}

		{//missing rows, wrong
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 2 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "(.*?)((((.*?)[\\\"](.*?))((.*?)[\\\"](.*?)))*?)(.*?)"
					+ "([\\\n]|$)"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}
		{//missing rows
			final String data =  new Scanner(new File(this.getClass().getClassLoader().getResource("testfile1").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 1 ===");
			Pattern patternForRecord = Pattern.compile("(?s)"
					+ "("
					+ "((((.*?)[\\\"](.*?))((.*?)[\\\"](.*?)))*?)"
					+ "([\\\n])"
					+ ")");
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
		}
	}

	
	//TODO test quotes appear even times
	@Test 
	public void testFindNewLineAfterEvenQuotesCount () {
		final String data = 
				"\"\n\"\n"
				+ "\"\"\"\"\n"
				+ "\"\"\n\"\"\n"
				+ "a\"\"\"\"\n\n"
				+ "a\"aa\na\"\"bbb\"\n"
				+ "\"\"\"\"\"\n\"\"\"\n";
		{
			System.out.println("=== 2 ===");
			Pattern p = Pattern.compile("(?s)"
					+ "("
					+ "(((.*?)[\\\"](.*?))((.*?)[\\\"](.*?)))*?"
					+ "[\\\n]"
					+ ")");
			Matcher m = p.matcher(data);
			while (m.find()) {
				System.out.println("|"+data.substring(m.start(), m.end())+"|");
				System.out.println("----");
			}
/*
=== 2 ===
|"
"
|
----
|""""
|
----
|""
|
----
|""
|
----
|a""""
|
----
|
|
----
|a"aa
a""bbb"
|
----
|"""""
"""
|
----

 */
		}
	}
	
	@Test 
	public void testSwitchPatternsNotWork () {
		final String data = "aaa\n"
				+ "b,bb\n"
				+ "aaa\n"
				+ "b,bb\n"
				+ "b,bb\n"
				+ "ccc";
		Pattern p1 = Pattern.compile("(?s)(a*?)([,]|[\\\n]|$)");
		Matcher m1 = p1.matcher(data);
		Pattern p2 = Pattern.compile("(?s)(b*?)([,]|[\\\n]|$)");
// infinite loop		
//		while (m1.find()) {
//			System.out.println("|"+data.substring(m1.start(), m1.end())+"|");
//			m1 = p2.matcher(data);
//		}
		
	}

	
	@Test
	public void testGroup () {
		// TODO test group
		final String data1 = "aa,a\n"
				+ "bbb\n";
		final String data2 = "aaa\n"
				+ "b,bb\n"
				+ "ccc";
		{
			
			Pattern p1 = Pattern.compile("((a*?)|(b*?)|(c*?))([,]|[\\\n]|$)");
			Matcher m1 = p1.matcher(data1);
			Pattern p2 = Pattern.compile("((a*?)|(b*?)|(c*?))([,]|[\\\n]|$)");
			Matcher m2 = p2.matcher(data2);
			System.out.println("1");
			while (m1.find()) {
				//System.out.println("m1.start()="+m1.start());
				//System.out.println("m1.end()="+m1.end());//Returns the offset after the last character matched.
				System.out.println("|"+data1.substring(m1.start(), m1.end())+"|");
			}
			System.out.println("2");
			while  (m2.find()) {
				//System.out.println("m2.start()="+m2.start());
				//System.out.println("m2.end()="+m2.end());
				System.out.println("|"+data2.substring(m2.start(), m2.end())+"|");
			}
		}
		/*
1
|aa,|
|a
|
|bbb
|
||
2
|aaa
|
|b,|
|bb
|
|ccc|
||		 */
		
	}
	// TODO test or condition
	@Test
	public void testOrCondition () {
		final String data1 = "aa,a\n"
				+ "bbb\n";
		final String data2 = "aaa\n"
				+ "b,bb\n"
				+ "ccc";
		{
			System.out.println("1");
			Pattern p1 = Pattern.compile("(?s),|[\\\n]");
			Matcher m1 = p1.matcher(data1);
			Matcher m2 = p1.matcher(data2);
			while (m1.find()) {
				System.out.println("m1.start()="+m1.start());
				System.out.println("m1.end()="+m1.end());//Returns the offset after the last character matched.
			}
			while  (m2.find()) {
				System.out.println("m2.start()="+m2.start());
				System.out.println("m2.end()="+m2.end());
			}
		}
	}
	
	@Test
	public void testMisc () {
		//System.out.println(System.getProperty("line.separator")); ="\n"
		// input is a Java String
		final String data1 = "aaa\n"
				+ "bbb\n";
		final String data2 = "aaa\n"
				+ "bbb\n"
				+ "ccc";
		// TODO find line separator
		{
			System.out.println("1");
			Pattern p1 = Pattern.compile("(?s).*?[\\\n]");
			Matcher m1 = p1.matcher(data1);
			Matcher m2 = p1.matcher(data2);
			while (m1.find()) {
				System.out.println("m1.start()="+m1.start());
				System.out.println("m1.end()="+m1.end());//Returns the offset after the last character matched.
			}
			while  (m2.find()) {
				System.out.println("m2.start()="+m2.start());
				System.out.println("m2.end()="+m2.end());
			}
		}
		
		// TODO find $
		{
			System.out.println("2");
			Pattern p1 = Pattern.compile("(?s).*?$");
			Matcher m1 = p1.matcher(data1);
			Matcher m2 = p1.matcher(data2);
			while  (m1.find()) {
				System.out.println("m1.start()="+m1.start());
				System.out.println("m1.end()="+m1.end());
			}
			while  (m2.find()) {
				System.out.println("m2.start()="+m2.start());
				System.out.println("m2.end()="+m2.end());
			}
		}
		// TODO find System.getProperty("line.separator")
		{
			System.out.println("3");
			Pattern p1 = Pattern.compile("(?s).*?["+System.getProperty("line.separator")+"]");
			Matcher m1 = p1.matcher(data1);
			Matcher m2 = p1.matcher(data2);
			while  (m1.find()) {
				System.out.println("m1.start()="+m1.start());
				System.out.println("m1.end()="+m1.end());
			}
			while  (m2.find()) {
				System.out.println("m2.start()="+m2.start());
				System.out.println("m2.end()="+m2.end());
			}
		}
		// TODO find '$' literal
		{
			System.out.println("4");
			Pattern p1 = Pattern.compile("(?s).*?[$]");
			Matcher m1 = p1.matcher(data1);
			Matcher m2 = p1.matcher(data2);
			while  (m1.find()) {
				System.out.println("m1.start()="+m1.start());
				System.out.println("m1.end()="+m1.end());
			}
			while  (m2.find()) {
				System.out.println("m2.start()="+m2.start());
				System.out.println("m2.end()="+m2.end());
			}
		}
/*
1
m1.start()=0
m1.end()=4
m1.start()=4
m1.end()=8
m2.start()=0
m2.end()=4
m2.start()=4
m2.end()=8
2
m1.start()=0
m1.end()=7
m1.start()=7
m1.end()=7
m1.start()=8
m1.end()=8
m2.start()=0
m2.end()=11
m2.start()=11
m2.end()=11
3
m1.start()=0
m1.end()=4
m1.start()=4
m1.end()=8
m2.start()=0
m2.end()=4
m2.start()=4
m2.end()=8
4
		
 */
	}
	
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
		// When a field is not started with a double quote, it does not have to be multi-lined.
		
		//TODO Need a haul for $ differing \\\n, see testIsDollarEndOfLineSame
		final String fieldDef1 = "^[^\\\"].*?[,|$|"+endOfLineSymbol+"|\\\n]";
/*
=====
|
,|__p1
----
b5,,d5
=====		
 */
		
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
	public void testIsDollarEndOfLineSame () {
		String data1 = "a\nb";
		String data2 = "a\n";
		final String endOfLineSymbol = System.getProperty("line.separator");
		for (String s: data1.split(endOfLineSymbol)) {
			System.out.println("|"+s+"|");
		}
		System.out.println("=====");
		for (String s: data1.split("$")) {
			System.out.println("|"+s+"|");
		}

/*
|a|
|b|
=====
|a
b|		
 */
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
