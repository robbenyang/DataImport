package com.noteofcourse.text_processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

import com.noteofcourse.database.Insertion;

public class Tag {
	static TreeSet<String> all_tags = new TreeSet<String>();

	public static TreeSet<String> loadTag(File f) throws IOException {
		BufferedReader reader = null;
		String line;
		reader = new BufferedReader(new FileReader(f));
		while((line = reader.readLine()) != null){
			if (line.length() > 4) {
				line = line.substring(4);
				while(line.charAt(0)>='0' && line.charAt(0) <= '9'){
					line = line.substring(1);
				}
				line = line.trim().toLowerCase();
				if(line.length() != 0){
					all_tags.add(line);
//					System.out.println(line);
				}
			}
		}
		reader.close();
		return new TreeSet<String>(all_tags);
	}
	
	public static void addTag(String text, int noteID, Insertion insertor, ArrayList<String> tags) throws SQLException{
		int index;
		for(int i = 0; i < tags.size();i++){
			String curr_tag = tags.get(i);
			index = text.indexOf(curr_tag);
			if(index >= 0){
				//System.out.println("note ID: "+noteID + " "+ text.substring(0,20) + ", "+"tagID: "+i+ ", tag: "+curr_tag + ", index: "+index);
				insertor.insertNoteTag(noteID, i+1);
			}
		}
	}
}
