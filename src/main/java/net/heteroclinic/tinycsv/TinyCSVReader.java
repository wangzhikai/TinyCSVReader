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
			Pattern patternForField = Pattern.compile("(?s)"
					+ "("
					+ "(\\G"
					+ "([^\\\"]*?)"
					+ "([,]|$))"
					+ "|"
					+ "(\\G"
					+ "((([^,]*?)[\\\"](.*?)[\\\"]([^,]*?))+?)"
					+ "([,]|$))"
					+ ")" );
			Matcher m = patternForRecord.matcher(data);
			while (m.find()) {
				String record = data.substring(m.start(), m.end());
				if (record.trim().length() <= 0)
					continue;
				// replace the trailing 
				record = record.replaceAll("\n$", "");
				//System.out.println(record);
				Matcher m2 = patternForField.matcher(record);
				System.out.print("|");
				recordLoop: while (m2.find()) {
					String field = record.substring(m2.start(), m2.end());
					// replace the trailing 
					// if field does not have a comma at the end, it is the last field
					if ( field.length() > 0 && (field.lastIndexOf(",") == field.length()-1)) {
						field = field.replaceAll(",$", "");
						System.out.print(field+"|");
					} else {
						System.out.print(field+"|");
						break recordLoop;
					}
				}
				System.out.println();
				System.out.println("----------");
			}
		}
	}

}
