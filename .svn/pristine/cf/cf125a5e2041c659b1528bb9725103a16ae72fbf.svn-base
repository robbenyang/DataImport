package com.noteofcourse.text_processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Processor {
	public static String file_to_string(File f) throws IOException{
		BufferedReader reader = null;
		String line;
		StringBuilder builder = new StringBuilder();
		reader = new BufferedReader(new FileReader(f));
		while((line = reader.readLine()) != null){
			builder.append(line);
			//builder.append("\n");
		}
		reader.close();
		return builder.toString();
	}
	
	public static String trimmer(String raw){
		raw = raw.toLowerCase().trim();
		char[] array = raw.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < array.length; i++){
			if(array[i] >= 32 && array[i] <= 126 && array[i] != '\'' && array[i] != '"' && array[i] != '\\'){
				builder.append(array[i]);
			}
		}
		return builder.toString();
	}
}
