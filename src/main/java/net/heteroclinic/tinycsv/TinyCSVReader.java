package net.heteroclinic.tinycsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TinyCSVReader {

	public static void main(String[] args) throws FileNotFoundException {
		{
			// Need copy test/resources files to main/resources folder
			final String data =  new Scanner(new File(TinyCSVReader.class.getClassLoader().getResource("testfile2").getFile())).useDelimiter("\\Z").next();
			//! final String data =  new Scanner(new File(new TinyCSVReader ().getClass().getResource("testfile2").getFile())).useDelimiter("\\Z").next();

			System.out.println("=== 1 ===");
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
				//System.out.println("|"+data.substring(m.start(), m.end())+"|");
				//System.out.println("----");
				String record = data.substring(m.start(), m.end());
				if (record.trim().length() <= 0)
					continue;
				if (!record.contains("\"")) {
					record = record.replaceAll("\n$", "");
					// split is not a good idea see split test
					Pattern regularCommaPattern = Pattern.compile("(.*?)(,)");
					Matcher regularCommaMatcher = regularCommaPattern.matcher(record);
					System.out.print("|");
					int commaCount = 0;
					int consumedLength = 0;
					while (regularCommaMatcher.find()) {
						String field = record.substring(regularCommaMatcher.start(),regularCommaMatcher.end());
						// remove trailing ","
						consumedLength += field.length();
						field = field.replaceAll(",$", "");
						System.out.print(field);
						System.out.print("|");
						commaCount++;						
					}
					String field  = record.substring(consumedLength);
					System.out.print(field);
					System.out.print("|");
				} else {
					
				}
				System.out.println("\n----------");
			}
		}
	}

}
