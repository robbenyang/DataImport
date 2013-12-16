import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

import com.noteofcourse.database.Insertion;
import com.noteofcourse.parser.Parser;
import com.noteofcourse.text_processor.Processor;
import com.noteofcourse.text_processor.Tag;

public class Testor {
	static ArrayList<String> tag_list = new ArrayList<String>();

	public static void main(String[] args) throws IOException, SQLException {
		/*
		 * This piece of code is used for insert tag values to the tag table
		 */
		String tag_file_name = "all_tag";
		File tag_name = new File(tag_file_name);
		TreeSet<String> tags = Tag.loadTag(tag_name);
		while (!tags.isEmpty()) {
			String tag = tags.first();
			tags.remove(tag);
			tag_list.add(tag);
			//System.out.println(tag);
		}

		// Insertion.insertTags(tags);

		String addr = "https://wiki.engr.illinois.edu/display/cs411fa13/Schedule";
		ArrayList<String> list = Parser.linkToPdf(addr);
		Insertion insertor = new Insertion();
		int i = 0;
		while (!list.isEmpty()) {
			String link = list.remove(0);
			URL url = new URL(link);
			File output = Parser.pdfToText(url);
			String text = Processor.file_to_string(output);
			String name = output.getName();
			name = name.substring(0, name.lastIndexOf('.'));
			text = Processor.trimmer(text);
			if(link.toLowerCase().indexOf("written") >= 0)
				continue;
			if(text.length() == 0)
				continue;
			System.out.println("name: "+ name + ", url: "+url);
			int key = insertor.insertNotes(text, name, link);
			Tag.addTag(text, key, insertor, tag_list);
			System.out.println(key);
			if(i++ > 5)
				break;
		}
		insertor.close();

		return;
	}
}
