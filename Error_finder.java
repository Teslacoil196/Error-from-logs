package com.Tesla.Fileerer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Errorfinder2 {
	public static void main(String[] args) throws IOException {
		
		final String regex = "^\\W\\d{1,2}\\W\\d{1,2}\\W\\d{2}\\s\\d{1,2}\\W\\d{2}\\W\\d{2}\\W\\d{3}\\sBST\\W\\s\\w{8}\\s[a-zA-Z]{5,13}\\s{1,2}I";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		BufferedReader reader;
		Calendar instance = Calendar.getInstance();
		String string = instance.getTime().toString();
		
		FileWriter full = new FileWriter(string +"-logs.txt");
		System.out.println("Started");
		// I recommend just to copy path from file explorer
		File folder = new File("C://folder-with-log-files"); 
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			
			if (file.isFile() && file.getName().endsWith(".log")) {
				//System.out.println("...1");System.out.println("...2");System.out.println("...3");
				System.out.println("Name :" + file.getName());
				
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				
				while(line != null) {
					Matcher matcher = pattern.matcher(line);
					if(!matcher.find()) {
						full.write(line+'\n');
					}
					line = reader.readLine();
				}
			}

		}
		full.close();
		System.out.println("Ended");
	}

}
