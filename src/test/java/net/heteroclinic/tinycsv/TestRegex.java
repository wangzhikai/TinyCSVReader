package net.heteroclinic.tinycsv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex {
	@Test
	public void testBeginEndEndSymbol() {
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

}
