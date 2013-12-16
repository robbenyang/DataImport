package com.noteofcourse.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class Parser {
	public static File pdfToText(URL input) {
		PDDocument pd;
		BufferedWriter wr;
		File output = null;
		String input_str = input.toString();
		String save_name = input_str.substring(input_str.lastIndexOf('/'),
				input_str.lastIndexOf('.'));
		String path_txt = "./txt/" + save_name + ".txt";
		try {

			output = new File(path_txt); // The text file where you are going to
											// store the extracted data
			if (!output.exists()) {
				output.createNewFile();
			}
			pd = PDDocument.load(input);
			PDFTextStripper stripper = new PDFTextStripper();
			wr = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(output)));
			stripper.writeText(pd, wr);
			if (pd != null) {
				pd.close();
			}
			// I use close() to flush the stream.
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * @param addr: an address to a course website
	 * Parse a http address and fetch all links to pdfs on this webpage
	 */
	public static ArrayList<String> linkToPdf(String addr) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		HtmlCleaner cleaner = new HtmlCleaner();

		TagNode node = null;
		try {
			node = cleaner.clean(new URL(addr));
		} catch (IOException e) {
			System.out.println("Unable to clean address!");
		}

		// Grabs all hyper link nodes
		TagNode[] Hyper_tags = node.getElementsByName("a", true);

		for (int i = 0; i < Hyper_tags.length; i++) {
			// Gets all child tags
			TagNode[] children = Hyper_tags[i].getChildTags();

			// Removes all child tags if any
			if (children.length > 0)
				for (int j = 0; j < children.length; j++)
					Hyper_tags[i].removeChild(children[j]);

			// Extracts the link of the node
			String link = Hyper_tags[i].getAttributeByName("href");

			// Grabs link name and pdf file
			if (link != null && link.contains(".pdf")) {
				// Appends absolute URL if not already absolute
				if (!link.contains("http"))
					link = "https://wiki.engr.illinois.edu" + link;
				String name = cleaner.getInnerHtml(Hyper_tags[i]).trim();
				//System.out.println(name + ": " + link + "\n");
				list.add(link);
			}
		}

		return list;
	}
}
